// EMPLOYEE CLASS - Contains EmployeePosition enum and employee details
package CA_2;

/**
 * Employee class - different types of employees
 */
public class Employee extends Person {
    
    // Different types of employee positions
    public enum EmployeePosition {
        INTERN("Intern - Entry level training position"),
        JUNIOR("Junior - Junior level employee"),
        MIDDLE("Middle - Mid-level employee"),
        SENIOR("Senior - Senior level employee"),
        CONTRACT("Contract - Contract-based employee");
        
        private final String description;
        
        EmployeePosition(String description) {
            this.description = description;
        }
        
        public String getDescription() { return description; }
    }
    
    private EmployeePosition position;
    private Department department;
    private String jobTitle;
    private String companyName;
    
    public Employee(String firstName, String lastName, String email, double salary,
                    EmployeePosition position, Department department, String jobTitle, String companyName) {
        super(firstName, lastName, email, salary);
        this.position = position;
        this.department = department;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
    }
    
    public EmployeePosition getPosition() { return position; }
    public Department getDepartment() { return department; }
    public String getJobTitle() { return jobTitle; }
    public String getCompanyName() { return companyName; }
    
    @Override
    public String toString() {
        return super.toString() + " | Position: " + position.name() + " | Job: " + jobTitle + 
               " | Department: " + department.getDepartmentName() + " | Company: " + companyName;
    }
}
