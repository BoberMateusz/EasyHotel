import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HotelServer implements Runnable
{

    @Override
    public void run()
    {
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

                String echoString = input.readLine();

                //2024-12-20 2024-12-34 2
                //2024-11-20 2024-12-34 3


                if (echoString.equals("exit"))
                {
                    break;
                }
                output.println("Echo from server" + echoString);
                System.out.println("Echo:" + echoString);
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
