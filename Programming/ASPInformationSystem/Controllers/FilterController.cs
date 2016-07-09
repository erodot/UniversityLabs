using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNet.Mvc;
using ASPInformationSystem.Models;
using Microsoft.Data.Entity;
using Microsoft.AspNet.Mvc.Rendering;
using Newtonsoft.Json;

namespace ASPInformationSystem.Controllers
{
    public class FilterController : Controller
    {
        ArchiveLibraryContext db;

        public FilterController(ArchiveLibraryContext context)
        {
            db = context;
        }

        // GET: /<controller>/
        public IActionResult Index(int categoryID=1, int facultyID=1, int departmentID=1)
        {
            try
            {
                var all = from c in db.ArchiveTables select c;
                db.ArchiveTables.RemoveRange(all);
                db.SaveChanges();

                foreach (Archive arc in db.Archives.ToList())
                {
                    if (categoryID > 1 && categoryID != arc.CategoryID)
                        continue;
                    if (facultyID > 1 && facultyID != (from dep in db.Departments where arc.DepartmentID == dep.ID select dep.FacultyID).FirstOrDefault())
                        continue;
                    if (departmentID > 1 && departmentID != arc.DepartmentID)
                        continue;
                    string _Category = (from cat in db.Categories where cat.ID == arc.CategoryID select cat.Name).FirstOrDefault();
                    string _Department = (from dep in db.Departments where dep.ID == arc.DepartmentID select dep.Name).FirstOrDefault();
                    string _Faculty = (from fac in db.Faculties where fac.ID == (from dep in db.Departments where dep.Name == _Department select dep.FacultyID).FirstOrDefault() select fac.Name).FirstOrDefault();
                    db.ArchiveTables.Add(new ArchiveTable { PublicationID=arc.ID, Name = arc.Name, Authors=arc.Authors, Category = _Category, Faculty = _Faculty, Department = _Department, Amount = arc.Amount.ToString(), Date = arc.Year.ToString() });
                }
                db.SaveChanges();

                ViewBag.Categories = db.Categories;
                ViewBag.Faculties = db.Faculties;
                ViewBag.Departments = db.Departments;
                ViewBag.CategoryID = categoryID;
                ViewBag.FacultyID = facultyID;
                ViewBag.DepartmentID = departmentID;

                return View(db.ArchiveTables);
            }
            catch
            {
                return HttpBadRequest("Connection to database unexpectedly dropped. Please reload the page.");
            }
        }

        [HttpPost]
        public JsonResult JGet()
        {
            List<JFaculty> JFacultyList = new List<JFaculty>();

            foreach (Faculty fac in db.Faculties)
            {
                JFaculty new_fac = new JFaculty();
                new_fac.FacultyID = fac.ID;
                new_fac.FacultyName = fac.Name;
                new_fac.DepartmentsList = (from dep in db.Departments where dep.FacultyID == fac.ID select new JDepartment(dep.ID, dep.Name)).ToList();

                JFacultyList.Add(new_fac);
            }

            return Json(JFacultyList);
        }
    }

    public class JDepartment
    {
        public JDepartment(int _id, string _name)
        {
            this.DepartmentID = _id;
            this.DepartmentName = _name;
        }

        public int DepartmentID { get; set; }
        public string DepartmentName { get; set; }
    }

    public class JFaculty
    {
        public int FacultyID { get; set; }
        public string FacultyName { get; set; }
        public List<JDepartment> DepartmentsList { get; set; }
    }
}
