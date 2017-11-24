import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class AndrewsDastardlyDataCleanser9000 {
  
  public static void main(String[] args) {
    
    try {
      
      String[] classes = new String[3587];
      File datafile = new File("C:/Users/Barack/Documents/CMSC_435/training(2).csv");
      Scanner scan = new Scanner(datafile);
      scan.useDelimiter(",|\n");
      String[] colHeaders = scan.nextLine().split(",");
      int numcol = colHeaders.length;
      double[][] data = new double[numcol-1][3587];
      int i = 0;
      
      while(scan.hasNextDouble()) {
        for(int j = 0; j < numcol-1; j++) {
          data[j][i] = scan.nextDouble();
        }
        classes[i] = scan.next();
        i++;
      }
      
      PrintWriter cleansedDataFile = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Barack/Documents/CMSC_435/cleansed_training.csv")));
      ArrayList<double[]> cleansedData = new ArrayList<double[]>();
      ArrayList<Integer> includedCol = new ArrayList<Integer>();
      
      for(i = 0; i < numcol-1; i++) {
        double[] col = data[i];
        int zeroCount = 0;
        for(int j = 0; j < col.length; j++) {
          if(col[j] == 0.0) zeroCount++;
          if(zeroCount >= 2000) break;
        }
        if(zeroCount < 2000) {
          cleansedData.add(col);
          includedCol.add(i);
        }
      }
      
      for(i = 0; i < colHeaders.length; i++) {
        if(includedCol.contains(i)) {
          cleansedDataFile.append(colHeaders[i]);
          if(i != colHeaders.length - 1) cleansedDataFile.append(",");
        }
      }
      cleansedDataFile.append(colHeaders[colHeaders.length - 1]);
      cleansedDataFile.append("\n");
      
      for(i = 0; i < 3587; i++) {
        for(int j = 0; j < cleansedData.size(); j++) {
          cleansedDataFile.append(Double.toString(cleansedData.get(j)[i]));
          cleansedDataFile.append(",");
        }
        cleansedDataFile.append(classes[i]);
        cleansedDataFile.append("\n");
      }
      
      cleansedDataFile.close();
      
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
  }
  
}