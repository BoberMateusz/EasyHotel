import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface DAO<T>
{
    default Connection createConnection() throws SQLException, FileNotFoundException
    {
        ArrayList<String> info = FileHandling.getDatabaseInfo();

        return  DriverManager.getConnection(
                info.get(0), info.get(1), info.get(2));
    }


    default ResultSet executeQuery(String sql)
    {
        try
        {
            Connection conn = createConnection();
            ResultSet rs = conn.createStatement()
                    .executeQuery(sql);
            conn.close();
            return rs;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    List<Room> executeQueryAndMapResult(String sql) throws SQLException;
    void add(List<T> tList, String sql);

}
