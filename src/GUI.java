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
	
	private JButton massSummonButton, selectBannerButton, summonButton;
	private JComboBox bannersComboBox;
	private JFrame mainFrame;
	private JLabel currentFocusRateLabel, currentFiveRateLabel, currentFourRateLabel, currentThreeRateLabel, imageLabel;
	private JPanel appearancePanel, bannerPanel, bannerSelectPanel, heroesSummonedPanel, mainPanel, radioButtonsPanel, 
		resultPanel, summonPanel, summonedFocusPanel, summonedFivePanel;
	private JRadioButton oneRadioButton, twoRadioButton, threeRadioButton, fourRadioButton, fiveRadioButton;
	private JScrollPane focusScrollPane, fiveScrollPane;
	private JTextField summonTextField;
	
	private ButtonGroup buttonGroup;
	private Summoner summoner;
	
	public GUI() {
		mainFrame = new JFrame("Fire Emblem Heroes Summon Simulator");
		mainPanel = new JPanel(new BorderLayout());
		bannerPanel = new JPanel(new BorderLayout());
		bannerSelectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 3));
		summonPanel = new JPanel(new GridBagLayout());
		resultPanel = new JPanel(new BorderLayout());
		appearancePanel = new JPanel(new GridBagLayout());
		heroesSummonedPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(6, 3, 0, 3);
		
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
		summonPanel.add(radioButtonsPanel, constraints);
		
		// Sets up remaining components of summonPanel (summonButton, massSummonButton, & summonTextField).
		summonButton = new JButton("Summon");
		summonButton.addActionListener(this);
		summonButton.setBackground(Color.WHITE);
		summonButton.setPreferredSize(new Dimension(120, 25));
		constraints.gridx = 1;
		summonPanel.add(summonButton, constraints);
		
		massSummonButton = new JButton("Mass Summon");
		massSummonButton.addActionListener(this);
		massSummonButton.setBackground(Color.WHITE);
		massSummonButton.setPreferredSize(new Dimension(120, 25));
		constraints.gridy = 1;
		summonPanel.add(massSummonButton, constraints);
		
		summonTextField = new JTextField();
		summonTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		summonTextField.setPreferredSize(new Dimension(185, 25));
		constraints.gridx = 0;
		summonPanel.add(summonTextField, constraints);
		
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
		
		mainPanel.add(bannerPanel, BorderLayout.NORTH);
		mainPanel.add(summonPanel, BorderLayout.CENTER);
		mainPanel.add(resultPanel, BorderLayout.SOUTH);
		
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
			int pulls = Integer.parseInt(summonTextField.getText());
			int fivePulls = Math.floorDiv(pulls, 5);
			int pullsAfterFivePulls = pulls % 5;
			int counter = 1;
			
			while (counter <= fivePulls) {	
				addToSummonedPanels(summoner.pull(5));
				counter++;
			}
			
			if (pullsAfterFivePulls != 0) {
				addToSummonedPanels(summoner.pull(pulls % 5));
			}
			
			currentFocusRateLabel.setText(Double.toString(summoner.getCurrentFocusRate()));
			currentFiveRateLabel.setText(Double.toString(summoner.getCurrentFiveRate()));
			currentFourRateLabel.setText(Double.toString(summoner.getCurrentFourRate()));
			currentThreeRateLabel.setText(Double.toString(summoner.getCurrentThreeRate()));
			
			resultPanel.revalidate();
		}
	}
	
	// Takes the returned List<String>[] from the Summoner class's "pull" method and updates the summoned panels.
	private void addToSummonedPanels(List<String>[] listArray) {
		for (int i = 0; i < listArray[0].size(); i++) {
			JLabel label = new JLabel(listArray[0].get(i), SwingConstants.CENTER);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			summonedFocusPanel.add(label);
		}
		
		for (int j = 0; j < listArray[1].size(); j++) {
			JLabel label = new JLabel(listArray[1].get(j), SwingConstants.CENTER);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			summonedFivePanel.add(label);
		}
	}
}
