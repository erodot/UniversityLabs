using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ASPInformationSystem.Models
{
    public class ArchiveTable
    {
        public int ID { get; set; }
        public int PublicationID { get; set; }
        public string Name { get; set; }
        public string Authors { get; set; }
        public string Category { get; set; }
        public string Faculty { get; set; }
        public string Department { get; set; }
        public string Amount { get; set; }
        public string Date { get; set; }
    }
}
