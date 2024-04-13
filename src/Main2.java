import java.io.FileNotFoundException;

public class Main2
{
    public static void main(String[] args) throws FileNotFoundException, InterruptedException
    {
        RoomDAO roomDAO = new RoomDAO();

        System.out.println(roomDAO.executeQuery(RoomDAO.SELECT_ALL_ROOMS_QUERY));
    }
}



