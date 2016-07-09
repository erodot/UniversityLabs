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
    public partial class TextEditor : Form
    {
        public TextEditor(string path)
        {
            InitializeComponent();
            OpenFile(path);
        }
        
        private void OpenFile(string path)
        {
            try
            {
                if (path.EndsWith("txt") || path.EndsWith("cs"))
                {
                    richTextBox1.LoadFile(path, RichTextBoxStreamType.PlainText);
                }
                else if (path.EndsWith("rtf"))
                {
                    richTextBox1.LoadFile(path, RichTextBoxStreamType.RichText);
                }
            }
            catch { }
        }

        private void відкритиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.Title = "Виберіть файл";
            openFileDialog1.Filter = "Текстові файли|*.txt";
            if (openFileDialog1.ShowDialog() == DialogResult.Cancel)
            {
                return;
            }
            string filename = openFileDialog1.FileName;

            OpenFile(filename);
        }

        private void зберегтиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFileDialog1.Title = "Зберегти";
            saveFileDialog1.Filter = "Текстові файли|*.txt";
            if (saveFileDialog1.ShowDialog() == DialogResult.Cancel)
            {
                return;
            }
            string filename = saveFileDialog1.FileName;

            if (filename.EndsWith("txt") || filename.EndsWith("cs"))
            {
                richTextBox1.SaveFile(filename, RichTextBoxStreamType.PlainText);
            }
            else if (filename.EndsWith("rtf"))
            {
                richTextBox1.SaveFile(filename, RichTextBoxStreamType.RichText);
            }
        }
    }
}
