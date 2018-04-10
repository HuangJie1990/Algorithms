cmake_minimum_required(VERSION 3.9)
project(CPPAlgorithms)

set(CMAKE_CXX_STANDARD 11)

add_executable(CPPAlgorithms main.cpp Vector.cpp Vector.h List.cpp List.h LCP.h LCPLinear.h Radix.h SuffixArray.cpp SuffixArray.h)