using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace EFInformationSystem
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
            this.FacultyBindedEdit(ctx, facultyComboBox, departmentComboBox, departmentBindingSource, isFormClosing);
        }

        private void SearchForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            this.isFormClosing = true;
        }

        private void searchButton_Click(object sender, EventArgs e)
        {
            this.Search();
        }

        private void SearchForm_Load(object sender, EventArgs e)
        {
            this.FacultyBindedEdit(ctx, facultyComboBox, departmentComboBox, departmentBindingSource, isFormClosing);
        }
    }
}
