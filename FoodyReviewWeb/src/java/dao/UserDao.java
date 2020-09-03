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
import entity.AccountDetail;
import entity.Admin;

/**
 *
 * @author DELL
 */
public class UserDao {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> list = new ArrayList<>();
        String query = "SELECT a.[ID],[UserName],[FullName]\n"
                + "      ,[DOB]\n"
                + "      ,[Gender],[status] FROM [dbo].[Account] a,[dbo].[AccountDetalt] b\n"
                + "	  where a.ID=b.ID";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt("ID"),
                        rs.getString("UserName"),
                        "",
                        rs.getBoolean("status"));
                AccountDetail d = new AccountDetail(rs.getInt("ID"), rs.getNString("FullName"), rs.getString("DOB"), rs.getInt("Gender"));
                a.setDetail(d);
                list.add(a);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    public void delUser(int id,int status) {
        String query = "UPDATE [dbo].[Account]\n"
                + "   SET [status] = ?\n"
                + " WHERE id=?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }

    }
}
