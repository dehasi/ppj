1. Given the following sequential code fragment and assuming N > 2, 
which of the possible approaches using forall loops (pseudocode introduced in Lecture 3.1)
will produce functionally equivalent values in the arrays x and y? 
Note that a range like "i : [0 : N]" in the forall pseudocode is assumed to be an inclusive range
that includes both the lower bound (0) and the upper bound (N).
  
```
for (i=0; i <= N; i = i + 1) {
  x[i] = x[i] + y[i];
  y[i+1] = w[i] + z[i];
}
```

``` 
forall (i : [0 : N]) {
  x[i] = x[i] + y[i];
}
forall (i : [0 : N]) {
  y[i+1] = w[i] + z[i];
}
``` 
  -- wrong

```
 forall (i : [0 : N]) {
   x[i] = x[i] + y[i];
   y[i+1] = w[i] + z[i];
 }
```

 -- wrong
 
 ``` 
 x[0] = x[0] + y[0];
 forall (i : [0 : N-1]) {
   y[i+1] = w[i] + z[i];
   x[i+1] = x[i+1] + y[i+1];
 }
 y[N+1] = w[N] + z[N];
 ```
 

2. Given the following sequential code fragment:

``` 
c = 0;
for (i = 0; i <= N; i++) {
  c = c + a[i];
}
println("c = " + c);
```
and the following attempt to parallelize the fragment using a forall loop (introduced in Lecture 3.1):

``` 
c = 0;
forall (i : [0 : N]) {
  c = c + a[i];
}
println("c = " + c);
```

Which of the following statements is true, related to the determinism properties introduced in Lecture 2.5?

A. The parallel code exhibits data races and structural determinism, but not functional determinism.

3. Assume that forall is implemented using a finish scope containing a sequential for loop in which each iteration 
is implemented as a parallel async task.

Given the following two versions of code that attempt to parallelize a matrix multiplication computation (introduced in Lecture 3.2). We now use a slightly different notation for forall loops that corresponds to actual code (in the PCDP library) rather than pseudocode. The lower and upper bound parameters for the forall constructs still represent inclusive ranges.

``` 
// Version 1
forall(0, N - 1, 0, N - 1, (i, j) -> {
  C[i][j] = 0;
  for (int k = 0; k < N; k++) {
    C[i][j] += A[i][k] * B[k][j];
  }
});    
```

and 

``` 
// Version 2
forall(0, N - 1, (i) -> {
  forall(0, N - 1, (j) -> {
    C[i][j] = 0;
    for (int k = 0; k < N; k++) {
      C[i][j] += A[i][k] * B[k][j];
    }
  });
});
```

Which of the following statements are true for Versions 1 and 2?

C. Version 1 and Version 2 will perform the same total number of multiply operations (from line 5 in version 1, and line 6 in version 2)

4. Recall that barriers were introduced in Lecture 3.3. True or false, the following code snippet that uses barriers can exhibit a data race?

``` 
sum = 0;
forall (0, 2, (i) -> {
  if (i == 0) {
    sum += i;
  }
  barrier;
  if (i == 1) {
    sum += i;
  }
  barrier;
  if (i == 2) {
    sum += i;
  }
});
```

False

5. Recall that barriers were introduced in Lecture 3.3. Which of the choices is a legal ordering of the print statements in the code snippet below?

``` 
forall (0, 1, (i) -> {
  println(“Hello from iteration “ + i);
  barrier;
  println(“Continuing iteration “ + i);
  barrier;
  println(“Finishing iteration “ + i);
});
```

C.

Hello from iteration 1

Hello from iteration 0

Continuing iteration 1

Continuing iteration 0

Finishing iteration 0

Finishing iteration 1

6. Consider the code below, and recall that barriers were introduced in Lecture 3.3. Which of the choices is a functionally equivalent barrier-based parallel program?
   
``` 
forall (0, 3, (i) -> {
  sum[i] = i;
});
forall (0, 3, (i) -> {
  sum[i] += sum[i + 1];
});
```

A.
``` 
forall (0, 3, (i) -> {
  sum[i] = i;
  barrier;
  sum[i] += sum[i + 1];
});
```

7. What was the primary benefit of using barriers in the one-dimensional iterative averaging example studied in Lecture 3.4?


B. Fewer tasks had to be created when we made use of barriers, leading to lower overhead

8. Which of the following is true about iteration grouping/chunking for parallel loops (as introduced in Lecture 3.5)?

D. Loop chunking does not affect the amount of work performed by the parallel loop and reduces the number of tasks created.

9. Given the sequential code snippet below:

``` 
for (i = 1; i <= 100; i++) {
  a[i] = b[i] + c[i + 10];
}
```

And four parallel versions of the above code snippet, which of the provided parallel versions is correct?

D. All of the parallel snippets are correct.

10. In general, recalling the contents of Lecture 3.5, what is a good heuristic for setting the number of chunks in a forall parallel loop?

A. The number of chunks should be similar to the number of hardware cores in the platform.
    

