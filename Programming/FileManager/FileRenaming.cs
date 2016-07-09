using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Manager
{
    public partial class FileRenaming : Form
    {
        private string NewName;

        public FileRenaming(string name)
        {
            InitializeComponent();
            richTextBox1.Text = name;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            NewName = richTextBox1.Text;
            Close();
        }

        public string GetNewName()
        {
            return this.NewName;
        }
    }
}
