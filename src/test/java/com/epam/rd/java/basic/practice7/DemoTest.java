package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.constants.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * @author D.Koretskyi on 02.09.2020.
 */
public class DemoTest {

    @Test
    public void demoTestPlaceholder() throws IOException {
        try {
            Demo.main(null);
        } catch (Exception e) {
            fail();
        }
        Assert.assertTrue(true);
        Files.delete(Paths.get(Constants.DOM_OUTPUT_XML_FILE));

    }

}