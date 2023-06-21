import java.util.ArrayList;
import java.util.Random;

public class App {
	static long RandomSeed = 1;
	static Random RandomGenerator = new Random(RandomSeed);

    private static ArrayList<Integer> generateIntArrayList(int howMany) {
		ArrayList<Integer> list = new ArrayList<Integer>(howMany);
		
		for(int i = 0; i < howMany; i++) {
			list.add(Integer.valueOf(RandomGenerator.nextInt()));
		}
		
		return list;
	}

	private static ArrayList<Integer> generateStrikeList(ArrayList<Integer> fromList, int howMany) {
		ArrayList<Integer> strikeList = new ArrayList<Integer>(howMany);
		int fromLast = fromList.size() - 1;
		
		for(int i = 0; i < howMany; i++) {
			strikeList.add(fromList.get(RandomGenerator.nextInt(fromLast)));
		}
		
		return strikeList;
	}

	private static ArrayList<Integer> generateRemoveList(ArrayList<Integer> fromList) {
		ArrayList<Integer> removeList = new ArrayList<Integer>(fromList.size()/2);
		
		for(int i = 0; i < fromList.size() / 2; i++) {
			removeList.add(fromList.get(i));
		}
		
		return removeList;
	}

	private static <T> int executeFinds(TwoFourTree coll, ArrayList<Integer> strikes) {
		boolean sentinel;
		int failures = 0;

		for (Integer e: strikes) {
			sentinel = coll.hasValue(e.intValue());
			if(sentinel == false) {
				failures++;
			}
		}
		
		if(failures > 0) {
			System.out.printf("(%,d missing) ", failures);
		}
		
		return 0;
	}

    public static void executeIntCase(int listSize, int strikeSize, boolean includeRemoves) {
		System.out.printf("CASE: %,d integers, %,d finds, %,d removals.  Generating...\n", listSize, strikeSize, strikeSize/2);

		ArrayList<Integer> intlist = generateIntArrayList(listSize);
		ArrayList<Integer> strikes = generateStrikeList(intlist, strikeSize);
        ArrayList<Integer> removeList = generateRemoveList(strikes);
		long start;
		long end;
		long ms;

        TwoFourTree theTree = new TwoFourTree();
		
        System.out.printf("  TwoFourTree ");

        start = System.currentTimeMillis();
        for (Integer e: intlist) {
            theTree.addValue(e.intValue());
        }
        end = System.currentTimeMillis();
        ms = end - start;
		System.out.printf("add: %,6dms  ", ms);

        start = System.currentTimeMillis();
        executeFinds(theTree, strikes);
        end = System.currentTimeMillis();
        ms = end - start;
		System.out.printf("find: %,6dms  ", ms);

        if(includeRemoves) {
            start = System.currentTimeMillis();
            for (Integer e: removeList) {
                // System.out.printf("----- delete %d from tree\n", e.intValue());
                /// theTree.printInOrder();
                theTree.deleteValue(e.intValue());
            }
            end = System.currentTimeMillis();
            ms = end - start;
            System.out.printf("del: %,6dms  ", ms);

            start = System.currentTimeMillis();
            executeFinds(theTree, strikes);
            end = System.currentTimeMillis();
            ms = end - start;
            System.out.printf("find: %,6dms  ", ms);
        }
	
		System.out.printf("\n");
        // theTree.printInOrder();
	}

    public static void main(String[] args) throws Exception {
        TwoFourTree tft = new TwoFourTree();
        tft.addValue(2);
        tft.addValue(3);
        tft.addValue(5);
        tft.addValue(7);
        tft.addValue(11);
        tft.addValue(13);
        tft.addValue(17);
        tft.addValue(19);
        tft.addValue(23);
        tft.addValue(29);
        tft.addValue(31);
        tft.addValue(37);
        tft.addValue(41);
        tft.addValue(43);
        tft.addValue(47);
        tft.addValue(53);
        tft.addValue(59);
        tft.addValue(67);
        tft.addValue(71);
        tft.addValue(73);
        tft.addValue(79);
        tft.addValue(83);
        tft.addValue(89);
        tft.addValue(97);

        if(tft.hasValue(2)){
            System.out.println("has value 2");
        }
        if(tft.hasValue(3)){
            System.out.println("has value 3");
        }
        if(tft.hasValue(5)){
            System.out.println("has value 5");
        }
        if(tft.hasValue(7)){
            System.out.println("has value 7");
        }
        if(tft.hasValue(11)){
            System.out.println("has value 11");
        }
        if(tft.hasValue(13)){
            System.out.println("has value 13");
        }
        if(tft.hasValue(17)){
            System.out.println("has value 17");
        }
        if(tft.hasValue(19)){
            System.out.println("has value 19");
        }
        if(tft.hasValue(23)){
            System.out.println("has value 23");
        }
        if(tft.hasValue(29)){
            System.out.println("has value 29" );
        }
        if(tft.hasValue(23)){
            System.out.println("has value 23");
        }
        if(tft.hasValue(79)){
            System.out.println("has value 79");
        }
        if(tft.hasValue(83)){
            System.out.println("has value 83");
        }
        if(tft.hasValue(89)){
            System.out.println("has value 89");
        }
        if(tft.hasValue(97)){
            System.out.println("has value 97");
        }





        System.out.println("Static test: first few prime numbers:");
        tft.printInOrder();
        // tft.deleteValue(37);
        // System.out.println("\nWithout 37:");
        // tft.printInOrder();
        // tft.deleteValue(73);
        // System.out.println("\nWithout 73:");
        // tft.printInOrder();
        // tft.deleteValue(97);
        // System.out.println("\nWithout 97:");
        // tft.printInOrder();

        executeIntCase(100, 20, true);
        executeIntCase(1000, 200, true);
        executeIntCase(10000, 2000, true);
        executeIntCase(100000, 20000, true);
        executeIntCase(1000000, 200000, true); 
        executeIntCase(10000000, 2000000, true);
    }
}
