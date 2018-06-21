Here is the algorithm:
Write a program that reads a file containing a alphabetically sorted list of words (one word per line, no spaces, all lower case) and:
1. Find the longest word in the file that can be constructed concatenating copies of shorter words also found in the file.
2. Also report the second longest word found
3. Also report the total count of words that can be constructed of other words in the file.

There are many ways to store and search strings inside text, 
like binary search trees or hash tables.
 I decided on using a trie data structure, 
 which is an ordered tree data structure where strings that share a common stem or prefix hang off a common node. 
 Tries insert and find strings in O(m) time, where m is the length of the string.
  You can code up your own trie, but I opted to use the Fast Trie library .
