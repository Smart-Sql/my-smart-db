package org.example.file;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFileUtil {

    public void myWriter(final String txt) throws IOException {
        File f = new File(this.getClass().getResource("").getPath());
        String path = f.getCanonicalPath();
        System.out.println(path);
        path = path + "/writer.log";

        List<String> lst = new ArrayList<>();
        lst.add(txt);
        //CharSink sink = Files.asCharSink(new File("src/main/resources/sample.txt"), Charsets.UTF_8, FileWriteMode.APPEND);
        CharSink sink = Files.asCharSink(new File(path), Charsets.UTF_8, FileWriteMode.APPEND);
        sink.writeLines(lst.stream());
    }
}









































