using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.Entity;
using ArchiveDataEF;

namespace EFInformationSystem
{
    public partial class MainForm : BaseForm
   {

        public MainForm()
        {
            this.InitializeComponent();
            this.DefaultFill();
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            this.isFormClosing = true;
        }

        private void categoryComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.RefreshDGV();
        }

        private void facultyComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.isFacultyChanging = true;
            this.FacultyBindedEdit(ctx, facultyComboBox, departmentComboBox, departmentBindingSource, isFormClosing);
            this.RefreshDGV();
            this.isFacultyChanging = false;
        }

        private void departmentComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (!this.isFacultyChanging)
                this.RefreshDGV();
        }

        private void addBookToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.LaunchForm(new AddBookForm());
            this.RefreshDGV(); //after form closed
        }

        private void addAuthorToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.LaunchForm(new AddAuthorForm());
        }

        private void searchToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.LaunchForm(new SearchForm());
        }

        private void dGVMainForm_UserDeletingRow(object sender, DataGridViewRowCancelEventArgs e)
        {
            this.DeleteSelectedRows();
        }

        private void dGVMainForm_UserDeletedRow(object sender, DataGridViewRowEventArgs e)
        {
            this.RefreshDGV();
        }

        private void dGVMainForm_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            this.UpdateInformation();
        }

        private void saveToXMLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.SaveToXML(this.dGVMainForm);
        }
    }
}
