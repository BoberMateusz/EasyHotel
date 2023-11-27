import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main
{
    //ThreadLocalRandom.current().nextInt(2, 6)
    public static ArrayList<Room> createRooms(int amount)
    {
        ArrayList<Room> rooms = new ArrayList<>();
        for(int i=1; i<=amount; i++)
        {
            rooms.add(new Room(i, 2, BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(200, 500))
                    .setScale(2, RoundingMode.HALF_UP).doubleValue()));
        }
        return rooms;
    }

    public static void main(String[] args)
    {
        ArrayList<Room> rooms = createRooms(5);
        System.out.println(rooms);

        LocalDate start1 = LocalDate.of(2024, 10, 11);
        LocalDate end1 = LocalDate.of(2024, 10, 13);
        LocalDate start2 = LocalDate.of(2024, 10, 14);
        LocalDate end2 = LocalDate.of(2024, 10, 16);

        System.out.println(Booking.getCheapestRoom(start1, end1, rooms, 2));


    }
}