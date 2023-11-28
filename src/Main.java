import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public static void main(String[] args)
    {
        ArrayList<Room> rooms = createRooms(100);
        System.out.println(rooms);
        System.out.println();

        for(int i=0; i<1000; i++)
        {
            LocalDate date = LocalDate.ofYearDay(2024, ThreadLocalRandom.current().nextInt(1, 359)); //366(leap year) - 7 days stay
            Booking.quickBook(date, date.plusDays(7), 2, rooms);
        }
    }


}