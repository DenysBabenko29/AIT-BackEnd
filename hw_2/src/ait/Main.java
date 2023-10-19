package ait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(checkingQuotes("2(3+a)*b( (q-1): 3+4)"));
        System.out.println(checkingQuotes( "2(3+a("));

        Map<String, Integer> map = numberOfRatings("file.txt");
        map.forEach((key, value) -> System.out.println(key + "=>" + value));

        System.out.println(getAllByAvg("file.txt", 4));
    }

    private static boolean checkingQuotes(String str){
        Stack<Character> stack = new Stack<>();
        for (Character c : str.toCharArray()){
            if (c == '('){
                stack.push(c);
            } else if (c == ')'){
                if (stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static Map<String, Integer> numberOfRatings(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(","))
                    .collect(Collectors.toMap(
                            student -> student[0],
                            student -> 1,
                            (prev, next) -> prev + next,
                            () -> new HashMap<>()
                    ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List getAllByAvg(String fileName, int n){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Map<String, Integer> map = reader.lines()
                    .map(line -> line.split(","))
                    .collect(Collectors.toMap(
                            student -> student[0],
                            student -> Integer.parseInt(student[1]),
                            (prev, next) -> (prev + next) / 2,
                            () -> new HashMap<>()
                    ));
            return map.entrySet().stream()
                    .filter(student -> student.getValue() >= n)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
