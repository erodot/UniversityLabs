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
    public partial class AddBookForm : Form
    {
        public AddBookForm()
        {
            this.InitializeComponent();
            this.DefaultFill();
        }

        private void AddBookForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            this.isFormClosing = true;
        }

        private void authorFirstComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.AuthorFirstChanged();
        }

        private void authorSecondComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.AuthorSecondChanged();
        }

        private void authorThirdComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.AuthorThirdChanged();
        }

        private void plusAuthorFirstLinkLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            this.ProcessPlus(authorSecondComboBox, plusAuthorFirstLinkLabel);
        }

        private void plusAuthorSecondLinkLabel_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            this.ProcessPlus(authorThirdComboBox, plusAuthorSecondLinkLabel);
        }

        private void facultyComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.FillDepartments();
        }

        private void addBookButton_Click(object sender, EventArgs e)
        {
            this.AddBook();
        }
    }
}
