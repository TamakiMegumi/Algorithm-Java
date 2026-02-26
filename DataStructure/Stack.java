package Algorithm.DataStructure;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A generic Stack implementation using ArrayList as the underlying data structure.
 * Follows LIFO (Last-In-First-Out) principle.
 *
 * @param <T> the type of elements in this stack
 */
public class Stack<T> {
    private ArrayList<T> data;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        data = new ArrayList<>();
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     */
    public int size() {
        return data.size();
    }

    /**
     * Pushes an element onto the top of the stack.
     * Allows null values.
     *
     * @param e the element to be pushed onto the stack
     */
    public void push(T e) {
        data.add(e);
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the top element
     * @throws NoSuchElementException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return data.getLast();
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the top element
     * @throws NoSuchElementException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return data.removeLast();
    }
}
