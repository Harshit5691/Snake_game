import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Leaderboard{
    
    public static void main(String []args){
        String file="Highscore.csv";
        BufferedReader reader = null;
        String line="";
        try {
            reader=new BufferedReader(new FileReader(file));
            for(int i=0;i<6;i++){
                line=reader.readLine();
                String[] row = line.split(",");
                for(String index:row)
                    System.out.printf("%-10s",index);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                reader.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
