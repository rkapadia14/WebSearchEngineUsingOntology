package DiceCoefficient;

import java.util.ArrayList;

import AntColonyOptimization.Route;

public class DiceCoefficient {
	public static void DiceCoefficientCal(ArrayList<Route> Rout) {
		double DiceCoefficient[] = new double[Rout.size()];
		long dStartTime = System.nanoTime();;
		System.out.println("Dice Co-efficient Start time:"+System.nanoTime());
		for (Route rout2 : Rout) {
			double score2 = rout2.getAntScore();
			int loop2 = 0;
			for (Route rout3 : Rout) {
                            int match = 0;
                            
                            double ele_rout2[] = rout2.getElements();
                             double ele_rout3[] = rout3.getElements();
                            
                             
                             for (double d : ele_rout2) {
                                 for (double e : ele_rout3) {
                                     if(d==e){
                                         match++;
                                     }
                                 }
                            }
                             
                    double A = 2 *match;
                    A = A/(ele_rout2.length+ele_rout3.length);
				DiceCoefficient[loop2] = A;
				loop2++;
			}
			rout2.setDiceCoefficient(DiceCoefficient);
		}
		long dEndTime = System.nanoTime();;
		System.out.println("Dice Co-efficient End time:"+System.nanoTime());
		long durationDice = dEndTime - dStartTime;
		System.out.println("Dice Co-efficient Total Run time:"+durationDice+" nano seconds");
		ClusterAntColonyOptimization(Rout);
	}

	public static void ClusterAntColonyOptimization(ArrayList<Route> Rout) {
		for (Route rout : Rout) {
			double ClusterAntScore = 0;
			double DiceCoefficient[] = rout.getDiceCoefficient();
			for (int i = 0; i < DiceCoefficient.length; i++) {
				ClusterAntScore = ClusterAntScore + DiceCoefficient[i];
			}
			ClusterAntScore = ClusterAntScore / DiceCoefficient.length;
			rout.setClusterAntScore(ClusterAntScore);
		}
	}
}
