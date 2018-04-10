//
// Created by B000544D on 2018-04-02.
//

#ifndef CPPALGORITHMS_VECTOR_H
#define CPPALGORITHMS_VECTOR_H

#include <algorithm>
#include <utility>

using namespace std;

template<typename Object>
class Vector {
public:
    explicit Vector(int iniSize = 0) : theSize{iniSize}, theCapacity{iniSize + SPARE_CAPACITY} {
        // 在堆上分配内存
        objects = new Object[theCapacity];
    }

    // 拷贝构造函数
    Vector(const Vector &rhs) : theSize{rhs.theSize}, theCapacity{rhs.theCapacity}, objects{nullptr} {
        objects = new Object[theCapacity];
        for (int i = 0; i < theSize; ++i) {
            objects[i] = rhs.objects[i];
        }
    }

    Vector &operator=(const Vector &rhs) {
        Vector copy = rhs;
        swap(*this, copy);
        return *this;
    }

    ~Vector() {
        delete[] objects;
    }

    Vector(Vector &&rhs) : theSize{rhs.theSize}, theCapacity{rhs.theCapacity}, objects{rhs.objects} {
        rhs.objects = nullptr;
        rhs.theSize = 0;
        rhs.theCapacity = 0;
    }

    Vector &operator=(Vector &&rhs) {
        swap(theSize, rhs.theSize);
        swap(theCapacity, rhs.theCapacity);
        swap(objects, rhs.objects);

        return *this;
    }

    void resize(int newSize) {
        reverse(newSize * 2);
        theSize = newSize;
    }

    void reverse(int newCapacity) {
        if (newCapacity < theCapacity) return;
        Object *newArray = new Object[newCapacity];
        for (int i = 0; i < theSize; ++i) {
            newArray[i] = move(objects[i]);
        }

        theCapacity = newCapacity;
        swap(objects, newArray);
        delete[] newArray;
    }

    Object &operator[](int index) {
        return objects[index];
    }

    const Object &operator[](int index) const {
        return objects[index];
    }

    bool empty() const {
        return theSize == 0;
    }

    int size() const {
        return theSize;
    }

    int capacity() const {
        return theCapacity;
    }

    void push_back(const Object &x) {
        if (theSize == theCapacity) reverse(2 * theCapacity + 1);
        objects[theSize++] = x;
    }

    void push_back(Object &&x) {
        if (theSize == theCapacity) reverse(2 * theSize + 1);
        objects[theSize++] = move(x);
    }

    void pop_back() {
        --theSize;
    }

    const Object &back() const {
        return objects[theSize - 1];
    }

    typedef Object *iterator;
    typedef Object *const_iterator;

    iterator begin() {
        return &objects[0];
    }

    const_iterator begin() const {
        return &objects[0];
    }

    iterator end() {
        return &objects[theSize];
    }

    const_iterator end() const {
        return &objects[theSize];
    }

    static const int SPARE_CAPACITY = 16;
private:
    int theSize;
    int theCapacity;
    Object *objects;
};


#endif //CPPALGORITHMS_VECTOR_H
