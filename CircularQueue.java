package DataStructures;

public class CircularQueue<E> implements Queue<E> {
    private E[] data ;
    private int size=0;
    private int front =0;
    public CircularQueue(int capacity){
        data=(E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public void enqueue(E e) {
        if(size== data.length) return;
        int avail = (front+size)% data.length;
        data[avail] = e ;
        size++;
    }
    public E first(){
        if (isEmpty())return null;
        return data[front];
    }
    public E dequeue(){
        if (isEmpty())return null;
        E answer = data[front];
        data[front]=null;
        front = (front+1)% data.length;
        size--;
        return answer;
    }
}
