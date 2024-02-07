package com.acdlabs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {

    File file;

    public InputReader(File file) throws FileNotFoundException {
        if (file.exists()) {
            this.file = file;
        } else {
            throw new FileNotFoundException("File not found in the directory.");
        }
    }

    public List<String> getLines() {
        try {
            return Files.lines(file.toPath()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Can't obtain lines from file!");
        }
    }
}
