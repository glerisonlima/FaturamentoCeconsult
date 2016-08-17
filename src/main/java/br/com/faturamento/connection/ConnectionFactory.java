// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionFactory.java

package br.com.faturamento.connection;

import java.sql.*;

public class ConnectionFactory
{

    public ConnectionFactory()
    {
    }

    public Connection getConnection()
        throws ClassNotFoundException
    {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try
        {
            Class.forName(driver);
            return DriverManager.getConnection("jdbc:sqlserver://10.85.8.7:1433;databaseName=SysacMe", "suporte", "!Sql2503");
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
