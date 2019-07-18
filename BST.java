/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.ArrayList;

/**
 *
 * @author SE130585
 */
public class BST {

    Node root;
    int size = 0;

    public BST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean insert(Word word) {
        Node newWord = new Node(word);
        Node parent = null;
        Node p = root;
        while (p != null) {
            parent = p;
            if (p.word.compareTo(word) > 0) {
                p = p.left;
            } else if (p.word.compareTo(word) < 0) {
                p = p.right;
            } else {
                p.word.addMean(word.mean);
                return false;
            }
        }
        if (root == null) {
            root = new Node(word);
        } else {
            if (parent.word.compareTo(word) > 0) {
                parent.left = new Node(word);
            } else {
                parent.right = new Node(word);
            }
        }
        size++;
        return true;
    }

    public void inorder(Node temp) {
        if (temp == null) {
            return;
        }
        inorder(temp.left);
        System.out.print(temp.word.word + "  ");
        inorder(temp.right);
    }

    public void preorder(Node temp) {
        if (temp == null) {
            return;
        }
        System.out.print(temp.word.word + "  ");
        preorder(temp.left);
        preorder(temp.right);

    }

    public void toArray(Word[] w) {
        toArray(w, root, 0);
    }

    void toArray(Word[] w, Node n, int i) {  //isSorted
        if (n != null) {
            toArray(w, n.left, i);
            w[i] = n.word;
            i++;
            toArray(w, n.right, i);
        }
    }

    Node clear(Node n) {
        if (n != null) {
            clear(n.left);
            clear(n.right);
            n = null;
            size--;
            return n;
        }
        return null;
    }

    void balance(Word[] w, int first, int last) {
        if (first <= last) {
            int mid = ((last + first) / 2);
            this.insert(w[mid]);
            balance(w, first, mid - 1);
            balance(w, mid + 1, last);
        } else {
            return;
        }
    }

    public void balance() {
        Word[] w = new Word[size];
        toArray(w, root, 0);
        root = null;
        size = 0;
        balance(w, 0, w.length - 1);

    }

    public Word search(String w) {
        Node temp = root;
        while (temp != null) {
            if (temp.word.word.equals(w)) {
                return temp.word;
            } else if (w.compareToIgnoreCase(temp.word.word) < 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return null;
    }

  
}
