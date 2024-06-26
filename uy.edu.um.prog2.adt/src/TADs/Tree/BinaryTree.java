package TADs.Tree;

import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.LinkedList.LinkedList;
import TADs.Queue.Exceptions.EmptyQueue;
import TADs.Queue.Queue;
import TADs.Tree.Exceptions.EmptyTree;
import TADs.Tree.Exceptions.InvalidKey;




public class BinaryTree<K,T> implements MyTree<K,T> {
    private Node<K,T> root;

    public Node<K,T> getRoot(){
        return this.root;
    }

    @Override
    public T search(K key) throws EmptyTree, InvalidKey {
        Node<K,T> res;
        if (this.root != null) {
            res = this.root.findNode(key);
        } else {
            throw new EmptyTree();
        }
        return res.getData();
    }

    @Override
    public Node<K,T> searchNode(K key) throws EmptyTree, InvalidKey {
        Node<K,T> res;
        if (this.root != null) {
            res = this.root.findNode(key);
        } else {
            throw new EmptyTree();
        }
        return res;
    }

    @Override
    public void add(K key, T data) throws InvalidKey {
        Node<K,T> add = new Node<>(key, data);
        Node<K,T> parent;
        if (this.root != null) {
            parent = this.root.findFreeParent(key);
            if (parent.compareTo(key) > 0) {
                parent.setLeftChild(add);
            } else {
                parent.setRightChild(add);
            }
        } else {
            this.root = add;
        }
    }

    @Override
    public void addNode(Node<K, T> add) throws InvalidKey{
        if (this.root != null) {
            Node<K,T> parent = this.root.findFreeParent(add.getKey());
            if (parent.compareTo(add.getKey()) > 0) {
                parent.setLeftChild(add);
            } else {
                parent.setRightChild(add);
            }
        } else {
            this.root = add;
        }
    }

    @Override
    public void delete(K key) throws InvalidKey, EmptyTree, EmptyList, InvalidIndex {
        if (this.root != null) {
            Node<K,T> del = this.searchNode(key);
            Node<K,T> parent = this.root.findParent(key);
            if (parent.getLeftChild() == del) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            LinkedList<Node<K,T>> list = new LinkedList<Node<K,T>>();
            del.getChildList(list);
            list.removeValue(list.get(0));
            for (int i = 0; i < list.size(); i ++) {
                list.get(i).setRightChild(null);
                list.get(i).setLeftChild(null);
                this.addNode(list.get(i));
            }
        } else {
            throw new EmptyTree();
        }
    }

    public void inOrder() throws EmptyTree, EmptyList, InvalidIndex {
        LinkedList<T> list = new LinkedList<T>();
        if (this.root != null) {
            this.root.inOrder(list);
        } else {
            throw new EmptyTree();
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == this.root.getData()) {
                System.out.println(list.get(i) + " [R]");
            } else {
                System.out.println(list.get(i));
            }
        }
    }
    public void preOrder() throws EmptyTree, EmptyList, InvalidIndex {
        LinkedList<T> list = new LinkedList<T>();
        if (this.root != null) {
            this.root.preOrder(list);
        } else {
            throw new EmptyTree();
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == this.root.getData()) {
                System.out.println(list.get(i) + " [R]");
            } else {
                System.out.println(list.get(i));
            }
        }
    }
    public void postOrder() throws EmptyTree, EmptyList, InvalidIndex {
        LinkedList<T> list = new LinkedList<T>();
        if (this.root != null) {
            this.root.postOrder(list);
        } else {
            throw new EmptyTree();
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == this.root.getData()) {
                System.out.println(list.get(i) + " [R]");
            } else {
                System.out.println(list.get(i));
            }
        }
    }

    public Node<K,T> getMax() throws EmptyTree, EmptyList, InvalidIndex {
        Node<K,T> maximum;
        if (this.root != null) {
            LinkedList<Node<K,T>> list = new LinkedList<Node<K,T>>();
            this.root.getChildList(list);
            maximum = list.get(0);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).compareTo(maximum.getKey()) > 0) {
                    maximum = list.get(i);
                }
            }
        } else {
            throw new EmptyTree();
        }
        return maximum;
    }

    public Node<K,T> getMin() throws EmptyTree, EmptyList, InvalidIndex {
        Node<K,T> minimum;
        if (this.root != null) {
            LinkedList<Node<K,T>> list = new LinkedList<Node<K,T>>();
            this.root.getChildList(list);
            minimum = list.get(0);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).compareTo(minimum.getKey()) < 0) {
                    minimum = list.get(i);
                }
            }
        } else {
            throw new EmptyTree();
        }
        return minimum;
    }

    public void levelRouting() throws EmptyTree, EmptyQueue {
        if (this.root != null) {
            Queue<Node<K,T>> queue = new Queue<Node<K, T>>();
            queue.enqueue(this.root);
            this.root.levelRouting(queue);
        } else {
            throw new EmptyTree();
        }
    }

    public int size() {
        return size(this.root);
    }

    private int size(Node<K, T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getLeftChild()) + size(node.getRightChild());
    }

    public boolean isEmpty() {
        if (this.root != null) {
            return false;
        } else {
            return true;
        }
    }

}
