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
    public partial class SearchForm : BaseForm
    {
        public SearchForm()
        {
            this.InitializeComponent();
            this.DefaultFill();
        }

        private void facultyComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.FacultyBindedEdit(dsArchive, facultyComboBox, departmentComboBox, departmentsTableAdapter, isFormClosing);
        }

        private void SearchForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            this.isFormClosing = true;
        }

        private void searchButton_Click(object sender, EventArgs e)
        {
            this.Search();
        }
    }
}
