package DataStructures;

public class ArrayDeque<E> implements Deque<E> {
    E[] data ;
    int front =-1;
    int rear=0;
    int size=0;

    public ArrayDeque(int C){
        data= (E[]) new Object[C];
    }
    public ArrayDeque(){
        this(1000);
    }
    @Override
    public boolean isEmpty() {
        return size==0 ;
    }

    public boolean isFull() {
        return size== data.length;
    }

    @Override
    public void insertfront(E e) {
      if (!isFull()) {
          if (front == -1) {
              front = 0;
              rear = 0;
          } else if (front == 0) front = data.length-1;
          else front=front-1;
          size++;
          data[front]=e;
      } else System.out.println("Over Flow");
    }

    @Override
    public void insertRear(E e) {
        if (!isFull()) {
            if (front == -1) {
                front = 0;
                rear = 0;
            } else if (rear == data.length-1)  rear=0;
            else rear=rear+1;
            size++;
            data[rear]=e;
        } else System.out.println("Over Flow");
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public E getRear() {
        return data[rear];
    }

    @Override
    public E removeFront() {
        if (!isEmpty()) {
            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (front == data.length-1) front = 0;
            else front=front+1;
            size--;
            return data[front];
        } else System.out.println("Under Flow");
        return null;
    }

    @Override
    public E removeRear() {
        if (!isEmpty()) {
            if (front == rear) {
                front = -1;
                rear = -1;
            } else if (rear==0) rear= data.length-1;
            else rear=rear-1;
            size--;
            return data[rear];
        } else System.out.println("Under Flow");
        return null;
    }
}
