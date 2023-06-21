import java.util.Arrays;

public class EmployeeBook {
    private final Employee[] employees;

    public EmployeeBook() {
        employees = MockEmployees.seedEmployees();
    }

    public Employee[] getEmployees() {
        return Arrays.stream(employees).filter(employee -> employee.getLastName() != null).toArray(Employee[]::new);
    }

    public Employee getEmployeeById(int id) {
        try {
            return employees[id];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Сотрудник с запрошенным id не обнаружен");
            return null;
        }
    }

    public Employee[] getEmployeesByDepartmentId(int departmentId) {
        return Arrays.stream(employees).filter(e -> e.getDepartment() == departmentId).toArray(Employee[]::new);
    }

    public void editEmployee(int id, Employee employee) {
        employees[id].setLastName(employee.getLastName());
        employees[id].setFirstName(employee.getFirstName());
        employees[id].setMiddleName(employee.getMiddleName());
        employees[id].setSalary(employee.getSalary());
        employees[id].setDepartment(employee.getDepartment());
    }

    public int findFreeId() {
        for (var employee : employees) {
            if (employee.getDepartment() == -1) {
                return employee.getID();
            }
        }
        return -1;
    }
}
