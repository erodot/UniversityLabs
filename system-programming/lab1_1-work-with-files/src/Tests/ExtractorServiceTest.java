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
        assertEquals("'     ' must return empty array", expectedOut, ExtractorService.extractWords("     "));
    }

    @Test
    public void emptyRow() throws Exception {
        ArrayList<String> expectedOut = new ArrayList<>(); // Empty array
        assertEquals("Empty row must return empty array", expectedOut, ExtractorService.extractWords(""));
    }

    @Test
    public void oneWord() throws Exception {
        ArrayList<String> expectedOut = new ArrayList<>(Arrays.asList("MyWord")); // Empty array
        assertEquals("'MyWord' must return 'MyWord'", expectedOut, ExtractorService.extractWords("MyWord"));
    }

    @Test
    public void oneWordWithSymbolsInTheBeginningAndEnd() throws Exception {
        ArrayList<String> expectedOut = new ArrayList<>(Arrays.asList("MyWord")); // Empty array
        assertEquals("'   12345667890MyWord!@#$%^&*()?:;' must return 'MyWord'", expectedOut, ExtractorService.extractWords("   12345667890MyWord!@#$%^&*()?:;"));
    }

    // TODO: write remaining tests for two and more words
}