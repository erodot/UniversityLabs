namespace InformationSystem
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle2 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle3 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle4 = new System.Windows.Forms.DataGridViewCellStyle();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.mainMenuStrip = new System.Windows.Forms.MenuStrip();
            this.addBookToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.addAuthorToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.searchToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.dGVMainForm = new System.Windows.Forms.DataGridView();
            this.nameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.authorsDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.categoryDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.facultyDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.departmentDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.amountDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.publicationDateDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.bindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.dsArchive = new ArchiveData.DSArchive();
            this.filterGroupBox = new System.Windows.Forms.GroupBox();
            this.departmentComboBox = new System.Windows.Forms.ComboBox();
            this.departmentBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.departmentLabel = new System.Windows.Forms.Label();
            this.facultyComboBox = new System.Windows.Forms.ComboBox();
            this.facultyBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.facultyLabel = new System.Windows.Forms.Label();
            this.categoryLabel = new System.Windows.Forms.Label();
            this.categoryComboBox = new System.Windows.Forms.ComboBox();
            this.categoryComboBoxBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.dtFilterTableAdapter = new ArchiveData.DSArchiveTableAdapters.DTFilterTableAdapter();
            this.categoriesTableAdapter = new ArchiveData.DSArchiveTableAdapters.CategoriesTableAdapter();
            this.facultiesTableAdapter = new ArchiveData.DSArchiveTableAdapters.FacultiesTableAdapter();
            this.departmentsTableAdapter = new ArchiveData.DSArchiveTableAdapters.DepartmentsTableAdapter();
            this.archiveTableAdapter = new ArchiveData.DSArchiveTableAdapters.ArchiveTableAdapter();
            this.authorsOfPublicationsTableAdapter = new ArchiveData.DSArchiveTableAdapters.AuthorsOfPublicationsTableAdapter();
            this.publicationsTableAdapter = new ArchiveData.DSArchiveTableAdapters.PublicationsTableAdapter();
            this.queriesTableAdapter = new ArchiveData.DSArchiveTableAdapters.QueriesTableAdapter();
            this.mainMenuStrip.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dGVMainForm)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dsArchive)).BeginInit();
            this.filterGroupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.departmentBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.facultyBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryComboBoxBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // mainMenuStrip
            // 
            this.mainMenuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.addBookToolStripMenuItem,
            this.addAuthorToolStripMenuItem,
            this.searchToolStripMenuItem});
            this.mainMenuStrip.Location = new System.Drawing.Point(0, 0);
            this.mainMenuStrip.Name = "mainMenuStrip";
            this.mainMenuStrip.Size = new System.Drawing.Size(914, 24);
            this.mainMenuStrip.TabIndex = 0;
            this.mainMenuStrip.Text = "menuStrip1";
            // 
            // addBookToolStripMenuItem
            // 
            this.addBookToolStripMenuItem.Name = "addBookToolStripMenuItem";
            this.addBookToolStripMenuItem.Size = new System.Drawing.Size(92, 20);
            this.addBookToolStripMenuItem.Text = "Додати книгу";
            this.addBookToolStripMenuItem.Click += new System.EventHandler(this.addBookToolStripMenuItem_Click);
            // 
            // addAuthorToolStripMenuItem
            // 
            this.addAuthorToolStripMenuItem.Name = "addAuthorToolStripMenuItem";
            this.addAuthorToolStripMenuItem.Size = new System.Drawing.Size(98, 20);
            this.addAuthorToolStripMenuItem.Text = "Додати автора";
            this.addAuthorToolStripMenuItem.Click += new System.EventHandler(this.addAuthorToolStripMenuItem_Click);
            // 
            // searchToolStripMenuItem
            // 
            this.searchToolStripMenuItem.Name = "searchToolStripMenuItem";
            this.searchToolStripMenuItem.Size = new System.Drawing.Size(58, 20);
            this.searchToolStripMenuItem.Text = "Пошук";
            this.searchToolStripMenuItem.Click += new System.EventHandler(this.searchToolStripMenuItem_Click);
            // 
            // dGVMainForm
            // 
            this.dGVMainForm.AllowUserToAddRows = false;
            dataGridViewCellStyle1.NullValue = "Усі";
            this.dGVMainForm.AlternatingRowsDefaultCellStyle = dataGridViewCellStyle1;
            this.dGVMainForm.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.dGVMainForm.AutoGenerateColumns = false;
            this.dGVMainForm.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            dataGridViewCellStyle2.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle2.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle2.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle2.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle2.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle2.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.dGVMainForm.ColumnHeadersDefaultCellStyle = dataGridViewCellStyle2;
            this.dGVMainForm.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dGVMainForm.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.nameDataGridViewTextBoxColumn,
            this.authorsDataGridViewTextBoxColumn,
            this.categoryDataGridViewTextBoxColumn,
            this.facultyDataGridViewTextBoxColumn,
            this.departmentDataGridViewTextBoxColumn,
            this.amountDataGridViewTextBoxColumn,
            this.publicationDateDataGridViewTextBoxColumn});
            this.dGVMainForm.DataSource = this.bindingSource;
            dataGridViewCellStyle3.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle3.BackColor = System.Drawing.SystemColors.Window;
            dataGridViewCellStyle3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle3.ForeColor = System.Drawing.SystemColors.ControlText;
            dataGridViewCellStyle3.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle3.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle3.WrapMode = System.Windows.Forms.DataGridViewTriState.False;
            this.dGVMainForm.DefaultCellStyle = dataGridViewCellStyle3;
            this.dGVMainForm.Location = new System.Drawing.Point(12, 27);
            this.dGVMainForm.Name = "dGVMainForm";
            this.dGVMainForm.ReadOnly = true;
            dataGridViewCellStyle4.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle4.BackColor = System.Drawing.SystemColors.Control;
            dataGridViewCellStyle4.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            dataGridViewCellStyle4.ForeColor = System.Drawing.SystemColors.WindowText;
            dataGridViewCellStyle4.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle4.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle4.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
            this.dGVMainForm.RowHeadersDefaultCellStyle = dataGridViewCellStyle4;
            this.dGVMainForm.Size = new System.Drawing.Size(890, 336);
            this.dGVMainForm.TabIndex = 1;
            this.dGVMainForm.UserDeletingRow += new System.Windows.Forms.DataGridViewRowCancelEventHandler(this.dGVMainForm_UserDeletingRow);
            // 
            // nameDataGridViewTextBoxColumn
            // 
            this.nameDataGridViewTextBoxColumn.DataPropertyName = "Name";
            this.nameDataGridViewTextBoxColumn.FillWeight = 130F;
            this.nameDataGridViewTextBoxColumn.HeaderText = "Назва";
            this.nameDataGridViewTextBoxColumn.Name = "nameDataGridViewTextBoxColumn";
            this.nameDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // authorsDataGridViewTextBoxColumn
            // 
            this.authorsDataGridViewTextBoxColumn.DataPropertyName = "Author(s)";
            this.authorsDataGridViewTextBoxColumn.FillWeight = 80F;
            this.authorsDataGridViewTextBoxColumn.HeaderText = "Автор(и)";
            this.authorsDataGridViewTextBoxColumn.Name = "authorsDataGridViewTextBoxColumn";
            this.authorsDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // categoryDataGridViewTextBoxColumn
            // 
            this.categoryDataGridViewTextBoxColumn.DataPropertyName = "Category";
            this.categoryDataGridViewTextBoxColumn.FillWeight = 50F;
            this.categoryDataGridViewTextBoxColumn.HeaderText = "Категорія";
            this.categoryDataGridViewTextBoxColumn.Name = "categoryDataGridViewTextBoxColumn";
            this.categoryDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // facultyDataGridViewTextBoxColumn
            // 
            this.facultyDataGridViewTextBoxColumn.DataPropertyName = "Faculty";
            this.facultyDataGridViewTextBoxColumn.FillWeight = 50F;
            this.facultyDataGridViewTextBoxColumn.HeaderText = "Факультет";
            this.facultyDataGridViewTextBoxColumn.Name = "facultyDataGridViewTextBoxColumn";
            this.facultyDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // departmentDataGridViewTextBoxColumn
            // 
            this.departmentDataGridViewTextBoxColumn.DataPropertyName = "Department";
            this.departmentDataGridViewTextBoxColumn.HeaderText = "Кафедра";
            this.departmentDataGridViewTextBoxColumn.Name = "departmentDataGridViewTextBoxColumn";
            this.departmentDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // amountDataGridViewTextBoxColumn
            // 
            this.amountDataGridViewTextBoxColumn.DataPropertyName = "Amount";
            this.amountDataGridViewTextBoxColumn.FillWeight = 25F;
            this.amountDataGridViewTextBoxColumn.HeaderText = "Обсяг";
            this.amountDataGridViewTextBoxColumn.Name = "amountDataGridViewTextBoxColumn";
            this.amountDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // publicationDateDataGridViewTextBoxColumn
            // 
            this.publicationDateDataGridViewTextBoxColumn.DataPropertyName = "PublicationDate";
            this.publicationDateDataGridViewTextBoxColumn.FillWeight = 37F;
            this.publicationDateDataGridViewTextBoxColumn.HeaderText = "Дата публікації";
            this.publicationDateDataGridViewTextBoxColumn.Name = "publicationDateDataGridViewTextBoxColumn";
            this.publicationDateDataGridViewTextBoxColumn.ReadOnly = true;
            // 
            // bindingSource
            // 
            this.bindingSource.DataMember = "DTFilter";
            this.bindingSource.DataSource = this.dsArchive;
            // 
            // dsArchive
            // 
            this.dsArchive.DataSetName = "DSArchive";
            this.dsArchive.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // filterGroupBox
            // 
            this.filterGroupBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.filterGroupBox.Controls.Add(this.departmentComboBox);
            this.filterGroupBox.Controls.Add(this.departmentLabel);
            this.filterGroupBox.Controls.Add(this.facultyComboBox);
            this.filterGroupBox.Controls.Add(this.facultyLabel);
            this.filterGroupBox.Controls.Add(this.categoryLabel);
            this.filterGroupBox.Controls.Add(this.categoryComboBox);
            this.filterGroupBox.Location = new System.Drawing.Point(12, 369);
            this.filterGroupBox.Name = "filterGroupBox";
            this.filterGroupBox.Size = new System.Drawing.Size(890, 50);
            this.filterGroupBox.TabIndex = 2;
            this.filterGroupBox.TabStop = false;
            this.filterGroupBox.Text = "Фільтр";
            // 
            // departmentComboBox
            // 
            this.departmentComboBox.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.departmentBindingSource, "ID", true));
            this.departmentComboBox.DataSource = this.departmentBindingSource;
            this.departmentComboBox.DisplayMember = "Name";
            this.departmentComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.departmentComboBox.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.departmentComboBox.FormattingEnabled = true;
            this.departmentComboBox.Location = new System.Drawing.Point(594, 19);
            this.departmentComboBox.Name = "departmentComboBox";
            this.departmentComboBox.Size = new System.Drawing.Size(173, 21);
            this.departmentComboBox.TabIndex = 5;
            this.departmentComboBox.ValueMember = "ID";
            this.departmentComboBox.SelectedIndexChanged += new System.EventHandler(this.departmentComboBox_SelectedIndexChanged);
            // 
            // departmentBindingSource
            // 
            this.departmentBindingSource.DataMember = "Departments";
            this.departmentBindingSource.DataSource = this.dsArchive;
            // 
            // departmentLabel
            // 
            this.departmentLabel.AutoSize = true;
            this.departmentLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.departmentLabel.Location = new System.Drawing.Point(524, 20);
            this.departmentLabel.Name = "departmentLabel";
            this.departmentLabel.Size = new System.Drawing.Size(64, 15);
            this.departmentLabel.TabIndex = 4;
            this.departmentLabel.Text = "Кафедра:";
            // 
            // facultyComboBox
            // 
            this.facultyComboBox.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.facultyBindingSource, "ID", true));
            this.facultyComboBox.DataSource = this.facultyBindingSource;
            this.facultyComboBox.DisplayMember = "Name";
            this.facultyComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.facultyComboBox.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.facultyComboBox.FormattingEnabled = true;
            this.facultyComboBox.Location = new System.Drawing.Point(337, 19);
            this.facultyComboBox.Name = "facultyComboBox";
            this.facultyComboBox.Size = new System.Drawing.Size(163, 21);
            this.facultyComboBox.TabIndex = 3;
            this.facultyComboBox.ValueMember = "ID";
            this.facultyComboBox.SelectedIndexChanged += new System.EventHandler(this.facultyComboBox_SelectedIndexChanged);
            // 
            // facultyBindingSource
            // 
            this.facultyBindingSource.DataMember = "Faculties";
            this.facultyBindingSource.DataSource = this.dsArchive;
            // 
            // facultyLabel
            // 
            this.facultyLabel.AutoSize = true;
            this.facultyLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.facultyLabel.Location = new System.Drawing.Point(257, 20);
            this.facultyLabel.Name = "facultyLabel";
            this.facultyLabel.Size = new System.Drawing.Size(74, 15);
            this.facultyLabel.TabIndex = 2;
            this.facultyLabel.Text = "Факультет:";
            // 
            // categoryLabel
            // 
            this.categoryLabel.AutoSize = true;
            this.categoryLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.categoryLabel.Location = new System.Drawing.Point(6, 22);
            this.categoryLabel.Name = "categoryLabel";
            this.categoryLabel.Size = new System.Drawing.Size(68, 15);
            this.categoryLabel.TabIndex = 1;
            this.categoryLabel.Text = "Категорія:";
            // 
            // categoryComboBox
            // 
            this.categoryComboBox.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.categoryComboBoxBindingSource, "ID", true));
            this.categoryComboBox.DataSource = this.categoryComboBoxBindingSource;
            this.categoryComboBox.DisplayMember = "Name";
            this.categoryComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.categoryComboBox.DropDownWidth = 151;
            this.categoryComboBox.FlatStyle = System.Windows.Forms.FlatStyle.System;
            this.categoryComboBox.Location = new System.Drawing.Point(77, 19);
            this.categoryComboBox.Name = "categoryComboBox";
            this.categoryComboBox.Size = new System.Drawing.Size(151, 21);
            this.categoryComboBox.TabIndex = 0;
            this.categoryComboBox.ValueMember = "ID";
            this.categoryComboBox.SelectedIndexChanged += new System.EventHandler(this.categoryComboBox_SelectedIndexChanged);
            // 
            // categoryComboBoxBindingSource
            // 
            this.categoryComboBoxBindingSource.DataMember = "Categories";
            this.categoryComboBoxBindingSource.DataSource = this.dsArchive;
            // 
            // dtFilterTableAdapter
            // 
            this.dtFilterTableAdapter.ClearBeforeFill = true;
            // 
            // categoriesTableAdapter
            // 
            this.categoriesTableAdapter.ClearBeforeFill = true;
            // 
            // facultiesTableAdapter
            // 
            this.facultiesTableAdapter.ClearBeforeFill = true;
            // 
            // departmentsTableAdapter
            // 
            this.departmentsTableAdapter.ClearBeforeFill = true;
            // 
            // archiveTableAdapter
            // 
            this.archiveTableAdapter.ClearBeforeFill = true;
            // 
            // authorsOfPublicationsTableAdapter
            // 
            this.authorsOfPublicationsTableAdapter.ClearBeforeFill = true;
            // 
            // publicationsTableAdapter
            // 
            this.publicationsTableAdapter.ClearBeforeFill = true;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(914, 431);
            this.Controls.Add(this.filterGroupBox);
            this.Controls.Add(this.dGVMainForm);
            this.Controls.Add(this.mainMenuStrip);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MainMenuStrip = this.mainMenuStrip;
            this.Name = "MainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Archive v1.0";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainForm_FormClosing);
            this.mainMenuStrip.ResumeLayout(false);
            this.mainMenuStrip.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dGVMainForm)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dsArchive)).EndInit();
            this.filterGroupBox.ResumeLayout(false);
            this.filterGroupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.departmentBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.facultyBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryComboBoxBindingSource)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip mainMenuStrip;
        private System.Windows.Forms.ToolStripMenuItem addBookToolStripMenuItem;
        private System.Windows.Forms.DataGridView dGVMainForm;
        private System.Windows.Forms.ToolStripMenuItem searchToolStripMenuItem;
        private System.Windows.Forms.GroupBox filterGroupBox;
        private System.Windows.Forms.Label categoryLabel;
        private System.Windows.Forms.ComboBox categoryComboBox;
        private System.Windows.Forms.Label facultyLabel;
        private System.Windows.Forms.ComboBox facultyComboBox;
        private System.Windows.Forms.ComboBox departmentComboBox;
        private System.Windows.Forms.Label departmentLabel;
        private System.Windows.Forms.BindingSource bindingSource;
        private ArchiveData.DSArchive dsArchive;
        private ArchiveData.DSArchiveTableAdapters.DTFilterTableAdapter dtFilterTableAdapter;
        private System.Windows.Forms.BindingSource categoryComboBoxBindingSource;
        private ArchiveData.DSArchiveTableAdapters.CategoriesTableAdapter categoriesTableAdapter;
        private System.Windows.Forms.BindingSource facultyBindingSource;
        private ArchiveData.DSArchiveTableAdapters.FacultiesTableAdapter facultiesTableAdapter;
        private System.Windows.Forms.BindingSource departmentBindingSource;
        private ArchiveData.DSArchiveTableAdapters.DepartmentsTableAdapter departmentsTableAdapter;
        private System.Windows.Forms.ToolStripMenuItem addAuthorToolStripMenuItem;
        private ArchiveData.DSArchiveTableAdapters.ArchiveTableAdapter archiveTableAdapter;
        private ArchiveData.DSArchiveTableAdapters.AuthorsOfPublicationsTableAdapter authorsOfPublicationsTableAdapter;
        private ArchiveData.DSArchiveTableAdapters.PublicationsTableAdapter publicationsTableAdapter;
        private ArchiveData.DSArchiveTableAdapters.QueriesTableAdapter queriesTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn nameDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn authorsDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn categoryDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn facultyDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn departmentDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn amountDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn publicationDateDataGridViewTextBoxColumn;
    }
}