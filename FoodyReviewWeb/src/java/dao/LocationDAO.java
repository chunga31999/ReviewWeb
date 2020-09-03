/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.Type;
import entity.Location;
import entity.Rate;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class LocationDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Type> getAllType() {
        ArrayList<Type> list = new ArrayList<>();
        String query = "SELECT [ID],[Name] FROM [dbo].[Type]";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Type a = new Type(rs.getInt("ID"),
                        rs.getString("Name"));
                list.add(a);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    public void addLocation(String name, String address, String timeOpen, String timeClose, int minPrice, int maxPrice, int TypeID, String img, int UserId) {
        String query = "INSERT INTO [dbo].[Location]\n"
                + "           ([Name]\n"
                + "           ,[Address]\n"
                + "           ,[TimeOpen]\n"
                + "           ,[TimeClose]\n"
                + "           ,[MinPrice]\n"
                + "           ,[MaxPrice]\n"
                + "           ,[TypeID]\n"
                + "           ,[ImageURL]\n"
                + "           ,[UserID])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setNString(1, name);
            ps.setNString(2, address);
            ps.setString(3, timeOpen);
            ps.setString(4, timeClose);
            ps.setInt(5, minPrice);
            ps.setInt(6, maxPrice);
            ps.setInt(7, TypeID);
            ps.setString(8, img);
            ps.setInt(9, UserId);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public Location getLocation(int id) {
        String query = "SELECT a.[ID]\n"
                + "      ,a.[Name]\n"
                + "      ,[Address]\n"
                + "      ,[TimeOpen]\n"
                + "      ,[TimeClose]\n"
                + "      ,[MinPrice]\n"
                + "      ,[MaxPrice]\n"
                + "      ,[TypeID]\n"
                + "      ,a.[ImageURL]\n"
                + "      ,a.[UserID]\n"
                + "	  ,Type.Name as tname\n"
                + "	  ,(SELECT  AVG(Score) as score FROM Rate where LocalID=?) as score\n"
                + "	FROM [dbo].[Location] a,Type\n"
                + "	where a.ID=? and type.ID=a.TypeID";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Type t = new Type(rs.getInt("TypeID"), rs.getString("tname"));
                Location a = new Location(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("TimeOpen"),
                        rs.getString("TimeClose"),
                        rs.getInt("MinPrice"),
                        rs.getInt("MaxPrice"),
                        t,
                        rs.getString("ImageURL"),
                        rs.getString("UserID"));
                a.setScore(rs.getFloat("score"));
                return a;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public ArrayList<Location> getALLLocation() {
        ArrayList<Location> list = new ArrayList<>();
        String query = "SELECT e.[ID]\n"
                + "      ,e.[Name]\n"
                + "      ,[Address]\n"
                + "      ,[TimeOpen]\n"
                + "      ,[TimeClose]\n"
                + "      ,[MinPrice]\n"
                + "      ,[MaxPrice]\n"
                + "      ,[TypeID]\n"
                + "      ,[ImageURL]\n"
                + "      ,[UserID]\n"
                + "	  ,(SELECT  AVG(Score) as score FROM Rate where LocalID=e.ID) as score"
                + ",Type.Name as tname\n"
                + "  FROM [dbo].[Location] e, Type\n"
                + "  where type.ID=e.TypeID";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Type t = new Type(rs.getInt("TypeID"), rs.getString("tname"));
                Location a = new Location(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("TimeOpen"),
                        rs.getString("TimeClose"),
                        rs.getInt("MinPrice"),
                        rs.getInt("MaxPrice"),
                        t,
                        rs.getString("ImageURL"),
                        rs.getString("UserID"));
                a.setScore(rs.getFloat("score"));
                list.add(a);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    public ArrayList<Location> searchALLLocation(String name, ArrayList<Integer> type, int min, int max) {
        ArrayList<Location> list = new ArrayList<>();
        String query = "SELECT e.[ID]\n"
                + "      ,e.[Name]\n"
                + "      ,[Address]\n"
                + "      ,[TimeOpen]\n"
                + "      ,[TimeClose]\n"
                + "      ,[MinPrice]\n"
                + "      ,[MaxPrice]\n"
                + "      ,[TypeID]\n"
                + "      ,[ImageURL]\n"
                + "      ,[UserID]\n"
                + "	  ,(SELECT  AVG(Score) as score FROM Rate where LocalID=e.ID) as score"
                + ",Type.Name as tname\n"
                + "  FROM [dbo].[Location] e, Type\n"
                + "  where type.ID=e.TypeID and e.Name like N'%" + name + "%'";
        if (!type.isEmpty()) {
            query += "and (TypeID=0 ";
            for (Integer t : type) {
                query += " or TypeID=" + t;
            }
            query += ")";
        }
        if (min != 0) {
            query += " and MinPrice>" + min;
        }

        if (max != 0) {
            query += " and MaxPrice>" + max;
        }
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Type t = new Type(rs.getInt("TypeID"), rs.getString("tname"));
                Location a = new Location(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("TimeOpen"),
                        rs.getString("TimeClose"),
                        rs.getInt("MinPrice"),
                        rs.getInt("MaxPrice"),
                        t,
                        rs.getString("ImageURL"),
                        rs.getString("UserID"));
                a.setScore(rs.getFloat("score"));
                list.add(a);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    public ArrayList<Location> searchALLLocation(int pageindex, int pagesize,
            String name, ArrayList<Integer> type, int min, int max) {
        ArrayList<Location> list = new ArrayList<>();
        String query = "SELECT e.[ID]\n"
                + "      ,e.[Name]\n"
                + "      ,[Address]\n"
                + "      ,[TimeOpen]\n"
                + "      ,[TimeClose]\n"
                + "      ,[MinPrice]\n"
                + "      ,[MaxPrice]\n"
                + "      ,[TypeID]\n"
                + "      ,[ImageURL]\n"
                + "      ,[UserID]\n"
                + "	  ,(SELECT  AVG(Score) as score FROM Rate where LocalID=e.ID) as score"
                + ",Type.Name as tname\n FROM\n"
                + "	(SELECT *,ROW_NUMBER() OVER (ORDER BY ID ASC) as row_num FROM  [dbo].[Location]"
                + "where Name like '%" + name + "%'";

        if (!type.isEmpty()) {
            query += "and (TypeID=0 ";
            for (Integer t : type) {
                query += " or TypeID=" + t;
            }
            query += ")";
        }
        if (min != 0) {
            query += " and MinPrice>" + min;
        }

        if (max != 0) {
            query += " and MaxPrice>" + max;
        }
        query += ")e, Type\n"
                + "	WHERE type.ID=e.TypeID and row_num >= (? - 1)*? +1 AND row_num<= ? * ?";
        query += " order by score DESC";
        try {
             con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, pageindex);
            ps.setInt(2, pagesize);
            ps.setInt(3, pageindex);
            ps.setInt(4, pagesize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Type t = new Type(rs.getInt("TypeID"), rs.getString("tname"));
                Location a = new Location(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("TimeOpen"),
                        rs.getString("TimeClose"),
                        rs.getInt("MinPrice"),
                        rs.getInt("MaxPrice"),
                        t,
                        rs.getString("ImageURL"),
                        rs.getString("UserID"));
                a.setScore(rs.getFloat("score"));
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int count(String name, ArrayList<Integer> type, int min, int max) {
        String query = "SELECT count(*) FROM Location   where Name like N'%" + name + "%'";
        if (!type.isEmpty()) {
            query += "and (TypeID=0 ";
            for (Integer t : type) {
                query += " or TypeID=" + t;
            }
            query += ")";
        }
        if (min != 0) {
            query += " and MinPrice>" + min;
        }

        if (max != 0) {
            query += " and MaxPrice>" + max;
        }
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
        }

        return -1;
    }

    public void addRate(String comment, int score, String img, int localid, int userid) {
        String query = "INSERT INTO [dbo].[Rate]\n"
                + "           ([Comment]\n"
                + "           ,[Score]\n"
                + "           ,[ImageURL]\n"
                + "           ,[LocalID]\n"
                + "           ,[UserID])\n"
                + "     VALUES(?,?,?,?,?)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setNString(1, comment);
            ps.setInt(2, score);
            ps.setString(3, img);
            ps.setInt(4, localid);
            ps.setInt(5, userid);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public ArrayList<Rate> getALLRate(int localID) {
        ArrayList<Rate> list = new ArrayList<>();
        String query = "SELECT r.[ID]\n"
                + "      ,[Comment]\n"
                + "      ,[Score]\n"
                + "      ,[ImageURL]\n"
                + "      ,[UserID]\n"
                + "	  ,a.UserName\n"
                + "  FROM [dbo].[Rate] r, [Account] a\n"
                + "  where a.ID=r.UserID and LocalID=? and a.status=1";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, localID);
            rs = ps.executeQuery();
            while (rs.next()) {
//                String comment, int score, String img, int userid, int localid, String username
                Rate a = new Rate(rs.getNString("Comment"),
                        rs.getInt("Score"),
                        rs.getString("ImageURL"),
                        rs.getInt("UserID"),
                        localID,
                        rs.getString("UserName"));
                list.add(a);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }
}
