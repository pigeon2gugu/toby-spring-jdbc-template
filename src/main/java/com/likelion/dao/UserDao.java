package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public PreparedStatement jdbcContextWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connectionMaker.getconnection();
            pstmt = statementStrategy.makePreparedStatement(conn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }

            if (conn != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
        }
        return pstmt;
    }
    public void add(User user) throws SQLException, ClassNotFoundException {
        jdbcContextWithStatementStrategy(new AddStrategy(user));
    }

    public User findById(String id) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = connectionMaker.getconnection();
            pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }

            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        if (user == null) throw new RuntimeException();

        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        jdbcContextWithStatementStrategy(new DeleteAllStrategy());
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = connectionMaker.getconnection();
            pstmt = conn.prepareStatement("SELECT count( *) From users");
            rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }

            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }



    }
    public static void main(String[] args) {
        System.out.println("dd");
    }
}
