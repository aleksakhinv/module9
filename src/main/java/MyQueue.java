public class MyQueue<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public void add(T value) {
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

    public void clear() {
        Node<T> current = first;
        Node<T> temp;
        for (int i = 0; i < size; i++) {
            temp = current.next;
            current.item = null;
            current.prev = current.next = null;
            current = temp;
        }
        first = last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (first == null) {
            return null;
        }
        return first.item;
    }

    public T poll() {
        if (first == null) {
            return null;
        } else if (size == 1) {
            T firstElement = first.item;
            first = null;
            last = null;
            size = 0;
            return firstElement;
        } else {
            T firstElement = first.item;
            Node<T> nextFirst = first.next;
            first.next = null;
            nextFirst.prev = null;
            first = nextFirst;
            size--;
            return firstElement;
        }
    }

    static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }

}

class MyQueueTest {
    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueue<>();
        System.out.println("--- Add new element ---");
        myQueue.add("A");
        myQueue.add("B");
        myQueue.add("C");
        System.out.println("Get first element of Queue (peek): " + myQueue.peek());
        System.out.println("Size of Queue is " + myQueue.size());
        myQueue.clear();
//        System.out.println("Peek() when Queue is empty : " + myQueue.peek());
        System.out.println("Queue size after clear " + myQueue.size());
        System.out.println("--- Add new element ---");
        myQueue.add("Q");
        myQueue.add("R");
        myQueue.add("S");
        System.out.println("Get first element of Queue (peek): " + myQueue.peek());
        System.out.println("Get first element of Queue (poll): " + myQueue.poll());
        System.out.println("Get first element of Queue (poll): " + myQueue.poll());
        System.out.println("Size of Queue is " + myQueue.size());
        System.out.println("Get first element of Queue (poll): " + myQueue.poll());
//        System.out.println("Get first element of Queue (peek): " + myQueue.peek());


    }
}
