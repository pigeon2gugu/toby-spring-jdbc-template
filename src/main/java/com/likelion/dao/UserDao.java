package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {

        // DB 접속 (sql workbench)
        Connection conn = connectionMaker.getconnection();

        //add Query
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        //Query문 실행
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public User findById(String id) throws SQLException, ClassNotFoundException {

        // DB 접속 (sql workbench)
        Connection conn = connectionMaker.getconnection();

        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();

        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) {
        System.out.println("dd");
    }
}
