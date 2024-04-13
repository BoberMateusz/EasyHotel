import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RoomRandUtil
{
    public static List<Room> createRandomRooms(int amount)
    {
        List<Room> rooms = Collections.synchronizedList(new ArrayList<>());
        for(int i=1; i<=amount; i++)
        {
            rooms.add(new Room(i, ThreadLocalRandom.current().nextInt(1, 5),
                    BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(100, 500)).setScale(2, RoundingMode.HALF_UP)));
        }
        return rooms;
    }
    public static void bookRandom(List<Room> rooms)
    {
        LocalDate date = LocalDate.ofYearDay(2024, ThreadLocalRandom.current().nextInt(1, 359)); //366(leap year) - 7 days stay
        RoomUtil.quickBook(date, date.plusDays(ThreadLocalRandom.current().nextInt(1, 14)), ThreadLocalRandom.current().nextInt(1, 5), rooms);
    }
    public static void bookRandom(List<Room> rooms, int percent)
    {
        int amount = 365/7*percent/100*rooms.size();
        for (int i = 0; i < amount; i++)
        {
            bookRandom(rooms);
        }
    }

    public static List<Room> quickRoomPreparation()
    {
        List<Room> rooms = createRandomRooms(100);
        bookRandom(rooms, 50);
        return rooms;
    }
}
