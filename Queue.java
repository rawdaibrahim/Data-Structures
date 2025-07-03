package DataStructures;

public interface Queue<E> {
    E first();
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
}
