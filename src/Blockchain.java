import java.util.ArrayList;
import java.util.Collection;

public class Blockchain {
    final double MAX_SUPPLY = 1000.0;
    final int MAX_TX = 2;
    Block Lastblock;
    ArrayList<Block> blocks;
    public Blockchain(){
        Lastblock = new Block(MAX_SUPPLY);
        this.blocks = new ArrayList<>();
        this.blocks.add(Lastblock);
        System.out.println("Genesis block:");
        System.out.println("Prevhash: " + Lastblock.prevhash);
        System.out.println("Timestamp: " + Lastblock.timestamp);
    }
    public int Size() {
        return this.blocks.size();
    }

    @Override
    public String toString() {
        return "Blockchain{" +
                "Lastblock=" + Lastblock.toString() +
                ", blocks=" + blocks.toString() +
                '}';
    }
}
