package TADs.Tree;

import TADs.Queue.Exceptions.EmptyQueue;
import TADs.Tree.Exceptions.InvalidKey;
import TADs.LinkedList.LinkedList;
import TADs.Queue.Queue;
public class Node<K,T> implements Comparable<K>{
    private T data;
    private K key;
    private Node<K,T> leftChild;
    private Node<K,T> rightChild;

    public Node(K key, T data) {
        this.data = data;
        this.key = key;
    }

    @Override
    public int compareTo(K otherKey) {
        return (int)(this.key) - (int)(otherKey);
    }

    public K getKey() {
        return this.key;
    }

    public T getData() {
        return this.data;
    }

    public Node<K, T> getLeftChild() {
        return this.leftChild;
    }

    public Node<K,T> getRightChild() {
        return this.rightChild;
    }

    public void setLeftChild(Node<K, T> left) {
        this.leftChild = left;
    }

    public void setRightChild(Node<K,T> right) {
        this.rightChild = right;
    }

    public Node<K, T> findNode(K key) throws InvalidKey{
        Node<K,T> res = null;
        if (this.key == key) {
            res = this;
        } else {
            if (this.compareTo(key) > 0) {
                if (this.leftChild != null) {
                    res = this.leftChild.findNode(key);
                }
            } else {
                if (this.rightChild != null) {
                    res = this.rightChild.findNode(key);
                }
            }
        }
        if (res == null) {
            throw new InvalidKey();
        }
        return res;
    }

    public Node<K, T> findFreeParent(K key) throws InvalidKey {
        Node<K,T> parent = null;
        if (this.compareTo(key) > 0) {
            if (this.leftChild == null) {
                parent = this;
            } else {
                parent = this.leftChild.findFreeParent(key);
            }
        } else if (this.compareTo(key) < 0) {
            if (this.rightChild == null) {
                parent = this;
            } else {
                parent = this.rightChild.findFreeParent(key);
            }
        } else {
            throw new InvalidKey();
        }

        return parent;
    }

    public void inOrder(LinkedList<T> list) {
        if (this.leftChild != null) {
            this.leftChild.inOrder(list);
        }
        list.addLast(this.data);
        if (this.rightChild != null) {
            this.rightChild.inOrder(list);
        }
    }

    public void preOrder(LinkedList<T> list) {
        list.addLast(this.data);
        if (this.leftChild != null) {
            this.leftChild.preOrder(list);
        }
        if (this.rightChild != null) {
            this.rightChild.preOrder(list);
        }
    }

    public void postOrder(LinkedList<T> list) {
        if (this.leftChild != null) {
            this.leftChild.postOrder(list);
        }
        if (this.rightChild != null) {
            this.rightChild.postOrder(list);
        }
        list.addLast(this.data);
    }

    public Node<K,T> findParent(K key) throws InvalidKey {
        Node<K,T> parent = null;
        if (this.leftChild != null && this.leftChild.key == key) {
            parent = this;
        }
        if (this.rightChild != null && this.rightChild.key == key) {
            parent = this;
        }

        if (parent == null) {
            if (this.compareTo(key) > 0) {
                if (this.leftChild != null) {
                    parent = this.leftChild.findParent(key);
                }
            } else {
                if (this.rightChild != null) {
                    parent = this.rightChild.findParent(key);
                }
            }
        }
        if (parent == null) {
            throw new InvalidKey();
        }
        return parent;
    }

    public void getChildList(LinkedList<Node<K,T>> list) {
        list.addLast(this);
        if (this.leftChild != null) {
            this.leftChild.getChildList(list);
        }
        if (this.rightChild != null) {
            this.rightChild.getChildList(list);
        }
    }
    public void levelRouting(Queue<Node<K,T>> queue) throws EmptyQueue {
        Node<K,T> temp = null;
        int originalSize = queue.getSize();
        boolean haySiguienteNivel = false;
        for (int i = 0; i < originalSize; i++) {
            temp = (queue.dequeue()).getValue();
            System.out.print(temp.data + " ");
            if (temp.leftChild != null) {
                queue.enqueue(temp.leftChild);
                haySiguienteNivel = true;
            }
            if (temp.rightChild != null) {
                queue.enqueue(temp.rightChild);
                haySiguienteNivel = true;
            }
            if (i == originalSize - 1) {
                System.out.println();
            }
        }
        if (haySiguienteNivel) {
            temp.levelRouting(queue);
        }


    }
}
