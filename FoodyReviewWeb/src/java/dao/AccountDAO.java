/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.Account;
import entity.Admin;

/**
 *
 * @author DELL
 */
public class AccountDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Account getAccount(String username, String password) {
        String query = "SELECT [ID],[UserName],[Password],[status] FROM [dbo].[Account] where UserName=? and Password=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getInt("ID"),
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getBoolean("status"));
                return a;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Admin getAdmin(String username, String password) {
        String query = "SELECT [ID]\n"
                + "      ,[UserName]\n"
                + "      ,[Password]\n"
                + "  FROM [dbo].[Admin] where UserName=? and Password=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                Admin a = new Admin(rs.getInt("ID"),
                        rs.getString("UserName"),
                        rs.getString("Password"));
                return a;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void addAccount(String username, String password, String name, String dob, String gender) {
        String query = "INSERT INTO [dbo].[Account]([UserName],[Password],[status])VALUES (?,?,?)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setBoolean(3, true);
            ps.executeUpdate();
        } catch (Exception e) {

        }
        query = "INSERT INTO [dbo].[AccountDetalt] ([FullName],[DOB],[Gender]) VALUES (?,?,?)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, dob);
            ps.setInt(3, Integer.parseInt(gender));
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }
}
