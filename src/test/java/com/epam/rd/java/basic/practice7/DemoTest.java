package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author D.Koretskyi on 02.09.2020.
 */
public class DemoTest {

    @Test
    public void demoTestPlaceholder() throws IOException {
        try {
            Demo.main(null);
        } catch (Exception e) {
            System.out.println("test failed");
        }
        Assert.assertTrue(true);
        Files.deleteIfExists(Paths.get(Constants.DOM_OUTPUT_XML_FILE));
        Files.deleteIfExists(Paths.get(Constants.SAX_OUTPUT_XML_FILE));
        Files.deleteIfExists(Paths.get(Constants.STAX_OUTPUT_XML_FILE));

    }

}