// Kacper Wiszniewski 338066 - ISSP - II rok - 4 semestr
import java.util.*;

public class Main
{
    // Task 1 - check how many times word appears in sentence
    static Map<String, Integer> numberOfOccurrences(String sentence)
    {
        // Map of words
        Map<String, Integer> mapOfWords = new HashMap<String, Integer>();
        // transfor all words to lower case
        // remove all characters that aren't letter
        String[] words = sentence.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
        // Checking words
        for (String word : words)
        {
            // if word exits already
            // then add to its number '1'
            if (mapOfWords.containsKey(word))
            {
                int i = mapOfWords.get(word);
                mapOfWords.replace(word, i, i + 1);
            }
            // else add new element to map
            else
            {
                mapOfWords.put(word, 1);
            }
        }
        return mapOfWords;
    }
    // Task 2 - method that finds duplicates
    static Set<Integer> findDuplicates(List<Integer> numbers)
    {
        // Creates two sets of temporary numbers and result of this method
        Set<Integer> tempSet = new HashSet<Integer>();
        Set<Integer> resultSet = new HashSet<Integer>();
        // Going by all the numbers from list
        // And checking if it is duplcate
        for (int el : numbers)
        {
            // if alreadu exist, then add it to result set
            if (tempSet.add(el) == false)
            {
                resultSet.add(el);
            }
        }
        return resultSet;
    }

    // Task 3 - creates map, adds to keys numbers
    // if it is odd - then boolean is false
    // if not, then its true
    static Map<Integer, Boolean> addToBoolean()
    {
        Map<Integer, Boolean> result = new HashMap<Integer, Boolean>();
        for (int i = 1; i <= 20; i++)
        {
            if (i % 2 == 0)
            {
                result.put(i, true);
            }
            else
            {
                result.put(i, false);
            }
        }
        return result;
    }
    // Main function
    public static void main(String[] args)
    {
        // Task 1
        System.out.println("Task 1");
        String[] sentences = {"Witam bardzo, serdecznie serdecznie siema", "Jeden dwa jeden dwa Dwa DWA trzy",
                              "Makraon gotuje sie sie", "java to troche jak kotlin ale jednak nie do konca",
                              "Witam bardzo serdecznie Witam nie za bardzo ser"};
        for (String el : sentences)
        {
            System.out.println(numberOfOccurrences(el));
        }
        // Task 2
        System.out.println("\nTask 2");
        List<Integer>[] lists = new List[] {
            List.of(0, 1, 1, 1, 4, 4, 4, 9, 3, 3, 3, 3, 3, 3), // 1, 3, 4
            List.of(1, 2, 2, 3),                               // 2
            List.of(0, 1, 2, 4, 6, 5, 5)                       // 5
        };
        for (List<Integer> el : lists)
        {
            System.out.println(findDuplicates(el));
        }
        // Task 3
        System.out.println("\nTask 3");
        System.out.println(addToBoolean());
    }
}
