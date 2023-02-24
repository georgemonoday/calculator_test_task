import java.util.Scanner;

public class Test {
    public static int summa(int a,int b){
        return a+b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        System.out.println( summa(a,b));
    }
}
