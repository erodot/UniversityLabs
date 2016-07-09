using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Xsl;
using System.IO;

namespace Manager
{
    public partial class XMLSearcher : Form
    {
        private string path;
        private List<Person> People;
        private DataTable table;

        private void LoadFile(DataGridView dataGridView)
        {
            OpenFileDialog Ofd = new OpenFileDialog { Filter = "XML файл|*.xml" };
            if (Ofd.ShowDialog() == DialogResult.OK)
            {
                path = Ofd.FileName;
                SearchAndShow(dataGridView, new RadioButton(), new RadioButton(), new RadioButton(), new RichTextBox(), new RichTextBox(), new RichTextBox(), new RichTextBox(), new RichTextBox(), new RichTextBox());
            }
        }

        private void SearchAndShow(DataGridView dataGridView, RadioButton radioButton1, RadioButton redioButton2, RadioButton radioButton3, RichTextBox richTextBox1, RichTextBox richTextBox2, RichTextBox richTextBox3, RichTextBox richTextBox4, RichTextBox richTextBox5, RichTextBox richTextBox6)
        {
            Person filter = new Person();

            try
            {
                if (richTextBox1.Text != "") filter._name = richTextBox1.Text;
                if (richTextBox2.Text != "") filter._faculty = richTextBox2.Text;
                if (richTextBox3.Text != "") filter._cathedra = richTextBox3.Text;
                if (richTextBox4.Text != "") filter._specialty = richTextBox4.Text;
                if (richTextBox5.Text != "") filter._entryYear = richTextBox5.Text;
                if (richTextBox6.Text != "") filter._graduationYear = richTextBox6.Text;
            }
            catch
            {
                MessageBox.Show("There are some errors in filters!", "Error");
                return;
            }

            People.Clear();

            if ((!radioButton1.Checked) && (!radioButton2.Checked) && (!radioButton3.Checked))
                radioButton1.Checked = true;

            //IParser doc1;

            if (radioButton1.Checked) //DOM
                People = (new DOM_XML_Parser(path)).Search(filter);

            if (radioButton2.Checked) //SAX
                People = (new SAX_XML_Parser(path)).Search(filter);

            if (radioButton3.Checked) //LINQ to XML
                People = (new LINQ_TO_XML_Parser(path)).Search(filter);

            table.Clear();

            foreach (Person p in People)
            {
                var row = table.NewRow();
                row[0] = p._name;
                row[1] = p._faculty;
                row[2] = p._cathedra;
                row[3] = p._specialty;
                row[4] = p._entryYear;
                row[5] = p._graduationYear;
                table.Rows.Add(row);
            }
            dataGridView.DataSource = table;
        }

        private async void MakeHTML()
        {
            string tab = "\t";

            StringBuilder sb = new StringBuilder();

            sb.AppendLine("<html>");
            sb.AppendLine(tab + "<body>");
            sb.AppendLine(tab + tab + "<table>");

            // headers.
            sb.Append(tab + tab + tab + "<tr>");

            foreach (DataColumn dc in table.Columns)
            {
                sb.AppendFormat("<td>{0}</td>", dc.ColumnName);
            }

            sb.AppendLine("</tr>");

            // data rows
            foreach (DataRow dr in table.Rows)
            {
                sb.Append(tab + tab + tab + "<tr>");

                foreach (DataColumn dc in table.Columns)
                {
                    string cellValue = dr[dc] != null ? dr[dc].ToString() : "";
                    sb.AppendFormat("<td>{0}</td>", cellValue);
                }

                sb.AppendLine("</tr>");
            }

            sb.AppendLine(tab + tab + "</table>");
            sb.AppendLine(tab + "</body>");
            sb.AppendLine("</html>");

            string name = "index.html";

            FileStream file = File.Open(name, FileMode.Create);
            
            using (StreamWriter outfile = new StreamWriter(file,Encoding.Unicode))
            {
                await outfile.WriteAsync(sb.ToString());
            }

            MessageBox.Show("Операція здійснена успішно!", "Успіх");
        }
    }

      

    class Algorythms
    {
        public static bool Check(Person person, Person filter)
        {
            bool flag = true;
            if ((filter._name != "") && (person._name != filter._name))
                flag = false;
            if ((filter._faculty != "") && (person._faculty != filter._faculty))
                flag = false;
            if ((filter._cathedra != "") && (person._cathedra != filter._cathedra))
                flag = false;
            if ((filter._specialty != "") && (person._specialty != filter._specialty))
                flag = false;
            if ((filter._entryYear != "") && (person._entryYear != filter._entryYear))
                flag = false;
            if ((filter._graduationYear != "") && (person._graduationYear != filter._graduationYear))
                flag = false;

            return flag;
        }
    }

}
