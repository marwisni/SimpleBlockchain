import java.util.Scanner;

public class Main {
    public static String asking(){
        System.out.println("Do you want to add new transaction to the blockchain?");
        System.out.println("(Y for \"Yes\" and anything else for \"No\")");
        return new Scanner(System.in).next();
    }
    public static void main(String[] args){
        boolean started = false;
        String scan = asking();
        Blockchain blockchain = null;
        while ("Y".equalsIgnoreCase(scan)) {
            if (!started) {
                blockchain = new Blockchain();
                started = true;
            }
            new Transaction(blockchain);
            scan = asking();
        }
        System.out.println(blockchain.toString());
        System.out.println("Thank you. Bye!");
    }
}
