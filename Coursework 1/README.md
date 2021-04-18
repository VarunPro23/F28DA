This repository contains the files for coursework 1.

This coursework focuses on developing a spell-checker program in java. It is basically a word position map. Two versions of this concept are implemented: Using a Linked List and using a Hash Table.

The linked list version is using Java implementations while the Hash Table version is my own implementation.

The program takes two files as the command line arguments. The first file contains the correctly spelled words, which acts as a digital dictionary. The second file contains misspelled words.

The program first reads all words from the dictionary file and inserts them into a set data structure. Then the program reads words from the second file and checks if they are in the set. For words that do exist in the set nothing needs to be done. For words which are not in the set, the program suggests possible correct spellings by printing to the standard output.

Spelling suggestions are done in four ways:

    1.Letter Omission
   
    2.Letter Addition
  
    3.Letter Swapping
  
    4.Letter Substitution
  
The main objective of this coursework is to implement and compare a linked list and hash table based set. This is achieved by calculating the run time of both implementations.
