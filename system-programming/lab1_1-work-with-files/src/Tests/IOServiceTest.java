package Tests;

import Services.IOService;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class IOServiceTest {
    @Test
    public void normalRead() throws Exception {
        String[] _expected = {"I returned from the City about three o'clock on that",
                "May afternoon pretty well disgusted with life.",
                "I had been three months in the Old Country, and was",
                "fed up with it.",
                "If anyone had told me a year ago that I would have",
                "been feeling like that I should have laughed at him;",
                "but there was the fact.",
                "The weather made me liverish,",
                "the talk of the ordinary Englishman made me sick,",
                "I couldn't get enough exercise, and the amusements",
                "of London seemed as flat as soda-water that",
                "has been standing in the sun.",
                "'Richard Hannay,' I kept telling myself, 'you",
                "have got into the wrong ditch, my friend, and",
                "you had better climb out.'"};
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList(_expected));
        String fileName = "txt/tests.txt";

        assertEquals("File 'txt/tests.txt' must be valid",expected, IOService.readFile(fileName));
    }


    @Test(expected = FileNotFoundException.class)
    public void fileNotFoundException() throws Exception {
        IOService.readFile("txt/fileNotFound.txt");
    }
}