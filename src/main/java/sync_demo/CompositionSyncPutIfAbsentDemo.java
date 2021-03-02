package sync_demo;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 *  also we can use composition for synchronization and there will be a small penalty
 */
public class CompositionSyncPutIfAbsentDemo<T> implements List<T> {
    private final List<T> list;

    CompositionSyncPutIfAbsentDemo(List<T> list) {
        this.list = list;
    }

    public synchronized boolean putIfAbsent(T v) {
        boolean absent = !list.contains(v);
        if (absent) {
            System.out.println("value will be added: " + v);
            list.add(v);
        } else {
            System.out.println("value will be not added: " + v);
        }
        return absent;
    }

    public static void main(String[] args) {
        String[] users = {"Abe", "Bob", "Cody", "Daniel"};
        CompositionSyncPutIfAbsentDemo<String> arr = new CompositionSyncPutIfAbsentDemo<>(new ArrayList<>());
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Stream.iterate(1, i -> i + 1)
                .limit(10)
                .forEach((i) -> {
                    String currentUser = users[new Random().nextInt(users.length)];
                    executorService.submit(() -> {
                        arr.putIfAbsent(currentUser);
                    });
                });
        executorService.shutdown();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
