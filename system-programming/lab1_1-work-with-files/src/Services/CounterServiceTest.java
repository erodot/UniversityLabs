package Services;

import org.junit.Test;

import static org.junit.Assert.*;

public class CounterServiceTest {
    @Test
    public void letterSequences() throws Exception {
        assertEquals("a must return 0", 0, CounterService.getLongestChain("a"));
        assertEquals("b must return 1", 1, CounterService.getLongestChain("b"));
        assertEquals("aa must return 0", 0, CounterService.getLongestChain("aa"));
        assertEquals("bb must return 2", 2, CounterService.getLongestChain("bb"));
        assertEquals("ab must return 1", 1, CounterService.getLongestChain("ab"));
        assertEquals("ba must return 1", 1, CounterService.getLongestChain("ba"));
        assertEquals("aaa must return 0", 0, CounterService.getLongestChain("aaa"));
        assertEquals("baa must return 1", 1, CounterService.getLongestChain("baa"));
        assertEquals("aba must return 1", 1, CounterService.getLongestChain("aba"));
        assertEquals("aab must return 1", 1, CounterService.getLongestChain("aab"));
        assertEquals("bca must return 2", 2, CounterService.getLongestChain("bca"));
        assertEquals("acb must return 2", 2, CounterService.getLongestChain("acb"));
        assertEquals("bcd must return 3", 3, CounterService.getLongestChain("bcd"));
        assertEquals("abba must return 2", 2, CounterService.getLongestChain("abba"));
        assertEquals("abbc must return 3", 3, CounterService.getLongestChain("abbc"));
        assertEquals("abbcabb must return 3", 3, CounterService.getLongestChain("abbcabb"));
        assertEquals("abbcabbc must return 3", 3, CounterService.getLongestChain("abbcabbc"));
        assertEquals("bbcabbcd must return 4", 4, CounterService.getLongestChain("bbcabbcd"));
        assertEquals("abbcabbcd must return 4", 4, CounterService.getLongestChain("abbcabbcd"));
        assertEquals("abbcabbcdaw must return 4", 4, CounterService.getLongestChain("abbcabbcdaw"));
        assertEquals("abbcabbcdd must return 5", 5, CounterService.getLongestChain("abbcabbcdd"));
        assertEquals("ccccabbcabbcd must return 4", 4, CounterService.getLongestChain("ccccabbcabbcd"));
    }

    @Test
    public void realWords() throws Exception {
        assertEquals("I must return 0", 0, CounterService.getLongestChain("I"));
        assertEquals("returned must return 2", 2, CounterService.getLongestChain("returned"));
        assertEquals("from must return 2", 2, CounterService.getLongestChain("from"));
        assertEquals("the must return 2", 2, CounterService.getLongestChain("the"));
        assertEquals("City must return 1", 1, CounterService.getLongestChain("City"));
        assertEquals("about must return 1", 1, CounterService.getLongestChain("about"));
        assertEquals("three must return 3", 3, CounterService.getLongestChain("three"));
        assertEquals("on must return 1", 1, CounterService.getLongestChain("on"));
        assertEquals("May must return 1", 1, CounterService.getLongestChain("May"));
        assertEquals("afternoon must return 2", 2, CounterService.getLongestChain("afternoon"));
        assertEquals("pretty must return 2", 2, CounterService.getLongestChain("pretty"));
        assertEquals("well must return 2", 2, CounterService.getLongestChain("well"));
        assertEquals("disgusted must return 2", 2, CounterService.getLongestChain("disgusted"));
        assertEquals("with must return 2", 2, CounterService.getLongestChain("with"));
        assertEquals("life must return 1", 1, CounterService.getLongestChain("life"));
    }

    @Test
    public void longWords() throws Exception {
        assertEquals("Pneumonoultramicroscopicsilicovolcanoconiosis must return 3", 3, CounterService.getLongestChain("Pneumonoultramicroscopicsilicovolcanoconiosis"));
        assertEquals("Pseudopseudohypoparathyroidism must return 2", 2, CounterService.getLongestChain("Pseudopseudohypoparathyroidism"));
        assertEquals("Floccinaucinihilipilification must return 2", 2, CounterService.getLongestChain("Floccinaucinihilipilification"));
        assertEquals("Antidisestablishmentarianism must return 3", 3, CounterService.getLongestChain("Antidisestablishmentarianism"));
        assertEquals("supercalifragilisticexpialidocious  must return 2", 2, CounterService.getLongestChain("supercalifragilisticexpialidocious"));
        assertEquals("Incomprehensibilities must return 3", 3, CounterService.getLongestChain("Incomprehensibilities"));
        assertEquals("Strengths must return 5", 5, CounterService.getLongestChain("Strengths"));
        assertEquals("Euouae must return 0", 0, CounterService.getLongestChain("Euouae"));
        assertEquals("Unimaginatively must return 1", 1, CounterService.getLongestChain("Unimaginatively"));
        assertEquals("honorificabilitudinitatibus must return 1", 1, CounterService.getLongestChain("honorificabilitudinitatibus"));
        assertEquals("tsktsk must return 6", 6, CounterService.getLongestChain("tsktsk"));
        assertEquals("uncopyrightable must return 3", 3, CounterService.getLongestChain("uncopyrightable"));
        assertEquals("subdermatoglyphic must return 2", 2, CounterService.getLongestChain("subdermatoglyphic"));
        assertEquals("sesquipedalianism must return 2", 2, CounterService.getLongestChain("sesquipedalianism"));
    }
}