using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNet.Mvc;
using ASPInformationSystem.Models;
using Microsoft.Data.Entity;
using Microsoft.AspNet.Mvc.Rendering;
using Microsoft.AspNet.Mvc.ViewFeatures;

namespace ASPInformationSystem.Controllers
{
    public class SearchController : Controller
    {
        ArchiveLibraryContext db;

        public SearchController(ArchiveLibraryContext context)
        {
            db = context;
        }

        [HttpGet]
        public IActionResult Index()
        {
            ViewBag.Categories = db.Categories;
            ViewBag.Faculties = db.Faculties;
            ViewBag.Departments = db.Departments;
            return View();
        }

        [HttpPost]
        public IActionResult Result(Archive sr) //search result
        {
            try
            {
                var query = from arc in db.Archives
                            join cat in db.Categories on arc.CategoryID equals cat.ID
                            join dep in db.Departments on arc.DepartmentID equals dep.ID
                            join fac in db.Faculties on dep.FacultyID equals fac.ID
                            select new { Name = arc.Name, Authors = arc.Authors, Category = cat.Name, Faculty = fac.Name, Department = dep.Name, Date = arc.Year.ToString(),Amount = arc.Amount };

                if (sr.Name != null) query = query.Where(x => x.Name.Contains(sr.Name));
                if (sr.Authors != null) query = query.Where(x => x.Authors.Contains(sr.Authors));
                if (sr.CategoryID != 1) query = query.Where(x => x.Category == (from c in db.Categories where c.ID == sr.CategoryID select c.Name).FirstOrDefault());
                if (sr.DepartmentID != 1) query = query.Where(x => x.Department == (from d in db.Departments where d.ID == sr.DepartmentID select d.Name).FirstOrDefault());
                if (sr.Year != 0) query = query.Where(x => x.Date == sr.Year.ToString());

                var all = from c in db.ArchiveTables select c;
                db.ArchiveTables.RemoveRange(all);
                db.SaveChanges();

                foreach (var q in query)
                {
                    db.ArchiveTables.Add(new ArchiveTable {
                        Name = q.Name,
                        Authors = q.Authors,
                        Category = q.Category,
                        Faculty = q.Faculty,
                        Department = q.Department,
                        Amount = q.Amount.ToString(),
                        Date = q.Date });
                }
                db.SaveChanges();
                return View(db.ArchiveTables);
            }
            catch
            {
                return HttpBadRequest("Connection to database unexpectedly dropped. Please retry search.");
            }
        }
    }
}
