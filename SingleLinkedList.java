package DataStructures;

public interface SingleLinkedList<E> {
    static class Node<E>{}
    int getSize();
    boolean isEmpty();
    E first();
    E last();
    void addFirst(E e);
    void addLast(E e);
    E removeFirst();
}
