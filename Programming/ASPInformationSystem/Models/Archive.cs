using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace ASPInformationSystem.Models
{
    public class Archive
    {
        public int ID { get; set; }

        [Display(Name = "Назва:")]
        [UIHint("String")]
        [Required(ErrorMessage ="Поле не може бути порожнім")]
        public string Name { get; set; }

        [Display(Name = "Автор(и):")]
        [UIHint("String")]
        [Required(ErrorMessage = "Поле не може бути порожнім")]
        public string Authors { get; set; }

        [Display(Name = "К-сть сторінок:")]
        [UIHint("int")]
        [Range(1, 2048, ErrorMessage = "Некоректне значення")]
        [Required(ErrorMessage = "Поле не може бути порожнім")]
        public int Amount { get; set; }

        [Display(Name = "Рік публікації:")]
        [UIHint("int")]
        [Range(1950, 2020, ErrorMessage = "Некоректний рік")]
        [Required(ErrorMessage = "Поле не може бути порожнім")]
        public int Year { get; set; }

        public int CategoryID { get; set; }
        public Category Category { get; set; }

        public int DepartmentID { get; set; }
        public Department Department { get; set; }
    }
}
