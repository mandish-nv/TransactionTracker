package Project;

import java.sql.ResultSet;
import java.util.Scanner;

public class transactiondisplay {
  public void viewTable(String sql) throws Exception {
    transactionDB tdb = new transactionDB();
    ResultSet resultSet = tdb.transactionDisplay(sql);
    System.out.println("logID\tsenderID\tsender\t\tAmount\tReceiverID\tReceiver\t\t\tDate/Time");
    System.out
        .println(
            "-----------------------------------------------------------------------------------------------------------------------------");
    String format = "%5s %8s %15s %15s %10s %15s %35s";
    while (resultSet.next()) {
      System.out.printf(format,
          resultSet.getInt("logID"), resultSet.getInt("senderID"), resultSet.getString("sender"),
          "Rs." + resultSet.getDouble("amount"), resultSet.getInt("receiverID"),
          resultSet.getString("receiver"), resultSet.getString("DateTime") + "\n");
    }
  }

  public void viewUsers() throws Exception {
    transactionDB tdb = new transactionDB();
    ResultSet resultSet = tdb.transactionDisplay("select * from records;");
    System.out.println("id\tName\t\tbalance");
    System.out.println("--------------------------------------");
    while (resultSet.next()) {
      System.out.println(
          resultSet.getInt("id") + "\t" + resultSet.getString("name") + "\t\t" + resultSet.getDouble("balance"));
    }
  }

  public void viewSingleUser() throws Exception {
    transactionDB tdb = new transactionDB();
    transactionusers tuser = new transactionusers();
    Scanner obj = new Scanner(System.in);
    char ch;

    System.out.print("Enter your ID: ");
    tuser.userID = obj.nextInt();
    obj.nextLine();
    System.out.print("Enter your name: ");
    tuser.userName = obj.nextLine();

    if (!tdb.userValidity(tuser.userID, tuser.userName)) {
      System.out.println("User not found");
      ch = (char) System.in.read();
      return;
    }

    System.out.print("Enter your password: ");
    tuser.password = obj.nextLine();

    if (!tdb.passwordValidity(tuser.userID, tuser.password)) {
      System.out.println("Wrong password");
      ch = (char) System.in.read();
      return;
    }

    System.out.print("\033[H\033[2J");
    System.out.flush();
    
    System.out.println("ID: "+tuser.userID+"\tUser: " + tuser.userName +"\n\n");

    System.out.println("SENT:\n");
    viewTable("select * from logs where senderID=" + tuser.userID + ";");

    System.out.println("\n\n");
    System.out.println("RECEIVED:\n");
    viewTable("select * from logs where receiverID=" + tuser.userID + ";");
  }
}