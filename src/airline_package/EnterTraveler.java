package airline_package;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class EnterTraveler extends JFrame{
	
	private JTextField fieldFirstName;
	private JTextField fieldLastName;
	private JTextField fieldAge;
	private JTextField fieldDateOfBirth;
	private JTextField fieldPlaceOfDeparture;
	private JTextField fieldDestination;
	private JTextField fieldTravelDate;

	public EnterTraveler() {
		createView();
		
		setTitle("Passenger Information Form");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 450);
		// Centers frame to middle of screen
		setLocationRelativeTo(null);
		setResizable(true);
	}
	
	private void createView() {
		
		JPanel panelForm = new JPanel(new GridBagLayout());
		getContentPane().add(panelForm);
			
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END; 
		
		JPanel p0 = new JPanel();
		panelForm.add(p0, c);
		p0.add(new JLabel("Please enter passenger info  "));
		
		c.gridy++;
		JPanel pBlank1 = new JPanel();
		panelForm.add(pBlank1, c);
		
		c.gridy++;
		JPanel pBlank2 = new JPanel();
		panelForm.add(pBlank2, c);
		
		c.gridy++;
		JPanel p1 = new JPanel();
		panelForm.add(p1, c);
		p1.add(new JLabel("First Name: "));
		fieldFirstName = new JTextField(14);
		p1.add(fieldFirstName);
		
		c.gridy++;
		JPanel p2 = new JPanel();
		panelForm.add(p2, c);
		p2.add(new JLabel("Last Name: "));
		fieldLastName = new JTextField(14);
		p2.add(fieldLastName);
		
		c.gridy++;
		JPanel p3 = new JPanel();
		panelForm.add(p3, c);
		p3.add(new JLabel("Age: "));
		fieldAge = new JTextField(14);
		p3.add(fieldAge);
		
		c.gridy++;
		JPanel p4 = new JPanel();
		panelForm.add(p4, c);
		p4.add(new JLabel("Date of Birth: "));
		fieldDateOfBirth = new JTextField(14);
		p4.add(fieldDateOfBirth);
		
		c.gridy++;
		JPanel p5 = new JPanel();
		panelForm.add(p5, c);
		p5.add(new JLabel("Place of Departure: "));
		fieldPlaceOfDeparture = new JTextField(14);
		p5.add(fieldPlaceOfDeparture);
		
		c.gridy++;
		JPanel p6 = new JPanel();
		panelForm.add(p6, c);
		p6.add(new JLabel("Destination: "));
		fieldDestination = new JTextField(14);
		p6.add(fieldDestination);
		
		c.gridy++;
		JPanel p7 = new JPanel();
		panelForm.add(p7, c);
		p7.add(new JLabel("Travel Date: "));
		fieldTravelDate = new JTextField(14);
		p7.add(fieldTravelDate);

		c.gridy++;
		JPanel pBlank3 = new JPanel();
		panelForm.add(pBlank3, c);
		
		c.gridy++;
		JPanel buttonPanel = new JPanel();
		panelForm.add(buttonPanel, c);
		JButton buttonSubmit = new JButton();
		buttonSubmit = new JButton("Submit");
		buttonPanel.add(buttonSubmit);
		buttonPanel.add(new JLabel("           "));
		
		c.gridy++;
		JPanel labelPanel = new JPanel();
		panelForm.add(labelPanel, c);
		JLabel labelMessage = new JLabel();
		labelPanel.add(labelMessage);
		
		
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
				if (age == -1) 
					labelMessage.setText("Age must be an integer.");
				
				else if (dateOfBirth == null)
					labelMessage.setText("Date of Birth: 'yyyy-mm-dd'");
				
				else if (travelDate == null)
					labelMessage.setText("Travel Date: 'yyyy-mm-dd'");
				
				else if (firstName.isEmpty() || lastName.isEmpty() ||
						placeOfDeparture.isEmpty() || destination.isEmpty()) {
					labelMessage.setText("You must fill all fields.");
				}
				
				else {
					
					Traveler traveler = new Traveler(0, firstName, lastName, age, dateOfBirth,
							placeOfDeparture, destination, travelDate);
						
					AirlineDAO airDAO = new AirlineDAO();
					airDAO.addToDB(traveler);
					
					dispose();
				}
			}
		});
		

		

		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EnterTraveler().setVisible(true);
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
