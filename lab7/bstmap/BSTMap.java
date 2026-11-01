package bstmap;



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

    /**
    private class BSTIterator<V> implements Iterator<V> {
        private int wizPos;
        BSTIterator(){
            wizPos = 0;
        }

        @Override
        public boolean hasNext(){
            return wizPos < size();
        }

        @Override
        public V next(){
            return null;
        }
    }
    */

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


    public BSTMap() {
        root = null;
        size = 0;
    }


    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        BSTNode node = findKey(key);
        if (node == null) {
            return false;
        } else {
            return true;
        }
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
            root = node;
            break;

        case -1 :
            find.left = node;
            break;

        case 1 :
            find.right = node;
            break;

        }

        size++;

    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }


    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
