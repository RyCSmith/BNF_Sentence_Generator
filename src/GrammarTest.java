package sentenceGenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Ryan Smith
 */
public class GrammarTest {
	Grammar grammar;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    	grammar = new Grammar();
    }


    /**
     * Test method for {@link sentenceGenerator.Grammar#addRule(java.lang.String)}.
     */
    @Test
    public final void testAddRule() {
        grammar.addRule("<sentence> ::= <noun phrase> <verb phrase>");
        assertEquals("<noun phrase>", grammar.getDefinitions("<sentence>").get(0).get(0));
        assertEquals("<verb phrase>", grammar.getDefinitions("<sentence>").get(0).get(1));
        grammar.addRule("<verb phrase> ::= <intransitive verb> | <transitive verb> <noun phrase>");
        assertEquals("<intransitive verb>", grammar.getDefinitions("<verb phrase>").get(0).get(0));
        assertEquals("<noun phrase>", grammar.getDefinitions("<verb phrase>").get(1).get(1));
    }

    /**
     * Test method for {@link sentenceGenerator.Grammar#getDefinitions(java.lang.String)}.
     */
    @Test
    public final void testGetDefinitions() {
        assertNull(grammar.getDefinitions("<sentence>"));
        grammar.addRule("<sentence> ::= <noun phrase> <verb phrase>");
        assertNotNull(grammar.getDefinitions("<sentence>"));
        assertEquals(1, grammar.getDefinitions("<sentence>").size());
        assertEquals("<noun phrase>", grammar.getDefinitions("<sentence>").get(0).get(0));
        
    }

}
