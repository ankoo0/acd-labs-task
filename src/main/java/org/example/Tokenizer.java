package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Tokenizer implements Function<String, String[]> {

    private static final char TAB_SYMBOL = '	';

    @Override
    public String[] apply(String line) {
//        line =line.substring(0,line.length());
//        if (line.startsWith(String.valueOf(TAB_SYMBOL)))

        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);

            if (current == TAB_SYMBOL) {

                if (!sb.toString().equals("")){
                    tokens.add(sb.toString());
                    sb = new StringBuilder();
                }
                tokens.add(String.valueOf(TAB_SYMBOL));


            } else {

                sb.append(current);
            }

            if (i == line.length() - 1) {
                if (!sb.toString().equals("")){
                    tokens.add(sb.toString());
                    sb = new StringBuilder();
                }

            }

        }

        return tokens.toArray(new String[0]);
    }
}
