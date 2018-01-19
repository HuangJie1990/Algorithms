package c3;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author
 * @create 2018-01-16-10:30
 **/
public class EqualTest {
    public static void main(String[] args) {
        Child parent = new Child("Tom", "xi li school");
        Parent parent1 = (Parent) new Child("Tom");
        StdOut.println(parent.equals(parent1));
    }
}

class Parent {
    private String name;

    public Parent(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Parent that = (Parent) obj;
        if (that.name != this.name) return false;
        return true;
    }
}

class Child extends Parent {

    private String school;

    public Child(String name) {
        super(name);
    }

    public Child(String name, String school) {
        this(name);
        this.school = school;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Child that = (Child) obj;
            if (that.school != this.school) return false;
            return true;
        }
        return false;
    }
}
