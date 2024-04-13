public class TestServer
{
    public static void main(String[] args)
    {
        HotelServer hotelServer = new HotelServer();
        Thread serverThread = new Thread(hotelServer);
        serverThread.start();
    }
}