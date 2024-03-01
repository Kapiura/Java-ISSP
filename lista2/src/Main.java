// Kacper Wiszniewski 338066 - ISSP - II rok - 4 semestr
import java.util.*;

public class Main {
    // Task 1 - finding missing number in list of positive integers
    static int missingNumber(int[] list)
    {
        int Obli = 0;
        if(isUnique(list))
            Obli++;
        if(!isNegative(list))
            Obli++;
        if(list.length >= max(list))
            Obli++;
        else
            System.out.println("Brakuje więcej niż jednego elementu!\n");
        if (Obli == 3)
        {
            Arrays.sort(list);
            int missingNumber = -1;
            for (int i = 1; i < list.length; ++i)
            {
                if (list[i] != 1 + list[i-1])
                {
                    missingNumber = list[i - 1] + 1;
                    break;
                }
            }
            return missingNumber;
        }
        else
            return 1;
    }
    static boolean isUnique(int[] list)
    {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int num : list)
        {
            uniqueNumbers.add(num);
        }
        boolean isU = uniqueNumbers.size() == list.length;
        if(!isU)
        {
            System.out.println("Tablica nie jest zbiorem!\n");
        }
        return isU;
    }
    static boolean isNegative(int[] list)
    {
        for (int i : list)
        {
            if (i < 0)
            {
                System.out.println("Lista zawiera element mniejszy od zera!\n");
                return true;
            }
        }
        return false;
    }
    static int max(int[] list)
    {
        int max  = list[0];
        for (int i : list) {
            if (max < i)
                max = i;
        }
        return max;
    }

    // Task 2 - checking if word is palindrome
    static String isPalindorme(String word)
    {
        String ans = "Yes";
        String s = word.replaceAll("\\s", "");
        for (int i = 0; i < s.length()/2; i++)
        {
            if (s.charAt(i) != s.charAt(s.length()-1-i))
            {
                ans = "No";
                break;
            }
        }
        return ans;
    }


public static void main(String[] args)
    {
        // Task 1
        int[][]  tables = new int[][] {{2, 4, 5, 3, 0, 6},{0, 1, 2, 4, 5}, {0, 1, 2 ,3 ,4 , 6}};// 1, 3, 5
        for (int[] el : tables)
        {
            System.out.println(missingNumber(el));
        }
        // Task 2
        String[] words = {"kajak", "ziutek", "ja tutaj"};
        for (String word: words)
        {
            System.out.println(isPalindorme(word));
        }
        // Task 3
    }
}