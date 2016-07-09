using System;
using System.IO;
using System.Windows.Forms;
using System.Drawing;

namespace Manager
{
    public partial class MainWindow : Form
    {
        private string path1 = @"C:\"; //вхідні точки програми
        private string path2 = @"C:\";

        public MainWindow()
        {
            InitializeComponent();

            OpenTree(treeView1);
            OpenTree(treeView2);

            RefreshList(listView1, path1);
            RefreshList(listView2, path2);

        }

        private void listView1_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            ExpandDirectory(listView1, ref path1);
        }

        private void listView2_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            ExpandDirectory(listView2, ref path2);
        }

        private void treeView1_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            ClickOnNodesInTree(treeView1, listView1, e, ref path1);
        }

        private void treeView2_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            ClickOnNodesInTree(treeView2, listView2, e, ref path2);
        }

        private void treeView1_BeforeExpand(object sender, TreeViewCancelEventArgs e)
        {
            TreeViewBeforeExpand(e);
        }

        private void treeView2_BeforeExpand(object sender, TreeViewCancelEventArgs e)
        {
            TreeViewBeforeExpand(e);
        }

        private void helpToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ShowHelp();
        }

        private void aboutToolStripMenuItem2_Click(object sender, EventArgs e)
        {
            ShowAbout();
        }

        private void вихідToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Dispose();
        }

        private void копіюватиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count > 0) CopyOrMove(listView1, listView2, path1, path2, true);
            if (listView2.SelectedItems.Count > 0) CopyOrMove(listView2, listView1, path2, path1, true);
        }

        private void переміститиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count > 0) CopyOrMove(listView1, listView2, path1, path2, false);
            if (listView2.SelectedItems.Count > 0) CopyOrMove(listView2, listView1, path2, path1, false);
        }

        private void видалитиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count > 0)
                Delete(listView1, path1);
            if (listView2.SelectedItems.Count > 0) 
                Delete(listView2, path2);

            RefreshList(listView1, path1);
            RefreshList(listView2, path2);
        }

        private void listView1_KeyDown(object sender, KeyEventArgs e)
        {
            if ((listView1.SelectedItems.Count > 0) && ((e.KeyCode == Keys.F3) || (e.KeyCode == Keys.Enter)))
                OpenFileOrDirectory(listView1, ref path1);

            if (e.KeyCode == Keys.Back)
                Return(listView1, ref path1);

            if (e.Control && e.KeyCode == Keys.F5)
            {
                RefreshList(listView1, path1);
                RefreshList(listView2, path2);
            }
        }

        private void listView2_KeyDown(object sender, KeyEventArgs e)
        {
            if ((listView2.SelectedItems.Count > 0) && ((e.KeyCode == Keys.F3) || (e.KeyCode == Keys.Enter)))
                OpenFileOrDirectory(listView2, ref path2);

            if (e.KeyCode == Keys.Back)
                Return(listView2, ref path2);

            if (e.Control && e.KeyCode == Keys.F5)
            {
                RefreshList(listView1, path1);
                RefreshList(listView2, path2);
            }
        }

        private void перейменуватиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count > 0)
                Rename(listView1, path1);
            if (listView2.SelectedItems.Count > 0)
                Rename(listView2, path2);

            RefreshList(listView1, path1);
            RefreshList(listView2, path2);
        }

        private void новийФайлToolStripMenuItem_Click(object sender, EventArgs e)
        {
            CreateFile(listView1, ref path1);
        }

        private void копіювати2ToolStripMenuItem_Click(object sender, EventArgs e) //копіювати з контекстного меню
        {
            if(listView1.SelectedItems.Count > 0)
                AddToBuffer(listView1, path1, true);

            if (listView2.SelectedItems.Count > 0)
                AddToBuffer(listView2, path2, true);
        }

        private void вставитиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Point mouse = PointToClient(new Point(contextMenuStrip1.Left, contextMenuStrip1.Top));
            if (MouseIn(mouse, listView1))
                Paste(path1, true);
            if (MouseIn(mouse, listView2))
                Paste(path2, true);

            RefreshList(listView1, path1);
            RefreshList(listView2, path2);
        }

        private void файлToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Point mouse = PointToClient(new Point(contextMenuStrip1.Left, contextMenuStrip1.Top));
            if (MouseIn(mouse, listView1))
                CreateFile(listView1, ref path1);

            if(MouseIn(mouse, listView2))
                CreateFile(listView2, ref path2);

            RefreshList(listView1, path1);
            RefreshList(listView2, path2);
        }

        private void папкуToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Point mouse = PointToClient(new Point(contextMenuStrip1.Left, contextMenuStrip1.Top));
            if (MouseIn(mouse, listView1))
                CreateFolder(listView1, path1);

            if (MouseIn(mouse, listView2))
                CreateFolder(listView2, path2);

            RefreshList(listView1, path1);
            RefreshList(listView2, path2);
        }

        private void роботаЗXMLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            StartXMLEditor();
        }
    }
}
