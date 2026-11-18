class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i;
            int len = 0;

            // Find words that fit in current line
            while (j < words.length &&
                   len + words[j].length() + (j - i) <= maxWidth) {
                len += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();

            // Last line or single word
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1) {
                        line.append(' ');
                    }
                }

                while (line.length() < maxWidth) {
                    line.append(' ');
                }
            }
            // Fully justified line
            else {
                int totalSpaces = maxWidth - len;
                int evenSpace = totalSpaces / gaps;
                int extraSpace = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k != j - 1) {
                        int spaces = evenSpace;

                        if (extraSpace > 0) {
                            spaces++;
                            extraSpace--;
                        }

                        for (int s = 0; s < spaces; s++) {
                            line.append(' ');
                        }
                    }
                }
            }

            res.add(line.toString());
            i = j;
        }

        return res;
    }
}
