import java.util.Arrays;
import java.util.EnumSet;

public class Main {
    private static final EmployeeBook employeeBook = new EmployeeBook();
    private static final CompanyService companyService = new CompanyService(employeeBook);
    private static DepartmentService departmentService;

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

        int departmentId = 2;
        departmentService = new DepartmentService(employeeBook, departmentId);
        printEmployeesFromDepartmentDetailed();

        printLowestSalaryEmployeesFromDepartment();

        printHighestSalaryEmployeesFromDepartment();

        printSumOfSalariesOfDepartment();

        adjustSalariesByPercentageForDepartment(percent);

        printAverageSalaryOfDepartment();

        float salary = 10_000f;
        printEmployeesWithSalaryBelow(salary);

        printEmployeesWithSalaryAbove(salary);
        //endregion

        //region level - hard
        int id = 1;
        deleteEmployee(id);

        addEmployee();

        editEmployee(id);

        printAllEmployeesDetailed();
        //endregion


    }

    private static void printAllEmployeesDetailed(){
        var employees = companyService.getAllEmployees();

        System.out.println("Список сотрудников компании:");
        OutputService.printEmployeesSetDetailed(employees, FieldFlags.ALL_FIELDS);
    }

    private static void printSumOfSalariesOfAllEmployees(){
        var employees = companyService.getAllEmployees();
        float sum = SalaryService.calculateSumOfSalaries(employees);

        System.out.printf("Сумма затрат компании на зарплаты: %.2f\n", sum);
    }

    private static void printLowestSalaryEmployees(){
        var employees = companyService.getAllEmployees();
        float minSalary = SalaryService.findLowestSalary(employees);
        var lowestSalaryEmployees = Arrays.stream(employees).filter(e -> e.getSalary() == minSalary).toArray(Employee[]::new);

        System.out.printf("Сотрудник(и) с наименьшей зарплатой зарплатой (%.2f):\n", minSalary);
        OutputService.printEmployeesSetDetailed(lowestSalaryEmployees, FieldFlags.ALL_FIELDS);
    }

    private static void printHighestSalaryEmployees(){
        var employees = companyService.getAllEmployees();
        float maxSalary = SalaryService.findHighestSalary(employees);
        var lowestSalaryEmployees = Arrays.stream(employees).filter(e -> e.getSalary() == maxSalary).toArray(Employee[]::new);

        System.out.printf("Сотрудник(и) с наивысшей зарплатой (%.2f):\n", maxSalary);
        OutputService.printEmployeesSetDetailed(lowestSalaryEmployees, FieldFlags.ALL_FIELDS);
    }

    private static void printAverageSalary(){
        var employees = companyService.getAllEmployees();

        System.out.printf("Средняя зарплата в компании: %.2f\n", SalaryService.calculateAverageSalary(employees));
    }

    private static void printAllEmployeesNames(){
        var employees = companyService.getAllEmployees();

        System.out.println("Список сотрудников компании:");
        OutputService.printEmployeesSetDetailed(employees, EnumSet.of(FieldFlags.FIO));
    }

    private static void adjustSalariesByPercentageForAllEmployees(float percent){
        var employees = companyService.getAllEmployees();

        SalaryService.adjustSalaryByPercent(employees, percent);
        System.out.printf("Adjusted salary for all employees by %.2f%%\n", percent);
    }

    private static void printEmployeesFromDepartmentDetailed(){
        System.out.printf("Список сотрудников отдела %d:\n", departmentService.getDepartmentId());
        OutputService.printEmployeesSetDetailed(departmentService.getDepartmentEmployees(), EnumSet.of(FieldFlags.ID, FieldFlags.FIO, FieldFlags.SALARY));
    }

    private static void printLowestSalaryEmployeesFromDepartment(){
        var departmentEmployees = departmentService.getDepartmentEmployees();
        float minSalary = SalaryService.findLowestSalary(departmentEmployees);
        var lowestSalaryEmployees = Arrays.stream(departmentEmployees).filter(e -> e.getSalary() == minSalary).toArray(Employee[]::new);

        System.out.printf("Сотрудник(и) с наименьшей зарплатой в отделе %d:\n", departmentService.getDepartmentId());
        OutputService.printEmployeesSetDetailed(lowestSalaryEmployees, EnumSet.of(FieldFlags.ID, FieldFlags.FIO, FieldFlags.SALARY));
    }

    private static void printHighestSalaryEmployeesFromDepartment(){
        var departmentEmployees = departmentService.getDepartmentEmployees();
        float maxSalary = SalaryService.findHighestSalary(departmentEmployees);
        var lowestSalaryEmployees = Arrays.stream(departmentEmployees).filter(e -> e.getSalary() == maxSalary).toArray(Employee[]::new);

        System.out.printf("Сотрудник(и) с наивысшей зарплатой в отделе %d:\n", departmentService.getDepartmentId());
        OutputService.printEmployeesSetDetailed(lowestSalaryEmployees, EnumSet.of(FieldFlags.ID, FieldFlags.FIO, FieldFlags.SALARY));
    }

    private static void printSumOfSalariesOfDepartment(){
        var departmentEmployees = departmentService.getDepartmentEmployees();
        var sum = SalaryService.calculateSumOfSalaries(departmentEmployees);

        System.out.printf("Сумма затрат на зарплаты в отделе %d: %.2f\n", departmentService.getDepartmentId(), sum);
    }

    private static void adjustSalariesByPercentageForDepartment(float percent){
        var departmentEmployees = departmentService.getDepartmentEmployees();
        SalaryService.adjustSalaryByPercent(departmentEmployees, percent);
        System.out.printf("Adjusted salary for all employees in department %d by %.2f%%\n",departmentService.getDepartmentId(), percent);
    }

    private static void printAverageSalaryOfDepartment(){
        var departmentEmployees = departmentService.getDepartmentEmployees();

        System.out.printf("Средняя зарплата в отделе %d: %.2f\n",departmentService.getDepartmentId(), SalaryService.calculateAverageSalary(departmentEmployees));
    }

    private static void printEmployeesWithSalaryBelow(float salary){
        var employees = companyService.getAllEmployees();
        var result = SalaryService.getEmployeesWithSalaryBelow(employees, salary);

        System.out.printf("Сотрудники с зарплатой менее %.2f:\n", salary);
        OutputService.printEmployeesSetDetailed(result, FieldFlags.ALL_FIELDS);
    }

    private static void printEmployeesWithSalaryAbove(float salary){
        var employees = companyService.getAllEmployees();
        var result = SalaryService.getEmployeesWithSalaryAbove(employees, salary);

        System.out.printf("Сотрудники с зарплатой более %.2f:\n", salary);
        OutputService.printEmployeesSetDetailed(result, FieldFlags.ALL_FIELDS);
    }

    private static void deleteEmployee(int id){
        System.out.println("Deleting employee");
        var employeeToDelete = companyService.getEmployeeById(id);
        OutputService.printEmployeeDetailed(employeeToDelete, FieldFlags.ALL_FIELDS);

        companyService.deleteEmployee(id);
    }

    private static void addEmployee(){
        Employee employee = new Employee("LastName", "FirstName", "MiddleName", 10f, 4);
        var freeId = companyService.addEmployee(employee);

        if(freeId != -1){
            System.out.println("Added employee");
            OutputService.printEmployeeDetailed(companyService.getEmployeeById(freeId), FieldFlags.ALL_FIELDS);
        }
        else {
            System.out.println("Failed to add employee");
            OutputService.printEmployeeDetailed(employee, EnumSet.of(FieldFlags.FIO, FieldFlags.SALARY, FieldFlags.DEPARTMENT));
        }
    }

    private static void editEmployee(int id){
        String newFirstName = "NewFirstName";
        String newMiddleName = "NewMiddleName";
        String newLastName = "NewLastName";
        float newSalary = 90_000f;
        int newDepartment = 3;
        var employee = new Employee(newLastName, newFirstName, newMiddleName, newSalary, newDepartment);
        companyService.editEmployee(id, employee);
    }
}

