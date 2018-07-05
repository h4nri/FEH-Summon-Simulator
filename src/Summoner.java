import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Summoner {
	
	private int pullsWithoutFocusOrFive, sessionPulls, totalPulls, redHeroes, blueHeroes, greenHeroes, colorlessHeroes;
	
	private double initialFocusRate, initialFiveRate, initialFourRate, initialThreeRate, currentFocusRate, currentFiveRate, currentFourRate, currentThreeRate, 
		focusRateIncrease, fiveRateIncrease, fourRateDecrease, threeRateDecrease;

	private List<String> redFocusList, redFiveList, redFourList, redThreeList, blueFocusList, blueFiveList, blueFourList, blueThreeList,
		greenFocusList, greenFiveList, greenFourList, greenThreeList, colorlessFocusList, colorlessFiveList, colorlessFourList, colorlessThreeList,
		focusList, fiveList, fourList, threeList, summonedFocusList, summonedFiveList, summonedFourList, summonedThreeList, preferredColors;
		
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
		
		preferredColors = new ArrayList<String>();
	}
	
	public int getSessionPulls() {
		return sessionPulls;
	}
	
	public double getCurrentFocusRate() {
		return currentFocusRate;
	}
	
	public double getCurrentFiveRate() {
		return currentFiveRate;
	}
	
	public double getCurrentFourRate() {
		return currentFourRate;
	}
	
	public double getCurrentThreeRate() {
		return currentThreeRate;
	}
	
	// TODO: Describe this method.
	public void setupBanner(String bannerName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Banners\\" + bannerName + "\\Heroes.txt"));
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
		
	 // System.out.println("Focus heroes: " + focusList);
		
		fiveList.addAll(redFiveList);
		fiveList.addAll(blueFiveList);
		fiveList.addAll(greenFiveList);
		fiveList.addAll(colorlessFiveList);
		
	 // System.out.println("5-star heroes: " + fiveList);
		
		fourList.addAll(redFourList);
		fourList.addAll(blueFourList);
		fourList.addAll(greenFourList);
		fourList.addAll(colorlessFourList);
		
	 // System.out.println("4-star heroes: " + fourList);
		
		threeList.addAll(redThreeList);
		threeList.addAll(blueThreeList);
		threeList.addAll(greenThreeList);
		threeList.addAll(colorlessThreeList);
		
	 // System.out.println("3-star heroes: " + threeList);
		
		// All colors are preferred at the beginning.
		preferredColors.add("red");
		preferredColors.add("blue");
		preferredColors.add("green");
		preferredColors.add("colorless");
		
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
	
	// TODO: Describe this method.
	public List<String>[] pull(int pulls) {
		List<String>[] listArray = new List[4];
		
		if (pulls < 1 || pulls > 5) {
			System.out.println("Error: Invalid number of pulls entered. Please enter a value from 1-5.");
		} else {
			boolean focusOrFiveSummoned = false;
			sessionPulls = 0;
			
			List newFocusList = new ArrayList<String>();
			List newFiveList = new ArrayList<String>();
			List newFourList = new ArrayList<String>();
			List newThreeList = new ArrayList<String>();
			listArray[0] = newFocusList;
			listArray[1] = newFiveList;
			listArray[2] = newFourList;
			listArray[3] = newThreeList;
			
			// Determines the rarity, hero, & color of all five orbs in a summoning session.
			String[] rarityArray = new String[5];
			String[] heroArray = new String[5];
			String[] colorArray = new String[5];
			
			for (int i = 0; i < 5; i++) {
				double determineRarity = Math.random();
				
				if ((0.0 <= determineRarity) && (determineRarity < currentFocusRate)) {
					rarityArray[i] = "focus";
					
					int determineHero = ThreadLocalRandom.current().nextInt(0, focusList.size());
					String hero = focusList.get(determineHero);
					heroArray[i] = hero;
					
					if (redFocusList.contains(hero) == true) {
						colorArray[i] = "red";
					} else if (blueFocusList.contains(hero) == true) {
						colorArray[i] = "blue";
					} else if (greenFocusList.contains(hero) == true) {
						colorArray[i] = "green";
					} else {
						colorArray[i] = "colorless";
					}
				} else if ((currentFocusRate <= determineRarity) && (determineRarity < (currentFocusRate + currentFiveRate))) {
					rarityArray[i] = "five";
					
					int determineHero = ThreadLocalRandom.current().nextInt(0, fiveList.size());
					String hero = fiveList.get(determineHero);
					heroArray[i] = hero;
					
					if (redFiveList.contains(hero) == true) {
						colorArray[i] = "red";
					} else if (blueFiveList.contains(hero) == true) {
						colorArray[i] = "blue";
					} else if (greenFiveList.contains(hero) == true) {
						colorArray[i] = "green";
					} else {
						colorArray[i] = "colorless";
					}
				} else if (((currentFocusRate + currentFiveRate) <= determineRarity) && (determineRarity < currentFocusRate + currentFiveRate + currentFourRate)) {
					rarityArray[i] = "four";
					
					int determineHero = ThreadLocalRandom.current().nextInt(0, fourList.size());
					String hero = fourList.get(determineHero);
					heroArray[i] = hero;
					
					if (redFourList.contains(hero) == true) {
						colorArray[i] = "red";
					} else if (blueFourList.contains(hero) == true) {
						colorArray[i] = "blue";
					} else if (greenFourList.contains(hero) == true) {
						colorArray[i] = "green";
					} else {
						colorArray[i] = "colorless";
					}
				} else {
					rarityArray[i] = "three";
					
					int determineHero = ThreadLocalRandom.current().nextInt(0, threeList.size());
					String hero = threeList.get(determineHero);
					heroArray[i] = hero;
					
					if (redThreeList.contains(hero) == true) {
						colorArray[i] = "red";
					} else if (blueThreeList.contains(hero) == true) {
						colorArray[i] = "blue";
					} else if (greenThreeList.contains(hero) == true) {
						colorArray[i] = "green";
					} else {
						colorArray[i] = "colorless";
					}
				}
			}
			
			System.out.println("Heroes: " + heroArray[0] + ", " + heroArray[1] + ", " + heroArray[2] + ", " + heroArray[3] + ", " + heroArray[4]);
			
			// Determines which heroes will be summoned based on color preferences.
			// TODO: Edit for priority.
			for (int i = 0; i < 5; i++) {
				if (sessionPulls != pulls) {
					if (preferredColors.contains(colorArray[i]) == true) {					
						if (rarityArray[i].equals("focus")) {
							summonedFocusList.add(heroArray[i]);
							newFocusList.add(heroArray[i]);
							focusOrFiveSummoned = true;
						} else if (rarityArray[i].equals("five")) {
							summonedFiveList.add(heroArray[i]);
							newFiveList.add(heroArray[i]);
							focusOrFiveSummoned = true;
						} else if (rarityArray[i].equals("four")) {
							summonedFourList.add(heroArray[i]);
							newFourList.add(heroArray[i]);
						} else {
							summonedThreeList.add(heroArray[i]);
							newThreeList.add(heroArray[i]);
						}			
						
						sessionPulls++;
					}
				}
			}
			
			// If none of the orbs are of a preferred color, then pull the first orb.
			// TODO: Edit for priority.
			if (sessionPulls == 0) {			
				if (rarityArray[0].equals("focus")) {
					summonedFocusList.add(heroArray[0]);
					newFocusList.add(heroArray[0]);
					focusOrFiveSummoned = true;
				} else if (rarityArray[0].equals("five")) {
					summonedFiveList.add(heroArray[0]);
					newFiveList.add(heroArray[0]);
					focusOrFiveSummoned = true;
				} else if (rarityArray[0].equals("four")) {
					summonedFourList.add(heroArray[0]);
					newFourList.add(heroArray[0]);
				} else {
					summonedThreeList.add(heroArray[0]);
					newThreeList.add(heroArray[0]);
				}
				
				sessionPulls++;
			}
			
			totalPulls = totalPulls + sessionPulls;
			System.out.println("Total pulls: " + totalPulls);

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
				pullsWithoutFocusOrFive = pullsWithoutFocusOrFive + sessionPulls;
				
				// Set current appearance rates based on how many pulls it has been since a focus or 5-star hero was summoned. 
				DecimalFormat df = new DecimalFormat("#.####");
				df.setRoundingMode(RoundingMode.HALF_UP);
				
				int rateChanges = Math.floorDiv(pullsWithoutFocusOrFive, 5);
				currentFocusRate = Double.parseDouble(df.format(initialFocusRate + (rateChanges * focusRateIncrease)));
				currentFiveRate =  Double.parseDouble(df.format(initialFiveRate + (rateChanges * fiveRateIncrease)));
				currentFourRate =  Double.parseDouble(df.format(initialFourRate - (rateChanges * fourRateDecrease)));
				currentThreeRate =  Double.parseDouble(df.format(initialThreeRate - (rateChanges * threeRateDecrease)));
			}
		}
		
		return listArray;
	}
	
	// Edits the contents of preferredColors based on whether preferredColors already contains the input or not.
	// Returns false if user is trying to remove the last preferred color.
	public Boolean changePreferredColors(String color) {
		if (preferredColors.contains(color)) {
			if (preferredColors.size() != 1) {
				preferredColors.remove(color);
			} else {
				return false;
			}
		} else {
			preferredColors.add(color);
		}
		
		return true;
	}
}
