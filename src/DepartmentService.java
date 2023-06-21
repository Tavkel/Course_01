public class DepartmentService {
    private final int departmentId;
    private final Employee[] departmentEmployees;

    public DepartmentService(EmployeeBook employeeBook, int departmentId) {
        this.departmentId = departmentId;
        departmentEmployees = employeeBook.getEmployeesByDepartmentId(departmentId);
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Employee[] getDepartmentEmployees(){
        return departmentEmployees;
    }
}
