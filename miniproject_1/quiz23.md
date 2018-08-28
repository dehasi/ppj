1. What advantage do actors have over object-based isolation?

[] Avoids data races
[] Less runtime overhead
[] Increased CPL
[x] Reduces programmer error by making a variable isolated by default

2. How does an actor interact with the local state?

[] Using parallel threads to access the local state variable
[FALSE] Using predefined message methods in that subclass of actor
[FALSE] Using only the methods INCREMENT, DECREMENT, EXIT
[] Using message methods the programmer defines for that subclass of actor


3. In the Actor model, message ordering can be preserved in which of the following cases?

[FALSE] Never
[] Same sender, different receiver
[] Same receiver, different sender
[x] Same sender and receiver



4. How many actors do you need in an actor-based pipeline?

[FALSE] One
[x] One actor per pipeline stage
[] One actor per actor sublcass



5. For generating the primes less than n, how many actors will the Sieve of Eratosthenes use?

[] 1
[] \sqrt{n} 
[] n - 1
[] n
[x] Number of primes less than nn

6. Which of the following statements is/are true regarding the sieve pipeline introduced in the video?
   
Please choose all options that are correct.

[x] The pipeline grows dynamically
[_] Each next actor in the pipeline is created and started by the previous actor
[x] What numbers get filtered in the next stage of the pipeline is arbitrarily determined

7. Which of the following would be good objects to use to implement an unbounded buffer in Java?
   
   Please choose all options that are correct.
   
   
[x] PriorityBlockingQueue
[_] SynchronousQueue
[x] ConcurrentLinkedQueue
[x] ArrayBlockingQueue

so-so
3-4: 0.50 / 1 
3: 0.75 / 1 

8.  Why is it beneficial to model producers, consumers and their unbounded buffer as actors?
    
    Please choose all options that are correct.
    
[x] Actors are more efficient than alternatives like waiting on a while loop condition to evaluate to true.
[x] There is no need for producers to check whether the buffer is full, or for consumers to check whether the buffer is empty.
[x] The consumer can remove items from the buffer at will.
[x] The producer must coordinate with the master actor when it's ready to insert items in the buffer.

so-so
2-3: 0.50 / 1 
1-3:0.75 / 1 
9. P is the number of producer actors and C is the number of consumer actors. 
What can we assume about the size of the buffer (B)?

[FALSE] B < PB<P and B < CB<C FALSE
[] B < PB<P or B < CB<C
[x] P \leq BP≤B
[] C \leq BC≤B   
   
10. The bounded buffer master actor coordinates with the producer actors and consumer actors by...

Please choose all options that are correct.

[x] Requesting data from a producer actor
[] Waiting for a producer actor to send it data
[] Requesting removal of data by consumer actors
[x] Waiting for a consumer actor to tell the buffer that it is ready