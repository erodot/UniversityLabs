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
    public partial class FolderCreation : Form
    {
        private string nameOfFolder;

        public FolderCreation()
        {
            InitializeComponent();
            richTextBox1.Text = "Нова папка";
            richTextBox1.Select(richTextBox1.Text.Length, 0);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            nameOfFolder = richTextBox1.Text;
            Dispose();
        }

        public string GetNameOfFolder()
        {
            return nameOfFolder;
        }
    }
}
