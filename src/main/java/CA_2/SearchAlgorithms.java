package CA_2;

import java.util.*;

/**
 * RECURSIVE searching algorithm for finding employees by name
 * Chosen Algorithm: Binary Search (Recursive)
 * 
 * JUSTIFICATION for choosing Binary Search:
 * 1. Extremely efficient - O(log n) time complexity, much faster than linear search
 * 2. Perfect for sorted lists - our employee list will be sorted before searching
 * 3. Recursive implementation - natural fit for recursive requirement
 * 4. Minimal comparisons - finds target in few steps even with 100+ employees
 * 5. Predictable performance - always divides search space in half
 * 6. Excellent for name-based search - names sort alphabetically well
 * 7. Reduces search time dramatically compared to linear search (O(log n) vs O(n))
 * 8. Works well with our sorted list from Merge Sort
 */
public class SearchAlgorithms {
    
    /**
     * Recursive Binary Search for finding employee by full name
     * Returns employee object if found, null if not found
     */
    public static Employee binarySearch(ArrayList<Employee> employees, String targetName, 
                                        int left, int right) {
        // Base case: element not found
        if (left > right) {
            return null;
        }
        
        int middle = (left + right) / 2;
        Employee middleEmployee = employees.get(middle);
        String middleName = middleEmployee.getFullName();
        
        // Compare ignoring case
        int comparison = middleName.compareToIgnoreCase(targetName);
        
        if (comparison == 0) {
            // Found the employee
            return middleEmployee;
        } else if (comparison < 0) {
            // Target is in right half
            return binarySearch(employees, targetName, middle + 1, right);
        } else {
            // Target is in left half
            return binarySearch(employees, targetName, left, middle - 1);
        }
    }
    
    /**
     * Wrapper method for binary search
     */
    public static Employee searchEmployee(ArrayList<Employee> employees, String name) {
        if (employees == null || employees.isEmpty()) {
            return null;
        }
        return binarySearch(employees, name, 0, employees.size() - 1);
    }
    
    /**
     * Display detailed employee information after search
     */
    public static void displayEmployeeDetails(Employee employee) {
        System.out.println("\n========== EMPLOYEE DETAILS ==========");
        System.out.println("Full Name: " + employee.getFullName());
        System.out.println("Manager Type: " + getManagerTypeFromEmployee(employee));
        System.out.println("Department: " + employee.getDepartment().getDepartmentName());
        System.out.println("Position: " + employee.getPosition().name());
        System.out.println("Job Title: " + employee.getJobTitle());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Salary: $" + employee.getSalary());
        System.out.println("Company: " + employee.getCompanyName());
        System.out.println("=======================================");
    }
    
    private static String getManagerTypeFromEmployee(Employee employee) {
        // Determine manager type based on position and department
        if (employee.getPosition() == Employee.EmployeePosition.SENIOR) {
            return "Senior Manager";
        } else if (employee.getPosition() == Employee.EmployeePosition.MIDDLE) {
            return "Team Lead";
        } else if (employee.getJobTitle().toLowerCase().contains("manager")) {
            return "Department Manager";
        } else if (employee.getPosition() == Employee.EmployeePosition.INTERN) {
            return "Intern (Non-Manager)";
        } else {
            return "Staff Member";
        }
    }
}

