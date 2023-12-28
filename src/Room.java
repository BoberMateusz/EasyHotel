import java.time.LocalDate;
import java.util.HashSet;

public class Room
{
    record Stay(LocalDate start, LocalDate end) {
        @Override
        public String toString()
        {
            return start + " - " + end + " | ";
        }
    }

        Integer id;
        Integer capacity;
        Double pricePN;
        HashSet<Stay> stays;

    public Room(Integer id, Integer capacity, Double pricePN)
    {
        this.id = id;
        this.capacity = capacity;
        this.pricePN = pricePN;
        stays = new HashSet<>();
    }


    @Override
    public String toString()
    {
        return "Room " + id + ": " + capacity + "p. $" + pricePN;
    }
}



