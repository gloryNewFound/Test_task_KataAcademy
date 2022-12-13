package com.dvn.test_task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

//    a + b, a - b, a * b, a / b

    public static void main( String[] args ) throws Exception {
        System.out.println("Please, write arithmetical expression of two number");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            throw new Exception(e);
        }

        System.out.println(calc(input));

    }


    public static String calc(String input) throws Exception {
        String[] arguments = input.split("\s"); //
        if (arguments.length != 3) {
            throw new Exception(); //Format of the expression is not correct
        }
        String result = "";
        if (isArabDigit(arguments[0])) {
            result = arabCalc(arguments);
        } else if (isRomeDigit(arguments[0])) {
            result = romeCalc(arguments);
        } else {
            throw new Exception();
        }
        return result;
    }

    private static boolean isArabDigit(String string) {
        return string.matches("\\d+");
    }

    private static boolean isDigitIsBetween1And10Incl(int digit) {
        return (digit <= 10) && (digit >= 1);
    }

    private static boolean isRomeDigit(String string) {
        return string.matches("[IVXLDM]+");
    }

    private static String arabCalc(String[] arguments) throws Exception {
        int a = Integer.parseInt(arguments[0]);
        if (!isDigitIsBetween1And10Incl(a)) throw new Exception();
        if (!isArabDigit(arguments[2])) throw new Exception();
        int b = Integer.parseInt(arguments[2]);
        if (!isDigitIsBetween1And10Incl(b)) throw new Exception();
        String action = arguments[1];
        int result = getCalculationResult(a, b, action);
        return result + "";
    }

    private static String romeCalc(String[] arguments) throws Exception {
        if (!isRomeDigit(arguments[2])) throw new Exception();
        int a = RomeDigitsClass.fromRomeToArab(arguments[0]);
        if (!isDigitIsBetween1And10Incl(a)) throw new Exception();
        int b = RomeDigitsClass.fromRomeToArab(arguments[2]);
        if (!isDigitIsBetween1And10Incl(b)) throw new Exception();
        String action = arguments[1];
        int calcResult = getCalculationResult(a, b, action);
        if (calcResult < 1) {
            throw new Exception();
        }

        return RomeDigitsClass.fromArabToRome(calcResult);
    }

    private static int getCalculationResult(int a, int b, String action) throws Exception {
        switch (action){
            case ("+"):
                return a + b;
            case ("-"):
                return a - b;
            case ("*"):
                return a * b;
            case ("/"):
                return a / b;
        }
        throw new Exception();
    }

}

class RomeDigitsClass {


    private static Map<Integer, String> forArabToRome = new TreeMap<>(Collections.reverseOrder());
    static {
        forArabToRome.put(1, "I");
        forArabToRome.put(4, "IV");
        forArabToRome.put(5, "V");
        forArabToRome.put(9, "IX");
        forArabToRome.put(10, "X");
        forArabToRome.put(40, "XL");
        forArabToRome.put(50, "L");
        forArabToRome.put(90, "XC");
        forArabToRome.put(100, "C");
    }

    public static String fromArabToRome(int arabDigit) {
        StringBuilder romeNumber = new StringBuilder();
        for (int i: forArabToRome.keySet()) {
            int index = arabDigit / i;
            arabDigit = arabDigit - i * index;
            while (index > 0) {
                romeNumber.append(forArabToRome.get(i));
                index--;
            }
        }
        return romeNumber.toString();
    }

    private static Map<Character, Integer> forRomeToArab = new HashMap<>();
    static {
        forRomeToArab.put('I', 1);
        forRomeToArab.put('V', 5);
        forRomeToArab.put('X', 10);
//        forRomeToArab.put('L', 50);
//        forRomeToArab.put('C', 100);
    }

    public static int fromRomeToArab(String romeDigit) {
        int result = 0;
        int temp = -1;
        for (int i = romeDigit.length() - 1; i >= 0; i--) {
            if (temp > forRomeToArab.get(romeDigit.charAt(i))) {
                result -= forRomeToArab.get(romeDigit.charAt(i));
            } else {
                result += forRomeToArab.get(romeDigit.charAt(i));
            }
            temp = forRomeToArab.get(romeDigit.charAt(i));
        }
        return result;
    }

}

