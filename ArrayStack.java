package DataStructures;

public class ArrayStack<E> implements StacK<E> {
    E[] array;
    int top=-1;
    public ArrayStack(int CAPACITY){
        array = (E[]) new Object[CAPACITY];
    }
    public ArrayStack(){
        this(1000);
    }
    public int size(){
        return top+1;
    }
    public boolean isEmpty(){
        return (top==-1);
    }
    public void push(E e){
        top++;
        array[top]=e;
    }
    public E peek(){
        if (isEmpty())return null;
        return array[top];
    }
    public E pop(){
        if (isEmpty())return null;
        E answer= array[top];
        array[top]=null;
        top--;
        return answer;
    }
    // running time O(size()) ......
    public E[] clone(){
        E[] arr =(E[]) new Object[array.length];
        for(int i = 0; i< size() ; i++){
            arr[i] = array[i];
        }
        return arr;
    }
}
