import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JComboBox;

public class GUI extends BSBot {

	private JFrame frmBsBot;
	private JTextField hand_display;
	private JTextField players_field;
	private JTextArea player_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmBsBot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBsBot = new JFrame();
		
		
		frmBsBot.setTitle("BS Bot");
		frmBsBot.setResizable(false);
		frmBsBot.setBounds(100, 100, 367, 445);
		frmBsBot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel cardPane = new JPanel();
		cardPane.setBackground(Color.GRAY);
		frmBsBot.getContentPane().add(cardPane, BorderLayout.CENTER);
		cardPane.setLayout(new CardLayout(0, 0));
		
		
		JPanel start_page = new JPanel();
		start_page.setBackground(Color.GRAY);
		cardPane.add(start_page, "name_565574586286447");
		start_page.setLayout(null);
		
		
		JPanel main_page = new JPanel();
		main_page.setBackground(Color.GRAY);
		cardPane.add(main_page, "name_565587899991394");
		main_page.setLayout(null);
		
		player_field = new JTextArea();
		player_field.setFont(new Font("Monospaced", Font.PLAIN, 20));
		player_field.setEditable(false);
		player_field.setForeground(Color.BLACK);
		player_field.setBackground(Color.WHITE);
		player_field.setBounds(10, 361, 35, 36);
		main_page.add(player_field);
		
		hand_display = new JTextField();
		hand_display.setToolTipText("display of your hand");
		hand_display.setEditable(false);
		hand_display.setFont(new Font("Tahoma", Font.PLAIN, 27));
		hand_display.setBounds(25, 61, 308, 47);
		main_page.add(hand_display);
		hand_display.setColumns(10);
		
		JTextArea txtrHand = new JTextArea();
		txtrHand.setEditable(false);
		txtrHand.setForeground(Color.WHITE);
		txtrHand.setBackground(Color.GRAY);
		txtrHand.setFont(new Font("Monospaced", Font.PLAIN, 36));
		txtrHand.setText("Hand");
		txtrHand.setBounds(139, 13, 97, 47);
		main_page.add(txtrHand);

