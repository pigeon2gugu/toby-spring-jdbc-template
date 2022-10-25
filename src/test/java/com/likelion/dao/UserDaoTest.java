package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    UserDao userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        user1 = new User("1", "kyeonghwan", "1123");
        user2 = new User("2", "sohyun", "1234");
        user3 = new User("3", "sujin", "11423");
    }

    @Test
    @DisplayName("Add and FindById")
    void addAndFindById()  throws SQLException, ClassNotFoundException {
        userDao.add(user1); //add user1 in SQL
        User selectUser = userDao.findById(user1.getId()); // findByID id = user1 id

        assertEquals(user1.getName(), selectUser.getName());
        System.out.println(selectUser.getName());
    }

    @Test
    @DisplayName("Delete All and Select")
    void delete()  throws SQLException, ClassNotFoundException {
        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        userDao.add(user2);
        assertEquals(2, userDao.getCount());
        userDao.add(user3);
        assertEquals(3, userDao.getCount());
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
    }
    @Test
    @DisplayName("getAll")
    void findAll() throws SQLException, ClassNotFoundException {
        assertEquals(0, userDao.getAll().size());
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        assertEquals(3, userDao.getAll().size());
    }


}