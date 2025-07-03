package DataStructures;
import java.util.LinkedList;

public class LinkedStack<E>{
    private LinkedList<E> stack = new LinkedList<>();

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }


    public void push(E e) {
        stack.addFirst(e);
    }

    public E peek() {
        return stack.getFirst();
    }

    public E pop() {
        return stack.removeFirst();
    }
}
