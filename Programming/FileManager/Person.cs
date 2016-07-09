using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Manager
{
    class Person
    {
        public string _name;
        public string _faculty;
        public string _cathedra;
        public string _specialty;
        public string _entryYear;
        public string _graduationYear;

        public Person(string name1, string faculty1, string cathedra1, string specialty1, string entryYear1, string graduationYear1)
        {
            _name = name1;
            _faculty = faculty1;
            _cathedra = cathedra1;
            _specialty = specialty1;
            _entryYear = entryYear1;
            _graduationYear = graduationYear1;
        }

        public Person()
        {
            _name = "";
            _faculty = "";
            _cathedra = "";
            _specialty = "";
            _entryYear = "";
            _graduationYear = "";
        }
    }
}
