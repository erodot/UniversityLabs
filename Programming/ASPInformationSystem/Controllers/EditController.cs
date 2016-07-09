using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNet.Mvc;
using ASPInformationSystem.Models;
using Microsoft.Data.Entity;
using Microsoft.AspNet.Mvc.Rendering;

namespace ASPInformationSystem.Controllers
{
    public class EditController : Controller
    {
        ArchiveLibraryContext db;

        public EditController(ArchiveLibraryContext context)
        {
            db = context;
        }

        [HttpGet]
        public IActionResult Index(int id)
        {
            Archive arc = db.Archives.Where(x => x.ID == id).First();

            ViewBag.ID = id;
            ViewBag.Name = (from a in db.Archives where a.ID==id select a.Name).FirstOrDefault();
            ViewBag.Authors = (from a in db.Archives where a.ID == id select a.Authors).FirstOrDefault();
            ViewBag.Departments = db.Departments.Where(x=>x.ID>1).ToList();
            int selectedDepartmentID = (from a in db.Archives where a.ID == id select a.DepartmentID).FirstOrDefault();
            ViewBag.SelectedDepartmentID = selectedDepartmentID;
            ViewBag.Faculties = db.Faculties.Where(x => x.ID > 1).ToList();
            ViewBag.SelectedFacultyID = (from d in db.Departments where d.ID == selectedDepartmentID select d.FacultyID).FirstOrDefault();
            ViewBag.Categories = db.Categories.Where(x => x.ID > 1).ToList();
            ViewBag.SelectedCategoryID = (from a in db.Archives where a.ID == id select a.CategoryID).FirstOrDefault();
            ViewBag.Amount = (from a in db.Archives where a.ID == id select a.Amount).FirstOrDefault();
            ViewBag.Year = (from a in db.Archives where a.ID == id select a.Year).FirstOrDefault();
            return View(arc);
        }

        [HttpPost]
        public IActionResult Result(Archive arc)
        {
            try
            {
                db.Archives.Attach(arc);
                db.Entry(arc).State = EntityState.Modified;
                db.SaveChanges();
                return View();
            }
            catch
            {
                return HttpBadRequest("Something went wrong. Please try again.");
            }
            
        }
    }
}
