import java.util.Objects;

public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);

        if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            Node<T> nextNode = head.next;
            nextNode.prev = null;
            head = nextNode;
        } else if (index == size - 1) {
            Node<T> prevNode = tail.prev;
            prevNode.next = null;
            tail = prevNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node<T> prevNode = current.prev;
            Node<T> nextNode = current.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            current.prev = current.next = null;
        }
        size--;
    }

    public void clear() {
        Node<T> current = head;
        while (current != null) {
            Node<T> temp = current.next;
            current.item = null;
            current.next = null;
            current.prev = null;
            current = temp;
        }
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(T item) {
            this.item = item;
        }
    }
}


class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        System.out.println("--- Add new element ---");
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        System.out.println("Get 2 node: " + myLinkedList.get(1));
        System.out.println("Get all Linkedlist element:");
        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(myLinkedList.get(i));
        }
        System.out.println("Size of LinkedList = " + myLinkedList.size());
        myLinkedList.clear();
        System.out.println("Size of LinkedList (after clear) = " + myLinkedList.size());
//        System.out.println("Get 2 node: " + myLinkedList.get(1));
        System.out.println("--- Add new element ---");
        myLinkedList.add("k");
        myLinkedList.add("l");
        myLinkedList.add("m");
        System.out.println("Get 2 node: " + myLinkedList.get(1));
        System.out.println("Size of LinkedList = " + myLinkedList.size());
        myLinkedList.remove(0);
        myLinkedList.remove(1);
        System.out.println("Get all Linkedlist element:");
        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(myLinkedList.get(i));
        }
    }
}
