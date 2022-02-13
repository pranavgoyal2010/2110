/**
 * This class creates a Pair object containing a character and it's equivalent probability.
 * This object is stored in our Huffman tree.
 */

public class Pair implements Comparable<Pair>{
    // declare all required fields
    private char value;
    private double prob;

    //constructor
    public Pair(char value, double prob)
    {
        this.value = value;
        this.prob = prob;
    }

    //setters
    public void setValue(char value) {
        this.value = value;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    //getters
    public char getValue() {
        return value;
    }

    public double getProb() {
        return prob;
    }

    //toString
    public String toString() {
        return "Pair{" +
                "value=" + value +
                ", prob=" + prob +
                '}';
    }

    /**
     The compareTo method overrides the compareTo method of the
     Comparable interface.
     */
    public int compareTo(Pair p){
        return Double.compare(this.getProb(), p.getProb());
    }
}
