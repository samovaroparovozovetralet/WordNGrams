package Projects.Duke4.InterfaceAbstract;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovN extends AbstractMarkovModel{
    private String myText;
    private Random myRandom;
    private int n;
    private HashMap<String,ArrayList<Character>> myMap;

    public MarkovN(int n) {
        myRandom = new Random();
        myMap = new HashMap<String,ArrayList<Character>>();
        this.n = n;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    @Override
    public void setTraining(String s){
        myText = s.trim();
        myMap.clear();
        myMap = alphabetFollower(myText);
    }

    public HashMap<String,ArrayList<Character>> alphabetFollower (String trainingText){
        
        HashMap<String,ArrayList<Character>> alphabetList = new HashMap<String,ArrayList<Character>>();
        
        for(int i = 0; i<trainingText.length()-n; i++){
            String currString = trainingText.substring(i, i+n);
            
            if (alphabetList.containsKey(currString)){
                alphabetList.get(currString).add(trainingText.charAt(i+n));
            }
            else{
                ArrayList<Character> temp = new ArrayList<Character>();
                temp.add(trainingText.charAt(i+n));
                alphabetList.put(currString, temp);
            }
        }

        

        return alphabetList;
    }

    public void printHashMapInfo(){
        int max = 0;
        String keyWithMax;
        ArrayList<String> keysWithMax = new ArrayList<String>();
        for (String key:myMap.keySet()){
            if (myMap.get(key).size()>max){
                max = myMap.get(key).size();
                keyWithMax = key;
                keysWithMax.clear();
                keysWithMax.add(key);
            }
            if (myMap.get(key).size() == max && !keysWithMax.contains(key)){
                keysWithMax.add(key);
            }
        }

        System.out.println("Map info:");
        System.out.println("####################");
        // print map itself
        if(myMap.size() < 50){
            for(String key : myMap.keySet()){
                ArrayList<Character> currArray = myMap.get(key);
                System.out.print(key + " ==> ");
                if(currArray.size()< 10){
                    for(int i = 0; i<currArray.size(); i++){    
                        System.out.print(currArray.get(i) + " ");
                    }
                    System.out.println();
                }
                else{
                    System.out.println(currArray.size() + " character");
                }
            }
            System.out.println();

        }

        // print number of keys in map
        System.out.println("Keys in map: " + myMap.size());
        System.out.println();
        // print the size of largest Array
        System.out.println("Largest array size : " + max);
        System.out.println();
        // print keys that have maximum size value
        System.out.println("Keys for largest array: ");
        for(String key : keysWithMax){
            System.out.println(key);
        }
        System.out.println();
        System.out.println("####################");
    }   
    

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        if (myText.length() < n){
            return myText;
        }
        StringBuilder sb = new StringBuilder();
        
        int start = myRandom.nextInt(myText.length()-n);
        StringBuilder currString = new StringBuilder();
        char nextChar = ' ';
        currString.append(myText.substring(start, start+n));
        sb.append(currString.toString());

        for(int k=0; k < numChars-n; k++){
            if(!myMap.containsKey(currString.toString())){
                break;
            }
            int size = myMap.get(currString.toString()).size();
            if (size == 0){
                break;
            }
            int index = myRandom.nextInt(size);
            nextChar = myMap.get(currString.toString()).get(index);
            currString.append(nextChar);
            currString.deleteCharAt(0);
            sb.append(nextChar);
        }

        return sb.toString();
    }
}
