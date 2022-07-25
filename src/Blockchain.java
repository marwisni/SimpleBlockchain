import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;


public class Blockchain {
    final double TOTAL_SUPPLY = 1000.0;
    final String OWNER = "Owner";
    final int MAX_TX = 10;
    Block Lastblock;
    ArrayList<Block> blocks;
    public Blockchain(){
        Lastblock = new Block(TOTAL_SUPPLY, OWNER);
        this.blocks = new ArrayList<>();
        this.blocks.add(Lastblock);
        /*
        System.out.println(" Genesis block:");
        System.out.println("Previous hash: " + Lastblock.prevhash);
        System.out.println("Timestamp: " + Lastblock.timestamp);
         */
    }
    public int Size() {
        return this.blocks.size();
    }
    public void BlockByHash(String hash){
        boolean flag = false;
        for (Block block : this.blocks) {
            if (block.hash.equals(hash)) {
                System.out.println(block.toString2());
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("There is no block with provided hash in the blockchain.");
        }
    }

    public boolean Validation(){
        Boolean flag = true;
        for (int i = 1; i <= this.blocks.size(); i++){
            if (!this.blocks.get(i-1).hash.equals(DigestUtils.sha256Hex(this.blocks.get(i-1).toStringNoHash()))){
                flag = false;
                System.out.println("Blockchain is not valid. The hash for Block number " + i + " is not correct.");
                System.out.println("Should be: "+ DigestUtils.sha256Hex(this.blocks.get(i-1).toStringNoHash()) + ",");
                System.out.println("but it is: "+ this.blocks.get(i-1).hash + ".");
            }
            for (int j = 1; j <= this.blocks.get(i-1).transactions.size(); j++){
                if (!this.blocks.get(i-1).transactions.get(j-1).hash.equals(DigestUtils.sha256Hex(this.blocks.get(i-1).transactions.get(j-1).toStringNoHash()))){
                    flag = false;
                    System.out.println("Blockchain is not valid. The hash for Transaction number " + j + " in block number " + i +" is not correct.");
                    System.out.println("Should be: "+ DigestUtils.sha256Hex(this.blocks.get(i-1).transactions.get(j-1).toStringNoHash()) + ",");
                    System.out.println("but it is: "+ this.blocks.get(i-1).transactions.get(j-1).hash + ".");
                }
            }
        }
        return flag;

    }

    public void Export(){
        String path = "flaga";
        while (path.equals("flaga")) {
            System.out.println("Path:");
            try {
                path = new Scanner(System.in).nextLine();
            } catch (Exception e) {
                System.out.println("Wrong path! Try again.");
                e.printStackTrace();
            }
            if (!path.endsWith(".json")) {
                System.out.println("Wrong path! Your path should end with \'.json\'. Try again.");
                path = "flaga";
            } else {
                Gson gson = new Gson();
                PrintWriter out = null;
                try {
                    //out = new PrintWriter(new FileWriter(path));
                    out = new PrintWriter(path);
                } catch (IOException e) {
                    System.out.println("Something go wrong!");
                    throw new RuntimeException(e);
                }
                //System.out.println("Tu powinno coś być:\n" + gson.toJson(this) + "\n****************");
                out.write(gson.toJson(this));
                out.close();
            }
        }
    }
    public void Import(){
        String path = "flaga";
        String jsonblockchain = null;
        BufferedReader fileReader = null;
        while (path.equals("flaga")) {
            System.out.println("Path:");
            try {
                path = new Scanner(System.in).nextLine();
                /*
                File jsonfile = new File(path);
                Scanner in = new Scanner(jsonfile);
                jsonblockchain = in.nextLine();
                 */
                fileReader = new BufferedReader((new FileReader(path)));
                jsonblockchain = fileReader.readLine();
            } catch (Exception e) {
                System.out.println("Wrong path! Try again.");
                e.printStackTrace();
            }
            if (!path.endsWith(".json")) {
                System.out.println("Wrong path! Your path should end with \'.json\'. Try again.");
                path = "flaga";
            } else {
                Gson gson = new Gson();
                System.out.println(jsonblockchain);
                this.blocks = gson.fromJson(jsonblockchain, Blockchain.class).blocks;
                this.Lastblock = gson.fromJson(jsonblockchain, Blockchain.class).Lastblock;
            }
        }
    }

    public double Wallet(String user){
        double balance = 0;
        for (int i = 0; i < this.blocks.size(); i++){
            for (int j = 0; j < this.blocks.get(i).transactions.size(); j++){
                if (user.equals(this.blocks.get(i).transactions.get(j).receiver)){
                    balance = balance + this.blocks.get(i).transactions.get(j).amount;
                }
                if (user.equals(this.blocks.get(i).transactions.get(j).sender)){
                    balance = balance - this.blocks.get(i).transactions.get(j).amount;
                }
            }
        }
        return balance;
    }
    public String History(String user){
        String history = "";
        Integer counter = 1;
        for (int i = 0; i < this.blocks.size(); i++){
            for (int j = 0; j < this.blocks.get(i).transactions.size(); j++){
                if (user.equals(this.blocks.get(i).transactions.get(j).receiver) || (user.equals(this.blocks.get(i).transactions.get(j).sender))){
                    history = history + "Transaction " + counter + ":\n" + this.blocks.get(i).transactions.get(j).toString2();
                    counter++;
                }
            }
        }
        return history;
    }


    @Override
    public String toString() {
        return "Blockchain{" +
                "Lastblock=" + Lastblock.toString() +
                ", blocks=" + blocks.toString() +
                '}';
    }
    public String toString2() {
        String result = "Lastblock:\n" + Lastblock.toString2() +"\n All blocks: \n";
        for (int i=1; i<=blocks.size(); i++){
            result = result + " Block number " + i + ":\n" + blocks.get(i-1).toString2();
        }
        return result;
    }

}
