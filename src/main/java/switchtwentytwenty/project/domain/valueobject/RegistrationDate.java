package switchtwentytwenty.project.domain.valueobject;

import switchtwentytwenty.project.util.DateHelper;

import java.util.Calendar;
import java.util.Objects;

public class RegistrationDate implements ValueObject {

    private Calendar date;

    /**
     * Constructor for a RegistrationDate Object, if the argument registrationDate is null the registration date will be the current date
     *
     * @param date a LocalDate object that will represent the date of the registration
     */
    public RegistrationDate(String date) {
        if (isDateNull(date) || isDateBlank(date)) {
            this.date = Calendar.getInstance();
        } else {

            this.date = DateHelper.parseDateToCalendar(date);
        }
    }

    /**
     * Method to determine if a date is valid, i.e. not null
     *
     * @return boolean - returns true if the date is valid, false if it is null
     */
    private boolean isDateNull(String registrationDate) {
        return registrationDate == null;
    }

    private boolean isDateBlank(String registrationDate){ return registrationDate.isEmpty() || registrationDate.trim().isEmpty(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationDate)) return false;
        RegistrationDate that = (RegistrationDate) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return date.get(Calendar.DAY_OF_MONTH)+"/"+ (date.get(Calendar.MONTH)+1)+"/"+ date.get(Calendar.YEAR);
    }
}
