import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Compress {

    String word;
    HashMap<String,Integer>dic = new HashMap<>();
    List<Integer> tags = new ArrayList<>();


    public Compress(String word) {
        this.word = word;
    }

    public void initDic(){
        //int count= 0;
        for(int i=0;i<128;i++){
            dic.put((char)i+"",i);
        }
    }


    public void compress(){
        initDic();
        String temp ="";
        String tag="";
        int key= 128;
        for(int i=0;i<word.length();i++){
            temp+=word.charAt(i);
            if(!dic.containsKey(temp)){
                // which value dic is have
                //System.out.println(temp);

                dic.put(temp,key++);
                tag = temp.substring(0,temp.length()-1);
                tags.add(dic.get(tag));
                temp="";
                i--;
            }
            else{
                // last substring
                if(i == word.length()-1){
                    tags.add(dic.get(temp));
                }
            }
        }
    }

    public void printTags(){
        for(int i=0;i<tags.size();i++){
            System.out.println(tags.get(i));
        }
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void writeTagInFile() throws IOException {
        FileWriter fileWriter = new FileWriter("tags.txt");
        for(int i=0;i<tags.size();i++){
            fileWriter.write(String.valueOf(tags.get(i))+"\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
