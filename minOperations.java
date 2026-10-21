
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        boolean sorted = true;
        char min = 'z';
        char max = 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            min = (char) Math.min(min, c);
            max = (char) Math.max(max, c);

            if (i > 0 && s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        if (s.charAt(0) == max && s.charAt(n - 1) == min) {
            int minCount = 0;
            int maxCount = 0;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == min) minCount++;
                if (s.charAt(i) == max) maxCount++;
            }

            if (minCount == 1 && maxCount == 1) {
                return 3;
            }
        }

        return 2;
    }
}
