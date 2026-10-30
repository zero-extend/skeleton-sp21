package deque;

import java.util.IllegalFormatCodePointException;
import java.util.Iterator;

/**
 * @author niannian
 * @date 2026/10/30 15:45
 * @description
 */
public class ArrayDeque<T> implements Iterable<T>{
    private T[] array_pointer;
    private int size;
    private int firstindex;
    private int lastindex;

    public ArrayDeque(){
        array_pointer = (T[]) new Object[8];
        size = 0;
        firstindex = array_pointer.length - 1;
        lastindex = 0;
    }

    private void resize(int resize){
        T[] pointer = (T[]) new Object[resize];
        for (int i = 0; i < array_pointer.length; i++)
        {
            pointer[i] = get(i);
        }

        array_pointer = pointer;
        /** new index */
        firstindex = (resize - 1 + resize) % resize;
        lastindex = (array_pointer.length) % resize;

    }

    public void addFirst(T item){
        if (size == array_pointer.length){
            resize(2*array_pointer.length);
        }
        array_pointer[firstindex] = item;
        firstindex = (firstindex-1+array_pointer.length) % array_pointer.length;
        size++;
    }

    public void addLast(T items){
        if (size == array_pointer.length){
            resize(2*array_pointer.length);
        }
        array_pointer[lastindex] = items;
        lastindex = (lastindex+1) % array_pointer.length;
        size++;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        else {
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){

    }

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
