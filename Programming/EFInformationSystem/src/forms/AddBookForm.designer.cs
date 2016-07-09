namespace EFInformationSystem
{
    partial class AddBookForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(AddBookForm));
            this.nameLabel = new System.Windows.Forms.Label();
            this.nameTextBox = new System.Windows.Forms.TextBox();
            this.authorsLabel = new System.Windows.Forms.Label();
            this.authorFirstComboBox = new System.Windows.Forms.ComboBox();
            this.authorFirstBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.categoryLabel = new System.Windows.Forms.Label();
            this.categoryComboBox = new System.Windows.Forms.ComboBox();
            this.categoryBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.facultyLabel = new System.Windows.Forms.Label();
            this.departmentLabel = new System.Windows.Forms.Label();
            this.facultyComboBox = new System.Windows.Forms.ComboBox();
            this.facultyBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.departmentComboBox = new System.Windows.Forms.ComboBox();
            this.departmentBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.amountLabel = new System.Windows.Forms.Label();
            this.amountTextBox = new System.Windows.Forms.TextBox();
            this.dateLabel = new System.Windows.Forms.Label();
            this.addBookButton = new System.Windows.Forms.Button();
            this.dateTextBox = new System.Windows.Forms.TextBox();
            this.plusAuthorFirstLinkLabel = new System.Windows.Forms.LinkLabel();
            this.authorSecondComboBox = new System.Windows.Forms.ComboBox();
            this.authorSecondBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.plusAuthorSecondLinkLabel = new System.Windows.Forms.LinkLabel();
            this.authorThirdComboBox = new System.Windows.Forms.ComboBox();
            this.authorThirdBindingSource = new System.Windows.Forms.BindingSource(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.authorFirstBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.facultyBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.departmentBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.authorSecondBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.authorThirdBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // nameLabel
            // 
            this.nameLabel.AutoSize = true;
            this.nameLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.nameLabel.Location = new System.Drawing.Point(12, 25);
            this.nameLabel.Name = "nameLabel";
            this.nameLabel.Size = new System.Drawing.Size(46, 15);
            this.nameLabel.TabIndex = 0;
            this.nameLabel.Text = "Назва:";
            // 
            // nameTextBox
            // 
            this.nameTextBox.Location = new System.Drawing.Point(111, 20);
            this.nameTextBox.Name = "nameTextBox";
            this.nameTextBox.Size = new System.Drawing.Size(375, 20);
            this.nameTextBox.TabIndex = 1;
            // 
            // authorsLabel
            // 
            this.authorsLabel.AutoSize = true;
            this.authorsLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.authorsLabel.Location = new System.Drawing.Point(12, 61);
            this.authorsLabel.Name = "authorsLabel";
            this.authorsLabel.Size = new System.Drawing.Size(60, 15);
            this.authorsLabel.TabIndex = 2;
            this.authorsLabel.Text = "Автор(и):";
            // 
            // authorFirstComboBox
            // 
            this.authorFirstComboBox.DataSource = this.authorFirstBindingSource;
            this.authorFirstComboBox.DisplayMember = "ShortName";
            this.authorFirstComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.authorFirstComboBox.FormattingEnabled = true;
            this.authorFirstComboBox.Location = new System.Drawing.Point(111, 60);
            this.authorFirstComboBox.Name = "authorFirstComboBox";
            this.authorFirstComboBox.Size = new System.Drawing.Size(121, 21);
            this.authorFirstComboBox.TabIndex = 3;
            this.authorFirstComboBox.ValueMember = "ID";
            this.authorFirstComboBox.SelectedIndexChanged += new System.EventHandler(this.authorFirstComboBox_SelectedIndexChanged);
            // 
            // categoryLabel
            // 
            this.categoryLabel.AutoSize = true;
            this.categoryLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.categoryLabel.Location = new System.Drawing.Point(12, 148);
            this.categoryLabel.Name = "categoryLabel";
            this.categoryLabel.Size = new System.Drawing.Size(68, 15);
            this.categoryLabel.TabIndex = 12;
            this.categoryLabel.Text = "Категорія:";
            // 
            // categoryComboBox
            // 
            this.categoryComboBox.DataSource = this.categoryBindingSource;
            this.categoryComboBox.DisplayMember = "Name";
            this.categoryComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.categoryComboBox.FormattingEnabled = true;
            this.categoryComboBox.Location = new System.Drawing.Point(111, 147);
            this.categoryComboBox.Name = "categoryComboBox";
            this.categoryComboBox.Size = new System.Drawing.Size(121, 21);
            this.categoryComboBox.TabIndex = 13;
            this.categoryComboBox.ValueMember = "ID";
            // 
            // facultyLabel
            // 
            this.facultyLabel.AutoSize = true;
            this.facultyLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.facultyLabel.Location = new System.Drawing.Point(12, 107);
            this.facultyLabel.Name = "facultyLabel";
            this.facultyLabel.Size = new System.Drawing.Size(74, 15);
            this.facultyLabel.TabIndex = 8;
            this.facultyLabel.Text = "Факультет:";
            // 
            // departmentLabel
            // 
            this.departmentLabel.AutoSize = true;
            this.departmentLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.departmentLabel.Location = new System.Drawing.Point(264, 107);
            this.departmentLabel.Name = "departmentLabel";
            this.departmentLabel.Size = new System.Drawing.Size(64, 15);
            this.departmentLabel.TabIndex = 10;
            this.departmentLabel.Text = "Кафедра:";
            // 
            // facultyComboBox
            // 
            this.facultyComboBox.DataSource = this.facultyBindingSource;
            this.facultyComboBox.DisplayMember = "Name";
            this.facultyComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.facultyComboBox.FormattingEnabled = true;
            this.facultyComboBox.Location = new System.Drawing.Point(111, 106);
            this.facultyComboBox.Name = "facultyComboBox";
            this.facultyComboBox.Size = new System.Drawing.Size(137, 21);
            this.facultyComboBox.TabIndex = 9;
            this.facultyComboBox.ValueMember = "ID";
            this.facultyComboBox.SelectedIndexChanged += new System.EventHandler(this.facultyComboBox_SelectedIndexChanged);
            // 
            // departmentComboBox
            // 
            this.departmentComboBox.DataSource = this.departmentBindingSource;
            this.departmentComboBox.DisplayMember = "Name";
            this.departmentComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.departmentComboBox.FormattingEnabled = true;
            this.departmentComboBox.Location = new System.Drawing.Point(334, 106);
            this.departmentComboBox.Name = "departmentComboBox";
            this.departmentComboBox.Size = new System.Drawing.Size(152, 21);
            this.departmentComboBox.TabIndex = 11;
            this.departmentComboBox.ValueMember = "ID";
            // 
            // amountLabel
            // 
            this.amountLabel.AutoSize = true;
            this.amountLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.amountLabel.Location = new System.Drawing.Point(303, 194);
            this.amountLabel.Name = "amountLabel";
            this.amountLabel.Size = new System.Drawing.Size(95, 15);
            this.amountLabel.TabIndex = 16;
            this.amountLabel.Text = "К-сть сторінок:";
            // 
            // amountTextBox
            // 
            this.amountTextBox.Location = new System.Drawing.Point(404, 192);
            this.amountTextBox.Name = "amountTextBox";
            this.amountTextBox.Size = new System.Drawing.Size(82, 20);
            this.amountTextBox.TabIndex = 17;
            // 
            // dateLabel
            // 
            this.dateLabel.AutoSize = true;
            this.dateLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.dateLabel.Location = new System.Drawing.Point(12, 194);
            this.dateLabel.Name = "dateLabel";
            this.dateLabel.Size = new System.Drawing.Size(98, 15);
            this.dateLabel.TabIndex = 14;
            this.dateLabel.Text = "Дата публікації:";
            // 
            // addBookButton
            // 
            this.addBookButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.addBookButton.Location = new System.Drawing.Point(212, 237);
            this.addBookButton.Name = "addBookButton";
            this.addBookButton.Size = new System.Drawing.Size(102, 23);
            this.addBookButton.TabIndex = 18;
            this.addBookButton.Text = "Додати книгу";
            this.addBookButton.UseVisualStyleBackColor = true;
            this.addBookButton.Click += new System.EventHandler(this.addBookButton_Click);
            // 
            // dateTextBox
            // 
            this.dateTextBox.Location = new System.Drawing.Point(111, 192);
            this.dateTextBox.Name = "dateTextBox";
            this.dateTextBox.Size = new System.Drawing.Size(100, 20);
            this.dateTextBox.TabIndex = 15;
            // 
            // plusAuthorFirstLinkLabel
            // 
            this.plusAuthorFirstLinkLabel.AutoSize = true;
            this.plusAuthorFirstLinkLabel.LinkColor = System.Drawing.Color.Black;
            this.plusAuthorFirstLinkLabel.Location = new System.Drawing.Point(239, 63);
            this.plusAuthorFirstLinkLabel.Name = "plusAuthorFirstLinkLabel";
            this.plusAuthorFirstLinkLabel.Size = new System.Drawing.Size(89, 13);
            this.plusAuthorFirstLinkLabel.TabIndex = 4;
            this.plusAuthorFirstLinkLabel.TabStop = true;
            this.plusAuthorFirstLinkLabel.Text = "+ додати автора";
            this.plusAuthorFirstLinkLabel.VisitedLinkColor = System.Drawing.Color.Black;
            this.plusAuthorFirstLinkLabel.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.plusAuthorFirstLinkLabel_LinkClicked);
            // 
            // authorSecondComboBox
            // 
            this.authorSecondComboBox.DataSource = this.authorSecondBindingSource;
            this.authorSecondComboBox.DisplayMember = "ShortName";
            this.authorSecondComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.authorSecondComboBox.FormattingEnabled = true;
            this.authorSecondComboBox.Location = new System.Drawing.Point(238, 60);
            this.authorSecondComboBox.Name = "authorSecondComboBox";
            this.authorSecondComboBox.Size = new System.Drawing.Size(121, 21);
            this.authorSecondComboBox.TabIndex = 5;
            this.authorSecondComboBox.ValueMember = "ID";
            this.authorSecondComboBox.SelectedIndexChanged += new System.EventHandler(this.authorSecondComboBox_SelectedIndexChanged);
            // 
            // plusAuthorSecondLinkLabel
            // 
            this.plusAuthorSecondLinkLabel.AutoSize = true;
            this.plusAuthorSecondLinkLabel.LinkColor = System.Drawing.Color.Black;
            this.plusAuthorSecondLinkLabel.Location = new System.Drawing.Point(365, 63);
            this.plusAuthorSecondLinkLabel.Name = "plusAuthorSecondLinkLabel";
            this.plusAuthorSecondLinkLabel.Size = new System.Drawing.Size(89, 13);
            this.plusAuthorSecondLinkLabel.TabIndex = 6;
            this.plusAuthorSecondLinkLabel.TabStop = true;
            this.plusAuthorSecondLinkLabel.Text = "+ додати автора";
            this.plusAuthorSecondLinkLabel.VisitedLinkColor = System.Drawing.Color.Black;
            this.plusAuthorSecondLinkLabel.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.plusAuthorSecondLinkLabel_LinkClicked);
            // 
            // authorThirdComboBox
            // 
            this.authorThirdComboBox.DataSource = this.authorThirdBindingSource;
            this.authorThirdComboBox.DisplayMember = "ShortName";
            this.authorThirdComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.authorThirdComboBox.FormattingEnabled = true;
            this.authorThirdComboBox.Location = new System.Drawing.Point(365, 60);
            this.authorThirdComboBox.Name = "authorThirdComboBox";
            this.authorThirdComboBox.Size = new System.Drawing.Size(121, 21);
            this.authorThirdComboBox.TabIndex = 7;
            this.authorThirdComboBox.ValueMember = "ID";
            this.authorThirdComboBox.SelectedIndexChanged += new System.EventHandler(this.authorThirdComboBox_SelectedIndexChanged);
            // 
            // AddBookForm
            // 
            this.AcceptButton = this.addBookButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(510, 272);
            this.Controls.Add(this.plusAuthorSecondLinkLabel);
            this.Controls.Add(this.plusAuthorFirstLinkLabel);
            this.Controls.Add(this.dateTextBox);
            this.Controls.Add(this.addBookButton);
            this.Controls.Add(this.dateLabel);
            this.Controls.Add(this.amountTextBox);
            this.Controls.Add(this.amountLabel);
            this.Controls.Add(this.departmentComboBox);
            this.Controls.Add(this.facultyComboBox);
            this.Controls.Add(this.departmentLabel);
            this.Controls.Add(this.facultyLabel);
            this.Controls.Add(this.categoryComboBox);
            this.Controls.Add(this.categoryLabel);
            this.Controls.Add(this.authorFirstComboBox);
            this.Controls.Add(this.authorsLabel);
            this.Controls.Add(this.nameTextBox);
            this.Controls.Add(this.nameLabel);
            this.Controls.Add(this.authorSecondComboBox);
            this.Controls.Add(this.authorThirdComboBox);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "AddBookForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Додати книгу";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.AddBookForm_FormClosing);
            this.Load += new System.EventHandler(this.AddBookForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.authorFirstBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.facultyBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.departmentBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.authorSecondBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.authorThirdBindingSource)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label nameLabel;
        private System.Windows.Forms.TextBox nameTextBox;
        private System.Windows.Forms.Label authorsLabel;
        private System.Windows.Forms.ComboBox authorFirstComboBox;
        private System.Windows.Forms.Label categoryLabel;
        private System.Windows.Forms.ComboBox categoryComboBox;
        private System.Windows.Forms.Label facultyLabel;
        private System.Windows.Forms.Label departmentLabel;
        private System.Windows.Forms.ComboBox facultyComboBox;
        private System.Windows.Forms.ComboBox departmentComboBox;
        private System.Windows.Forms.Label amountLabel;
        private System.Windows.Forms.TextBox amountTextBox;
        private System.Windows.Forms.Label dateLabel;
        private System.Windows.Forms.Button addBookButton;
        private System.Windows.Forms.BindingSource authorFirstBindingSource;
        private System.Windows.Forms.BindingSource facultyBindingSource;
        private System.Windows.Forms.BindingSource departmentBindingSource;
        private System.Windows.Forms.BindingSource categoryBindingSource;
        private System.Windows.Forms.TextBox dateTextBox;
        private System.Windows.Forms.LinkLabel plusAuthorFirstLinkLabel;
        private System.Windows.Forms.ComboBox authorSecondComboBox;
        private System.Windows.Forms.LinkLabel plusAuthorSecondLinkLabel;
        private System.Windows.Forms.ComboBox authorThirdComboBox;
        private System.Windows.Forms.BindingSource authorSecondBindingSource;
        private System.Windows.Forms.BindingSource authorThirdBindingSource;
    }
}