using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InformationSystem
{
    public class BaseForm:Form
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
        protected void FacultyBindedEdit(ArchiveData.DSArchive dsArchive, ComboBox facultyComboBox, ComboBox departmentComboBox, ArchiveData.DSArchiveTableAdapters.DepartmentsTableAdapter departmentsTableAdapter, bool isFormClosing)
        {
            if (!isFormClosing)
                try
                {
                    int faculty_id = (int)facultyComboBox.SelectedValue;
                    if (faculty_id == 0)
                    {
                        departmentComboBox.Enabled = false;
                        departmentComboBox.SelectedValue = 0;
                    }
                    else
                    {
                        departmentComboBox.Enabled = true;
                        departmentsTableAdapter.FillByFaculty(dsArchive.Departments, faculty_id);
                    }
                }
                catch
                {
                    MessageBox.Show("Помилка оновлення даних про кафедри факультету \"" + facultyComboBox.Text + "\".", "Помилка");
                }
        }
    }

    public partial class MainForm:BaseForm
    {
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
                this.categoriesTableAdapter.Fill(dsArchive.Categories);
                this.facultiesTableAdapter.Fill(dsArchive.Faculties);
                this.departmentsTableAdapter.Fill(dsArchive.Departments);
                this.RefreshDGV();
                this.departmentComboBox.Enabled = false;
            }
            catch
            {
                MessageBox.Show("Помилка завантаження даних з бази. Перезапустіть програму","Помилка");
                this.Hide();
                this.Dispose();
            }
        }

        /// <summary>
        /// Refresh MainForm.DataGridView
        /// </summary>
        private void RefreshDGV()
        {
            if (!this.isFormClosing)
                try
                {
                    string category_id = this.categoryComboBox.SelectedIndex.ToString();
                    string faculty_id = this.facultyComboBox.SelectedIndex.ToString();
                    string department_id = this.departmentComboBox.SelectedIndex.ToString();

                    this.dtFilterTableAdapter.Fill(this.dsArchive.DTFilter, "%%", "%%", faculty_id, department_id, category_id, "");
                }
                catch
                {
                    MessageBox.Show("Помилка оновлення даних. Будь ласка, перезапустіть програму.", "Помилка");
                }
        }

        /// <summary>
        /// Implements search on database
        /// </summary>
        private void OpenSearchForm()
        {
            this.LaunchForm(new SearchForm());
        }

        /// <summary>
        /// Delete selected on MainForm.DataGridView publications from database
        /// </summary>
        private void DeleteSelectedRows()
        {
            try
            {
                foreach (DataGridViewRow row in this.dGVMainForm.SelectedRows)
                {
                    int publication_id = Convert.ToInt32(this.queriesTableAdapter.SQIDOfName(row.Cells[0].Value.ToString()));
                    this.archiveTableAdapter.Delete(publication_id);
                    this.authorsOfPublicationsTableAdapter.Delete(publication_id);
                    this.publicationsTableAdapter.Delete(publication_id);
                }
            }
            catch(Exception ex)
            {
                MessageBox.Show("Помилка видалення публікації. Спробуйте ще раз." + ex.StackTrace, "Помилка");
            }
        }
    }

    public partial class AddAuthorForm:Form
    {
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
        /// Move cursor to the first TextBox on the form
        /// </summary>
        private void MoveCursorToStart()
        {
            this.surnameTextBox.Select();
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

            string shortName = firstName[0] + ". " + secondName[0] + ". " + surname;
            if (this.queriesTableAdapter.SQCountAuthorsWithData(surname, firstName, secondName) > 0)
            {
                MessageBox.Show("Такий автор вже є в базі.", "Помилка додавання");
                this.surnameTextBox.Clear();
                this.firstNameTextBox.Clear();
                this.secondNameTextBox.Clear();
                this.surnameTextBox.Select();
            }
            else
                try
                {
                    this.authorsTableAdapter.Insert(surname, firstName, secondName, shortName);
                    MessageBox.Show("Автор успішно доданий!", "Успіх");
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
                this.authorsTableAdapter.FillSortingBySurname(this.dsArchive.Authors);
                this.facultiesTableAdapter.FillNoDefault(this.dsArchive.Faculties);
                this.departmentsTableAdapter.FillByFacultyNoDefault(this.dsArchive.Departments, (int)this.facultyComboBox.SelectedValue);
                this.categoriesTableAdapter.FillNoDefault(this.dsArchive.Categories);
                this.plusAuthorFirstLinkLabel.Hide();
                this.plusAuthorSecondLinkLabel.Hide();
                this.authorSecondComboBox.Hide();
                this.authorThirdComboBox.Hide();
                this.isFormClosing = false;
            }
            catch
            {
                MessageBox.Show("При завантаженні форми виникла помилка. Будь ласка, спробуйте ще раз.", "Помилка");
                this.Hide();
                this.Dispose();
            }
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

                int faculty_id = (int)this.facultyComboBox.SelectedValue;
                int department_id = (int)this.departmentComboBox.SelectedValue;

                int category_id = (int)this.categoryComboBox.SelectedValue;

                string date_str = this.dateTextBox.Text;
                DateTime date = new DateTime(Convert.ToInt32(date_str.Substring(0, 4)), Convert.ToInt32(date_str.Substring(5, 2)), Convert.ToInt32(date_str.Substring(8, 2)));

                int amount = Int32.Parse(this.amountTextBox.Text);

                //check if book is absent in database
                if ((int)this.queriesTableAdapter.SQCountSamePublications(name, category_id, date_str) > 0)
                {
                    MessageBox.Show("Така книга вже є в базі даних!", "Помилка");
                    return;
                }

                //inserting data into database
                this.publicationsTableAdapter.Insert(name);
                int name_id = (int)this.queriesTableAdapter.SQIDOfName(name);
                foreach (var author_id in authors_id)
                    this.authorsOfPublicationsTableAdapter.Insert(name_id, author_id);
                this.archiveTableAdapter.Insert(name_id,department_id,faculty_id,category_id,amount,date);

                MessageBox.Show("Книга успішно додана!", "Успіх");

                this.Hide();

                this.Dispose();
            }
            catch
            {
                MessageBox.Show("Сталася помилка. Неможливо додати книгу в базу", "Помилка");
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
                    this.departmentsTableAdapter.FillByFacultyNoDefault(this.dsArchive.Departments, (int)this.facultyComboBox.SelectedValue);
                }
                catch(System.NullReferenceException)
                {
                    //not bug, just closing form
                }
                catch
                {
                    //bug
                    MessageBox.Show("Помилка завантаження даних про факультети. Будь ласка, перезавантажте програму.\n", "Помилка");
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
                this.facultiesTableAdapter.Fill(this.dsArchive.Faculties);
                this.departmentsTableAdapter.FillByFaculty(this.dsArchive.Departments, (int)this.facultyComboBox.SelectedValue);
                this.departmentComboBox.Enabled = false;
                this.categoriesTableAdapter.Fill(this.dsArchive.Categories);
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
            string nameQuery = "%"+this.nameTextBox.Text.Replace("%","!%")+"%"; 
            string authorQuery = "%" + this.authorTextBox.Text.Replace("%", "!%") + "%";
            string facultyID = this.facultyComboBox.SelectedValue.ToString();
            string departmentID = this.departmentComboBox.SelectedValue.ToString();
            string categoryID = this.categoryComboBox.SelectedValue.ToString();
            string publicationYear = this.publicationYearTextBox.Text;
            if (publicationYear != "")
            {
                DateTime date;
                bool yearFlag = DateTime.TryParse(publicationYear+",1,1", out date);
                if (!yearFlag || date.Year < 1970 || date.Year > DateTime.Now.Year)
                {
                    MessageBox.Show("Введіть коректний рік!", "Помилка");
                    return;
                }
            }
            this.LaunchForm(new SearchResultForm(nameQuery,authorQuery,facultyID,departmentID,categoryID,publicationYear));
        }
    }

    public partial class SearchResultForm:Form
    {
        private void ShowSearchResult(string nameQuery, string authorQuery, string facultyID, string departmentID, string categoryID, string publicationYear)
        {
            try
            {
                this.dtFilterTableAdapter.Fill(this.dsArchive.DTFilter, nameQuery, authorQuery, facultyID, departmentID, categoryID, publicationYear);
            }
            catch
            {
                MessageBox.Show("При спробі пошуку сталась помилка. Будь ласка, спробуйте ще раз.", "Помилка");
            }
        }
    }
}