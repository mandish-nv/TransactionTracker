package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class transactionDB {
  public void transactionEntry(String sql) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction", "root", "root");
    Statement statement = connection.createStatement();
    statement.executeUpdate(sql);
    connection.close();
  }

  public ResultSet transactionDisplay(String sql) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction", "root", "root");
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
    return resultSet;
  }

  public boolean userValidity(int id, String name) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction", "root", "root");
    Statement statement = connection.createStatement();
    String sqlQueryString = "select * from records where id=" + id + " and name='" + name + "';";
    ResultSet resultSet = statement.executeQuery(sqlQueryString);
    while (resultSet.next()) {
      if (id == resultSet.getInt("id") && name.equals(resultSet.getString("name"))) {
        return true;
      }
    }
    return false;
  }

  public boolean passwordValidity(int id, String password) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction", "root", "root");
    Statement statement = connection.createStatement();
    String sqlQueryString = "select * from records where id=" + id + " and password='" + password + "';";
    ResultSet resultSet = statement.executeQuery(sqlQueryString);
    while (resultSet.next()) {
      if (id == resultSet.getInt("id") && password.equals(resultSet.getString("password"))) {
        return true;
      }
    }
    return false;
  }

  public boolean amountValidity(int id, double amount) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction", "root", "root");
    Statement statement = connection.createStatement();
    String sqlQueryString = "select * from records where id=" + id + ";";
    ResultSet resultSet = statement.executeQuery(sqlQueryString);
    while (resultSet.next()) {
      if (id == resultSet.getInt("id") && amount <= resultSet.getDouble("balance")) {
        return true;
      }
    }
    return false;
  }

}

// create table records (id int primary key,name varchar(30),balance double,password varchar(20));
// create table logs(logID int primary key AUTO_INCREMENT,senderID int,sender varchar(30),amount double, receiverID int, receiver varchar(30));