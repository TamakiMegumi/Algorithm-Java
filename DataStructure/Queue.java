package Algorithm.DataStructure;

import java.util.NoSuchElementException;

/**
 * A generic circular Queue implementation using a fixed-size array.
 * Follows FIFO (First-In-First-Out) principle.
 * Uses a circular array structure to optimize space usage and avoid element shifting.
 *
 * @param <T> the type of elements in this queue
 */
public class Queue<T> {
    private Object[] data;
    private int front;  // Points to the first element
    private int rear;   // Points to the next empty slot after the last element

    /**
     * Constructs an empty queue with default capacity of 16.
     */
    public Queue() {
        this(16);
    }

    /**
     * Constructs an empty queue with specified initial capacity.
     *
     * @param capacity the initial capacity of the queue
     * @throws NegativeArraySizeException if capacity is less than or equal to 0
     */
    public Queue(int capacity) {
        if (capacity <= 0) {
            throw new NegativeArraySizeException("Capacity must be positive");
        }
        data = new Object[capacity];
        front = 0;
        rear = 0;
    }

    /**
     * Checks whether the queue is empty.
     * The queue is empty when front equals rear.
     *
     * @return true if the queue contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * Returns the number of elements in the queue.
     * Uses modulo arithmetic to handle circular array wrapping.
     *
     * @return the size of the queue
     */
    public int size() {
        return (rear - front + data.length) % data.length;
    }

    /**
     * Adds an element to the rear (end) of the queue.
     * Automatically expands the queue capacity when full.
     * Null values are ignored and not added to the queue.
     *
     * @param e the element to be added to the queue
     */
    public void enqueue(T e) {
        if (e == null) {
            return;
        }
        if ((rear + 1) % data.length == front) {
            reserve(2 * data.length + 1);
        }
        data[rear] = e;
        rear = (rear + 1) % data.length;
    }

    /**
     * Resizes the queue to a new capacity and reorganizes elements.
     * Copies all elements from the old array to a new array starting at index 0.
     * Updates front and rear pointers accordingly.
     *
     * @param capacity the new capacity
     * @throws NegativeArraySizeException if capacity is less than or equal to 0
     */
    private void reserve(int capacity) {
        if (capacity <= 0) {
            throw new NegativeArraySizeException("Capacity must be positive");
        }
        Object[] tempData = new Object[capacity];
        int i = front, j = 0;
        while (i != rear) {
            tempData[j] = data[i];
            j++;
            i = (i + 1) % data.length;
        }
        front = 0;
        rear = j;
        data = tempData;
    }

    /**
     * Returns the element at the front (head) of the queue without removing it.
     *
     * @return the front element
     * @throws NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return (T) data[front];
    }

    /**
     * Removes and returns the element at the front (head) of the queue.
     *
     * @return the front element
     * @throws NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T res = (T) data[front];
        front = (front + 1) % data.length;
        return res;
    }
}
