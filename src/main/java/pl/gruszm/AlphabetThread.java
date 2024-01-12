package pl.gruszm;

import java.util.concurrent.Callable;

public class AlphabetThread implements Callable<Void>
{
    private static final int NUMBER_OF_LETTERS = 25;
    private int threadNumber;

    public AlphabetThread(int threadNumber)
    {
        this.threadNumber = threadNumber;
    }

    @Override
    public Void call()
    {
        int counter = 0;
        char letter = 'A';

        while (true)
        {
            System.out.println(letter + String.valueOf(threadNumber));
            letter++;
            counter++;

            if (counter > NUMBER_OF_LETTERS)
            {
                counter = 0;
                letter = 'A';
            }

            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                break;
            }
        }

        return null;
    }
}