		JTextArea change_log = new JTextArea();
		change_log.setBackground(Color.GRAY);
		change_log.setForeground(Color.WHITE);
		change_log.setEditable(false);
		change_log.setBounds(22, 121, 311, 22);
		main_page.add(change_log);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(269, 171, 61, 22);
		for (String s : KEY) {
			comboBox.addItem(s);
		}
		main_page.add(comboBox);
		

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(25, 171, 61, 22);
		for (String s : KEY) {
			comboBox_1.addItem(s);
		}
		main_page.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setToolTipText("Select the next card you will be playing");
		comboBox_2.setBounds(269, 312, 61, 22);
		for (String s : KEY) {
			comboBox_2.addItem(s);
		}
		main_page.add(comboBox_2);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add the above card to hand");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox_1.getSelectedItem().toString().equals("")) {
					hand.add(comboBox_1.getSelectedItem().toString());
					change_log.setText("Added " + comboBox_1.getSelectedItem().toString() + " to hand.");
					updateDisplay();
				}
			}
		});
		btnAdd.setBounds(25, 206, 97, 25);
		main_page.add(btnAdd);
		
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setToolTipText("Remove the above card from hand");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().toString().equals("")) {
					if (hand.contains(comboBox.getSelectedItem().toString())) {
						hand.remove(comboBox.getSelectedItem().toString());
						change_log.setText("Removed " + comboBox.getSelectedItem().toString() + " from hand.");
					}
					else {
						change_log.setText("You do not have a " + comboBox.getSelectedItem().toString() + " in hand.");
					}
					updateDisplay();
				}
					
			}
		});
		btnRemove.setBounds(236, 206, 97, 25);
		main_page.add(btnRemove);
		
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nextCard = comboBox_2.getSelectedItem().toString();
				if (!nextCard.equals("")) {
					
					sort();
					change_log.setText("Sorted!");
					updateDisplay();
				}
				else {
					change_log.setText("To sort, enter the next card to play.");
				}
			}
		});
		btnSort.setToolTipText("Sorts by order to be played, not value");
		btnSort.setBounds(129, 253, 107, 22);
		main_page.add(btnSort);
		
		
		
		JTextArea txtrNumberOfPlayers = new JTextArea();
		txtrNumberOfPlayers.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtrNumberOfPlayers.setText("Number of Players");
		txtrNumberOfPlayers.setForeground(Color.WHITE);
		txtrNumberOfPlayers.setEditable(false);
		txtrNumberOfPlayers.setBackground(Color.GRAY);
		txtrNumberOfPlayers.setBounds(0, 343, 107, 16);
		main_page.add(txtrNumberOfPlayers);
		
		
		
		
		
		
		
		
		
		
		JTextArea txtrNoOfPlayers = new JTextArea();
		txtrNoOfPlayers.setText("No. of players");
		txtrNoOfPlayers.setForeground(Color.WHITE);
		txtrNoOfPlayers.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtrNoOfPlayers.setEditable(false);
		txtrNoOfPlayers.setBackground(Color.GRAY);
		txtrNoOfPlayers.setBounds(132, 258, 161, 22);
		start_page.add(txtrNoOfPlayers);
		
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.setToolTipText("Start game");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isNumeric(players_field.getText())) {
					players = Integer.parseInt(players_field.getText());
					CardLayout cl = (CardLayout) cardPane.getLayout();
					cl.show(cardPane, "name_565587899991394");
					updateDisplay();
				}
				else {
					txtrNoOfPlayers.setText("Invalid number of players.");
				}
					
				
				
			}
		});
		btnNewButton.setBounds(114, 326, 117, 35);
		start_page.add(btnNewButton);
		
		JTextArea txtrBsbot = new JTextArea();
		txtrBsbot.setEditable(false);
		txtrBsbot.setForeground(Color.WHITE);
		txtrBsbot.setBackground(Color.GRAY);
		txtrBsbot.setFont(new Font("Times New Roman", Font.BOLD, 36));
		txtrBsbot.setText("BS-Bot");
		txtrBsbot.setBounds(114, 35, 117, 48);
		start_page.add(txtrBsbot);
		
		players_field = new JTextField();
		players_field.setToolTipText("Enter the number of players");
		players_field.setBounds(132, 278, 82, 22);
		start_page.add(players_field);
		players_field.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.setToolTipText("Add a player");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				players++;
				updateDisplay();
			}
		});
		btnNewButton_1.setBounds(48, 367, 48, 16);
		main_page.add(btnNewButton_1);
		
		JButton button = new JButton("-");
		button.setToolTipText("Remove a player");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				players--;
				updateDisplay();
			}
		});
		button.setBounds(48, 381, 48, 16);
		main_page.add(button);
		
		JTextArea next = new JTextArea();
		next.setText("Next card to play");
		next.setForeground(Color.WHITE);
		next.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		next.setEditable(false);
		next.setBackground(Color.GRAY);
		next.setBounds(248, 283, 92, 16);
		main_page.add(next);
		
		JButton btnGetMove = new JButton("Get Move");
		btnGetMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextCard = comboBox_2.getSelectedItem().toString();
				if (!nextCard.equals("")) {
					
					List<String> tempHand = new ArrayList<String>();
					for (String s : hand) {
						tempHand.add(s);
					}
					sort();
					if (hand.size() == 0) {
						change_log.setText("You have no cards in your hand to play.");
					}
					else {
						String cd = hand.get(0);
						if (cd.charAt(0) == '(') {
							change_log.setText("BS from your " + hand.get(hand.size() - 1) + "s.");
						}
						else {
							change_log.setText("Play all of your " + hand.get(0) + "s.");
						}
					}
					hand = tempHand;
					comboBox_2.setSelectedItem("");
				}
				else {
					change_log.setText("To get next move, enter the next card to play.");
				}
		}});
		btnGetMove.setToolTipText("Suggests your next move.");
		btnGetMove.setBounds(129, 283, 107, 57);
		main_page.add(btnGetMove);
		
		
		
	}
	public void updateDisplay() {
		hand_display.setText("");
		for (String s : hand) {
			if (s.charAt(0) != '(') {
				hand_display.setText(hand_display.getText() + s + " ");
			}
		}
		player_field.setText(players + "");
	}
	public boolean isNumeric(String s) {
		try {
			int i = Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
