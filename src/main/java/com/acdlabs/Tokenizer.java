package com.acdlabs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Tokenizer {

    private static final char TAB_SYMBOL = '	';

    public static String[][] tokenize(List<String> lines) {
        return lines.stream().map(getNoRegexpTokenizer())
                .toArray(String[][]::new);
    }

    private static Function<String, String[]> getNoRegexpTokenizer() {
        return line -> {

            List<String> tokens = new ArrayList<>();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < line.length(); i++) {
                char current = line.charAt(i);

                if (current == TAB_SYMBOL) {

                    if (!sb.toString().isEmpty()) {
                        tokens.add(sb.toString());
                        sb = new StringBuilder();
                    }
                    tokens.add(String.valueOf(TAB_SYMBOL));


                } else {

                    sb.append(current);
                }

                if (i == line.length() - 1 && (!sb.toString().isEmpty())) {
                    tokens.add(sb.toString());
                    sb = new StringBuilder();

                }

            }

            return tokens.toArray(new String[0]);
        };
    }

}
