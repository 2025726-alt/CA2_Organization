// BINARY TREE - Level-order (breadth-first) insertion for employee hierarchy
package CA_2;

import java.util.*;

/**
 * Binary Tree for Employee Hierarchy
 * Uses level-order (breadth-first) insertion
 */
public class BinaryTree {
    private TreeNode root;
    private int nodeCount;
    
    public BinaryTree() {
        this.root = null;
        this.nodeCount = 0;
    }
    
    /**
     * Insert employee using level-order (breadth-first) insertion
     * Fill left first, then right; when both taken, move to next node
     */
    // Inserts employee using breadth-first level order (fill left to right)
    public void insertLevelOrder(Employee employee) {
        TreeNode newNode = new TreeNode(employee);
        
        if (root == null) {
            root = newNode;
            nodeCount++;
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            
            if (current.getLeft() == null) {
                current.setLeft(newNode);
                nodeCount++;
                return;
            } else {
                queue.add(current.getLeft());
            }
            
            if (current.getRight() == null) {
                current.setRight(newNode);
                nodeCount++;
                return;
            } else {
                queue.add(current.getRight());
            }
        }
    }
    
    /**
     * Level Order Traversal - displays hierarchy level by level
     */
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        
        System.out.println("\n========== EMPLOYEE HIERARCHY (Level Order) ==========");
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("\nLevel " + level + " (" + levelSize + " employee(s)): ");
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print("\n  • " + current.getEmployee().getFullName() + 
                               " - " + current.getEmployee().getJobTitle());
                
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
            level++;
        }
        System.out.println("\n=====================================================");
    }
    
    public int getTreeHeight() {
        return calculateHeight(root);
    }
    
    private int calculateHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public int getNodeCount() { return nodeCount; }
    public TreeNode getRoot() { return root; }
}


