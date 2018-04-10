#include <iostream>
#include "Vector.h"
#include "Radix.h"
#include "SuffixArray.h"

using namespace std;

int main() {
//    int a[]={170,45,75,90,802,24,2,66};
//    int n= sizeof(a)/ sizeof(int);
//    radixSort(a,n);
//    print(a,n);

    char txt[] = "BBBBAAAAAB";
    int n = strlen(txt);
    int *suffixArray = buildSuffixArray(txt, n);
    cout << "Following is suffix array for " << txt << endl;
    print(suffixArray, n);
    return 0;
}