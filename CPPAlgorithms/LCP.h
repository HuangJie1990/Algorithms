//
// Created by B000544D on 2018-04-08.
//

#ifndef CPPALGORITHMS_LCP_H
#define CPPALGORITHMS_LCP_H

#endif //CPPALGORITHMS_LCP_H

#include <string>
#include <vector>
#include <stdexcept>
#include <algorithm>
#include <cstring>

using namespace std;

int computeLCP(const string &s1, const string &s2) {
    int i = 0;
    while (i < s1.length() && i < s2.length() && s1[i] == s2[i])
        ++i;
    return i;
}

void createSuffixArraySlow(const string &str, vector<int> &SA, vector<int> &LCP) {
    if (SA.size() != str.length() || LCP.size() != str.length())
        throw invalid_argument{"Mismatched vector sizes"};

    size_t N = str.length();
    const char *cstr = str.c_str();

    vector<const char *> suffixes{N};

    for (int i = 0; i < N; ++i) {
        suffixes[i] = cstr + i;
    }

    std::sort(begin(suffixes), end(suffixes), [](const char *s1, const char *s2) { return strcmp(s1, s2) < 0; });

    for (int j = 0; j < N; ++j) {
        SA[j] = suffixes[j] - cstr;
    }

    LCP[0] = 0;
    for (int k = 1; k < N; ++k) {
        LCP[k] = computeLCP(suffixes[k - 1], suffixes[k]);
    }
}