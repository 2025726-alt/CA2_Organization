package CA_2;

/**
 * TreeNode - Node for Binary Tree structure
 */
public class TreeNode {
    private Employee employee;
    private TreeNode left;
    private TreeNode right;
    
    public TreeNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
    
    public Employee getEmployee() { return employee; }
    public TreeNode getLeft() { return left; }
    public TreeNode getRight() { return right; }
    
    public void setLeft(TreeNode left) { this.left = left; }
    public void setRight(TreeNode right) { this.right = right; }
}
