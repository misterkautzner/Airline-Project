package airline_package;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Airline extends JFrame{
	
	private JTextField fieldFirstName;
	private JTextField fieldLastName;
	private JTextField fieldAge;
	private JTextField fieldDateOfBirth;
	private JTextField fieldPlaceOfDeparture;
	private JTextField fieldDestination;
	private JTextField fieldTravelDate;
	
	private JLabel labelMessage;
	private JLabel blankLine;
	private JButton buttonSubmit;

	public Airline() {
		createView();
		
		setTitle("Airline Flight Form");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 250);
		// Centers frame to middle of screen
		setLocationRelativeTo(null);
		setResizable(true);
	}
	
	private void createView() {
		JPanel panelMain = new JPanel();
		getContentPane().add(panelMain);
		
		JPanel panelForm = new JPanel(new GridBagLayout());
		panelMain.add(panelForm);
//		getContentPane().add(panelForm);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;  // Right aligns the following labels
		
		
		panelForm.add(new JLabel("First Name: "), c);
		c.gridy++;
		panelForm.add(new JLabel("Last Name: "), c);
		c.gridy++;
		panelForm.add(new JLabel("Age: "), c);
		c.gridy++;
		panelForm.add(new JLabel("Date of Birth: "), c);
		c.gridy++;
		panelForm.add(new JLabel("Place of Departure: "), c);
		c.gridy++;
		panelForm.add(new JLabel("Destination: "), c);
		c.gridy++;
		panelForm.add(new JLabel("Travel Date: "), c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;  //  Left aligns the following text fields
		// fieldName = new JTextField();
		fieldFirstName = new JTextField(12);	
		panelForm.add(fieldFirstName, c);
		c.gridy++;
		fieldLastName = new JTextField(12);
		panelForm.add(fieldLastName, c);
		c.gridy++;
		fieldAge = new JTextField(12);
		panelForm.add(fieldAge, c);
		c.gridy++;
		fieldDateOfBirth = new JTextField(12);
		panelForm.add(fieldDateOfBirth, c);
		c.gridy++;
		fieldPlaceOfDeparture = new JTextField(12);
		panelForm.add(fieldPlaceOfDeparture, c);
		c.gridy++;
		fieldDestination = new JTextField(12);
		panelForm.add(fieldDestination, c);
		c.gridy++;
		fieldTravelDate = new JTextField(12);
		panelForm.add(fieldTravelDate, c);
		
		
		
		// Make an action listener connected to a submit button that grabs all of these
		buttonSubmit = new JButton("Submit");
		buttonSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = fieldFirstName.getText();
				String lastName = fieldLastName.getText();
				
				int age;
				try {
				age = Integer.parseInt(fieldAge.getText());
				}
				catch(NumberFormatException ex) {
					age = -1;
				}
				
				SimpleDateFormat sqlDate = new SimpleDateFormat("YYYY-MM-DD");
				java.sql.Date dateOfBirth;
				java.util.Date dob;
				try {
					dob = sqlDate.parse(fieldDateOfBirth.getText());
					dateOfBirth = new java.sql.Date(dob.getTime());
				}
				catch(ParseException parseEx) {
					dateOfBirth = null; //new SimpleDateFormat("yyyy-mm-dd").parse("0000-00-00");
				}
				//String dateOfBirth = fieldDateOfBirth.getText();
				
				
				String placeOfDeparture = fieldPlaceOfDeparture.getText();
				String destination = fieldDestination.getText();
				
				java.sql.Date travelDate;
				java.util.Date travDate;
				try {
					travDate = sqlDate.parse(fieldTravelDate.getText());
					travelDate = new java.sql.Date(travDate.getTime());
				}
				catch(ParseException parseEx) {
					travelDate = null; 
				}
				//String travelDate = fieldTravelDate.getText();
				if (age == -1) 
					labelMessage.setText("Age must be an integer.");
				
				else if (dateOfBirth == null)
					labelMessage.setText("Date of Birth must be in form: 'yyyy-mm-dd'");
				
				else if (travelDate == null)
					labelMessage.setText("Travel Date must be in form: 'yyyy-mm-dd'");
				
				else if (firstName.isEmpty() || lastName.isEmpty() ||
						placeOfDeparture.isEmpty() || destination.isEmpty()) {
					labelMessage.setText("You must fill all fields.");
				}
				
				else {
					
					Traveler traveler = new Traveler(0, firstName, lastName, age, dateOfBirth,
							placeOfDeparture, destination, travelDate);
						
					AirlineDAO airDAO = new AirlineDAO();
					airDAO.addToDB(traveler);
					// What happens with the info....?  I think I figured it out already.
					
					dispose();
				}
			}
		});
		
		c.gridy++;
		c.anchor = GridBagConstraints.CENTER;
		panelForm.add(buttonSubmit, c);
		
		labelMessage = new JLabel("");
		c.gridy++;
		panelForm.add(labelMessage, c);
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Airline().setVisible(true);
			}
		});
//		SimpleDateFormat sqlDate = new SimpleDateFormat("YYYY-MM-DD");
//		java.sql.Date travelDate;
//		java.util.Date travDate;
//		travDate = sqlDate.parse("2001-11-11");
//		travelDate = new java.sql.Date(travDate.getTime());
//		Traveler abe = Traveler(1, "Abraham", "Lincoln", 99, travelDate, "ST LOUIS", "CHICAGO", travelDate);
//		AirlineDAO airlineDAO;
//		airlineDAO.addToDB(abe);
	}
	
}
