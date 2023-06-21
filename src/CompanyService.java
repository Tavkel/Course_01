public class CompanyService {
    private final EmployeeBook employeeBook;

    public CompanyService(EmployeeBook employeeBook){

        this.employeeBook = employeeBook;
    }

    public Employee[] getAllEmployees(){
        return employeeBook.getEmployees();
    }

    public Employee getEmployeeById(int id){
        return employeeBook.getEmployeeById(id);
    }

    public void deleteEmployee(int id){
        var employee = employeeBook.getEmployeeById(id);
        employee.setSalary(0f);
        employee.setDepartment(-1);
        employee.setFirstName(null);
        employee.setMiddleName(null);
        employee.setLastName(null);
    }

    public void editEmployee(int id, Employee employee){
        employeeBook.editEmployee(id, employee);
    }

    public int addEmployee(Employee employee){
        int freeId = employeeBook.findFreeId();
        if(freeId == -1){
            return -1;
        }
        else{
            employeeBook.editEmployee(freeId, employee);
            return freeId;
        }
    }
}
