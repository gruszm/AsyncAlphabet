package pl.gruszm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main
{
    private static final int NUMBER_OF_THREADS = 10;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        List<Future<Void>> futureList = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (int i = 0; i < NUMBER_OF_THREADS; i++)
        {
            AlphabetThread thread = new AlphabetThread(i);
            futureList.add(executor.submit(thread));
        }

        int numberOfDeletedThreads = 0;
        while (true)
        {
            int threadToDelete;

            System.out.println("Which thread would you like to delete?");

            threadToDelete = scanner.nextInt();
            threadToDelete %= 10;

            futureList.get(threadToDelete).cancel(true);
            numberOfDeletedThreads++;

            if (numberOfDeletedThreads == NUMBER_OF_THREADS)
            {
                break;
            }
        }

        System.out.println("All threads have been deleted, the program will now end.");

        executor.shutdown();
        scanner.close();
    }
}
