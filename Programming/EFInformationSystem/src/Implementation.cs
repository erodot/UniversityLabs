using System;
using System.Data;
using System.Linq;
using System.IO;
using System.Text;
using System.Windows.Forms;
using EntityFramework.Extensions;
using System.Data.Entity;
using System.Collections.Generic;
using ArchiveDataEF;

namespace EFInformationSystem
{
    public class BaseForm : Form
    {
        /// <summary>
        /// Incapsulate working with forms
        /// </summary>
        /// <param name="form"></param>
        protected void LaunchForm(Form form)
        {
            form.ShowDialog();
            form.Dispose();
        }

        /// <summary>
        /// Changing departmentComboBox values according to selected faculty
        /// </summary>
        protected void FacultyBindedEdit(ArchiveDataEF.DBEArchiveEntities ctx, ComboBox facultyComboBox, ComboBox departmentComboBox, BindingSource departmentBindingSource, bool isFormClosing)
        {
            if (!isFormClosing)
                try
                {
                    int faculty_id = facultyComboBox.SelectedIndex;
                    departmentComboBox.Enabled = (faculty_id > 0);
                    departmentBindingSource.DataSource = (from d in ctx.Departments where d.FacultyID == faculty_id || d.FacultyID == 0 select d).ToList();
                }
                catch (System.NullReferenceException)
                {
                    //not bug
                }
                catch
                {
                    MessageBox.Show("Помилка оновлення даних про кафедри факультету \"" + facultyComboBox.Text + "\".", "Помилка");
                }
        }

        /// <summary>
        /// Save current DataGridView records to XML file
        /// </summary>
        protected void SaveToXML(DataGridView dGV)
        {
            try
            {
                FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
                folderBrowserDialog.RootFolder = Environment.SpecialFolder.Desktop;
                folderBrowserDialog.ShowNewFolderButton = true;
                folderBrowserDialog.Description = "Виберіть папку для збереження XML-файлу:";
                DialogResult result = folderBrowserDialog.ShowDialog();
                if (result == DialogResult.OK)
                {
                    DataTable data = this.GetDataTableFromDataGridView(dGV);

                    string folderName = folderBrowserDialog.SelectedPath;
                    string path = folderName + "\\index.xml";

                    if (File.Exists(path))
                        File.Delete(path);

                    using (FileStream fs = File.Create(path))
                    {
                        Byte[] info = new UTF8Encoding(true).GetBytes("<?xml version=\"1.0\" encoding=\"UTF - 8\" standalone=\"yes\"?>" + System.Environment.NewLine);
                        fs.Write(info, 0, info.Length);
                        data.WriteXml(fs);
                    }
                    MessageBox.Show("Файл успішно створено! ", "Успіх");
                }
            }
            catch
            {
                MessageBox.Show("Не вдалось зберегти файл. Спробуйте ще раз.", "Помилка");
            }

        }

        protected DataTable GetDataTableFromDataGridView(DataGridView dGV)
        {
            DataTable dt = new DataTable();
            dt.TableName = "record";

            foreach (DataGridViewColumn column in dGV.Columns)
            {
                DataColumn dc = new DataColumn(column.DataPropertyName.ToString());
                dt.Columns.Add(dc);
            }


            for (int i = 0; i < dGV.Rows.Count; i++)
            {
                DataGridViewRow row = dGV.Rows[i];
                DataRow dr = dt.NewRow();
                for (int j = 0; j < dGV.Columns.Count; j++)
                {
                    dr[j] = (row.Cells[j].Value == null) ? "" : row.Cells[j].Value.ToString();
                }
                dt.Rows.Add(dr);
            }

            foreach (DataGridViewColumn column in dGV.Columns)
            {
                if (!column.Visible)
                    dt.Columns.Remove(column.DataPropertyName);
            }
            return dt;
        }
    }

    public partial class MainForm:BaseForm
    {
        private ArchiveDataEF.DBEArchiveEntities ctx;

        /// <summary>
        /// Value that indicates is form closing at the moment
        /// </summary>
        private bool isFormClosing = false;

