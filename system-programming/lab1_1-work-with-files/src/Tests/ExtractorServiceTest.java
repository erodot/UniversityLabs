package Tests;

import Services.ExtractorService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ExtractorServiceTest {
    @Test
    public void noOneLetter() throws Exception {
        ArrayList<String> expectedOut = new ArrayList<>(); // Empty array
        assertEquals("'1234567890' must return empty array", expectedOut, ExtractorService.extractWords("1234567890"));
        assertEquals("'@#$%^&* ::;' must return empty array", expectedOut, ExtractorService.extractWords("@#$%^&* ::;"));
    }

    @Test
    public void emptyRow() throws Exception {
        ArrayList<String> expectedOut = new ArrayList<>(Arrays.asList("")); // Array with one empty string
        assertEquals("Empty row must return array with empty string", expectedOut, ExtractorService.extractWords(""));
    }

    // TODO: write remaining tests
}