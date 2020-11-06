import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type a natural number!");
        long n = sc.nextInt();
        System.out.println("Factorial: " + factorial(n));
        System.out.println("Fibonacci number: " + fibonacciWrapper(n));
    }

    // Produces the factorial of the number n
    public static long factorial (long n) {
        if (n == 0) return 1;
        else return n * factorial(--n);
    }

    // Wrapper function for fibonacci number function
    public static long fibonacciWrapper(long n) {
        return fibonacci(0, 1, n);
    }
 
    // Produces the nth fibonacci number
    public static long fibonacci(long n0, long n1, long n) {
        if (n == 0)  return n0;
        else return fibonacci (n1, (n0 + n1), --n);
    }
}
