import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FileUtil
{
    public static ArrayList<String> getDatabaseInfo() throws FileNotFoundException
    {
        File file = new File("src/databaseInfo.txt");
        Scanner reader = new Scanner(file);
        ArrayList<String> info = new ArrayList<>();
        while (reader.hasNextLine())
        {
            info.add(reader.nextLine());
        }
        reader.close();
        return info;
    }

    public static void createFile(String name)
    {
        try //creating a file
        {
            File report = new File(name);
            if(report.createNewFile()) {
                System.out.println("File " + report.getName() + " has been created");
            }
            else
            {
                System.out.println("File already exists");
            }
        }
        catch (IOException e)
        {
            System.out.println("Error in creating a file");
            e.getCause();
        }
    }


    public static void writeBookingReport(String name, List<Room> rooms)
    {
        createFile(name);

        try(FileWriter fileWriter = new FileWriter(name)) //writing down everything
        {
            BufferedWriter writer = new BufferedWriter(fileWriter);
            StringBuilder roomAndStays = new StringBuilder();

            rooms.stream()
                    .sorted(Comparator.comparing(r -> r.id))
                    .forEach(r ->
                    {
                        roomAndStays.append(r).append("  Stays: ");
                        try
                        {
                            writer.newLine();
                        } catch (IOException e)
                        {
                            throw new RuntimeException(e);
                        }
                        r.stays.stream()
                                .sorted(Comparator.comparing(Room.Stay::start))
                                .forEach(roomAndStays::append);

                        try
                        {
                            writer.write(String.valueOf(roomAndStays));
                        } catch (IOException e)
                        {
                            throw new RuntimeException(e);
                        }

                        roomAndStays.setLength(0);
                    });
            writer.close();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

//    public static List<Room> createRooms(String fileName) throws FileNotFoundException
//    {
//        List<Room> rooms = new ArrayList<>();
//        File file = new File(fileName);
//        try(Scanner scanner = new Scanner(file))
//        {
//            while (scanner.hasNextLine())
//            {
//                String lineData = scanner.nextLine();
//                var data = lineData.split(",");
//                //don't make it primitive due to possible mistakes in input
//                Integer id;
//                Integer capacity;
//                BigDecimal price;
//                try
//                {
//                    id = Integer.valueOf(data[0]);
//                    capacity = Integer.valueOf(data[1]);
//                    price = BigDecimal.valueOf(Long.parseLong(data[2]));
//
//                    Room room = new Room(id, capacity, price);
//                    rooms.add(room);
//                    System.out.println("Room added "+room.id+ " price " + room.pricePN);
//
//                }
//                catch (NumberFormatException e)
//                {
//                    System.err.println(e.getMessage());
//                }
//
//            }
//        }
//        return rooms;
//    }
}
