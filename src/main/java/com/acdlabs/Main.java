package com.acdlabs;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class Main {

    private static final String INPUT_FILE_PATH = "./in.txt";

    public static void main(String[] args) throws IOException {

        ValueComparator comparator = new ValueComparator();
        CustomSorter sorter = new CustomSorter(comparator);

        File input = new File(INPUT_FILE_PATH);
        InputReader reader = new InputReader(input);

        List<String> lines = reader.getLines();

        String[][] array = Tokenizer.tokenize(lines);

        sorter.sort(array);

        OutputWriter.write(array);
    }

}
