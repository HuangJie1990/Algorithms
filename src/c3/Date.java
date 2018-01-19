package c3;

/**
 * @author
 * @create 2018-01-16-10:07
 **/
//typically unsafe to use equals() withe inheritance(could violate symmetry)
public final class Date implements Comparable<Date> {

    private final int month;
    private final int day;
    private final int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public int compareTo(Date o) {
        return 0;
    }

    @Override
    /*
    自定义类型等价关系标准实现四部曲
    1，是否引用相等
    2，检查是否为空
    3，检查两个对象是否类型相同
    4，比较重要的字段
        如果字段是基元类型，用==
        如果字段是引用类型，使用equal(),并重复上述步骤
        如果字段是数组，比较每一个元素（可用Arrays.equal(a,b)或者Arrays.deepEqual(a,b)替代）
     */
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Date that = (Date) obj;
        if (that.day != this.day) return false;
        if (that.month != this.month) return false;
        if (that.year != this.year) return false;
        return true;
    }
}
