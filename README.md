# Algorithmic Efficiency Analysis

**Authors:** Carlos J. Chiluisa and Cristian D. Espinosa

## Project Overview
This repository contains two Java implementations (`Benchmarking` and `AnalyzerTwo`) designed to measure and compare the execution time and the total number of operations performed when processing arrays of 100 and 1000 random integers. The core task involves partitioning the data, reducing it through summation, and finding duplicate values using different comparison logic.

## Methodology: How It Works
The process follows a three-step logical pipeline for both algorithms:

1. **Partitioning (Chunking):**
   The main array (size *n*) is divided into smaller 2D sub-arrays (chunks) of 10 elements each.
   > *(Visual concept: Dealing 100 cards into 10 separate piles of 10 cards each).*

2. **Reduction (Summation):**
   The algorithm iterates through each chunk and calculates the total sum of its internal elements. These sums are stored in a new, smaller array.
   > *(Visual concept: Counting the total value of the cards in each pile and writing that single number on a whiteboard).*

3. **Comparison & Duplicate Detection:**
   A nested loop compares each sum against the subsequent sums to find duplicates. 
   > *(Simple explanation: To find matches, we used two different tricks. Algorithm A simply checks if two numbers are exactly the same. Algorithm B takes one number, subtracts the other, and if the result is exactly 0, it means they are repeated values).*
   * **Algorithm A (`Benchmarking.java`)** uses a direct equality check: `sums[i] == sums[j]`.
   * **Algorithm B (`AnalyzerTwo.java`)** uses a subtraction-based check: `(sums[i] - sums[j]) == 0`.
   * A boolean array is used to flag already counted duplicates to avoid redundant operations.

## Performance Results
Based on the execution tests recorded in `image_0d1f08.png`, we obtained the following metrics for both algorithms:   

| Algorithm | Data Size (n) | Total Operations | Execution Time (ms) |
| :--- | :--- | :--- | :--- |
| **A** | 100 | 244 | 0.0149 |
| **A** | 1000 | 4418 | 0.0779 |
| **B** | 100 | 241 | 0.0101 |
| **B** | 1000 | 4464 | 0.0825 |

## Conclusion

### 1. Algorithmic Complexity (Growth Rate)
The most significant finding is the non-linear growth of the operations. When the dataset size increases by a factor of 10 (from 100 to 1000), the number of operations does not merely multiply by 10. Instead, it jumps from ~240 to over 4400 (an 18x increase). This perfectly illustrates a quadratic time complexity $O(n^2)$ behavior, driven entirely by the nested loops (`for i ... for j = i + 1`) required to compare every group sum against the rest.   

### 2. Direct Equality vs. Subtraction
The performance difference between Algorithm A (direct equality) and Algorithm B (subtraction to zero) is practically negligible.
* For 100 data points, **Algorithm B** was marginally faster (0.0101 ms vs 0.0149 ms).   
* For 1000 data points, **Algorithm A** was slightly faster (0.0779 ms vs 0.0825 ms).

These microsecond variations are heavily dependent on the CPU's immediate state and Java's JVM warm-up times. Logically, both a direct comparison and a subtraction are primitive $O(1)$ operations that cost the processor virtually the same amount of effort.
