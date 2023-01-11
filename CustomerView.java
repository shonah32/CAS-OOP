package coursework;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class CustomerView extends JFrame {

	private JPanel contentPane;
	private static JTable tblShowProducts;
	public static List<Product> listBasket = new ArrayList<Product>();
	private JTable tblDisplayBasket;
	public static double totalPrice = 0;
	public static Object[] row = new Object[9];
	private static final DecimalFormat df = new DecimalFormat("0.00");
	public static String brandChosen = "Select Brand";
	public static String numButtons = "Select No. of Buttons";
	public static boolean bothChosen = false;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	
	/**
	 * Create the frame.
	 */
	
	public static void showTable(List<Product> listProduct) {
		
		String filePath = "Stock"; 
		File file = new File(filePath); 
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String[] columnHeaders = {"ID", "Item", "Type", "Brand", "Colour", "Connectivity", "Quantity in Stock", "Retail Price", "Additional Info"}; 
			DefaultTableModel model = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) { //function to make sure that the cells are not editable 
					return false; 
				}
				
			};
			
			tblShowProducts.setModel(model);
			model.setColumnIdentifiers(columnHeaders); //sets the column headers 
			
			MainFrame mainFrame = new MainFrame();  
			sortByRPrice rPriceComp = new sortByRPrice(); 
			Collections.sort(listProduct, rPriceComp); 
			
			for(Product prod: listProduct){
				if(prod instanceof Keyboard) {
					Keyboard keyboard = (Keyboard) prod; 
					Object[] product = {prod.getProductID(), prod.getWhichProduct(), keyboard.getKeyboardType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getRetailPrice(), (keyboard.getKeyboardLayout() + " layout")};
					model.addRow(product);
				}
				else if(prod instanceof Mouse) {
					Mouse mouse = (Mouse) prod;
					Object[] product = {prod.getProductID(), prod.getWhichProduct(), mouse.getMouseType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getRetailPrice(), (mouse.getButtonNums() + " buttons")};
					model.addRow(product);
				}
				
			}		

		} 
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
	
	public CustomerView(List<Product> listProduct, String value, List<User> listUsers) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcome = new JLabel("Welcome, Customer!");
		welcome.setBounds(35, 15, 202, 25);
		welcome.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		contentPane.add(welcome);
		
		JButton btnReturnLogin = new JButton("Return to Login");
		btnReturnLogin.setBounds(850, 17, 132, 29);
		contentPane.add(btnReturnLogin);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(16, 65, 967, 450);
		contentPane.add(tabbedPane);
		
		JPanel viewProducts = new JPanel();
		tabbedPane.addTab("View All Products", null, viewProducts, null);
		viewProducts.setLayout(null);
		
		tblShowProducts = new JTable();
		tblShowProducts.setBounds(19, 19, 908, 365);
		viewProducts.add(tblShowProducts);
		
		JScrollPane scrollPane = new JScrollPane(tblShowProducts);
		scrollPane.setBounds(20, 12, 906, 342);
		viewProducts.add(scrollPane);
		
		showTable(listProduct);
			
		JPanel viewBasket = new JPanel();
		tabbedPane.addTab("View My Shopping Basket", null, viewBasket, null);
		viewBasket.setLayout(null);
		
		tblDisplayBasket = new JTable();
		tblDisplayBasket.setEnabled(false);
		tblDisplayBasket.setBounds(20, 20, 906, 328);
		JScrollPane scrollPane_1 = new JScrollPane(tblDisplayBasket);
		scrollPane_1.setBounds(21, 12, 919, 339);
		viewBasket.add(scrollPane_1);
		
		totalPrice = 0;
		
		JButton btnAddBasket = new JButton("Add To Basket");
		btnAddBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				TableModel model1 = tblShowProducts.getModel(); 
				int[] indexes = tblShowProducts.getSelectedRows(); 
				DefaultTableModel model2 = (DefaultTableModel) tblDisplayBasket.getModel();
				String[] columnHeaders = {"ID", "Item", "Type", "Brand", "Colour", "Connectivity", "Quantity in Stock", "Retail Price", "Additional Info"}; 
				model2.setColumnIdentifiers(columnHeaders);
						
				boolean add = true;

				for(int i=0; i<indexes.length; i++) {
					row[0] = model1.getValueAt(indexes[i], 0); 
					row[1] = model1.getValueAt(indexes[i], 1); 
					row[2] = model1.getValueAt(indexes[i], 2); 
					row[3] = model1.getValueAt(indexes[i], 3); 
					row[4] = model1.getValueAt(indexes[i], 4); 
					row[5] = model1.getValueAt(indexes[i], 5); 
					row[6] = model1.getValueAt(indexes[i], 6); 
					row[7] = model1.getValueAt(indexes[i], 7); 
					row[8] = model1.getValueAt(indexes[i], 8); 
					
					if((Integer)row[6] == 0) {
						add = false;
					}		
					

					if(row[1].equals("mouse")) {		
						for(Product mice: listProduct) {
							if(mice.getWhichProduct() == "mouse") {
								if((Integer) row[0] == mice.getProductID()) {
									listBasket.add(mice); //adds the product in basket to the listBasket array list 
								}
							}
						}	
					}
					
					else if(row[1].equals("keyboard")){			
						for(Product keyboards: listProduct) {
							if(keyboards.getWhichProduct() == "keyboard") {
								if((Integer) row[0] == keyboards.getProductID()) {
									listBasket.add(keyboards); //adds the product in basket to the listBasket array list 
								}
							}	
						}	
					}

					System.out.println("lsit basket array " + listBasket);
					for(Product prod: listBasket) {
						if(prod.getProductID() == (Integer)row[0]){
							int count = Collections.frequency(listBasket, prod);
							System.out.println("count for " + prod.getProductID() + ": " + count);
							
							if(count > prod.getQuantityInStock()) {
								System.out.println("ERRORRRR");
								JOptionPane.showMessageDialog(null,  "Sorry, product " + prod.getProductID() + " is now out of stock.", "Error", JOptionPane.ERROR_MESSAGE); 
								System.out.println("before removal" + listBasket);
								listBasket.remove(prod);
								
								System.out.println("after removal " + listBasket);
								
								break;
							}
									
							else {
								System.out.println("CAN CHECKOUT");
								if(row[i] != null && add == true) {
									model2.addRow(row); 
									
									totalPrice += Double.parseDouble(row[7].toString());
								}
							}
							
							break;
							
						}
					}
				
					add = true; //allows the other items to be added to the basket even if one of the selected items is out of stock 
				
				}
				

			}
		}); 
		
		btnAddBasket.setBounds(20, 366, 117, 29);
		viewProducts.add(btnAddBasket);
		
		DefaultComboBoxModel model4 = new DefaultComboBoxModel();
		JComboBox comboFilterBrand = new JComboBox(model4);
		comboFilterBrand.addItem("Select Brand");
		
		for(Product prod: listProduct) { //adds the existing Brands in the products list to the combo box 
			if(model4.getIndexOf(prod.getProductBrand()) == -1 ) {
			    model4.addElement(prod.getProductBrand()); //only adds the Brand to the combobox items if it isn't already there 
			}
		}
		
		DefaultComboBoxModel model5 = new DefaultComboBoxModel();
		JComboBox comboFilterButtons = new JComboBox(model5);
		comboFilterButtons.addItem("Select No. of Buttons");
		comboFilterButtons.setSelectedItem("Select No. of Buttons");
		
		for(Product prod: listProduct) { //adds the existing numbers of mouse buttons into the combo box 
			if(prod instanceof Mouse) {
				Mouse mouse = (Mouse) prod; 
				if(model5.getIndexOf(mouse.getButtonNums()) == -1 ) {
				    model5.addElement(mouse.getButtonNums()); //only adds the number for mouse buttons to the combobox items if it isn't already there 
				}
			}
		}
		
		
		comboFilterButtons.setBounds(738, 364, 188, 32);
		viewProducts.add(comboFilterButtons);
		
		comboFilterBrand.setBounds(592, 364, 134, 32);
		viewProducts.add(comboFilterBrand);
		comboFilterBrand.setSelectedItem("Search By Brand");
		
		comboFilterBrand.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
		        brandChosen = (String) comboFilterBrand.getSelectedItem(); //get the selected item in the combobox
		        
		        if(!numButtons.equals("Select No. of Buttons")) {
		        	bothChosen = true;
		        	System.out.println(bothChosen); 
		        	String[] columnHeaders = {"ID", "Item", "Type", "Brand", "Colour", "Connectivity", "Quantity in Stock", "Retail Price", "Additional Info"}; 
					DefaultTableModel model = (DefaultTableModel)tblShowProducts.getModel(); 
					model.setRowCount(0);
					model.setColumnIdentifiers(columnHeaders); //sets the column headers 
					
					for(Product prod: listProduct){
						if(prod instanceof Mouse) {
							Mouse mouse = (Mouse) prod;
							if(prod.getProductBrand().equals(brandChosen) && String.valueOf(mouse.getButtonNums()).equals(numButtons)) {
								Object[] product = {prod.getProductID(), prod.getWhichProduct(), mouse.getMouseType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getRetailPrice(), (mouse.getButtonNums() + " buttons")};
								model.addRow(product);
							}
							
						}
						
						else if(brandChosen.equals("Select Brand")) {	
							comboFilterButtons.setSelectedItem("Select No. of Buttons");
							bothChosen = false;
							System.out.println(comboFilterButtons.getSelectedItem().toString());
							System.out.println(bothChosen);
							showTable(listProduct);
							break;
						}
						
					}
		        	
		        } //end of if statement
		        
		        else if(bothChosen == false){
		        	String[] columnHeaders = {"ID", "Item", "Type", "Brand", "Colour", "Connectivity", "Quantity in Stock", "Retail Price", "Additional Info"}; 
					DefaultTableModel model = (DefaultTableModel)tblShowProducts.getModel(); 
					model.setRowCount(0);
					model.setColumnIdentifiers(columnHeaders); //sets the column headers 
					
					for(Product prod: listProduct){
						if(prod instanceof Keyboard && prod.getProductBrand().equals(brandChosen)) {
							Keyboard keyboard = (Keyboard) prod; 
							Object[] product = {prod.getProductID(), prod.getWhichProduct(), keyboard.getKeyboardType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getRetailPrice(), (keyboard.getKeyboardLayout() + " layout")};
							model.addRow(product);
						}
						
						else if(prod instanceof Mouse && prod.getProductBrand().equals(brandChosen)) {
							Mouse mouse = (Mouse) prod;
							Object[] product = {prod.getProductID(), prod.getWhichProduct(), mouse.getMouseType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getRetailPrice(), (mouse.getButtonNums() + " buttons")};
							model.addRow(product);
						}
						
						else if(brandChosen.equals("Select Brand")) {
							comboFilterButtons.setSelectedItem("Select No. of Buttons");
							bothChosen = false;
							System.out.println(comboFilterButtons.getSelectedItem().toString());
							System.out.println(bothChosen);
							showTable(listProduct);
							break;
						}
						
					}
		        }
		        
		        
		        
			}
		}); 
		
		
		
		comboFilterButtons.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
		        numButtons = comboFilterButtons.getSelectedItem().toString(); //get the selected item in the combobox
		        
		        if(!brandChosen.equals("Select Brand")) {
		        	bothChosen = true;
		        	String[] columnHeaders = {"ID", "Item", "Type", "Brand", "Colour", "Connectivity", "Quantity in Stock", "Retail Price", "Additional Info"}; 
					DefaultTableModel model = (DefaultTableModel)tblShowProducts.getModel(); 
					model.setRowCount(0);
					model.setColumnIdentifiers(columnHeaders); //sets the column headers 	
					
					for(Product prod: listProduct){
						if(prod instanceof Mouse){
							Mouse mouse = (Mouse) prod;
							String nums = String.valueOf(mouse.getButtonNums());
							if(nums.equals(numButtons) && prod.getProductBrand().equals(brandChosen)) {
								Object[] product = {prod.getProductID(), prod.getWhichProduct(), mouse.getMouseType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getRetailPrice(), (mouse.getButtonNums() + " buttons")};
								model.addRow(product);
							}
						}

							else if(String.valueOf(numButtons).equals("Select No. of Buttons")){
								comboFilterBrand.setSelectedItem("Select Brand");
								bothChosen = false;
								System.out.println(comboFilterButtons.getSelectedItem().toString());
								System.out.println(bothChosen);
								showTable(listProduct);
								break;
							}
						
						}
						
		        } //end of if statement
		        
		        else if(bothChosen == false) {
		        	String[] columnHeaders = {"ID", "Item", "Type", "Brand", "Colour", "Connectivity", "Quantity in Stock", "Retail Price", "Additional Info"}; 
					DefaultTableModel model = (DefaultTableModel)tblShowProducts.getModel(); 
					model.setRowCount(0);
					model.setColumnIdentifiers(columnHeaders); //sets the column headers 
					
					for(Product prod: listProduct){
						if(prod instanceof Mouse){
							Mouse mouse = (Mouse) prod;
							String nums = String.valueOf(mouse.getButtonNums());
							if(nums.equals(numButtons)) {
								Object[] product = {prod.getProductID(), prod.getWhichProduct(), mouse.getMouseType(), prod.getProductBrand(), prod.getProductColour(), prod.getProductConnectivity(), prod.getQuantityInStock(), prod.getRetailPrice(), (mouse.getButtonNums() + " buttons")};
								model.addRow(product);
							}
						}

							else if(String.valueOf(numButtons).equals("Select No. of Buttons")){
								comboFilterBrand.setSelectedItem("Select Brand");
								bothChosen = false;
								System.out.println(comboFilterButtons.getSelectedItem().toString());
								System.out.println(bothChosen);
								showTable(listProduct);
								break;
							}
						
					}
		        }
		 
				
			}
		}); 
		
		JLabel lblNewLabel = new JLabel("Filter Searches:");
		lblNewLabel.setBounds(477, 371, 101, 16);
		viewProducts.add(lblNewLabel);
		
		for(int i=0; i<tblDisplayBasket.getRowCount(); i++) {
			System.out.println(tblDisplayBasket);
		}
			
		
		JButton btnClearBasket = new JButton("Clear Basket");
		btnClearBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) tblDisplayBasket.getModel();
				model.setRowCount(0);
				System.out.println("before " + listBasket);
				listBasket.removeAll(listBasket);
				System.out.println("after " + listBasket);
				totalPrice = 0;
				
			}
		});
		btnClearBasket.setBounds(311, 360, 117, 29);
		viewBasket.add(btnClearBasket);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tblDisplayBasket.selectAll();
				TableModel model1 = tblDisplayBasket.getModel(); 
				int indexes[] = tblDisplayBasket.getSelectedRows();
				for(int i=0; i<indexes.length;i++) {
					System.out.println("indexES " + indexes[i]);
				}
				
				Object[] row = new Object[9];
				CheckoutView checkout = new CheckoutView(totalPrice, row, listProduct, listBasket, listUsers, value); 
				DefaultTableModel model2 = (DefaultTableModel) checkout.checkoutProducts.getModel();
				
				for(int i=0; i<indexes.length; i++) {
					
					row[0] = model1.getValueAt(indexes[i], 0); 
					row[1] = model1.getValueAt(indexes[i], 1); 
					row[2] = model1.getValueAt(indexes[i], 2); 
					row[3] = model1.getValueAt(indexes[i], 3); 
					row[4] = model1.getValueAt(indexes[i], 4); 
					row[5] = model1.getValueAt(indexes[i], 5); 
					row[6] = model1.getValueAt(indexes[i], 6); 
					row[7] = model1.getValueAt(indexes[i], 7); 
					row[8] = model1.getValueAt(indexes[i], 8); 
					
					model2.addRow(row); 
				
				}
				
				dispose();
				checkout.setVisible(true);
				
			}
		});
		btnCheckout.setBounds(503, 360, 117, 29);
		viewBasket.add(btnCheckout);
		
		
		btnReturnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					listBasket.removeAll(listProduct);
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
} 

