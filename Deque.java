package DataStructures;

public interface Deque<E> {

    boolean isEmpty();
    void insertfront(E e);
    void insertRear(E e);
    E removeFront();
    E removeRear();
    E getRear();
    E getFront();
}
