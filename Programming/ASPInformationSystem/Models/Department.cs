using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ASPInformationSystem.Models
{
    public class Department
    {
        public int ID { get; set; }
        public string Name { get; set; }

        public int FacultyID { get; set; }
        public Faculty Faculty { get; set; }
    }
}
