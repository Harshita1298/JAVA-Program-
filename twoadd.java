import java.util.Scanner;

public class twoadd {
    public static void main(String[] arge) {
        System.out.println("Enter two no :");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = a + b;
        System.out.println("Sum of two no is :" + c);

    }
}
