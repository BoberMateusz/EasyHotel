import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main
{
    public static ArrayList<Room> createRooms(int amount)
    {
        ArrayList<Room> rooms = new ArrayList<>();
        for(int i=1; i<=amount; i++)
        {
            rooms.add(new Room(i, ThreadLocalRandom.current().nextInt(1, 5), BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(100, 500))
                    .setScale(2, RoundingMode.HALF_UP).doubleValue()));
        }
        return rooms;
    }

    //C:\Users\mateu\Documents\JavaPrograms\EasyHotel\src\data.csv

    public static ArrayList<Room> createRooms(String fileName) throws FileNotFoundException
    {
        ArrayList<Room> rooms = new ArrayList<>();
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
            {
                String lineData = scanner.nextLine();
                var data = lineData.split(",");

                Integer id = null;
                Integer capacity = null;
                Double price = null;
                try
                {
                    id = Integer.valueOf(data[0]);
                    capacity = Integer.valueOf(data[1]);
                    price = Double.valueOf(data[2]);
                }
                catch (NumberFormatException e)
                {
                    e.getCause();
                }

                Room room = new Room(id, capacity, price);
                rooms.add(room);
            }
        }
        return rooms;
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        ArrayList<Room> rooms = createRooms(100); //overloaded method - can randomly create rooms or from a txt file
        System.out.println(rooms);
        System.out.println();

        for(int i=0; i<1000; i++)
        {
            LocalDate date = LocalDate.ofYearDay(2024, ThreadLocalRandom.current().nextInt(1, 359)); //366(leap year) - 7 days stay
            Booking.quickBook(date, date.plusDays(7), 2, rooms);
        }

        FileHandling.writeBookingReport("Report1", rooms);
    }


}