import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main2
{
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, SQLException
    {
        RoomDAO roomDAO = new RoomDAO();

        System.out.println(roomDAO.executeQueryAndMapResult(RoomDAO.SELECT_ALL_ROOMS_QUERY));
    }
}



