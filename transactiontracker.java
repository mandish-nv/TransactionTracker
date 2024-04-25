package Project;

import Project.transactionusers;
import Project.transactionDB;
import java.sql.ResultSet;

import java.util.Scanner;

public class transactiontracker {

  public static void main(String[] args) throws Exception {
    int option;
    char ch;
    
    Scanner obj = new Scanner(System.in);
    transactionusers tuser = new transactionusers();
    transactionDB tdb = new transactionDB();

    
    do {
      System.out.print("\033[H\033[2J");
      System.out.flush();

      System.out.println("1. Create new user");
      System.out.println("2. Money transaction"); // (password to confirm, transaction operation)
      System.out.println("3. View transaction table");
      System.out.println("4. View all users");

      System.out.print("Enter an option: ");
      option = obj.nextInt();
      obj.nextLine();
      switch (option) {
        case 1:
          //createUser();
          System.out.print("\033[H\033[2J");
          System.out.flush();
          System.out.print("Enter your ID: ");
          tuser.userID = obj.nextLine();
          System.out.print("Enter your name: ");
          tuser.userName = obj.nextLine();
          System.out.print("Enter your password: ");
          tuser.password = obj.nextLine();
          tdb.transactionEntry("insert into records values('"+tuser.userID+"','"+tuser.userName+"',"+tuser.balance+",'"+tuser.password+"');");
          ch = (char) System.in.read();
          break;
        case 2:
          //transact();
          ch = (char) System.in.read();
          break;
        case 3:
          //viewTable();
          ch = (char) System.in.read();
          break;
        case 4:
          //viewUsers();
          ResultSet resultSet = tdb.transactionDisplay("select * from records;");
          while (resultSet.next()) {
            System.out.println(resultSet.getString("id") + " " + resultSet.getString("name") + " " + resultSet.getDouble("balance"));
          }
          ch = (char) System.in.read();
          break;
        case 5:
          System.out.println("Exiting...");  
          ch = (char) System.in.read();
          break;
        default:
          System.out.println("Invalid input");
          ch = (char) System.in.read();
          break;
      }
    } while (option != 5);

  }
}
