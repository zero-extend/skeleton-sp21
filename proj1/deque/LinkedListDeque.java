package deque;

import java.util.Iterator;

/**
 * @author niannian
 * @date 2026/10/30 13:49
 * @description
 */
public class LinkedListDeque<T> implements Iterable<T>{


    private  static class node<T>{
        T val;
        node<T> head;
        node<T> next;


        public node(T value){
            this.val = value;
            this.head = this;
            this.next = this;
        }
    }

    private node<T> sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new node<T>(null);
        size = 0;
    }

    private void addinList_First(node<T> node){
        sentinel.next = node;
        sentinel.head = node;
    }

    private void deleteInList_OnlyOne(){
        sentinel.next = sentinel;
        sentinel.head = sentinel;
    }

    public void addFirst(T item)
    {
        node<T> new_point = new node<T>(item);
        if(size == 0)
        {
        addinList_First(new_point);
        }
        else{
        new_point.head = sentinel;
        new_point.next = sentinel.next;
        sentinel.next.head = new_point;
        sentinel.next = new_point;
        }
        size++;

    }

    public void addLast(T item){
        node<T> new_point = new node<T>(item);
        if(size == 0)
        {
            addinList_First(new_point);
        }
        else{
            new_point.head = sentinel.head;
            new_point.next = sentinel;
            sentinel.head.next = new_point;
            sentinel.head = new_point;
        }
        size++;
    }

    public boolean isEmpty(){
        if (size == 0)
            return true;
        else
            return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(get(i)+" ");
        }
        System.out.println("");
    }

    public T removeFirst(){
        if(size == 0)
            return null;

        node<T> del = sentinel.next;
        T val = del.val;
        if(size == 1)
        {
            deleteInList_OnlyOne();
        }
        else{
            sentinel.next = sentinel.next.next;
            sentinel.next.head = sentinel;
            del.head = null;
            del.next = null;
        }
        size--;
        return val;
    }

    public T removeLast(){
        if(size == 0)
            return null;

        node<T> del = sentinel.head;
        T val = del.val;
        if(size == 1)
        {
            deleteInList_OnlyOne();
        }
        else{
            sentinel.head = sentinel.head.head;
            sentinel.head.next = sentinel;
            del.head = null;
            del.next = null;
        }
        size--;
        return val;
    }

    public T get(int index){
        node<T> p = sentinel.next;

        if ((index > size-1)||(index < 0))
            return null;

        while(index != 0)
        {
            p = p.next;
            index--;
        }
        return p.val;
    }


    public T getRecursive(int index){
        if (index > size-1)
            return null;

        node<T> p = sentinel.next;
        T val = null;
        if(index == 0)
            val = p.val;
        else {
            val = getRecusive_true(index--,p.next);
        }
        return val;
    }

    private T getRecusive_true(int index, node<T> tnode){
        if (index == 0)
        { return tnode.val;}
        else {
            T val = null;
            val = getRecusive_true(index--,tnode.next);
            return val;
        }
    }


    private class LinkdedListDequeIterator implements Iterator<T> {
        private int wiz_pos;
        public LinkdedListDequeIterator(){
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
        return new LinkdedListDequeIterator();
    }


    @Override
    public boolean equals(Object o){
        if (o instanceof LinkedListDeque)
        {
            LinkedListDeque<T> Other = (LinkedListDeque<T>) o;
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
