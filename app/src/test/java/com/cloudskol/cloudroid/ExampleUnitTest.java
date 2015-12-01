package com.cloudskol.cloudroid;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testRemoveSlash() {
        String value = "/g23cs30dCMiG4ldaoVNP1ucjs6.jpg";
        if (value.startsWith("/")) {
            value.substring(value.indexOf("/") + 1);
        }

        System.out.println("Final value: " + value);
    }
}