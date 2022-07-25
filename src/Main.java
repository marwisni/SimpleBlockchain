import com.google.gson.internal.JsonReaderInternalAccess;
import org.w3c.dom.ls.LSInput;

import java.util.Scanner;

public class Main {
    public static void question(){
        System.out.println("What do you want to do?");
        System.out.println("(0) - Exit");
        System.out.println("(1) - add transaction to the blockchain");
        System.out.println("(2) - print out blockchain length");
        System.out.println("(3) - print out block details by block hash");
        System.out.println("(4) - print out block details by block height");
        System.out.println("(5) - print out last block details");
        System.out.println("(6) - print out balance of particular user");
        System.out.println("(7) - print out transaction history for particular user");
        System.out.println("(8) - export the blockchain in to JSON file");
        System.out.println("(9) - import the blockchain from the JSON file");
        System.out.println("(10) - check if blockchain is valid");
    }
    public static Integer asking(){
        Integer answer = -1;
        question();
        while (answer == -1) {
            try {
                answer = new Scanner(System.in).nextInt();
                if (answer == -1) {
                    System.out.println("Wrong number! Try again.");
                }
            } catch (Exception e) {
                System.out.println("It is not a number! Try again.");
            }
        }
        return answer;
    }
    public static Integer asking2() {
        Integer answer = -1;
        while (answer == -1) {
            try {
                answer = new Scanner(System.in).nextInt();
                if (answer == -1) {
                    System.out.println("Wrong number! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Your input is not a number! Try again.");
            }
        }
        return answer;
    }

        public static void main(String[] args){
        Integer answer = asking();
        Boolean flag = false;
        Blockchain blockchain = null;
        while(answer != 0) {
            switch (answer) {
                case 1:
                    System.out.println("You have chosen (1) - add transaction to the blockchain.");
                    if (blockchain == null) {
                        blockchain = new Blockchain();
                        System.out.println(" This will be first transaction in the blockchain so genesis block has been added to the blockchain.");
                        System.out.println("\'" + blockchain.OWNER + "\' received " + blockchain.TOTAL_SUPPLY + " in genesis transaction.");
                        System.out.println("Remember that she/he must be a sender of the first transaction as only she/he has got money so far.");
                    }
                    new Transaction(blockchain);
                    flag = false;
                    break;
                case 2:
                    System.out.println("You have chosen (2) - print out blockchain length.");
                    if (blockchain == null) {
                        System.out.println("Blockchain does not exist yet.");
                    } else {
                        System.out.println("Blockchain length is " + blockchain.Size() + " blocks.");
                    }
                    flag = false;
                    break;
                case 3:
                    System.out.println("You have chosen (3) - print out block details by block hash.");
                    System.out.println("Block hash:");
                    if (blockchain == null) {
                        System.out.println("Blockchain does not exist yet.");
                    } else {
                        String answer3 = new Scanner(System.in).next();
                        blockchain.BlockByHash(answer3);
                    }
                    flag = false;
                    break;
                case 4:
                    System.out.println("You have chosen (4) - print out block details by block height.");
                    System.out.println("Block height:");
                    if (blockchain == null) {
                        System.out.println("Blockchain does not exist yet.");
                    } else {
                        try {
                            Integer answer4 = new Scanner(System.in).nextInt();
                            System.out.println("Block number " + answer4 +":");
                            System.out.println(blockchain.blocks.get(answer4 - 1).toString2());
                        } catch (Exception e) {
                            System.out.println("There is no block with this height in the blockchain.");
                        }
                    }
                    flag = false;
                    break;
                case 5:
                    System.out.println("You have chosen (5) - print out last block details.");
                    if (blockchain == null) {
                        System.out.println("Blockchain does not exist yet.");
                    } else {
                        System.out.println("Last block: ");
                        System.out.println(blockchain.Lastblock.toString2());
                    }
                    flag = false;
                    break;
                case 6:
                    System.out.println("You have chosen (6) - print out balance of particular user");
                    System.out.println("User:");
                    if (blockchain == null) {
                        System.out.println("Blockchain does not exist yet.");
                    } else {
                        String answer6 = new Scanner(System.in).next();
                        System.out.println("Balance of user " + answer6 + " is: " + blockchain.Wallet(answer6));
                    }
                    flag = false;
                    break;
                case 7:
                    System.out.println("You have chosen (7) - print out transaction history of particular user");
                    System.out.println("User:");
                    if (blockchain == null) {
                        System.out.println("Blockchain does not exist yet.");
                    } else {
                        String answer7 = new Scanner(System.in).next();
                        System.out.println("Transaction history of user " + answer7 + " :\n" + blockchain.History(answer7));
                    }
                    flag = false;
                    break;
                case 8:
                    System.out.println("You have chosen (8) - export the blockchain in to JSON file");
                    if (blockchain == null) {
                        System.out.println("Blockchain does not exist yet.");
                    } else {
                        blockchain.Export();
                    }
                    flag = false;
                    break;
                case 9:
                    System.out.println("You have chosen (9) - import the blockchain from the JSON file.");
                    System.out.println("Current blockchain data will be lost and replaced by imported data.");
                    System.out.println("Do you want to continue?");
                    System.out.println("Y = Yes.");
                    System.out.println("N = No and go back to main menu.");
                    String answer9 = new Scanner(System.in).next();
                    while (!answer9.equalsIgnoreCase("Y") && !answer9.equalsIgnoreCase("N")) {
                        answer9 = new Scanner(System.in).next();
                    }
                    if (answer9.equalsIgnoreCase("Y")){
                        if (blockchain == null) {
                            blockchain = new Blockchain();
                        }
                        blockchain.Import();
                    }
                    flag = false;
                    break;
                case 10:
                    System.out.println("You have chosen (10) - check if blockchain is valid.");
                    if (blockchain == null) {
                        System.out.println("Blockchain does not exist yet.");
                    } else {
                        if (blockchain.Validation()){
                            System.out.println("Blockchain is valid.");
                        }
                    }
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong number! Try again.");
                    answer = asking2();
                    flag = true;
            }
            if (!flag) {
                answer = asking();
            }
        }
        try {
            System.out.println(blockchain.toString2());
        } catch (Exception e){
        }
        System.out.println("Thank you. Bye!");
    }
}
