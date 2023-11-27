import java.time.LocalDate;
import java.util.HashSet;

public class Room
{
    static class Stay
    {
        private LocalDate start;
        private LocalDate end;

        public LocalDate getStart()
        {
            return start;
        }

        public void setStart(LocalDate start)
        {
            this.start = start;
        }

        public LocalDate getEnd()
        {
            return end;
        }

        public void setEnd(LocalDate end)
        {
            this.end = end;
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
    }


    @Override
    public String toString()
    {
        return "Room " + id + ": " + capacity + "p. $" + pricePN;
    }
}