        /// <summary>
        /// Value that indicates if the change of departmentComboBox is caused 
        /// by facultyComboBox.SelectedIndexChangedEvent
        /// </summary>
        private bool isFacultyChanging = false;

        /// <summary>
        /// Fill all DataGridViews and ComboBoxes after program started
        /// </summary>
        private void DefaultFill()
        {
            try
            {
                this.ctx = new ArchiveDataEF.DBEArchiveEntities();

                this.categoryBindingSource.DataSource = this.ctx.Categories.Future().ToList();
                this.facultyBindingSource.DataSource = this.ctx.Faculties.Future().ToList();
                this.departmentBindingSource.DataSource = this.ctx.Departments.Future().ToList();
            }
            catch
            {
                MessageBox.Show("Помилка завантаження даних з бази. Перезапустіть програму.", "Помилка");
                this.Hide();
                this.Dispose();
            }

            this.departmentComboBox.Enabled = false;
            this.RefreshDGV();
            this.dGVMainForm.Select();
        }

        /// <summary>
        /// Refresh MainForm.DataGridView
        /// </summary>
        private void RefreshDGV()
        {
            if (!this.isFormClosing)
                try
                {
                    int category_id = this.categoryComboBox.SelectedIndex;
                    int faculty_id = this.facultyComboBox.SelectedIndex;
                    int department_id = this.departmentComboBox.SelectedIndex;

                    var arc = this.ctx.Archives.Future();
                    var pub = this.ctx.Publications.Future();
                    var cat = this.ctx.Categories.Future();
                    var fac = this.ctx.Faculties.Future();
                    var dep = this.ctx.Departments.Future();
                    var Query = (from a in arc
                                         join p in pub on a.PubID equals p.ID
                                         join c in cat on a.CategoryID equals c.ID
                                         join f in fac on a.FacultyID equals f.ID
                                         join d in dep on a.DepartmentID equals d.ID
                                 select new
                                         {
                                             Publication = p.Name,
                                             Authors = this.ctx.AuthorsOfPub(p.ID).First().Authors,
                                             Category = c.Name,
                                             Faculty = f.Name,
                                             Department = d.Name,
                                             Amount = a.Amount,
                                             PubDate = a.PubDate,
                                             ID = a.ID,
                                             PubID =a.PubID,
                                             FacultyID=a.FacultyID,
                                             CategoryID=a.CategoryID,
                                             DepartmentID=a.DepartmentID
                                         });
                    if(category_id>0)
                        Query = Query.Where(x => x.CategoryID==category_id);
                    if (faculty_id > 0)
                        Query = Query.Where(x => x.FacultyID == faculty_id);
                    if (department_id > 0)
                        Query = Query.Where(x => x.DepartmentID == department_id);
                    
                    this.bindingSource.DataSource = Query.OrderBy(x=>x.Publication).ToList();        
                }
                catch (System.Data.Entity.Core.EntityCommandExecutionException)
                {
                    //empty table
                }
                catch
                {
                    MessageBox.Show("Помилка оновлення даних. Будь ласка, перезапустіть програму.\n", "Помилка");
                }
        }

        /// <summary>
        /// Delete selected on MainForm.DataGridView publications from database
        /// </summary>
        private void DeleteSelectedRows()
        {
            if(MessageBox.Show("Ви справді хочете видалити?","Видалення", MessageBoxButtons.YesNo)==DialogResult.Yes)
                try
                {
                    foreach (DataGridViewRow row in this.dGVMainForm.SelectedRows)
                    {
                        int pub_id = (int)(row.Cells["pubIDDataGridViewTextBoxColumn"].Value);
                        int id = (int)(row.Cells["iDDataGridViewTextBoxColumn"].Value);
                        this.ctx.AuthorsOfPublications.Where(a => a.PublicationID == pub_id).Delete();
                        this.ctx.Archives.Where(r => r.ID == id).Delete();
                        this.ctx.Publications.Where(p => p.ID == pub_id).Delete();
                    }
                }
                catch
                {
                    MessageBox.Show("Помилка видалення публікації. База даних пошкоджена.", "Помилка");
                }
        }

