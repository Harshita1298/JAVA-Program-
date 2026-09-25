import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s, int left, int right) {
        Set<String> result = new HashSet<>();

        int i = left;

        while (i <= right) {

            Set<String> current;

            if (s.charAt(i) == '{') {
                int count = 1;
                int j = i + 1;

                while (count != 0) {
                    if (s.charAt(j) == '{') count++;
                    if (s.charAt(j) == '}') count--;
                    j++;
                }

                current = parse(s, i + 1, j - 2);
                i = j;

            } else {
                current = new HashSet<>();
                current.add(String.valueOf(s.charAt(i)));
                i++;
            }

            if (result.isEmpty()) {
                result.addAll(current);
            } else {
                Set<String> temp = new HashSet<>();

                for (String a : result) {
                    for (String b : current) {
                        temp.add(a + b);
                    }
                }

                result = temp;
            }

            // Handle comma -> union
            if (i <= right && s.charAt(i) == ',') {
                i++;

                Set<String> next = parse(s, i, right);

                result.addAll(next);
                break;
            }
        }

        return result;
    }
}
