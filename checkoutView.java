package coursework;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class CheckoutView extends JFrame {

	private JPanel contentPane;
	public JTable checkoutProducts;
	private JTextField txtCardNum;
	private JTextField txtPaypalEmail;
	private JTextField txtCardNum1;
	public static String[] fileRow;
	private JPasswordField txtSecCode;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the frame.
	 * @param totalPrice 
	 */
	public CheckoutView(double totalPrice, Object[] row, List<Product> listProduct, List<Product> listBasket, List<User> listUsers, String value) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Checkout");
		lblNewLabel.setBounds(35, 15, 178, 25);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		checkoutProducts = new JTable();
		checkoutProducts.setBounds(2, 2, 921, 0);
		contentPane.add(checkoutProducts);
		
		JScrollPane scrollPane = new JScrollPane(checkoutProducts);
		scrollPane.setBounds(35, 80, 925, 170);
		contentPane.add(scrollPane);
		
		DefaultTableModel model = (DefaultTableModel)checkoutProducts.getModel();
		String[] columnHeaders = {"ID", "Item", "Type", "Brand", "Colour", "Connectivity", "Quantity in Stock", "Retail Price", "Additional Info"}; 
		
		model.setColumnIdentifiers(columnHeaders);
	
		JLabel lblItemsBasket = new JLabel("Items In Your Basket:");
		lblItemsBasket.setBounds(35, 52, 149, 16);
		lblItemsBasket.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		contentPane.add(lblItemsBasket);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setBounds(35, 272, 82, 16);
		lblTotalPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		contentPane.add(lblTotalPrice);
		
		JLabel lblMoney = new JLabel();
		lblMoney.setBounds(35, 300, 170, 25);
		//DISPLAYS TOTAL PRICE ON SCREEN 
		lblMoney.setText("£" + df.format(totalPrice));
		lblMoney.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblMoney);
		
		JLabel lblPaymentDetails = new JLabel("Payment Details:");
		lblPaymentDetails.setBounds(269, 268, 117, 25);
		lblPaymentDetails.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		contentPane.add(lblPaymentDetails);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(257, 300, 703, 207);
		contentPane.add(tabbedPane);
		
		JPanel creditcard = new JPanel();
		tabbedPane.addTab("Pay By Credit Card", null, creditcard, null);
		creditcard.setLayout(null);
		
		JLabel enterDetails = new JLabel("Enter Your Card Details");
		enterDetails.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		enterDetails.setBounds(20, 20, 194, 16);
		creditcard.add(enterDetails);
		
		JLabel lblCardNum = new JLabel("Card Number (6 digits)");
		lblCardNum.setBounds(20, 61, 151, 16);
		creditcard.add(lblCardNum);
			
		JLabel lblSecCode = new JLabel("Security Code (3 digits)");
		lblSecCode.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblSecCode.setBounds(255, 61, 151, 16);
		creditcard.add(lblSecCode);
		
		txtCardNum1 = new JTextField();
		txtCardNum1.setBounds(20, 86, 151, 26);
		creditcard.add(txtCardNum1);
		txtCardNum1.setColumns(10);
		txtCardNum1.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { //function to not allow anything that's not an integer to be entered into the text field 
		        char c = e.getKeyChar();
		        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();  //If it's not a number, ignore the event
		        }
		     }
		});
		
		txtSecCode = new JPasswordField();
		txtSecCode.setBounds(255, 88, 151, 21);
		creditcard.add(txtSecCode);	
		txtSecCode.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { //function to not allow anything that's not an integer to be entered into the text field 
		        char c = e.getKeyChar();
		        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();  //If it's not a number, ignore the event
		        }
		     }
		});
		
		JButton placeOrder = new JButton("Place Order");
		placeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cardNo = txtCardNum1.getText(); 
				String secNo = String.valueOf(txtSecCode.getPassword());
				
				if(cardNo.length() == 6 && secNo.length() == 3) {
	
					int number = 0;
					
					String newContent = "";
					
					
					
					for(Product prod: listProduct) {
						if(listBasket.contains(prod)) {
							
							number = Collections.frequency(listBasket, prod); //checks how many times the product is added to the basket 
							
							System.out.println("in basket " + prod.getProductID());
							
							if(prod instanceof Mouse) {
								System.out.println("here1");
								Mouse mouse = (Mouse) prod; 
								newContent = newContent.concat(Integer.toString(prod.getProductID()) + ", "); 
								newContent = newContent.concat("mouse, ");
								newContent = newContent.concat(mouse.getMouseType() + ", ");
								newContent = newContent.concat(prod.getProductBrand() + ", ");
								newContent = newContent.concat(prod.getProductColour() + ", ");
								newContent = newContent.concat(prod.getProductConnectivity() + ", ");
								newContent = newContent.concat(Integer.toString(prod.getQuantityInStock()-1) + ", ");
								newContent = newContent.concat(Double.toString(prod.getOriginalPrice()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getRetailPrice()) + ", ");
								newContent = newContent.concat(Integer.toString(mouse.getButtonNums()) + "\n");
								
								prod.decrementQuantityInStock(number);
	
							}
							
							else if(prod instanceof Keyboard) {
								System.out.println("here2");
								Keyboard keyboard = (Keyboard) prod; 
								newContent = newContent.concat(Integer.toString(prod.getProductID()) + ", "); 
								newContent = newContent.concat("keyboard, ");
								newContent = newContent.concat(keyboard.getKeyboardType() + ", ");
								newContent = newContent.concat(prod.getProductBrand() + ", ");
								newContent = newContent.concat(prod.getProductColour() + ", ");
								newContent = newContent.concat(prod.getProductConnectivity() + ", ");
								newContent = newContent.concat(Integer.toString(prod.getQuantityInStock()-1) + ", ");
								newContent = newContent.concat(Double.toString(prod.getOriginalPrice()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getRetailPrice()) + ", ");
								newContent = newContent.concat(keyboard.getKeyboardLayout() + "\n");
								
								prod.decrementQuantityInStock(number);
								
							}
							
						}
						
						
						else {
							if(prod instanceof Mouse) {
								System.out.println("here3");
								Mouse mouse = (Mouse) prod; 
								newContent = newContent.concat(Integer.toString(prod.getProductID()) + ", "); 
								newContent = newContent.concat("mouse, ");
								newContent = newContent.concat(mouse.getMouseType() + ", ");
								newContent = newContent.concat(prod.getProductBrand() + ", ");
								newContent = newContent.concat(prod.getProductColour() + ", ");
								newContent = newContent.concat(prod.getProductConnectivity() + ", ");
								newContent = newContent.concat(Integer.toString(prod.getQuantityInStock()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getOriginalPrice()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getRetailPrice()) + ", ");
								newContent = newContent.concat(Integer.toString(mouse.getButtonNums()) + "\n");
	
							}
							
							else if(prod instanceof Keyboard) {
								System.out.println("here4");
								Keyboard keyboard = (Keyboard) prod; 
								newContent = newContent.concat(Integer.toString(prod.getProductID()) + ", "); 
								newContent = newContent.concat("keyboard, ");
								newContent = newContent.concat(keyboard.getKeyboardType() + ", ");
								newContent = newContent.concat(prod.getProductBrand() + ", ");
								newContent = newContent.concat(prod.getProductColour() + ", ");
								newContent = newContent.concat(prod.getProductConnectivity() + ", ");
								newContent = newContent.concat(Integer.toString(prod.getQuantityInStock()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getOriginalPrice()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getRetailPrice()) + ", ");
								newContent = newContent.concat(keyboard.getKeyboardLayout() + "\n");
								
							}
						}
						
					}
					
					//System.out.println(newContent);
					File inputFile = new File("Stock");
					try {
						FileWriter writer = new FileWriter(inputFile, false);
						writer.write(newContent.trim());
						writer.close();
						
						String houseNum = null;
						String city = null;
						String postcode = null;
					
						for(User users: listUsers) {
							if(value == users.getUsername()) {
								System.out.println(users.getUsername());
								houseNum = String.valueOf(users.getAddress().getHouseNum());
								city = String.valueOf(users.getAddress().getCity());
								postcode = String.valueOf(users.getAddress().getPostcode());
							}
						}
						
						JOptionPane.showMessageDialog(null, "£" +  df.format(totalPrice) + " paid using Credit Card, and the delivery address is " + houseNum + ", " + city + ", " + postcode + "." , "Success", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						MainFrame view1 = new MainFrame();
						view1.setVisible(true);
					} 
					
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
								
				} //end of if statement - validates that card no length is 6 and security code length is 3
				
				else {
					JOptionPane.showMessageDialog(null,  "Please enter 6 digits for the card number and 3 digits for the security code.", "Error", JOptionPane.ERROR_MESSAGE); //displays an error message if the card number and security code are in the wrong format
				}
				
				
				
			}//end of action event
	
		});
		
		
		placeOrder.setBounds(480, 86, 117, 29);
		creditcard.add(placeOrder);
		
		
		JPanel paypal = new JPanel();
		tabbedPane.addTab("Pay By PayPal", null, paypal, null);
		paypal.setLayout(null);
		
		JLabel enterDetails2 = new JLabel("Enter Your PayPal Details");
		enterDetails2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		enterDetails2.setBounds(20, 20, 202, 20);
		paypal.add(enterDetails2);

		JLabel LblEmail = new JLabel("PayPal Email");
		LblEmail.setBounds(20, 61, 151, 16);
		paypal.add(LblEmail);
		
		txtPaypalEmail = new JTextField();
		txtPaypalEmail.setBounds(20, 86, 202, 26);
		paypal.add(txtPaypalEmail);
		txtPaypalEmail.setColumns(10);
		
		JButton placeOrder2 = new JButton("Place Order");
		placeOrder2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = txtPaypalEmail.getText(); 
				boolean match = email.matches("[a-zA-Z0-9@.]+");
				if(match && email.contains("@") && email.contains(".")) {
	
					int number = 0;
					
					String newContent = "";
					
					for(Product prod: listProduct) {
						if(listBasket.contains(prod)) {
											
							number = Collections.frequency(listBasket, prod); //checks how many times the product is added to the basket 
								
							if(prod instanceof Mouse) {
								Mouse mouse = (Mouse) prod; 
								newContent = newContent.concat(Integer.toString(prod.getProductID()) + ", "); 
								newContent = newContent.concat("mouse, ");
								newContent = newContent.concat(mouse.getMouseType() + ", ");
								newContent = newContent.concat(prod.getProductBrand() + ", ");
								newContent = newContent.concat(prod.getProductColour() + ", ");
								newContent = newContent.concat(prod.getProductConnectivity() + ", ");
								newContent = newContent.concat(Integer.toString(prod.getQuantityInStock()-1) + ", ");
								newContent = newContent.concat(Double.toString(prod.getOriginalPrice()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getRetailPrice()) + ", ");
								newContent = newContent.concat(Integer.toString(mouse.getButtonNums()) + "\n");
								
								prod.decrementQuantityInStock(number);
								
							}
							
							else if(prod instanceof Keyboard) {
								Keyboard keyboard = (Keyboard) prod; 
								newContent = newContent.concat(Integer.toString(prod.getProductID()) + ", "); 
								newContent = newContent.concat("keyboard, ");
								newContent = newContent.concat(keyboard.getKeyboardType() + ", ");
								newContent = newContent.concat(prod.getProductBrand() + ", ");
								newContent = newContent.concat(prod.getProductColour() + ", ");
								newContent = newContent.concat(prod.getProductConnectivity() + ", ");
								newContent = newContent.concat(Integer.toString(prod.getQuantityInStock()-1) + ", ");
								newContent = newContent.concat(Double.toString(prod.getOriginalPrice()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getRetailPrice()) + ", ");
								newContent = newContent.concat(keyboard.getKeyboardLayout() + "\n");
								
								prod.decrementQuantityInStock(number);
								
							}
							
						}
						
						
						else {
							if(prod instanceof Mouse) {
								Mouse mouse = (Mouse) prod; 
								newContent = newContent.concat(Integer.toString(prod.getProductID()) + ", "); 
								newContent = newContent.concat("mouse, ");
								newContent = newContent.concat(mouse.getMouseType() + ", ");
								newContent = newContent.concat(prod.getProductBrand() + ", ");
								newContent = newContent.concat(prod.getProductColour() + ", ");
								newContent = newContent.concat(prod.getProductConnectivity() + ", ");
								newContent = newContent.concat(Integer.toString(prod.getQuantityInStock()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getOriginalPrice()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getRetailPrice()) + ", ");
								newContent = newContent.concat(Integer.toString(mouse.getButtonNums()) + "\n");
	
							}
							
							else if(prod instanceof Keyboard) {
								Keyboard keyboard = (Keyboard) prod; 
								newContent = newContent.concat(Integer.toString(prod.getProductID()) + ", "); 
								newContent = newContent.concat("keyboard, ");
								newContent = newContent.concat(keyboard.getKeyboardType() + ", ");
								newContent = newContent.concat(prod.getProductBrand() + ", ");
								newContent = newContent.concat(prod.getProductColour() + ", ");
								newContent = newContent.concat(prod.getProductConnectivity() + ", ");
								newContent = newContent.concat(Integer.toString(prod.getQuantityInStock()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getOriginalPrice()) + ", ");
								newContent = newContent.concat(Double.toString(prod.getRetailPrice()) + ", ");
								newContent = newContent.concat(keyboard.getKeyboardLayout() + "\n");
								
							}
						}
						
					}
					
					
					//System.out.println(newContent);
					File inputFile = new File("Stock");
					try {
						FileWriter writer = new FileWriter(inputFile, false);
						writer.write(newContent.trim());
						writer.close();
						
						String houseNum = null;
						String city = null;
						String postcode = null;
					
						for(User users: listUsers) {
							if(value == users.getUsername()) {
								System.out.println(users.getUsername());
								houseNum = String.valueOf(users.getAddress().getHouseNum());
								city = String.valueOf(users.getAddress().getCity());
								postcode = String.valueOf(users.getAddress().getPostcode());
							}
						}
						
						JOptionPane.showMessageDialog(null, "£" +  df.format(totalPrice) + " paid using Credit Card, and the delivery address is " + houseNum + ", " + city + ", " + postcode + "." , "Success", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						MainFrame view1 = new MainFrame();
						view1.setVisible(true);
					} 
					
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
								
				} //end of if statement - validates that card no length is 6 and security code length is 3
				
				else {
					JOptionPane.showMessageDialog(null,  "Please make sure you enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}//end of action event
	
		});
		
		
		placeOrder2.setBounds(480, 86, 117, 29);
		paypal.add(placeOrder2);
		
		
	}
} //end of class


