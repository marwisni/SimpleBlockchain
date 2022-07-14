import java.sql.Timestamp;
import java.util.Scanner;

public class Transaction {

    public Transaction(){
        System.out.println("Sender:");
        sender = new Scanner(System.in).nextLine();
        System.out.println("Receiver:");
        receiver = new Scanner(System.in).nextLine();
        System.out.println("Amount:");
        amount = new Scanner(System.in).nextDouble();
        timestamp = new Timestamp(System.currentTimeMillis());
        hash = hashCode() + "";
    }
    String hash;
    Timestamp timestamp;
    String receiver;
    String sender;
    Double amount;
}
