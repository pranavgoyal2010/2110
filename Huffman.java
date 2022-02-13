/**
 * This class provides methods to encode plaintext to Huffman code and decode text to plaintext text.
 * This class creates a Huffman tree to build unique huffman codes for every character.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Huffman {

    //This method encodes a plaintext to its equivalent Huffman code.
    //Method creates 2 files, Huffman.txt having Huffman code of respective characters.
    //and Encode.txt having equivalent Huffman code of entire plaintext.
    public static void encode() throws IOException
    {
        // Reading the filename containing plaintext.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename to read from/encode: ");
        String filename = scanner.nextLine();
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);


        //Initialising variable to store plaintext read from file.
        String text = "";


        //Reading plaintext from file and storing it in "text" variable.
        while(inputFile.hasNext())
        {
            text = text + inputFile.nextLine();

            //add newline character only if there are more lines in given file.
            if(inputFile.hasNext())
                text = text + "\n";
        }

        inputFile.close();


        //Calculate the number of occurrences of non-whitespace characters.
        int[] freq = new int[256];
        char[] chars = (text.replaceAll("\\s", "")).toCharArray();
        for(char c: chars)
            freq[c]++;


        //Arraylist stores Pair objects, each containing a character with its respective probability.
        ArrayList <Pair> pairs = new ArrayList<>();


        //calculating the probability of each character in the variable "text".
        for(int i=0; i<256; i++)
        {
            double prob = Math.round(freq[i]*10000d/chars.length)/10000d;
            char value = (char)i;

            //storing characters having probability greater than zero.
            if(prob > 0)
                pairs.add(new Pair(value, prob));

        }


        //Sorting list of Pairs in ascending order of probabilities.
        Collections.sort(pairs);


        //Declaring 2 Arraylists to store Binary tree nodes in ascending order.
        //List T is empty initially.
        ArrayList <BinaryTree<Pair>> S = new ArrayList<>();
        ArrayList <BinaryTree<Pair>> T = new ArrayList<>();


        //Adding nodes to List S in ascending order.
        for(int i=0; i< pairs.size(); i++)
        {
            BinaryTree<Pair> node = new BinaryTree<>();
            node.makeRoot(pairs.get(i));
            S.add(node);
        }


        //Declaring root node of the resultant Huffman tree.
        BinaryTree<Pair> rootNode = new BinaryTree<>();


        //This loop is used to construct our Huffman tree with help of minimum() method.
        //minimum() method is defined below findEncoding() methods.
        //********Adapted from https://www.youtube.com/watch?v=IX810fNtTzU***************
        while(!(S.isEmpty() && T.size()==1)) {
            //storing 2 nodes with least probabilities.
            BinaryTree<Pair> A = minimum(S, T);
            BinaryTree<Pair> B = minimum(S, T);

            //constructing a new node with probability equal to sum of node A and node B.
            BinaryTree<Pair> P = new BinaryTree<>();
            Pair pair = new Pair('⁂', A.getData().getProb() + B.getData().getProb());
            P.makeRoot(pair);

            P.attachLeft(A);
            P.attachRight(B);

            //Adding new node to arraylist T.
            T.add(P);
        }


        //Assigning the only node left in list T as the root node of this Huffman tree.
        rootNode = T.remove(0);


        //Storing a String array containing Huffman codes of each character.
        String [] encodings = findEncoding(rootNode);


        System.out.println("Printing codes to Huffman.txt");


        //Creating Huffman.txt file that stores the Huffman codes of each character.
        PrintWriter output = new PrintWriter("Huffman.txt");

        output.print("Symbol\tProb.\tHuffman Code");

        for(int i=pairs.size()-1; i>=0; i--)
        {
            output.println();
            output.print(pairs.get(i).getValue()+"\t\t"+pairs.get(i).getProb()+"\t\t"+encodings[pairs.get(i).getValue()]);
        }
        output.close();


        System.out.println("Printing encoded text to Encoded.txt");


        //Creating Encoded.txt file that stores the equivalent binary of plaintext present in Pokemon.txt file.
        output = new PrintWriter("Encoded.txt");

        for(int i=0; i<text.length(); i++)
        {
            if(text.charAt(i) == '\n')
                output.println();
            else if(text.charAt(i) == ' ')
                output.print(" ");
            else
                output.print(encodings[text.charAt(i)]);
        }
        output.close();


        System.out.println("\n*****************\n");

    }

    private static String[] findEncoding(BinaryTree<Pair> bt)
    {
        String[] result = new String[256];
        findEncoding(bt, result, "");
        return result;
    }

    private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix)
    {
        // test is node/tree is a leaf
        if (bt.getLeft()==null && bt.getRight()==null){
            a[bt.getData().getValue()] = prefix;
        }
        // recursive calls
        else{
            findEncoding(bt.getLeft(), a, prefix+"0");
            findEncoding(bt.getRight(), a, prefix+"1");
        }
    }


    //This method is used to find a node with least probability.
    public static BinaryTree<Pair> minimum(ArrayList<BinaryTree<Pair>> S, ArrayList<BinaryTree<Pair>> T)
    {
        if(S.isEmpty())
            return T.remove(0);

        if(T.isEmpty())
            return S.remove(0);

        if (S.get(0).getData().compareTo(T.get(0).getData()) == -1)
            return S.remove(0);

        return T.remove(0);
    }


    //This method decodes an encoded file. This encoded file was encoded using Huffman codes.
    public static void decode() throws IOException
    {
        //Accept user input file containing Huffman codes.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename containing Huffman codes: ");
        String codes = scanner.nextLine();
        File file = new File(codes);
        Scanner inputFile = new Scanner(file);


        //Declaring a Hash Map to store Huffman code as keys and it's equivalent character as values.
        HashMap<String, Character> map = new HashMap<>();


        //consuming title in Huffman.txt
        inputFile.nextLine();


        //Reading each line and storing character and it's equivalent Huffman code in Hash map.
        while(inputFile.hasNextLine())
        {
            char c = inputFile.next().charAt(0);
            inputFile.next(); // consume/discard probability
            String s = inputFile.next();

            // putting the character and code in Hash Map.
            map.put(s, c);
        }

        inputFile.close();


        //Accept user input filename containing encoded text.
        System.out.print("Enter the filename to read from/decode: ");
        String encodedFile = scanner.nextLine();
        file = new File(encodedFile);
        inputFile = new Scanner(file);


        System.out.println("Printing decoded text to Decoded.txt");


        //Creating Decode.txt file that prints a characters in plaintext.
        PrintWriter output = new PrintWriter("Decode.txt");

        while(inputFile.hasNextLine())
        {
            String s = inputFile.nextLine();
            String bits = "";

            //Prints characters in plaintext.
            for(int i=0; i<s.length(); i++)
            {

                if(s.charAt(i) == ' ')
                    output.print(" ");

                else {
                    bits = bits + s.charAt(i);

                    if (map.containsKey(bits)) {
                        output.print(map.get(bits));
                        bits = "";
                    }
                }
            }

            //moving cursor to new line if there are more lines in the text file.
            if(inputFile.hasNextLine())
                output.println();

        }

        output.close();

    }
}
