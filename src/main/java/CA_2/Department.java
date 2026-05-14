package CA_2;

import java.util.ArrayList;

/**
 * Department class - different types of departments in department store
 */
public class Department {
    
    // Different types of departments
    public enum DepartmentType {
        CUSTOMER_SERVICE("Customer Service - Handles customer inquiries and support"),
        SALES("Sales - Manages product sales and promotions"),
        HR("Human Resources - Manages employee relations and recruitment"),
        FINANCE("Finance - Handles budgeting and financial operations"),
        IT_SUPPORT("IT Support - Manages technical infrastructure"),
        OPERATIONS("Operations - Oversees daily store operations"),
        MARKETING("Marketing - Handles advertising and promotions"),
        ACCOUNTING("Accounting - Manages financial records");
        
        private final String description;
        
        DepartmentType(String description) {
            this.description = description;
        }
        
        public String getDescription() { return description; }
    }
    
    private String departmentName;
    private DepartmentType departmentType;
    private ArrayList<Employee> employees;
    private Manager departmentManager;
    
    public Department(String departmentName, DepartmentType departmentType) {
        this.departmentName = departmentName;
        this.departmentType = departmentType;
        this.employees = new ArrayList<>();
    }
    
    public String getDepartmentName() { return departmentName; }
    public DepartmentType getDepartmentType() { return departmentType; }
    public ArrayList<Employee> getEmployees() { return employees; }
    public Manager getDepartmentManager() { return departmentManager; }
    
    public void setDepartmentManager(Manager departmentManager) { this.departmentManager = departmentManager; }
    public void addEmployee(Employee employee) { employees.add(employee); }
    
    @Override
    public String toString() {
        return "Department: " + departmentName + " (" + departmentType.name() + ")";
    }
}

