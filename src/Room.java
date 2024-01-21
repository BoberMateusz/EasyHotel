import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

public class Room
{
    record Stay(LocalDate start, LocalDate end) implements Comparable<Stay>{
        @Override
        public String toString()
        {
            return start + " - " + end + " | ";
        }
        @Override
        public int compareTo(Stay o)
        {
            return this.start.compareTo(o.start);
        }
    }

    Integer id;
    Integer capacity;
    BigDecimal pricePN;
    SortedSet<Stay> stays;

    public Room(Integer id, Integer capacity, BigDecimal pricePN)
    {
        this.id = id;
        this.capacity = capacity;
        this.pricePN = pricePN;
        this.stays = new TreeSet<>();
    }


    @Override
    public String toString()
    {
        return "Room " + id + ": " + capacity + "p. $" + pricePN;
    }
}






