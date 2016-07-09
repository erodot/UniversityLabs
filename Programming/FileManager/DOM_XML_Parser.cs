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
    class DOM_XML_Parser:IParser
    {
        string path;

       public DOM_XML_Parser(string path)
        {
            this.path = path;
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

        internal LINQ_TO_XML_Parser LINQ_TO_XML_Parser
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
            var xmlDoc = new XmlDocument();
            try { xmlDoc.Load(path); }
            catch
            {
                MessageBox.Show("XML не знайдено!","Помилка");
                return null;
            }
            List<Person> PeopleList = new List<Person>();
            XmlNode root = xmlDoc.DocumentElement;

            foreach (XmlNode alumnus in root.ChildNodes) //foreach 0-level-node
            {
                if (alumnus.NodeType == XmlNodeType.Element)
                {
                    Person person = new Person();
                    person._name = (alumnus["name"].InnerText);
                    person._faculty = (alumnus["faculty"].InnerText);
                    person._cathedra = (alumnus["cathedra"].InnerText);
                    person._specialty = (alumnus["specialty"].InnerText);

                    XmlNode node = alumnus["years"]; //if 1-level-node exists
                    if (node["entry"] != null) person._entryYear = node["entry"].InnerText;
                    if (node["graduation"] != null) person._graduationYear = node["graduation"].InnerText;

                    if (Algorythms.Check(person, filter)) PeopleList.Add(person); //if pupil satisfy search filters -> save pupil
                }
            }

            return PeopleList;
        }
    }
}
