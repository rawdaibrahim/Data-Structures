package DataStructures;

public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Node<E> p) { return numChildren(p) > 0; }
    public boolean isExternal(Node<E> p) { return numChildren(p) == 0; }
    public boolean isRoot(Node<E> p) { return p == root( ); }
    public boolean isEmpty( ) { return size( ) == 0; }
    public int depth(Node<E> p) {
        if (isRoot(p))
        return 0;
        else
        return 1 + depth(parent(p));
    }
    private int heightBad( ) { // works, but quadratic worst-case time
        int h = 0;
        for (Node<E> p : positions())
            if (isExternal(p)) // only consider leaf positions
                h = Math.max(h, depth(p));
        return h;
    }
    public int height(Node<E> p) {
        int h = 0; // base case if p is external
        for (Node<E> c : children(p))
            h = Math.max(h, 1 + height(c));
        return h;
        }

}
