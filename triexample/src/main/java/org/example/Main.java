package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        var trie = new Trie();

        var shouldQuit = false;
        var inputScanner = new Scanner(System.in);

        while (!shouldQuit) {
            String menuString = "> 1 - Inserir nova string\n> 2 - Visualizar árvore trie\n> 3 - Remover palavra \n> 4 - Consultar palavra\n> 5 - Consultar começo de palavra\n> 6 - Sair";

            System.out.println("\nEscolha uma função:");
            System.out.println(menuString);
            System.out.print("\n> ");

            var inputLine = inputScanner.nextLine();
            switch (inputLine) {
                case "1":
                    System.out.println("Escreva uma string (digite \".voltar\" para voltar):");

                    System.out.print("String a inserir: ");
                    var str = inputScanner.nextLine();
                    if (str.equalsIgnoreCase(".voltar"))
                        continue;

                    try {
                        trie.insert(str);
                    } catch (Exception e) {
                        System.out.println("Erro ao inserir string");
                        break;
                    }

                    System.out.println("String inserida com sucesso! Árvore Trie atual:");
                    trie.printTrie();
                    break;

                case "2":
                    System.out.println("Árvore Trie atual:");
                    trie.printTrie();
                    break;
                case "3":
                    System.out.println("Escreva uma string para remover (digite \".voltar\" para voltar):");

                    System.out.print("String a remover: ");
                    var wordToRemove = inputScanner.nextLine();
                    if (wordToRemove.equalsIgnoreCase(".voltar"))
                        continue;

                    trie.remove(wordToRemove);
                    System.out.println("String removida com sucesso! Árvore Trie atual:");
                    trie.printTrie();
                    break;
                case "4":
                    System.out.println("Escreva uma string para consultar (digite \".voltar\" para voltar):");

                    System.out.print("String a consultar: ");
                    var word = inputScanner.nextLine();
                    if (word.equalsIgnoreCase(".voltar"))
                        continue;

                    System.out.println("A palavra " + (trie.search(word) ? "foi" : "não foi") + " encontrada na árvore. Árvore Trie atual:");
                    trie.printTrie();
                    break;
                case "5":
                    System.out.println("Escreva uma string para consultar (digite \".voltar\" para voltar):");

                    System.out.print("String a consultar: ");
                    var needle = inputScanner.nextLine();
                    if (needle.equalsIgnoreCase(".voltar"))
                        continue;

                    System.out.println("O começo da palavra " + (trie.startsWith(needle) ? "foi" : "não foi") + " encontrado na árvore. Árvore Trie atual:");
                    trie.printTrie();
                    break;
                case "6":
                    shouldQuit = true;
                    break;
                default:
                    System.out.println("Função inválida.");
                    break;
            }
        }

        inputScanner.close();
    }
}
