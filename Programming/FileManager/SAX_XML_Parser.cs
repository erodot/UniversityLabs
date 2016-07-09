using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;

namespace Manager
{
    class SAX_XML_Parser:IParser
    {
        private string path;

        public SAX_XML_Parser(string path)
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
            XmlReader xmlReader;
            try { xmlReader = new XmlTextReader(path); }
            catch { return null; }
            List<Person> PeopleList = new List<Person>();

            try
            {
                xmlReader.ReadToFollowing("AlumniList");
                while (xmlReader.Read())
                {
                    if (xmlReader.Name.Equals("alumnus") && xmlReader.NodeType == XmlNodeType.Element) //find 0-level-node
                    {
                        Person p = new Person();
                        p = ParseItemNode(xmlReader.ReadSubtree()); //parse every alumnus

                        if (Algorythms.Check(p, filter)) PeopleList.Add(p); //if pupil satisfy search filters -> save pupil
                    }
                }
            }
            catch (Exception e) { throw e; }
            finally { xmlReader.Close(); }

            return PeopleList;
        }

        private Person ParseItemNode(XmlReader xmlReader)
        {
            Person p = new Person();

            while (xmlReader.Read()) //parse every node in the subtree of <alumnus>
            {
                if (xmlReader.NodeType == XmlNodeType.Element)
                {
                    switch (xmlReader.Name)
                    {
                        case "name":
                            p._name = xmlReader.ReadElementContentAsString();
                            break;

                        case "faculty":
                            p._faculty = xmlReader.ReadElementContentAsString();
                            break;

                        case "cathedra":
                            p._cathedra = xmlReader.ReadElementContentAsString();
                            break;

                        case "specialty":
                            p._specialty = xmlReader.ReadElementContentAsString();
                            break;

                        case "years":
                            ParseYearsNode(xmlReader.ReadSubtree(),ref p);
                            break;
                    }
                }

                else if (xmlReader.Name.Equals("alumnus") && xmlReader.NodeType == XmlNodeType.EndElement)
                {
                    break;
                }
            }

            return p;
        }

        private void ParseYearsNode(XmlReader xmlReader, ref Person p) //determination entry year and graduation year
        {
            while (xmlReader.Read())
            {
                if (xmlReader.NodeType == XmlNodeType.Element)
                {
                    switch (xmlReader.Name)
                    {
                        case "entry":
                            p._entryYear = xmlReader.ReadElementContentAsString();
                            break;

                        case "graduation":
                            p._graduationYear = xmlReader.ReadElementContentAsString();
                            break;
                    }
                }
                else if (xmlReader.Name.Equals("place") && xmlReader.NodeType == XmlNodeType.EndElement)
                {
                    break;
                }
            }
        }
    }
}