        /// <summary>
        /// Update publication name if [Publication name] cell is clicked
        /// </summary>
        private void UpdateInformation()
        {
            try
            {
                foreach (DataGridViewCell cell in this.dGVMainForm.SelectedCells)
                {
                    if (cell == (cell.OwningRow).Cells["publicationDataGridViewTextBoxColumn"])
                        this.LaunchForm(new UpdatePublicationNameForm(cell.OwningRow));
                }
                this.ctx.Dispose();
                this.ctx = new ArchiveDataEF.DBEArchiveEntities();
                this.RefreshDGV();
            }
            catch
            {
                MessageBox.Show("Помилка оновленн інформації. Будь ласка, спробуйте ще раз.", "Помилка");
            }
        }
    }

    public partial class AddAuthorForm:Form
    {
        private ArchiveDataEF.DBEArchiveEntities ctx;

        /// <summary>
        /// Returns a copy of this string with capitalized first letter
        /// </summary>
        /// <param name="s"></param>
        /// <returns></returns>
        static string UppercaseFirst(string s)
        {
            // Check for empty string.
            if (string.IsNullOrEmpty(s))
            {
                return string.Empty;
            }
            // Return char and concat substring.
            return char.ToUpper(s[0]) + s.Substring(1);
        }

        /// <summary>
        /// Returns a copy of this string in normal form. (First letter capitalized, other - lowercase) 
        /// </summary>
        /// <param name="s"></param>
        /// <returns></returns>
        static string Standartify(string s)
        {
            return UppercaseFirst(s.ToLower());
        }

        /// <summary>
        /// Incapsulate all work on adding author
        /// </summary>
        private void AddAuthor()
        {
            if (this.surnameTextBox.Text != "" && this.firstNameTextBox.Text != "" && this.secondNameTextBox.Text != "")
                this.AddAuthor(this.surnameTextBox.Text, this.firstNameTextBox.Text, this.secondNameTextBox.Text);
            else
                MessageBox.Show("Помилка: усі поля повинні бути заповненими!", "Помилка");
        }

        /// <summary>
        /// Add author info into database
        /// </summary>
        /// <param name="surname"></param>
        /// <param name="firstName"></param>
        /// <param name="secondName"></param>
        private void AddAuthor(string surname, string firstName, string secondName)
        {
            Standartify(surname); surname = surname.Trim();
            Standartify(firstName); firstName = firstName.Trim();
            Standartify(secondName); secondName = secondName.Trim();

            try
            {
                string shortName = firstName[0] + ". " + secondName[0] + ". " + surname;
                if ((from a in this.ctx.Authors where a.FirstName == firstName && a.SecondName == secondName && a.Surname == surname select a).Count() > 0)
                {
                    MessageBox.Show("Такий автор вже є в базі.", "Помилка додавання");
                    this.surnameTextBox.Clear();
                    this.firstNameTextBox.Clear();
                    this.secondNameTextBox.Clear();
                    this.surnameTextBox.Select();
                }
                else
                {
                    ArchiveDataEF.Author author = new ArchiveDataEF.Author();
                    author.Surname = surname;
                    author.FirstName = firstName;
                    author.SecondName = secondName;
                    author.ShortName = shortName;
                    this.ctx.Authors.Add(author);

                    this.ctx.SaveChanges();
                    MessageBox.Show("Автор успішно доданий!", "Успіх");
                }
            }
            catch
            {
                MessageBox.Show("Сталася помилка при додаванні автора. Спробуйте ще раз.", "Помилка");
            }
            finally
            {
                this.Hide();
                this.Dispose();
            }
        }
    }

    public partial class AddBookForm:Form
    {
        private ArchiveDataEF.DBEArchiveEntities ctx;

        /// <summary>
        /// Value that indicates is form closing at the moment
        /// </summary>
        private bool isFormClosing;

