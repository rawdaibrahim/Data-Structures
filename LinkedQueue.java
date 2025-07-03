package DataStructures;

import java.util.LinkedList;

public class LinkedQueue<E> implements Queue<E> {
    public static void main(String[] args) {
        LinkedQueue m= new LinkedQueue();
        for (int i=1 ; i<= 6 ; i++) {
            m.enqueue(i);
        }

        for (int i=1 ; i< 7 ; i++) {
            System.out.println( m.dequeue());
        }
        for (int i=1 ; i<= 6 ; i++) {
            m.enqueue(i);
        }
        m.dequeue();
        m.dequeue();
        m.enqueue(7);
        m.enqueue(8);
        for (int i=1 ; i< 7 ; i++) {
            System.out.println( m.dequeue());
        }

    }
    int size;
    LinkedList qu = new LinkedList<>();

    @Override
    public int size() {
        return qu.size();
    }

    @Override
    public boolean isEmpty() {
        return getSize()==0;
    }
    public int getSize(){
        return qu.size();
    }

    @Override
    public void enqueue(E e) {
        qu.addLast(e);
    }

    @Override
    public E first() {
        return (E) qu.getFirst();
    }

    @Override
    public E dequeue() {
        return (E) qu.removeFirst();
    }
}
