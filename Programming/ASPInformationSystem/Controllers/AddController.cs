using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNet.Mvc;
using ASPInformationSystem.Models;
using Microsoft.AspNet.Mvc.Rendering;

namespace ASPInformationSystem.Controllers
{
    public class AddController : Controller
    {
        ArchiveLibraryContext db;

        public AddController(ArchiveLibraryContext context)
        {
            db = context;
        }

        [HttpGet]
        public IActionResult Index()
        {
            ViewBag.Categories = db.Categories.Where(x => x.ID > 1);
            ViewBag.Faculties = db.Faculties.Where(x => x.ID > 1);
            ViewBag.Departments = db.Departments.Where(x => x.ID > 1);
            return View();
        }

        [HttpPost]
        public IActionResult Result(Archive ar)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    db.Archives.Add(ar);
                    return View();
                }
                catch
                {
                    db.Remove(ar);
                    return HttpBadRequest("Something went wrong. Please try again.");
                }
                finally
                {
                    db.SaveChanges();
                }
            }
            else
                return HttpBadRequest("Your data is not correct. Please, enter correct data.");
        }
    }
}
