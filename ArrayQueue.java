package DataStructures;

public class ArrayQueue<E> implements Queue<E>{
    E[] data ;
    int size =0 ;
    int addFront =0;
    int front =0;

    public static void main(String[] args) {
        ArrayQueue m= new ArrayQueue(6);
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

    public ArrayQueue(int C){
        data = (E[]) new Object[C];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        return size==0;
    }

    @Override
    public E first() {
        return data[front-1];
    }

    @Override
    public void enqueue(E e) {
        if (size!= data.length){
            if (data [data.length-1]==null && addFront ==0) {
                data[size] = e;
                size++;
            }else{
                data[addFront]=e;
                addFront++;
                size++;

            }
        }
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
            E answer = data[front];
            data[front] = null;
            size--;
            if (front == data.length-1) front=0;
            else front++;
            return answer;
    }
}
