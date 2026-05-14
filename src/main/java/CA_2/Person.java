// PERSON CLASS - Base class for all employees and managers
package CA_2;

/**
 * Person - Base/Parent class for all people in the organization
 */
public class Person {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected double salary;
    
    // Constructor
    public Person(String firstName, String lastName, String email, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
    }
    
    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    
    // Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(double salary) { this.salary = salary; }
    
    @Override
    public String toString() {
        return "Name: " + getFullName() + " | Email: " + email + " | Salary: $" + salary;
    }
}
