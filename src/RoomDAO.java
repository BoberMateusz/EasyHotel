import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO
{

    private static final String ROOM_ID = "room_id";
    private static final String ROOM_CAPACITY = "room_capacity";
    private static final String ROOM_PRICE = "room_price";
    static final String SELECT_ALL_ROOMS_QUERY = "SELECT %s, %s, %s  FROM rooms".formatted(ROOM_ID, ROOM_CAPACITY, ROOM_PRICE);
    static final String INSERT_INTO_ROOMS_VALUES = "INSERT INTO rooms VALUES (?, ?, ?)";

    private Connection createConnection() throws SQLException, FileNotFoundException
    {
        ArrayList<String> info = FileHandling.getDatabaseInfo();

        return  DriverManager.getConnection(
                info.get(0), info.get(1), info.get(2));
    }


    public List<Room> executeQuery(String sql)
    {
        try(Connection conn = createConnection())
        {
            var resultSet = conn.createStatement()
                    .executeQuery(sql);
            return mapResult(resultSet);

        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    private List<Room> mapResult(ResultSet rs) throws SQLException
    {
        List<Room> mappedResult = new ArrayList<>();
        while (rs.next())
        {
            mappedResult.add(
                    new Room(rs.getInt(ROOM_ID), rs.getInt(ROOM_CAPACITY), rs.getBigDecimal(ROOM_PRICE))
            );
        }
        return mappedResult;
    }



    public void addRooms(List<Room> rooms, String sql)
    {
        try (Connection conn = createConnection())
        {
            PreparedStatement ps = conn.prepareStatement(sql);
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


}
