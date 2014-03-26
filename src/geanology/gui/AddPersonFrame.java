package geanology.gui;

import geanology.Person;
import geanology.client.GenealogyClient;
import geanology.packets.requests.AddPersonRequest;
import geanology.packets.responses.AddPersonResponse;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

public class AddPersonFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4837965865478419141L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;

	
	private geanology.Person toPerson() {
		String firstName = textField.getText();
		String lastName = textField_1.getText();
		String dateOfBirth_str = textField_3.getText();
		String placeOfBirth = textField_4.getText();
		String motherID_str = textField_5.getText();
		String fatherID_str = textField_6.getText();
		String trildrenID_str = textField_7.getText();
		String dateOfDeath_str = textField_8.getText();
		String placeOfDeath = textField_9.getText();
		String biography = textField_10.getText();
		
		int motherID = 0;
		try {
			motherID = Integer.parseInt(motherID_str);
		} catch(NumberFormatException e) {
			System.out.println("Invalid mother ID! "+motherID_str);
		}
		
		int fatherID = 0;
		try {
			fatherID = Integer.parseInt(fatherID_str);
		} catch(NumberFormatException e) {
			System.out.println("Invalid father ID! "+fatherID_str);
		}
		
		String[] trildrenID_strArray = trildrenID_str.split(",");
		int[] trildrenID = new int[trildrenID_strArray.length];
		for(int i = 0; i < trildrenID.length; i++) {
			try {
				int trildID = Integer.parseInt(trildrenID_strArray[i]);
				trildrenID[i] = trildID;
			} catch(NumberFormatException e) {
				System.out.println("Invalid trild ID! "+trildrenID_strArray[i]);
			}
		}
		
		Date dateOfBirth = null, dateOfDeath = null;
		if(!dateOfBirth_str.equals("")) {
			dateOfBirth = convertStringToDate(dateOfBirth_str);
		} else if(!dateOfDeath_str.equals("")) {
			dateOfDeath = convertStringToDate(dateOfDeath_str);
		}
	
		
		
		return new Person(0, firstName, lastName, dateOfBirth, placeOfBirth, motherID, fatherID, trildrenID, placeOfDeath, dateOfDeath, biography);
		
		
	}
	
	public java.sql.Date convertStringToDate(String d) {
		String[] dateParts = d.split("/");
		try {
			int day = Integer.parseInt(dateParts[0]);
			int month = Integer.parseInt(dateParts[1]);
			int year = Integer.parseInt(dateParts[2]);
			if(year > 1900)
				year -= 1900;
			return new java.sql.Date(year, month, day);
		} catch(NumberFormatException e) {
			System.out.println("Invalid date format: "+d);
			System.out.println("Should be dd/mm/yyyy");
			return null;
		}
	}

	/**
	 * Create the frame.
	 */
	public AddPersonFrame() {
		super("Add Person");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 509, 352);
		contentPane = new JPanel();
		contentPane.setToolTipText("ds");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[107px][114px,grow][]",
				"[19px][15px][15px][15px][15px][][][][][][][]"));

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblName, "cell 0 0,alignx left,aligny center");

		textField = new JTextField();
		contentPane.add(textField, "cell 1 0,growx,aligny center");
		textField.setColumns(10);

		JLabel lblSurname = new JLabel("Surname:");
		contentPane.add(lblSurname, "cell 0 1,alignx left,aligny center");

		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		contentPane.add(lblId, "cell 0 2,alignx left,aligny center");

		textField_2 = new JTextField();
		contentPane.add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		textField_2.setEditable(false);

		JLabel lblPlaceOfBirth = new JLabel("Date of birth:");
		contentPane.add(lblPlaceOfBirth, "cell 0 3,alignx left,aligny center");

		textField_3 = new JTextField();
		contentPane.add(textField_3, "cell 1 3,growx");
		textField_3.setColumns(10);

		JLabel lblPlaceOfDeath = new JLabel("Place of birth:");
		contentPane.add(lblPlaceOfDeath, "cell 0 4,alignx left,aligny top");

		textField_4 = new JTextField();
		contentPane.add(textField_4, "cell 1 4,growx");
		textField_4.setColumns(10);

		JLabel lblMotherId = new JLabel("Mother ID:");
		lblMotherId.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblMotherId, "cell 0 5,alignx left");

		textField_5 = new JTextField();
		contentPane.add(textField_5, "cell 1 5,growx");
		textField_5.setColumns(10);

		JLabel lblFatherId = new JLabel("Father ID:");
		contentPane.add(lblFatherId, "cell 0 6,alignx left");

		textField_6 = new JTextField();
		contentPane.add(textField_6, "cell 1 6,growx");
		textField_6.setColumns(10);

		JLabel lblChildId = new JLabel("Child ID:");
		contentPane.add(lblChildId, "cell 0 7,alignx left");

		textField_7 = new JTextField();
		contentPane.add(textField_7, "cell 1 7,growx");
		textField_7.setColumns(10);

		JLabel lblDateOfDeath = new JLabel("Date of death:");
		contentPane.add(lblDateOfDeath, "cell 0 8,alignx left");

		textField_8 = new JTextField();
		contentPane.add(textField_8, "cell 1 8,growx");
		textField_8.setColumns(10);

		JLabel lblPlaceOfDeath_1 = new JLabel("Place of death:");
		contentPane.add(lblPlaceOfDeath_1, "cell 0 9,alignx trailing");

		textField_9 = new JTextField();
		contentPane.add(textField_9, "cell 1 9,growx");
		textField_9.setColumns(10);
		
		JLabel lblBiography = new JLabel("Biography:");
		contentPane.add(lblBiography, "cell 0 10,alignx trailing");
		
		textField_10 = new JTextField();
		contentPane.add(textField_10, "cell 1 10,growx");
		textField_10.setColumns(10);

		JButton btnEdit = new JButton("Add");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Person thisPerson = toPerson();
				if(thisPerson == null) {
					System.out.println("Now null!");
				}
				AddPersonRequest addRequest = new AddPersonRequest(toPerson());
				AddPersonResponse response = (AddPersonResponse) GenealogyClient.getResponseForRequest(addRequest);
				PersonFrame frame = new PersonFrame(response.getPersonAdded());
				frame.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnEdit, "cell 2 10,growx");

		JButton btnDelete = new JButton("Cancel");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnDelete, "cell 2 11");
	}
}