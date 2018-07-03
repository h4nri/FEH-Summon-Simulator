import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Summoner {
	
	private int pullsWithoutFocusOrFive, totalPulls, redHeroes, blueHeroes, greenHeroes, colorlessHeroes;
	
	private double initialFocusRate, initialFiveRate, initialFourRate, initialThreeRate, currentFocusRate, currentFiveRate, currentFourRate, currentThreeRate, 
		focusRateIncrease, fiveRateIncrease, fourRateDecrease, threeRateDecrease;

	private List<String> redFocusList, redFiveList, redFourList, redThreeList, blueFocusList, blueFiveList, blueFourList, blueThreeList,
		greenFocusList, greenFiveList, greenFourList, greenThreeList, colorlessFocusList, colorlessFiveList, colorlessFourList, colorlessThreeList,
		focusList, fiveList, fourList, threeList, summonedFocusList, summonedFiveList, summonedFourList, summonedThreeList;
		
	public Summoner() {		
		redFocusList = new ArrayList<String>();
		redFiveList = new ArrayList<String>();
		redFourList = new ArrayList<String>();
		redThreeList = new ArrayList<String>();
		blueFocusList = new ArrayList<String>();
		blueFiveList = new ArrayList<String>();
		blueFourList = new ArrayList<String>();
		blueThreeList = new ArrayList<String>();
		greenFocusList = new ArrayList<String>();
		greenFiveList = new ArrayList<String>();  
		greenFourList = new ArrayList<String>();
		greenThreeList = new ArrayList<String>();
		colorlessFocusList = new ArrayList<String>();
		colorlessFiveList = new ArrayList<String>();
		colorlessFourList = new ArrayList<String>();
		colorlessThreeList = new ArrayList<String>();
		
		focusList = new ArrayList<String>();
		fiveList = new ArrayList<String>(); 
		fourList = new ArrayList<String>(); 
		threeList = new ArrayList<String>(); 
		
		summonedFocusList = new ArrayList<String>();
		summonedFiveList = new ArrayList<String>();
		summonedFourList = new ArrayList<String>();
		summonedThreeList = new ArrayList<String>();
	}
	
	// TODO: Describe this method.
	public void pull(int pulls) {
		if (pulls < 1 || pulls > 5) {
			System.out.println("Error: Invalid number of pulls entered. Please enter a value from 1-5.");
		} else {
			int counter = 1;
			boolean focusOrFiveSummoned = false;
			
			while (counter <= pulls) {
				double determineRarity = Math.random();
				System.out.println("determineRarity: " + determineRarity);
				if ((0.0 <= determineRarity) && (determineRarity < currentFocusRate)) {
					int determineHero = ThreadLocalRandom.current().nextInt(0, focusList.size());
					summonedFocusList.add(focusList.get(determineHero));
					focusOrFiveSummoned = true;
				} else if ((currentFocusRate <= determineRarity) && (determineRarity < (currentFocusRate + currentFiveRate))) {
					int determineHero = ThreadLocalRandom.current().nextInt(0, fiveList.size());
					summonedFiveList.add(fiveList.get(determineHero));
					focusOrFiveSummoned = true;
				} else if (((currentFocusRate + currentFiveRate) <= determineRarity) && (determineRarity < currentFocusRate + currentFiveRate + currentFourRate)) {
					int determineHero = ThreadLocalRandom.current().nextInt(0, fourList.size());
					summonedFourList.add(fourList.get(determineHero));
				} else {
					int determineHero = ThreadLocalRandom.current().nextInt(0, threeList.size());
					summonedThreeList.add(threeList.get(determineHero));
				}
				counter++;
			}

			System.out.println("Summoned focus heroes: " + summonedFocusList);
			System.out.println("Summoned five heroes: " + summonedFiveList);
			System.out.println("Summoned four heroes: " + summonedFourList);
			System.out.println("Summoned three heroes: " + summonedThreeList);
			
			// Change appearance rates based on whether or not a focus or 5-star hero was summoned.
			if (focusOrFiveSummoned == true) {
				pullsWithoutFocusOrFive = 0;
				currentFocusRate = initialFocusRate;
				currentFiveRate = initialFiveRate;
				currentFourRate = initialFourRate;
				currentThreeRate = initialThreeRate;
			} else {
				pullsWithoutFocusOrFive = pullsWithoutFocusOrFive + pulls;
				
				// Set current appearance rates based on how many pulls it has been since a focus or 5-star hero was summoned. 
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
	
	// TODO: Describe this method.
	private void setupBanner(String bannerName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(bannerName + "\\Heroes.txt"));
			String line;
			
			// Fills the color-grade lists (ex. redFocusList).
			if ((line = br.readLine()).equals("None") == false) 
				redFocusList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				redFiveList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				redFourList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				redThreeList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				blueFocusList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				blueFiveList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				blueFourList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				blueThreeList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				greenFocusList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				greenFiveList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				greenFourList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				greenThreeList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				colorlessFocusList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				colorlessFiveList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				colorlessFourList = Arrays.asList(line.split(","));
			if ((line = br.readLine()).equals("None") == false)
				colorlessThreeList = Arrays.asList(line.split(","));	
		
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		// Fills the grade lists (ex. focusList).
		focusList.addAll(redFocusList);
		focusList.addAll(blueFocusList);
		focusList.addAll(greenFocusList);
		focusList.addAll(colorlessFocusList);
		
		System.out.println("Focus heroes: " + focusList);
		
		fiveList.addAll(redFiveList);
		fiveList.addAll(blueFiveList);
		fiveList.addAll(greenFiveList);
		fiveList.addAll(colorlessFiveList);
		
		System.out.println("5-star heroes: " + fiveList);
		
		fourList.addAll(redFourList);
		fourList.addAll(blueFourList);
		fourList.addAll(greenFourList);
		fourList.addAll(colorlessFourList);
		
		System.out.println("4-star heroes: " + fourList);
		
		threeList.addAll(redThreeList);
		threeList.addAll(blueThreeList);
		threeList.addAll(greenThreeList);
		threeList.addAll(colorlessThreeList);
		
		System.out.println("3-star heroes: " + threeList);
		
		// Calculates and stores the total number of heroes for each color. Do I really need these variables?
		redHeroes = redFocusList.size() + redFiveList.size() + redFourList.size() + redThreeList.size();
		blueHeroes = blueFocusList.size() + blueFiveList.size() + blueFourList.size() + blueThreeList.size();
		greenHeroes = greenFocusList.size() + greenFiveList.size() + greenFourList.size() + greenThreeList.size();
		colorlessHeroes = colorlessFocusList.size() + colorlessFiveList.size() + colorlessFourList.size() + colorlessThreeList.size();
		
		// Legendary banners do not contain 5-star heroes (aside from the focus ones), and therefore have different appearance rates.
		if (fiveList.size() != 0) {
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
			initialFocusRate = 0.08;
			initialFiveRate = 0.0;
			initialFourRate = 0.58;
			initialThreeRate = 0.34;
			
			currentFocusRate = 0.08;
			currentFiveRate = 0.0;
			currentFourRate = 0.58;
			currentThreeRate = 0.34;
			
			focusRateIncrease = 0.005;
			fiveRateIncrease = 0.0;
			fourRateDecrease = 0.0032;
			threeRateDecrease = 0.0018;
		}
		
		pullsWithoutFocusOrFive = 0;
		totalPulls = 0;
	}
	
	public static void main(String[] args) {
		Summoner summoner = new Summoner();
		summoner.setupBanner("Scattered Fangs");
		summoner.pull(5);
	}
}
