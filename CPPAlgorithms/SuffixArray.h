//
// Created by B000544D on 2018-04-10.
//

#ifndef CPPALGORITHMS_SUFFIXARRAY_H
#define CPPALGORITHMS_SUFFIXARRAY_H

#endif //CPPALGORITHMS_SUFFIXARRAY_H

#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

struct suffix {
    int index;
    int rank[2];
};

int cmp(struct suffix a, struct suffix b) {
    return (a.rank[0] == b.rank[0]) ? (a.rank[1] < b.rank[1] ? 1 : 0) : (a.rank[0] < b.rank[0] ? 1 : 0);
}

int charAt(char *txt, int i) {
    int length = strlen(txt);
    if (i < length) return txt[i];
    return txt[i - length];
}

int *buildSuffixArray(char *txt, int n) {
    struct suffix suffixes[n];

    for (int i = 0; i < n; ++i) {
        suffixes[i].index = i;
        suffixes[i].rank[0] = charAt(txt, i);
        suffixes[i].rank[1] = charAt(txt, i + 1);
    }

    sort(suffixes, suffixes + n, cmp);

    int index[n];

    for (int k = 4; k < 2 * n; k *= 2) {
        int rank = 0;
        int pre_rank = suffixes[0].rank[0];
        suffixes[0].rank[0] = rank;
        index[suffixes[0].index] = 0;

        for (int i = 1; i < n; ++i) {
            if (suffixes[i].rank[0] == pre_rank && suffixes[i].rank[1] == suffixes[i - 1].rank[1]) {
                pre_rank = suffixes[i].rank[0];
                suffixes[i].rank[0] = rank;
            } else {
                pre_rank = suffixes[i].rank[0];
                suffixes[i].rank[0] = ++rank;
            }
            index[suffixes[i].index] = i;
        }

        for (int i = 0; i < n; ++i) {
            int nextIndex = suffixes[i].index + k / 2;
            suffixes[i].rank[1] = (nextIndex < n) ? suffixes[index[nextIndex]].rank[0] : suffixes[index[nextIndex -
                                                                                                        n]].rank[0];
        }

        sort(suffixes, suffixes + n, cmp);
    }

    int *suffixArray = new int[n];
    for (int i = 0; i < n; ++i) {
        suffixArray[i] = suffixes[i].index;
    }
    return suffixArray;
}
