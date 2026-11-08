class Solution {
    public int lengthOfLastWord(String s) {
        int len = 0;
        int i = s.length() - 1;

        // Last ke spaces skip karo
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Last word count karo
        while (i >= 0 && s.charAt(i) != ' ') {
            len++;
            i--;
        }

        return len;
    }
}
