package bstmap;



import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author niannian
 * @date 2026/11/1 19:09
 * @description
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private BSTNode root;
    Set<K> keySet;

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        private BSTNode(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }


    private class BSTIterator implements Iterator<K> {
        private Iterator<K> keyIterator;
        BSTIterator(){
           keyIterator = keySet.iterator();
        }

        @Override
        public boolean hasNext(){
            return keyIterator.hasNext();
        }

        @Override
        public K next(){
            return keyIterator.next();
        }
    }


    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }


    public BSTMap() {
        root = null;
        size = 0;
        keySet = new HashSet<K>();
    }


    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
        keySet = new HashSet<>();
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        BSTNode node = findKey(key);
        if (node == null){
            return null;
        }
        return node.value;
    }


    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * find the BSTNode of the key
     * if don't have the key, return null
     * @param key
     * @return BSTNode or null
     */
    private BSTNode findKey(K key) {
        BSTNode findPrivate = root;
        /** find the correct location and put the node */
        while (findPrivate != null) {
            if (key.compareTo(findPrivate.key) > 0) {
                findPrivate = findPrivate.right;
            } else if (key.compareTo(findPrivate.key) < 0) {
                findPrivate = findPrivate.left;
            } else {
                return findPrivate;
            }
        }
        return null;

    }

    /**
     * return the parent Node of the key Node
     * if the key is root.key, return null;
     * @param key
     * @return BSTNode or null
     */
    private BSTNode findKeyParent(K key) {
        BSTNode findParent = null;
        BSTNode findPrivate = root;
        /** find the correct location and put the node */
        while (findPrivate.key.equals(key) == false) {
            findParent = findPrivate;
            if (key.compareTo(findPrivate.key) > 0) {
                findPrivate = findPrivate.right;
            } else if (key.compareTo(findPrivate.key) < 0) {
                findPrivate = findPrivate.left;
            }
        }
        return findParent;

    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        BSTNode node = new BSTNode(key, value);
        BSTNode find = root;
        BSTNode findPrivate = root;
        int directionFlag = 0;

        /** find the correct location and put the node */
        while (findPrivate != null) {
            directionFlag = 0;
            find = findPrivate;

            if (key.compareTo(findPrivate.key) > 0) {
                findPrivate = findPrivate.right;
                directionFlag = 1;
            } else if (key.compareTo(findPrivate.key) < 0) {
                findPrivate = findPrivate.left;
                directionFlag = -1;
            } else {
                findPrivate.value = value;
                return;
            }
        }

        switch (directionFlag) {
        case 0 :
            /** if root is null, must use root instead of find,
             * because null is value, pass by value instead of address*/
            root = node;
            break;

        case -1 :
            find.left = node;
            break;

        case 1 :
            find.right = node;
            break;

        }

        keySet.add(node.key);

        size++;

    }

    public void printInOrder() {
        printRecurive(root);

    }

    private void printRecurive(BSTNode node) {
        if (node == null) {
            return ;
        } else {
            printRecurive(node.left);
            System.out.println((node.value));
            printRecurive(node.right);
        }
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * call after making sure the node isn't null
     * find the max KeyNode in the left of tree from node
     * if the left of tree is null, return null
     * @return BSTNode
     */
    private BSTNode findMaxInleft(BSTNode node) {
        if (node == null) {
            return null;
        }

        BSTNode find = node.left;
        BSTNode findPrivate = node.left;

        while (findPrivate != null) {
            find = findPrivate;
            findPrivate = findPrivate.right;
        }
        return find;
    }

    private BSTNode findMinInright(BSTNode node) {
        if (node == null) {
            return null;
        }

        BSTNode find = node.right;
        BSTNode findPrivate = node.right;

        while (findPrivate != null) {
            find = findPrivate;
            findPrivate = findPrivate.left;
        }
        return find;
    }

    private V deleteNode(BSTNode delBstNode) {
        V value = delBstNode.value;
        K key = delBstNode.key;
        BSTNode maxInLeft = findMaxInleft(delBstNode);

        /** just only has right child */
        if (maxInLeft == null) {
            BSTNode minInRight = findMinInright(delBstNode);

            /** non-child Node*/
            if (minInRight == null) {
                BSTNode NonSonFather = findKeyParent(delBstNode.key);

                /** only has root */
                if (NonSonFather == null) {
                    clear();
                    return value;
                }

                if (delBstNode.key.compareTo(NonSonFather.key) > 0) {
                    NonSonFather.right = null;
                } else {
                    NonSonFather.left = null;
                }
                size--;
                keySet.remove(key);
                return value;
            }

            BSTNode minInRightParent = findKeyParent(minInRight.key);
            delBstNode.key = minInRight.key;
            delBstNode.value = minInRight.value;
            if (minInRightParent == delBstNode) {
                minInRightParent.right = delBstNode.right;
            } else {
                minInRightParent.left = delBstNode.right;
            }size--;
            keySet.remove(key);
            return value;
        }

        /** has left child situation */
        BSTNode maxInLeftParent = findKeyParent(maxInLeft.key);
        delBstNode.key = maxInLeft.key;
        delBstNode.value = maxInLeft.value;
        if (maxInLeftParent == delBstNode) {
            maxInLeftParent.left = delBstNode.left;
        } else {
            maxInLeftParent.right = delBstNode.left;
        }
        size--;
        keySet.remove(key);
        return value;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        BSTNode delBstNode = findKey(key);

        /** If don't have this key, return null; */
        if (delBstNode == null) {
            return null;
        }

            return deleteNode(delBstNode);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
       if (get(key) != value) {
           return null;
       }
       return remove(key);
    }
}
