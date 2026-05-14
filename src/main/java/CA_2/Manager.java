package CA_2;

/**
 * Manager class - different types of managers in the department store
 */
public class Manager extends Person {
    
    // Different types of managers
    public enum ManagerType {
        HEAD_MANAGER("Head Manager - Oversees entire store operations"),
        SENIOR_MANAGER("Senior Manager - Manages department clusters"),
        ASSISTANT_MANAGER("Assistant Manager - Supports department managers"),
        TEAM_LEAD("Team Lead - Leads specific teams"),
        DEPARTMENT_MANAGER("Department Manager - Manages single department");
        
        private final String description;
        
        ManagerType(String description) {
            this.description = description;
        }
        
        public String getDescription() { return description; }
    }
    
    private ManagerType managerType;
    private Department assignedDepartment;
    
    public Manager(String firstName, String lastName, String email, double salary, 
                   ManagerType managerType, Department assignedDepartment) {
        super(firstName, lastName, email, salary);
        this.managerType = managerType;
        this.assignedDepartment = assignedDepartment;
    }
    
    public ManagerType getManagerType() { return managerType; }
    public Department getAssignedDepartment() { return assignedDepartment; }
    
    public void setManagerType(ManagerType managerType) { this.managerType = managerType; }
    public void setAssignedDepartment(Department assignedDepartment) { this.assignedDepartment = assignedDepartment; }
    
    @Override
    public String toString() {
        return super.toString() + " | Role: Manager | Type: " + managerType.name() + 
               " | Department: " + assignedDepartment.getDepartmentName();
    }
}
