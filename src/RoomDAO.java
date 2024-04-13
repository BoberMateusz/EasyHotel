import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO implements DAO<Room>
{

    private static final String ROOM_ID = "room_id";
    private static final String ROOM_CAPACITY = "room_capacity";
    private static final String ROOM_PRICE = "room_price";
    static final String SELECT_ALL_ROOMS_QUERY = "SELECT %s, %s, %s  FROM rooms".formatted(ROOM_ID, ROOM_CAPACITY, ROOM_PRICE);
    static final String INSERT_INTO_ROOMS_VALUES = "INSERT INTO rooms VALUES (?, ?, ?)";

    @Override
    public List<Room> executeQueryAndMapResult(String sql) throws SQLException
    {
        List<Room> mappedResult = new ArrayList<>();
        try (ResultSet rs = executeQuery(sql))
        {
            while (rs.next())
            {
                mappedResult.add(
                        new Room(rs.getInt(ROOM_ID), rs.getInt(ROOM_CAPACITY), rs.getBigDecimal(ROOM_PRICE)));
            }
        }
        return mappedResult;
    }

    @Override
    public void add(List<Room> rooms, String sql)
    {
        try (Connection conn = createConnection())
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Room room : rooms)
            {
                System.out.println("Create room" + room);
                ps.setInt(1, room.id);
                ps.setInt(2, room.capacity);
                ps.setDouble(3, room.pricePN.doubleValue());
                ps.executeUpdate();
            }
        } catch (Exception e)
        {
            System.err.println(e);
        }
    }
}
