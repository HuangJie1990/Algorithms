//
// Created by B000544D on 2018-04-04.
//

#ifndef CPPALGORITHMS_LIST_H
#define CPPALGORITHMS_LIST_H

#include <algorithm>
#include <utility>

using namespace std;

template<typename Object>
class List {
private:
    struct Node {
        Object data;
        Object *prev;
        Object *next;

        Node(const Object &d = Object{}, Node *p = nullptr, Node *n = nullptr) : data{d}, prev{p}, next{n} {}

        Node(Object &&d, Node *p = nullptr, Node *n = nullptr) : data{d}, prev{p}, next{n} {}
    };

public:
    class const_iterator {
    public:
        const_iterator() : current{nullptr} {}

        const Object &operator*() const {
            return retrieve();
        }

        const_iterator &operator++() {
            current = current->next;
            return *this;
        }

        const_iterator &operator++(int) {
            const_iterator old = *this;
            ++(*this);
            return old;
        }

        bool operator==(const const_iterator &rhs) const {
            return current == rhs.current;
        }

        bool operator!=(const const_iterator &rhs) const {
            return !(*this == rhs);
        }

    protected:
        Node *current;

        Object &retrieve() const {
            return current->data;
        }

        const_iterator(Node *p) : current{p} {}

        friend class List<Object>;
    };

    class iterator : public const_iterator {
    public:
        iterator() {}

        Object &operator*() {
            return const_iterator::retrieve();
        }

        const Object &operator*() const {
            return const_iterator::operator*();
        };

        iterator &operator++() {
            this->current = this->current->next;
            return *this;
        }

        iterator operator++(int) {
            iterator old = *this;
            ++(*this);
            return old;
        }

    protected:
        iterator(Node *p) : const_iterator{p} {}

        friend class List<Object>;
    };

public:
    List() {}

    List(const List &rhs) {}

    ~List() {}

    List &operator=(const List &rhs) {}

    List(List &&rhs) {}

    List &operator=(List &&rhs) {}

    iterator begin() {
        return {head->next};
    }

    const_iterator begin() const {
        return {head->next};
    }

    iterator end() {
        return {tail};
    }

    const_iterator end() const {
        return {tail};
    }

    int size() const { return theSize; }

    bool empty() const { return theSize == 0; }

    void clear() {}

    Object &front() { return *begin(); }

    const Object &front() const { return *begin(); }

    Object &back() { return *--end(); }

    const Object &back() const { return *--end(); }

    void push_front(const Object &x) {}

    void push_front(Object &&x) {}

    void push_back(const Object &x) {}

    void push_back(Object &&x) {}

    void pop_front() {}

    void pop_back() {};

    iterator insert(iterator itr, const Object &x) {}

    iterator insert(iterator itr, Object &&x) {}

    iterator erase(iterator itr) {}

    iterator erase(iterator from, iterator to) {}

private:
    int theSize;
    Node *head;
    Node *tail;

    void init() {}
};


#endif //CPPALGORITHMS_LIST_H
