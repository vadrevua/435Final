import java.util.*;

public class confusioncalc {
	static int[][] matrix;
	public static void main(String[] args) {
		matrix = new int[][] {{1038,569,309,574},
							{0,2,1,0},
							{0,0,0,0},
							{204,145,115,630}};
		
		int[] numbers = getTPFPTNFN(0);
		double[] calc;
		double mccave = 0.0;
		
		int total = 0;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				total += matrix[i][j];
			}
		}
		
		System.out.println(total);
		/*
		for(int i = 0; i < 4; i++) {
			numbers = getTPFPTNFN(i);
			System.out.println(i);
			System.out.println(numbers [0]);
			System.out.println(numbers [1]);
			System.out.println(numbers [2]);
			System.out.println(numbers [3]);
		}
		*/
		for(int i = 0; i < 4; i++) {
			calc = getINFO(getTPFPTNFN(i));
			mccave += calc[3];
			System.out.println(i);
			System.out.println("Sensitivity = " + calc [0]);
			System.out.println("Specificity = " + calc [1]);
			System.out.println("PredictiveAcc = " + calc [2]);
			System.out.println("MCC = " + calc [3]);
			System.out.println();
		}
		System.out.println("MCC Ave. = " + (mccave / 4));
	}
	
	/*
	 * 0 = TP
	 * 1 = FP
	 * 2 = TN
	 * 3 = FN
	 */
	private static int[] getTPFPTNFN(int number) {
		int[] numbers = new int[4];
		
		numbers[0] = matrix[number][number];
		
		for(int i = 0; i < 4; i++) {
			if(i != number)
				numbers[1] += matrix[number][i];
			if(i != number)
				numbers[3] += matrix[i][number];
			for(int j = 0; j < 4; j++) {
				if(i != number && j != number)
					numbers[2] += matrix[i][j];
			}
		}
		
		return numbers;
	}
	
	/*
	 * 0 = Sensitivity
	 * 1 = Specificity
	 * 2 = PredictiveAcc
	 * 3 = MCC
	 */
	private static double[] getINFO(int[] numbers) {
		double[] calc = new double[4];
		
		//Sens
		calc[0] = 100.0 * (double) numbers[0] / (double) (numbers[0] + numbers[3]);
		
		//Spec
		calc[1] = 100.0 * (double) numbers[2] / (double) (numbers[2] + numbers[1]);
		
		//PredAcc
		calc[2] = 100.0 * (double) (numbers[0] + numbers[2]) / (double) (numbers[0] + numbers[1] + numbers[2] + numbers[3]);
		
		//MCC
		calc[3] = ((numbers[0] * numbers[2]) - (numbers[1] * numbers[3])) / ((Math.sqrt((double) (numbers[0] + numbers[1]) * (numbers[0] + numbers[3]))) 
																		* (Math.sqrt((double) (numbers[2] + numbers[1]) * (numbers[2] + numbers[3]))));
		
		return calc;
	}
}
