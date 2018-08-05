1. Consider the following parallel pseudo-code that use future tasks, as introduced in Lecture 2.1. Select the statement below which must be true for this code snippet.
   
``` 
finish {
   future A = future { S1; return 1;} ;
   future B = future { S2; int a = A.get(); S3; return 3;} ;
   future C = future { int a = A.get(); S4; int b = B.get(); 
                       S5; return 5;} ; 
}
```
D. S2 can run in parallel with S4.

2. Which of the following dependencies result from the following pseudo-code using futures (as introduced in Lecture 2.1)?

``` 
finish {
   future A = future { int a = 1; S1; S2; return a;} ;
   future B = future { int b = 2; S3; int a = A.get(); S4; 
                       return a+b;} ;
}
```

B. S4 depends on S2 having executed

3. You can use futures in the Java Fork-Join framework (as discussed in Lecture 2.2) by implementing which class?

RecursiveTask

4. How do you retrieve the value from a future in the Java Fork-Join framework (as discussed in Lecture 2.2) ?

C.	By the return value of the join() method of ForkJoinTask.

5. Consider the Pascal’s triangle implementation below, with a triangle containing R rows, with row i containing i entries.
 A triangle with R rows would therefore have $$\frac{R(R + 1)}{2}$$ total entries. 
 Assuming a memoized parallelization strategy like the one below (and discussed in Lecture 2.3), 
 how many futures would be created when computing Pascal’s triangle with R rows ?
 
 D. $$\frac{R(R + 1)}{2} $$
 
6. Based on the same Pascal's triangle implementation above, if memoization and futures are used to compute a Pascal’s triangle of R rows, how many future get() calls must be made to compute the values for the R rows? Keep in mind the special cases of elements on the left and right edges of the triangle. You should ignore the get() calls on the second to last line (line 31) in the code example above; only consider the get()s necessary to compute the actual contents of the triangle.
 ​	
   
B. $\frac{R(R + 1)}{2} $ -- wrong

2 \times R2×R -- wrong

7. Supposed you had a List of Integers in Java: [3, 6, 8, 2, 1, 0]. Which of the stream-based programs below would be equivalent to the provided loop-based Java code?
 (Recall that Java streams were introduced in Lecture 2.4.)

​B.	input.stream().filter(v -> v >= 3);


8. Consider a search algorithm that uses many tasks to examine the search space in parallel. Every task that discovers a target value in the search space will set a global boolean flag to true (it is initialized to false). However, no task will ever read this global flag during execution, hence there will be no early exit of tasks. The flag will only be read after all tasks have terminated.
   
   Such a program will exhibit which of the following? (Recall that data races, functional determinism, and structural determinism were introduced in Lecture 2.5.)

B.	Functional and structural determinism, with a data race

9. Now consider another search algorithm that uses many tasks to examine the search space in parallel. Each task increments a shared integer counter (e.g., count = count +1) when the search is successful.
   
   Such a program will exhibit which of the following?


D. Structural but not functional determinism, with a data race


10. 

C. Benign non-determinism, with a data race.