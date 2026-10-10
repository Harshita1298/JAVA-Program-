class Solution {
    public int divide(int dividend, int divisor) {
        // Edge case: overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Sign check
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert to long and take absolute
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long result = 0;

        // Bit manipulation division
        while (a >= b) {
            long temp = b, multiple = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        // Apply sign
        result = negative ? -result : result;

        // Bound check
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return (int) result;
    }
}
