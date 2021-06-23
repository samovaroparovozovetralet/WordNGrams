package Projects.Duke4.InterfaceAbstract;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;
public class Tester {
    
    public void testHashMap(){
        MarkovN two = new MarkovN(5);
        // String source = "yes-this-is-a-thin-pretty-pink-thistle";
        FileResource fr = new FileResource();
        String source = fr.asString();
        source = source.replace('\n', ' ');
        two.setRandom(531);
        two.setTraining(source);
        two.printHashMapInfo();
    }
  }
