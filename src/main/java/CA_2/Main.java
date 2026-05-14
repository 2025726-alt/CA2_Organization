// Main program - Department Store System
package CA_2;

import java.io.*;
import java.util.*;

/**
 * Main Application - Department Store Organization System
 * Demonstrates sorting, searching, binary tree hierarchy, and user input validation
 */
public class Main {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<Department> departments = new ArrayList<>();
    private static BinaryTree employeeTree = new BinaryTree();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  DEPARTMENT STORE ORGANIZATION SYSTEM");
        System.out.println("=============================================");
        
        // Initialize departments
        initializeDepartments();
        
        // Load data from file
        loadDataFromFile("applicants.txt");
        
        boolean running = true;
        
        while (running) {
            MenuEnum.displayMenu();
            System.out.print("Enter your choice (1-" + MenuEnum.values().length + "): ");
            
            int choice = getValidIntInput(1, MenuEnum.values().length);
            MenuEnum selectedOption = MenuEnum.values()[choice - 1];
            
            switch (selectedOption) {
                case SORT:
                    sortAndDisplay();
                    break;
                case SEARCH:
                    searchEmployee();
                    break;
                case ADD_RECORDS:
                    addNewEmployee();
                    break;
                case CREATE_BINARY_TREE:
                    createBinaryTree();
                    break;
                case DISPLAY_TREE:
                    displayBinaryTree();
                    break;
                case EXIT:
                    System.out.println("Thank you for using the system. Goodbye!");
                    running = false;
                    break;
            }
        }
        scanner.close();
    }
    
    /**
     * Initialize all departments for the department store
     */
    private static void initializeDepartments() {
        departments.add(new Department("Customer Service", Department.DepartmentType.CUSTOMER_SERVICE));
        departments.add(new Department("Sales", Department.DepartmentType.SALES));
        departments.add(new Department("Human Resources", Department.DepartmentType.HR));
        departments.add(new Department("Finance", Department.DepartmentType.FINANCE));
        departments.add(new Department("IT Support", Department.DepartmentType.IT_SUPPORT));
        departments.add(new Department("Operations", Department.DepartmentType.OPERATIONS));
        departments.add(new Department("Marketing", Department.DepartmentType.MARKETING));
        departments.add(new Department("Accounting", Department.DepartmentType.ACCOUNTING));
    }
    
    /**
     * Load employee data from CSV file
     */
    private static void loadDataFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);
            
            // Read header line
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }
            
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                
                if (data.length >= 9) {
                    String firstName = data[0];
                    String lastName = data[1];
                    String gender = data[2];
                    String email = data[3];
                    double salary = Double.parseDouble(data[4]);
                    String deptName = data[5];
                    String position = data[6];
                    String jobTitle = data[7];
                    String company = data[8];
                    
                    // Find or create department
                    Department department = findDepartmentByName(deptName);
                    if (department == null && !departments.isEmpty()) {
                        department = departments.get(0); // Default to first department
                    }
                    
                    // Determine employee position
                    Employee.EmployeePosition empPosition = getPositionFromString(position);
                    
                    Employee employee = new Employee(firstName, lastName, email, salary, 
                                                     empPosition, department, jobTitle, company);
                    employees.add(employee);
                }
            }
            fileScanner.close();
            System.out.println("File read successfully! Loaded " + employees.size() + " employees.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File '" + filename + "' not found!");
            System.out.println("Please ensure applicants.txt exists in the project root directory.");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static Department findDepartmentByName(String name) {
        for (Department dept : departments) {
            if (dept.getDepartmentName().equalsIgnoreCase(name)) {
                return dept;
            }
        }
        return null;
    }
    
    private static Employee.EmployeePosition getPositionFromString(String position) {
        if (position == null || position.isEmpty()) return Employee.EmployeePosition.MIDDLE;
        
        String pos = position.toLowerCase();
        if (pos.contains("intern")) return Employee.EmployeePosition.INTERN;
        if (pos.contains("junior")) return Employee.EmployeePosition.JUNIOR;
        if (pos.contains("middle")) return Employee.EmployeePosition.MIDDLE;
        if (pos.contains("senior")) return Employee.EmployeePosition.SENIOR;
        if (pos.contains("contract")) return Employee.EmployeePosition.CONTRACT;
        return Employee.EmployeePosition.MIDDLE;
    }
    
    /**
     * SORTING SECTION - Using Recursive Merge Sort
     */
    private static void sortAndDisplay() {
        if (employees.isEmpty()) {
            System.out.println("No employees to sort!");
            return;
        }
        
        System.out.println("\n[SORT] Using Recursive Merge Sort Algorithm");
        System.out.println("Justification: Merge Sort provides O(n log n) guaranteed performance,");
        System.out.println("is stable, recursive by nature, and excellent for sorting employee records.");
        
        // Create a copy to sort
        ArrayList<Employee> sortedEmployees = new ArrayList<>(employees);
        SortAlgorithms.mergeSort(sortedEmployees, 0, sortedEmployees.size() - 1);
        
        // Display first 20
        System.out.println("\nFirst 20 employees after sorting alphabetically:");
        int count = Math.min(20, sortedEmployees.size());
        for (int i = 0; i < count; i++) {
            System.out.println((i+1) + ". " + sortedEmployees.get(i).getFullName());
        }
        
        // Store sorted list back
        employees = sortedEmployees;
    }
    
    /**
     * SEARCH SECTION - Using Recursive Binary Search
     */
    private static void searchEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees to search!");
            return;
        }
        
        System.out.println("\n[SEARCH] Using Recursive Binary Search Algorithm");
        System.out.println("Justification: Binary Search provides O(log n) time complexity,");
        System.out.println("perfect for sorted lists, and minimizes comparisons dramatically.");
        
        // Ensure list is sorted first
        SortAlgorithms.mergeSort(employees, 0, employees.size() - 1);
        
        System.out.print("Enter the full name to search (e.g., 'John Smith'): ");
        String searchName = scanner.nextLine();
        
        Employee found = SearchAlgorithms.searchEmployee(employees, searchName);
        
        if (found != null) {
            SearchAlgorithms.displayEmployeeDetails(found);
        } else {
            System.out.println("\nEmployee '" + searchName + "' not found in the system.");
        }
    }
    
    /**
     * ADD NEW RECORDS - With validation
     */
    private static void addNewEmployee() {
        System.out.println("\n[ADD NEW EMPLOYEE]");
        System.out.println("Please enter the following information:");
        
        // Get first name
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        while (firstName.trim().isEmpty()) {
            System.out.print("First Name cannot be empty. Please enter: ");
            firstName = scanner.nextLine();
        }
        
        // Get last name
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        while (lastName.trim().isEmpty()) {
            System.out.print("Last Name cannot be empty. Please enter: ");
            lastName = scanner.nextLine();
        }
        
        // Get email
        System.out.print("Email: ");
        String email = scanner.nextLine();
        while (email.trim().isEmpty() || !email.contains("@")) {
            if (!email.contains("@")) {
                System.out.print("Invalid email. Please enter valid email (must contain @): ");
            } else {
                System.out.print("Email cannot be empty. Please enter: ");
            }
            email = scanner.nextLine();
        }
        
        // Get salary
        System.out.print("Salary: ");
        double salary = getValidDoubleInput();
        
        // Select Department (validation)
        System.out.println("\nSelect Department:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i+1) + ". " + departments.get(i).getDepartmentName());
        }
        System.out.print("Enter choice (1-" + departments.size() + "): ");
        int deptChoice = getValidIntInput(1, departments.size());
        Department selectedDept = departments.get(deptChoice - 1);
        
        // Select Manager Type (validation)
        System.out.println("\nSelect Manager Type:");
        Manager.ManagerType[] managerTypes = Manager.ManagerType.values();
        for (int i = 0; i < managerTypes.length; i++) {
            System.out.println((i+1) + ". " + managerTypes[i].name() + " - " + managerTypes[i].getDescription());
        }
        System.out.print("Enter choice (1-" + managerTypes.length + "): ");
        int managerChoice = getValidIntInput(1, managerTypes.length);
        Manager.ManagerType selectedManagerType = managerTypes[managerChoice - 1];
        
        // Select Position
        System.out.println("\nSelect Employee Position:");
        Employee.EmployeePosition[] positions = Employee.EmployeePosition.values();
        for (int i = 0; i < positions.length; i++) {
            System.out.println((i+1) + ". " + positions[i].name() + " - " + positions[i].getDescription());
        }
        System.out.print("Enter choice (1-" + positions.length + "): ");
        int posChoice = getValidIntInput(1, positions.length);
        Employee.EmployeePosition selectedPosition = positions[posChoice - 1];
        
        // Job title
        System.out.print("Job Title: ");
        String jobTitle = scanner.nextLine();
        while (jobTitle.trim().isEmpty()) {
            System.out.print("Job Title cannot be empty. Please enter: ");
            jobTitle = scanner.nextLine();
        }
        
        // Company
        System.out.print("Company Name: ");
        String company = scanner.nextLine();
        while (company.trim().isEmpty()) {
            System.out.print("Company Name cannot be empty. Please enter: ");
            company = scanner.nextLine();
        }
        
        // Create and add employee
        Employee newEmployee = new Employee(firstName, lastName, email, salary, 
                                            selectedPosition, selectedDept, jobTitle, company);
        employees.add(newEmployee);
        
        System.out.println("\n✓ \"" + newEmployee.getFullName() + "\" has been added successfully!");
        System.out.println("  Manager Type: " + selectedManagerType.name());
        System.out.println("  Department: " + selectedDept.getDepartmentName());
        System.out.println("  Position: " + selectedPosition.name());
        
        // Display all records
        System.out.println("\n========== ALL CURRENT EMPLOYEES ==========");
        for (Employee emp : employees) {
            System.out.println("• " + emp.getFullName() + " - " + emp.getJobTitle() + " - " + emp.getDepartment().getDepartmentName());
        }
        System.out.println("Total Records: " + employees.size());
    }
    
    /**
     * CREATE BINARY TREE - Insert minimum 20 records using level-order insertion
     */
    private static void createBinaryTree() {
        System.out.println("\n[CREATE BINARY TREE]");
        System.out.println("Building employee hierarchy using level-order (breadth-first) insertion...");
        
        employeeTree = new BinaryTree();
        int recordsToInsert = Math.min(20, employees.size());
        
        if (employees.size() < 20) {
            System.out.println("Warning: Only " + employees.size() + " employees available. Inserting all.");
            recordsToInsert = employees.size();
        }
        
        for (int i = 0; i < recordsToInsert; i++) {
            employeeTree.insertLevelOrder(employees.get(i));
        }
        
        System.out.println("Successfully inserted " + recordsToInsert + " employee records into binary tree.");
        System.out.println("Tree Height: " + employeeTree.getTreeHeight());
        System.out.println("Total Nodes: " + employeeTree.getNodeCount());
        
        employeeTree.levelOrderTraversal();
    }
    
    /**
     * DISPLAY BINARY TREE
     */
    private static void displayBinaryTree() {
        if (employeeTree.getNodeCount() == 0) {
            System.out.println("\nBinary tree is empty. Please create the tree first (Option 4).");
            return;
        }
        
        employeeTree.levelOrderTraversal();
        System.out.println("\nTree Height: " + employeeTree.getTreeHeight());
        System.out.println("Total Nodes: " + employeeTree.getNodeCount());
    }
    
    // Utility methods for input validation
    private static int getValidIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
    
    private static double getValidDoubleInput() {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input >= 0) {
                    return input;
                }
                System.out.print("Salary must be positive. Please enter: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}