        /// <summary>
        /// Fill all form components while form launching
        /// </summary>
        private void DefaultFill()
        {
            try
            {
                this.isFormClosing = false;
                this.ctx = new ArchiveDataEF.DBEArchiveEntities();

                var _authors = this.ctx.Authors.OrderBy(a => a.Surname).ToList();
                this.authorFirstBindingSource.DataSource = _authors;
                this.authorSecondBindingSource.DataSource = _authors;
                this.authorThirdBindingSource.DataSource = _authors;

                this.facultyBindingSource.DataSource = this.ctx.Faculties.Where(f => f.ID > 0).ToList();

                this.categoryBindingSource.DataSource = this.ctx.Categories.Where(d => d.ID > 0).ToList();
            }
            catch
            {
                MessageBox.Show("При завантаженні форми виникла помилка. Будь ласка, спробуйте ще раз.", "Помилка");
                this.Hide();
                this.Dispose();
            }

            this.plusAuthorFirstLinkLabel.Hide();
            this.plusAuthorSecondLinkLabel.Hide();
            this.authorSecondComboBox.Hide();
            this.authorThirdComboBox.Hide();
        }

        /// <summary>
        /// Indicates if book name is correct
        /// </summary>
        /// <param name="n"></param>
        /// <returns></returns>
        private bool validateName(string n)
        {
            return n != "";
        }

        /// <summary>
        /// Indicates if publication date is correct and format is right
        /// </summary>
        /// <param name="d"></param>
        /// <returns></returns>
        private bool validateDate(string d)
        {
            //input format 'YYYY-MM-DD'
            if (d.Length != 10)
                return false;
            if (d[4] != '-' || d[7] != '-')
                return false;
            try
            {
                DateTime date = new DateTime(Convert.ToInt32(d.Substring(0, 4)), Convert.ToInt32(d.Substring(5, 2)), Convert.ToInt32(d.Substring(8, 2)));

                if (date <= DateTime.Now && date >= new DateTime(1970, 1, 1))
                    return true;
                else
                    return false;
            }
            catch
            {
                return false;
            }
        }

        /// <summary>
        /// Indicates if input data is natural number
        /// </summary>
        /// <param name="a"></param>
        /// <returns></returns>
        private bool validateAmount(string a)
        {
            int int_a;
            bool flag = Int32.TryParse(a, out int_a);
            return ((flag)&&(int_a>0));
        }

        /// <summary>
        /// Indicates if all fields on form was inputted correctly and ready to add
        /// </summary>
        /// <returns></returns>
        private bool allAddBookFieldsAreOK()
        {
            bool name_flag = validateName(this.nameTextBox.Text);
            bool date_flag = validateDate(this.dateTextBox.Text);
            bool amount_flag = validateAmount(this.amountTextBox.Text);

            if (!name_flag)
            {
                MessageBox.Show("Введіть назву!", "Помилка");
                return false;
            }

            if (((int)this.authorFirstComboBox.SelectedValue == 0) && ((int)this.authorFirstComboBox.SelectedValue == 0) && ((int)this.authorFirstComboBox.SelectedValue == 0))
            {
                MessageBox.Show("Виберіть хоча б одного автора!", "Помилка");
                return false;
            }

            if (!date_flag)
            {
                MessageBox.Show("Перевірте правильність введення дати!\nФормат вводу - YYYY-MM-DD. (Рік-Місяць-День)\nДата не може бути меншою за 1 січня 1970р.", "Помилка");
                return false;
            }

            if (!amount_flag)
            {
                MessageBox.Show("Кількість сторінок може бути лише натуральним числом!", "Помилка");
                return false;
            }

            return true;
        }

