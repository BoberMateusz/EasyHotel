import java.util.List;

public class CommonUtil
{
    public static List<Room> getAllRoomsFromDatabase()
    {
        RoomDAO roomDAO = new RoomDAO();
        return roomDAO.executeQuery(RoomDAO.SELECT_ALL_ROOMS_QUERY);
    }






}
