import java.sql.Timestamp;
import java.util.ArrayList;

public class Block {
    String hash;
    String prevhash;
    ArrayList<Transaction> transactions;
    Timestamp timestamp;

    public Block(double amount) {
        this.prevhash = "-1";
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.transactions = new ArrayList<>();
        this.transactions.add(new Transaction(this, "RichUncleFromTheUSA", amount));
        this.hash = "$" + hashCode() + "$";
    }

    public Block(Blockchain blockchain){
        blockchain.Lastblock.hash = "$" + hashCode() + "$";
        this.prevhash = blockchain.Lastblock.hash;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.transactions = new ArrayList<>();
        this.hash = "$" + hashCode() + "$";
        blockchain.blocks.add(this);
        blockchain.Lastblock=this;
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", prevhash='" + prevhash + '\'' +
                ", transactions=" + transactions +
                ", timestamp=" + timestamp +
                '}';
    }
}
