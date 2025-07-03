package DataStructures;

public class DoublyLinkedList<E>{
    private static class Node<E>{
        private E element ;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e , Node<E> p , Node<E> n){
            element=e;
            prev=p;
            next=n;
        }
        public E getElement(){
            return element;
        }
        public void setNext(Node<E> n){
            next=n;
        }
        public void setPrev(Node<E> p){
            prev=p;
        }
        public Node<E> getNext(){
            return next;
        }
        public Node<E> getprev(){
            return prev;
        }

    }
    Node<E> header;
    Node<E> trailer;
    int size=0;
    public DoublyLinkedList(){
        header= new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    public int size (){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public E first (){
        if (isEmpty())return null;
        return header.getNext().getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return trailer.getprev().getElement();
    }
    public void addFirst(E e){
        addBetween(e, header, header.getNext());
    }
    public void addLast(E e){
        addBetween(e, trailer, trailer.getprev());

    }
    public E removeFirst(){
        if (isEmpty()) return null;
        else
            return remove(header.getNext());
    }
    public E removeLast(){
        if (isEmpty()) return null;
        else
            return remove(trailer.getprev());
    }
    private void addBetween(E e, Node<E> next , Node<E> prev){
        Node<E> m = new Node<>(e, prev, next);
        prev.setNext(m);
        next.setPrev(m);
        size++;
    }
    private E remove(Node<E> node){
        Node<E> prev = node.getprev();
        Node<E> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return node.getElement();
    }
}


