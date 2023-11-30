package AntColonyOptimization;

import java.util.ArrayList;

import DiceCoefficient.DiceCoefficient;

public class AntColonyOptimization {

    public static void main(double[][] similarityMatrix) {
    	
		// double[][] similarityMatrix = {
        // { 0, 8, 0, 6 },
        // { 8, 0, 4, 0 },
        // { 0, 4, 0, 15 },
        // { 6, 0, 15, 0 },
        // };
    	
        int length = similarityMatrix.length;
        ArrayList<Route> Routs = new ArrayList<Route>();
        String comb = Combinations.Combinations(length);

        double[][] combinations = new double[length][length];
        combinations = combParser(comb.trim(), length);
        int Count = 0;
        long startTimeForACO = System.nanoTime();
        for (double[] is : combinations) {
            double rout[] = new double[is.length];
            double avgScore = 0;
            for (int i = 0; i < is.length; i++) {
            	if (similarityMatrix[Count][(int) is[i]] > avgScore) {
                    avgScore = avgScore + similarityMatrix[Count][(int) is[i]];
                    int div = i + 1;
                    avgScore = avgScore / div;
                    rout[i] = is[i];
                } else {
                    rout[i] = 32165;
                }

            }
            avgScore = avgScore / is.length;
            Routs.add(new Route(rout, avgScore, Count + 1, null, 0));
            Count++;
        }
        long endTimeForACO = System.nanoTime();
        long durationForACO = endTimeForACO - startTimeForACO;
        System.out.println("Start time Ant Colony Optimization::: " + startTimeForACO + " nano seconds");
		System.out.println("End time for Ant colony Optimization::: " + endTimeForACO + " nano seconds");
        System.out.println("Runtime for Ant Colony Optimization::: " + durationForACO + " nano seconds");
		

        
		
		
        for (Route rt : Routs) {

            double[] rt_arr = rt.getElements();
            for (int i = 0; i < rt_arr.length; i++) {
               // System.out.print(rt_arr[i] + " ");
            }
        }
        
        DiceCoefficient.DiceCoefficientCal(Routs);
    }

    public static double[][] combParser(String comb, int length) {
        comb = comb.trim();
        double[][] combinations = new double[length][length];
        String Lines[] = comb.split("\n");
        for (int i = 0; i < Lines.length; i++) {
            String Elemenths[] = Lines[i].split(",");
            for (int j = 0; j < Elemenths.length; j++) {
                combinations[i][j] = Integer.parseInt(Elemenths[j]);
            }
        }
        return combinations;
    }

}
