
import java.util.Scanner;

public class Backtracking_Submission {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int choice = 0;
		int leng = 0;

		Scanner s = new Scanner(System.in);

		while (choice != 3) {
			System.out.println("\n\nChoose one of the following options:");
			System.out.println("1 - Subset");
			System.out.println("2 - Permutation");
			System.out.println("3 - Exit");

			choice = s.nextInt();

			if (choice == 1) {
				System.out.println("\nChoose a number larger than 1");

				leng = s.nextInt();
				initSubset(leng);
			}

			if (choice == 2) {
				System.out.println("\nChoose a number larger than 1");

				leng = s.nextInt();
				initPermutation(leng);
			}
		}
	}

	public static void initSubset(int size) {
		int[] value = new int[size];
		subset(size, 0, value);
		System.out.println();
	}

	public static void subset(int size, int pos, int[] value) {
		if (pos >= size) {
			System.out.print("(");
			for (int x = 0; x < value.length - 1; x++) {
				if (value[x] == -1) {
					System.out.print("-,");
				} else {
					System.out.print("" + value[x] + ",");
				}
			}

			if (value[value.length - 1] == -1) {
				System.out.print("-) ");
			} else {
				System.out.print("" + value[value.length - 1] + ") ");
			}
			return;
		}

		value[pos] = pos + 1;
		subset(size, pos + 1, value);

		value[pos] = -1;
		subset(size, pos + 1, value);

	}

	public static void initPermutation(int size) {
		int[] value = new int[size];
		int[] visited = new int[size];
		for (int x = 0; x < size; x++) {
			value[x] = -1;
			visited[x] = 0;
		}
		permutation(size, 0, value, visited);

	}

	public static void permutation(int size, int pos, int[] value, int[] visited) {
		if (pos >= size) {
			System.out.print("(");
			for (int x = 0; x < value.length - 1; x++) {
				System.out.print("" + value[x] + ",");
			}
			System.out.print("" + value[value.length - 1] + ") ");

			return;
		}

		for (int i = 1; i < size + 1; i++) {

			if (visited[i - 1] == 0) {

				value[pos] = i;
				visited[i - 1] = 1;
				permutation(size, pos + 1, value, visited);
				visited[i - 1] = 0;

			}

		}

	}

}