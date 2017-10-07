
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class lab4_130364V {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        CS2022LinkedStack<String> stack = new CS2022LinkedStack<String>();
        CS2022PostfixCalculator cal = new CS2022PostfixCalculator();
        stack.init_stack();

        FileOutputStream out = new FileOutputStream("E:\\Javaexamples\\data structures and algorithms\\StacksandQues\\src\\output.out");
        PrintStream output = new PrintStream(out);
        FileReader in = new FileReader(args[0]);
        BufferedReader line = new BufferedReader(in);
        String str;
        str = line.readLine();
        while (str != null) {
            if (str.equals("pop")) {
                String st = stack.pop();
                System.out.println(st);
                output.println(st);
            } else {
                String[] x = str.split(" ", 2);
                if (x[0].equals("push")) {
                    String[] y = x[1].split(",");
                    for (int i = 0; i < y.length; i++) {
                        stack.push(y[i]);
                    }
                } else if (x[0].equals("calculate")) {
                    float ans = cal.calculate(x[1]);
                    int an = (int) ans;
                    if (ans - an == 0) {
                        System.out.println(an);
                        output.println(an);
                    } else {
                        System.out.println(ans);
                        output.println(ans);
                    }
                }
            }
            str = line.readLine();
        }
        out.close();
        line.close();
    }
}

class Node<E> {

    private E element;
    private Node<E> next;

    public void setElement(E element) {
        this.element = element;
    }

    public E getElement() {
        return element;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getNext() {
        return next;
    }
}

class CS2022LinkedList<E> {

    private Node<E> head;
    private int length;

    public void init_list() {
        Node<E> list = new Node<E>();
        list.setElement(null);
        list.setNext(null);
        setHead(list);
        length = 0;
    }

    public boolean is_empty() {
        if (head.getElement() == null) {
            return true;
        } else {
            return false;
        }
    }

    public Node<E> search(E element) {
        if (head.getElement().equals(element)) {
            return head;
        }
        Node<E> newNode = new Node<E>();
        newNode = getHead();
        for (int i = 1; i < getLength(); i++) {
            newNode = newNode.getNext();
            if (newNode.getElement().equals(element)) {
                return newNode;
            }
        }
        return null;
    }

    public E delete(E element) {
        if (head.getElement() == null) {
            return null;
        } else if (head.getElement().equals(element)) {
            head = head.getNext();
            decrementLength();
            return element;
        } else {
            Node<E> newNode = new Node<E>();
            newNode = getHead();
            for (int i = 1; i < getLength(); i++) {
                if (newNode.getNext().getElement().equals(element)) {
                    newNode.setNext(newNode.getNext().getNext());
                    decrementLength();
                    return element;
                }
                newNode = newNode.getNext();
            }
            return null;
        }
    }

    public E deleteNodeAt(int i) {
        if (getLength() < i || i <= 0) {
            return null;
        } else if (i == 1) {
            E element = head.getElement();
            head = head.getNext();
            decrementLength();
            return element;
        } else {
            E element;
            Node<E> newNode = new Node<E>();
            newNode = getHead();
            for (int j = 1; j < i - 1; j++) {
                newNode = newNode.getNext();
            }
            element = newNode.getNext().getElement();
            newNode.setNext(newNode.getNext().getNext());
            decrementLength();
            return element;
        }

    }

    public boolean insert(E element) {
        Node<E> n = new Node<E>();
        n.setElement(null);
        n.setNext(null);
        Node<E> n2 = new Node<E>();
        n2.setElement(null);
        n2.setNext(null);
        if (getLength() == 0) {
            head.setElement(element);
            head.setNext(n);
            incrementLength();
            return true;
        } else if (getLength() != 0) {
            n.setElement(element);
            Node<E> newNode = new Node<E>();
            newNode = getHead();
            for (int i = 1; i < getLength(); i++) {
                newNode = newNode.getNext();
            }
            n.setNext(n2);
            newNode.setNext(n);
            incrementLength();
            return true;
        } else {
            return false;
        }
    }

    public boolean insertAt(E element, int i) {
        if (i > getLength()) {
            return false;
        } else if (i == 1) {
            Node<E> n = new Node<E>();
            n.setElement(element);
            n.setNext(getHead());
            setHead(n);
            incrementLength();
            return true;
        } else {
            Node<E> n = new Node<E>();
            n.setElement(element);
            Node<E> newNode1 = new Node<E>();
            Node<E> newNode2 = new Node<E>();
            newNode1 = getHead();
            for (int j = 1; j < i - 1; j++) {
                newNode1 = newNode1.getNext();
            }
            newNode2 = newNode1.getNext();
            n.setNext(newNode2);
            newNode1.setNext(n);
            incrementLength();
            return true;
        }
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getHead() {
        return head;
    }

    public void incrementLength() {
        this.length++;
    }

    public void decrementLength() {
        this.length--;
    }

    public int getLength() {
        return length;
    }
}

class CS2022LinkedStack<E> {

    private CS2022LinkedStack<E> elements;
    CS2022LinkedList<E> list = new CS2022LinkedList<E>();

    public void init_stack() {
        list.init_list();
    }

    public boolean is_empty() {
        if (list.getLength() == 0) {
            return true;
        }
        return false;
    }

    public boolean push(E element) {
        return list.insert(element);
    }

    public E pop() {
        return list.deleteNodeAt(list.getLength());
    }
}

class CS2022PostfixCalculator {

    private String input;
    CS2022LinkedStack<String> stack = new CS2022LinkedStack<String>();

    public float calculate(String s) {
        float num1, num2, ans = 0;
        stack.init_stack();
        input = s;
        String[] ar;
        ar = input.split(" ");
        for (int i = 0; i < ar.length; i++) {
            if (ar[i].equals("+") || ar[i].equals("-") || ar[i].equals("*") || ar[i].equals("/")) {;
                num2 = Float.parseFloat(stack.pop());
                num1 = Float.parseFloat(stack.pop());
                if (ar[i].equals("+")) {
                    ans = num1 + num2;
                } else if (ar[i].equals("-")) {
                    ans = num1 - num2;
                } else if (ar[i].equals("*")) {
                    ans = num1 * num2;
                } else if (ar[i].equals("/")) {
                    ans = num1 / num2;
                }
                stack.push(Float.toString(ans));
            } else {
                stack.push(ar[i]);
            }
        }
        return Float.parseFloat(stack.pop());
    }
}
