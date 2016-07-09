using System;
using System.IO;
using System.Collections.Generic;
using System.Windows.Forms;
using System.Drawing;

namespace Manager
{
    public partial class MainWindow : Form
    {
        List<string> copyFolders = new List<string>();
        List<string> copyFiles = new List<string>();

        private void GoToFolder(ListView listView, ref string path, string fName)
        {
            if (path.LastIndexOf('\\') != path.Length - 1) path += '\\';
                path += fName;
            try { RefreshList(listView, path); }
            catch(Exception e) {
                MessageBox.Show(e.Message, "Помилка");
                Return(listView, ref path);
            }
        }

        private void ShowAbout() //show author information
        {
            MessageBox.Show("Файловий менеджер, версія 1.0\n\nРоманус Теодор\ntheodoreromanus@gmail.com","About");
        }

        private void ShowHelp()
        {
            MessageBox.Show("Для допомоги пишіть на адресу theodoreromanus@gmail.com","Help");
        }

        private void RefreshList(ListView listView, string path)
        { 
            string[] files = Directory.GetFiles(path); 
            string[] directories = Directory.GetDirectories(path);

            listView.Items.Clear();
            {
                string dirName = "";
                for (int i = 0; i < directories.Length; i++)
                {
                    ListViewItem lvi = new ListViewItem();
                    dirName = directories[i].Substring(directories[i].LastIndexOf('\\') + 1);
                    lvi.Text = dirName;
                    lvi.ImageIndex = 0;
                    listView.Items.Add(lvi);
                }

                string fileName = "";
                for (int i = 0; i < files.Length; i++)
                {
                    ListViewItem lvi = new ListViewItem();
                    fileName = files[i].Substring(files[i].LastIndexOf('\\') + 1);
                    lvi.Text = fileName;
                    lvi.ImageIndex = 1;
                    listView.Items.Add(lvi);
                }

                listView.View = View.List;
            }

        }

        private void OpenTree(TreeView treeView)
        {
            string[] str = Environment.GetLogicalDrives();
            int n = 0;
            foreach (string s in str)
            {
                try
                {
                    TreeNode tn = new TreeNode();
                    tn.Name = s;
                    tn.Text = "Локальний диск " + s;
                    treeView.Nodes.Add(tn.Name, tn.Text, 0);
                    string t = "";
                    string[] str2 = Directory.GetDirectories(s);
                    foreach (string s2 in str2)
                    {
                        t = s2.Substring(s2.LastIndexOf('\\') + 1);
                        ((TreeNode)treeView.Nodes[n]).Nodes.Add(s2, t, 1);
                    }
                }
                catch { }
                n++;
            }
        }

        private void ExpandDirectory(ListView listView, ref string path)
        {
            if (listView.SelectedItems.Count > 0)
            {
                if (listView.SelectedItems[0].ImageIndex == 0)     //go to the next folder inside
                    try { GoToFolder(listView, ref path, listView.SelectedItems[0].Text); }
                    catch(Exception e) { MessageBox.Show(e.Message, "Помилка"); }
            }
            else //that's file
            {
               MessageBox.Show("Доступ заборонено", "Помилка");
            }
        }

        private void ClickOnNodesInTree(TreeView treeView, ListView listView, TreeNodeMouseClickEventArgs e, ref string path)
        {
            try
            {
                path = e.Node.Name;
                RefreshList(listView, path);
            }
            catch(Exception _e) { MessageBox.Show(_e.Message, "Помилка"); }
        }

        private void TreeViewBeforeExpand(TreeViewCancelEventArgs e)
        {
            TreeNodeCollection daughter = e.Node.Nodes;
            foreach (TreeNode tn in daughter)
            {
                tn.Nodes.Clear();
                try
                {
                    string s = tn.Name;

                    string[] str2 = Directory.GetDirectories(s);
                    string t;
                    foreach (string s2 in str2)
                    {
                        t = s2.Substring(s2.LastIndexOf('\\') + 1);
                        tn.Nodes.Add(s2, t, 1);
                    }
                }
                catch { }
            }
        }

        private void CopyFile(string from, string to, bool overwrite)
        {
            {
                int i;
                for (i = from.Length; from[i - 1] == '\\'; i--) ;
                from = from.Substring(0, i);
            }
            string fileName = from.Substring(from.LastIndexOf('\\')+1);
            try { File.Copy(from, to + '\\' + fileName, overwrite); }
            catch { MessageBox.Show("Такий файл вже існує.","Помилка"); }
        }

        private void CopyFolder(string from, string to)
        {
            {
                int i;
                for (i = from.Length; from[i - 1] == '\\'; i--) ;
                from = from.Substring(0, i);
            }

            string fName = from.Substring(from.LastIndexOf('\\') + 1);
            to += '\\' + fName;
            if (!Directory.Exists(to)) Directory.CreateDirectory(to);

            string[] files = Directory.GetFiles(from);
            foreach (string s in files)
                CopyFile(s, to, true);

            string[] folders = Directory.GetDirectories(from);
            foreach (string s in folders)
                CopyFolder(s, to);
        }

