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
	static String[][] cDataSet = new String[3588][941];
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

	public static void cleanse(){

		//if the column has more than 2000 0's then we want to remove the column and update the file/array
		int cleansedArrayCounter = 0;
		int counter = 0;
		for(int y = 0; y<941;y++){
			for(int x =0; x<3588; x++){
				if(dataSet[x][y].equals("0")){
				counter++;	
				}
				if(counter<2000){
					cDataSet[x][y] = dataSet[x][y];
				}
				else
				values[y] = cleansedArrayCounter;
				
			}
			counter = 0;
			cleansedArrayCounter++;
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
