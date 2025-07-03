package DataStructures;

import com.company.StopWatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Math.max;

public class AVL<T extends Comparable<T>> {
    private class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

        private String data;
        private T phone;
        private T email;
        private T address;
        private Node<T> left;
        private Node<T> right;

        public Node(String data , T phone , T email , T address) {
            this(data, phone , email , address , null, null);
        }

        public Node(String data, T phone , T email , T address , Node<T> left, Node<T> right) {
            super();
            this.data = data;
            this.phone= phone ;
            this.email = email ;
            this.address= address;
            this.left = left;
            this.right = right;
        }



        public String getName() {
            return data;
        }

        public T getPhoneNumber() {
            return phone;
        }

        public T getEmail() {
            return email;
        }

        public T getAddress() {
            return address;
        }

        public void setName(String data) {
            this.data = data;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public void setAddress(T address) {
            this.address = address;
        }

        public void setPhone(T phone) {
            this.phone = phone;
        }

        public void setEmail(T email) {
            this.email = email;
        }

        public int compareTo(Node<T> o) {
            return this.data.compareTo(o.data);
        }

    }
    Node<T> root = null;

    public Node<T> getRoot(){
        return root;
    }
    private int size=0;

    public int getSize() {
        return size;
    }

    private int depth(Node<T> node) {
        if (node == null)
            return 0;
        return 1 + Math.max(depth(node.getLeft()), depth(node.getRight()));
    }

    public Node<T> insert(Node<T> node ,String name , T phone , T email , T address){
        if (root==null){
            root =new Node<>(name, phone , email , address);
            size++;
            return root;
        }
        if (node == null){
            size++;
            return new Node<>(name, phone , email , address);
        }
        if (node.getName().compareTo(name) >0){
                node.setLeft(insert(node.getLeft(),name, phone, email, address));
        }
        else if (node.getName().compareTo(name) <0) {
                node.setRight(insert(node.getRight(),name, phone, email, address));
        }
        node= rotation(node);
        return node;
    }

