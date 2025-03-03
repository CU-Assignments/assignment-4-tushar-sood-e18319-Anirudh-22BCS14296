import java.util.Scanner;

public class EmployeeManagement {

    static String[] employeeNames = new String[100];
    static int[] employeeIds = new int[100];
    static int employeeCount = 0;

    public static void addEmployee(String name, int id) {
        if (employeeCount < employeeNames.length) {
            employeeNames[employeeCount] = name;
            employeeIds[employeeCount] = id;
            employeeCount++;
        }
    }

    public static void displayEmployees() {
        for (int i = 0; i < employeeCount; i++) {
            System.out.println("ID: " + employeeIds[i] + ", Name: " + employeeNames[i]);
        }
    }

    public static void searchEmployeeById(int id) {
        for (int i = 0; i < employeeCount; i++) {
            if (employeeIds[i] == id) {
                System.out.println("Employee Found: ID: " + id + ", Name: " + employeeNames[i]);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    addEmployee(name, id);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.print("Enter Employee ID to Search: ");
                    int searchId = scanner.nextInt();
                    searchEmployeeById(searchId);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
