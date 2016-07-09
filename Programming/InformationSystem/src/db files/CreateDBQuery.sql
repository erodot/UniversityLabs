CREATE DATABASE DBEArchive
GO

USE DBEArchive
GO

CREATE TABLE Faculties(
	ID int IDENTITY(0,1) CONSTRAINT PK_Faculties PRIMARY KEY,
	Name nvarchar(30) NOT NULL
)
GO

CREATE TABLE Departments(
	ID int IDENTITY(0,1),
	Name nvarchar(60) NOT NULL,
	FacultyID int NOT NULL,
	CONSTRAINT PK_Departments PRIMARY KEY(ID),
	CONSTRAINT FK_Departments_FacultyID FOREIGN KEY (FacultyID) REFERENCES Faculties(ID)
)
GO

CREATE TABLE Categories(
	ID int IDENTITY(0,1),
	Name nvarchar(30) NOT NULL,
	CONSTRAINT PK_Categories PRIMARY KEY(ID)
)
GO

CREATE TABLE Authors(
	ID int IDENTITY(0,1),
	Surname nvarchar(30),
	FirstName nvarchar(30),
	SecondName nvarchar(30),
	ShortName nvarchar(30),
	CONSTRAINT PK_Authors PRIMARY KEY(ID)
)
GO

CREATE TABLE Publications(
	ID int IDENTITY(1,1),
	Name nvarchar(128) NOT NULL,
	CONSTRAINT PK_Publications PRIMARY KEY(ID),
)
GO

CREATE TABLE AuthorsOfPublications(
	ID int IDENTITY(1,1),
	PublicationID int NOT NULL,
	AuthorID int NOT NULL,
	CONSTRAINT PK_AuthorsOfPublications PRIMARY KEY(ID),
	CONSTRAINT FK_AuthorsOfPublications_PublicationID FOREIGN KEY(PublicationID) REFERENCES Publications(ID),
	CONSTRAINT FK_AuthorsOfPublications_AuthorID FOREIGN KEY(AuthorID) REFERENCES Authors(ID)
)
GO

CREATE TABLE Archive(
	ID int IDENTITY(1,1),
	PubID int NOT NULL,
	DepartmentID int,
	FacultyID int,
	CategoryID int NOT NULL,
	Amount int,
	PubDate date NOT NULL,
	CONSTRAINT PK_Archive PRIMARY KEY(ID),
	CONSTRAINT FK_Archive_DepartmentID FOREIGN KEY(DepartmentID) REFERENCES Departments(ID),
	CONSTRAINT FK_Archive_FacultyID FOREIGN KEY(FacultyID) REFERENCES Faculties(ID),
	CONSTRAINT FK_Archive_TypeID FOREIGN KEY(CategoryID) REFERENCES Categories(ID),
	CONSTRAINT FK_Archive_PubID FOREIGN KEY(PubID) REFERENCES Publications(ID)
)
GO

IF OBJECT_ID (N'dbo.AuthorsOfPub', N'FN') IS NOT NULL
    DROP FUNCTION dbo.ISOweek;
GO
CREATE FUNCTION dbo.AuthorsOfPub(@pub_id int)
RETURNS TABLE
AS
RETURN
(
	WITH AuthorsCTE (AuID, ShortName) AS
	(
    SELECT 1, CAST('' AS NVARCHAR(30)) 
    UNION ALL
    SELECT B.AuID + 1, CAST(B.ShortName +  A.ShortName + ', ' AS NVARCHAR(30)) 
    FROM (SELECT Row_Number() OVER (ORDER BY aop.ID) AS RN, au.ShortName FROM AuthorsOfPublications aop JOIN Authors au ON au.ID=aop.AuthorID WHERE aop.PublicationID=@pub_id) AS A 
    JOIN AuthorsCTE AS B ON A.RN = B.AuID 
	)
	SELECT TOP 1 LEFT(au_cte.ShortName,LEN(au_cte.ShortName)-1) AS Authors FROM AuthorsCTE au_cte ORDER BY au_cte.AuID DESC
)
GO