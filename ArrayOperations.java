package arrayoperations;

public class ArrayOperations {

    private final int[] array;
    private int size;

    public ArrayOperations(int capacity) {
        array = new int[capacity];
        size = 0;
    }
    // Insert an element at the end of the array

    public void insert(int element) {
        if (size >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Array is full. Cannot insert element.");
        }
        array[size++] = element;
    }
    // Remove the first occurrence of the element from the array

    public void remove(int element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new IllegalArgumentException("Element not found in the array.");
        }
        removeAt(index);
    }
    // Insert an element at a specific index

    public void insertAt(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if (size >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Array is full. Cannot insert element.");
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }
    // Remove an element at a specific index

    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }
    // Print the array elements

    public void print() {
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array elements: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    // Find the index of the first occurrence of the element

    private int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1; // Element not found
    }

    public static void main(String[] args) {
        ArrayOperations arrOps = new ArrayOperations(10);
        // Testing insert operations
        try {
            arrOps.insert(10);
            arrOps.insert(20);
            arrOps.insert(30);
            arrOps.print(); // Expected: 10 20 30
            arrOps.insertAt(1, 15);
            arrOps.print(); // Expected: 10 15 20 30
            arrOps.remove(20);
            arrOps.print(); // Expected: 10 15 30
            arrOps.removeAt(0);
            arrOps.print(); // Expected: 15 30
            // Edge cases
            arrOps.insertAt(1, 25);
            arrOps.print(); // Expected: 15 25 30
            arrOps.removeAt(1);
            arrOps.print(); // Expected: 15 30
            arrOps.insert(40);
            arrOps.insert(50);
            arrOps.print(); // Expected: 15 30 40 50
            arrOps.insert(60); // Should throw ArrayIndexOutOfBoundsException
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Testing edge cases and exceptions
        try {
            arrOps.remove(100); // Should throw IllegalArgumentException
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            arrOps.removeAt(5); // Should throw IndexOutOfBoundsException
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            arrOps.insertAt(-1, 5); // Should throw IndexOutOfBoundsException
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            arrOps.removeAt(1); // Should be valid
            arrOps.print(); // Expected: 15 40 50
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
