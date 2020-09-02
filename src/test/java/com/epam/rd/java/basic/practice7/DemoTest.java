package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author D.Koretskyi on 02.09.2020.
 */
public class DemoTest {

    @Test
    public void demoTestPlaceholder() {
        try {
            Demo.main(null);
        } catch (Exception e) {
            fail();
        }
        Assert.assertTrue(true);

    }

}