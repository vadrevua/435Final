package cmsc435Final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataCleanser {

	private static final String COMMA_DELIMITER = ",";
	static String[][] dataSet = new String[3588][941];
	static String[][] cDataSet; //= new String[3588][941];
	static int[] values = new int[941];
	public static void loadFile(){
		for(int a = 0; a <values.length; a++){
			values[a] = 9999999;
		}
		String line = null;
		BufferedReader br = null;

		int counter = 0;
		try
		{
			br = new BufferedReader(new FileReader("D:/Downloads/training(2).csv"));

			while ((line = br.readLine()) != null) 
			{
				dataSet[counter] = line.split(COMMA_DELIMITER);
				//System.out.println(counter);
				if(dataSet.length < 0 ){
					System.out.println("sup");
				}
				counter++;
			}

		}catch(Exception ee){
			ee.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(IOException ie){
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}		
	}

	public static void cleanse(int minimumCount){

		//if the column has more than minimumCount amount of 0's then we want to remove the column and update the file/array
		int nextDeletedColumn = 0;
		int counter = 0;
		for(int y = 0; y<941;y++){
			counter = 0;
			for(int x =0; x<3588; x++){
				if(dataSet[x][y].equals("0")){
				counter++;	
				}
				if(counter>minimumCount){
					break;
				}
			}
			
			if(counter<minimumCount) {
				values[nextDeletedColumn] = y;
				nextDeletedColumn++;
			}
			
			//counter = 0;
			//cleansedArrayCounter++;
		}
		
		cDataSet = new String[3588][nextDeletedColumn];
		
		for(int x = 0; x<3588;y++){
			for(int y = 0; y<941; x++){
				boolean set = false;
				int badColumns = 0;
				for(deletedColumn:values) {
					if(y>deletedColumn) {
						set = true;
						break;
					}
					if(y = deletedColumn) {
						badColumns++;
						break;
					}
				}
				if(set) {
					cDataSet[x][y-badColumns] = dataSet[x][y];
				}
			}

		}
		
	}


	public static void writeFile(){
		BufferedWriter bufferedWriter = null;
		try {

			File file = new File("C:/Users/Adi/Desktop/Fall 2017/435/Final Proj/cleansedData.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =0; x<2500; x++){
				for(int y = 0; y<941;y++){
					
					bufferedWriter.write(cDataSet[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
				System.out.println(x);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		loadFile();
		cleanse();
		writeFile();
	}

}
