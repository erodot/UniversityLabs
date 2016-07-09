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
    public partial class AddAuthorForm
    {
        public AddAuthorForm()
        {
            this.InitializeComponent();
            this.ctx = new ArchiveDataEF.DBEArchiveEntities();
            this.surnameTextBox.Select();
        }

        private void addAuthorButton_Click(object sender, EventArgs e)
        {
            this.AddAuthor();
        }
    }
}
