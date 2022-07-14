public class Main {
    public static void main(String[] args){
        Transaction transaction1 = new Transaction();
        System.out.println("Twoja transakcja:\nSender: " + transaction1.sender);
        System.out.println("Receiver: " + transaction1.receiver);
        System.out.println("Amount: " + transaction1.amount);
        System.out.println("Timestamp: " + transaction1.timestamp);
        System.out.println("Hash: " + transaction1.hash);
    }
}
