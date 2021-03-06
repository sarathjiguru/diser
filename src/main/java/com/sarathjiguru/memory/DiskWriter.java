package com.sarathjiguru.memory;

import org.joda.time.DateTime;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

/**
 * Created by sarath on 22/11/17.
 * Writes SET commands to file periodically.
 */
public class DiskWriter {

    private final int timeout;
    private final Path filePath;
    private DateTime nextTime;
    private BufferedWriter bufferedWriter;

    public DiskWriter() throws IOException {
        this("/tmp/diser-data.txt");
    }

    public DiskWriter(String path) throws IOException {
        this.timeout = 5000;
        StandardOpenOption so = StandardOpenOption.CREATE;
        this.filePath = Paths.get(path);
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        if (Files.exists(filePath)) {
            so = StandardOpenOption.APPEND;
        }
        bufferedWriter = Files.newBufferedWriter(filePath, so);
        this.nextTime = DateTime.now();
    }

    public void write(String commandOject) throws IOException {
        bufferedWriter.write(commandOject);
        bufferedWriter.newLine();
        if (nextTime.plus(this.timeout).isAfterNow()) {
            bufferedWriter.flush();
            bufferedWriter = Files.newBufferedWriter(this.filePath, StandardOpenOption.APPEND);
            nextTime = DateTime.now();
        }
    }

    public void close() throws IOException {
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
