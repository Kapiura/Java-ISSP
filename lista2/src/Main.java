// Kacper Wiszniewski 338066 - ISSP - II rok - 4 semestr
import java.util.*;

public class Main
{
    // Task 1 - finding missing number in list of positive integers
    static int missingNumber(int[] list)
    {
        int Obli = 0;
        if (isUnique(list))
            Obli++;
        if (!isNegative(list))
            Obli++;
        if (list.length >= max(list))
        {
            Obli++;
        }
        else
        {
            System.out.println("Brakuje więcej niż jednego elementu!");
        }
        if (Obli == 3)
        {
            Arrays.sort(list);
            int missingNumber = -1;
            for (int i = 1; i < list.length; ++i)
            {
                if (list[i] != 1 + list[i - 1])
                {
                    missingNumber = list[i - 1] + 1;
                    break;
                }
            }
            if (missingNumber == -1)
                missingNumber = 1 + list[list.length - 1];
            if (list[0] != 0)
            {
                missingNumber = 0;
            }
            return missingNumber;
        }
        else
            return -1;
    }
    static boolean isUnique(int[] list) // check if list of integers is unique
    {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int num : list)
        {
            uniqueNumbers.add(num);
        }
        boolean isU = uniqueNumbers.size() == list.length;
        if (!isU)
        {
            System.out.println("Tablica nie jest zbiorem!");
        }
        return isU;
    }
    static boolean isNegative(int[] list) //  checks if list of integers is negative
    {
        for (int i : list)
        {
            if (i < 0)
            {
                System.out.println("Lista zawiera element mniejszy od zera!");
                return true;
            }
        }
        return false;
    }
    static int max(int[] list) // returns max element of list of integers
    {
        int max = list[0];
        for (int i : list)
        {
            if (max < i)
                max = i;
        }
        return max;
    }

    // Task 2 - checking if word is palindrome
    static String isPalindorme(String word) // return short answer (yes, no) if word is a palindrome
    {
        String ans = "Yes";
        String s = word.replaceAll("\\s", "");
        for (int i = 0; i < s.length() / 2; i++)
        {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
            {
                ans = "No";
                break;
            }
        }
        return ans;
    }
    // Task 3 - Pascal's pyramid
    static void pascalPyramid(int height) // printing
    {
        System.out.println("Height of Pascal's pyramid: " + height + "\n");
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < height - i - 1; ++j)
            {
                System.out.print(" ");
            }
            int number = 1;
            for (int k = 0; k <= i; k++)
            {
                System.out.print(number + " ");
                number = number * (i - k) / (k + 1);
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        // Task 1
        System.out.println("Task 1");
        int[][] tables = new int[][] {{2, 4, 5, 3, 0, 6}, // 1
                                      {0, 1, 2, 4, 5},    // 3
                                      {0, 1, 2, 3, 4, 6}, // 5
                                      {1, 2, 3},          // 0
                                      {0, 0, 1, 2, 4},    // not unique
                                      {0, 1, 2, 5},       // missing more than 1 number
                                      {-1, 0, 1, 2, 4},   // there is a negative number
                                      {0, 1, 2, 3, 4}};   // 5
        for (int[] el : tables)
        {
            System.out.println(missingNumber(el));
        }
        // Task 2
        System.out.println("\nTask 2");
        String[] words = {"kajak", "ziutek", "ja tutaj", "abba", "monogamia"};
        for (String word : words)
        {
            System.out.println("Is \"" + word + "\" palindorme? " + isPalindorme(word));
        }
        // Task 3
        System.out.println("\nTask 3");
        pascalPyramid(5);
    }
}
