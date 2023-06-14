import java.util.EnumSet;

public class Employee {
    public static int idCounter = 0;
    private final int ID;
    private String lastName;
    private String firstName;
    private String middleName;
    private float salary;
    private int department;

    public Employee(String lastName, String firstName, String middleName, float salary, int department) {

        this.ID = idCounter;
        idCounter++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.salary = salary;
        this.department = department;
    }


    //region getters
    public int getID() {
        return ID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public float getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }
    //endregion

    //region setters
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
    //endregion

    //region methods
    @Override
    public String toString() {
        return String.format("Id: %d\n" +
                        "ФИО: %s %s %s\n" +
                        "Заработная плата: %.2f\n" +
                        "Отдел: %d",
                ID, lastName, firstName, middleName, salary, department);
    }

    public String toString(EnumSet<FieldFlags> flags) {
        if (flags == FieldFlags.ALL_FIELDS) {
            return toString();
        }

        String result = "";

        if (flags.contains(FieldFlags.ID)) {
            result += String.format("Id: %d\n", ID);
        }
        if (flags.contains(FieldFlags.FIO)) {
            result += String.format("ФИО: %s %s %s\n", lastName, firstName, middleName);
        }
        if (flags.contains(FieldFlags.SALARY)){
            result += String.format("Заработная плата: %.2f\n", salary);
        }
        if (flags.contains(FieldFlags.DEPARTMENT)){
            result += String.format( "Отдел: %d", department);
        }

        return result;
    }

    public String getNames() {
        return lastName + " " + firstName + " " + middleName;
    }
    //endregion
}

