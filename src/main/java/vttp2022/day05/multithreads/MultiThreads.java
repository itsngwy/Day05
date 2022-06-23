package vttp2022.day05.multithreads;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Done in day07
// Creating MultiThreads
// MultiThreads ThrMain

public class MultiThreads {
    public static void main( String[] args ) {

        // Create a thread pool, can even produce more threads than what we have on our computer
        // We choose how many threads we want to run at the same time. 
        // If we are running just 4 threads but with 10 things to do, 
        // we will wait till a thread is done then it will do the 5th item so on and so forth
        ExecutorService thrPool = Executors.newFixedThreadPool(10);

        // The best way to create a random number
        Random rand = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            String msg = "Thread-%d".formatted(i);
            int sec = rand.nextInt(1, 5);
            System.out.printf("\tDispatching %s to pool\n", msg);
            
            // Creates an object that consists of the task to run
            ThrMain thr = new ThrMain(msg, sec);

            // Submit the task into the pool
            // Esseentially the same but
            // Submit returns a Future object while execute doesnt
            // Submut method can cancel task prematurely and wait for task to finish running use Get()
            // Object thr has a run method 
            thrPool.submit(thr);
            //thrPool.execute(thr);
        }
        System.out.println( "Main thread: Dispatched all thread" );
    }
}
