//
// Created by B000544D on 2018-04-08.
//

#ifndef CPPALGORITHMS_LCPLINEAR_H
#define CPPALGORITHMS_LCPLINEAR_H

#endif //CPPALGORITHMS_LCPLINEAR_H

#include <string>
#include <vector>

using namespace std;

void createSuffixArray(const string &str, vector<int> &SA, vector<int> &LCP) {

}

void makeSuffixArray(const vector<int> &s, vector<int> &SA, int n, int K) {
    int n0 = (n + 2) / 3;
    int n1 = (n + 1) / 3;
    int n2 = n / 3;
    // 当且仅当n%3==1时，t=1
    int t = n0 - n1;
    int n12 = n1 + n2 + t;

    vector<int> s12(n12 + 3);
    vector<int> SA12(n12 + 3);
    vector<int> s0(n0);
    vector<int> SA0(n0);

    for (int i = 0, j = 0; i < n + t; ++i) {
        if (i % 3 != 0)
            s12[j++] = i;
    }
}

int assignNames(const vector<int> &s,vector<int> &s12,vector<int> &SA12,int n0,int n12,int K){

}

int getIndexIntoS(const vector<int> &SA12, int t, int n0) {
    if (SA12[t] < n0)
        return SA12[t] * 3 + 1;
    else
        return (SA12[t] - n0) * 3 + 2;
}

// 若[a1, a2] <= [b1, b2]则为true
bool leq(int a1, int a2, int b1, int b2) {
    return a1 < b1 || a1 == b1 && a2 <= b2;
}

// 若[a1, a2, a3] <= [b1, b2, b3]则为true
bool leq(int a1, int a2, int a3, int b1, int b2, int b3) {
    return a1 < b1 || a1 == b1 && leq(a2, a3, b2, b3);
}

bool
suffix12IsSmaller(const vector<int> &s, const vector<int> &s12, const vector<int> &SA12, int n0, int i, int j, int t) {

}