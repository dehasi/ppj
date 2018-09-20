1. Using multiple threads per process can help with:

[x] Resource sharing
[x] Performance
[] Responsiveness to JVM delays
[] Scalability
[x] Responsiveness to network delays
[FALSE] Resource availability

2. True or false: on a node with 16 cores, running 16 processes with 1 thread each will always be faster 
than running one process with 16 threads?

FALSE

3. The benefits of using a multithreaded server vs. a single-threaded one are:

[x] Increased throughput of completed requests
[FALSE] Reduced time it takes to service an individual request
[x] Reduced delay between request submission and processing of a request
[] Elimination of data races and contention between requests   
   
4. In the following multithreaded file server pseudo-code:

``` 
listener = new ServerSocket(…);
while(true){
   s = listener.accept(…);                     // A
   t = new Thread( () -> {
      read file request from s.getInputStream; // B
      access the file;                         // C
      send file to s.getOutputStream;          // D
   });
   t.start();
}
```

Which of the operations in the algorithm have to ensure that the concurrent access to memory or resources is handled correctly?

[] None, the implementation does not have to worry about concurrency
[FALSE] All of them: A, B, C and D have to ensure a safe concurrent thread access
[] A and C
[] Only C

5. Which of the following is not a valid MPI mode?

[] Funneled
[] Multiple
[] Single
[FALSE] Serialized

6. I have a program with threads T_0, T_1, T_2 and T_3. I want to make all communications to the MPI go through T_0.
Which of the MPI modes would I want to use?

[?] Funneled
[] Multiple
[] Single
[] Serialized

7. Which of the following statement is false?:

[] Remote actors residing on different nodes cannot exchange object references because they can only communicate through message passing.
[x] All messages sent from an actor must be serialized and be passed by copy in a distributed actor program.
[] Multiple actors in an actor-based program can run on different physical nodes without change to the program logic.
[] In a distributed actor system, actors maintain a logical name that can be remotely referenced by other actors across the node boundaries.

8. Consider a distributed actor-based implementation of the Sieve of Eratosthenes as follows:
   
``` 
SieveActor{
  int local_prime;
  SieveActor next;
  SieveActor(int prime) { local_prime = prime;}
  void process(Message message){
    if  ( 0 != message.val % local_prime) {
      if ( NULL != next ){
        next.send(message);        
      } else {
        //create the next sieve actor at local node
        next = newActor(class:=SieveActor.class, arguments:=[message.val]);
      } 
    }  
  }
}
```

Assuming there are two physical nodes in the network, 
with 32 bit nodeID with integer values 0 and 1, which of the following programs that replaces line 11
can maximize the number of messages crossing the node boundary?

B,C

9. Which of the following statements is true?

[] An advantage of the actor model is the ability of the actor to specify when to receive data.
[] A polling model where the consumer requests items periodically reduces delays in receiving information.
[?] In reactive programming, producers propagate events to subscribers to trigger reactions.
[] In reactive programming, the subscriber has no way to specify how frequently it will receive data. 
 
10. What is the expected output of the following piece of Java-based pseudocode?

``` 
Publisher pub = new Publisher();
Subscriber sub = new Subscriber() {
  int x = 0;
  void onNext(int item) {
    x += item;
    System.out.print(x + “ ,”);
  }
};
pub.subscribe(sub);
pub.submit(3);
pub.submit(30);
```

. 3, 30, -- FALSE