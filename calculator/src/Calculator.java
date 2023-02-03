import java.util.Scanner;

public class Calculator {
    static class CalculatorException extends Exception {
        public CalculatorException(String message) {
            super(message);
        }
    }
    static int arabicOrRomans(String[] array) throws CalculatorException {
        String[] arabics = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] romans = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int a = 0;
        for (int i = 0; i < arabics.length; i++) {
            if (array[0].equals(arabics[i])) {
                for (String arabic : arabics) {
                    if (array[1].equals(arabic)) {
                        a = 1;
                        break;
                    }
                }
            } else if (array[0].equals(romans[i])) {
                for (String roman : romans) {
                    if (array[1].equals(roman)) {
                        a = 2;
                        break;
                    }
                }
            }
        }
        return a;
    }
    static String resultConvertToRomans(int result) {
        int[] arabics = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        StringBuilder output = new StringBuilder();
        while (index < arabics.length) {
            while (result >= arabics[index]) {
                int tmp = result / arabics[index];
                result = result % arabics[index];
                for (int i = 0; i < tmp; i++) {
                    output.append(romans[index]);
                }
            }
            index++;
        }
        return new String(output);
    }
    public static String calc(String input) throws CalculatorException {
        String[] stringArray;
        stringArray = input.toUpperCase().replaceAll("\\s", "").split("[+\\-*/]",2);
        if(stringArray.length<2){throw new CalculatorException("Введено некорректное выражение");}
        int[] digits = new int[2];
        int forResultOutput=0;
        if (arabicOrRomans(stringArray) == 1) {
            forResultOutput=1;
            digits = new int[stringArray.length];
            for (int i = 0; i < digits.length; i++) {
                digits[i] = Integer.parseInt(stringArray[i]);
            }
        } else if (arabicOrRomans(stringArray) == 2) {
            forResultOutput=2;
            for (int i = 0; i < stringArray.length; i++) {
                switch (stringArray[i]) {
                    case "I" -> stringArray[i] = "1";
                    case "II" -> stringArray[i] = "2";
                    case "III" -> stringArray[i] = "3";
                    case "IV" -> stringArray[i] = "4";
                    case "V" -> stringArray[i] = "5";
                    case "VI" -> stringArray[i] = "6";
                    case "VII" -> stringArray[i] = "7";
                    case "VIII" -> stringArray[i] = "8";
                    case "IX" -> stringArray[i] = "9";
                    case "X" -> stringArray[i] = "10";
                    default -> throw new CalculatorException("Введено некорректное выражение");
                }
            }
            digits = new int[stringArray.length];
            for (int i = 0; i < digits.length; i++) {
                digits[i] = Integer.parseInt(stringArray[i]);
            }
        }
        for (int d : digits) {
            if (d < 1 || d > 10) {
                throw new CalculatorException("Введено некорректное выражение");
            }
        }
        int a = digits[0];
        int b = digits[1];
        int result = 0;
        char[] operator = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            switch (operator[i]) {
                case '+' -> result = a + b;
                case '-' -> result = a - b;
                case '*' -> result = a * b;
                case '/' -> result = a / b;
            }
        }
        if(forResultOutput==2){
            if(result<1){
                throw new CalculatorException("В римской системе счисления нет отрицательных чисел или ответ не м.б. равен 0");
            }
            System.out.println("Ответ: ");
            return resultConvertToRomans(result);
        } else if (forResultOutput==1){
            System.out.println("Ответ: ");
            return Integer.toString(result);
        } else {throw new CalculatorException("");}
    }

    public static void main(String[] args) throws CalculatorException {
        System.out.println("Введите математическое выражение с арабскими или римскими цифрами в диапазоне от 1-10: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }
}