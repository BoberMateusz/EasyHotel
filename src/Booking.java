import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Booking
{
    private static boolean availability(Room room, LocalDate start, LocalDate end)
    {
        return room.stays == null || room.stays.parallelStream()
                .allMatch(stay -> !end.isAfter(stay.getStart())
                        || !start.isBefore(stay.getEnd()));
    }

    public static ArrayList<Room> getAvailableRooms(LocalDate start, LocalDate end, ArrayList<Room> rooms, Integer capacity)
    {
        return rooms.parallelStream()
                .filter(room -> Objects.equals(room.capacity, capacity))
                .filter(room -> availability(room, start, end))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Room getCheapestRoom(LocalDate start, LocalDate end, ArrayList<Room> rooms, Integer capacity)
    {
        ArrayList<Room> availableRooms = getAvailableRooms(start, end, rooms, capacity);
        return Collections.min(availableRooms, Comparator.comparing(room -> room.pricePN));
    }


}
