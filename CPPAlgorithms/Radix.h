//
// Created by B000544D on 2018-04-09.
//

#ifndef CPPALGORITHMS_RADIX_H
#define CPPALGORITHMS_RADIX_H

#endif //CPPALGORITHMS_RADIX_H

#include <iostream>

using namespace std;

int getMax(int a[], int n) {
    int max = a[0];
    for (int i = 1; i < n; ++i) {
        if (a[i] > max) max = a[i];
    }
    return max;
}

void countSort(int a[], int n, int exp) {
    int output[n];
    int count[11] = {0};

    for (int j = 0; j < n; ++j) {
        count[a[j] / exp % 10 + 1]++;
    }

    for (int k = 1; k < 11; ++k) {
        count[k] += count[k - 1];
    }

    for (int l = 0; l < n; ++l) {
        output[count[a[l] / exp % 10]++] = a[l];
    }

    for (int m = 0; m < n; ++m) {
        a[m] = output[m];
    }
}

void radixSort(int a[], int n) {
    int max = getMax(a, n);

    for (int exp = 1; max / exp > 0; exp *= 10) {
        countSort(a, n, exp);
    }
}

void print(int a[], int n) {
    for (int i = 0; i < n; ++i) {
        cout << a[i] << endl;
    }
}