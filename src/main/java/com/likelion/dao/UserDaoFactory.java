package com.likelion.dao;

public class UserDaoFactory {

    public UserDao AWSuserDao() {
        AWSConnectionMaker awsConnectionMaker = new AWSConnectionMaker();
        return  new UserDao(awsConnectionMaker);
    }
}
