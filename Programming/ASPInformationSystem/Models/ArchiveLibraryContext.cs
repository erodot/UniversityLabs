using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.Data.Entity;

namespace ASPInformationSystem.Models
{
    public class ArchiveLibraryContext : DbContext
    {
        public DbSet<Archive> Archives { get; set; }
        public DbSet<ArchiveTable> ArchiveTables { get; set; }
        public DbSet<Category> Categories { get; set; }
        public DbSet<Department> Departments { get; set; }
        public DbSet<Faculty> Faculties { get; set; }
    }
}
