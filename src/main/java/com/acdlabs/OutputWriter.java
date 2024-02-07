package com.acdlabs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputWriter {

    private static final String OUTPUT_FILE_NAME = "out.txt";


    public static void write(String[][] transformedInput){
        List<String> list = stringify(transformedInput);
        handleWriting(list);
    }

    private static List<String> stringify(String[][] array){
        return Arrays.stream(array).
                map(arr-> String.join("", arr))
                .collect(Collectors.toList());
    }

    private static void handleWriting(List<String> list){
        try (
                FileWriter fileWriter = new FileWriter(OUTPUT_FILE_NAME)) {
            for (String str : list) {
                fileWriter.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file!");
        }
    }



}
