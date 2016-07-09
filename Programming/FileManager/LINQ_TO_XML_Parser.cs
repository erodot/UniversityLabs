using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;
using System.Windows.Forms;

namespace Manager
{
    class LINQ_TO_XML_Parser:IParser
    {
        string path;

        public LINQ_TO_XML_Parser(string path)
        {
            this.path = path;
        }

        internal DOM_XML_Parser DOM_XML_Parser
        {
            get
            {
                throw new System.NotImplementedException();
            }

            set
            {
            }
        }

        internal SAX_XML_Parser SAX_XML_Parser
        {
            get
            {
                throw new System.NotImplementedException();
            }

            set
            {
            }
        }

        public List<Person> Search(Person filter)
        {
            XDocument doc;
            try { doc = XDocument.Load(path); }
            catch { MessageBox.Show("XML не знайдено!", "Помилка"); return null; }

            List<Person> PeopleList = new List<Person>();
            IEnumerable<Person>  persons = from ev in doc.Descendants("alumnus") //LINQ Query
                             select new Person(
                             ev.Element("name").Value,
                             ev.Element("faculty").Value,
                             ev.Element("cathedra").Value,
                             ev.Element("specialty").Value,
                             ev.Element("years").Element("entry").Value,
                             ev.Element("years").Element("graduation").Value
                             );

            foreach(Person person in persons)
            {
                if (Algorythms.Check(person, filter)) PeopleList.Add(person);
            }

            return PeopleList;
        }
    }
}
