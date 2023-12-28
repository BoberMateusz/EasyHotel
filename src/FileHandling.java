import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class FileHandling
{
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


    public static void writeBookingReport(String name, ArrayList<Room> rooms)
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
                        roomAndStays.append("Room number: ").append(r.id).append("  Stays: ");
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
}
