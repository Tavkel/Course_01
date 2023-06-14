import java.util.Arrays;

public class Main {
    private static Employee[] employees = MockEmployees.seedEmployees();

    public static void main(String[] args) {
        printAllEmployeesDetailed();

        printSumOfSalariesOfAllEmployees();

        printLowestSalaryEmployees();

        printHighestSalaryEmployees();

        printAverageSalary();

        printAllEmployeesNames();
    }

    //region extracted methods
    private static void printAllEmployeesNames() {
        System.out.println("\nСписок имен сотрудников:");
        printEmployeesSetNames(employees);
    }

    private static void printAverageSalary() {
        System.out.println("\nСредняя зарплата в компании:");
        System.out.println(getAverageSalary(employees));
    }

    private static void printHighestSalaryEmployees() {
        System.out.println("\nHighest salary employee(s):");
        Employee[] highestSalaryEmployees = getEmployeesBySalary(employees, getHighestSalary(employees));
        printEmployeesSetDetailed(highestSalaryEmployees);
    }

    private static void printLowestSalaryEmployees() {
        System.out.println("\nLowest salary employee(s):");
        Employee[] lowestSalaryEmployees = getEmployeesBySalary(employees, getLowestSalary(employees));
        printEmployeesSetDetailed(lowestSalaryEmployees);
    }

    private static void printSumOfSalariesOfAllEmployees() {
        System.out.printf("Сумма затрат компании на зарплаты: %.2f\n", getSumOfSalaries(employees));
    }

    private static void printAllEmployeesDetailed() {
        System.out.println("Список сотрудников:");
        printEmployeesSetDetailed(employees);
    }
    //endregion

    //region methods
    public static void printEmployeesSetDetailed(Employee[] employees) {
        if (employees == null || employees.length == 0) {
            System.out.println("Список пуст.");
            return;
        }
        for (Employee employee : employees) {
            System.out.println(employee);
            System.out.println();
        }
    }

    public static void printEmployeesSetNames(Employee[] employees) {
        if (employees == null || employees.length == 0) {
            System.out.println("Список пуст.");
            return;
        }
        for (Employee employee : employees) {
            System.out.println(employee.getNames());
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
    //endregion
}