
package homework2;

public class HomeWork3 {
    public static void main(String[] args) {
        invertArray();
        System.out.println();
        fillArray();
        System.out.println();
        changeArray();
        System.out.println();
        fillDiagonal();
        zadaniePyat(4, 82   );
        System.out.println();
        minAndMax();
        System.out.println();
        int[] dlyaProverkiSummi={-1,1,1,-2};
        System.out.println(proverkaSummi(dlyaProverkiSummi));// наконец понял как ввести массив
    }


    public static void invertArray() {
        int[] arr = { 1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==1) {
                arr[i]=0;
            } else { arr[i]=1;
            }
            System.out.print(arr[i]);
        }
    }
    public static void fillArray() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i+1;
            System.out.print(arr[i]);
        }
    }
    public static void changeArray() {
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]<6) {
                arr[i] = arr[i] * 2;
                System.out.print(arr[i]);
            }else System.out.print(arr[i]);
            }
        }
    public static void fillDiagonal() {
        int[][] arr = new int[4][4];
        for (int i = 0; i < 4; i++) {
           arr[i][i]=1;
           arr[i][3-i]=1;
                }
        for (int i=0; i< 4; i++){
            for (int j=0; j< 4; j++){
                System.out.print(arr[i][j]);
        }
            System.out.println();
            }
        }
    public static void zadaniePyat(int len, int initialValue){
        int[] arr = new int[len];
        for (int i=0; i< len; i++){
            arr[i]=initialValue;
            System.out.print(arr[i] + " ");
        }
    }
    public static void minAndMax(){
        int [] arr = {11,2,3,48,5,6,79,8,9};
        int min, max;
        min=arr[0];
        max=arr[0];
        for (int i=0; i<8; i++){
            if (min>arr[i]){
                min=arr[i];
            }
            if (max<arr[i]){
                max=arr[i];
            }
        }
        System.out.println(min);
        System.out.println(max); // громоздко, но работает
    }
    public static boolean proverkaSummi(int[] arr){ //вот тут начал гуглить операторы для сравнения
        int summaSleva = 0;
        for(int i = 0; i < arr.length; i++){
            summaSleva += arr[i];
            int summaSprava = 0;
            for (int j = arr.length-1; j > 0; j--){
                summaSprava += (j > i)? arr[j] : 0;  //пытался сделать чтобы отсчет сначала шел с одной стороны, потом с другой
            }                                       // увидел условный оператор, стало понятней
            if(summaSleva == summaSprava){          //однако решил не пытаться сравнивать разное количество слева и справа (на подобии 2 слева, 4 справа)
                return true;
            }
        }
        return false;
    }
    }





