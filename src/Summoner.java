import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Summoner {
	
	private int pullsWithoutFocusOrFive, totalPulls, redHeroes, blueHeroes, greenHeroes, colorlessHeroes, focusHeroes, fiveHeroes, fourHeroes, threeHeroes; 
	
	private double initialFocusRate, initialFiveRate, initialFourRate, initialThreeRate, currentFocusRate, currentFiveRate, currentFourRate, currentThreeRate, 
		focusRateIncrease, fiveRateIncrease, fourRateDecrease, threeRateDecrease;
	
	private String[] redFocusArray, redFiveArray, redFourArray, redThreeArray, blueFocusArray, blueFiveArray, blueFourArray, blueThreeArray,
		greenFocusArray, greenFiveArray, greenFourArray, greenThreeArray, colorlessFocusArray, colorlessFiveArray, colorlessFourArray, colorlessThreeArray;

	private ArrayList<String> focusArrayList, fiveArrayList, fourArrayList, threeArrayList, 
		summonedFocusArrayList, summonedFiveArrayList, summonedFourArrayList, summonedThreeArrayList;
	
		
	public Summoner() {		
		focusArrayList = new ArrayList<>();
		fiveArrayList = new ArrayList<>(); 
		fourArrayList = new ArrayList<>(); 
		threeArrayList = new ArrayList<>(); 
		summonedFocusArrayList = new ArrayList<>();
		summonedFiveArrayList = new ArrayList<>();
		summonedFourArrayList = new ArrayList<>();
		summonedThreeArrayList = new ArrayList<>();
		
		setupBanner("Scattered Fangs");
	}
	
	public void pull(int pulls) {
		if (pulls < 0 || pulls > 5) {
			System.out.println("Error: Invalid number of pulls entered. Please enter a value from 0-5.");
		} else {
			boolean focusOrFiveSummoned = false;
	
			for (int i = 0; i <= pulls; i++) {
				double determineRarity = Math.random();
				System.out.println("generateRarity: " + determineRarity);
				if ((0.0 <= determineRarity) && (determineRarity < currentFocusRate)) {
					int determineHero = ThreadLocalRandom.current().nextInt(0, focusHeroes);
					summonedFocusArrayList.add(focusArrayList.get(determineHero));
					focusOrFiveSummoned = true;
				} else if ((currentFocusRate <= determineRarity) && (determineRarity < (currentFocusRate + currentFiveRate))) {
					int determineHero = ThreadLocalRandom.current().nextInt(0, fiveHeroes);
					summonedFiveArrayList.add(fiveArrayList.get(determineHero));
					focusOrFiveSummoned = true;
				} else if (((currentFocusRate + currentFiveRate) <= determineRarity) && (determineRarity < currentFocusRate + currentFiveRate + currentFourRate)) {
					int determineHero = ThreadLocalRandom.current().nextInt(0, fourHeroes);
					summonedFourArrayList.add(fourArrayList.get(determineHero));
				} else {
					int determineHero = ThreadLocalRandom.current().nextInt(0, threeHeroes);
					summonedThreeArrayList.add(threeArrayList.get(determineHero));
				}
			}
			
			System.out.println("Summoned focus heroes: " + summonedFocusArrayList);
			System.out.println("Summoned five heroes: " + summonedFiveArrayList);
			System.out.println("Summoned four heroes: " + summonedFourArrayList);
			System.out.println("Summoned three heroes: " + summonedThreeArrayList);
			
			if (focusOrFiveSummoned == true) {
				pullsWithoutFocusOrFive = 0;
				currentFocusRate = initialFocusRate;
				currentFiveRate = initialFiveRate;
				currentFourRate = initialFourRate;
				currentThreeRate = initialThreeRate;
			} else {
				pullsWithoutFocusOrFive = pullsWithoutFocusOrFive + pulls;
				
				// Set current appearance rates based on how many pulls it has been since a focus or 5-star hero was pulled. 
				int rateChanges = Math.floorDiv(pullsWithoutFocusOrFive, 5);
				currentFocusRate = initialFocusRate + (rateChanges * focusRateIncrease);
				currentFiveRate = initialFiveRate + (rateChanges * fiveRateIncrease);
				currentFourRate = initialFourRate - (rateChanges * fourRateDecrease);
				currentThreeRate = initialThreeRate - (rateChanges * threeRateDecrease);
			}
			
			totalPulls = totalPulls + pulls;
			
			System.out.println("Total pulls: " + totalPulls);
		}
	}
	
	// Changes the values of variables to represent the selected banner.
	private void setupBanner(String bannerName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(bannerName + "\\Heroes.txt"));
			
			// Populates the String[]s with the summonable heroes.
			redFocusArray = br.readLine().split(",");
			redFiveArray = br.readLine().split(",");
			redFourArray = br.readLine().split(",");
			redThreeArray = br.readLine().split(",");
			blueFocusArray = br.readLine().split(",");
			blueFiveArray = br.readLine().split(",");
			blueFourArray = br.readLine().split(",");
			blueThreeArray = br.readLine().split(",");
			greenFocusArray = br.readLine().split(",");
			greenFiveArray = br.readLine().split(",");
			greenFourArray = br.readLine().split(",");
			greenThreeArray = br.readLine().split(",");
			colorlessFocusArray = br.readLine().split(",");
			colorlessFiveArray = br.readLine().split(",");
			colorlessFourArray = br.readLine().split(",");
			colorlessThreeArray = br.readLine().split(",");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	

		// Calculates and stores total values.
		// Ex. determines the total number of focus heroes and stores that value in "focusHeroes".
		redHeroes = redFocusArray.length + redFiveArray.length + redFourArray.length + redThreeArray.length;
		blueHeroes = blueFocusArray.length + blueFiveArray.length + blueFourArray.length + blueThreeArray.length;
		greenHeroes = greenFocusArray.length + greenFiveArray.length + greenFourArray.length + greenThreeArray.length;
		colorlessHeroes = colorlessFocusArray.length + colorlessFiveArray.length + colorlessFourArray.length + colorlessThreeArray.length;
		focusHeroes = redFocusArray.length + blueFocusArray.length + greenFocusArray.length + colorlessFocusArray.length;
		fiveHeroes = redFiveArray.length + blueFiveArray.length + greenFiveArray.length + colorlessFiveArray.length;
		fourHeroes = redFourArray.length + blueFourArray.length + greenFourArray.length + colorlessFourArray.length;
		threeHeroes = redThreeArray.length + blueThreeArray.length + greenThreeArray.length + colorlessThreeArray.length;
		
		// Adds all focus heroes into "focusArrayList".
		for (int i = 0; i < focusHeroes; i++) {
			if (i < redFocusArray.length) {
				focusArrayList.add(redFocusArray[i]);
			} else if ((redFocusArray.length <= i) && (i < (redFocusArray.length + blueFocusArray.length))) {
				focusArrayList.add(blueFocusArray[i - redFocusArray.length]);
			} else if (((redFocusArray.length + blueFocusArray.length) <= i) && (i < (redFocusArray.length + blueFocusArray.length + greenFocusArray.length))) {
				focusArrayList.add(greenFocusArray[i - redFocusArray.length - blueFocusArray.length]);
			} else {
				focusArrayList.add(colorlessFocusArray[i - redFocusArray.length - blueFocusArray.length - greenFocusArray.length]);
			}
		}
		
		System.out.println("Focus heroes: " + focusArrayList);
		
		// Adds all five heroes into "fiveArrayList".
		for (int i = 0; i < fiveHeroes; i++) {
			if (i < redFiveArray.length) {
				fiveArrayList.add(redFiveArray[i]);
			} else if ((redFiveArray.length <= i) && (i < (redFiveArray.length + blueFiveArray.length))) {
				fiveArrayList.add(blueFiveArray[i - redFiveArray.length]);
			} else if (((redFiveArray.length + blueFiveArray.length) <= i) && (i < (redFiveArray.length + blueFiveArray.length + greenFiveArray.length))) {
				fiveArrayList.add(greenFiveArray[i - redFiveArray.length - blueFiveArray.length]);
			} else {
				fiveArrayList.add(colorlessFiveArray[i - redFiveArray.length - blueFiveArray.length - greenFiveArray.length]);
			}
		}
		
		System.out.println("5-star heroes: " + fiveArrayList);
		
		// Adds all four heroes into "fourArrayList".
		for (int i = 0; i < fourHeroes; i++) {
			if (i < redFourArray.length) {
				fourArrayList.add(redFourArray[i]);
			} else if ((redFourArray.length <= i) && (i < (redFourArray.length + blueFourArray.length))) {
				fourArrayList.add(blueFourArray[i - redFourArray.length]);
			} else if (((redFourArray.length + blueFourArray.length) <= i) && (i < (redFourArray.length + blueFourArray.length + greenFourArray.length))) {
				fourArrayList.add(greenFourArray[i - redFourArray.length - blueFourArray.length]);
			} else {
				fourArrayList.add(colorlessFourArray[i - redFourArray.length - blueFourArray.length - greenFourArray.length]);
			}
		}
		
		System.out.println("4-star heroes: " + fourArrayList);
		
		// Adds all three heroes into "threeArrayList".
		for (int i = 0; i < threeHeroes; i++) {
			if (i < redThreeArray.length) {
				threeArrayList.add(redThreeArray[i]);
			} else if ((redThreeArray.length <= i) && (i < (redThreeArray.length + blueThreeArray.length))) {
				threeArrayList.add(blueThreeArray[i - redThreeArray.length]);
			} else if (((redThreeArray.length + blueThreeArray.length) <= i) && (i < (redThreeArray.length + blueThreeArray.length + greenThreeArray.length))) {
				threeArrayList.add(greenThreeArray[i - redThreeArray.length - blueThreeArray.length]);
			} else {
				threeArrayList.add(colorlessThreeArray[i - redThreeArray.length - blueThreeArray.length - greenThreeArray.length]);
			}
		}
		
		System.out.println("3-star heroes: " + threeArrayList);
		
		// Legendary banners do not contain 5-star heroes (aside from the focus ones), and therefore have different appearance rates.
		if (fiveHeroes != 0) {
			initialFocusRate = 0.03;
			initialFiveRate = 0.03;
			initialFourRate = 0.58;
			initialThreeRate = 0.36;
			
			currentFocusRate = 0.03;
			currentFiveRate = 0.03;
			currentFourRate = 0.58;
			currentThreeRate = 0.36;
			
			focusRateIncrease = 0.0025;
			fiveRateIncrease = 0.0025;
			fourRateDecrease = 0.0019;
			threeRateDecrease = 0.0031;
		} else {
			// TODO: Fix these rates.
			initialFocusRate = 0.08;
			initialFiveRate = 0.0;
			initialFourRate = 0.58;
			initialThreeRate = 0.36;
			
			currentFocusRate = 0.08;
			currentFiveRate = 0.0;
			currentFourRate = 0.58;
			currentThreeRate = 0.36;
			
			focusRateIncrease = 0.005;
			fiveRateIncrease = 0.0;
			fourRateDecrease = 0.0019;
			threeRateDecrease = 0.0031;
		}
		
		pullsWithoutFocusOrFive = 0;
		totalPulls = 0;
	}
	
	public static void main(String[] args) {
		Summoner summoner = new Summoner();
		summoner.pull(5);
	}
}
