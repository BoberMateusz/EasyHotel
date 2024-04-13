import java.util.List;

public class TestDB
{
    public static void main(String[] args)
    {
        RoomDAO roomDAO = new RoomDAO();

        List<Room> rooms = roomDAO.executeQuery(RoomDAO.SELECT_ALL_ROOMS_QUERY);
        RoomRandUtil.bookRandom(rooms, 50);

        FileUtil.writeBookingReport("Report1", rooms);
    }
}



