package homework2;

public class HomeWork2 {


    public static void  main(String[] args){
        System.out.println(first(5, 15));
        ifPozOrNeg(0);
        System.out.println(isNegative(-15));
        printWordNTimes("stroka", 4);
        System.out.println(visokos(2021));
           }
    public static boolean first(int a, int b) {
        int sum = a + b ;
        if (sum <= 20 && sum>= 10) {
            return true;
        } else {
            return false;
        }
    }
    public static void ifPozOrNeg(int c) {
        if (c>=0) {
            System.out.println("Positive");
        } else {
            System.out.println("Negative");
        }
    }
    public static boolean isNegative(int d) {
        if (d >= 0) return false;
        else return true;
    }
    public static void printWordNTimes(String word , int times) {

        for (int i = 0; i < times; i++) {
            System.out.println(word);
        }
    }
    public static boolean visokos(int year) {
        if (!(year % 4 == 0) || ((year % 100 == 0) && !(year % 400 == 0)))  // || это 'или' && это 'и' ! это 'нет', ей богу, голову сломал тут)
            return false;
            else return true;

    }

}
