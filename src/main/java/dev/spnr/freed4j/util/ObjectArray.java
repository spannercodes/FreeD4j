package dev.spnr.freed4j.util;

public final class ObjectArray<T> {
    private final T[] array;
    public ObjectArray(int size) {
        //noinspection unchecked
        this.array = (T[]) new Object[size];
    }
    public void set(int index, T value) {
        array[index] = value;
    }
    public T get(int index) {
        return array[index];
    }
}
