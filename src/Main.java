import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        boolean started = false;
        System.out.println("Do you want to add new transaction to the blockchain? (1=Y/anything else=N)");
        Integer scan = new Scanner(System.in).nextInt();
        if (scan == 1){
            if (!started){
                Blockchain blockchain = new Blockchain();
                System.out.println("Genesis block:");
                System.out.println("Prevhash: " + blockchain.Lastblock.prevhash);
                System.out.println("Timestamp: " + blockchain.Lastblock.timestamp);
                started = true;
            }
            Transaction transaction1 = new Transaction();
            System.out.println("Your transaction:\nSender: " + transaction1.sender);
            System.out.println("Receiver: " + transaction1.receiver);
            System.out.println("Amount: " + transaction1.amount);
            System.out.println("Timestamp: " + transaction1.timestamp);
            System.out.println("Hash: " + transaction1.hash);
            blockchain.Lastblock.transactions.add(transaction1);
            } else {
                System.out.println(scan);
                System.out.println("Thank you. Bye!");
            }

    }
}
