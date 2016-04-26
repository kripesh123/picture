/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kripesh.upload.dao.impl;

import com.kripesh.upload.dao.UserDAO;
import com.kripesh.upload.dbutil.DbConnection;
import com.kripesh.upload.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserDAOImpl implements UserDAO {

    private DbConnection conn;

    public UserDAOImpl() {
        conn = new DbConnection();
    }

    @Override
    public int insert(User u) throws SQLException,ClassNotFoundException {
        conn.open();
        String sql="INSERT into user(first_name,last_name,email,username,password)VALUES(?,?,?,?,?)";
        PreparedStatement stmt=conn.initStatement(sql);
        stmt.setString(1, u.getFirstName());
        stmt.setString(2, u.getLastName());
        stmt.setString(3, u.getEmail());
        stmt.setString(4, u.getUsername());
        stmt.setString(5, u.getPassword());
        int result=conn.executeUpdate();
        conn.close();
        return result;
    }

    @Override
    public int update(User u)throws SQLException,ClassNotFoundException  {
       conn.open();
       String sql="UPDATE user SET first_name=?,last_name=?,email=?,username=?,password=?,status=? WHERE id=?";
       PreparedStatement stmt=conn.initStatement(sql);
       stmt.setString(1, u.getFirstName());
       stmt.setString(2, u.getLastName());
       stmt.setString(3, u.getEmail());
       stmt.setString(4, u.getUsername());
       stmt.setString(5, u.getPassword());
       stmt.setBoolean(6, u.isStatus());
       stmt.setInt(7, u.getId());
       int result=conn.executeUpdate();
       conn.close();
       return result;
    }

    @Override
    public int delete(int id)throws SQLException,ClassNotFoundException  {
        conn.open();
        String sql="DELETE FROM user WHERE id=?";
        PreparedStatement stmt=conn.initStatement(sql);
        stmt.setInt(1, id);
        int result=conn.executeUpdate();
        conn.close();
        return result;
    }

    @Override
    public List<User> getAll()throws SQLException,ClassNotFoundException  {
        List<User> uList= new ArrayList<User>();
        conn.open();
        String sql="SELECT * FROM user WHERE staus=1";
        ResultSet rs= conn.executeQuery();
        while(rs.next()){
            uList.add(mapData(rs));
        }
        conn.close();
        return uList;
    }

    @Override
    public User getById(int id) throws SQLException,ClassNotFoundException {
        User u = null;
        conn.open();
        String sql="SELECT * FROM user WHERE id=?";
        PreparedStatement stmt=conn.initStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs=conn.executeQuery();
        while(rs.next()){
            u=mapData(rs);
        }
        conn.close();
        return u;
    }

    @Override
    public User login(String username, String password) throws SQLException,ClassNotFoundException{
        User login=null;
        conn.open();
        String sql="SELECT * FROM user WHERE username=? AND password=?";
        PreparedStatement stmt=conn.initStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = conn.executeQuery();
        while(rs.next()){
            login= new User();
            login.setId(rs.getInt("id"));
            login.setUsername(rs.getString("username"));
            login.setPassword(rs.getString("password"));
        }
        conn.close();
        return login;
    }

    private User mapData(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setFirstName(rs.getString("first_name"));
        u.setLastName(rs.getString("last_name"));
        u.setEmail(rs.getString("email"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setStatus(rs.getBoolean("status"));
        return u;
    }
}
