package sentenceGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;

/**
 * This is my version of a CIT594 assignment to read in a BNF grammar
 * and produce sentences from that grammar.
 * @author Dave Matuszek
 * @author Ryan Smith
 */
public class SentenceGenerator {
    private Grammar grammar;
    private Random random = new Random();

    /**
     * Prompts the user for a file containing a BNF grammar, then
     * generates several sentences from that grammar.
     * 
     * @param args Unused.
     */
    public static void main(String[] args) {
        try {
            new SentenceGenerator().run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
        	//catches Grammar.sytaxError() if thrown
        	System.out.println("Syntax error in your file.");
        }
    }

    /**
     * Does the work of this class.
     * 
     * @throws IOException If an input exception occurs.
     */
    private void run() throws IOException {
    	//get file from user
    	BufferedReader bReader = getFileReader();
    	//create grammar from file
    	grammar = new Grammar(bReader);
    	grammar.print();
    	//generate sentence and print (x20)
    	for(int i = 0; i < 20; i++){
    		List<String> generatedString = generate("<sentence>");
    		printAsSentence(generatedString);
    	} 
    }
    
    /**
     * Expands the given term into a list of terminals. If the given
     * term is already a terminal, a list containing this single term
     * is returned.
     * 
     * @param term A terminal or nonterminal to expand into a list.
     * @return A list of terminals.
     */
    List<String> generate(String term) {
        List<String> expandList = new ArrayList<String>();
        //we get the list of definitions for the term and choose a random SingleDefinition
        SingleDefinition randomDef = (SingleDefinition) chooseRandomElement(grammar.getDefinitions(term));
        
        //if the first element begins with "<" we know it is nonterminal and call the method recursively for all pieces of the SingleDefinition
        if (randomDef.get(0).startsWith("<")){
        	for (String item : randomDef){
        		expandList.addAll(generate(item));
        	}
        	return expandList;
        }
        //otherwise, we've reached a terminal and add all elements of the SingleDefinition to the list and return
        else {
        	for (String item : randomDef){
        		expandList.add(item);
        	}
        	return expandList;
        }
        	
    }

    /**
     * Randomly choose and return one element from a list.
     * 
     * @param list The list from which the selection is to be made.
     * @return The randomly selected element.
     */
    private Object chooseRandomElement(List list) {
        return (list.get(random.nextInt(list.size())));
    }

    /**
     * Prints the given list of words as a sentence. The first word is
     * capitalized, and a period is printed at the end.
     * 
     * @param list The words to be printed.
     */
    //Uses StringBuilder to construct a formatted string from the list, then prints.
    void printAsSentence(List<String> list) {
    	StringBuilder formattedString = new StringBuilder();
        formattedString.append(Character.toUpperCase(list.get(0).charAt(0)));
        formattedString.append(list.get(0).substring(1));
        for (String word : list.subList(1, list.size())){
        	formattedString.append(" " + word);
        }
        formattedString.append(".");
        System.out.printf("%s\n", formattedString.toString());
    }
    
    /**
     * Prompts the user to choose a file, which should contain a BNF grammar.
     * 
     * @return The chosen file, or <code>null</code> if none is chosen.
     * @throws IOException 
     */
    //pops up JFileChooser and creates BufferedReader with path
    //BufferedReader requires FileReader which requires file path
    private BufferedReader getFileReader() {
        BufferedReader reader = null;
        String fileName;
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load which file?");
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file != null) {
                try {
					fileName = file.getCanonicalPath();
					reader = new BufferedReader(new FileReader(fileName));
				} 
                catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
                }
            }
        }
        return reader;
    }
}

