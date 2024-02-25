import java.util.Dictionary;

// Kacper Wiszniewski 338066 - ISSP - II rok - semestr 4
public class Main {
    // zadanie nr 1
    static String lista1(int x)
    {
        String number= "";
        if (x % 3 == 0)
        {
            number += "trzy";
        }
        if (x % 5 == 0)
        {
            number += "piec";
        }
        if(x % 11 == 0)
        {
            number += "jedenascie";
        }
        if(x % 7 == 0)
        {
            number += "siedem";
        }
        if(x % 13 == 0)
        {
            number += "trzynascie";
        }
        if(x % 15 == 0)
        {
            number += "pietnascie";
        }
        if(x % 21 == 0)
        {
            number += "dwadziesciajeden";
        }
        return number;
    }
    public static void main(String[] args)
    {
        int[] variables = {15015, 1155, 585, 3465};
        for (int i = 0; i < variables.length; i++)
        {
            System.out.println(Integer.toString(variables[i]) + "\t" + lista1(variables[i]));
        }
    }

}