        private void AddToBuffer(ListView listView, string path, bool flag)
        {
            if (listView.SelectedItems.Count > 0)
            {
                copyFolders.Clear();
                copyFiles.Clear();
                if (!path.EndsWith("\\"))
                path += '\\';
                foreach (int n in listView.SelectedIndices)
                {
                    if (n>((Directory.GetDirectories(path)).Length-1)) //файл
                        copyFiles.Add(path + listView.Items[n].Text);
                    else // папка
                        copyFolders.Add(path + listView.Items[n].Text);    
                }
            }
        }

        private void Paste(string path, bool flag)
        {
            {

                foreach (string s in copyFolders)
                {// checking to copy in the subfolder
                    string t = "";
                    t += s[0];
                    for (int i = 1; i < s.Length; i++)
                    {
                        if (s[i] != '\\' || s[i - 1] != '\\') t += s[i];
                    }
                    if (path.Contains(t)) { MessageBox.Show("Не жартуй!" + path + t, "Помилка"); return; }

                }

                foreach (string s in copyFiles) // copying files with reburning
                    CopyFile(s, path, true);

                foreach (string s in copyFolders)
                    CopyFolder(s, path);

                if (!flag)
                {//if move, delete in the starting folder
                    foreach (string s in copyFiles)
                        File.Delete(s);

                    foreach (string s in copyFolders)
                        Directory.Delete(s, true); //delete with subfolders
                }

                copyFiles.Clear(); copyFolders.Clear();
            }
        }

        private void CopyOrMove(ListView list1, ListView list2, string path1, string path2, bool flag)
        {
            AddToBuffer(list1, path1, flag);
            Paste(path2, flag);
            RefreshList(list1, path1);
            RefreshList(list2, path2);
        }

        private void Delete(ListView listView, string path)
        {
                    path += '\\';
                foreach (int n in listView.SelectedIndices)
                {
                try {
                    if (n > ((Directory.GetDirectories(path)).Length - 1)) // file
                        File.Delete(path + listView.Items[n].Text);
                    else // folder
                        Directory.Delete(path + listView.Items[n].Text, true);
                        }
                catch (Exception e) { MessageBox.Show(e.Message, "Error"); }
                }
            RefreshList(listView, path);
        }

        private void OpenTextEditor(ListView listView, string path)
        {
                TextEditor form = new TextEditor(path);
                form.Show();
        }

        private void OpenFileOrDirectory(ListView listView, ref string path)
        {
            foreach (int n in listView.SelectedIndices)
            {
                if (n > ((Directory.GetDirectories(path)).Length - 1)) //файл
                    OpenTextEditor(listView, path + '\\' + listView.Items[n].Text);
                else // папка
                    ExpandDirectory(listView, ref path);
            }
        }

        private void Return(ListView listView, ref string path)
        {
            
            while (path.LastIndexOf('\\') == path.Length - 1) path = path.Substring(0, path.LastIndexOf('\\'));
            if (path.LastIndexOf('\\') == -1) { path += '\\'; System.Console.Beep(3000, 300); return; }
            else
            {
                string temp = path;
                path = path.Substring(0, path.LastIndexOf('\\')) + '\\';
                RefreshList(listView, path);
                temp = temp.Remove(0, path.Length);
                for (int i = 0; i < listView.Items.Count; i++)
                {
                    if (temp == listView.Items[i].Text)
                    {
                        listView.Items[i].Selected = true;
                        listView.Items[i].Focused = true;
                    }
                }
            }
        }

        private void CreateFile(ListView listView, ref string path)
        {
            FileCreation form = new FileCreation(path);
            form.ShowDialog();
            path = form.GetPath();
        }

        private void Rename(ListView listView, string path)
        {
            foreach(int n in listView.SelectedIndices)
            {
                string name = listView.Items[n].Text;
                FileRenaming form = new FileRenaming(name);
                form.ShowDialog();
                string newName = form.GetNewName();

                if(n > ((Directory.GetDirectories(path)).Length - 1)) //file
                        File.Move(path + name, path + newName);
                    else // folder
                        Directory.Move(path + name, path + newName);
            }
        }

        private bool MouseIn(Point ms,ListView listView)
        {
            return (ms.X >= listView.Left) && (ms.X <= listView.Right) && (ms.Y >= listView.Top) && (ms.Y <= listView.Bottom);
        }

        private void CreateFolder(ListView listView, string path)
        {
            FolderCreation form = new FolderCreation();
            form.ShowDialog();
            string folder = form.GetNameOfFolder();
            try
            {
                if (!Directory.Exists(path + '\\' + folder))
                {
                    var dI = Directory.CreateDirectory(path + '\\' + folder);
                    MessageBox.Show("Папка успішно створена!");
                }
            }
            catch
            { MessageBox.Show("Folder exists!", "Error"); }
        }

        private void StartXMLEditor()
        {
            XMLSearcher form = new XMLSearcher();
            form.ShowDialog();
        }
      }
}