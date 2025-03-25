import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter starting number: ");
            int start = sc.nextInt();
            System.out.print("Enter ending number: ");
            int end = sc.nextInt();

            validateRange(start, end);

            int[] divisors = new int[10];
            String[] words = new String[10];
            int ruleCount = collectRules(sc, divisors, words);

            NumbersAnalysis(start, end, divisors, words, ruleCount);

        }
        catch (InvalidRangeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("An unexpected error : " + e.getMessage());
        }
    }

    static void validateRange(int start, int end) throws InvalidRangeException {
        if (start > end) {
            throw new InvalidRangeException("Starting number cannot be greater than ending number");
        }
    }

    static int collectRules(Scanner sc, int[] divisors, String[] words) {
        System.out.println("Enter divisor (0 to finish):");
        int count = 0;
        while (count < 10) {
            System.out.print("Divisor: ");
            int divisor = sc.nextInt();
            if (divisor == 0)
                break;

            System.out.print("Replacement word: ");
            divisors[count] = divisor;
            words[count] = sc.next();
            count++;
        }
        return count;
    }

    static void NumbersAnalysis(int start, int end, int[] divisors, String[] words, int ruleCount) {
        for (int num = start; num <= end; num++) {
            String output = buildOutput(num, divisors, words, ruleCount);
            printOutput(output, num);
        }
    }

    static String buildOutput(int num, int[] divisors, String[] words, int ruleCount) {
        String output = "";
        for (int i = 0; i < ruleCount; i++) {
            if (num % divisors[i] == 0) {
                output += words[i];
            }
        }
        return output;
    }

    static void printOutput(String output, int num) {
        if (output.isEmpty()) {
            System.out.println(num);
        } else {
            System.out.println(output);
        }
    }
}

class InvalidRangeException extends Exception {
    public InvalidRangeException(String message) {
        super(message);
    }
}