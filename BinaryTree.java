package DataStructures;

public interface BinaryTree<E> extends Tree<E> {
    /**
     * Returns the Position of p's left child (or null if no child exists).
     */
    Node<E> left(Node<E> p) throws IllegalArgumentException;

    /**
     * Returns the Position of p's right child (or null if no child exists).
     */
    Node<E> right(Node<E> p) throws IllegalArgumentException;

    /**
     * Returns the Position of p's sibling (or null if no sibling exists).
     */
    Node<E> sibling(Node<E> p) throws IllegalArgumentException;
}
