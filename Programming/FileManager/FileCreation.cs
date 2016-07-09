using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using System.IO;

namespace Manager
{
    public partial class FileCreation : Form
    {
        private string path;
        static object locker = new object();

        public FileCreation(string _path)
        {
            InitializeComponent();
            path = _path;
            richTextBox1.Text = "*.txt";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            CreateFile(richTextBox1.Text, path);
        }

        private void CreateFile(string name, string path)
        {
            if (path.LastIndexOf('\\') != path.Length - 1) path += '\\';

            try
            {

                if (!File.Exists(path + name))
                {
                    var fs = File.Create(path + name);
                    fs.Close();
                    
                    MessageBox.Show("Файл успішно створено!", "Успіх");
                }
                else
                    MessageBox.Show("Такий файл вже існує!", "Помилка");
            } catch (Exception e) { MessageBox.Show(e.Message, "Помилка"); }
            Dispose();
        }

        public string GetPath()
        {
            return path;
        }
    }
}
