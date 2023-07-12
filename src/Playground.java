import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Arrays;

public class Playground {
	private Soldier[] search_space;
	private int gold_amount = 0;
	private int max_level = 0;
	private int pieces_per_level = 0;

	public Playground() throws FileNotFoundException {
		create_searchSpace();
	}

	public void dynamicProgrraming() {
		dynamicProgrammingApproach(this.gold_amount, search_space);
	}

	public void greedyProgramming() {
		greedyApproach(gold_amount, search_space); // Functions recreated to run on main without taking any parameters
	} // since input file and data inputs are not volatile. No need to use them on
		// main and violate encapsulation principle.

	public void randomApproach() {
		randomApproach(this.gold_amount, search_space);
	}

//==================================================================================================================================================================
	private void create_searchSpace() throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the gold amount ");
		gold_amount = sc.nextInt();
		while (gold_amount < 5 || gold_amount > 1200) {
			System.out.print("Please enter a valid gold amount.");
			gold_amount = sc.nextInt();
		}
		System.out.print("Enter the max level allowed ");
		max_level = sc.nextInt();
		while (max_level < 0 || max_level > 9) {
			System.out.print("Please enter a valid level value.");
			max_level = sc.nextInt();
		}
		System.out.print("Enter the number of available pieces per level ");
		pieces_per_level = sc.nextInt();
		while (pieces_per_level < 0 || pieces_per_level > 25) {
			System.out.print("Please enter a valid number");
			pieces_per_level = sc.nextInt();
		}
		sc.close();

		search_space = new Soldier[max_level * pieces_per_level];

		File file = new File("src/input_1.csv");
		if (!file.exists()) {
			System.out.println("File not found");
		}
		Scanner s = new Scanner(file);
		s.nextLine();

