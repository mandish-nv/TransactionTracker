package Project;

import java.sql.ResultSet;

public class transactiondisplay {
  public void viewTable(transactionDB tdb) throws Exception {
    ResultSet resultSet;
    resultSet = tdb.transactionDisplay("select * from logs;");
    System.out.println("logID\tsenderID\tsender\t\tAmount\t\tReceiverID\t\tReceiver");
    System.out
        .println("-----------------------------------------------------------------------------------------");
    while (resultSet.next()) {
      System.out.println(
          resultSet.getInt("logID") + "\t" + resultSet.getInt("senderID") + "\t\t" + resultSet.getString("sender")
              + "\t\tRs." + resultSet.getDouble("amount") + "\t\t" + resultSet.getInt("receiverID") + "\t\t"
              + resultSet.getString("receiver"));
    }
  }

  public void viewUsers(transactionDB tdb) throws Exception {
    ResultSet resultSet;
    resultSet = tdb.transactionDisplay("select * from records;");
    System.out.println("id\tName\t\tbalance");
    System.out.println("--------------------------------------");
    while (resultSet.next()) {
      System.out.println(
          resultSet.getInt("id") + "\t" + resultSet.getString("name") + "\t\t" + resultSet.getDouble("balance"));
    }
  }
}
