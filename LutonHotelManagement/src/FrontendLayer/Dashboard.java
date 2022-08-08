package FrontendLayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BusinessLayer.BLUser;
import Helper.InputException;
import Models.User;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard {

	private JFrame mainFrame;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTable tblData;
	
	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 854, 549);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel paneltop = new JPanel();
		mainFrame.getContentPane().add(paneltop, BorderLayout.NORTH);
		
		JLabel lblName = new JLabel("Name");
		paneltop.add(lblName);
		
		tfName = new JTextField();
		paneltop.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		paneltop.add(lblAddress);
		
		tfAddress = new JTextField();
		paneltop.add(tfAddress);
		tfAddress.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// On click of the save button
				// Read data from the fields and store it in the model
				// Create an object of Business Layer and pass the model to business layer
				// Perform the required action from the business layer.
				try {
					User user = new User();
					user.setName(tfName.getText());
					user.setAddress(tfAddress.getText());
					
					BLUser blUser = new BLUser();
					blUser.setUser(user);
					user = blUser.save();
					JOptionPane.showMessageDialog(null, user.toString());
					
				}
				catch(InputException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		paneltop.add(btnSave);
		
		JPanel panelMain = new JPanel();
		mainFrame.getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tblData = new JTable();
		panelMain.add(tblData);
	}
	
	public void setVisible(boolean state) {
		mainFrame.setVisible(state);
	}

}
