import java.sql.Timestamp;
import java.util.ArrayList;

public class Block {
    String hash;
    String prevhash;
    ArrayList<Transaction> transactions;
    Timestamp timestamp;

    public Block() {
        prevhash = "-1";
        timestamp = new Timestamp(System.currentTimeMillis());
        transactions = new ArrayList<>();
    }
    public static void main (String args[])
    {


        int counter;
        Transaction first = new Transaction();
        first.hash = "Hash transakcji";
    }
}
