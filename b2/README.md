# Mysterious Road Signs

## Problem
The town of Signfield is on a perfectly straight and infinitely long road running from west to east. Along that road, there is a sequence of S mysterious road signs with numbers on both sides. The i-th sign (numbered in order from west to east) is at a point Di kilometers east of Signfield, and has a number Ai on the west-facing side and a number Bi on the east-facing side.

Nobody in Signfield knows what these signs are trying to say. You think that the numbers on the west sides of the signs are intended for drivers traveling east, and that they represent distances to some particular destination. Similarly, you think that the numbers on the east sides of the signs are for drivers traveling west, and that they represent distances to some particular destination. You suspect that not all of the signs may be consistent with this theory, though.

To start testing your theory, you are interested in finding valid sets of signs that obey the following rules:

The set is a contiguous subsequence of the sequence of all road signs. (The entire sequence also counts as a contiguous subsequence.)
There exist locations M and N kilometers east of Signfield, where M and N are (not necessarily positive and not necessarily distinct) numbers, such that for every sign in that set, at least one of the following is true:
Di + Ai = M.
Di - Bi = N.
What is the largest possible number of signs in a valid set as described above, and how many different valid sets of that size are there?

## Input
The first line of the input gives the number of test cases, T. T test cases follow. Each begins with one line containing one integer S: the number of road signs. Then, S more lines follow. The i-th of these lines represents the i-th sign (in west-to-east order), and contains three integers Di, Ai, and Bi: the sign's distance (in kilometers) east of Signfield, the number on its west side, and the number on its east side.

## Output
For each test case, output one line containing Case #x: y z, where x is the test case number (starting from 1), and y and z are the largest possible number of signs in a valid set and the number of valid sets of that size, as described in the problem statement.

## Limits
1 ≤ T ≤ 60.
1 ≤ Di ≤ 106, for all i.
Di < Dj, for all i < j.
1 ≤ Ai ≤ 106, for all i.
1 ≤ Bi ≤ 106, for all i.
Time limit (for each test set): 10 seconds.
Memory limit: 1GB.

Test set 1 (Visible)
1 ≤ S ≤ 100 for all test cases.

Test set 2 (Hidden)
1 ≤ S ≤ 100 for all but 3 test cases.
S = 105 for 3 test cases.

## Sample

### Input 

``` 
3
1
1 1 1
5
2 7 12
6 3 11
8 10 1
11 11 12
13 9 14
5
1 3 3
2 2 2
3 1 1
4 2 2
5 3 3
```

### Output 

```
Case #1: 1 1
Case #2: 3 2
Case #3: 5 1
```

In Sample Case #1, there is only one sign. If we choose just that sign as our set, there are many possible values of M and N that work — for example:

M = 2 and N = 0
M = 1 and N = 0 (remember that each sign only needs to be correct for one of its values; also, M and N might be in the same place as one or more signs, or Signfield itself)
M = 2 and N = -12345 (N might be west of Signfield)
M = 0 and N = 0 (M and N are not necessarily distinct)
M = 2 and N = 3 (N might be east of M)
So, the set consisting of just that one sign is valid. That is the only set of that length, so the answer is 1 1.

In Sample Case #2, note that the first, second, fourth, and fifth signs would be consistent with M = 9 and N = -1, but they do not form a contiguous subsequence. (The 1 on the back of the third sign cannot be used as if it were on the front.) As it turns out, there is no valid set of four signs. There are two different valid sets of three signs. Note that although there are two different M/N pairs that make the second set of three signs valid, that set counts only once:

the first, second, and third signs, with M = 9 and N = 7
the third, fourth, and fifth signs, with M = 18 and N = -1 or with M = 22 and N = 7
In Sample Case #3, the entire sequence is a valid set, with M = 4 and N = 2.
