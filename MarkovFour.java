package Projects.Duke4.InterfaceAbstract;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovFour extends AbstractMarkovModel{
    private String myText;
    private Random myRandom;


    public MarkovFour() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public HashMap<String,ArrayList<Character>> alphabetFollower (String trainingText){
        
        HashMap<String,ArrayList<Character>> alphabetList = new HashMap<String,ArrayList<Character>>();
        
        for(int i = 0; i<trainingText.length()-4; i++){
            String currString = trainingText.substring(i, i+4);
            
            if (alphabetList.containsKey(currString)){
                alphabetList.get(currString).add(trainingText.charAt(i+4));
            }
            else{
                ArrayList<Character> temp = new ArrayList<Character>();
                temp.add(trainingText.charAt(i+4));
                alphabetList.put(currString, temp);
            }
        }

        return alphabetList;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        HashMap<String,ArrayList<Character>> alphabetMap = alphabetFollower(myText);
        
        int start = myRandom.nextInt(myText.length()-4);
        StringBuilder currString = new StringBuilder();
        char nextChar = ' ';
        currString.append(myText.substring(start, start+4));
        sb.append(currString.toString());

        for(int k=0; k < numChars-4; k++){
            if(!alphabetMap.containsKey(currString.toString())){
                break;
            }
            int size = alphabetMap.get(currString.toString()).size();
            if (size == 0){
                break;
            }
            int index = myRandom.nextInt(size);
            nextChar = alphabetMap.get(currString.toString()).get(index);
            currString.append(nextChar);
            currString.deleteCharAt(0);
            sb.append(nextChar);
        }

        return sb.toString();
    }
}