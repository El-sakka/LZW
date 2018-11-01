import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gui {
    private JButton compressBtn;
    private JButton deCompressBtn;

    private JFrame frame;

    public Gui() {
        frame = new JFrame("LzW");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //compressBtn
        frame.setLayout(new FlowLayout());
        frame.add(compressBtn);
        frame.add(deCompressBtn);
        frame.setVisible(true);


    }

    void CompressButton() throws IOException {


        // read file
        FileReader fileReader = new FileReader("text.txt");
        String word="";
        int c = 0;
        while((c = fileReader.read() )!= -1){
            word += (char) c ;
        }

        Compress compress = new Compress(word);

        compressBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compress.compress();
                try {
                    compress.writeTagInFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        deCompressBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> list = new ArrayList<>();

                try {
                    FileReader fileReader1 = new FileReader("tags.txt");
                    int c = 0;
                    String s ="";
                    while((c = fileReader1.read() )!= -1){
                        if(c != '\n'){
                            s += (char)c;

                        }else if (c == '\n'){
                          //  System.out.println(s);
                            list.add(Integer.valueOf(s));
                            s = "";
                        }

                        //list.add(Integer.valueOf(c));
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Decompress decompress = new Decompress(list);
                decompress.deCompress();

                try {
                    decompress.writeOnFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }
}
