package Algorithm.DataStructure;

import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> data;

    public Stack() {
        data = new ArrayList<>();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public void add(T e) {
        data.add(e);
    }

    public T peek() {
        return data.getLast();
    }

    public T poll() {
        return data.removeLast();
    }
}
