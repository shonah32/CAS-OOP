package coursework;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;

public class AdminView extends JFrame {

	private JPanel contentPane;
	private JTable jTable1;
	private JTextField newIDInput;
	private JTextField newBrandInput;
	private JTextField newColourInput;
	private JTextField newStockInput;
	private JTextField newOGPriceInput;
	private JTextField newRetailPriceInput;
	private JTextField txtMouseButtons;
	public static Mouse mouse = null;
	public static Keyboard keyboard = null; 
	public static String typeSelected = "";
	public static boolean writeToFile = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	

	public AdminView(List<Product> listProduct) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome, Admin!");
		lblNewLabel.setBounds(35, 15, 178, 25);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Return to Login");
		btnNewButton.setBounds(850, 17, 132, 29);
		contentPane.add(btnNewButton);
		
		jTable1 = new JTable();
		jTable1.setBounds(28, 80, 632, 428);
		JScrollPane scrollPane = new JScrollPane(jTable1); 
		scrollPane.setBounds(35, 80, 579, 428);
		contentPane.add(scrollPane);
		
		JButton jButtonImport = new JButton("View Products");
		
		jButtonImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String filePath = "Stock"; 
				File file = new File(filePath); 
				
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String[] columnHeaders = {"ID", "Item", "Type", "Brand", "Colour", "Connectivity", "Quantity in Stock", "Original Price", "Retail Price", "Additional Info"}; 
					DefaultTableModel model = new DefaultTableModel() {
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {
							return false; //function to make sure that the cells are not editable 
						}
					};
					
					jTable1.setModel(model);
					
					
					model.setRowCount(0); //sets it so that the data does not repeatedly get output at the bottom of the table if the button is clicked again  
					model.setColumnIdentifiers(columnHeaders); //sets the column headers 
									
					MainFrame mainFrame = new MainFrame();  
					sortByRPrice rPriceComp = new sortByRPrice(); 
					Collections.sort(listProduct, rPriceComp); 
					
