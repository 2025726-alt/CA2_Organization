package CA_2;

/**
 * ENUM for menu options - provides structured menu navigation
 */
public enum MenuEnum {
    SORT("Sort Dummy List of People"),
    SEARCH("Search Person by Name"),
    ADD_RECORDS("Add New Employee Record"),
    CREATE_BINARY_TREE("Create Employee Hierarchy Binary Tree"),
    DISPLAY_TREE("Display Binary Tree"),
    EXIT("Exit Program");
    
    private final String description;
    
    MenuEnum(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        MenuEnum[] options = values();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i].getDescription());
        }
        System.out.println("=================================");
    }
}
