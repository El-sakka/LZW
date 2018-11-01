import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Decompress {
    List<Integer> tags = new ArrayList<>();
    HashMap<Integer,String>dictionary = new HashMap<>();
    String word = "";

    public Decompress(List<Integer> tags) {
        this.tags = tags;

        System.out.println(tags.size());
    }

    public void deCompress(){
        String temp = "";
        int key = 128;
        int tag;
        for(int i=0;i<tags.size();i++){
            tag = tags.get(i);
         //   System.out.println(tag);
            if(i>0){
                if(tag>127){
                    // found  in dictionary
                    if(dictionary.containsKey(tag)){
                        String value = dictionary.get(tag);
                        word+=value;
                        temp += value.charAt(0);
                        dictionary.put(key++,temp);
                        temp=value;
                    }else{
                        // special case
                        String value = temp + temp.charAt(0);
                        word+=value;
                        dictionary.put(key++,value);
                        temp =value;
                    }

                }else{
                    char c = (char) tag;
                    temp += c;
                   // System.out.println(temp);
                    dictionary.put(key++,temp);
                    word+=c;
                    temp = c+"";
                }
            }else{
                // first char
                char first = (char)tag;
                temp = first+"";
                word+=temp;
            }
        }
    }

    public void printDic(){
        int key = 128;
        for(int i=0;i<dictionary.size();i++){
            System.out.println(dictionary.get(key++));
        }
    }

    public String getWord() {
        return word;
    }

    public void writeOnFile() throws IOException {
        FileWriter fileWriter = new FileWriter("result.txt");
        for(int i=0;i<word.length();i++){
            fileWriter.write(word.charAt(i));
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