        /// <summary>
        /// Checks if record with same parameters is absent in database
        /// </summary>
        /// <param name="PublicationName"></param>
        /// <param name="Authors"></param>
        /// <param name="CategoryID"></param>
        /// <param name="FacultyID"></param>
        /// <param name="DepartmentID"></param>
        /// <param name="Amount"></param>
        /// <param name="Date"></param>
        /// <returns></returns>
        private bool IsAbsentInDB(string PublicationName, string Authors, int CategoryID, int FacultyID, int DepartmentID, int Amount, DateTime Date)
        {
            var arc = this.ctx.Archives.Future();
            var pub = this.ctx.Publications.Future();
            var cat = this.ctx.Categories.Future();
            var fac = this.ctx.Faculties.Future();
            var dep = this.ctx.Departments.Future();

            var Query = (from a in arc
                         join p in pub on a.PubID equals p.ID
                         join c in cat on a.CategoryID equals c.ID
                         join f in fac on a.FacultyID equals f.ID
                         join d in dep on a.DepartmentID equals d.ID
                         select new
                         {
                             Publication = p.Name,
                             Authors = this.ctx.AuthorsOfPub(p.ID).First().Authors,
                             CategoryID = c.ID,
                             FacultyID = f.ID,
                             DepartmentID = d.ID,
                             Amount = (int)a.Amount,
                             PubDate = a.PubDate
                         }).ToList();

            bool absent = true;
            if (this.ctx.Archives.Count() > 0)
                foreach (var book in Query)
                {
                    if (
                        book.Publication == PublicationName &&
                        book.Authors == Authors &&
                        book.CategoryID == CategoryID &&
                        book.FacultyID == FacultyID &&
                        book.DepartmentID == DepartmentID &&
                        book.Amount == Amount &&
                        book.PubDate == Date
                        )
                        absent = false;
                }
            return absent;
        }

        /// <summary>
        /// Adding book info into database
        /// </summary>
        private void AddBook()
        {
            try
            {
                if (!this.allAddBookFieldsAreOK())
                    return;

                //readout
                string name = this.nameTextBox.Text;

                //creating collection of authors
                List<int> authors_id = new List<int>();
                if ((int)this.authorFirstComboBox.SelectedValue != 0)
                    authors_id.Add((int)this.authorFirstComboBox.SelectedValue);
                if ((int)this.authorSecondComboBox.SelectedValue != 0)
                    authors_id.Add((int)this.authorSecondComboBox.SelectedValue);
                if ((int)this.authorThirdComboBox.SelectedValue != 0)
                    authors_id.Add((int)this.authorThirdComboBox.SelectedValue);

                //creating authors cell that will be displayed on the MainForm DataFridView
                string authors="";
                foreach (var au_id in authors_id)
                    authors += (from a in this.ctx.Authors.Local where a.ID == au_id select a.ShortName).First() + ", ";
                authors = authors.Substring(0, authors.Length - 2);

                int faculty_id = (int)this.facultyComboBox.SelectedValue;
                int department_id = (int)this.departmentComboBox.SelectedValue;

                int category_id = (int)this.categoryComboBox.SelectedValue;

                string date_str = this.dateTextBox.Text;
                DateTime date = new DateTime(Convert.ToInt32(date_str.Substring(0, 4)), Convert.ToInt32(date_str.Substring(5, 2)), Convert.ToInt32(date_str.Substring(8, 2)));

                int amount = Int32.Parse(this.amountTextBox.Text);

                //check if book is absent in database
                if (!IsAbsentInDB(name,authors,category_id,faculty_id,department_id,amount,date))
                {
                    MessageBox.Show("Така книга вже є в базі даних!", "Помилка");
                    return;
                }

                //inserting data into database
                Publication newPublicationRecord = new Publication();
                newPublicationRecord.Name = name;
                this.ctx.Publications.Add(newPublicationRecord);
                this.ctx.SaveChanges();

                var pub_ids = (from p in this.ctx.Publications where p.Name == name select p.ID).ToList();
                int pub_id = pub_ids.Last();
                foreach (var author_id in authors_id)
                {
                    AuthorsOfPublication newAOFRecord = new AuthorsOfPublication();
                    newAOFRecord.AuthorID = author_id;
                    newAOFRecord.PublicationID = pub_id;
                    this.ctx.AuthorsOfPublications.Add(newAOFRecord);
                }
                this.ctx.SaveChanges();

                Archive newArchiveRecord = new Archive();
                newArchiveRecord.PubID = pub_id;
                newArchiveRecord.DepartmentID = department_id;
                newArchiveRecord.FacultyID = faculty_id;
                newArchiveRecord.CategoryID = category_id;
                newArchiveRecord.Amount = amount;
                newArchiveRecord.PubDate = date;
                this.ctx.Archives.Add(newArchiveRecord);
                this.ctx.SaveChanges();

                MessageBox.Show("Книга успішно додана!", "Успіх");

                this.Hide();
                this.Dispose();
            }
            catch
            {
                MessageBox.Show("Сталася помилка. Неможливо додати книгу в базу.", "Помилка");
            }

        }

