package org.example;

import java.util.Map;
import java.util.function.BiFunction;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        traverse(word, (current, ch) -> current.children.computeIfAbsent(ch, k -> new TrieNode()), root).isEndOfWord = true;
    }

    public boolean search(String word) {
        return traverse(word, TrieNode::getChild, root) != null && traverse(word, TrieNode::getChild, root).isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return traverse(prefix, TrieNode::getChild, root) != null;
    }

    private TrieNode traverse(String word, BiFunction<TrieNode, Character, TrieNode> action, TrieNode node) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .reduce(node, (current, ch) -> current == null ? null : action.apply(current, ch), (a, b) -> b);
    }

    public void remove(String word) {
        removeHelper(word, root, 0);
    }

    private boolean removeHelper(String word, TrieNode current, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            return current.children.isEmpty();
        }

        char ch = word.charAt(index);
        TrieNode node = current.getChild(ch);
        if (node == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = removeHelper(word, node, index + 1);

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.isEmpty() && !current.isEndOfWord;
        }

        return false;
    }


    public void printTrie() {
        printNode(root, "", "", true);
    }

    private void printNode(TrieNode node, String prefix, String accumulatedWord, boolean isTail) {
        if (!accumulatedWord.isEmpty())
            System.out.println(prefix + (isTail ? "└── " : "├── ") + accumulatedWord + (node.isEndOfWord ? " [*]" : ""));

        var children = node.children.entrySet().toArray(new Map.Entry[0]);

        for (int i = 0; i < children.length; i++) {
            char childChar = (char) children[i].getKey();
            TrieNode childNode = (TrieNode) children[i].getValue();
            printNode(childNode, prefix + (isTail ? "    " : "│   "), accumulatedWord + childChar, i == children.length - 1);
        }
    }
}
