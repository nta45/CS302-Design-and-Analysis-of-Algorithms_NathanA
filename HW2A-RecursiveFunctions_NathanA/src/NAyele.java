/**
 * Student: Nathan Ayele
 * Class: CS302 - Design and Analysis of Algorithms | CaldwellSpring'23
 * Date: February 9th, 2023
 */

public class NAyele {
    public static void main(String[] args) throws Exception {
        System.out.println("Factorial Formula of 6 : " + displayFactorialFormula(6));
        System.out.println("Reverse Factorial Formula of 6 : " + displayFactorialFormulaReverse(6));
        System.out.println("Reverse of \"time\" : " + reverse("time"));
        System.out.println("Sum of the digits \"123\" : " + sumOfDigits(123));
        System.out.println("The minimum digit of \"12039\" : " + minDigit(12039));

    }

    public static String displayFactorialFormula(int n) {
        if (n == 1) {
            return "1";
        }
        return displayFactorialFormula(n - 1) + "*" + n;

    }

    public static String displayFactorialFormulaReverse(int n) {
        if (n == 1) {
            return "1";
        }
        return n + "*" + displayFactorialFormulaReverse(n - 1);
    }

    public static String reverse(String n) {
        if (n.length() == 1) {
            return n;
        }
        return n.charAt(n.length() - 1) + reverse(n.substring(0, n.length() - 1));
    }

    public static int sumOfDigits(int s) {
        if (s < 10) {
            return s;
        }
        return s % 10 + sumOfDigits(s / 10);
    }

    public static int minDigit(int n) {
        if (n < 10) {
            return n;
        }
        return Math.min(n % 10, minDigit(n / 10));
    }
}
