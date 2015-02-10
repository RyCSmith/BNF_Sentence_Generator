package sentenceGenerator;
import java.util.ArrayList;

/**
 * A <code>ListOfDefinitions</code> object consists of a list of alternatives
 * (each of which is a list of terminals and/or nonterminals), but
 * does not include the thing being defined.
 * 
 * @author Ryan Smith
 */
public class ListOfDefinitions extends ArrayList<SingleDefinition> {
    
    /**
     * Constructs an empty list of definitions.
     */
    ListOfDefinitions() {}
    
    /**
     * Returns a string containing the contents of this <code>ArrayList</code>,
     * separated by <code>" | "</code> symbols.
     * 
     * @see java.util.AbstractCollection#toString()
     */
    @Override
    public String toString() {
    	String contents = new String();
        for (SingleDefinition item : this){
        	contents = contents + item.toString() + " | ";
        }
        return contents.substring(0, contents.lastIndexOf("|")).trim();
    }
}
