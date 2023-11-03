import java.util.Objects;

public class MyStack<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;


    public void push(T value) {
        Node<T> node = new Node<>(value);

        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        size++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);

        if (size == 1) {
            first = null;
            last = null;
        } else if (index == 0) {
            Node<T> nextFirst = first.next;
            nextFirst.prev = null;
            first.next = null;
            first = nextFirst;
        } else if (index == size - 1) {
            Node<T> nextLast = last.prev;
            nextLast.next = null;
            last.prev = null;
            last = nextLast;
        } else {
            Node<T> current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node<T> prevNode = current.prev;
            Node<T> nextNode = current.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            current.prev = current.next = null;
            current.item = null;
        }
        size--;
    }

    public void clear() {
        if (size > 0) {
            Node<T> current = first;
            for (int i = 0; i < size - 1; i++) {
                Node<T> temp = current.next;
                current.item = null;
                current.prev = current.next = null;
                current = temp;
            }
            first = last = null;
            size = 0;
        }
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (size == 0) {
            return null;
        } else {
            return last.item;
        }
    }

    public T pop() {
        T lastElement = peek();
        remove(size - 1);
        return lastElement;
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

class MyStackTest {
    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();
        System.out.println("--- Add new element ---");
        myStack.push("A");
        myStack.push("B");
        myStack.push("C");
        myStack.push("D");
        System.out.println("Stack size is " + myStack.size());
        System.out.println("Stack first element (peek) is " + myStack.peek());
        System.out.println("Stack first element (pop) is " + myStack.pop());
        System.out.println("Stack first element (peek) is " + myStack.peek());
        System.out.println("Stack size is " + myStack.size());
        myStack.clear();
        System.out.println("Stack size after clear " + myStack.size());
        System.out.println("Stack first element (peek) is " + myStack.peek());
//        System.out.println("Stack first element (pop) is " + myStack.pop());
        System.out.println("--- Add new element ---");
        myStack.push("K");
        myStack.push("L");
        myStack.push("M");
        System.out.println("Stack size is " + myStack.size());
        System.out.println("--- Remove one element ---");
        myStack.remove(1);
        System.out.println("Stack size is " + myStack.size());

    }
}
