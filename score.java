package SnakeGame;
import java.io.FileReader; //idk maybe this works if not "import java.io.*"
import java.io.File;
import java.io.FileWriter;

//format is tanmay:69
//draw score/javafx method pls

public class Score extends Jpanel{
    private int scores=0;
    private String highscore="";

    //to view scores ingame
    //create some object that deals with shit
    obj.setColor(Color.WHITE); //racist
    obj.setFont(new Font("arial",Font.PLAIN,14)); //setting font
    obj.drawString("Scores: "+scores,780,30) //(text to be shown, x pos , y pos);
    //when collission happen do scores ++ 

    //highscore bs fu
    public String GetHighScore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try{
            readFile = new FileReader("highscore.dat")
            reader = new BufferedReader(readFile);
            return reader.readLine();
        }
        catch(Exception e){
            return "Nobody:0";
        }
        finally{
            try{
                if(reader!=null)
                    reader.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    if (highscore.equals("")){
        highscore=this.GetHighScore();
    }

    
public void CheckScore(){
    if(scores>Integer.parseInt(highscore.split(":")[1])){
        //while playing new record made
        String name= JOptionPane.showInputDialog("You set a new highscore, What is thy name?");//bhupendra jogi
        highscore= name+":"+scores;

        File scorefile = new File("highscore.dat");
        if(!scorefile.exists()){
            try{
                scorefile.createNewFile();
            }   catch(IOException e){
                e.printStackTrace();
            }
        }
        FileWriter writeFile = null;
        BufferedWriter writer = null;
        try{
            writeFile = new FileWriter(scorefile);
            write = new BufferedWriter(writeFile);
            writer.write(this.highscore);
        } catch(Exception e){

        }
        finally{
            try{
                if(writer!=null)
                    writer.close();
            }
            catch(Exception e){}
        }
    }
}

}