        /// <summary>
        /// Load departments on departmentComboBox that is relevant to current faculty
        /// </summary>
        private void FillDepartments()
        {
            if (!this.isFormClosing)
                try
                {
                    var faculty_id = (from f in this.ctx.Faculties where f.Name == this.facultyComboBox.Text select f.ID).FirstOrDefault();
                    this.departmentBindingSource.DataSource = (from d in this.ctx.Departments where d.FacultyID == faculty_id select d).ToList();
                }
                catch (System.NullReferenceException)
                {
                    //not bug, just closing form
                }
                catch
                {
                    //bug
                    MessageBox.Show("Помилка завантаження даних про кафедри. Будь ласка, перезавантажте програму.", "Помилка");
                }
        }
         
        /// <summary>
        /// Process authorFirstComboBox_SelectedIndexChangedEvent
        /// </summary>
        private void AuthorFirstChanged()
        {
            try
            {
                int author_first_id = (int)this.authorFirstComboBox.SelectedValue;
                int author_second_id = (int)this.authorSecondComboBox.SelectedValue;
                int author_third_id = (int)this.authorThirdComboBox.SelectedValue;
                if (author_first_id != 0 && (author_first_id == author_second_id || author_first_id == author_third_id))
                {
                    MessageBox.Show("Такий автор вже вибраний", "Помилка");
                    this.authorFirstComboBox.SelectedValue = 0;
                }
                if (!this.plusAuthorFirstLinkLabel.Visible && !this.authorSecondComboBox.Visible)
                    this.plusAuthorFirstLinkLabel.Show();
            }
            catch(System.NullReferenceException)
            {
                //Closing form, not a problem
            }
            catch
            {
                //problem
                MessageBox.Show("Неможливо вибрати автора!", "Помилка");
                this.authorFirstComboBox.SelectedValue = 0;
            }
        }

        /// <summary>
        /// Process authorSecondComboBox_SelectedIndexChangedEvent
        /// </summary>
        private void AuthorSecondChanged()
        {
            try
            {
                int author_first_id = (int)this.authorFirstComboBox.SelectedValue;
                int author_second_id = (int)this.authorSecondComboBox.SelectedValue;
                int author_third_id = (int)this.authorThirdComboBox.SelectedValue;
                if (author_second_id != 0 && (author_second_id == author_first_id || author_second_id == author_third_id))
                {
                    MessageBox.Show("Такий автор вже вибраний", "Помилка");
                    this.authorSecondComboBox.SelectedValue = 0;
                }
                if (((int)this.authorSecondComboBox.SelectedValue) > 0 && !this.plusAuthorSecondLinkLabel.Visible && !this.authorThirdComboBox.Visible)
                    this.plusAuthorSecondLinkLabel.Show();
            }
            catch (System.NullReferenceException)
            {
                //Closing form, not a bug
            }
            catch
            {
                //bug
                MessageBox.Show("Неможливо вибрати автора!", "Помилка");
                this.authorSecondComboBox.SelectedValue = 0;
            }
        }

        /// <summary>
        /// Process authorThirdComboBox_SelectedIndexChangedEvent
        /// </summary>
        private void AuthorThirdChanged()
        {
            try
            {
                int author_first_id = (int)this.authorFirstComboBox.SelectedValue;
                int author_second_id = (int)this.authorSecondComboBox.SelectedValue;
                int author_third_id = (int)this.authorThirdComboBox.SelectedValue;
                if (author_third_id != 0 && (author_third_id == author_first_id || author_third_id == author_second_id))
                {
                    MessageBox.Show("Такий автор вже вибраний", "Помилка");
                    this.authorThirdComboBox.SelectedValue = 0;
                }
            }
            catch (System.NullReferenceException)
            {
                //Closing form, not a problem
            }
            catch
            {
                //problem
                MessageBox.Show("Неможливо вибрати автора!", "Помилка");
                this.authorThirdComboBox.SelectedValue = 0;
            }
        }

