# Ant Stack

## Problem

Scott has an ant farm containing N ants. Each ant has a certain length and weight.

Today, as a challenge for the ants, Scott has placed some food at the top of the ant farm. The ants will try to reach it by arranging themselves into a vertical stack, with each ant in the stack directly holding the next on its back. In this way, each ant bears the weight of all ants above it. Scott's ants are very strong for their size and are able to carry up to 6 times their own weight. For example, an ant that weights 8 milligrams can carry two other ants weighing 24 milligrams each! Each ant also has a body length; the exact lengths are not important, except that they are all different.

The stack must be linear. Each ant except for the top ant must be directly below exactly one ant, and each ant except for the bottom ant must be directly above exactly one ant.
The lengths of the ants in the stack must be strictly decreasing from the bottom to the top of the stack; this ensures that each new ant that joins the stack will be able to climb up to the top.
For each ant, the sum of the weights of all the ants above it in the stack must be no more than 6 times the weight of that ant.
What is the maximum number of these ants that can form such a stack?

## Input

The first line of the input gives the number of test cases, T. T test cases follow. Each begins with one line with an integer N: the number of ants in the colony. Then, a second line follows containing N integers W1, W2, ..., WN, where Wi is the weight in milligrams of the i-th ant. The ants are listed in strictly increasing order of length. Notice that no actual length values are given; only the order is important.

## Output

For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the maximum number of the given ants that can form a stack that obeys the rules above.

## Limits

7 ≤ T ≤ 100.
Time limit: 15 seconds per test set.
Memory limit: 1GB.

Test set 1 (Visible)
For exactly 6 cases, N = 100; for the other T - 6 cases, 2 ≤ N ≤ 50.
1 ≤ Wi ≤ 1000, for all i.

Test set 2 (Hidden)
For exactly 6 cases, N = 105; for the other T - 6 cases, 2 ≤ N ≤ 500.
1 ≤ Wi ≤ 109, for all i.

## Sample

### Input 

```
3
2
9 1
3
8 4 100
9
10 10 10 10 10 10 10 10 100
```

### Output 

```
Case #1: 1
Case #2: 3
Case #3: 8
```

In Sample Case #1, there are two ants. The first weighs 9 mg; the second weighs 1 mg, and is longer than the first ant. The first ant is strong enough to hold the second ant (since it can hold up to 9 × 6 mg), but it cannot, because the second ant is longer. The second ant is not strong enough to hold the first ant (since it can only hold up to 1 × 6 mg, which is less than 9 mg). So it is only possible to make a "stack" of one of the two ants.

In Sample Case #2, it is possible for all three ants to form a stack, with the third holding up the second, which holds up the first.

In Sample Case #3, the optimal solution has the ninth ant on the bottom, and then seven of the other ants above it.
