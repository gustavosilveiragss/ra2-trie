package org.example;

import java.util.HashMap;

class TrieNode {
    public HashMap<Character, TrieNode> children;
    public boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }

    public TrieNode getChild(char ch) {
        return children.get(ch);
    }
}
