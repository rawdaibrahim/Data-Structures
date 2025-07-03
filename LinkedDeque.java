package DataStructures;

import java.util.LinkedList;

public class LinkedDeque<E> implements Deque<E> {
    LinkedList d = new LinkedList<>();

    @Override
    public boolean isEmpty() {
        return d.isEmpty();
    }

    @Override
    public void insertRear(E e) {
        d.addFirst(e);
    }

    @Override
    public void insertfront(E e) {
        d.addLast(e);
    }

    @Override
    public E getFront() {
        return (E) d.getFirst();
    }

    @Override
    public E getRear() {
        return (E) d.getLast();
    }

    @Override
    public E removeFront() {
        return (E) d.removeFirst();
    }

    @Override
    public E removeRear() {
        return (E) d.removeLast();
    }
}
