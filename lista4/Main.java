// Kacper Wiszniewski 338066 - ISSP - II rok - 4 semestr
import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

public class Main {
    // Task 1 - Interface for lambda
    public interface Task1 {
        String task1(String str, int x);
    }

    // Task 2 - returning sum of positive numbers from list
    static int sum_map(List<Integer> numbers) {
        return numbers.stream().map(it -> (it > 0) ? it : 0).reduce(0, Integer::sum);
    }

    // Task 3  - counting number of every element
    static Map<String, Integer> countElements(List<List<String>> str) {
        return str.stream().flatMap(List::stream).collect(Collectors.groupingBy(i -> i, Collectors.summingInt(e -> 1)));
    }

    // Task 4 - return list of square number that are positive, index is odd from list
    static List<Integer> evenPositiveSquare(List<Integer> numbers) {
        return numbers.stream().filter(it -> numbers.indexOf(it) % 2 != 0 && it > 0).map(it -> it * it).collect(Collectors.toList());
    }

    // Task 5 - return sorted list that has elements with even lengths
    static List<List<List<String>>> srt(List<String> str)
    {
        Map<Character, List<String>> temp = str.stream().collect(Collectors.groupingBy(it -> it.charAt(0)));
        final int[] index = {0};

        return temp.values().stream()
                .map(sublist -> sublist.stream()
                        .filter(it -> it.length() % 2 == 0)
                        .collect(Collectors.toList()))
                .map(it -> {
                    char t = temp.keySet().stream().toList().get(index[0]++);
                    List<List<String>> innerList = new ArrayList<>();
                    innerList.add(List.of(String.valueOf(t)));
                    innerList.add(it);
                    return innerList;
                }).collect(Collectors.toList());
    }

    // Task 6 - function perm - get List<Int> and return list every possible permutations
    static List<List<Integer>> perm(List<Integer> numbers)
    {
        if(numbers.isEmpty())
        {
            List<List<Integer>> temp = new ArrayList<>();
            temp.add(new ArrayList<>());
            return temp;
        }
        else
        {
            List<List<Integer>> temp = numbers.stream()
                    .flatMap(element ->
                    {
                        List <Integer> copy = new ArrayList<>(numbers);
                        copy.remove(element);
                        return perm(copy).stream()
                                .map(subElement ->
                                {
                                    List<Integer> tempCopy = new ArrayList<>(subElement);
                                    tempCopy.add(0,element);
                                    return tempCopy;
                                });
                    })
                    .toList();
            return temp;
        }
    }
    // Task 7 - checking function
    // check if list is unique and made of only positive numbers
    // verify if any pair sum in preamble equals the subsequent list number
    // if we find one like this, move to next index upon finding a match
    // return the unmatched number if sum doesnt match
    // return -1 for a unique and positive list
    // I added to return -2 when list isnt unique or some numbers arent positive
    static Integer check(int pre, List<Integer> numbers)
    {
        boolean found = false;

        Set<Integer> unique = new HashSet<Integer>(numbers);
        //check if these numbers are unique
        if(unique.size() != numbers.size())
        {
            System.out.println("List isnt unique");
            return -2;
        }
        // check if all the numbers are positive
        if(unique.stream().anyMatch(it -> it < 0))
        {
            System.out.println("Some numbers are not positive");
            return -2;
        }

        for(int i = 0; i < numbers.size() - pre; i++)
        {
                List<Integer> preambula = numbers.subList(i,i+pre);
            if(numbers.size() != i+pre)
                {
                    found = false;
                    int temp = numbers.get(i+pre);
                    for(int j = 0; j < preambula.size()-1; j++)
                    {
                        int k = j+1;
                        while(!found && k < preambula.size())
                        {
                            if(preambula.get(j)+preambula.get(k)==temp)
                                found = true;
                            k++;
                        }
                    }
                    if(!found)
                        return temp;
                }
        }
            return -1;
    }
    static public void main(String[] args)
    {
        // Task 1 - returning string of n copies of str
        System.out.println("Task 1\n");

        // lambda expression
        Task1 t1 = (str, number) ->
        {
            String temp = "";
            for (int i = 0; i < number; i++)
                temp += str;
            return temp;
        };
        // printing result of lambda - "M" 2 times
        System.out.println(t1.task1("M", 2));  // MM
        System.out.println(t1.task1("3", 2));  // 33
        System.out.println(t1.task1("L", 5));  // LLLLL
        System.out.println(t1.task1("VC", 3)); // VCVCVC

        System.out.println("\nTask 2\n");
        System.out.println(sum_map(List.of(1, 2, 3, 4, 5, 6, -150)));   // 21
        System.out.println(sum_map(List.of(-2, -7, -8, -1, -56, 0)));   // 0
        System.out.println(sum_map(List.of(2, -9, 8, 74, -89, -5, 2))); // 86

        System.out.println("\nTask 3\n");
        System.out.println(countElements(List.of(List.of("a", "b", "c"), List.of("c", "d", "f"), List.of("d", "f", "g")))); //{a=1, b=1, c=2, d=2, f=2, g=1}
        System.out.println(countElements(List.of(List.of("a", "b", "a"), List.of("a", "a", "a"), List.of("a", "a", "a")))); //{a=8, b=1}
        System.out.println(countElements(List.of(List.of("a", "b", "a"), List.of("b", "b", "c"), List.of("c", "b", "b")))); //{a=2, b=5, c=2}

        System.out.println("\nTask 4\n");
        System.out.println(evenPositiveSquare(List.of(1, 2, 3, 5, -6, -1, -1, 2, 3)));  //[4, 25, 4]
        System.out.println(evenPositiveSquare(List.of(1, 2, 3,-345,7,-32,5,1, 5, -6, -1, -1, 2, -3)));  //[4, 4]
        System.out.println(evenPositiveSquare(List.of(1,-23,-13,7,9,3,-6,5,1, -2, 3, 5, -6, -1, -1, 2, 3)));  //[49, 9, 25, 9, 25, 4, 9]
        System.out.println(evenPositiveSquare(List.of(1,2,3,5,-6,-1,-1,2,3)));

        System.out.println("\nTask 5\n");
        System.out.println(srt(List.of("cherry","blueberry","citrus","apple","apricot","banana","coconut")));

        System.out.println("\nTask 6\n");
        System.out.println(perm(List.of(1,2,3)));

        System.out.println("\nTask 7\n");
        System.out.println(check(3, List.of(1, 2, 3, 4, 5, 6))); // 6
        System.out.println(check(5, List.of(35, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576))); // 127
    }
}
