import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws SQLException
    {

        var roomDao = new RoomDAO();

        roomDao.printRooms();

        List<Room> rooms=new ArrayList<>();


        for (int i = 104; i < 220; i++)
        {
            var room=new Room(i,1+i%2, BigDecimal.valueOf(1+i%2));
            rooms.add(room);
        }

        roomDao.createNewRooms(rooms);
    }






}