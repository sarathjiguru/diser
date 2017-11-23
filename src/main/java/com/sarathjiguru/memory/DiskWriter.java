package com.sarathjiguru.memory;

import org.joda.time.DateTime;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

/**
 * Created by sarath on 22/11/17.
 */
public class DiskWriter {

    private final int timeout;
    private DateTime nextTime;
    private BufferedWriter bufferedWriter;

    public DiskWriter() throws IOException {
        this("/tmp/diser-data.txt");
    }

    public DiskWriter(String path) throws IOException {
        this.timeout = 5000;
        StandardOpenOption so = StandardOpenOption.CREATE;
        if (Files.exists(Paths.get(path))) {
            so = StandardOpenOption.APPEND;
        }
        bufferedWriter = Files.newBufferedWriter(Paths.get(path), so);
        this.nextTime = DateTime.now();
    }

    public void write(String commandOject) throws IOException {
        bufferedWriter.write(commandOject);
        bufferedWriter.newLine();
        if (nextTime.plus(this.timeout).isAfterNow()) {
            bufferedWriter.flush();
            bufferedWriter = Files.newBufferedWriter(Paths.get("/tmp/diser-data.txt"), StandardOpenOption.APPEND);
            nextTime = DateTime.now();
        }
    }

    public void close() throws IOException {
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
