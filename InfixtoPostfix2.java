package infixtopostfix2;

class myStack {

    static final int MAX = 10;
    int top;
    char[] a = new char[MAX]; // Maximum size of Stack

    myStack() {
        top = -1;
    }

    boolean push(char x) {
        if (top >= (MAX - 1)) {
            System.out.println("Stack Overflow");
            return false;
        } else {
            a[++top] = x;
            return true;
        }
    }

    int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return Integer.MIN_VALUE;
        } else {
            int x = a[top--];
            return x;
        }
    }

    char peek() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        } else {
            char x = a[top];
            return x;
        }
    }

    int size() {
        return (top + 1);
    }

    boolean isEmpty() {
        return top == -1;
    }

    boolean isFull() {
        return top == MAX - 1;
    }
}

public class InfixtoPostfix2 {

    static boolean checkIfOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }
// Function to compare precedence
// If we return larger value means higher precedence

    static int precedence(char ch) {
        switch (ch) {
            case '+', '-' -> {
                return 1;
            }
            case '*', '/' -> {
                return 2;
            }
            case '^' -> {
                return 3;
            }
        }
        return -1;
    }

    static StringBuilder covertInfixToPostfix(String expr) {
        int i;
        myStack s = new myStack();
        StringBuilder result = new StringBuilder("");
        for (i = 0; i < expr.length(); ++i) {
// Here we are checking is the character we scanned is operand or not
// and this adding to output.
            if (checkIfOperand(expr.charAt(i))) {
                result.append(expr.charAt(i));
            } // Here, if we scan the character ‘(‘, '[', '{' we need to push it to the stack.
            else if (expr.charAt(i) == '(' || expr.charAt(i) == '[' || expr.charAt(i) == '{') {
                s.push(expr.charAt(i));
            } // Here, if we scan character is an ‘)’, we need to pop and print from the stack
            // do this until an ‘(‘ is encountered in the stack.
            else if (expr.charAt(i) == ')' || expr.charAt(i) == ']' || expr.charAt(i) == '}') {
                if (expr.charAt(i) == ')') {
                    while (!s.isEmpty() && s.peek() != '(') {
                        result.append(s.peek());
                        s.pop();
                    }
                    s.pop();
                }
                if (expr.charAt(i) == ']') {
                    while (!s.isEmpty() && s.peek() != '[') {
                        result.append(s.peek());
                        s.pop();
                    }
                    s.pop();
                }
                if (expr.charAt(i) == '}') {
                    while (!s.isEmpty() && s.peek() != '{') {
                        result.append(s.peek());
                        s.pop();
                    }
                    s.pop();
                }
            } else // if an operator
            {
                while (!s.isEmpty() && precedence(expr.charAt(i)) <= precedence(s.peek())) {
                    result.append(s.peek());
                    s.pop();
                }
                s.push(expr.charAt(i));
            }
        }
// Once all initial expression characters are traversed
// adding all left elements from stack to exp
        return result;
    }
// Driver code

    public static void main(String[] args) {
        String infixExpression = "((a+(b*c))-d)";
        System.out.println("The Infix Expression is: " + infixExpression);
        StringBuilder result = covertInfixToPostfix(infixExpression);
        System.out.println("The Postfix of the given Infix Expression is: " + result);
    }
}
