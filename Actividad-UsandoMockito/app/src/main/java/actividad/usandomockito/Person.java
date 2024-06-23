package actividad.usandomockito;

import java.time.LocalDate;
public class Person {
    private int id;
    private String first;
    private String last;
    private LocalDate birthdate;
    public Person(int id, String first, String last, LocalDate birthdate) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }
}
