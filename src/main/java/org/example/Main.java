package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        URL in = classloader.getResource("in.txt");

        File file = new File("./in.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File not found in the directory.");
        }
        Function<String, String[]> noRegexpTokenizer = new Tokenizer();
//       List<String[]> list = Files.lines(Paths.get(in.toURI())).map(l->l.split("((?<=\\t)|(?=\\t))")).collect(Collectors.toList());

//        String[]s =Files.lines(file.toPath()).toArray(String[]::new);

        String[][] array = Files.lines(file.toPath())
         .map(noRegexpTokenizer)
                .toArray(String[][]::new);


        sort2DArrayBasedOnColumnNumber(array);

        List<String> er =  Arrays.stream(array).map(arr-> Arrays.stream(arr).collect(Collectors.joining(""))).collect(Collectors.toList());


        try (FileWriter fileWriter = new FileWriter("out.txt")) {
            for (String str : er) {
                fileWriter.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sort2DArrayBasedOnColumnNumber(String[][] array) {
        Comparator<String> valComparator = new ValueComparator();
        Arrays.sort(array, (first, second) -> {

            int firstLength = first.length;
            int secondLength = second.length;
            int min = Math.min(firstLength, secondLength);

            for (int i = 0; i < min; i++) {
                int res = valComparator.compare(first[i], second[i]);
                if (res > 0) {
                    return 1;
                } else if (res<0){
                    return -1;
                }
            }

          return 0;
        });


    }

}
