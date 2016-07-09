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
    public partial class SearchResultForm : BaseForm
    {
        public SearchResultForm(string nameQuery, string authorQuery, int facultyID, int departmentID, int categoryID, DateTime publicationYear)
        {
            this.InitializeComponent();
            this.ctx = new ArchiveDataEF.DBEArchiveEntities();
            this.ShowSearchResult(nameQuery, authorQuery, facultyID, departmentID, categoryID, publicationYear);
        }

        private void зберегтиВXMLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.SaveToXML(this.dataGridView);
        }
    }
}
