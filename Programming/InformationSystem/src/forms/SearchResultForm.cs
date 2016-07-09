﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InformationSystem
{
    public partial class SearchResultForm : Form
    {
        public SearchResultForm(string nameQuery, string authorQuery, string facultyID, string departmentID, string categoryID, string publicationYear)
        {
            this.InitializeComponent();
            this.ShowSearchResult(nameQuery, authorQuery, facultyID, departmentID, categoryID, publicationYear);
        }
    }
}
