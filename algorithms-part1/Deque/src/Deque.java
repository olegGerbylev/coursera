import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node tail;
    private Node head;
    private int size = 0;

    // construct an empty deque
    public Deque() {

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.removeFirst();
        deque.removeFirst();
        deque.addFirst(5);
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("U cant add null element at the collection");
        }
        Node newItem = new Node(item);
        if (isEmpty()) {
            tail = newItem;
            head = newItem;
            size++;
            return;
        }
        newItem.next = tail;
        tail.previous = newItem;
        tail = newItem;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("U cant add null element at the collection");
        }
        Node newItem = new Node(item);
        if (isEmpty()) {
            tail = newItem;
            head = newItem;
            size++;
            return;
        }
        newItem.previous = head;
        head.next = newItem;
        head = newItem;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("collection is empty");
        }
        Item deletedValue = tail.value;
        if (size == 1) {
            tail = null;
            head = null;
            size--;
            return deletedValue;
        }
        Node newTail = tail.next;
        newTail.previous = null;
        tail.next = null;
        tail = newTail;
        size--;
        return deletedValue;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("collection is empty");
        }
        Item deletedValue = head.value;
        if (size == 1) {
            tail = null;
            head = null;
            size--;
            return deletedValue;
        }
        Node newHead = head.previous;
        head.previous = null;
        newHead.next = null;
        head = newHead;
        size--;
        return deletedValue;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        Node next;
        Node previous;
        Item value;

        public Node(Item item) {
            value = item;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node currentElement = tail;

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item returnedValue = currentElement.value;
            currentElement = currentElement.next;
            return returnedValue;
        }
    }

}
