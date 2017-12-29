package ChapterOne.BagStackQueue;

public class FixedCapacityStack<Item> {

    private Item[] items;
    private int n;

    public FixedCapacityStack(int cap) {
        items = (Item[]) new Object[cap];
    }

    public void push(Item item) {
        items[n++] = item;
    }

    public Item pop() {
        return items[--n];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }
}
