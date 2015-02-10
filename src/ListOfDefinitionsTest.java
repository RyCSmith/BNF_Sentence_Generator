package sentenceGenerator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Ryan Smith
 */
public class ListOfDefinitionsTest {
	ListOfDefinitions listDef;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    	listDef = new ListOfDefinitions();
    	SingleDefinition singleDef1 = new SingleDefinition();
    	SingleDefinition singleDef2 = new SingleDefinition();
    	singleDef1.add("<noun phrase>");
    	singleDef1.add("<verb>");
    	singleDef1.add("<pronoun>");
    	singleDef2.add("cat");
    	listDef.add(singleDef1);
    	listDef.add(singleDef2);
    }

    /**
     * Test method for {@link sentenceGenerator.ListOfDefinitions#toString()}.
     */
    @Test
    public final void testToString() {
        assertEquals("<noun phrase> <verb> <pronoun> | cat", listDef.toString());
    }

}
