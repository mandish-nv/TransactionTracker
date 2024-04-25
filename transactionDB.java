package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class transactionDB {
  public void transactionEntry(String sql) throws Exception{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction", "root", "root");
      Statement statement = connection.createStatement();
      statement.executeUpdate(sql);
      connection.close(); 
  }

  public ResultSet transactionDisplay(String sql) throws Exception{
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction", "root", "root");
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
    return resultSet;
  }

  

}
