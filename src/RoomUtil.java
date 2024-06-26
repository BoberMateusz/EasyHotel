import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RoomUtil
{
    public static boolean availability(@NotNull Room room, LocalDate start, LocalDate end)
    {
        return room.stays == null || room.stays.parallelStream()
                .allMatch(stay -> !end.isAfter(stay.start())
                        ^ !start.isBefore(stay.end()));
    }

    public static ArrayList<Room> getAvailableRooms(LocalDate start, LocalDate end, Integer capacity, List<Room> rooms)
    {
        return rooms.parallelStream()
                .filter(room -> Objects.equals(room.capacity, capacity))
                .filter(room -> availability(room, start, end))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Nullable
    public static Room getCheapestRoom(LocalDate start, LocalDate end, Integer capacity, List<Room> rooms)
    {
        List<Room> availableRooms = getAvailableRooms(start, end, capacity, rooms);
        if(!availableRooms.isEmpty())
            return Collections.min(availableRooms, Comparator.comparing(room -> room.pricePN));
        else
        {
            System.out.println("No more available rooms for this term");
            return null;
        }
    }

    public static void bookRoom(Room room, LocalDate start, LocalDate end)
    {
        if(availability(room, start, end))
        {
            room.stays.add(new Room.Stay(start, end));
            System.out.println("Room " + room.id + " has been booked from " + start + " to " + end);
        }
        else
        {
            System.out.println("Unable to book. Desired term is already booked.");
        }
    }

    public static Boolean quickBook(LocalDate start, LocalDate end, Integer capacity, List<Room> rooms)
    {
        Room foundRoom = getCheapestRoom(start, end, capacity, rooms);
        if(foundRoom!=null)
        {
            bookRoom(foundRoom, start, end);
            return true;
        }
            return false;
    }


}
