import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{

    public static List<Integer> evenPositiveSquare(List<Integer> lst)
    {
        return lst.stream().filter(e -> lst.indexOf(e) % 2 != 0 && e > 0).map(e -> e * e).toList();
    }

    public static void main(String[] args)
    {
        List<Integer> numbersList = List.of(1, 2, 3, 5, -6, -1, -1, 3, 2, 3);

        System.out.println(evenPositiveSquare(numbersList));
    }
}
