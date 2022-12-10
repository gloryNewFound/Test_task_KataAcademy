package com.dvn.test_task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

//    a + b, a - b, a * b, a / b

    public static void main( String[] args ) throws Exception {
        System.out.println("Please, write arithmetical exception of two number");
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
        int a = RomeDigits.valueOf(arguments[0]).getArabDigit();
        int b = RomeDigits.valueOf(arguments[2]).getArabDigit();
        String action = arguments[1];
        int calcResult = getCalculationResult(a, b, action);
        if (calcResult < 1) {
            throw new Exception();
        }
        for (RomeDigits digit: RomeDigits.values()) {
            if (digit.getArabDigit() == calcResult) {
                return digit.name();
            }
        }
        return "";
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

enum RomeDigits {

    I(1),
    II(2),
    III(3),
    IV(4),
    V( 5),
    VI(6),
    VII(7),
    VIII(8),
    IX(9),
    X(10);

    private int arabDigit;

    RomeDigits(int arabDigit) {
        this.arabDigit = arabDigit;
    }

    public int getArabDigit() {
        return arabDigit;
    }

}
