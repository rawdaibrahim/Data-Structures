package DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedBinaryTree<E> {
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    protected Node<E> creatNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    protected Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree() {
    }

    protected Node<E> validate(Position<E> p) {
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node) return null;
        return node;
    }

    public int size() {
        return size;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    public Node<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = creatNode(e, null, null, null);
        size = 1;
        return root;
    }

    public Node<E> addLeft(Node<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) throw new IllegalArgumentException("p already have a left child");
        Node<E> child = creatNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Node<E> addRight(Node<E> p, E e) throws IllegalArgumentException {
        Node parent = validate(p);
        if (parent.getRight() != null) throw new IllegalArgumentException("p already has a right child");
        Node<E> node = creatNode(e, parent, null, null);
        parent.setRight(node);
        size++;
        return node;
    }

    public E set(Node<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    public Node<E> sibling(Node<E> p) {
        Node<E> parent = parent(p);
        if (parent == null) return null; // p must be the root
        if (p == left(parent)) // p is a left child
            return right(parent); // (right child might be null)
        else return left(parent); // p is a right child
        //  return left(parent); // (left child might be null)
    }

    public int numChildren(Node<E> p) {
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }

    public Iterable<Node<E>> children(Node<E> p) {
        ArrayList<Node<E>> snapshot = new ArrayList<>(2); // max capacity of 2
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }

    public boolean isInternal(Node<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Node<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Node<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void attach(Node<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t1.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    public E remove(Node<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) child.setParent(node.getParent());
        if (node == root) root = child;
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) parent.setLeft(child);
            else parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }

    public int height(Node<E> p) {
        int h = 0; // base case if p is external
        for (Node<E> c : children(p))
            h = Math.max(h, 1 + height(c));
        return h;
    }

    public int num(Node<E> p) {
        int num = 0;
        for (Node<E> c : children(p))
            num++;
        return num;
    }

    int size(Node node) {
        if (node == null)
            return 0;
        else
            return (size(node.left) + 1 + size(node.right));
    }

    public int pruneSubtree(Node<E> node) throws IllegalArgumentException {
        if (node == null) return 0;
        if (node.getLeft() != null) pruneSubtree(node.getLeft());
        if (node.getRight() != null) pruneSubtree(node.getRight());
        remove(node);
        return size;
    }

    public void inOrder(Node<E> node) {
        if (node == null) return;
        if (node.getLeft() != null)
            inOrder(node.getLeft());
        System.out.println("node value is " + node.getElement() + " the height of this node " + height(node));
        if (node.getRight() != null)
            inOrder(node.getRight());

    }

    public void printPostorder(Node node) {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.getElement() + " ");
    }

    public Node<E> preOrderClone(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node clone = creatNode(node.getElement(), null, null, null);
        clone.setLeft(preOrderClone(node.left));
        clone.setRight(preOrderClone(node.right));
        return clone;
    }

    int dist = -1;

    int depth(Node<E> node) {

        // Base case
        if (node == null)
            return -1;
        return 1 + depth(node.getParent());

    }

    void printLevelOrder() {
        for (int i = 1; i <= height(root) + 1; i++) {
            for (int j = height(root) - (i) + 1; j > 0; j--) {
                System.out.print("  ");
            }
            printCurrentLevel(root, i);
            System.out.println();
            for (int j = 5; j > 1; j--) {
                System.out.print("  ");
            }
        }
    }

    int f = 10;

    public void printCurrentLevel(Node node, int level) {
        if (node == null)
            return;
        if (level == 1) {
            // f-=3;
            System.out.print(node.getElement() + "    ");
            //  for (int j=f; j>1 ; j--){
            //     System.out.print("  ");
            //  }
        } else if (level > 1) {
            printCurrentLevel(node.left, level - 1);
            printCurrentLevel(node.right, level - 1);
        }
    }

    public int insertArray(int num, int[] arr, int p) {
        if (arr[p] == 0 && ((arr[(p - 1) / 2] > num) || (arr[(p - 2) / 2] < num))) {
            arr[p] = num;
            return p;
        }
        if (num < arr[p]) return insertArray(num, arr, 2 * p + 1);
        if (num > arr[p]) return insertArray(num, arr, 2 * p + 2);
        else System.out.println("sorry we can't insert this element");
        return -1;
    }
    public void printBinaryTree(Node root) {
        LinkedList<Node> treeLevel = new LinkedList<Node>();
        treeLevel.add(root);
        LinkedList<Node> temp = new LinkedList<Node>();
        int counter = 0;
        int height = height(root)-1;
        //System.out.println(height);
        double numberOfElements = (Math.pow(2 , (height + 1)) - 1);
        //System.out.println(numberOfElements);
        while (counter <= height) {
            Node removed = treeLevel.removeFirst();
            if (temp.isEmpty()) {
                printSpace(numberOfElements / Math.pow(2 , counter + 1), removed);
            } else {
                printSpace(numberOfElements / Math.pow(2 , counter), removed);
            }
            if (removed == null) {
                temp.add(null);
                temp.add(null);
            } else {
                temp.add(removed.left);
                temp.add(removed.right);
            }

            if (treeLevel.isEmpty()) {
                System.out.println("");
                System.out.println("");
                treeLevel = temp;
                temp = new LinkedList<>();
                counter++;
            }

        }
    }

    public void printSpace(double n, Node removed){
        for(;n>0;n--) {
            System.out.print("\t");
        }
        if(removed == null){
            System.out.print(" ");
        }
        else {
            System.out.print(removed.getElement());
        }
    }



    public static void main(String[] args) {
        LinkedBinaryTree<Integer> b = new LinkedBinaryTree<>();
        Node root= (Node) b.addRoot(14);
        b.addLeft(root, 15);
        b.addRight(root, 5);
        Node left1 =root.getLeft();
        b.addLeft(left1, 3);
        b.addRight(left1, 1);
        Node left2 = left1.getLeft();
        b.addLeft(left2, 4);
        b.addRight(left2, 9);
        Node right1 =left1.getRight();
        b.addLeft(right1, 2);
        b.addRight(right1, 55);
        Node right2 = right1.getRight();
        b.addLeft(right2, 12);
        b.addRight(right2, 44);
        b.printPostorder(root);
        //Node n = b.clone(root);
        System.out.println();
      //  b.printPostorder(n);
        Node v = b.preOrderClone(root);
        System.out.print("The copy of the tree in postorder: ");
        b.printPostorder(v);
        System.out.println();
        b.inOrder(v);
        System.out.println();
        b.printLevelOrder();
        //System.out.println("Tree size after delet the subtree left2: "+b.pruneSubtree(left2));
        int[] arr = {5,3,8,2,4,7,10,0,0,0,0,0,0,9,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int index =b.insertArray(6,arr,0);
        if (index !=-1) {
            System.out.println("Index of the inserted number is: " + index+"\nThe number is: "+ arr[index]);
        }
        else System.out.println(b.insertArray(6,arr,0));
        System.out.println();
        //b.printBinaryTree(root);
        System.out.println(b.depth(left2));
        b.printBinaryTree(root);

    }

}














