import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    // Task 1
    // calculate factorial
    static BigInteger factorial(int n) {
        // Handling negative numbers
        if (n < 0) {
            throw new IllegalArgumentException("You cannot calculate factorial of negative number");
        } else if (n == 0) {
            return BigInteger.ONE;
        }

        // Getting the number of available processors
        int p = Runtime.getRuntime().availableProcessors();

        // Creating a thread pool with the number of available processors
        ExecutorService executor = Executors.newFixedThreadPool(p);

        // List to hold Future objects representing results from threads
        List<Future<BigInteger>> fut = new ArrayList<>();

        // Calculating the size of one chunk for each thread
        int chunk = n / p;

        // Creating tasks for each thread
        for (int i = 0; i < p; i++) {
            final int start = i * chunk + 1;
            final int end;
            if (i == p - 1) {
                end = n;
            } else {
                end = start + chunk - 1;
            }

            // Creating task implementing Callable<BigInteger>
            Callable<BigInteger> task = () -> {
                BigInteger result = BigInteger.ONE;
                for (int j = start; j <= end; j++) {
                    result = result.multiply(BigInteger.valueOf(j));
                }
                return result;
            };

            // Sending job to executor and storing future with result
            fut.add(executor.submit(task));
        }

        // Final result
        BigInteger result = BigInteger.ONE;

        try {
            for (Future<BigInteger> f : fut) {
                result = result.multiply(f.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Closing the executor
            executor.shutdown();
        }
        return result;
    }

    // Task 2
    // calculate sum of reciprocals of factorials
    public static BigDecimal calEuler(int n) {
        // Checking if n is a negative number
        if (n < 0) {
            throw new IllegalArgumentException("You cannot calculate factorial of negative number");
        }

        // Setting precision for calculations
        MathContext mathContext = new MathContext(100, RoundingMode.HALF_UP);

        // Getting the number of available processors
        int p = Runtime.getRuntime().availableProcessors();

        // Creating a thread pool with the number of available processors
        ExecutorService executor = Executors.newFixedThreadPool(p);

        // List to hold Future objects representing results from threads
        List<Future<BigDecimal>> fut = new ArrayList<>();

        // Calculating the size of one chunk for each thread
        int chunk = n / p;

        // Creating tasks for each thread
        for (int i = 0; i < p; i++) {
            final int start = i * chunk + 1;
            final int end;
            if (i == p - 1) {
                end = n;
            } else {
                end = start + chunk - 1;
            }
            Callable<BigDecimal> task = () -> {
                BigDecimal result = BigDecimal.ZERO;
                for (int j = start; j <= end; j++) {
                    BigInteger factorial = factorial(j);
                    result = result.add(BigDecimal.ONE.divide(new BigDecimal(factorial), mathContext));
                }
                return result;
            };
            fut.add(executor.submit(task));
        }

        BigDecimal result = BigDecimal.ONE;
        try {
            for (Future<BigDecimal> future : fut) {
                result = result.add(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();

        return result;
    }

    // Task 3
    // Find integer with the highest number of divisors
    public record Pair<K, V>(K key, V value) {}

    public static Pair<Integer,Integer> task3()
    {
        int n = 100000;

        // Get the number of available processors
        int p = Runtime.getRuntime().availableProcessors();

        // Create a thread pool with the number of available processors
        ExecutorService executor = Executors.newFixedThreadPool(p);

        // List to hold Future objects representing results from threads
        Future<Pair<Integer,Integer>> fut[] = new Future[p];

        // Calculating the size of one chunk for each thread
        int chunk = n / p;

        // Creating tasks for each thread
        for (int i = 0; i < p; i++) {
            final int start = i * chunk + 1;
            final int end;
            if (i == p - 1) {
                end = n;
            } else {
                end = start + chunk - 1;
            }
            Callable<Pair<Integer,Integer>> task = () ->
            {
                int maxNum = 0;
                int maxDiv = 0;
                for(int j = start; j <= end; j++)
                {
                    int count = 0;
                    for (int k = 1; k <= Math.sqrt(j); k++) {
                        if (j % k == 0)
                        {
                            count += (k * k == j) ? 1 : 2;
                        }
                    }
                    if (count > maxDiv) {
                        maxDiv = count;
                        maxNum = j;
                    }
                }
                return new Pair<>(maxNum, maxDiv);
            };
            fut[i] = executor.submit(task);
        }
        Pair<Integer, Integer> maxDiv = new Pair<>(0,0);
        try {
            for (Future<Pair<Integer, Integer>> future : fut) {
                Pair<Integer, Integer> result = future.get();
                if (result.value() > maxDiv.value()) {
                    maxDiv = result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();

        return maxDiv;
    }

    public static void main(String[] args) {
        // Task 1
        System.out.println("Task 1:");
        int n = 17;
        BigInteger rel = factorial(n);
        System.out.println("Factorial of " + n + " = " + rel);

        // Task 2
        System.out.println("\nTask 2:");
        BigDecimal rel2 = calEuler(n);
        System.out.println("For n = " + n);
        System.out.println("e = " + rel2);

        // Task 3
        System.out.println("\nTask 3:");
        Pair<Integer, Integer> output = task3();
        System.out.println("liczba = " + output.key());
        System.out.println("liczba dzielnikow = " + output.value());
    }
}
