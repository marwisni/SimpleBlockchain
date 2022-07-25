import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Block {
    String hash;
    String prevhash;
    ArrayList<Transaction> transactions;
    Timestamp timestamp;

    public Block(double amount, String owner) {
        this.prevhash = null;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.transactions = new ArrayList<>();
        this.transactions.add(new Transaction(owner, amount));
        this.hash = DigestUtils.sha256Hex(this.toStringNoHash());
    }

    public Block(Blockchain blockchain){
        //blockchain.Lastblock.hash = DigestUtils.sha256Hex(blockchain.Lastblock.toString());
        this.prevhash = blockchain.Lastblock.hash;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.transactions = new ArrayList<>();
        // this.hash = DigestUtils.sha256Hex(this.toString());
        blockchain.blocks.add(this);
        blockchain.Lastblock=this;
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", prevhash='" + prevhash + '\'' +
                ", transactions=" + transactions.toString() +
                ", timestamp=" + timestamp +
                '}';
    }
    public String toStringNoHash() {
        return "Block{" +
                ", prevhash='" + prevhash + '\'' +
                ", transactions=" + transactions.toString() +
                ", timestamp=" + timestamp +
                '}';
    }
    public String toString2() {
        String result = "hash: " + hash + "\nPrevious hash: " + prevhash + "\ntimestamp: " + timestamp + "\nTransactions:\n";
        for (int i=1; i<=transactions.size(); i++){
            result = result + " Transaction number " + i + ":\n" + transactions.get(i-1).toString2();
        }
        return result;
    }
}
