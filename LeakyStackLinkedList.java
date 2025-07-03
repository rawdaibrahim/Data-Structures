package DataStructures;

import java.util.LinkedList;

public class LeakyStackLinkedList<E> {
    private LinkedList<E> stack = new LinkedList<>();
    int maxCap;

    public LeakyStackLinkedList(int max){
        maxCap = max;
    }
    public int size() {
        return stack.size();
    }
    public boolean isEmpty() {
        return stack.isEmpty();
    }


    public void push(E e) {
        if (size()<maxCap){
            stack.addFirst(e);
        }
        else{
            stack.removeLast();
            push(e);
        }
    }


    public E peek() {
        return stack.getFirst();
    }


    public E pop() {
        return stack.removeFirst();
    }
}
