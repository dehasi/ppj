1. Say you have a logical 4-element array of data and 2 nodes to process that data with. 
The global view of this logical array is similarly a 4-element array storing the full dataset. 
How is node 0’s local view of that same array likely to be different from the global view, 
assuming that data is distributed as evenly as possible between the two nodes?

[] Node 0’s local view will also store the full 4 elements. By being local to node 0 performance will be improved when accessing that array.
[x] Node 0’s local view will be half the size of the global view, and will only store 2 elements of the logical array. However, which two elements it will store is up to the programmer and is referred to as the data distribution.
[] Node 0’s local view will be half the size of the global view, and will only store 2 elements of the logical array. Node 0 must store the first 2 elements in the global logical array.
[] Node 0 will store zero elements from the global array because its rank is equal to zero.

2. In the first lecture video, we see a global view XG split into two local views, 
each called XL and each stored in a separate node. True or false: Node 0 can directly access node 1’s XL, and vice versa?

False

3. In MPI programs, how would you normally select the logic for different SPMD nodes to run?

[] By looking up the hostname of the current node
[] Through a global negotiation with the other nodes in the SPMD program
[x] By querying for the MPI rank of the current node
[] Based on MPI tags

4. Which of the below communication patterns are considered an example of point-to-point communication?

[x] Send
[] Broadcast
[x] Receive
[] Scatter
[] Gather

5. Given the following three nodes and their send/receive schedules, which will finish its sends/receives first?

P0: Send X to P1; Recv Y from P2;
P1: Recv X from P0; Recv Z from P2;
P2: Send Y to P0; Send Z to P1;

[] P0
[] P1
[FALSE] P2
[FALSE] It can't be known because there's no guarantee of message order
[] It can't be known because this schedule will result in deadlock

6. In the above node schedule (in question five), which operations can be blocking simultaneously? 
Assume there are no network delays.
   
[] Send Y to P0 and Recv Y from P2
[x] Send X to P1 and Send Y to P0
[] Recv X from P0 and Recv Z from P2
[] Recv Y from P2 and Send Z to P1
[x] Recv Z from P2 and Send Y to P0

7. Which of the following are advantages to using ISend and IRecv (and Wait) instead of Send and Recv?

[] They're less likely to produce data races
[] They require writing less code to achieve the same result
[x] They reduce the possibility of deadlock
[x] They can increase parallelism

8. Which of the following statements about non-blocking communication is correct?

[] It’s impossible to have deadlock if one only uses ISend, IRecv, and WaitAny
[] It’s impossible to have deadlock if one only uses ISend, IRecv, and WaitAll
[FALSE] Using the result of an IRecv before it has actually been received implicitly calls Wait on the request returned by IRecv
[x] One can emulate blocking Send and Recv calls by immediately calling any variety of Wait after a call to ISend or IRecv

9. Which of the following is true for MPI’s broadcast and reduce collectives?
   
   1. A broadcast sends data from one node to all nodes, while a reduce sends data from all nodes to one node.
   
   2. Both broadcast and reduce apply some mathematical transformation to their inputs to produce an output.
   
   3. A broadcast can transmit many integers at once, but a reduce can only be applied to one integer at a time.
   
   4. In both, the root parameter specifies a main process that either sends or receives all data.
   
   
[x] 1 and 4
[FALSE] 1, 3, and 4
[] 1 and 3
[] 2 and 3

10. What is one of the benefits of using MPI collectives?

[] The operations that MPI collectives implement can only be implemented in the operating system kernel, and so we must rely on lower levels of the stack for them.
[] They offer constant time cost for all collective operations and processor counts.
[x] MPI collectives offer optimized and succinct implementations of common, distributed operations.