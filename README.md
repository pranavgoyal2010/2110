# Huffman Encoding Decoding

This project implements encoding and decoding of text using binary trees. The text is encoded based on frequency of characters resulting in size compression of text. This decreases the overall memory space occupied by the encoded text. The project applies Object Oriented Priniciples such as Inheritance, Abstarction and Encapsulation.

**Pair.java** is used to represent a character and its corresponding number of occurences(frequency) in the given text.

**BinaryTree.java** class implements the basic functionalities of a binary tree.

**Huffman.java** implements logic of encoding and decoding text resulting in compression of text without any data loss. It creates the Binary tree after calculating the frequency of each character. The tree created is called as Huffman tree

This class calculates the frequency of each character present in the input text file. The input text in this case is **Pokemon.txt**.

<img width="707" alt="image" src="https://user-images.githubusercontent.com/93623710/233726240-a0c9d566-4b90-45df-b746-a67aaebb7dfa.png">

The frequency of each charcter is calculated using the following code snippet.

<img width="482" alt="image" src="https://user-images.githubusercontent.com/93623710/233728624-7c644798-a894-46f3-a51b-b1a25a0f23a2.png">

The resulting frequencies are tabulated in **Huffman.txt**. This file also generates the Huffman code to represent a character. 

This is the code snippet which generates the **Huffman.txt** file.

<img width="719" alt="image" src="https://user-images.githubusercontent.com/93623710/233728985-e6ba519e-2c3d-401c-914d-937bbc04258a.png">

This code snippet generates the Huffman code for each character.

<img width="510" alt="image" src="https://user-images.githubusercontent.com/93623710/233729441-60a29db9-36fb-49d2-b62d-ca966891699a.png">

The **Huffman.txt** file is shown as follows.

<img width="229" alt="image" src="https://user-images.githubusercontent.com/93623710/233729175-012cc0f4-9ff2-40a4-924a-7696ba08fb8a.png">

The encoded file is saved as **Encoded.txt** and is shown as follows.

<img width="960" alt="image" src="https://user-images.githubusercontent.com/93623710/233729858-0e3be5d1-fff9-4fb7-b821-03a757e6e03c.png">

The decoded text is acheived with the help of **decode()** method in the **Huffman.java** class. The decoded text is shown as follows and looks exactly like the original text. It is stored in **Decode.txt**

<img width="960" alt="image" src="https://user-images.githubusercontent.com/93623710/233732254-54e694e4-e244-4728-9947-9fa5519ab3a1.png">

The HuffmanDemo.java class consists of the main method which executes the encoding and decoding methods.

<img width="434" alt="image" src="https://user-images.githubusercontent.com/93623710/233732554-68f01b25-f24e-42d0-b211-0bfbe553afef.png">



