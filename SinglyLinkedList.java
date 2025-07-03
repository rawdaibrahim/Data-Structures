package DataStructures;

public class SinglyLinkedList<E> implements SingleLinkedList<E>{
    static class Node<E>{
      E element ;
      Node<E> next ;
      public Node(E e , Node<E> n){
          element =e ;
          next=n;
      }
      E getElement(){
          return element;
      }
      Node<E> getNext(){
          return next;
      }
      public void setNext(Node<E> n) {
            next = n;
        }
    }
    Node<E> head = null;
    Node<E> tail = null;
    int size=0;

    public boolean isEmpty() {
        return size==0;
    }

    public int getSize() {
        return size;
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);
        if(size==0)
            tail=head;
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e,null);
        if(isEmpty())
            head=newest;
        else
            tail.setNext(newest);
        tail=newest;
        size++;
    }

    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    public E last() {
        if (isEmpty( )) return null;
        return tail.getElement( );
    }

    public E removeFirst() {
        if (isEmpty())return null;
        E remove = head.getElement();
        head= head.getNext();
        size--;
        if (size==0) tail=null;
        return remove;
    }
    public E removeLast(){
        if (isEmpty())return null;
        Node<E> m = head;
        for(int i =2; i<=size-1 ; i++){
            m=m.getNext();
        }
        E answer = tail.getElement();
        tail=m;
        return answer;
    }
    public E removeBetween(E element){
        if (isEmpty())return null;
        Node<E> node = head;
        Node<E> back= head ;
        for (int i =0 ; i<= size ; i++){
            back = node;
            node=node.getNext();
            if (node.getElement()== element) break;
        }
        back.setNext(node.getNext());
        E answer = node.getElement();
        node=null;
        size--;
        return answer;
    }
}
