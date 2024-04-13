import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class HotelServer implements Runnable
{

    @Override
    public void run()
    {
        //only for now
        List<Room> rooms = RoomRandUtil.quickRoomPreparation();


        try(ServerSocket serverSocket = new ServerSocket(5000))
        {
            System.out.println("Start");
            Socket socket = serverSocket.accept();
            while (true)
            {
                System.out.println("Client connected");
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(
                        socket.getOutputStream(), true);

                String request = input.readLine();

                //2024-12-20 2024-12-34 2
                //2024-11-20 2024-12-34 3


                if (request.equals("exit"))
                {
                    break;
                }
                output.println(inputReader(request, rooms));
                System.out.println("Echo:" + request);
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private String inputReader(String request, List<Room> rooms)
    {

        if(Parser.startsWithNumberRegex(request))
        {
            Parser.DateRangeWithPersons stayAndPeople = Parser.parseDateRange(request);
            return RoomUtil.quickBook(stayAndPeople.startDate(), stayAndPeople.endDate(), stayAndPeople.persons(), rooms).toString();
        }
        else
        {
            if (request.equals("get all rooms"))
            {
                return CommonUtil.getAllRoomsFromDatabase().toString();
            }
            return "Command not known";
        }
    }
}
