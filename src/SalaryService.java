import java.util.Arrays;

public class SalaryService {
    public static float calculateSumOfSalaries(Employee[] employees){
        float result = 0f;
        for(var employee : employees){
            result += employee.getSalary();
        }
        return result;
    }

    public static float calculateAverageSalary(Employee[] employees){
        float sum = calculateSumOfSalaries(employees);
        return sum / employees.length;
    }

    public static void adjustSalaryByPercent(Employee[] employees, float percent){
        for(var employee : employees){
            adjustSalaryByPercent(employee, percent);
        }
    }
    public static void adjustSalaryByPercent(Employee employee, float percent){
        employee.setSalary(employee.getSalary() * (1 + percent/100));
    }

    public static float findLowestSalary(Employee[] employees){
        float minSalary = employees[0].getSalary();
        for(var employee : employees){
            if (minSalary > employee.getSalary()){
                minSalary = employee.getSalary();
            }
        }
        return minSalary;
    }

    public static float findHighestSalary(Employee[] employees){
        float maxSalary = employees[0].getSalary();
        for(var employee : employees){
            if (maxSalary < employee.getSalary()){
                maxSalary = employee.getSalary();
            }
        }
        return maxSalary;
    }

    public static Employee[] getEmployeesWithSalaryBelow(Employee[] employees, float salary){
        return Arrays.stream(employees).filter(s -> s.getSalary() < salary).toArray(Employee[]::new);
    }

    public static Employee[] getEmployeesWithSalaryAbove(Employee[] employees, float salary){
        return Arrays.stream(employees).filter(s -> s.getSalary() >= salary).toArray(Employee[]::new);
    }
}
