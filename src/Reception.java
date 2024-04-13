import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Reception
{
    public static List<Room> createRooms(int amount)
    {
        List<Room> rooms = Collections.synchronizedList(new ArrayList<>());
        for(int i=1; i<=amount; i++)
        {
            rooms.add(new Room(i, ThreadLocalRandom.current().nextInt(1, 5),
                    BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(100, 500)).setScale(2, RoundingMode.HALF_UP)));
        }
        return rooms;
    }

    public static void createRooms(String fileName) throws FileNotFoundException
    {
        List<Room> rooms = new ArrayList<>();
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
            {
                String lineData = scanner.nextLine();
                var data = lineData.split(",");
                //don't make it primitive due to possible mistakes in input
                Integer id;
                Integer capacity;
                BigDecimal price;
                try
                {
                    id = Integer.valueOf(data[0]);
                    capacity = Integer.valueOf(data[1]);
                    price = BigDecimal.valueOf(Long.parseLong(data[2]));

                    Room room = new Room(id, capacity, price);
                    rooms.add(room);
                    System.out.println("Room added "+room.id+ " price " + room.pricePN);

                }
                catch (NumberFormatException e)
                {
                    System.err.println(e.getMessage());
                }

            }
        }
        RoomsUTIL.setRooms(rooms);
    }





}
