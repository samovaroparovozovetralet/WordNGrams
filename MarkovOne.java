package Projects.Duke4.InterfaceAbstract;

/**
 * Write a description of class MarkovZero here.
 *
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovOne extends AbstractMarkovModel{
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public HashMap<Character,ArrayList<Character>> alphabetFollower (String trainingText){
        
        HashMap<Character,ArrayList<Character>> alphabetList = new HashMap<Character,ArrayList<Character>>();
        
        for(int i = 0; i<trainingText.length()-1; i++){
            char currCharacter = trainingText.charAt(i);
            
            if (alphabetList.containsKey(currCharacter)){
                alphabetList.get(currCharacter).add(trainingText.charAt(i+1));
            }
            else{
                ArrayList<Character> temp = new ArrayList<Character>();
                temp.add(trainingText.charAt(i+1));
                alphabetList.put(currCharacter, temp);
            }
        }

        return alphabetList;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        HashMap<Character,ArrayList<Character>> alphabetMap = alphabetFollower(myText);
        
        int start = myRandom.nextInt(myText.length()-1);
        char currChar = myText.charAt(start);
        char nextChar = currChar;
        sb.append(currChar);

        for(int k=0; k < numChars-1; k++){
            int size = alphabetMap.get(currChar).size();
            if (size == 0){
                break;
            }
            int index = myRandom.nextInt(size);
            nextChar = alphabetMap.get(currChar).get(index);
            sb.append(nextChar);
            currChar = nextChar;
        }

        return sb.toString();
    }
}
