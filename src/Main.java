import java.sql.SQLException;

public class Main
{
    public static void main(String[] args) throws SQLException
    {
        HotelServer hotelServer = new HotelServer();
        Thread serverThread = new Thread(hotelServer);
        serverThread.start();
    }
}