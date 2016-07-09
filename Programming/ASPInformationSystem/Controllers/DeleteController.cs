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
    public class DeleteController : Controller
    {
        ArchiveLibraryContext db;

        public DeleteController(ArchiveLibraryContext context)
        {
            db = context;
        }

        [HttpGet]
        public IActionResult Index(int id)
        {
            ViewBag.ID = id;
            return View();
        }

        [HttpGet]
        public IActionResult Result(int id)
        {
            try
            {
                var delet = (from a in db.Archives where a.ID == id select a).First();
                db.Archives.Remove(delet);
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
