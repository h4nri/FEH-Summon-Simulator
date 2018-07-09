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
	
	private int pullsWithoutFocusOrFive; 
	private int sessionPulls;
	private int totalPulls;
	
	private double initialFocusRate; 
	private double initialFiveRate;
	private double initialFourRate; 
	private double initialThreeRate; 
	private double currentFocusRate; 
	private double currentFiveRate;
	private double currentFourRate; 
	private double currentThreeRate; 
	private double focusRateIncrease; 
	private double fiveRateIncrease;
	private double fourRateDecrease; 
	private double threeRateDecrease;

	private List<String> redFocusList; 
	private List<String> redFiveList; 
	private List<String> redFourList; 
	private List<String> redThreeList; 
	private List<String> blueFocusList; 
	private List<String> blueFiveList; 
	private List<String> blueFourList; 
	private List<String> blueThreeList;
	private List<String> greenFocusList; 
	private List<String> greenFiveList; 
	private List<String> greenFourList; 
	private List<String> greenThreeList; 
	private List<String> colorlessFocusList; 
	private List<String> colorlessFiveList; 
	private List<String> colorlessFourList; 
	private List<String> colorlessThreeList;
	private List<String> focusList; 
	private List<String> fiveList; 
	private List<String> fourList; 
	private List<String> threeList; 
	private List<String> summonedFocusList; 
	private List<String> summonedFiveList; 
	private List<String> summonedFourList; 
	private List<String> summonedThreeList; 
	private List<String> preferredColors;
		
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
	
	public double getInitialFocusRate() {
		return initialFocusRate;
	}
	
	public double getInitialFiveRate() {
		return initialFiveRate;
	}
	
	public double getInitialFourRate() {
		return initialFourRate;
	}
	
	public double getInitialThreeRate() {
		return initialThreeRate;
	}
	
	public List<String> getFocusList() {
		return focusList;
	}
	
	public int getPullsWithoutFocusOrFive() {
		return pullsWithoutFocusOrFive;
	}
	
	public int getSessionPulls() {
		return sessionPulls;
	}
	
	public int getTotalPulls() {
		return totalPulls;
	}
	
	// Updates instance variables with corresponding information from the specified banner.
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

		fiveList.addAll(redFiveList);
		fiveList.addAll(blueFiveList);
		fiveList.addAll(greenFiveList);
		fiveList.addAll(colorlessFiveList);
		
		fourList.addAll(redFourList);
		fourList.addAll(blueFourList);
		fourList.addAll(greenFourList);
		fourList.addAll(colorlessFourList);
		
		threeList.addAll(redThreeList);
		threeList.addAll(blueThreeList);
		threeList.addAll(greenThreeList);
		threeList.addAll(colorlessThreeList);
		
		// All colors are preferred at the beginning.
		preferredColors.add("red");
		preferredColors.add("blue");
		preferredColors.add("green");
		preferredColors.add("colorless");
		
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
	
	/*  Calculates and determines which heroes are present in a summoning session,
	 *  and summons those heroes with colors present in the preferredColors list.
	 *  Returns an array of String lists containing the summoned heroes of each rarity.
	 */ 
	public List<String>[] pull(int pulls) {
		List<String>[] listArray = new List[4];
		
		if (pulls < 1 || pulls > 5) {
			System.out.println("Error: Invalid number of pulls entered. Please enter a value from 1-5.");
		} else {			
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
	// Returns false if user is trying to remove the last preferred color, otherwise returns true.
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
	
	public String calculateSnipeRate(String hero) {
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.HALF_UP);
			
		double numerator = Double.parseDouble(df.format(initialFocusRate * (1.0 / (double) focusList.size())));
		double denominator = 0.0;
		
		if (redFocusList.contains(hero)) {
			denominator = Double.parseDouble(df.format(numerator + (initialFiveRate * ((double) redFiveList.size() / (double) fiveList.size())) 
					+ (initialFourRate * ((double) redFourList.size() / (double) fourList.size())) 
					+ (initialThreeRate * ((double) redThreeList.size() / (double) threeList.size()))));
		} else if (blueFocusList.contains(hero)) {
			denominator = Double.parseDouble(df.format(numerator + (initialFiveRate * ((double) blueFiveList.size() / (double) fiveList.size())) 
					+ (initialFourRate * ((double) blueFourList.size() / (double) fourList.size())) 
					+ (initialThreeRate * ((double) blueThreeList.size() / (double) threeList.size()))));
		} else if (greenFocusList.contains(hero)) {
			denominator = Double.parseDouble(df.format(numerator + (initialFiveRate * ((double) greenFiveList.size() / (double) fiveList.size())) 
					+ (initialFourRate * ((double) greenFourList.size() / (double) fourList.size())) 
					+ (initialThreeRate * ((double) greenThreeList.size() / (double) threeList.size()))));
		} else if (colorlessFocusList.contains(hero)) {
			denominator = Double.parseDouble(df.format(numerator + (initialFiveRate * ((double) colorlessFiveList.size() / (double) fiveList.size())) 
					+ (initialFourRate * ((double) colorlessFourList.size() / (double) fourList.size())) 
					+ (initialThreeRate * ((double) colorlessThreeList.size() / (double) threeList.size()))));
		} else {
			return "Error!";
		}
		
		return df.format((numerator / denominator) * 100.0) + "%";
	}
}
