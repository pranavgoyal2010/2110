/**
 * This is a demo program that executes the encode and decode methods.
 */

import java.io.IOException;

public class HuffmanDemo {

    //main() method invokes the encode() and decode() methods.
    //they generate appropriate files and console outputs.
    public static void main(String [] args) throws IOException {
        Huffman.encode();
        Huffman.decode();
    }
}
