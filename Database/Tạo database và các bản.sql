-- Tạo database 
create database FoodyDB
go

-- Tạo bảng Account
use FoodyDB
go
create table Account(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	UserName varchar(50) not null,
	Password varchar(50) not null,
	status bit not null
)
-- Tạo bảng Account Detail
use FoodyDB
go
create table AccountDetalt(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	FullName nvarchar(150) not null,
	DOB date not null, 
	Gender int not null
)

--Tạo bảng Admin
use FoodyDB
go
create table Admin(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	UserName varchar(50) not null,
	Password varchar(50) not null
)
--Tao bang Type
use FoodyDB
go
create table Type(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	Name nvarchar(50) not null
)
INSERT INTO [dbo].[Type] ([Name]) VALUES (N'Sang trọng')
GO
INSERT INTO [dbo].[Type] ([Name]) VALUES (N'Ăn vặt/vỉa hè')
GO
INSERT INTO [dbo].[Type] ([Name]) VALUES (N'Cafe/Desert')
GO
INSERT INTO [dbo].[Type] ([Name]) VALUES (N'Quán ăn')
GO
INSERT INTO [dbo].[Type] ([Name]) VALUES (N'Tiệm bánh')
GO
INSERT INTO [dbo].[Type] ([Name]) VALUES (N'Bar/pub')
GO
INSERT INTO [dbo].[Type] ([Name]) VALUES (N'Shop online')
GO
INSERT INTO [dbo].[Type] ([Name]) VALUES (N'Buffet')
GO
--Tao bang location
 use FoodyDB
 go
 create table Location(
 ID int IDENTITY(1,1) primary key,
 Name nvarchar (100) not null,
 Address nvarchar (100) not null,
 TimeOpen varchar (100) not null,
 TimeClose varchar (100) not null,
 MinPrice int not null,
 MaxPrice int not null,
 TypeID int not null,
 ImageURL nchar(200) not null,
 UserID int not null
 )
 --Tao bang rate
 use FoodyDB
 go
create table Rate(
 ID int IDENTITY(1,1) primary key,
 Comment nvarchar (500) not null,
 Score int not null,
 ImageURL nchar(200) not null,
 LocalID int not null,
 UserID int not null
 )



