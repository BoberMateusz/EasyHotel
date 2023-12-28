import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    public static void writeBookingReport(String name, ArrayList<Room> rooms)
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


        try(FileWriter writer = new FileWriter(name)) //writing down everything
        {
            StringBuilder roomAndStays = new StringBuilder();

            rooms.stream()
                    .sorted(Comparator.comparing(r -> r.id))
                    .forEach(r ->
                    {
                        roomAndStays.append("Room number: ").append(r.id).append("  Stays: ");
                        r.stays.stream()
                                .sorted(Comparator.comparing(Room.Stay::start))
                                .forEach(s -> roomAndStays.append(s.start()).append("-").append(s.end()));

                        try
                        {
                            writer.write(String.valueOf(roomAndStays));
                        } catch (IOException e)
                        {
                            throw new RuntimeException(e);
                        }

                        roomAndStays.setLength(0);
                    });

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        ArrayList<Room> rooms = createRooms("data.csv");
        System.out.println(rooms);
        System.out.println();

        for(int i=0; i<100; i++)
        {
            LocalDate date = LocalDate.ofYearDay(2024, ThreadLocalRandom.current().nextInt(1, 359)); //366(leap year) - 7 days stay
            Booking.quickBook(date, date.plusDays(7), 2, rooms);
        }

        writeBookingReport("Report1", rooms);
    }


}