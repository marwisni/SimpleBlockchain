import java.sql.Timestamp;
import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;

public class Transaction {

    public Transaction(Blockchain blockchain){
        double senderbalance;
        System.out.println("Sender:");
        this.sender = new Scanner(System.in).nextLine();
        senderbalance = blockchain.Wallet(this.sender);
        if (senderbalance > 0) {
            System.out.println("Receiver:");
            this.receiver = new Scanner(System.in).nextLine();
            System.out.println("Amount:");
            this.amount = new Scanner(System.in).nextDouble();
            while ((this.amount < 0) || (this.amount > senderbalance)) {
                if (this.amount < 0) {
                    System.out.println("Amount must be greater than 0!");
                    System.out.println("Amount:");
                    this.amount = new Scanner(System.in).nextDouble();
                }
                if (this.amount > senderbalance) {
                    System.out.println("Not enough money!");
                    System.out.println(senderbalance + " < " + this.amount);
                    System.out.println("Amount:");
                    this.amount = new Scanner(System.in).nextDouble();
                }
            }
            this.timestamp = new Timestamp(System.currentTimeMillis());
            this.hash = DigestUtils.sha256Hex(this.toStringNoHash());
            System.out.println("Your transaction:\nSender: " + this.sender);
            System.out.println("Receiver: " + this.receiver);
            System.out.println("Amount: " + this.amount);
            System.out.println("Timestamp: " + this.timestamp);
            System.out.println("Hash: " + this.hash);
            if (blockchain.Lastblock.transactions.size() == blockchain.MAX_TX) {
                blockchain.Lastblock = new Block(blockchain);
            }
            blockchain.Lastblock.transactions.add(this);
            blockchain.Lastblock.hash = DigestUtils.sha256Hex(blockchain.Lastblock.toStringNoHash());
        } else {
            System.out.println("Your wallet is empty," + this.sender + "!");
        }
    }

    public Transaction(String receiver, Double amount){
        this.sender = null;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.hash = DigestUtils.sha256Hex(this.toStringNoHash());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "hash='" + hash + '\'' +
                ", timestamp=" + timestamp +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", amount=" + amount +
                '}' + "\n";
    }
    public String toString2() {
        return  "hash='" + hash + '\'' +
                ", timestamp=" + timestamp +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", amount=" + amount +
                '}' + "\n";
    }
    public String toStringNoHash() {
        return "Transaction{" +
                ", timestamp=" + timestamp +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", amount=" + amount +
                '}' + "\n";
    }



    String hash;
    Timestamp timestamp;
    String receiver;
    String sender;
    Double amount;
}