        /// <summary>
        /// Show hidden authorComboBox on the form
        /// </summary>
        /// <param name="comboBox"></param>
        /// <param name="linkLabel"></param>
        private void ProcessPlus(ComboBox comboBox, LinkLabel linkLabel)
        {
            comboBox.Show();
            linkLabel.Hide();
        }
    }

    public partial class SearchForm:BaseForm
    {
        private ArchiveDataEF.DBEArchiveEntities ctx;

        /// <summary>
        /// Value that indicates is form closing at the moment
        /// </summary>
        private bool isFormClosing = false;

        /// <summary>
        /// Fills all ComboBoxes after program started
        /// </summary>
        private void DefaultFill()
        {
            try
            {
                this.ctx = new ArchiveDataEF.DBEArchiveEntities();

                var fac = this.ctx.Faculties.Future();
                var cat = this.ctx.Categories.Future();
                this.facultyBindingSource.DataSource = fac.ToList();
                this.categoryBindingSource.DataSource = cat.ToList();

                this.departmentComboBox.Enabled = false;
                this.nameTextBox.Select();
            }
            catch
            {
                MessageBox.Show("При завантаженні форми виникла помилка. Будь ласка, спробуйте ще раз.", "Помилка");
                this.Hide();
                this.Dispose();
            }
        }

        /// <summary>
        /// Searches on parameters entered into the form
        /// </summary>
        private void Search()
        {
            string nameQuery = this.nameTextBox.Text; 
            string authorQuery = this.authorTextBox.Text;
            int facultyID = this.facultyComboBox.SelectedIndex;
            int departmentID = this.departmentComboBox.SelectedIndex;
            int categoryID = this.categoryComboBox.SelectedIndex;
            DateTime date;
            if (this.publicationYearTextBox.Text != "")
            {
                bool yearFlag = DateTime.TryParse(this.publicationYearTextBox.Text + ",1,1", out date);
                if (!yearFlag || date.Year < 1970 || date.Year > DateTime.Now.Year)
                {
                    MessageBox.Show("Введіть коректний рік!", "Помилка");
                    return;
                }
            }
            else
                date = new DateTime(1960, 1, 1); //defaut year
            this.LaunchForm(new SearchResultForm(nameQuery,authorQuery,facultyID,departmentID,categoryID,date));
        }
    }

    public partial class SearchResultForm : BaseForm
    {
        private ArchiveDataEF.DBEArchiveEntities ctx;

        /// <summary>
        /// Shows search results that were inputted on SearchForm
        /// </summary>
        /// <param name="nameQuery"></param>
        /// <param name="authorQuery"></param>
        /// <param name="faculty_id"></param>
        /// <param name="department_id"></param>
        /// <param name="category_id"></param>
        /// <param name="publicationYear"></param>
        private void ShowSearchResult(string nameQuery, string authorQuery, int faculty_id, int department_id, int category_id, DateTime publicationYear)
        {
            try
            {
                var arc = this.ctx.Archives.Future();
                var pub = this.ctx.Publications.Future();
                var cat = this.ctx.Categories.Future();
                var fac = this.ctx.Faculties.Future();
                var dep = this.ctx.Departments.Future();

                var Query = (from a in arc
                             join p in pub on a.PubID equals p.ID
                             join c in cat on a.CategoryID equals c.ID
                             join f in fac on a.FacultyID equals f.ID
                             join d in dep on a.DepartmentID equals d.ID
                             select new
                             {
                                 Publication = p.Name,
                                 Authors = this.ctx.AuthorsOfPub(p.ID).First().Authors,
                                 Category = c.Name,
                                 Faculty = f.Name,
                                 Department = d.Name,
                                 Amount = a.Amount,
                                 PubDate = a.PubDate,
                                 PubID = a.PubID,
                                 FacultyID = a.FacultyID,
                                 CategoryID = a.CategoryID,
                                 DepartmentID = a.DepartmentID,
                                 ID = a.ID
                             });

                Query = Query.Where(x => x.Publication.Contains(nameQuery));

                Query = Query.Where(x => x.Authors.Contains(authorQuery));

                if (category_id > 0)
                    Query = Query.Where(x => x.CategoryID == category_id);
                if (faculty_id > 0)
                    Query = Query.Where(x => x.FacultyID == faculty_id);
                if (department_id > 0)
                    Query = Query.Where(x => x.DepartmentID == department_id);
                if (publicationYear.Year >=1970)
                    Query = Query.Where(x => x.PubDate.Year == publicationYear.Year);

                this.bindingSource.DataSource = Query.OrderBy(x => x.Publication).ToList();

            }
            catch
            {
                MessageBox.Show("При спробі пошуку сталась помилка. Будь ласка, спробуйте ще раз.", "Помилка");
            }
        }
    }

