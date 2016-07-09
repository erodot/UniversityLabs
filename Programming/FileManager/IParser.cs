using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Manager
{
    interface IParser
    {
        List<Person> Search(Person filter);
    }
}
