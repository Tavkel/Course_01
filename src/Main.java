import java.util.Arrays;
import java.util.EnumSet;

public class Main {
    private static Employee[] employees = MockEmployees.seedEmployees();

    public static void main(String[] args) {
        //region level - easy
        printAllEmployeesDetailed();

        printSumOfSalariesOfAllEmployees();

        printLowestSalaryEmployees();

        printHighestSalaryEmployees();

        printAverageSalary();

        printAllEmployeesNames();
        //endregion

        //region level - medium
        float percent = 10f;
        adjustSalariesByPercentageForAllEmployees(percent);

        int departmentId = 1;
        printEmployeesFromDepartmentDetailed(departmentId);

        printLowestSalaryEmployeesFromDepartment(departmentId);

        printHighestSalaryEmployeesFromDepartment(departmentId);

        printSumOfSalariesOfDepartment(departmentId);

        adjustSalariesByPercentageForDepartment(departmentId, percent);

        printAverageSalaryOfDepartment(departmentId);

        float salary = 80_000f;
        printEmployeesWithSalaryBelow(salary);

        printEmployeesWithSalaryAbove(salary);
        //endregion
    }

    //region extracted methods
    private static void printAverageSalaryOfDepartment(int departmentId) {
        System.out.printf("Средняя зарплата в отделе %d: ", departmentId);
        Employee[] departmentEmployees = getEmployeesByDepartmentId(employees, departmentId);
        System.out.printf("%.2f\n", getAverageSalary(departmentEmployees));
    }

    private static void printEmployeesWithSalaryBelow(float salary) {
        System.out.printf("Список сотрудников с зарплатой ниже %.2f:\n", salary);

        var flags = EnumSet.allOf(FieldFlags.class);
        flags.remove(FieldFlags.DEPARTMENT);

        var toPrint = getEmployeesWithSalaryBelow(employees, salary);

        printEmployeesSetDetailed(toPrint, flags);
    }

    private static void printEmployeesWithSalaryAbove(float salary) {
        System.out.printf("Список сотрудников с зарплатой выше %.2f:\n", salary);

        var flags = EnumSet.allOf(FieldFlags.class);
        flags.remove(FieldFlags.DEPARTMENT);

        var toPrint = getEmployeesWithSalaryAbove(employees, salary);

        printEmployeesSetDetailed(toPrint, flags);
    }

    private static void adjustSalariesByPercentageForDepartment(int departmentId, float percent) {
        Employee[] departmentEmployees = getEmployeesByDepartmentId(employees, departmentId);
        adjustSalariesByPercentageForEmployeesSet(departmentEmployees, percent);
    }

    private static void printSumOfSalariesOfDepartment(int departmentId) {
        System.out.printf("Сумма затрат на зарплаты в отделе %d: ", departmentId);

        Employee[] departmentEmployees = getEmployeesByDepartmentId(employees, departmentId);
        System.out.println(getSumOfSalaries(departmentEmployees));
    }

    private static void printHighestSalaryEmployeesFromDepartment(int departmentId) {
        System.out.printf("Сотрудник(и) с наивысшей зарплатой в отделе %d:\n", departmentId);

        var flags = EnumSet.allOf(FieldFlags.class);
        flags.remove(FieldFlags.DEPARTMENT);

        Employee[] departmentEmployees = getEmployeesByDepartmentId(employees, departmentId);
        Employee[] highestSalaryEmployees = getEmployeesBySalary(departmentEmployees, getHighestSalary(departmentEmployees));
        printEmployeesSetDetailed(highestSalaryEmployees, flags);
    }

    private static void printLowestSalaryEmployeesFromDepartment(int departmentId) {
        System.out.printf("Сотрудник(и) с наименьшей зарплатой в отделе %d:\n", departmentId);

        var flags = EnumSet.allOf(FieldFlags.class);
        flags.remove(FieldFlags.DEPARTMENT);

        Employee[] departmentEmployees = getEmployeesByDepartmentId(employees, departmentId);
        Employee[] lowestSalaryEmployees = getEmployeesBySalary(departmentEmployees, getLowestSalary(departmentEmployees));
        printEmployeesSetDetailed(lowestSalaryEmployees, flags);
    }

    private static void printEmployeesFromDepartmentDetailed(int departmentId){
        System.out.printf("Список сотрудников отдела %d:\n", departmentId);

        var flags = EnumSet.allOf(FieldFlags.class);
        flags.remove(FieldFlags.DEPARTMENT);

        Employee[] departmentEmployees = getEmployeesByDepartmentId(employees, departmentId);
        printEmployeesSetDetailed(departmentEmployees, flags);
    }

