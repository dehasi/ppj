package edu.coursera.concurrent;

import edu.rice.pcdp.Actor;

import static edu.rice.pcdp.PCDP.finish;

/**
 * An actor-based implementation of the Sieve of Eratosthenes.
 * <p>
 * TODO Fill in the empty SieveActorActor actor class below and use it from
 * countPrimes to determin the number of primes <= limit.
 */
public final class SieveActor extends Sieve {
    /**
     * {@inheritDoc}
     * <p>
     * TODO Use the SieveActorActor class to calculate the number of primes <=
     * limit in parallel. You might consider how you can model the Sieve of
     * Eratosthenes as a pipeline of actors, each corresponding to a single
     * prime number.
     */
    @Override
    public int countPrimes(final int limit) {
        final SieveActorActor sieveActor = new SieveActor.SieveActorActor(2);

        finish(() -> {
            for (int i = 3; i < limit; i += 2)
                sieveActor.send(i);
            sieveActor.send(0);
        });

        int count = 0;
//        for (SieveActorActor actor = sieveActor; actor != null; actor = actor.nextActor) {
//            count += actor.numLocalPrimes;
//        }
        SieveActorActor actor = sieveActor;
        while (actor != null){
            count += actor.numLocalPrimes;
            actor = actor.nextActor;
        }
        return count;
    }

    /**
     * An actor class that helps implement the Sieve of Eratosthenes in
     * parallel.
     */
    public static final class SieveActorActor extends Actor {
        private static final int MAX_LOCAL_PRIMES = 1_000;
        int numLocalPrimes = 0;
        SieveActorActor nextActor = null;
        private int[] primeNumbers = new int[MAX_LOCAL_PRIMES];

        public SieveActorActor(int localPrime) {
            this.primeNumbers[0] = localPrime;
            this.numLocalPrimes = 1;
        }

        /**
         * Process a single message sent to this actor.
         * <p>
         * TODO complete this method.
         *
         * @param msg Received message
         */
        @Override
        public void process(final Object msg) {
            int candidate = (Integer) msg;
            if (candidate <= 0) {
                if (nextActor != null) nextActor.send(0);
                return;
            }

            if (isLocallyPrime(candidate)) {
                if (numLocalPrimes < MAX_LOCAL_PRIMES) {
                    primeNumbers[numLocalPrimes++] = candidate;
//                    numLocalPrimes += 1;
                } else {
                    if (nextActor == null)
                        nextActor = new SieveActorActor(candidate);
                    nextActor.send(candidate);
                }
            }

        }

        private boolean isLocallyPrime(int candidate) {
            for (int i = 0; i < numLocalPrimes; i++) {
                if ((candidate % primeNumbers[i]) == 0) return false;
            }
            return true;
        }
    }
}
