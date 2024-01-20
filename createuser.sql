CREATE LOGIN sbuser WITH PASSWORD = '<YourStrong@Passw0rd>';
GO

USE master;
GO

CREATE USER sbuser FOR LOGIN sbuser;
GO

EXEC sp_addrolemember 'db_ddladmin', sbuser;
GO
