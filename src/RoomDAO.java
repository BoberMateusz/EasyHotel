import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO
{

    public static final String ROOM_ID = "room_id";
    public static final String ROOM_CAPACITY = "room_capacity";
    public static final String ROOM_PRICE = "room_price";
    public static final String SELECT_ALL_ROOMS_QUERY = "SELECT %s, %s, %s  FROM rooms".formatted(ROOM_ID, ROOM_CAPACITY, ROOM_PRICE);
    public static final String INSERT_INTO_ROOMS_VALUES = "INSERT INTO rooms VALUES (?, ?, ?)";

    private Connection createConnection() throws SQLException, FileNotFoundException
    {
        ArrayList<String> info = FileHandling.getDatabaseInfo();

        return  DriverManager.getConnection(
                info.get(0), info.get(1), info.get(2));
    }

    public void createNewRooms(List<Room> rooms)
    {
        try (Connection conn = createConnection())
        {
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_ROOMS_VALUES);
            for (Room room:rooms) {
                System.out.println("Create room"+room);
                ps.setInt(1, room.id);
                ps.setInt(2, room.capacity);
                ps.setDouble(3, room.pricePN.doubleValue());
                ps.executeUpdate();
            }


        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void printRooms()
    {
        try (Connection connection = createConnection())
        {
            var res=connection.createStatement().executeQuery(SELECT_ALL_ROOMS_QUERY);
            while (res.next())
            {
               System.out.println(res.getString(ROOM_ID) + " " + res.getString(ROOM_CAPACITY)+ " " + res.getString(ROOM_PRICE));
            }
        }
        catch (Exception e) {
            System.err.println(e);

        }


//
//            return connection;
    }

}
