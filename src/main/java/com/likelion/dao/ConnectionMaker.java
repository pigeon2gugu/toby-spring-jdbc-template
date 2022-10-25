package com.likelion.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    Connection getconnection() throws ClassNotFoundException, SQLException;
}
