// SORT ALGORITHMS - Recursive Merge Sort (O(n log n) guaranteed performance)
// WHY MERGE SORT: Guaranteed O(n log n), stable, recursive by nature
package CA_2;

import java.util.*;

/**
 * RECURSIVE sorting algorithms for sorting employee list
 * Chosen Algorithm: Merge Sort (Recursive)
 * 
 * JUSTIFICATION for choosing Merge Sort:
 * 1. Stable sorting - maintains relative order of equal elements, important for employee records
 * 2. Guaranteed O(n log n) performance - reliable for any input size
 * 3. Excellent for large datasets - handles 20+ employees efficiently
 * 4. Recursive by nature - perfect fit for recursive implementation requirement
 * 5. Works well with sequential access data structures like ArrayList
 * 6. Predictable performance unlike QuickSort which can degrade to O(n²)
 * 7. No worst-case performance issues - maintains efficiency regardless of data arrangement
 */
public class SortAlgorithms {
    
    /**
     * Recursive Merge Sort for sorting employees by name
     */
    public static void mergeSort(ArrayList<Employee> employees, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            
            // Recursive calls
            mergeSort(employees, left, middle);
            mergeSort(employees, middle + 1, right);
            
            // Merge the sorted halves
            merge(employees, left, middle, right);
        }
    }
    
    private static void merge(ArrayList<Employee> employees, int left, int middle, int right) {
        // Create temporary arrays
        ArrayList<Employee> leftArray = new ArrayList<>();
        ArrayList<Employee> rightArray = new ArrayList<>();
        
        // Copy data to temp arrays
        for (int i = left; i <= middle; i++) {
            leftArray.add(employees.get(i));
        }
        for (int i = middle + 1; i <= right; i++) {
            rightArray.add(employees.get(i));
        }
        
        int i = 0, j = 0, k = left;
        
        // Merge back the temp arrays
        while (i < leftArray.size() && j < rightArray.size()) {
            if (leftArray.get(i).getFullName().compareToIgnoreCase(rightArray.get(j).getFullName()) <= 0) {
                employees.set(k, leftArray.get(i));
                i++;
            } else {
                employees.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < leftArray.size()) {
            employees.set(k, leftArray.get(i));
            i++;
            k++;
        }
        
        while (j < rightArray.size()) {
            employees.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }
    
    /**
     * Display first 20 employees from sorted list
     */
    public static void displayFirst20(ArrayList<Employee> employees) {
        System.out.println("\n========== FIRST 20 EMPLOYEES (Sorted Alphabetically) ==========");
        int displayCount = Math.min(20, employees.size());
        for (int i = 0; i < displayCount; i++) {
            System.out.println((i + 1) + ". " + employees.get(i).getFullName() + 
                             " | Job: " + employees.get(i).getJobTitle() +
                             " | Dept: " + employees.get(i).getDepartment().getDepartmentName());
        }
        System.out.println("=================================================================");
    }
}
