import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class Main2
{
    public static void main(String[] args) throws FileNotFoundException, InterruptedException
    {

        Reception.createRooms(100); //overloaded method - can randomly create rooms or from a txt file
        //Reception.createRooms("data.csv"); //overloaded method - can randomly create rooms or from a txt file

        System.out.println();

        var executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++)
        {
            executorService.submit(new Task());
            executorService.invokeAll(new ArrayList<>());
        }
        

        FileHandling.writeBookingReport("Report1");
    }





    static class Task implements Runnable
    {
        @Override
        public void run()
        {
                for (int i = 0; i < 100; i++)
                {
                    Booking.bookRandom();
                }
                Thread.currentThread().interrupt();
        }
    }
}