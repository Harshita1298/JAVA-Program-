import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the array to handle duplicates and enable early stopping
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int[] candidates, int target, int startIndex, List<Integer> currentList, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        
        for (int i = startIndex; i < candidates.length; i++) {
            // Skip duplicates at the same level of the decision tree
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            // Since the array is sorted, if the current number exceeds the remaining target,
            // all subsequent numbers will also exceed it.
            if (candidates[i] > target) {
                break;
            }
            
            currentList.add(candidates[i]);
            // Use i + 1 because each number can only be used once
            backtrack(candidates, target - candidates[i], i + 1, currentList, result);
            currentList.remove(currentList.size() - 1); // Backtrack
        }
    }
}