1. Consider the following example algorithm, taken from the lecture video:

``` 
Thread 1 {
    MYBAL = MYBAL - 100
    FAMILY(W1) = FAMILY(R1) + 100
}

Thread 2 {
    FAMILY(W2) = FAMILY(R2) - 100
    DBAL = DBAL + 100
}
```
We denote the read and write for FAMILY in thread 1 as R1 and W1, and the read and write for FAMILY in thread 2 as R2 and W2.
Which of the following is not a possible ordering of reads and writes for the family’s account balance (FAMILY)?

[] R1, W1, R2, W2
[] R1, R2, W2, W1
[x] R2, W1, R1, W2
[] R1, R2, W1 W2
[] R2, W2, R1, W1

2. Consider the following example algorithm, taken from the lecture video:

``` 
Thread 1 {
    MYBAL = MYBAL - 100
    FAMILY(W1) = FAMILY(R1) + 100
}

Thread 2 {
    FAMILY(W2) = FAMILY(R2) - 100
    DBAL = DBAL + 100
}
```

The beginning balance of the FAMILY account is 1000. 
We denote the read and write for FAMILY in thread 1 as R1 and W1, and the read and write for FAMILY in thread 2 as R2 and W2. 
Which of the following is not a possible value that the family account can end up with?

[x] 800
[] 900
[] 1000
[] 1100

3. Assume we have the doubly-linked list shown below. Using object-based isolation, which sets of object deletions can occur simultaneously?

[x] (A, D)
[x] (A, C, F)
[] (B, C, F)
[x] (B, E)

4. Assume we are using the provided bank transaction code. We have six accounts: A, B, C, D, E, F We make eight transfers, all asynchronously:
   
   1. $100 from A to B
   
   2. $50 from B to C
   
   3. $30 from B to C
   
   4. $20 from B to A
   
   5. $70 from D to A
   
   6. $40 from B to D
   
   7. $30 from E to D
   
   8. $20 from D to F

Each transaction requires one computation step worth of time, but multiple transactions can run in parallel in the same computation step. 
Given enough processors, what is the minimum number of computation steps needed to run these eight transactions without causing a data race?

2 
FALSE

5. Consider the following pseudocode snippet for computing the spanning tree in parallel.

``` 
compute(v) {
  for each neighbor n of v {
    if makeParent(v, n) {
      async compute(n)
    }
  }
}

boolean makeParent(v, c) {
  isolated(c) {
    if c.parent == null {
      doWork(1)
      c.parent = v
      return true
    } else {
      return false
    }
  }
}

finish {
  root.parent = root;
  root.compute();
}
```

For a complete graph with 4 vertices, what is the WORK of this algorithm?

[x] 3
[] 4
[] It depends on the order the node neighbors are selected.
[] It depends on what gets executed in parallel.

6. If we re-run the algorithm from Question 5 on the spanning tree (which is also a graph) that is produced in Question 5, 
what will be the total WORK of the algorithm?

[x] 3
[] 4
[] It depends on the order the node neighbors are selected.
[] It depends on what gets executed in parallel.

7. Consider the following pseudocode snippet:

```
finish {
  AtomicReference r = new AtomicReference()
  async {
     println(r.get())
  }
  forall i in 0..1000 {
    r.compareAndSet(null, i)
  }
}
```
What is most accurate statement regarding the value of r.get() printed by the async?

[] The string is null
[] Any value between 0 and 1000.
[x] The string is null or any value between 0 and 1000.

8. Now, consider the following pseudocode snippet:

``` 
finish {
  AtomicReference r = new AtomicReference()
  forall i in 0..1000 {
    r.compareAndSet(null, i)
  }
  async {
    println(r.get())
  }
}
```

What is most accurate statement regarding the value of r.get() printed by the async?

[] The string null
[x] Any value between 0 and 1000.
[] The string is null or any value between 0 and 1000.

9. Assume we have defined objects x and y. The following pseudo-code uses object isolation on three blocks of code, A, B, and C.

``` 
async {
  isolated (x)  A
}
async {
  isolated (x)  B
}
async {
  isolated (y)  C
}
```

Which code blocks can run simultaneously?

Please choose all options that are correct.

[]	A and B, but not also C
[x]	A and C, but not also B
[x]	B and C, but not also A
[]	A, B, and C

10. Assume we have defined object y. The following pseudo-code uses object isolation on three blocks of code, D, E, and F.

``` 
async {
  isolated (read(y))  D
}
async {
  isolated (read(y))  E
}
async {
  isolated (y)  F
}
``` 

Which code blocks can run simultaneously?

[] D and E, but not also F
[] D and F, but not also E
[] E and F, but not also D
[x] D, E, and F
FALSE

11. Let's say that you have some additional information: blocks D and E actually write y and block F only reads y. 
However, we are leaving the isolated declarations as is. How does this change the previous answer?

[]	No change.
[x]	It reverses all the True/False answers.
[]	The code is erroneous and won't run.
FALSE