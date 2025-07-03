package DataStructures;

public class LeakyStack<E> {
    public static void main(String[] args) {
        LeakyStack<Integer> s= new LeakyStack<>(10);
        for(int i=0; i<15 ; i++){
            s.push(i);
        }
        s.print();
        System.out.println();

        System.out.println(s.undo());
        System.out.println(s.undo());
        System.out.println(s.undo());

        System.out.println();
        s.print();

        System.out.println();
        s.push(1000);
        s.push(1200);
        s.push(2445);
        System.out.println();
        s.print();
        s.push(100000000);
        s.push(1000);
        s.push(1200);
        s.push(2445);

        s.push(100000000);
        System.out.println();
        s.print();
        s.push(2445);
        s.push(100000000);
        s.push(777777);
        System.out.println(s.undo());
        System.out.println();
        s.print();
        s.push(999999999);
        System.out.println();
        s.print();
    }
    E[] array;
    int top=-1;
    int front =0;
    int size=0;
    public LeakyStack(int CAPACITY){
        array = (E[]) new Object[CAPACITY];
    }
    public LeakyStack(){
        this(20);
    }
    public int size(){
        return top+1;
    }
    public boolean isEmpty(){
        return (top==-1);
    }

    public void push(E e){
        if (size < array.length&& front==0){
            array[++top]=e;
            size++;
        }
        else{
            if (front==array.length-1)front=-1;
            int avail = (size+front)% array.length;
            array[avail]=e;
            front++;
        }

    }
    public E undo(){
        E answer=null;
        if (isEmpty())return null;
        else if (size < array.length&& front==0){
            answer= array[top];
            array[top--]=null;
            size--;
        }
        else {
            answer= array[--front];
            array[front]=null;
        }
        return answer;
    }




    public void print(){
        for(int i =0; i< array.length; i++){
            System.out.println(array[i]);
        }
        System.out.println();

        System.out.println(front);
    }
}
