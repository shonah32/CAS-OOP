package coursework;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComboBox;


public class MainFrame extends JFrame implements ActionListener{
	
	public static String[] users;
	public static String[] stock;
	public static Admin admin = null;
	public static Customer customer = null;
	public static Mouse mouse = null;
	public static Keyboard keyboard = null; 
	public static List<User> listUsers = new ArrayList<User>();  
	public static List<Product> listProduct = new ArrayList<Product>(); 
	private JPanel contentPane;
	public static String value;

	public static void readFiles() throws FileNotFoundException {
		File inputFile = new File("UserAccounts");
		Scanner fileScanner = new Scanner(inputFile); 
		
		while(fileScanner.hasNextLine()) {
			users = fileScanner.nextLine().split(","); 
			Address address = new Address(Integer.parseInt(users[3].trim()), users[4].trim(), users[5].trim()); 
			if(users[6].trim().equals("admin")) {
				admin = new Admin(Integer.parseInt(users[0].trim()), users[1].trim(), users[2].trim(), address);
				listUsers.add(admin);
			} 
			else {
				customer = new Customer(Integer.parseInt(users[0].trim()), users[1].trim(), users[2].trim(), address);
				listUsers.add(customer);
			}
			
		} //end of while loop 
		
		
		File inputFile2 = new File("Stock");
		Scanner fileScanner2 = new Scanner(inputFile2); 
		
		while(fileScanner2.hasNextLine()) {
			stock = fileScanner2.nextLine().split(","); 
			if(stock[1].trim().equals("mouse")) {
				mouse = new Mouse(Integer.parseInt(stock[0].trim()), stock[2].trim(), stock[3].trim(), stock[4].trim(), stock[5].trim(), Integer.parseInt(stock[6].trim()), Double.parseDouble(stock[7].trim()), Double.parseDouble(stock[8].trim()), "mouse", Integer.parseInt(stock[9].trim()));
				listProduct.add(mouse);
			}
			
			else {
				keyboard = new Keyboard(Integer.parseInt(stock[0].trim()), stock[2].trim(), stock[3].trim(), stock[4].trim(), stock[5].trim(), Integer.parseInt(stock[6].trim()), Double.parseDouble(stock[7].trim()), Double.parseDouble(stock[8].trim()), "keyboard", stock[9].trim());
				listProduct.add(keyboard); 
			}
			
		} //end of while loop 
		
	} //end of function 
	
	
	public static void main(String[] args) throws FileNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		readFiles(); 
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public MainFrame() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Computer Accessories Shop");
		lblNewLabel.setBounds(353, 95, 268, 25);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to the");
		lblNewLabel_1.setBounds(428, 64, 121, 19);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Please select a user:");
		lblNewLabel_2.setBounds(414, 165, 161, 16);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_2);
		
		
		JComboBox comboUserSelect = new JComboBox();
		comboUserSelect.addItem("Select User");
		comboUserSelect.setBounds(414, 208, 161, 25);
		contentPane.add(comboUserSelect);
		
		for(User name: listUsers) {
			comboUserSelect.addItem(name.getUsername().toString()); 
		} 
		
		comboUserSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
		        value = (String) comboUserSelect.getSelectedItem(); //get the selected item in the combobox
		        
		        for(User name: listUsers){
		        	if(name.getUsername() == value && name instanceof Admin) {
		        		System.out.println("Admin");
		        		dispose(); //gets rid of this View before opening a new window 
			            AdminView view1 = new AdminView(listProduct); //call the class
			            view1.setVisible(true); //sets the jframe to visible 
		        		break;
		        	}
		        	
		        	else if(name.getUsername() == value && name instanceof Customer){
		        		System.out.println("Customer"); 
		        		dispose(); 
			        	CustomerView view2 = new CustomerView(listProduct, value, listUsers); //call the class
			            view2.setVisible(true); //sets the jframe to visible 
		        		break;
		        	}
		        	
		        }    	
		 
		      }

		    }); //end of action listener 		
		
	} //end of MainFrame constructor 
	
	public void actionPerformed(ActionEvent e) {
		CustomerView customerView = new CustomerView(listProduct, value, listUsers);
		customerView.setVisible(true);
	}
	
} //end of class 