					for(Product prod: listProduct){
						if(prod instanceof Keyboard) {
							Keyboard keyboard = (Keyboard) prod; 
							Object[] product = {prod.getProductID(), prod.getWhichProduct(), keyboard.getKeyboardType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getOriginalPrice(), prod.getRetailPrice(), (keyboard.getKeyboardLayout() + " layout")};
							model.addRow(product);
						}
						else if(prod instanceof Mouse) {
							Mouse mouse = (Mouse) prod;
							Object[] product = {prod.getProductID(), prod.getWhichProduct(), mouse.getMouseType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getOriginalPrice(), prod.getRetailPrice(), (mouse.getButtonNums() + " buttons")};
							model.addRow(product);
						}
						
					}		
		
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
			}
		});
		
		jButtonImport.setBounds(28, 47, 117, 29);
		contentPane.add(jButtonImport);
		
		JLabel lblNewLabel_1 = new JLabel("Add Product");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(645, 60, 103, 16);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(645, 80, 337, 428);
		contentPane.add(panel);
		panel.setLayout(null);
		
		newIDInput = new JTextField();
		newIDInput.setBounds(18, 50, 130, 26);
		panel.add(newIDInput);
		newIDInput.setColumns(10);
		newIDInput.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { //function to not allow anything that's not an integer to be entered into the text field 
		        char c = e.getKeyChar();
		        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();  //If it's not a number, ignore the event
		        }
		     }
		});
		
		JLabel newIDLabel = new JLabel("ID Number");
		newIDLabel.setBounds(18, 30, 73, 16);
		panel.add(newIDLabel);
		newIDLabel.setForeground(Color.WHITE);
		
		JLabel newTypeLabel = new JLabel("Device Type");
		newTypeLabel.setForeground(Color.WHITE);
		newTypeLabel.setBounds(18, 167, 83, 16);
		panel.add(newTypeLabel);
		
		String keyboardTypes[] = {"Select Keyboard Type", "standard", "gaming", "flexible"};
		JComboBox keyboardOptions = new JComboBox(keyboardTypes);
		keyboardOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!("Select Keyboard Type".equals(keyboardOptions.getSelectedItem()))) {
					typeSelected = keyboardOptions.getSelectedItem().toString().trim();
					System.out.println("KEYBOARD chOOOSEEEe " + typeSelected);
				}
			}
		});
		keyboardOptions.setBounds(18, 221, 130, 27);
		keyboardOptions.setEnabled(false);
		panel.add(keyboardOptions);
		
		String mouseTypes[] = {"Select Mouse Type", "standard", "gaming"};
		JComboBox mouseOptions = new JComboBox(mouseTypes);
		mouseOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!("Select Mouse Type".equals(mouseOptions.getSelectedItem()))) {
					typeSelected = mouseOptions.getSelectedItem().toString().trim();
					System.out.println("MOUSE chOOOSEEEe " + typeSelected);
				}
			}
		});
		mouseOptions.setBounds(18, 187, 130, 27);
		mouseOptions.setEnabled(false);
		panel.add(mouseOptions);
		
		String layout[] = {"Select Layout", "UK", "US"};
		JComboBox comboKeyboardLayout = new JComboBox(layout);
		comboKeyboardLayout.setEnabled(false);
		comboKeyboardLayout.setBounds(188, 304, 130, 27);
		panel.add(comboKeyboardLayout);
		
		txtMouseButtons = new JTextField();
		txtMouseButtons.setEnabled(false);
		txtMouseButtons.setColumns(10);
		txtMouseButtons.setBounds(188, 334, 130, 26);
		panel.add(txtMouseButtons);
		txtMouseButtons.setText("0");
		txtMouseButtons.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { //function to not allow anything that's not an integer to be entered into the text field 
		        char c = e.getKeyChar();
		        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();  //If it's not a number, ignore the event
		        }
		     }
		});
		
		String items[] = {"Select Product", "keyboard", "mouse"};
		JComboBox newItemInput = new JComboBox(items);
		newItemInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("keyboard".equals(newItemInput.getSelectedItem())) {
					keyboardOptions.setEnabled(true);
					mouseOptions.setEnabled(false);
					comboKeyboardLayout.setEnabled(true);
					txtMouseButtons.setEnabled(false);
					txtMouseButtons.setText("");
					mouseOptions.setSelectedIndex(0);
				}
				
				else if("mouse".equals(newItemInput.getSelectedItem())) {
					mouseOptions.setEnabled(true);
					keyboardOptions.setEnabled(false);
					keyboardOptions.setSelectedIndex(0);
					comboKeyboardLayout.setEnabled(false);
					comboKeyboardLayout.setSelectedIndex(0);
					txtMouseButtons.setEnabled(true);
				}
				
				else {
					keyboardOptions.setEnabled(false);
					mouseOptions.setEnabled(false);
					comboKeyboardLayout.setEnabled(false);
					txtMouseButtons.setEnabled(false);
					txtMouseButtons.setText("");
					comboKeyboardLayout.setSelectedIndex(0);
					keyboardOptions.setSelectedIndex(0);
					mouseOptions.setSelectedIndex(0);
				}
			}
		});
		
		
		newItemInput.setBounds(18, 118, 130, 27);
		panel.add(newItemInput);
		
		JLabel newItemLabel = new JLabel("Item");
		newItemLabel.setForeground(Color.WHITE);
		newItemLabel.setBounds(18, 98, 83, 16);
		panel.add(newItemLabel);
		
		JLabel newBrandLabel = new JLabel("Brand");
		newBrandLabel.setForeground(Color.WHITE);
		newBrandLabel.setBounds(18, 260, 83, 16);
		panel.add(newBrandLabel);
		
		newBrandInput = new JTextField();
		newBrandInput.setColumns(10);
		newBrandInput.setBounds(18, 277, 130, 26);
		panel.add(newBrandInput);
		
		JLabel newColourLabel = new JLabel("Colour");
		newColourLabel.setForeground(Color.WHITE);
		newColourLabel.setBounds(18, 315, 83, 16);
		panel.add(newColourLabel);
		
		newColourInput = new JTextField();
		newColourInput.setColumns(10);
		newColourInput.setBounds(18, 334, 130, 26);
		panel.add(newColourInput);
		
		JLabel newConnectLabel = new JLabel("Connectivity");
		newConnectLabel.setForeground(Color.WHITE);
		newConnectLabel.setBounds(188, 30, 83, 16);
		panel.add(newConnectLabel);
		
		String connect[] = {"wired", "wireless"}; 
		JComboBox newConnectInput = new JComboBox(connect);
		newConnectInput.setBounds(188, 51, 130, 27);
		panel.add(newConnectInput);
		
		JLabel newStockLabel = new JLabel("Quantity in Stock");
		newStockLabel.setForeground(Color.WHITE);
		newStockLabel.setBounds(188, 98, 114, 16);
		panel.add(newStockLabel);
	
		newStockInput = new JTextField();	
		newStockInput.setColumns(10);
		newStockInput.setBounds(188, 118, 130, 26);
		panel.add(newStockInput);
		newStockInput.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { //function to not allow anything that's not an integer to be entered into the text field 
		        char c = e.getKeyChar();
		        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();  //If it's not a number, ignore the event
		        }
		     }
		});
		
		
		JLabel newOGPriceLabel = new JLabel("Original Price");
		newOGPriceLabel.setForeground(Color.WHITE);
		newOGPriceLabel.setBounds(188, 167, 89, 16);
		panel.add(newOGPriceLabel);
		
		newOGPriceInput = new JTextField();
		newOGPriceInput.setColumns(10);
		newOGPriceInput.setBounds(188, 186, 130, 26);
		panel.add(newOGPriceInput);
		
		JLabel newRetailPriceLabel = new JLabel("Retail Price");
		newRetailPriceLabel.setForeground(Color.WHITE);
		newRetailPriceLabel.setBounds(188, 224, 89, 16);
		panel.add(newRetailPriceLabel);
		
		newRetailPriceInput = new JTextField();
		newRetailPriceInput.setColumns(10);
		newRetailPriceInput.setBounds(188, 243, 130, 26);
		panel.add(newRetailPriceInput);
		
		JLabel newAdditionalInfoLabel = new JLabel("Additional Info");
		newAdditionalInfoLabel.setForeground(Color.WHITE);
		newAdditionalInfoLabel.setBounds(188, 282, 100, 16);
		panel.add(newAdditionalInfoLabel);
		
		
		JButton addProductBtn = new JButton("Submit");
		addProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeToFile = true;
				
				for(Product prod: listProduct) {
					if(prod.getProductID() == Integer.parseInt(newIDInput.getText())) {
						JOptionPane.showMessageDialog(null,  "Please make sure ID number is not the same as an existing product's ID.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if anything other than an integer is entered as the quantity in stock 
					     writeToFile = false;
					     System.out.println(newIDInput.getText());
					}
				}
				
				String doublePattern = ("\\b\\d{1,5}\\.\\d{1,2}\\b"); //makes sure that the maximum numbers you can have before the decimal is 5, and the maximum after is 2 decimal places 
				
				boolean OGPrice = Pattern.matches(doublePattern, newOGPriceInput.getText()); //checks if the original price entered is in the correct format 
				boolean retailPrice = Pattern.matches(doublePattern, newRetailPriceInput.getText()); //checks if the retail price entered is in the correct format 
				
				if(OGPrice == false) {
					JOptionPane.showMessageDialog(null,  "Please make sure the original price is a double value, and not more than 2 decimal places.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if anything other than the correct format is entered as the original price 
					writeToFile = false;
				}
				
				else if(retailPrice == false) {
					JOptionPane.showMessageDialog(null,  "Please make sure the retail price is a double value, and not more than 2 decimal places.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if anything other than the correct format is entered as the retail price
					writeToFile = false; 
				}
				
				else if(newIDInput.getText() == "" || newBrandInput.getText() == "" || newColourInput.getText() == "" || newStockInput.getText() == "" || newOGPriceInput.getText() == "" || newRetailPriceInput.getText() == "" || newItemInput.getSelectedItem().toString() == "Select Product") { //if any of the fields are empty 
					JOptionPane.showMessageDialog(null,  "Please make sure all input fields are filled out.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if any of the fields are left blank 
					writeToFile = false; 
					System.out.println("input error");
				}
				
				else if(newIDInput.getText().length() != 6) {
					JOptionPane.showMessageDialog(null,  "Please make sure the ID number is 6 digits.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if any of the fields are left blank 
					writeToFile = false; 
				}
				
				else if(newStockInput.getText().length() > 5) {
					JOptionPane.showMessageDialog(null,  "Please make sure the quantity in stock is not greater than 99,999.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if any of the fields are left blank 
					writeToFile = false; 
				}
				
				
				
				if(!(newItemInput.getSelectedItem().toString() == "Select Product")) {
					if(newItemInput.getSelectedItem().toString() == "keyboard") {
						if(keyboardOptions.getSelectedItem().toString() == "Select Keyboard Type" || comboKeyboardLayout.getSelectedItem().toString() == "Select Layout" ) {
							JOptionPane.showMessageDialog(null,  "Please make sure all input fields are filled out.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if any of the fields are left blank 
							writeToFile = false; 
							System.out.println("keyboard error");
						}
					}
					
					else if(newItemInput.getSelectedItem().toString() == "mouse") {
						if(mouseOptions.getSelectedItem().toString() == "Select Mouse Type" || txtMouseButtons.getText().isEmpty()) { 
							JOptionPane.showMessageDialog(null,  "Please make sure all input fields are filled out.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if any of the fields are left blank 
							writeToFile = false; 
							System.out.println("mouse error");
						}
						
						else if(Integer.parseInt(txtMouseButtons.getText()) > 50) {
							JOptionPane.showMessageDialog(null,  "Please make sure the number of buttons on the mouse is not more than 50.", "Error", JOptionPane.ERROR_MESSAGE); //shows an error message if any of the fields are left blank 
							writeToFile = false; 
						}
					}
				}
				
				
				
				if(writeToFile == true) {
					String itemDetailsMouse = newIDInput.getText().trim() + ", " + newItemInput.getSelectedItem().toString().trim() + ", " + typeSelected + ", " + newBrandInput.getText().trim() + ", " + newColourInput.getText().trim() + ", " + newConnectInput.getSelectedItem().toString().trim() + ", " + newStockInput.getText().trim() + ", " + newOGPriceInput.getText().trim() + ", " + newRetailPriceInput.getText().trim() + ", " + txtMouseButtons.getText().trim() ; 
					String itemDetailsKeyboard = newIDInput.getText().trim() + ", " + newItemInput.getSelectedItem().toString().trim() + ", " + typeSelected + ", " + newBrandInput.getText().trim() + ", " + newColourInput.getText().trim() + ", " + newConnectInput.getSelectedItem().toString().trim() + ", " + newStockInput.getText().trim() + ", " + newOGPriceInput.getText().trim() + ", " + newRetailPriceInput.getText().trim() + ", " + comboKeyboardLayout.getSelectedItem().toString(); 
					FileWriter writer; 
					try {	
						File input = new File("Stock");
						writer = new FileWriter("Stock", true);
						
						if(newItemInput.getSelectedItem() == "mouse") {
							writer.write("\n" + itemDetailsMouse); //writes the newly added product's details to the Stock file 
							writer.close();
							mouse = new Mouse(Integer.parseInt(newIDInput.getText()), typeSelected, newBrandInput.getText(), newColourInput.getText(), newConnectInput.getSelectedItem().toString(), Integer.parseInt(newStockInput.getText()), Double.parseDouble(newOGPriceInput.getText()), Double.parseDouble(newRetailPriceInput.getText()), "mouse", Integer.parseInt(txtMouseButtons.getText()));
							listProduct.add(mouse); //adds the new product to the array list of products 
						}
						
						else {
							writer.write("\n" + itemDetailsKeyboard); //writes the newly added product's details to the Stock file 
							writer.close();
							keyboard = new Keyboard(Integer.parseInt(newIDInput.getText()), typeSelected, newBrandInput.getText(), newColourInput.getText(), newConnectInput.getSelectedItem().toString(), Integer.parseInt(newStockInput.getText()), Double.parseDouble(newOGPriceInput.getText()), Double.parseDouble(newRetailPriceInput.getText()), "keyboard", comboKeyboardLayout.getSelectedItem().toString());
							listProduct.add(keyboard); //adds the new product to the array list of products 
						}
						
						MainFrame mainFrame = new MainFrame();  
						sortByRPrice rPriceComp = new sortByRPrice(); 
						Collections.sort(listProduct, rPriceComp); //sorts the products list by retail price 
						
					}
					catch(IOException e1){
						e1.printStackTrace(); 
					}
					
					newIDInput.setText(""); //clears the text fields from previous entries 
					newItemInput.setSelectedIndex(0); //clears the combo box selection but does not empty it of its selection choices 
					keyboardOptions.setSelectedIndex(0);
					mouseOptions.setSelectedIndex(0);
					keyboardOptions.setEnabled(false);
					comboKeyboardLayout.setEnabled(false);
					comboKeyboardLayout.setSelectedIndex(0);
					txtMouseButtons.setText("0");
					mouseOptions.setEnabled(false);
					txtMouseButtons.setEnabled(false);
					newBrandInput.setText("");  
					newColourInput.setText("");
					newConnectInput.setSelectedIndex(0);
					newStockInput.setText("");
					newOGPriceInput.setText("");
					newRetailPriceInput.setText("");
					txtMouseButtons.setText(""); 
				}

			}
		});
		
		
		addProductBtn.setForeground(Color.BLACK);
		addProductBtn.setBounds(111, 371, 117, 29);
		panel.add(addProductBtn);
		
		
		JLabel lblNewLabel_2 = new JLabel("<- Press to Update Table");
		lblNewLabel_2.setBounds(146, 52, 165, 16);
		contentPane.add(lblNewLabel_2);
		
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					MainFrame view1 = new MainFrame();
					view1.setVisible(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		
	}
} //end of class


