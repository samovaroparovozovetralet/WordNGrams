package Projects.Duke4.InterfaceAbstract;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public ArrayList<String> getFollows(String start){
        ArrayList<String> temp = new ArrayList<String>();
        int index = 0;
        int startLength = start.length();
        while(true){
            index = myText.indexOf(start, index);
            if(index == -1 || index == myText.length() - startLength){
                break;
            }
            temp.add(myText.substring(index, index + startLength));
        }
        return temp;
    } 

    public String toString(int numChars){
        String out = "----------------------------------";
        out.concat("/n");
        String[] words = getRandomText(numChars).split("//n");
        int psize = 0;
		for(int k=0; k < words.length; k++){
			out.concat(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				out.concat("/n");
				psize = 0;
			}
        }
        out.concat("----------------------------------");
        return out;
    }

    abstract public String getRandomText(int numChars);

}
