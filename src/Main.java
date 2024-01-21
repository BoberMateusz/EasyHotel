import java.io.FileNotFoundException;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {

        //Reception.createRooms(100); //overloaded method - can randomly create rooms or from a txt file
        Reception.createRooms("data.csv"); //overloaded method - can randomly create rooms or from a txt file

        System.out.println();

        for(int i=0; i<10000; i++)
        {
            Booking.bookRandom();
        }

        FileHandling.writeBookingReport("Report1");
    }
}