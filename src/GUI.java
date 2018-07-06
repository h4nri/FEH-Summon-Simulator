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
	
	private int sessionsRow = 0;
	
	private JButton selectBannerButton;
	private JButton summonButton;
	private JButton massSummonButton;
	private JComboBox bannersComboBox;
	private JDialog sessionsDialog;
	private JFrame mainFrame;
	private JLabel bannerImageLabel;
	private JLabel currentFocusRateLabel; 
	private JLabel currentFiveRateLabel; 
	private JLabel currentFourRateLabel; 
	private JLabel currentThreeRateLabel;
	private JPanel mainPanel;
	private JPanel bannerPanel;
	private JPanel bannerSelectPanel;
	private JPanel summonPanel;
	private JPanel summonPreferencesPanel;
	private JPanel summonNumberPanel;
	private JPanel radioButtonsPanel;
	private JPanel resultsPanel;
	private JPanel appearanceRatesPanel;
	private JPanel heroesSummonedPanel;
	private JPanel summonedFocusPanel; 
	private JPanel summonedFivePanel; 
	private JPanel sessionsFormatPanel;
	private JPanel sessionsHeaderPanel;
	private JPanel sessionsSummonsPanel; 
	private JRadioButton oneRadioButton;
	private JRadioButton twoRadioButton;
	private JRadioButton threeRadioButton;
	private JRadioButton fourRadioButton;
	private JRadioButton fiveRadioButton;
	private JScrollPane focusScrollPane;
	private JScrollPane fiveScrollPane;
	private JScrollPane sessionsScrollPane;
	private JTextField summonTextField;
	private JToggleButton redPreferenceButton;
	private JToggleButton bluePreferenceButton;
	private JToggleButton greenPreferenceButton;
	private JToggleButton colorlessPreferenceButton;
	
	private ButtonGroup buttonGroup;
	private Summoner summoner;
	
	public GUI() {		
		// Initializes and sets up bannerSelectPanel and its components (bannerLabel, bannersComboBox, & selectBannerButton).	
		JLabel bannerLabel = new JLabel("Banner:", SwingConstants.CENTER);
		bannerLabel.setPreferredSize(new Dimension(50, 25));
		
		try {
			List<String> bannersList = Files.readAllLines(Paths.get("Banners.txt"), StandardCharsets.UTF_8);
			bannersComboBox = new JComboBox<>(bannersList.toArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bannersComboBox.setBackground(Color.WHITE);
		bannersComboBox.setPreferredSize(new Dimension(200, 25));
		((JLabel) bannersComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		selectBannerButton = new JButton("Select");
		selectBannerButton.addActionListener(this);
		selectBannerButton.setBackground(Color.WHITE);
		selectBannerButton.setPreferredSize(new Dimension(75, 25));
		
		bannerSelectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		bannerSelectPanel.add(bannerLabel);
		bannerSelectPanel.add(bannersComboBox);
		bannerSelectPanel.add(selectBannerButton);
		
		// Initializes and sets up bannerImageLabel & bannerPanel.
		bannerImageLabel = new JLabel(new ImageIcon("Banners\\" + bannersComboBox.getSelectedItem() + "\\Image.png"));
		bannerImageLabel.setPreferredSize(new Dimension(428, 301));   // TODO: Change these dimensions to match whatever standard I decide on in the future.

		bannerPanel = new JPanel(new BorderLayout());
		bannerPanel.add(bannerSelectPanel, BorderLayout.NORTH);
		bannerPanel.add(bannerImageLabel, BorderLayout.CENTER);
		
		// Initializes and sets up the four color preference buttons.
		// TODO: Change the ImageIcons.
		ImageIcon preferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");	
		redPreferenceButton = new JToggleButton(preferenceImage, true);
		redPreferenceButton.addActionListener(this);
		redPreferenceButton.setPreferredSize(new Dimension(50, 50));
		
		preferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");	
		bluePreferenceButton = new JToggleButton(preferenceImage, true);
		bluePreferenceButton.addActionListener(this);
		bluePreferenceButton.setPreferredSize(new Dimension(50, 50));
		
		preferenceImage = new ImageIcon("Banners\\Summer's Arrival\\Image.png");	
		greenPreferenceButton = new JToggleButton(preferenceImage, true);
		greenPreferenceButton.addActionListener(this);
		greenPreferenceButton.setPreferredSize(new Dimension(50, 50));
		
		preferenceImage = new ImageIcon("Banners\\Scattered Fangs\\Image.png");	
		colorlessPreferenceButton = new JToggleButton(preferenceImage, true);
		colorlessPreferenceButton.addActionListener(this);
		colorlessPreferenceButton.setPreferredSize(new Dimension(50, 50));
		
		// Initializes and sets up summonPreferencesPanel.
		summonPreferencesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
		summonPreferencesPanel.add(redPreferenceButton);
		summonPreferencesPanel.add(bluePreferenceButton);
		summonPreferencesPanel.add(greenPreferenceButton);
		summonPreferencesPanel.add(colorlessPreferenceButton);
			
		// Initializes and sets up radioButtonsPanel & the radio buttons on it.
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
		
		// Initializes and sets up remaining components of summonNumberPanel (summonButton, massSummonButton, & summonTextField).
		summonButton = new JButton("Summon");
		summonButton.addActionListener(this);
		summonButton.setBackground(Color.WHITE);
		summonButton.setPreferredSize(new Dimension(120, 25));
		
		massSummonButton = new JButton("Mass Summon");
		massSummonButton.addActionListener(this);
		massSummonButton.setBackground(Color.WHITE);
		massSummonButton.setPreferredSize(new Dimension(120, 25));
		
		summonTextField = new JTextField();
		summonTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		summonTextField.setPreferredSize(new Dimension(185, 25));
		
		// Initializes and sets up summonNumberPanel.
		GridBagConstraints constraints = new GridBagConstraints();	
		summonNumberPanel = new JPanel(new GridBagLayout());
			
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(3, 3, 0, 3);
		summonNumberPanel.add(radioButtonsPanel, constraints);	
		constraints.gridx = 1;
		summonNumberPanel.add(summonButton, constraints);
		constraints.gridy = 1;
		summonNumberPanel.add(massSummonButton, constraints);
		constraints.gridx = 0;
		summonNumberPanel.add(summonTextField, constraints);
		
		// Initializes and sets up summonPanel.
		summonPanel = new JPanel(new BorderLayout());
		summonPanel.add(summonPreferencesPanel, BorderLayout.NORTH);
		summonPanel.add(summonNumberPanel, BorderLayout.CENTER);
		
		// Initializes and sets up components of appearanceRatesPanel.		
		summoner = new Summoner();
		summoner.setupBanner(bannersComboBox.getSelectedItem().toString());
		
		JLabel appearanceRatesLabel = new JLabel("Appearance Rates", SwingConstants.CENTER);
		appearanceRatesLabel.setPreferredSize(new Dimension(125, 25));		
		
		JLabel focusRateLabel = new JLabel("Focus:", SwingConstants.LEFT);
		focusRateLabel.setPreferredSize(new Dimension(50, 25));		
		currentFocusRateLabel = new JLabel(Double.toString(summoner.getCurrentFocusRate()), SwingConstants.LEFT);
		currentFocusRateLabel.setPreferredSize(new Dimension(50, 25));	
		
		JLabel fiveRateLabel = new JLabel("Five:", SwingConstants.LEFT);
		fiveRateLabel.setPreferredSize(new Dimension(50, 25));	
		currentFiveRateLabel = new JLabel(Double.toString(summoner.getCurrentFiveRate()), SwingConstants.LEFT);
		currentFiveRateLabel.setPreferredSize(new Dimension(50, 25));	
		
		JLabel fourRateLabel = new JLabel("Four:", SwingConstants.LEFT);
		fourRateLabel.setPreferredSize(new Dimension(50, 25));	
		currentFourRateLabel = new JLabel(Double.toString(summoner.getCurrentFourRate()), SwingConstants.LEFT);
		currentFourRateLabel.setPreferredSize(new Dimension(50, 25));	
		
		JLabel threeRateLabel = new JLabel("Three:", SwingConstants.LEFT);
		threeRateLabel.setPreferredSize(new Dimension(50, 25));		
		currentThreeRateLabel = new JLabel(Double.toString(summoner.getCurrentThreeRate()), SwingConstants.LEFT);
		currentThreeRateLabel.setPreferredSize(new Dimension(50, 25));
			
		// Initializes and sets up appearanceRatesPanel.	
		appearanceRatesPanel = new JPanel(new GridBagLayout());
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.insets = new Insets(4, 0, 0, 0);
		appearanceRatesPanel.add(appearanceRatesLabel, constraints);
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		appearanceRatesPanel.add(focusRateLabel, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(4, 0, 0, 15);
		appearanceRatesPanel.add(currentFocusRateLabel, constraints);
		constraints.gridx = 2;
		constraints.insets = new Insets(4, 15, 0, 0);
		appearanceRatesPanel.add(fiveRateLabel, constraints);
		constraints.gridx = 3;
		constraints.insets = new Insets(4, 0, 0, 0);
		appearanceRatesPanel.add(currentFiveRateLabel, constraints);
		constraints.gridy = 2;
		appearanceRatesPanel.add(currentThreeRateLabel, constraints);
		constraints.gridx = 2;
		constraints.insets = new Insets(4, 15, 0, 0);
		appearanceRatesPanel.add(threeRateLabel, constraints);
		constraints.gridx = 1;
		constraints.insets = new Insets(4, 0, 0, 15);
		appearanceRatesPanel.add(currentFourRateLabel, constraints);
		constraints.gridx = 0;
		constraints.insets = new Insets(4, 0, 0, 0);
		appearanceRatesPanel.add(fourRateLabel, constraints);
		
		// Initializes and sets up components of heroesSummonedPanel.
		JLabel summonedFocusLabel = new JLabel("Focus Heroes Summoned");
		summonedFocusLabel.setPreferredSize(new Dimension(150, 25));
		summonedFocusPanel = new JPanel();
		summonedFocusPanel.setLayout(new BoxLayout(summonedFocusPanel, BoxLayout.Y_AXIS));
		summonedFocusPanel.setBackground(Color.WHITE);
		focusScrollPane = new JScrollPane(summonedFocusPanel);
		focusScrollPane.setPreferredSize(new Dimension(185, 400));
		
		JLabel summonedFiveLabel = new JLabel("5-Star Heroes Summoned");
		summonedFiveLabel.setPreferredSize(new Dimension(150, 25));
		summonedFivePanel = new JPanel();
		summonedFivePanel.setLayout(new BoxLayout(summonedFivePanel, BoxLayout.Y_AXIS));
		summonedFivePanel.setBackground(Color.WHITE);
		fiveScrollPane = new JScrollPane(summonedFivePanel);
		fiveScrollPane.setPreferredSize(new Dimension(185, 400));
		
		// Initializes and sets up heroesSummonedPanel.
		heroesSummonedPanel = new JPanel(new GridBagLayout());
		constraints.gridx = 0;
		constraints.gridy = 0;
		heroesSummonedPanel.add(summonedFocusLabel, constraints);
		constraints.gridx = 1;
		heroesSummonedPanel.add(summonedFiveLabel, constraints);
		constraints.gridy = 1;
		constraints.insets = new Insets(4, 10, 6, 5);
		heroesSummonedPanel.add(fiveScrollPane, constraints);
		constraints.gridx = 0;
		constraints.insets = new Insets(4, 5, 6, 10);
		heroesSummonedPanel.add(focusScrollPane, constraints);
		
		// Initializes and sets up resultsPanel.
		resultsPanel = new JPanel(new BorderLayout());
		resultsPanel.add(appearanceRatesPanel, BorderLayout.NORTH);
		resultsPanel.add(heroesSummonedPanel, BorderLayout.CENTER);
		
		// Initializes and sets up mainPanel.
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(bannerPanel, BorderLayout.NORTH);
		mainPanel.add(summonPanel, BorderLayout.CENTER);
		mainPanel.add(resultsPanel, BorderLayout.SOUTH);
		
		// Initializes and sets up mainFrame.
		mainFrame = new JFrame("Fire Emblem Heroes Summon Simulator");
		mainFrame.setContentPane(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.pack();
		
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - mainFrame.getWidth()) / 2);
	    mainFrame.setLocation(x, 20);

		// Initializes and sets up sessionsDialog's components.		
		JLabel sessionNumberLabel = new JLabel("Session", SwingConstants.CENTER);
		JLabel sessionHeroesLabel = new JLabel("Heroes Summoned", SwingConstants.CENTER);
		sessionNumberLabel.setPreferredSize(new Dimension(75, 25));	
		sessionHeroesLabel.setPreferredSize(new Dimension(250, 25));
		
		sessionsHeaderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sessionsHeaderPanel.add(sessionNumberLabel);
		sessionsHeaderPanel.add(sessionHeroesLabel);
		
		sessionsSummonsPanel = new JPanel();	
		sessionsSummonsPanel.setLayout(new GridBagLayout());	
		sessionsFormatPanel = new JPanel(new BorderLayout());
		sessionsFormatPanel.add(sessionsSummonsPanel, BorderLayout.NORTH);

		sessionsScrollPane = new JScrollPane(sessionsFormatPanel);
		sessionsScrollPane.getVerticalScrollBar().setUnitIncrement(16);	
		sessionsScrollPane.setBorder(BorderFactory.createEmptyBorder());	
		sessionsScrollPane.setColumnHeaderView(sessionsHeaderPanel);		
		sessionsScrollPane.setPreferredSize(new Dimension(350, 750));		
			
		// Initializes and sets up sessionsDialog.
		sessionsDialog = new JDialog(mainFrame, "Summons", Dialog.ModalityType.MODELESS);
		sessionsDialog.setContentPane(sessionsScrollPane);
		sessionsDialog.setLocation((mainFrame.getX() + (mainFrame.getWidth())), 20);
		sessionsDialog.setResizable(false);
		sessionsDialog.setVisible(true);
		sessionsDialog.pack();
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
				bannerImageLabel.setIcon(new ImageIcon("Banners\\" + bannersComboBox.getSelectedItem().toString() + "\\Image.png"));
				
				summoner = new Summoner();
				summoner.setupBanner(bannersComboBox.getSelectedItem().toString());
				currentFocusRateLabel.setText(Double.toString(summoner.getCurrentFocusRate()));
				currentFiveRateLabel.setText(Double.toString(summoner.getCurrentFiveRate()));
				currentFourRateLabel.setText(Double.toString(summoner.getCurrentFourRate()));
				currentThreeRateLabel.setText(Double.toString(summoner.getCurrentThreeRate()));
				
				summonedFocusPanel.removeAll();
				summonedFivePanel.removeAll();
				sessionsSummonsPanel.removeAll();
				sessionsRow = 1;
				
				sessionsSummonsPanel.repaint();
				sessionsSummonsPanel.revalidate();
				mainPanel.repaint();
				mainPanel.revalidate();
			}
		} else if (action.equals("Summon")) {			
			addToSummonedPanels(summoner.pull(Integer.parseInt(buttonGroup.getSelection().getActionCommand())));
			
			currentFocusRateLabel.setText(Double.toString(summoner.getCurrentFocusRate()));
			currentFiveRateLabel.setText(Double.toString(summoner.getCurrentFiveRate()));
			currentFourRateLabel.setText(Double.toString(summoner.getCurrentFourRate()));
			currentThreeRateLabel.setText(Double.toString(summoner.getCurrentThreeRate()));
			
			resultsPanel.repaint();
			resultsPanel.revalidate();
		} else if (action.equals("Mass Summon")) {
			int pullsRemaining = Integer.parseInt(summonTextField.getText());
			
			while (pullsRemaining != 0) {			
				if (pullsRemaining >= 5) {
					addToSummonedPanels(summoner.pull(5));		
				} else {
					addToSummonedPanels(summoner.pull(pullsRemaining));
				}
				
				pullsRemaining = pullsRemaining - summoner.getSessionPulls();
			}
			
			currentFocusRateLabel.setText(Double.toString(summoner.getCurrentFocusRate()));
			currentFiveRateLabel.setText(Double.toString(summoner.getCurrentFiveRate()));
			currentFourRateLabel.setText(Double.toString(summoner.getCurrentFourRate()));
			currentThreeRateLabel.setText(Double.toString(summoner.getCurrentThreeRate()));
			
			resultsPanel.repaint();
			resultsPanel.revalidate();		
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
				redPreferenceButton.repaint();
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
				bluePreferenceButton.repaint();
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
				greenPreferenceButton.repaint();
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
				colorlessPreferenceButton.repaint();
				colorlessPreferenceButton.revalidate();
			} else {
				colorlessPreferenceButton.setSelected(true);
				JOptionPane.showMessageDialog(mainFrame, "You must have at least one color that you wish to summon.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// Takes the returned List<String>[] from the Summoner class's "pull" method and updates the summoned panels.
	// TODO: Update rarity images.
	private void addToSummonedPanels(List<String>[] listArray) {		
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel singleSessionNumberLabel = new JLabel(Integer.toString(sessionsRow + 1), SwingConstants.CENTER);
		singleSessionNumberLabel.setPreferredSize(new Dimension(75, 50));
		constraints.gridx = 0;
		constraints.gridy = sessionsRow;
		sessionsSummonsPanel.add(singleSessionNumberLabel, constraints);
		
		JPanel singleSessionPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		singleSessionPanel.setPreferredSize(new Dimension(250, 70));
		constraints.gridx = 1;
		sessionsSummonsPanel.add(singleSessionPanel, constraints);
		
		for (int i = 0; i < listArray[0].size(); i++) {
			JLabel nameLabel = new JLabel(listArray[0].get(i), SwingConstants.CENTER);
			nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			summonedFocusPanel.add(nameLabel);
			
			JLabel bannerImageLabel = new JLabel(new ImageIcon("Hero Icons\\" + listArray[0].get(i) + ".png"));
			bannerImageLabel.setPreferredSize(new Dimension(50, 50));
	
			JLabel rarityLabel = new JLabel(new ImageIcon("Rarity Icons\\5 Stars.png"));
			rarityLabel.setPreferredSize(new Dimension(50, 10));
		
			JPanel iconsPanel = new JPanel(new BorderLayout());
			iconsPanel.add(bannerImageLabel, BorderLayout.CENTER);
			iconsPanel.add(rarityLabel, BorderLayout.SOUTH);
			singleSessionPanel.add(iconsPanel);
		}
		
		for (int j = 0; j < listArray[1].size(); j++) {
			JLabel nameLabel = new JLabel(listArray[1].get(j), SwingConstants.CENTER);
			nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			summonedFivePanel.add(nameLabel);
					
			JLabel bannerImageLabel = new JLabel(new ImageIcon("Hero Icons\\" + listArray[1].get(j) + ".png"));
			bannerImageLabel.setPreferredSize(new Dimension(50, 50));
			
			JLabel rarityLabel = new JLabel(new ImageIcon("Rarity Icons\\5 Stars.png"));
			rarityLabel.setPreferredSize(new Dimension(50, 10));
			
			JPanel iconsPanel = new JPanel(new BorderLayout());
			iconsPanel.add(bannerImageLabel, BorderLayout.CENTER);
			iconsPanel.add(rarityLabel, BorderLayout.SOUTH);
			singleSessionPanel.add(iconsPanel);
		}
		
		for (int k = 0; k < listArray[2].size(); k++) {			
			JLabel bannerImageLabel = new JLabel(new ImageIcon("Hero Icons\\" + listArray[2].get(k) + ".png"));
			bannerImageLabel.setPreferredSize(new Dimension(50, 50));

			JLabel rarityLabel = new JLabel(new ImageIcon("Rarity Icons\\4 Stars.png"));
			rarityLabel.setPreferredSize(new Dimension(50, 10));

			JPanel iconsPanel = new JPanel(new BorderLayout());
			iconsPanel.add(bannerImageLabel, BorderLayout.CENTER);
			iconsPanel.add(rarityLabel, BorderLayout.SOUTH);
			singleSessionPanel.add(iconsPanel);
		}
		
		for (int l = 0; l < listArray[3].size(); l++) {
			JLabel bannerImageLabel = new JLabel(new ImageIcon("Hero Icons\\" + listArray[3].get(l) + ".png"));
			bannerImageLabel.setPreferredSize(new Dimension(50, 50));
			
			JLabel rarityLabel = new JLabel(new ImageIcon("Rarity Icons\\3 Stars.png"));
			rarityLabel.setPreferredSize(new Dimension(50, 10));

			JPanel iconsPanel = new JPanel(new BorderLayout());
			iconsPanel.add(bannerImageLabel, BorderLayout.CENTER);
			iconsPanel.add(rarityLabel, BorderLayout.SOUTH);
			singleSessionPanel.add(iconsPanel);
		}

		sessionsSummonsPanel.repaint();
		sessionsSummonsPanel.revalidate();
		
		// TODO: How to move the scroll bar to the very bottom?
		JScrollBar sessionsScrollBar = sessionsScrollPane.getVerticalScrollBar();
		sessionsScrollBar.setValue(sessionsScrollBar.getMaximum());
		
		sessionsRow++;
	}
}
