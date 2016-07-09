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
    public partial class UpdatePublicationNameForm : Form
    {
        public UpdatePublicationNameForm(DataGridViewRow _dGVRow)
        {
            this.dGVRow = _dGVRow;
            this.InitializeComponent();
            this.DefaultFill();
        }

        private void acceptButton_Click(object sender, EventArgs e)
        {
            this.UpdateRow();
        }
    }
}
