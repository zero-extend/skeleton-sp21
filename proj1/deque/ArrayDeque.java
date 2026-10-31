package deque;

import java.util.Iterator;

/**
 * @author niannian
 * @date 2026/10/30 15:45
 * @description
 */
public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] arrayPointer;
    private int size;
    private int firstindex;
    private int lastindex;

    public ArrayDeque() {
        arrayPointer = (T[]) new Object[8];
        size = 0;
        firstindex = arrayPointer.length - 1;
        lastindex = 0;
    }

    private void resize(int capacity) {
        T[] pointer = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            pointer[i] = get(i);
        }

        /** new index */
        firstindex = (capacity - 1 + capacity) % capacity;
        lastindex = (size) % capacity;

        arrayPointer = pointer;
    }

    @Override
    public void addFirst(T item) {
        if (size == arrayPointer.length) {
            resize(2 * arrayPointer.length);
        }
        arrayPointer[firstindex] = item;
        firstindex = (firstindex - 1 + arrayPointer.length) % arrayPointer.length;
        size++;
    }

    @Override
    public void addLast(T items) {
        if (size == arrayPointer.length) {
            resize(2 * arrayPointer.length);
        }
        arrayPointer[lastindex] = items;
        lastindex = (lastindex + 1) % arrayPointer.length;
        size++;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println("");

    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int realIndex = (firstindex + 1) % arrayPointer.length;
        T val = arrayPointer[realIndex];
        arrayPointer[realIndex] = null;
        size--;
        firstindex = realIndex;
        if ((arrayPointer.length >= 16) && (size < (arrayPointer.length / 4))) {
            resize(arrayPointer.length / 2);
        }
        return val;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int realIndex = (lastindex - 1 + arrayPointer.length) % arrayPointer.length;
        T val = arrayPointer[realIndex];
        arrayPointer[realIndex] = null;
        size--;
        lastindex = realIndex;
        if ((arrayPointer.length >= 16) && (size < (arrayPointer.length / 4))) {
            resize(arrayPointer.length / 2);
        }
        return val;
    }

    @Override
    public T get(int index) {
        if ((index > size - 1) || (index < 0)) {
            return null;
        }

        int baseIndex = (firstindex + 1) % arrayPointer.length;
        return arrayPointer[(baseIndex + index) % arrayPointer.length];

    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        private ArrayDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T val = get(wizPos);
            wizPos++;
            return val;
        }

    }

    @Override
    public  Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<?> other = (Deque<?>) o;
            if (other.size() != this.size()) {
                return false;
            } else {
                int index = 0;
                for (T i : this) {
                    if (i.equals(other.get(index)) == false) {
                        return false;
                    }
                    index++;
                }
                return true;
            }
        } else {
            return false;
        }

    }




}
