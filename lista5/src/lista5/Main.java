import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;

public class Main
{
    // Task 1
    // getting a list of costs,
    // which will return a map of costs grouped by months and sorted in ascending order
    public static Map<Month, List<Cost>> groupedCostMap(ArrayList<Cost> arr)
    {
        // TreeMap will sort our costs in ascending order
        Map<Month, List<Cost>> res = new TreeMap<>();
        for (Cost el : arr)
        {
            Month month = el.getDate().getMonth();
            List<Cost> cost = new ArrayList<>();
            if (res.containsKey(month))
            {
                cost = res.get(month);
            }
            cost.add(el);
            res.put(month, cost);
        }
        return res;
    }

    // Task 2
    // print all the costs sorted by months and dates
    static void printSortedCost(ArrayList<Cost> arr)
    {
        Map<Month, List<Cost>> res = new TreeMap<>();
        res = groupedCostMap(arr);
        // listing by month sorted in ascending order
        for (Map.Entry<Month, List<Cost>> el : res.entrySet())
        {
            System.out.println(el.getKey());
            // sorting by dates
            Collections.sort(el.getValue(), Comparator.comparing(Cost::getDate));
            for (Cost cos : el.getValue())
            {
                System.out.println(cos.getDate().getDayOfMonth() + " " + cos.getType() + " " + cos.getAmount() +
                                   " PLN");
            }
        }
    }

    // Task 3
    // summary all the costs for all the cars
    // as a argument it get name of car
    // return lists of pairs all the types of costs
    // and sum for this car
    static ArrayList<Pair<CostType, Integer>> getCarsCosts(String car_name)
    {
        ArrayList<Pair<CostType, Integer>> result = new ArrayList<>();
        for(CostType ziutek: CostType.values())
        {
                result.add(new Pair<CostType, Integer>(ziutek,0));
        }
        // getting car with car_name
        Car car = DataProvider.getCars().stream().filter(it -> it.getName() == car_name).collect(Collectors.toList()).get(0);
        // sorting by Type
        Map<CostType, List<Cost>> res = new TreeMap<>();
        for (Cost el : car.getCosts())
        {
            CostType type = el.getType();
            List<Cost> cost = new ArrayList<>();
            if (res.containsKey(type))
            {
                cost = res.get(type);
            }
            cost.add(el);
            res.put(type, cost);
        }
        // summary costs
        for(Map.Entry<CostType, List<Cost>> el : res.entrySet())
        {
            int sum = 0;
            for (Cost coel : el.getValue()) {
                sum += coel.getAmount();
            }
            boolean updated = false;
            ArrayList<Pair<CostType, Integer>> updatedResult = new ArrayList<>();
            for (Pair<CostType, Integer> pair : result) {
                if (pair.key() == el.getKey()) {
                    // update new pair value
                    updatedResult.add(new Pair<>(pair.key(), sum));
                    updated = true;
                }
                else
                {
                    // add not changed pair to result list
                    updatedResult.add(pair);
                }
            }
            if (!updated)
            {
                // add new pait do result
                updatedResult.add(new Pair<>(el.getKey(), sum));
            }
            result = updatedResult;
        }


        return result;
    }
    // listing all costs for one car
    static void printCarCosts(ArrayList<Pair<CostType, Integer>> lol)
    {
        for(Pair<CostType, Integer> el: lol)
        {
            System.out.println(el.key() + " : " + el.value());
        }
    }

    // Task 4
    // summary costs for all the cars
    // returning map<costype, integer>
    static HashMap<CostType, Integer> getFullCosts(ArrayList<Car> cars)
    {
        // result hashmap
        HashMap<CostType, Integer> res = new HashMap<>();
        // empty map with types
        for (CostType costType : CostType.values())
        {
            res.put(costType, 0);
        }
        // going through cars and summing theirs cost types
        for(Car car: cars)
        {
            ArrayList<Pair<CostType,Integer>> rel = new ArrayList<>();
            rel = getCarsCosts(car.getName());
            for(Pair<CostType,Integer> para: rel)
            {
                int temp = 0;
                temp = para.value() + res.get(para.key());
                res.put(para.key(),temp);
            }
        }
        return res;
    }

    static void printFullCost(HashMap<CostType, Integer> costs)
    {
        for (Map.Entry<CostType, Integer> el : costs.entrySet())
        {
            System.out.println(el.getKey() + " : " + el.getValue());
        }
    }

    public static void main(String[] args)
    {
        // Task 1
        System.out.println("\nTask 1");
        ArrayList<Cost> arr = DataProvider.getGeneralCosts();
        Map<Month, List<Cost>> temp = groupedCostMap(arr);
        // display all costs grouped by months
        System.out.println(temp);
        // display it more readable for humans
        for (Map.Entry<Month, List<Cost>> el : temp.entrySet())
        {
            System.out.println(el.getKey() + " = {");
            for (Cost coel : el.getValue())
            {
                System.out.println(coel.getInfo() + " PLN");
            }
            System.out.println("}");
        }

        // Task 2
        System.out.println("\nTask 2");
        printSortedCost(arr);

        // Task 3
        System.out.println("\nTask 3");
        ArrayList<Car> cars = DataProvider.getCars();
        // displaying costs for "Domowy"
        for(Car car: cars)
        {
            System.out.println(car.getName());
            printCarCosts(getCarsCosts(car.getName()));
            System.out.println();
        }

        // Task 4
        System.out.println("\nTask 4");
        printFullCost(getFullCosts(cars));
        // wyniki ostanich zadan sa inne ze wzgledu na to ze przy uruchamianiu funkcji z 3 zadania
        // generujemy nowe koszty
    }
}
