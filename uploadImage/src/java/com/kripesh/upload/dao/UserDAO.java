/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kripesh.upload.dao;

import com.kripesh.upload.entity.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserDAO {
    int insert(User u)throws SQLException,ClassNotFoundException ;
    int update(User u)throws SQLException,ClassNotFoundException ;
    int delete(int id)throws SQLException,ClassNotFoundException ;
    List<User> getAll()throws SQLException,ClassNotFoundException ;
    User getById(int id)throws SQLException,ClassNotFoundException ;
    User login(String username,String password)throws SQLException,ClassNotFoundException;
}
