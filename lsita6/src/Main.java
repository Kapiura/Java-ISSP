import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    // Task 1
    // List<T> getTail(List<T> list) return list of elements without the first one
    static <T> List<T> getTail(List<T> list)
    {
        List<T> result = new ArrayList<T>(list);
        result.remove(0);
        return result;
    }
    // T getHead(List<T> list) return first element of list
    static <T> T getHead(List<T> list)
    {
        return list.get(0);
    }

    // Task 2
    // check if list is sorted according tp BiPredicate
    static <T> boolean isSorted(List<T> aa, BiPredicate<T, T> ordered)
    {
        if(aa.isEmpty() || ordered == null || aa == null)
        {
            return false;
        }

        for(int i = 0; i < aa.size()-1; i++)
        {
            if(!ordered.test(aa.get(i), aa.get(i+1)))
            {
                return false;
            }
        }
        return true;
    }

    // Task 3
    // get list of numbers and return list every positive numebrs which indexes are odd
    // return square of this list of these numbers
    static List<Number> square(List<Number> numbers)
    {
        List<Number> result = new ArrayList<>();
        if(numbers.isEmpty())
        {
            return result;
        }
        for(int index = 0; index < numbers.size(); index++)
        {
            if(index % 2 != 0 && numbers.get(index).intValue() > 0)
            {
                result.add(Math.pow(numbers.get(index).doubleValue(),2));
            }
        }
//        return  numbers.stream().filter(it -> numbers.indexOf(it) % 2 != 0 && it.doubleValue() > 0).collect(Collectors.toList()).stream().map(it -> Math.pow(it.doubleValue(),2)).collect(Collectors.toList());
    return result;
    }

    // Task 4
    // place last element from list on the beginning
    static <T> List<T> tailToHead(List<T> list)
    {
        if(list.isEmpty() || list == null)
        {
            throw new IllegalArgumentException("Empty list");
        }
        List<T> result = new ArrayList<T>();
        result.add(list.get(list.size()-1));
        result.addAll(list.subList(0,list.size()-1));
        return result;
    }

    // Task 5
    // change first element of list
    static <T> List<T> setHead(List<T> list, T newHead)
    {
        if(list.isEmpty() || list == null)
        {
            throw new IllegalArgumentException("Empty list");
        }
        List<T> result = new ArrayList<T>();
        result.add(newHead);
        result.addAll(list.subList(1,list.size()));
        return result;
    }

    // Task 6
    // drop items unit predicate is fulfilled
    static <T> List<T> dropWhile(List<T> list, Predicate<T> predicate)
    {
        if(list.isEmpty() || list == null)
        {
            throw new IllegalArgumentException("Empty list");
        }
        int index = 0;
        while(index < list.size() && predicate.test(list.get(index)))
        {
            index++;
        }
        return list.subList(index, list.size()-1);
    }

    public static void main(String[] args)
    {
        // Task 1
        System.out.println("Task 1");
        List<Integer> numbers = List.of(1,9,3,4,5);
        List<String> words = List.of("apple", "banana", "cherry");
        System.out.println(getTail(numbers));
        System.out.println(getHead(numbers));
        System.out.println(getTail(words));
        System.out.println(getHead(words));

        // Task 2
        System.out.println("\nTask 2");
        BiPredicate<Integer, Integer> isAscending = (a, b) -> a <= b;
        System.out.println(isSorted(numbers, isAscending));
        BiPredicate<String, String> isLexicographic = (a, b) -> a.compareTo(b) <= 0;
        System.out.println(isSorted(words, isLexicographic));

        // Task 3
        System.out.println("\nTask 3");
        List<Number> num = List.of(1, 2, 3.5, 5, -6, 1, 1);
        System.out.println(square(num));

        // Task 4
        System.out.println("\nTask 4");
        List<Integer> num1 = List.of(1,2,3);
        System.out.println(tailToHead(num1));

        // Task 5
        System.out.println("\nTask 5");
        System.out.println(setHead(List.of(1, 2, 3), 5));

        // Task 6
        System.out.println("\nTask 6");
        List<Integer> list = List.of(1, 2, 3, 4, 3, 2, 1, 9);
        Predicate<Integer> lessThanThree = (i) -> i < 3;
        System.out.println(dropWhile(list, lessThanThree));
    }
}