import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.*;

public class GUI implements ActionListener {
	
	private int sessionCount;
	
	private JButton massSummonButton, selectBannerButton, summonButton;
	private JComboBox bannersComboBox;
	private JFrame mainFrame;
	private JLabel currentFocusRateLabel, currentFiveRateLabel, currentFourRateLabel, currentThreeRateLabel, imageLabel;
	private JPanel appearancePanel, bannerPanel, bannerSelectPanel, formatPanel, fundamentalsPanel, heroesSummonedPanel, mainPanel, radioButtonsPanel, 
		resultPanel, sessionsPanel, summonPanel, summonedFocusPanel, summonedFivePanel, summonNumberPanel, summonPreferencesPanel;
	private JRadioButton oneRadioButton, twoRadioButton, threeRadioButton, fourRadioButton, fiveRadioButton;
	private JScrollPane focusScrollPane, fiveScrollPane, sessionsScrollPane;
	private JTextField summonTextField;
	private JToggleButton redPreferenceButton, bluePreferenceButton, greenPreferenceButton, colorlessPreferenceButton;
	
	private ButtonGroup buttonGroup;
	private Summoner summoner;
	
	public GUI() {
		mainFrame = new JFrame("Fire Emblem Heroes Summon Simulator");
		mainPanel = new JPanel(new BorderLayout());
		fundamentalsPanel = new JPanel(new BorderLayout());
		bannerPanel = new JPanel(new BorderLayout());
		bannerSelectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		summonPanel = new JPanel(new BorderLayout());
		summonPreferencesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
		summonNumberPanel = new JPanel(new GridBagLayout());
		resultPanel = new JPanel(new BorderLayout());
		appearancePanel = new JPanel(new GridBagLayout());
		heroesSummonedPanel = new JPanel(new GridBagLayout());
		sessionsPanel = new JPanel(new GridBagLayout());
		formatPanel = new JPanel(new BorderLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(3, 3, 0, 3);
		
		// Sets up bannerSelectPanel.		
		JLabel bannerLabel = new JLabel("Banner:", SwingConstants.CENTER);
		bannerLabel.setPreferredSize(new Dimension(50, 25));
		bannerSelectPanel.add(bannerLabel);
		
		try {
			List<String> bannersList = Files.readAllLines(Paths.get("Banners.txt"), StandardCharsets.UTF_8);
			bannersComboBox = new JComboBox<>(bannersList.toArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bannersComboBox.setBackground(Color.WHITE);
		bannersComboBox.setPreferredSize(new Dimension(200, 25));
		((JLabel) bannersComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		bannerSelectPanel.add(bannersComboBox);
		
		selectBannerButton = new JButton("Select");
		selectBannerButton.addActionListener(this);
		selectBannerButton.setBackground(Color.WHITE);
		selectBannerButton.setPreferredSize(new Dimension(75, 25));
		bannerSelectPanel.add(selectBannerButton);
		
		bannerPanel.add(bannerSelectPanel, BorderLayout.NORTH);
		
		// Sets up imageLabel.
		imageLabel = new JLabel(new ImageIcon("Banners\\" + bannersComboBox.getSelectedItem() + "\\Image.png"));
		imageLabel.setPreferredSize(new Dimension(428, 301));   // TODO: Change these dimensions to match whatever standard I decide on in the future.
		bannerPanel.add(imageLabel, BorderLayout.CENTER);
		
		// Sets up summonPreferencesPanel.
		// TODO: Change the ImageIcons.
		ImageIcon preferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");	
		redPreferenceButton = new JToggleButton(preferenceImage, true);
		redPreferenceButton.addActionListener(this);
		redPreferenceButton.setPreferredSize(new Dimension(50, 50));
		summonPreferencesPanel.add(redPreferenceButton);
		
		preferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");	
		bluePreferenceButton = new JToggleButton(preferenceImage, true);
		bluePreferenceButton.addActionListener(this);
		bluePreferenceButton.setPreferredSize(new Dimension(50, 50));
		summonPreferencesPanel.add(bluePreferenceButton);
		
		preferenceImage = new ImageIcon("Banners\\Summer's Arrival\\Image.png");	
		greenPreferenceButton = new JToggleButton(preferenceImage, true);
		greenPreferenceButton.addActionListener(this);
		greenPreferenceButton.setPreferredSize(new Dimension(50, 50));
		summonPreferencesPanel.add(greenPreferenceButton);
		
		preferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");	
		colorlessPreferenceButton = new JToggleButton(preferenceImage, true);
		colorlessPreferenceButton.addActionListener(this);
		colorlessPreferenceButton.setPreferredSize(new Dimension(50, 50));
		summonPreferencesPanel.add(colorlessPreferenceButton);
			
		// Sets up radio buttons.
		oneRadioButton = new JRadioButton("1");
		twoRadioButton = new JRadioButton("2");
		threeRadioButton = new JRadioButton("3");
		fourRadioButton = new JRadioButton("4");
		fiveRadioButton = new JRadioButton("5");
		
		oneRadioButton.setActionCommand("1");
		twoRadioButton.setActionCommand("2");
		threeRadioButton.setActionCommand("3");
		fourRadioButton.setActionCommand("4");
		fiveRadioButton.setActionCommand("5");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(oneRadioButton);
		buttonGroup.add(twoRadioButton);
		buttonGroup.add(threeRadioButton);
		buttonGroup.add(fourRadioButton);
		buttonGroup.add(fiveRadioButton);
		fiveRadioButton.setSelected(true);
		
		radioButtonsPanel = new JPanel();
		radioButtonsPanel.add(oneRadioButton);
		radioButtonsPanel.add(twoRadioButton);
		radioButtonsPanel.add(threeRadioButton);
		radioButtonsPanel.add(fourRadioButton);
		radioButtonsPanel.add(fiveRadioButton);
		radioButtonsPanel.setPreferredSize(new Dimension(185, 25));
		summonNumberPanel.add(radioButtonsPanel, constraints);
		
		// Sets up remaining components of summonPanel (summonButton, massSummonButton, & summonTextField).
		summonButton = new JButton("Summon");
		summonButton.addActionListener(this);
		summonButton.setBackground(Color.WHITE);
		summonButton.setPreferredSize(new Dimension(120, 25));
		constraints.gridx = 1;
		summonNumberPanel.add(summonButton, constraints);
		
		massSummonButton = new JButton("Mass Summon");
		massSummonButton.addActionListener(this);
		massSummonButton.setBackground(Color.WHITE);
		massSummonButton.setPreferredSize(new Dimension(120, 25));
		constraints.gridy = 1;
		summonNumberPanel.add(massSummonButton, constraints);
		
		summonTextField = new JTextField();
		summonTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		summonTextField.setPreferredSize(new Dimension(185, 25));
		constraints.gridx = 0;
		summonNumberPanel.add(summonTextField, constraints);
		
		summonPanel.add(summonPreferencesPanel, BorderLayout.NORTH);
		summonPanel.add(summonNumberPanel, BorderLayout.CENTER);
		
		// Sets up the summoner.
		summoner = new Summoner();
		summoner.setupBanner(bannersComboBox.getSelectedItem().toString());
		
		// Sets up appearancePanel.
		JLabel appearanceRatesLabel = new JLabel("Appearance Rates", SwingConstants.CENTER);
		appearanceRatesLabel.setPreferredSize(new Dimension(125, 25));		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.insets = new Insets(4, 0, 0, 0);
		appearancePanel.add(appearanceRatesLabel, constraints);
		
		JLabel focusRateLabel = new JLabel("Focus:", SwingConstants.LEFT);
		focusRateLabel.setPreferredSize(new Dimension(50, 25));	
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		appearancePanel.add(focusRateLabel, constraints);
		
		currentFocusRateLabel = new JLabel(Double.toString(summoner.getCurrentFocusRate()), SwingConstants.LEFT);
		currentFocusRateLabel.setPreferredSize(new Dimension(50, 25));	
		constraints.gridx = 1;
		constraints.insets = new Insets(4, 0, 0, 15);
		appearancePanel.add(currentFocusRateLabel, constraints);
		
		JLabel fiveRateLabel = new JLabel("Five:", SwingConstants.LEFT);
		fiveRateLabel.setPreferredSize(new Dimension(50, 25));	
		constraints.gridx = 2;
		constraints.insets = new Insets(4, 15, 0, 0);
		appearancePanel.add(fiveRateLabel, constraints);
		
		currentFiveRateLabel = new JLabel(Double.toString(summoner.getCurrentFiveRate()), SwingConstants.LEFT);
		currentFiveRateLabel.setPreferredSize(new Dimension(50, 25));	
		constraints.gridx = 3;
		constraints.insets = new Insets(4, 0, 0, 0);
		appearancePanel.add(currentFiveRateLabel, constraints);
		
		currentThreeRateLabel = new JLabel(Double.toString(summoner.getCurrentThreeRate()), SwingConstants.LEFT);
		currentThreeRateLabel.setPreferredSize(new Dimension(50, 25));	
		constraints.gridy = 2;
		appearancePanel.add(currentThreeRateLabel, constraints);
		
		JLabel threeRateLabel = new JLabel("Three:", SwingConstants.LEFT);
		threeRateLabel.setPreferredSize(new Dimension(50, 25));	
		constraints.gridx = 2;
		constraints.insets = new Insets(4, 15, 0, 0);
		appearancePanel.add(threeRateLabel, constraints);
		
		currentFourRateLabel = new JLabel(Double.toString(summoner.getCurrentFourRate()), SwingConstants.LEFT);
		currentFourRateLabel.setPreferredSize(new Dimension(50, 25));	
		constraints.gridx = 1;
		constraints.insets = new Insets(4, 0, 0, 15);
		appearancePanel.add(currentFourRateLabel, constraints);
		
		JLabel fourRateLabel = new JLabel("Four:", SwingConstants.LEFT);
		fourRateLabel.setPreferredSize(new Dimension(50, 25));	
		constraints.gridx = 0;
		constraints.insets = new Insets(4, 0, 0, 0);
		appearancePanel.add(fourRateLabel, constraints);
		
		resultPanel.add(appearancePanel, BorderLayout.NORTH);
		
		// Sets up heroesSummonedPanel.
		JLabel summonedFocusLabel = new JLabel("Focus Heroes Summoned");
		summonedFocusLabel.setPreferredSize(new Dimension(150, 25));
		constraints.gridx = 0;
		constraints.gridy = 0;
		heroesSummonedPanel.add(summonedFocusLabel, constraints);
		
		JLabel summonedFiveLabel = new JLabel("5-Star Heroes Summoned");
		summonedFiveLabel.setPreferredSize(new Dimension(150, 25));
		constraints.gridx = 1;
		heroesSummonedPanel.add(summonedFiveLabel, constraints);
		
		summonedFivePanel = new JPanel();
		summonedFivePanel.setLayout(new BoxLayout(summonedFivePanel, BoxLayout.Y_AXIS));
		summonedFivePanel.setBackground(Color.WHITE);
		fiveScrollPane = new JScrollPane(summonedFivePanel);
		fiveScrollPane.setPreferredSize(new Dimension(185, 400));
		constraints.gridy = 1;
		constraints.insets = new Insets(4, 10, 6, 5);
		heroesSummonedPanel.add(fiveScrollPane, constraints);
		
		summonedFocusPanel = new JPanel();
		summonedFocusPanel.setLayout(new BoxLayout(summonedFocusPanel, BoxLayout.Y_AXIS));
		summonedFocusPanel.setBackground(Color.WHITE);
		focusScrollPane = new JScrollPane(summonedFocusPanel);
		focusScrollPane.setPreferredSize(new Dimension(185, 400));
		constraints.gridx = 0;
		constraints.insets = new Insets(4, 5, 6, 10);
		heroesSummonedPanel.add(focusScrollPane, constraints);
		
		resultPanel.add(heroesSummonedPanel, BorderLayout.CENTER);
		
		fundamentalsPanel.add(bannerPanel, BorderLayout.NORTH);
		fundamentalsPanel.add(summonPanel, BorderLayout.CENTER);
		fundamentalsPanel.add(resultPanel, BorderLayout.SOUTH);
		mainPanel.add(fundamentalsPanel, BorderLayout.CENTER);
		
		// Sets up sessionsPanel and sessionsScrollPane.
		sessionCount = 1;
		
		JLabel sessionLabel = new JLabel("Session", SwingConstants.CENTER);
		sessionLabel.setBackground(Color.WHITE);
		sessionLabel.setPreferredSize(new Dimension(75, 25));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 0);
		sessionsPanel.add(sessionLabel, constraints);
		
		JLabel sessionHeroesLabel = new JLabel("Heroes Summoned", SwingConstants.CENTER);
		sessionHeroesLabel.setBackground(Color.WHITE);
		sessionHeroesLabel.setPreferredSize(new Dimension(300, 25));
		constraints.gridx = 1;
		sessionsPanel.add(sessionHeroesLabel, constraints);
		
		formatPanel.add(sessionsPanel, BorderLayout.NORTH);
		sessionsScrollPane = new JScrollPane(formatPanel);
		sessionsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		sessionsScrollPane.setBorder(BorderFactory.createEmptyBorder());
		sessionsScrollPane.setPreferredSize(new Dimension(400, 1));
		
		mainPanel.add(sessionsScrollPane, BorderLayout.EAST);
		
		// Sets up the mainFrame.
		mainFrame.setContentPane(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if (action.equals("Select")) {
			// Yes = 0 and No = 1.
			int selectBannerOption = JOptionPane.showConfirmDialog(
				    mainFrame,
				    "Are you sure you want to switch to a different banner?\n"
				    + "All existing data will be wiped upon switching.",
				    "Confirmation",
				    JOptionPane.YES_NO_OPTION);
			
			if (selectBannerOption == 0) {
				imageLabel.setIcon(new ImageIcon("Banners\\" + bannersComboBox.getSelectedItem().toString() + "\\Image.png"));
				summoner = new Summoner();
				summoner.setupBanner(bannersComboBox.getSelectedItem().toString());
				currentFocusRateLabel.setText(Double.toString(summoner.getCurrentFocusRate()));
				currentFiveRateLabel.setText(Double.toString(summoner.getCurrentFiveRate()));
				currentFourRateLabel.setText(Double.toString(summoner.getCurrentFourRate()));
				currentThreeRateLabel.setText(Double.toString(summoner.getCurrentThreeRate()));
				summonedFocusPanel.removeAll();
				summonedFivePanel.removeAll();
				
				sessionsPanel.removeAll();
				sessionCount = 1;
				
				mainPanel.repaint();
				mainPanel.revalidate();
			}
		} else if (action.equals("Summon")) {			
			addToSummonedPanels(summoner.pull(Integer.parseInt(buttonGroup.getSelection().getActionCommand())));
			
			currentFocusRateLabel.setText(Double.toString(summoner.getCurrentFocusRate()));
			currentFiveRateLabel.setText(Double.toString(summoner.getCurrentFiveRate()));
			currentFourRateLabel.setText(Double.toString(summoner.getCurrentFourRate()));
			currentThreeRateLabel.setText(Double.toString(summoner.getCurrentThreeRate()));
			
			resultPanel.revalidate();
		} else if (action.equals("Mass Summon")) {
			int pullsRemaining = Integer.parseInt(summonTextField.getText());
			
			while (pullsRemaining != 0) {			
				if (pullsRemaining >= 5) {
					addToSummonedPanels(summoner.pull(5));		
				} else {
					addToSummonedPanels(summoner.pull(pullsRemaining));
				}
				
				System.out.println("Session Pulls: " + summoner.getSessionPulls());
				pullsRemaining = pullsRemaining - summoner.getSessionPulls();
			}
			
			currentFocusRateLabel.setText(Double.toString(summoner.getCurrentFocusRate()));
			currentFiveRateLabel.setText(Double.toString(summoner.getCurrentFiveRate()));
			currentFourRateLabel.setText(Double.toString(summoner.getCurrentFourRate()));
			currentThreeRateLabel.setText(Double.toString(summoner.getCurrentThreeRate()));
			
			resultPanel.revalidate();		
		// TODO: Change the ImageIcons.
		} else if (e.getSource().equals(redPreferenceButton)) {
			if (summoner.changePreferredColors("red") == true) {
				ImageIcon newPreferenceImage;
				
				if (redPreferenceButton.isSelected() == true) {
					newPreferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");
				} else {
					newPreferenceImage = new ImageIcon("Banners\\Summer's Arrival\\Image.png");
				}
				
				redPreferenceButton.setIcon(newPreferenceImage);
				redPreferenceButton.revalidate();
			} else {
				redPreferenceButton.setSelected(true);
				JOptionPane.showMessageDialog(mainFrame, "You must have at least one color that you wish to summon.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(bluePreferenceButton)) {
			if (summoner.changePreferredColors("blue") == true) {
				ImageIcon newPreferenceImage;
				
				if (bluePreferenceButton.isSelected() == true) {
					newPreferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");
				} else {
					newPreferenceImage = new ImageIcon("Banners\\Summer's Arrival\\Image.png");
				}
				
				bluePreferenceButton.setIcon(newPreferenceImage);
				bluePreferenceButton.revalidate();
			} else {
				bluePreferenceButton.setSelected(true);
				JOptionPane.showMessageDialog(mainFrame, "You must have at least one color that you wish to summon.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(greenPreferenceButton)) {
			if (summoner.changePreferredColors("green") == true) {
				ImageIcon newPreferenceImage;
				
				if (greenPreferenceButton.isSelected() == true) {
					newPreferenceImage = new ImageIcon("Banners\\Summer's Arrival\\Image.png");
				} else {
					newPreferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");
				}
				
				greenPreferenceButton.setIcon(newPreferenceImage);
				greenPreferenceButton.revalidate();
			} else {
				greenPreferenceButton.setSelected(true);
				JOptionPane.showMessageDialog(mainFrame, "You must have at least one color that you wish to summon.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(colorlessPreferenceButton)) {
			if (summoner.changePreferredColors("colorless") == true) {
				ImageIcon newPreferenceImage;
				
				if (colorlessPreferenceButton.isSelected() == true) {
					newPreferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");
				} else {
					newPreferenceImage = new ImageIcon("Banners\\Summer's Arrival\\Image.png");
				}
				
				colorlessPreferenceButton.setIcon(newPreferenceImage);
				colorlessPreferenceButton.revalidate();
			} else {
				colorlessPreferenceButton.setSelected(true);
				JOptionPane.showMessageDialog(mainFrame, "You must have at least one color that you wish to summon.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// Takes the returned List<String>[] from the Summoner class's "pull" method and updates the summoned panels.
	private void addToSummonedPanels(List<String>[] listArray) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = sessionCount;
		
		JLabel sessionCountLabel = new JLabel(Integer.toString(sessionCount), SwingConstants.CENTER);
		sessionCountLabel.setPreferredSize(new Dimension(75, 50));
		sessionsPanel.add(sessionCountLabel, constraints);
		
		JPanel sessionSummonedPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		
		for (int i = 0; i < listArray[0].size(); i++) {
			JLabel nameLabel = new JLabel(listArray[0].get(i), SwingConstants.CENTER);
			nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			summonedFocusPanel.add(nameLabel);
			
			JLabel imageLabel = new JLabel(new ImageIcon("Hero Icons\\" + listArray[0].get(i) + ".png"));
			imageLabel.setPreferredSize(new Dimension(50, 50));
			sessionSummonedPanel.add(imageLabel);
		}
		
		for (int j = 0; j < listArray[1].size(); j++) {
			JLabel label = new JLabel(listArray[1].get(j), SwingConstants.CENTER);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			summonedFivePanel.add(label);
			
			JLabel imageLabel = new JLabel(new ImageIcon("Hero Icons\\" + listArray[1].get(j) + ".png"));
			imageLabel.setPreferredSize(new Dimension(50, 50));
			sessionSummonedPanel.add(imageLabel);
		}
		
		for (int k = 0; k < listArray[2].size(); k++) {			
			JLabel imageLabel = new JLabel(new ImageIcon("Hero Icons\\" + listArray[2].get(k) + ".png"));
			imageLabel.setPreferredSize(new Dimension(50, 50));
			sessionSummonedPanel.add(imageLabel);
		}
		
		for (int l = 0; l < listArray[3].size(); l++) {
			JLabel imageLabel = new JLabel(new ImageIcon("Hero Icons\\" + listArray[3].get(l) + ".png"));
			imageLabel.setPreferredSize(new Dimension(50, 50));
			sessionSummonedPanel.add(imageLabel);
		}
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		sessionsPanel.add(sessionSummonedPanel, constraints);
		
		sessionCount++;
		sessionsScrollPane.revalidate();
		JScrollBar sessionsScrollBar = sessionsScrollPane.getVerticalScrollBar();
		sessionsScrollBar.setValue(sessionsScrollBar.getMaximum());
	}
}
