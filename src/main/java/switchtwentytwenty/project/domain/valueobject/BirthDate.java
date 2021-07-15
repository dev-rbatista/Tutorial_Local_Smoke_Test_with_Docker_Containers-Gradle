package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.util.DateHelper;

import java.util.Calendar;
import java.util.Objects;


public class BirthDate implements ValueObject {
    private final Calendar date;

    public BirthDate(String date) {
        this.date = DateHelper.parseDateToCalendar(date);

    }

    @Override
    public String toString() {
        return date.get(Calendar.DAY_OF_MONTH)+"/"+ (date.get(Calendar.MONTH)+1)+"/"+ date.get(Calendar.YEAR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthDate birthDate1 = (BirthDate) o;
        return date.equals(birthDate1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
