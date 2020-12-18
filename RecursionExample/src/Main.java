import java.util.Scanner;

// Welcome to a simple class with mathematical functions. Feel free to implement these into your
//    own programs, as these computations are very useful in many applications!
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n, a, b, c;

        System.out.println("Type a natural number!");
        n = sc.nextInt();
        System.out.println("Factorial: " + factorial(n));
        System.out.println("Fibonacci number: " + fibonacciWrapper(n));
        System.out.println("Input two integers to calculate their GCD");
        a = sc.nextLong();
        b = sc.nextLong();
        System.out.println("GCD of " + a + " and " + b + " is " + gcd(a,b));

        System.out.println("Provide values of a,b,c for the equation ax+by=c. We will provide you integer solutions of x,y.");
        a = sc.nextLong();
        b = sc.nextLong();
        c = sc.nextLong();

        System.out.println(linearDiophantine(a,b,c));
    }

    // Produces the factorial of the number n
    // * Input: Natural Number 'n'
    // * Output: n!
    public static long factorial (long n) {
        if (n == 0) return 1;
        else return n * factorial(--n);
    }

    // Wrapper function for fibonacci number function
    // * Input: Natural Number 'n'
    // * Output: nth fibonacci number
    public static long fibonacciWrapper(long n) {
        return fibonacci(0, 1, n);
    }
 
    // Produces the nth fibonacci number
    // * Input: Natural Number 'n'
    // * Output: nth fibonacci number
    public static long fibonacci(long n0, long n1, long n) {
        if (n == 0)  return n0;
        else return fibonacci (n1, (n0 + n1), --n);
    }

    // Produces the greatest common divisor of two integers
    // * Input: Integers 'a', 'b'
    // * Output: The greatest common divisor of a, b, using the Euclidean method
    public static long gcd(long a, long b) {
        if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Produces a set of integer solutions to the equation ax + by = c (where a,b,c are constant coefficients with a, b non-zero)
    // If no solutions exist, produce the empty array
    // * Input: Integers a, b, c
    // * Output: String of the set of solutions
    public static String linearDiophantine(long a, long b, long c) {
        // Temporary values to send into linearDiophantineSolver method/output
        long[] tempX = new long[2];
        long[] tempY = new long[2];
        long[] tempQ = new long[2];
        long[] tempR = new long[2];
        String output = "";

        tempX[0] = 1;
        tempX[1] = 0;

        tempY[0] = 0;
        tempY[1] = 1;

        tempQ[0] = a;
        tempQ[1] = b;

        tempR[0] = 0;
        tempR[1] = 0;

        long[] solution = certificateOfCorrectness(tempX, tempY, tempQ, tempR);

        if (c % solution[2] == 0) { // We note that a solution to ax+by=c exists if and only if the gcd(a,b) divides c.
            output = "One solution to " + a + "x+" + b + "y=" + c + " is x = " + (solution[0] * c) + " y = " + (solution[1] * c);
        } else {
            output = "This particular linear Diophantine equation has no x,y integer solutions.";
        }

        return output;
    }

    // Gives a particular solution to the linear Diophantine Equation (ax + by = gcd(a, b)) using the Extended Euclidean Algorithm
    // > We call this solution the certificate of correctness.
    // * Input: Size 2 Integer Arrays x, y, q, r, where the first element represents the i-2 row, and the second element represents the i-1row
    // * Output: Integer array, where the first element is the x solution, and the second element is the y solution
    public static long[] certificateOfCorrectness(long[] x, long[] y, long[] q, long[] r) {
        if (q[1] == 0) {
            long[] temp = new long[3];
            // x,y are your particular solution, q is your GCD of (a, b)
            temp[0] = x[0];
            temp[1] = y[0];
            temp[2] = q[0];
            return temp;
        } else {
            long newQ = Math.floorDiv(q[0], q[1]);
            long[] tempX = new long[2];
            long[] tempY = new long[2];
            long[] tempQ = new long[2];
            long[] tempR = new long[2];

            // Initializing the next row
            tempX[0] = x[1];
            tempX[1] = x[0] - (newQ * x[1]);

            tempY[0] = y[1];
            tempY[1] = y[0] - (newQ * y[1]);

            tempQ[0] = q[1];
            tempQ[1] = q[0] % q[1];

            tempR[0] = r[1];
            tempR[1] = newQ;

            return certificateOfCorrectness(tempX, tempY, tempQ, tempR);
        }

    }
}
