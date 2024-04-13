import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser
{
    public record DateRangeWithPersons(LocalDate startDate, LocalDate endDate, int persons) {
    }

    public static DateRangeWithPersons parseDateRange(String dateRangeString) throws DateTimeParseException {
        String[] parts = dateRangeString.split(" ");
        if (parts.length != 3) {
            throw new DateTimeParseException("Invalid date range format. Expected 'dd.mm.yyyy dd.mm.yyyy xp' (optional)", dateRangeString, 0);
        }

        LocalDate[] localDates = new LocalDate[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (int i = 0; i < 2; i++) {
            localDates[i] = LocalDate.parse(parts[i].trim(), formatter);
        }

        String personsString = parts[2].trim().substring(0, parts[2].length()-1);
        int persons;
        try {
            persons = Integer.parseInt(personsString);
        } catch (NumberFormatException e) {
            throw new DateTimeParseException("Invalid format for persons count. Expected an integer", dateRangeString, dateRangeString.length());
        }

        return new DateRangeWithPersons(localDates[0], localDates[1], persons);
    }


    public static boolean startsWithNumberRegex(String text) {
        return text != null && text.matches("^[0-9].*");
    }
}