		int level = 0; // vertical
		int positionIn_1D_array = 0; // horizontal
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] split = line.split(",");
			if (level == max_level) {
				break;
			}

			if (split[1].equals("Pawn")) {
				if (level == 1) {
					continue;
				}
				search_space[positionIn_1D_array] = new Pawn(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			} else if (split[1].equals("Rook")) {
				if (level == 2) {
					continue;
				}
				search_space[positionIn_1D_array] = new Rook(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			} else if (split[1].equals("Archer")) {
				if (level == 3) {
					continue;
				}
				search_space[positionIn_1D_array] = new Archer(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			} else if (split[1].equals("Knight")) {
				if (level == 4) {
					continue;
				}
				search_space[positionIn_1D_array] = new Knight(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			} else if (split[1].equals("Bishop")) {
				if (level == 5) {
					continue;
				}
				search_space[positionIn_1D_array] = new Bishop(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			} else if (split[1].equals("War_ship")) {
				if (level == 6) {
					continue;
				}
				search_space[positionIn_1D_array] = new War_ship(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			} else if (split[1].equals("Siege")) {
				if (level == 7) {
					continue;
				}
				search_space[positionIn_1D_array] = new Siege(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			} else if (split[1].equals("Queen")) {
				if (level == 8) {
					continue;
				}
				search_space[positionIn_1D_array] = new Queen(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			} else if (split[1].equals("King")) {
				search_space[positionIn_1D_array] = new King(split[0], Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
			}

			positionIn_1D_array++;
			if (positionIn_1D_array != 0 && (positionIn_1D_array % pieces_per_level) == 0) {
				level++;
			}

		}
		s.close();
	}

	private void randomApproach(int gold, Soldier searchspace[]) {
		Random random = new Random();
		int expense = 0;
		int combat_power = 0;
		for (int i = 0; i < max_level; i++) {
			int m = i * pieces_per_level;
			int randomNumber = random.nextInt(pieces_per_level) + m;
			if (searchspace[randomNumber].getCost() + expense <= gold) {
				expense = searchspace[randomNumber].getCost() + expense;
				combat_power = combat_power + searchspace[randomNumber].getAttackPoint();
				searchspace[randomNumber].unitInfo();
			}
		}
		System.out.println();
		System.out.println("Total combat power : " + combat_power);
		System.out.println("Expense : " + expense);
	}

	private void greedyApproach(int gold, Soldier searchspace[]) {
		int expense = 0;
		int combat_power = 0;
		Soldier levelarray[] = new Soldier[max_level];
		int cnt = 0;
		

		for (int i = 0; i < max_level; i++) {
			Soldier selectedUnit_forLevel = null;
			int current_ratio = Integer.MIN_VALUE;
			int m = i * pieces_per_level;

			for (int j = m; j < pieces_per_level + m; j++) {
				if (gold >= searchspace[j].getCost()) {
					if (searchspace[j].getAttackPoint() / searchspace[j].getCost() > current_ratio) {
						current_ratio = searchspace[j].getAttackPoint() / searchspace[j].getCost();
						selectedUnit_forLevel = searchspace[j];
					}
				}
			}

			if (selectedUnit_forLevel != null) {
				levelarray[cnt] = selectedUnit_forLevel;
				combat_power = combat_power + selectedUnit_forLevel.getAttackPoint();
				expense = expense + selectedUnit_forLevel.getCost();
				gold = gold - selectedUnit_forLevel.getCost();
				cnt++;
			}
		}

		for (int i = 0; i < levelarray.length; i++) {
			if (levelarray[i] != null) {
				levelarray[i].unitInfo();
			} else {
				continue;
			}
		}
		System.out.println();
		System.out.println("Total combat power : " + combat_power);
		System.out.println("Expense : " + expense);

	}

	private void dynamicProgrammingApproach(int gold, Soldier searchspace[]) {
		int i, w;
		int values[] = new int[pieces_per_level];
		int K[][] = new int[max_level + 1][gold + 1];
		Soldier soldiermap[][] = new Soldier[max_level + 1][gold + 1]; // Map to use backtracking.

// Build table K[][] in bottom up manner
		for (i = 0; i <= max_level; i++) {
			for (w = 0; w <= gold; w++) {
				if (i == 0 || w == 0) {
					K[i][w] = 0;
					soldiermap[i][w] = null;
				}

				 else {
					values = new int[pieces_per_level];
					Soldier soldiers[] = new Soldier[pieces_per_level];
					int m = (i - 1) * pieces_per_level + 1;
					int cnt = 0;
					for (int j = (i - 1) * pieces_per_level + 1; j < m + pieces_per_level; j++) {

						if (searchspace[j - 1].getCost() <= w) {
							values[cnt] = K[i - 1][w - searchspace[j - 1].getCost()]
									+ searchspace[j - 1].getAttackPoint();
							soldiers[cnt] = searchspace[j - 1];
							cnt++;
							// An extended version of max value finding in the one-dimensional knapsack
							// problem.
						}
						else if (searchspace[j - 1].getCost() > w) {
							K[i][w] = K[i - 1][w];
							soldiermap[i][w] = soldiermap[i - 1][w];
						}
					}
                    
					int local_max = Arrays.stream(values).max().getAsInt();
					K[i][w] = Math.max(K[i - 1][w], local_max);
					int max = Integer.MIN_VALUE;
					int index = -1;
					for (int f = 0; f < values.length; f++) {
						if (max < values[f]) {
							max = values[f];
							index = f;
						}
					}
					soldiermap[i][w] = soldiers[index];

				}
			}
			// Define m[i, w] to be the maximum value that can be attained with weight less
			// than or equal to w using items up to i.
			// or pieces_per_level players available as choices for some role
			// i (with c_i_j being the cost of choice j and p_i_j being the points),
			/*
			 * m[i, c] = max(m[i-1, c], m[i-1, c-c_i_1] + p_i_1 if c_i_1 <= c, otherwise 0,
			 * m[i-1, c-c_i_2] + p_i_2 if c_i_2 <= c, otherwise 0, ... m[i-1, c-c_i_n] +
			 * p_i_n if c_i_n <= c, otherwise 0)
			 */

		}
		int res = K[max_level][gold];
		int expense = 0;
		w = gold;
		for (i = max_level; i > 0 && res > 0; i--) {

			// either the result comes from the top
			// (K[i-1][w]) or from (val[i-1] + K[i-1]
			// [w-wt[i-1]]) as in Knapsack table. If
			// it comes from the latter one/ it means
			// the item is included.
			if (res == K[i - 1][w])
				continue;
			else {

				// This item is included.

				soldiermap[i][w].unitInfo();
				expense = expense + soldiermap[i][w].getCost();
				res = res - soldiermap[i][w].getAttackPoint();
				w = w - soldiermap[i][w].getCost();

			}
		}
		System.out.println("\nTotal combat power : " + K[max_level][gold]);
		System.out.println("Expense : " + expense);

	}
}
