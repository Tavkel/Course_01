public class MockEmployees {
    public static Employee[] seedEmployees() {
        Employee[] employees = new Employee[]{
                new Employee("Vorontsova", "Maria", "Ivanovna", 60_000f, 1),
                new Employee("Zazu", "Petr", "Yegorovich", 65_000f, 2),
                new Employee("Letniy", "Boris", "Abramovich", 95_000f, 1),
                new Employee("Elkina", "Irina", "Sergeevna", 75_000f, 1),
                new Employee("Pechkin", "Aleksey", "Alexeevich", 86_000f, 4),

                new Employee("Korobkina", "Liliya", "Petrovna", 100_000f, 3),
                new Employee("Pogodin", "Lev", "Andreevich", 77_000f, 5),
                new Employee("Pirogov", "Artem", "Dmitrievich", 43_000f, 5),
                new Employee("Pushkina", "Aleksandra", "Sergeevna", 43_000f, 3),
                new Employee("Rukin", "Egor", "Ivanovich", 70_000f, 2)
        };
        return employees;
    }
}
