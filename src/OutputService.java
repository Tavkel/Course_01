import java.util.EnumSet;

public class OutputService {
    public static void printEmployeesSetDetailed(Employee[] employees, EnumSet<FieldFlags> flags) {
        if (employees == null || employees.length == 0) {
            System.out.println("Список пуст.");
            return;
        }
        for (Employee employee : employees) {
            printEmployeeDetailed(employee, flags);
        }
    }

    public static void printEmployeeDetailed(Employee employee, EnumSet<FieldFlags> flags){
        System.out.println(employee.toString(flags).trim());
        System.out.println();
    }
}
