1. What is a potential downside of the shown getAndAdd() function, which employs optimistic concurrency in the following code block?

```
getAndAdd(int delta) {
  while (true) {
    cur = this.get();
    next = cur + delta;
    if (this.compareAndSet(cur, next) return cur;
  }
}
```

[x] It may perform many unused computations.
[] It requires the use of expensive locks.
[] It may deadlock.
[] It may livelock.

2. Under what circumstances might optimistic concurrency be a good strategy when designing a concurrent algorithm?

[] Computation on the shared object is very expensive compared to the overhead of locks.
[x] You expect very low contention.
[] The optimistically computed operation has side effects.

3. Which variables in an implementation of a sequential queue would need to be handled differently in a concurrent implementation?
   
[] Since not all variables would be part of a data race, only those that would be part of a data race.
[x] Since all variables would be part of a data race, all variables.
[] All variables that are used in an enqueue operation.
[] All variables that are used in a dequeue operation.
[] None of the above.

4. What's the best way to modify TAIL.NEXT in a concurrent implementation of ENQ(X)?
   
[] LOCK(X) { TAIL.NEXT = X }
[] ISOLATED(X) {TAIL.NEXT = X }
[] TAIL.NEXT.COMPAREANDSET(TAIL, X)
[x] TAIL.NEXT.COMPAREANDSET(NULL, X)
[] Both A and B

5. Consider the following timeline. Assuming the enqueue operations are linearizable, 
which of the below are possible results from a sequence of dequeue operations?

[x] a, m, x, b, n, y
[x] m, x, a, n, b, y
[x] m, x, y, a, b, n
[x] x, a, b, m, n, y
[x] x, a, y, m, n, b
[x] x, a, y, b, m, n
res : 0.83 / 1 

6. Consider the scenario where threads T1 and T2 (and no other threads) are attempting to obtain a lock on L1. 
Which of the following are linearizable orderings of statements executed?

[] 1) T1 calls L1.lock(). 2) T2 calls L1.lock(). 3) T2 is unable to obtain lock. 4) T1 successfully obtains lock.
[] 1) T1 calls L1.lock(). 2) T2 calls L1.lock(). 3) T1 is unable to obtain lock. 4) T2 successfully obtains lock.
[x] 1) T1 calls L1.lock(). 2) T2 calls L1.lock(). 3) T1 successfully obtains lock. 4) T2 is unable to obtain lock.
[x] 1) T1 calls L1.lock(). 2) T2 calls L1.lock(). 3) T2 successfully obtains lock. 4) T1 is unable to obtain lock.
[] 1) T1 calls L1.lock(). 2) T2 calls L1.lock(). 3) T2 successfully obtains lock. 4) T1 successfully obtains lock.
[] 1) T1 calls L1.lock(). 2) T2 calls L1.lock(). 3) T1 is unable to obtain lock. 4) T2 is unable to obtain lock.

res: 0.67 / 1 

7. If multiple threads are trying to write a value in the map for the same key using PUTIFABSENT(key, value), 
only one of the threads will succeed.

True

8. Which of the following operations are not linearizable?

[] PUT(key, value)
[x?] PUTIFABSENT(key, value)
[x] PUTALL()
[x] CLEAR()
[] GET (key)

res: 0 / 1 

9. What is a possible downside of using locks to transform a sequential MST algorithm into a concurrent algorithm?
   
[] As our merged tree gets smaller, we have more collisions when using trylock, thus reducing performance.
[] This method may not account for all data races.
[] It results in a deadlock.
[] The merging step could result in two processes attempting to merge the same nodes and thus result in a bug.
[x] There would be more code, which would hurt our brains.

FALSE

10. s it possible for a minimum spanning tree to have the same total weight as its original graph?
Yes

