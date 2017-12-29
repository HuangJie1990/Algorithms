package ChapterTwo;

public class IndexMinPQ<Item extends Comparable<Item>> {
    private Item[] items;
    private int n;
    private int[] index;

    public IndexMinPQ(int max) {
        items= (Item[]) new Comparable[max+1];
    }

    public void insert(int k,Item item){}

    public void change(int k,Item item){}

    public boolean contains(int k){
        return false;
    }

    public void delete(int k){}

    public Item min(){
        return items[0];
    }

    public int minIndex(){
        return 1;
    };

    public int delMin(){
        return 1;
    }
    public boolean isEmpty(){
        return true;
    }

    public int size(){
        return n;
    }
}