    private static void adjustSalariesByPercentageForAllEmployees(float percent) {
        adjustSalariesByPercentageForEmployeesSet(employees, percent);
    }

    private static void printAllEmployeesNames() {
        System.out.println("Список имен сотрудников:");
        printEmployeesSetDetailed(employees, EnumSet.of(FieldFlags.FIO));
    }

    private static void printAverageSalary() {
        System.out.print("Средняя зарплата в компании: ");
        System.out.printf("%.2f\n", getAverageSalary(employees));
    }

    private static void printHighestSalaryEmployees() {
        System.out.println("Highest salary employee(s):");
        Employee[] highestSalaryEmployees = getEmployeesBySalary(employees, getHighestSalary(employees));
        printEmployeesSetDetailed(highestSalaryEmployees, FieldFlags.ALL_FIELDS);
    }

    private static void printLowestSalaryEmployees() {
        System.out.println("Lowest salary employee(s):");
        Employee[] lowestSalaryEmployees = getEmployeesBySalary(employees, getLowestSalary(employees));
        printEmployeesSetDetailed(lowestSalaryEmployees, FieldFlags.ALL_FIELDS);
    }

    private static void printSumOfSalariesOfAllEmployees() {
        System.out.printf("Сумма затрат компании на зарплаты: %.2f\n", getSumOfSalaries(employees));
    }

    private static void printAllEmployeesDetailed() {
        System.out.println("Список сотрудников:");
        printEmployeesSetDetailed(employees, FieldFlags.ALL_FIELDS);
    }
    //endregion

    //region methods
    public static void adjustSalariesByPercentageForEmployeesSet(Employee[] employees, float percent){
        for(Employee employee : employees){
            adjustSalaryByPercentage(employee, percent);
        }
    }

    public static Employee[] getEmployeesByDepartmentId(Employee[] employees, int departmentId){
        return Arrays.stream(employees).filter(e -> e.getDepartment() == departmentId).toArray(Employee[]::new);
    }

    public static void printEmployeesSetDetailed(Employee[] employees, EnumSet<FieldFlags> flags) {
        if (employees == null || employees.length == 0) {
            System.out.println("Список пуст.");
            return;
        }
        for (Employee employee : employees) {
            System.out.println(employee.toString(flags).trim());
            System.out.println();
        }
    }

    public static Employee getEmployeeById(int id) {
        try {
            return Arrays.stream(employees).filter(e -> e.getID() == id).findFirst().get();
        } catch (Exception e) {
            System.out.println("Сотрудник с запрошенным id не обнаружен");
            return null;
        }
    }

    public static float getSumOfSalaries(Employee[] employees) {
        float result = 0.0f;
        for (Employee employee : employees) {
            result += employee.getSalary();
        }
        return result;
    }

    public static Employee[] getEmployeesBySalary(Employee[] employees, float salary) {
        var result = Arrays.stream(employees).filter(e -> e.getSalary() == salary).toArray(Employee[]::new);
        if (result.length == 0) {
            System.out.println("Сотрудников с указанной зароботной платой не существует");
            return null;
        }
        return result;
    }

    public static float getLowestSalary(Employee[] employees) {
        float minimalSalary = employees[0].getSalary();
        for (Employee employee : employees) {
            if (minimalSalary > employee.getSalary()) {
                minimalSalary = employee.getSalary();
            }
        }
        return minimalSalary;
    }

    public static float getHighestSalary(Employee[] employees) {
        float maxSalary = employees[0].getSalary();
        for (Employee employee : employees) {
            if (maxSalary < employee.getSalary()) {
                maxSalary = employee.getSalary();
            }
        }
        return maxSalary;
    }

    public static float getAverageSalary(Employee[] employees) {
        return getSumOfSalaries(employees) / employees.length;
    }

    public static void adjustSalaryByPercentage(Employee employee, float percent){
        employee.setSalary(employee.getSalary() * (1 + percent / 100));
    }

    public static Employee[] getEmployeesWithSalaryBelow(Employee[] employees, float salary){
        return Arrays.stream(employees).filter(s -> s.getSalary() < salary).toArray(Employee[]::new);
    }

    public static Employee[] getEmployeesWithSalaryAbove(Employee[] employees, float salary){
        return Arrays.stream(employees).filter(s -> s.getSalary() >= salary).toArray(Employee[]::new);
    }
    //endregion
}