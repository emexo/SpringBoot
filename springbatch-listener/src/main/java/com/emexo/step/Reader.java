package com.emexo.step;

import org.springframework.batch.item.ItemReader;

public class Reader implements ItemReader<String> {

    private String[] messages = { "Welcome to eMexo Technologies",
            "Welcome to Spring Batch Example",
            "We use H2 Database for this example" };

    private int count = 0;

    @Override
    public String read() throws Exception {


        if (count < messages.length) {
            return messages[count++];
        } else {
            count = 0;
        }
        return null;
    }

}
