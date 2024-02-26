
//Тестовое задание выполнила Завгородняя Регина Музагитовна

//Реализуй класс Main с методом public static String calc(String input).
// Метод должен принимать строку с арифметическим выражением между двумя числами и
// возвращать строку с результатом их выполнения.

import java.io.IOException;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        System.out.println("Input:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String result = calc(str);
        System.out.println("Output:");
        System.out.println(result);
    }

    public static String calc(String input) {
        //Определяем корректно ли введена строка на количество операторов(арифметических знаков).
        //Если операторов больше 1 программа выдает исключение и завершает работу
        getCountSign(input);

        //Определяем оператор
        String[] sign = {"+", "-", "/", "*"};//создаю массив возможных операторов
        String[] signForSplit = {"\\+", "-", "/", "\\*"};//создаю массив знаков, которые будут разделителями входной строки

        int signIndex = checkSignIndex(sign, input); //Метод определяет индекс оператора в созданном массиве

        //После того как выяснила оператор, использую его чтобы разбить строку и вытащить операнды.
        // Создаю массив из элементов, которые должны быть операндами
        String[] num = input.split(signForSplit[signIndex]);

        //Провеяем, что "операнды" в массиве являются числами. Проверка условия >=1 и <=10
        checkNum(num);

        //Метод счета
        int num1 = Integer.parseInt(num[0]); //меняем тип элемента массива на int
        int num2 = Integer.parseInt(num[1]);
        int result;
        switch (sign[signIndex]) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            default:
                result = num1 / num2;
                break;
        }
        return Integer.toString(result);
    }

    // Метод счета количества знаков опреторов в строке, если операторо больше 1 программа выдаст исключение и завершит работу
    public static void getCountSign(String input) {

        long countPlus = input.chars().filter(ch -> ch == '+').count();
        long countMinus = input.chars().filter(ch -> ch == '-').count();
        long countMulti = input.chars().filter(ch -> ch == '*').count();
        long countDevis = input.chars().filter(ch -> ch == '/').count();
        long count = countPlus + countMinus + countMulti + countDevis;

        if (count > 1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Output:");
                System.out.println("throws Exception //Введено больше одного оператора");
                System.exit(0);
            }
        }
    }

    //Метод проверяет можно ли строку массива операндов перевести в число.
    //Если возможно, проверяет удовлетворяет ли число условию >=1 и <=10
    public static void checkNum(String num[]) {
        int i;
        for (i = 0; i < num.length; i++) {
            try {
                Integer.parseInt(num[i]);
                if (Integer.parseInt(num[i]) < 1 || Integer.parseInt(num[i]) > 10) {
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("Output:");
                        System.out.println("throws Exception //Введено число не удовлетворяющее условию от 1 до 10");
                        System.exit(0);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Output:");
                System.out.println("throws Exception // Вместо числа ввели другой символ");
                System.exit(0);
            }
        }
    }

    //Метод определяет индекс оператора согласно созданному массиву операторов, и если индекс не определется то выдает исключение и прекращает работу
    public static int checkSignIndex(String[] sign, String input){
        int signIndex = -1;
        for (int i = 0; i < sign.length; i++) {
            if (input.contains(sign[i])) { //проверяет каждый элемент массива, содержится ли он во входной строке
                signIndex = i;
                break;
            }
        }
        if (signIndex == -1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Output:");
                System.out.println("throws Exception //Оператор не введен, или вместо опертора ввели другой символ");
                System.exit(0);
            }
        }
        return signIndex;
    }
}