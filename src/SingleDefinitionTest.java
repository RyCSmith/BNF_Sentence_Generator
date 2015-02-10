package sentenceGenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Ryan Smith
 */
public class SingleDefinitionTest {
	SingleDefinition singleDef1;
	SingleDefinition singleDef2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    	singleDef1 = new SingleDefinition();
    	singleDef2 = new SingleDefinition();
    	singleDef1.add("<noun phrase>");
    	singleDef1.add("<verb>");
    	singleDef1.add("<pronoun>");
    	singleDef2.add("cat");
    }

    /**
     * Test method for {@link sentenceGenerator.SingleDefinition#toString()}.
     */
    @Test
    public final void testToString() {
        assertEquals("<noun phrase> <verb> <pronoun>", singleDef1.toString());
        assertEquals("cat", singleDef2.toString());
    }

}
