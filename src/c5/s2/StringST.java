package c5.s2;

public abstract class StringST<Value> {

    //向表中插入键值对（如果值是null则删除键key）
    abstract void put(String key, Value value);

    //键key所对应的值
    abstract Value get(String key);

    //删除键key（和它的值）
    abstract void delete(String key);

    //表中是否存在保存着key的值
    abstract boolean contains(String key);

    //符号表是否为空
    abstract boolean isEmpty();

    //s的前缀中最长的键
    abstract String longestPrefixOf(String s);

    //所有以s为前缀的键
    abstract Iterable<String> keysWithPrefix(String s);

    //所有和s匹配的键（其中“.”能够匹配任意字符）
    abstract Iterable<String> keysThatMatch(String s);

    //键值对的数量
    abstract int size();

    //符号表中所有的键
    abstract Iterable<String> keys();
}
