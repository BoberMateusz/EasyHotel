import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
//        ArrayList<Room> rooms = createRooms(100); //overloaded method - can randomly create rooms or from a txt file
        ArrayList<Room> rooms = Reception.createRooms("data.csv"); //overloaded method - can randomly create rooms or from a txt file
        System.out.println(rooms);
        System.out.println();

        for(int i=0; i<10000; i++)
        {
            LocalDate date = LocalDate.ofYearDay(2024, ThreadLocalRandom.current().nextInt(1, 359)); //366(leap year) - 7 days stay
            Booking.quickBook(date, date.plusDays(7), 2, rooms);
        }

        FileHandling.writeBookingReport("Report1", rooms);
    }



}