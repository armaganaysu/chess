import java.io.FileNotFoundException;
public class driver {
	public static void main(String[] args) throws FileNotFoundException {

		Playground play = new Playground();
		System.out.println("========= TRIAL #1 =========");
		System.out.println("Computer's Greedy Approach Results:");
		long startTime = System.currentTimeMillis();
		play.greedyProgramming();
		long endTime = System.currentTimeMillis();
		System.out.println("\nTotal Time :: " + (endTime - startTime) + "ms\n----------------------------");
		System.out.println("\nUser's Dynamic Programming Results:");
		startTime = System.currentTimeMillis();
		play.dynamicProgrraming();
		endTime = System.currentTimeMillis();
		System.out.println("\nTotal Time :: " + (endTime - startTime) + "ms");
		System.out.println("\n========= TRIAL #2 =========");
		System.out.println("Computer's Random Approach Results:");
		startTime = System.currentTimeMillis();
		play.randomApproach();
		endTime = System.currentTimeMillis();
		System.out.println("\nTotal Time :: " + (endTime - startTime) + "ms\n----------------------------");
		System.out.println("\nUser's Dynamic Programming Results:");
		startTime = System.currentTimeMillis();
		play.dynamicProgrraming();
		endTime = System.currentTimeMillis();
		System.out.println("\nTotal Time :: " + (endTime - startTime) + "ms");
	}
}