    private int balanceNumber(Node<T> node) {
        int L = depth(node.getLeft());
        int R = depth(node.getRight());
        if (L - R >= 2)
            return -1;
        else if (L - R <= -2)
            return 1;
        return 0;
    }
    public Node<T> minimum(Node<T> node) {
        if (node.getLeft()==null)
            return node;
        if (node==null)
            return null;
        return minimum(node.getLeft());
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return right;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return left;
    }
    public boolean search(String data) {
        Node<T> local = root;
        while (local != null) {
            if (local.getName().equals(data)){
                System.out.println("This contact has been found with : \n    phone number-> " + local.getPhoneNumber()+ "\n    email-> "+ local.getEmail()+ "\n    address-> "+ local.getAddress());
                return true;
            }
            else if (local.getName().compareTo(data) > 0)
                local = local.getLeft();
            else
                local = local.getRight();
        }
        return false;
    }
    public LinkedQueue<String> searchFirst(String data) {
        Node<T> node = root;
        String name ;
        LinkedQueue<String> names= new LinkedQueue<>();
        while (node != null) {
            name = node.getName().substring(0, node.getName().indexOf(" "));
            if (name.compareTo(data) == 0){
                names.enqueue(node.getName());
            }
            if (name.compareTo(data) > 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return names;
    }
    public void inOrder(Node<T> node) {
        if (node == null) return;
        if (node.getLeft() != null)
            inOrder(node.getLeft());
        System.out.println(node.getName()+" : "+node.getPhoneNumber()+" : "+node.getEmail()+" : " + node.getAddress());
        if (node.getRight() != null)
            inOrder(node.getRight());
    }
    LinkedQueue<String> names = new LinkedQueue<>();
    public LinkedQueue<String> inOrderSearch(Node<T> node, String key) {
        if (node == null)
            return null;

        if (node.getLeft() != null)
            inOrderSearch(node.getLeft(), key);

        if (node.getName().contains(key)) {
            names.enqueue(node.getName());
        }

        if (node.getRight() != null)
            inOrderSearch(node.getRight(), key);

        return names;
    }
    public int height(Node<T> root)
    {
        if (root == null)
            return 0;
        return 1 + max(height(root.getLeft()), height(root.getRight()));
    }
    public Node<T> deleteNode(Node<T> node, String name)
    {
        if (node == null){
            //System.out.println("contact not found");
            return node;
        }
        if ( node.getName().compareTo(name)>0)
            node.setLeft(deleteNode(node.getLeft(), name));

        else if (node.getName().compareTo(name)<0)
            node.setRight(deleteNode(node.getRight(), name));

            // if key is same as root's key, then this is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if ((node.left == null) || (node.right == null))
            {
                if (node.getRight()!=null)
                    node = node.getRight();
                else if (node.getLeft()!=null)
                    node = node.getLeft();
                else {
                    node = null;
                    return node ;
                }
                size--;
            }
            else
            {
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node<T> temp = minimum(node.right);

                // Copy the inorder successor's data to this node
                node.setName(temp.getName());
                node.setAddress(temp.getAddress());
                node.setPhone(temp.getPhoneNumber());
                node.setEmail(temp.getEmail());

                // Delete the inorder successor
                node.right = deleteNode(node.right, temp.getName());

            }
        }

        //node.setDepth(1 + Math.max(depth(node.getLeft()), depth(node.getRight())));
        node = rotation(node);
        return node;
    }
    public Node<T> rotation(Node<T> root){
        int balance = balanceNumber(root);

        /// Left Left Case
        if (balance > 1 && balanceNumber(root.left) >= 0)
            return rotateRight(root);

        // Left Right Case
        if (balance > 1 && balanceNumber(root.left) < 0)
        {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // Right Right Case
        if (balance < -1 && balanceNumber(root.right) <= 0)
            return rotateLeft(root);

        // Right Left Case
        if (balance < -1 && balanceNumber(root.right) > 0)
        {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        return root;
    }
    void printCurrentLevel(Node root, int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.data + " ");
        else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }
    void printLevelOrder(int level)
    {
        //int h = height(root);
        int i;
        for (i = level; i <= level+2; i++) {
            printCurrentLevel(root, i);
            System.out.println();
        }
    }

    public void AVLOperations(int selected){
        StopWatch w = new StopWatch();
        //printLevelOrder(0);
        //printBinaryTree(root);
        Scanner input = new Scanner(System.in);
        switch (selected) {
            case 1 -> {
                System.out.print("Enter contact name : ");
                Scanner inpu = new Scanner(System.in);
                String name = inpu.nextLine();
                System.out.print("Enter contact number : ");
                T number = (T) input.next();
                System.out.print("Enter contact email : ");
                T email = (T) input.next();
                System.out.print("Enter contact address : ");
                Scanner in = new Scanner(System.in);
                T address = (T) in.nextLine();
                w.start();
                insert(root, name, number, email, address);
                w.stop();
                System.out.println("It takes : " +w.getElapsedTime()+ " nanosecond to insert this element");
            }
            case 2 -> {
                System.out.print("Enter contact name : ");
                Scanner i = new Scanner(System.in);
                String delete = i.nextLine();
                int s = size;
                w.start();
                deleteNode(root , delete);
                w.stop();
                s-=size;
                if (s==0)
                    System.out.println("There's no contact with this name");
                else
                    System.out.println("It takes : " +w.getElapsedTime()+ " nanosecond to delete this element");
            }
            case 3 -> {
                System.out.println("If you want to search with:\n   first name and last name choose 1 \n   first name choose 2 \n   last name choose 3 \n   part of them choose 4");
                int search = input.nextInt();
                System.out.println("Enter your search key : ");
                Scanner inp = new Scanner(System.in);
                String key = inp.nextLine();
                switch (search) {
                    case 1 -> {
                        w.start();
                        boolean inserted = search(key);
                        w.stop();
                        if (!inserted)
                            System.out.println("There's no contact with this name");
                        else
                            System.out.println("It takes : " +w.getElapsedTime()+ " nanosecond to search for this contact");
                    }
                    case 2 -> {
                        w.start();
                        LinkedQueue<String> names = searchFirst(key);
                        w.stop();
                        if (names.size() == 0)
                            System.out.println("There's no contact with this name");
                        else if (names.size() == 1) {
                            System.out.println("One name match : " + names.first() + "\n   Are you looking for this name?");
                            boolean match = input.nextBoolean();
                            if (match) {
                                search(names.dequeue());
                            }
                        } else {
                            System.out.println(names.size() + " name match");
                            while (!names.isEmpty()) {
                                System.out.println(names.dequeue());
                            }
                            boolean match = input.nextBoolean();
                            if (match) {
                                System.out.println("Which name of them ?");
                                key = input.next();
                                search(key);
                            }
                        }
                        System.out.println("It takes : " +w.getElapsedTime()+ " nanosecond to search for this contact");
                    }
                    case 3, 4 -> {
                        w.start();
                        LinkedQueue<String> contacts = inOrderSearch(root, key);
                        w.stop();
                        if (contacts.size() == 0)
                            System.out.println("There's no contact with this name");
                        else if (contacts.size() == 1) {
                            System.out.println("One name match : " + contacts.first() + "\n   Are you looking for this name?");
                            boolean match = input.nextBoolean();
                            if (match) {
                                search(contacts.dequeue());
                            }
                        } else {
                            System.out.println(contacts.size() + " name match :");
                            while (!contacts.isEmpty()) {
                                System.out.println(contacts.dequeue());
                            }
                            System.out.println("Do you look for any name of them ?");
                            boolean match = input.nextBoolean();
                            if (match) {
                                System.out.println("Which name of them ?");
                                Scanner m = new Scanner(System.in);
                                key = m.nextLine();
                                search(key);
                            }
                        }
                        System.out.println("It takes : " +w.getElapsedTime()+ " nanosecond to search for this contact");
                    }


                }
            } case 4 -> {
                System.out.println("To:\n   get Number of nodes choose 1 \n   Apply inorder traversal choose 2 \n   get the Height of the tree choose 3");
                int choice = input.nextInt();
                switch (choice){
                    case 1-> {
                        System.out.println(getSize());
                        System.out.println("it will take a constant time");
                    }
                    case 2 -> {
                        w.start();
                        inOrder(getRoot());
                        w.stop();
                        System.out.println("It takes : " +w.getElapsedTime()+ " nanosecond to get all contacts in inOrder traversal");
                    }
                    case 3 -> {
                        System.out.println(height(getRoot()));
                        System.out.println("It takes : " +w.getElapsedTime()+ " nanosecond to get the height of the tree");
                    }
                }

            }
        }
    }
        public static void main(String[] args) throws IOException {
            String[] names ;
            String[] phone;
            String[] email ;
            String[] address;
            String[] data ;
            int count =0;
            BufferedReader reader = null;
            String line = "" ;
            try {
                Path countLines = Paths.get("C:\\Users\\DELL\\Documents\\contacts.csv");
                count = (int) Files.lines(countLines).count();
                names = new String[count - 1];
                phone = new String[count - 1];
                email = new String[count - 1];
                address = new String[count - 1];
                reader = new BufferedReader(new FileReader("C:\\Users\\DELL\\Documents\\contacts.csv"));
                reader.readLine();
                for (int i = 0; i <= count - 2; i++) {
                    line = reader.readLine();
                    data = line.split(",");
                    names[i] = data[0];
                    phone[i] = data[1];
                    email[i] = data[2];
                    if (line.charAt(line.length()-1)=='"'){
                        address[i]= line.substring(data[0].length()+data[1].length()+data[2].length()+4, line.length()-1);
                    }else
                        address[i] = data[3];
                }
                int choice=0;
                while (choice != 4){
                System.out.println("To build AVL CHOOSE 1 \nTo build a Heap CHOOSE 2 \nTo build 2-4 Tree CHOOSE 3 \nTo exit CHOOSE 4");
                Scanner input = new Scanner(System.in);
                choice = input.nextInt();
                switch (choice) {
                    case 1 -> {
                        AVL<String> phoneBook = new AVL<>();
                        for (int i = 0; i <= count - 2; i++) {
                            phoneBook.insert(phoneBook.root, names[i], phone[i], email[i], address[i]);
                        }
                        int num = 0;
                        while (num != 5) {
                            System.out.println("If you want to :\n   Add A contact choose 1 \n   Delete a contact choose 2 \n   search choose 3 \n   Get information about this tree choose 4 \n   Back to menu choose 5");
                            num = input.nextInt();
                            phoneBook.AVLOperations(num);
                        }
                    }
                    case 2 -> {

                    }
                    case 3 -> {

                    }
                }
                }
            }catch (Exception o){
                System.out.println("incorrect path");
            }finally {
                reader.close();
            }
    }
}
