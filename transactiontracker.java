package Project;

import Project.transactionusers;
import Project.transactionDB;

import java.sql.ResultSet;
import java.util.Scanner;

public class transactiontracker {
  public static void main(String[] args) throws Exception {
    int option, amount;
    char ch;
    String sqlQueryString;
    ResultSet resultSet;

    Scanner obj = new Scanner(System.in);
    transactionusers tuser = new transactionusers();
    transactionusers sender = new transactionusers();
    transactionusers receiver = new transactionusers();
    transactionDB tdb = new transactionDB();

    do {
      System.out.print("\033[H\033[2J");
      System.out.flush();

      System.out.println("1. Create new user");
      System.out.println("2. Money transaction");
      System.out.println("3. View transaction table");
      System.out.println("4. View all users");
      System.out.println("5. Exit");

      System.out.print("\nEnter an option: ");
      option = obj.nextInt();
      switch (option) {
        case 1:
          // createUser();
          System.out.print("\033[H\033[2J");
          System.out.flush();

          System.out.print("Enter your ID: ");
          tuser.userID = obj.nextInt();
          obj.nextLine();
          System.out.print("Enter your name: ");
          tuser.userName = obj.nextLine();
          System.out.print("Enter your balance: ");
          tuser.balance = obj.nextDouble();
          obj.nextLine();
          System.out.print("Enter your password: ");
          tuser.password = obj.nextLine();
          tdb.transactionEntry("insert into records values('" + tuser.userID + "','" + tuser.userName + "',"
              + tuser.balance + ",'" + tuser.password + "');");

          System.out.println("User has been created.");
          ch = (char) System.in.read();
          break;

        case 2:
          // transact();
          System.out.print("\033[H\033[2J");
          System.out.flush();

          System.out.print("Enter your Sender ID: ");
          sender.userID = obj.nextInt();
          obj.nextLine();
          System.out.print("Enter your Sender name: ");
          sender.userName = obj.nextLine();

          if (!tdb.userValidity(sender.userID, sender.userName)) {
            System.out.println("not found");
            ch = (char) System.in.read();
            break;
          }

          System.out.print("Enter your Receiver ID: ");
          receiver.userID = obj.nextInt();
          obj.nextLine();
          System.out.print("Enter your Receiver name: ");
          receiver.userName = obj.nextLine();

          if (sender.userID == receiver.userID && sender.userName.equals(receiver.userName)) {
            System.out.println("ERROR! Sender and Receiver are same.");
            ch = (char) System.in.read();
            break;
          }

          if (!tdb.userValidity(receiver.userID, receiver.userName)) {
            System.out.println("User not found");
            ch = (char) System.in.read();
            break;
          }

          System.out.print("Enter amount to transfer: ");
          amount = obj.nextInt();
          obj.nextLine();

          System.out.print("\nEnter your password: ");
          sender.password = obj.nextLine();

          if (!tdb.passwordValidity(sender.userID, sender.password)) {
            System.out.println("Wrong password");
            ch = (char) System.in.read();
            break;
          }

          // underflow
          if (!tdb.amountValidity(sender.userID, amount)) {
            System.out.println("Insufficient balance");
            ch = (char) System.in.read();
            break;
          }

          // transaction
          sqlQueryString = "update records set balance = balance + " + amount + " where id=" + receiver.userID + ";";
          tdb.transactionEntry(sqlQueryString);
          sqlQueryString = "update records set balance = balance - " + amount + " where id=" + sender.userID + ";";
          tdb.transactionEntry(sqlQueryString);

          tdb.transactionEntry("insert into logs(senderID,sender,amount,receiverID,receiver) values (" + sender.userID
              + ",'" + sender.userName + "'," + amount + "," + receiver.userID + ",'" + receiver.userName + "')");

          System.out
              .println("\nRs." + amount + " has been transferred from " + sender.userName + " to " + receiver.userName);

          ch = (char) System.in.read();
          break;

        case 3:
          // viewTable();
          System.out.print("\033[H\033[2J");
          System.out.flush();

          resultSet = tdb.transactionDisplay("select * from logs;");
          System.out.println("logID\tsenderID\tsender\t\tAmount\t\tReceiverID\tReceiver");
          System.out
              .println("-----------------------------------------------------------------------------------------");
          while (resultSet.next()) {
            System.out.println(
                resultSet.getInt("logID") + "\t" + resultSet.getInt("senderID") + "\t\t" + resultSet.getString("sender")
                    + "\t\t" + resultSet.getDouble("amount") + "\t\t" + resultSet.getInt("receiverID") + "\t\t"
                    + resultSet.getString("receiver"));
          }

          ch = (char) System.in.read();
          break;

        case 4:
          // viewUsers();
          System.out.print("\033[H\033[2J");
          System.out.flush();

          resultSet = tdb.transactionDisplay("select * from records;");
          System.out.println("id\tName\t\tbalance");
          System.out.println("--------------------------------------");
          while (resultSet.next()) {
            System.out.println(
                resultSet.getInt("id") + "\t" + resultSet.getString("name") + "\t\t" + resultSet.getDouble("balance"));
          }
          ch = (char) System.in.read();
          break;

        case 5:
          System.out.println("\nExiting...");
          ch = (char) System.in.read();
          break;

        default:
          System.out.println("\nInvalid input");
          ch = (char) System.in.read();
          break;
      }
    } while (option != 5);
    obj.close();
  }
}
