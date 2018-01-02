package w2;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author
 * @create 2018-01-02-14:24
 **/
public class QueueWithTwoStacks<Item> implements Iterable<Item> {
    private Stack<Item> stack1;
    private Stack<Item> stack2;

    public QueueWithTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return stack1.size() + stack2.size();
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("argument to enqueue is null");
        if (!stack1.isEmpty())
            ;
        else {
            while (!stack2.isEmpty())
                stack1.push(stack2.pop());
        }
        stack1.push(item);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        if (!stack2.isEmpty())
            ;
        else {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Stack<Item> stack3;

        public QueueIterator() {
            if (!stack2.isEmpty())
                ;
            else {
                while (!stack1.isEmpty())
                    stack2.push(stack1.pop());
            }
            stack3 = stack2;
        }

        @Override
        public boolean hasNext() {
            return stack3.size() > 0;
        }

        @Override
        public Item next() {
            return stack3.pop();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is unsupported");
        }
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<String> queue = new QueueWithTwoStacks<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-") && !queue.isEmpty()) {
                StdOut.print(queue.dequeue() + " ");
            } else if (item.equals("show")) {
                for (String s : queue) {
                    StdOut.println(s);
                }
            } else if (item.equals("size")) {
                StdOut.println("(" + queue.size() + " left on stack)");
            } else if (item.equals("exit")) {
                break;
            } else {
                queue.enqueue(item);
            }
        }
        StdOut.println("(" + queue.size() + " left on stack)");
    }
}
