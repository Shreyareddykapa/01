
import java.util.ArrayList;

public class DynamicArrayStack<T> {

    private final ArrayList<T> stack;

    public DynamicArrayStack() {
        stack = new ArrayList<>();
    }
    // Push an element onto the stack

    public void push(T element) {
        stack.add(element);
    }
    // Pop an element from the stack

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop element.");
        }
        return stack.remove(stack.size() - 1);
    }
    // Peek the top element of the stack

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek element.");
        }
        return stack.get(stack.size() - 1);
    }
    // Check if the stack is empty

    public boolean isEmpty() {
        return stack.isEmpty();
    }
    // Print all elements in the stack

    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Stack elements (top to bottom):");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }

    public static void main(String[] args) {
        DynamicArrayStack<Integer> stack = new DynamicArrayStack<>();
        try {
            // Testing push operations
            stack.push(10);
            stack.push(20);
            stack.push(30);
            stack.print(); // Expected: 30 20 10
            // Testing peek operation
            System.out.println("Peek: " + stack.peek()); // Expected: 30
            // Testing pop operations
            System.out.println("Pop: " + stack.pop()); // Expected: 30
            stack.print(); // Expected: 20 10
            System.out.println("Pop: " + stack.pop()); // Expected: 20
            stack.print(); // Expected: 10
            // Testing stack underflow
            System.out.println("Pop: " + stack.pop()); // Expected: 10
            stack.print(); // Expected: Stack is empty
            // Testing stack underflow exception
            try {
                stack.pop(); // Should throw IllegalStateException
            } catch (Exception e) {
                System.out.println(e.getMessage()); // Expected: Stack is empty. Cannot pop element.
            }
            // Testing peek on empty stack
            try {
                stack.peek(); // Should throw IllegalStateException
            } catch (Exception e) {
                System.out.println(e.getMessage()); // Expected: Stack is empty. Cannot peek element.
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
