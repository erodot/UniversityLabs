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
    public partial class XMLSearcher : Form
    {
        public XMLSearcher()
        {
            InitializeComponent();
            this.path = "";
            this.People = new List<Person>();
            this.table = new DataTable();

            for (int i = 0; i < 6; i++)
                table.Columns.Add(new DataColumn());
            table.Columns[0].ColumnName = "П.І.Б";
            table.Columns[1].ColumnName = "Факультет";
            table.Columns[2].ColumnName = "Кафедра";
            table.Columns[3].ColumnName = "Спеціальність";
            table.Columns[4].ColumnName = "Рік вступу";
            table.Columns[5].ColumnName = "Рік випуску";
        }

        private void завантажитиФайлToolStripMenuItem_Click(object sender, EventArgs e)
        {
            LoadFile(dataGridView1);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            SearchAndShow(dataGridView1, radioButton1, radioButton2, radioButton3, richTextBox1, richTextBox2, richTextBox3, richTextBox4, richTextBox5, richTextBox6);
        }

        private void перетворитиВHTMLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            MakeHTML();
        }
    }
}
