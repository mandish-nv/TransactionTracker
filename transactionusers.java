package Project;

import java.util.Scanner;

public class transactionusers {
  int userID;
  String userName = new String();
  double balance = 0;
  String password = new String();

  public void createUser(transactionDB tdb) throws Exception {
    Scanner obj = new Scanner(System.in);
    System.out.print("Enter your ID: ");
    userID = obj.nextInt();
    obj.nextLine();
    System.out.print("Enter your name: ");
    userName = obj.nextLine();
    System.out.print("Enter your balance: ");
    balance = obj.nextDouble();
    obj.nextLine();
    System.out.print("Enter your password: ");
    password = obj.nextLine();
    tdb.transactionEntry("insert into records values('" + userID + "','" + userName + "',"
        + balance + ",'" + password + "');");

    System.out.println("User has been created.");
  }

  public void transact(transactionDB tdb) throws Exception {
    double amount;
    char ch;
    String sqlQueryString;
    Scanner obj = new Scanner(System.in);

    transactionusers sender = new transactionusers();
    transactionusers receiver = new transactionusers();

    System.out.print("Enter your Sender ID: ");
    sender.userID = obj.nextInt();
    obj.nextLine();
    System.out.print("Enter your Sender name: ");
    sender.userName = obj.nextLine();

    if (!tdb.userValidity(sender.userID, sender.userName)) {
      System.out.println("not found");
      ch = (char) System.in.read();
      return;
    }

    System.out.print("Enter your Receiver ID: ");
    receiver.userID = obj.nextInt();
    obj.nextLine();
    System.out.print("Enter your Receiver name: ");
    receiver.userName = obj.nextLine();

    if (sender.userID == receiver.userID && sender.userName.equals(receiver.userName)) {
      System.out.println("ERROR! Sender and Receiver are same.");
      ch = (char) System.in.read();
      return;
    }

    if (!tdb.userValidity(receiver.userID, receiver.userName)) {
      System.out.println("User not found");
      ch = (char) System.in.read();
      return;
    }

    System.out.print("Enter amount to transfer: ");
    amount = obj.nextDouble();
    obj.nextLine();

    System.out.print("\nEnter your password: ");
    sender.password = obj.nextLine();

    if (!tdb.passwordValidity(sender.userID, sender.password)) {
      System.out.println("Wrong password");
      ch = (char) System.in.read();
      return;
    }

    // underflow
    if (!tdb.amountValidity(sender.userID, amount)) {
      System.out.println("Insufficient balance");
      ch = (char) System.in.read();
      return;
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

  }
}
