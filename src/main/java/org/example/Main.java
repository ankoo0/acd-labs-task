package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL in = classloader.getResource("in.txt");

        Function<String, String[]> noRegexpTokenizer = s -> {
            List<String> tokens = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);
                if (current == '	') {
                    tokens.add(sb.toString());
                    sb = new StringBuilder();
                    tokens.add("	");
                } else {
                        sb.append(current);
                    }
                }
            tokens.add(sb.toString());


            return tokens.toArray(new String[0]);
        };
//       List<String[]> list = Files.lines(Paths.get(in.toURI())).map(l->l.split("((?<=\\t)|(?=\\t))")).collect(Collectors.toList());

        String[][] array = Files.lines(Paths.get(in.toURI()))
////                .map(l -> l.split("((?<=\\t)|(?=\\t))((?<=\\n)|(?=\\n))"))
         .map(noRegexpTokenizer)
                .toArray(String[][]::new);

//        System.out.println(Arrays.toString(noRegexpTokenizer.apply(Files.lines(Paths.get(in.toURI())).findFirst().get())));


        String[][] strings = new String[][]{
                new String[]{"2.2", "	", "12345q", "	", "69", "	", "-abg"},
                new String[]{"2.2", "	", "12345q", "	", "69", "	", "-asdf"}
        };
//        sort2DArrayBasedOnColumnNumber(strings);
//        System.out.println(Arrays.deepToString(array));
        System.out.println(array[0][1].length());
        Arrays.stream(array).forEach(arr-> System.out.println(Arrays.toString(arr)));
//
//        System.out.println(compareValues("13dn", "er"));

    }

    public static void sort2DArrayBasedOnColumnNumber(String[][] array) {
        Arrays.sort(array, (first, second) -> {
//            System.out.println(Arrays.toString(first) + " " + Arrays.toString(second));

            int firstLength = first.length;
            int secondLength = second.length;
            int min = Math.min(firstLength, secondLength);

            int idx = 0;

            int res = compareValues(first[idx], second[idx]);
            if (res == 0) {
                while (res == 0 && idx < min) {
                    System.out.println(first[idx] + "    " + second[idx]);
                    res = compareValues(first[idx], second[idx]);
                    idx++;
                }

            }
            if (res == 1) {
                return 1;
            } else {
                return -1;
            }
        });


    }

//    private static void swap(int firstIdx, int secondIdx, String[][]arr) {
//        String[] temp = arr[firstIdx];
//        arr[firstIdx]= arr[secondIdx];
//        arr[secondIdx] = temp;
//        return
//    }


    private static int compareValues(String first, String second) {
        boolean isFirstNumber = isNumber(first);
        boolean isSecondNumber = isNumber(second);
        if (isFirstNumber && isSecondNumber) {
            double num1 = Double.parseDouble(first);
            double num2 = Double.parseDouble(second);
            if (num1 == num2) {
                return 0;
            }
            if (num1 < num2)
                return 1;
            else
                return -1;
        } else {
            if (isFirstNumber) {
                return -1;
            }
            if (isSecondNumber) {
                return 1;
            }
            return second.compareTo(first);
        }

    }

    public static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
