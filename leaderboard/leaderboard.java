import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class leaderboard{
    public void readScores() {
        String csvFile = "yourfile.csv";
        String[] data = {"T,80", "H,29", "S,3"};
        int nc = 2;
        String[][] sorted =new String[1000][nc];
        
        try{
	    BufferedReader reader = new BufferedReader(new FileReader("yourfile.csv"));
	    String line;
	    int row=0;
	    while((line=reader.readLine())!=null){
	    	String[] values = line.split(",");
                if (values.length == nc) {
                    sorted[row] = values;
                    row++;
                }
            }
            for(int i=0;i<row-1;i++)
            	for(int j=0;j<row-1-i;j++)
			if(Integer.parseInt(sorted[j][1])<Integer.parseInt(sorted[j+1][1])){
				String temp[]=sorted[j];
				sorted[j]=sorted[j+1];
				sorted[j+1]=temp;
			}
	    for(int i=0;i<3;i++){
	    	for(int j=0;j<2;j++)
	    		System.out.print(sorted[i][j]+" ");
	    	System.out.println();
	    }
	    reader.close();
	} catch (IOException e){
		e.printStackTrace();
	}
    }
    public void writeScores(String [] data){
    try {
            FileWriter fileWriter = new FileWriter(csvFile, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String value : data) {
                writer.write(value);
                writer.write(",");
                writer.newLine();
            }

            writer.close();
            fileWriter.close();

            System.out.println("Data added to the CSV file successfully.\n\n\n\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

