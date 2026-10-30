
class Solution {
    public double myPow(double x, int n) {

        long power = n;
        
        if (power < 0) {
            power = -power;
        }

        double ans = 1.0;
        double base = x;

        while (power > 0) {

            if ((power & 1) == 1) {
                ans *= base;
            }

            base *= base;
            power >>= 1;
        }

        if (n < 0) {
            ans = 1.0 / ans;
        }

        return ans;
    }
}
