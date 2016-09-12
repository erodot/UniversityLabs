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

    @Test
    public void pureTwoWords() throws Exception {
        String[] _expectedOut = {"Two","words"};
        ArrayList<String> expectedOut = new ArrayList<>(Arrays.asList(_expectedOut));

        assertEquals("'Two words' must return {'Two','words'}",expectedOut,ExtractorService.extractWords("Two words"));
    }

    @Test
    public void dirtyTwoWords() throws Exception {
        String[] _expectedOut = {"Two","words"};
        ArrayList<String> expectedOut = new ArrayList<>(Arrays.asList(_expectedOut));

        assertEquals("'   &Two words' must return {'Two','words'}",expectedOut,ExtractorService.extractWords("   &Two words"));
        assertEquals("'Two$^&%$words' must return {'Two','words'}",expectedOut,ExtractorService.extractWords("Two$^&%$words"));
        assertEquals("'Two words. ' must return {'Two','words'}",expectedOut,ExtractorService.extractWords("Two words. "));
        assertEquals("'654Two123 '''()words.,!' must return {'Two','words'}",expectedOut,ExtractorService.extractWords("654Two123 '''()words.,!"));
    }

    @Test
    public void pureThreeWords() throws Exception {
        String[] _expectedOut = {"Pure", "three", "words"};
        ArrayList<String> expectedOut = new ArrayList<>(Arrays.asList(_expectedOut));

        assertEquals("'Pure three words' must return {'Pure', 'three', 'words'}",expectedOut,ExtractorService.extractWords("Pure three words"));
    }

    @Test
    public void dirtyThreeWords() throws Exception {
        String[] _expectedOut = {"Dirty", "three", "words"};
        ArrayList<String> expectedOut = new ArrayList<>(Arrays.asList(_expectedOut));

        assertEquals("'Dirty. three  |!  words' must return {'Dirty', 'three', 'words'}",expectedOut,ExtractorService.extractWords("Dirty. three  |!  words"));
        assertEquals("'Dirty!@#$three_words----' must return {'Dirty', 'three', 'words'}",expectedOut,ExtractorService.extractWords("Dirty!@#$three_words----"));
        assertEquals("'=+~ Dirty_&?three words...' must return {'Dirty', 'three', 'words'}",expectedOut,ExtractorService.extractWords("=+~ Dirty_&?three words..."));
    }

    @Test
    public void manyWords() throws Exception {
        String[] _expectedOut = { "Knowing", "that", "millions", "of", "peoples", "around", "the", "world", "would", "be", "watching", "in", "person", "and", "on", "television", "and", "expecting", "great", "things", "from", "him", "at", "least", "one", "more", "gold", "medal"};
        ArrayList<String> expectedOut = new ArrayList<>(Arrays.asList(_expectedOut));

        String testedInput = "\"Knowing\", \"that\", 134millions\"    , \"of&^$%^\", \"';[[[[]peoples1111111101011\", \"around\",\\ \"the\", \"world\", \"would\",\"be \"watching\", \"in\", \"person\", \"and\", \"on\", \"television\", \"and\", \"expecting\", \"great\", \"things\", \"from\", \"him\", \"at\", \"least\", \"one\", \"more\", \"gold\", \"medal\"";

        assertEquals(expectedOut,ExtractorService.extractWords(testedInput));
    }
}