    public partial class UpdatePublicationNameForm : Form
    {
        /// <summary>
        /// DataGridViewRow record, which publication name should be updated
        /// </summary>
        private DataGridViewRow dGVRow;

        /// <summary>
        /// Context valuable
        /// </summary>
        private ArchiveDataEF.DBEArchiveEntities ctx;

        /// <summary>
        /// Fills all custom components after program started
        /// </summary>
        private void DefaultFill()
        {
            ctx = new ArchiveDataEF.DBEArchiveEntities();
            this.publicationNameTextBox.Text = this.dGVRow.Cells["publicationDataGridViewTextBoxColumn"].Value.ToString();
            this.publicationNameTextBox.SelectAll();
        }

        /// <summary>
        /// Update selected publication name in database
        /// </summary>
        private void UpdateRow()
        {
            if(this.publicationNameTextBox.Text!="")
                try
                {

                    var arc = this.ctx.Archives.Future();
                    var pub = this.ctx.Publications.Future();
                    var cat = this.ctx.Categories.Future();
                    var fac = this.ctx.Faculties.Future();
                    var dep = this.ctx.Departments.Future();

                    var Query = (from a in arc
                                 join p in pub on a.PubID equals p.ID
                                 join c in cat on a.CategoryID equals c.ID
                                 join f in fac on a.FacultyID equals f.ID
                                 join d in dep on a.DepartmentID equals d.ID
                                 select new
                                 {
                                     Publication = p.Name,
                                     Authors = this.ctx.AuthorsOfPub(p.ID).First().Authors,
                                     Category = c.Name,
                                     Faculty = f.Name,
                                     Department = d.Name,
                                     Amount = (int)a.Amount,
                                     PubDate = a.PubDate
                                 }).ToList();

                        foreach (var book in Query)
                        {
                            if (
                                book.Publication == this.publicationNameTextBox.Text&&
                                book.Authors == this.dGVRow.Cells["authorsDataGridViewTextBoxColumn"].Value.ToString() &&
                                book.Category == this.dGVRow.Cells["categoryDataGridViewTextBoxColumn"].Value.ToString() &&
                                book.Faculty == this.dGVRow.Cells["facultyDataGridViewTextBoxColumn"].Value.ToString() &&
                                book.Department == this.dGVRow.Cells["departmentDataGridViewTextBoxColumn"].Value.ToString() &&
                                book.Amount == (int)this.dGVRow.Cells["amountDataGridViewTextBoxColumn"].Value &&
                                book.PubDate == (DateTime)this.dGVRow.Cells["pubDateDataGridViewTextBoxColumn"].Value
                                )
                            {
                                MessageBox.Show("Така книга вже є в базі.", "Помилка");
                                return;
                            }
                        }
                    {
                        
                    }

                    int pub_id = (int)this.dGVRow.Cells["pubIDDataGridViewTextBoxColumn"].Value;
                    this.ctx.Publications
                        .Where(x => x.ID == pub_id)
                        .Update(x => new Publication { Name = this.publicationNameTextBox.Text});
                }
                catch
                {
                    MessageBox.Show("Помилка при зміні  інформації. Спробуйте ще раз.", "Помилка");
                }
                finally
                {
                    this.Hide();
                    this.Dispose();
                }
        }
    }
}