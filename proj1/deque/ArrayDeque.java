package deque;

import java.util.Iterator;

/**
 * @author niannian
 * @date 2026/10/30 15:45
 * @description
 */
public class ArrayDeque<T> implements Iterable<T>,Deque<T>{
    private T[] array_pointer;
    private int size;
    private int firstindex;
    private int lastindex;

    public ArrayDeque(){
        array_pointer = (T[]) new Object[8];
        size = 0;   // actually element size
        firstindex = array_pointer.length - 1;
        lastindex = 0;
    }

    private void resize(int capacity){
        T[] pointer = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++)
        {
            pointer[i] = get(i);
        }

        /** new index */
        firstindex = (capacity - 1 + capacity) % capacity;
        lastindex = (size) % capacity;

        array_pointer = pointer;
    }

    @Override
    public void addFirst(T item){
        if (size == array_pointer.length){
            resize(2*array_pointer.length);
        }
        array_pointer[firstindex] = item;
        firstindex = (firstindex-1+array_pointer.length) % array_pointer.length;
        size++;
    }

    @Override
    public void addLast(T items){
        if (size == array_pointer.length){
            resize(2*array_pointer.length);
        }
        array_pointer[lastindex] = items;
        lastindex = (lastindex+1) % array_pointer.length;
        size++;
    }


    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
    for (int i = 0; i < size; i++)
    {
        System.out.print(get(i)+" ");
    }
    System.out.println("");

    }

    @Override
    public T removeFirst(){
        if (size == 0)
            return null;

        int real_index = (firstindex+1)%array_pointer.length;
        T val = array_pointer[real_index];
        array_pointer[real_index] = null;
        size--;
        firstindex = real_index;
        if ((array_pointer.length >= 16)&&(size < (array_pointer.length/4))){
            resize(array_pointer.length/2);
        }
        return val;
    }

    @Override
    public T removeLast(){
        if (size == 0)
            return null;

        int real_index = (lastindex-1+array_pointer.length) % array_pointer.length;
        T val = array_pointer[real_index];
        array_pointer[real_index] = null;
        size--;
        lastindex = real_index;
        if ((array_pointer.length >= 16)&&(size < (array_pointer.length/4))){
            resize(array_pointer.length/2);
        }
        return val;
    }

    @Override
    public T get(int index){
        if ((index > size-1)||(index < 0))
            return null;

        int base_index = (firstindex + 1) % array_pointer.length;
        return array_pointer[(base_index+index) % array_pointer.length];

    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int wiz_pos;
        public ArrayDequeIterator(){
            wiz_pos = 0;
        }

        @Override
        public boolean hasNext(){
            return wiz_pos < size;
        }

        @Override
        public T next(){
            T val = get(wiz_pos);
            wiz_pos++;
            return val;
        }

    }

    @Override
    public  Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }


    @Override
    public boolean equals(Object o){
        if (o instanceof ArrayDeque )
        {
            ArrayDeque<T> Other = (ArrayDeque<T>) o;
            if (Other.size() != this.size())
                return false;
            else {
                int index = 0;
                for (T i : this)
                {
                if(i.equals(Other.get(index)) == false)
                { return false;}
                    index++;
                }
                return true;
            }
        }
        else {return false;}

    }




}
