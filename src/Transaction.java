import java.sql.Timestamp;
import java.util.Scanner;

public class Transaction {

    public Transaction(Blockchain blockchain){
        System.out.println("Sender:");
        this.sender = new Scanner(System.in).nextLine();
        System.out.println("Receiver:");
        this.receiver = new Scanner(System.in).nextLine();
        System.out.println("Amount:");
        this.amount = new Scanner(System.in).nextDouble();
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.hash = "$" + this.hashCode() + "$";
        System.out.println("Your transaction:\nSender: " + this.sender);
        System.out.println("Receiver: " + this.receiver);
        System.out.println("Amount: " + this.amount);
        System.out.println("Timestamp: " + this.timestamp);
        System.out.println("Hash: " + this.hash);
        if (blockchain.Lastblock.transactions.size() == blockchain.MAX_TX){
            blockchain.Lastblock.hash = "$" + blockchain.Lastblock.hashCode() + "$";
            blockchain.Lastblock = new Block(blockchain);
        }
        blockchain.Lastblock.transactions.add(this);
    }

    public Transaction(Block genesisblock, String receiver, Double amount){
        this.sender = "Nobody";
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.hash = "$" + hashCode() + "$";
        genesisblock.transactions.add(this);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "hash='" + hash + '\'' +
                ", timestamp=" + timestamp +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", amount=" + amount +
                '}';
    }

    String hash;
    Timestamp timestamp;
    String receiver;
    String sender;
    Double amount;
}
