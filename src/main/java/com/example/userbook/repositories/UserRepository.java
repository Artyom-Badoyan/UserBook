package com.example.userbook.repositories;

import com.example.userbook.configs.db.DBConnectionProvider;
import com.example.userbook.models.User;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRepository {


    private final Connection connection = DBConnectionProvider.getInstance().getConnection();


    @SneakyThrows
    public User save(User user) {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users (`name`, surname, email, password, age) values(?,?,?,?,? )",
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setInt(5, user.getAge());
        int execute = preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int id = generatedKeys.getInt(1);
        user.setId(id);
        if (execute > 0) {
            System.out.println("new user added: " + user);
        }
        return user;

    }

    @SneakyThrows
    public User getByEmail(String email) {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users u where u.email =?");
        User user = null;
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    @SneakyThrows
    public User getById(int id) {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users u where u.id =?");
        User user = new User();
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    @SneakyThrows
    public boolean existByEmail(String email) {
        boolean exist = false;
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select id from users u where u.email =?");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            exist = true;
        }
        return exist;
    }

}
