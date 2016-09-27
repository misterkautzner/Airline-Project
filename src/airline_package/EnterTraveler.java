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

	private JTextField fieldDateOfBirth;
	private JTextField fieldEmail;
	private JTextField fieldUserName;
	private JTextField fieldPassword;

	public EnterTraveler(JPanel mainPanel) {
//		enterTView();
//		
//		setTitle("Passenger Information Form");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setSize(800, 450);
//		// Centers frame to middle of screen
//		setLocationRelativeTo(null);
//		setResizable(true);
//	}
//	
//	private void enterTView() {
		
		//JFrame frame = null;
		//JPanel contentFrame = (JPanel) frame.getContentPane();
		JPanel panelForm = new JPanel(new GridBagLayout());
//		JPanel mainPanel = (JPanel) getContentPane().add(panelForm);
		mainPanel.add(panelForm);
			
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
		JPanel p4 = new JPanel();
		panelForm.add(p4, c);
		p4.add(new JLabel("Date of Birth: "));
		fieldDateOfBirth = new JTextField(14);
		p4.add(fieldDateOfBirth);
		
		c.gridy++;
		JPanel p5 = new JPanel();
		panelForm.add(p5, c);
		p5.add(new JLabel("Email Address: "));
		fieldEmail = new JTextField(14);
		p5.add(fieldEmail);
		
		c.gridy++;
		JPanel p6 = new JPanel();
		panelForm.add(p6, c);
		p6.add(new JLabel("Username: "));
		fieldUserName = new JTextField(14);
		p6.add(fieldUserName);
		
		c.gridy++;
		JPanel p7 = new JPanel();
		panelForm.add(p7, c);
		p7.add(new JLabel("Password: "));
		fieldPassword = new JTextField(14);
		p7.add(fieldPassword);

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
				String email = fieldEmail.getText();
				String userName = fieldUserName.getText();
				String password = fieldPassword.getText();
				
				if (dateOfBirth == null)
					labelMessage.setText("Date of Birth: 'yyyy-mm-dd'");
				
				else if (firstName.isEmpty() || lastName.isEmpty() ||
						email.isEmpty() || userName.isEmpty() || password.isEmpty()) {
					labelMessage.setText("You must fill all fields.");
				}
				
				else {
					
					Traveler traveler = new Traveler(0, userName, password, firstName, 
							lastName, dateOfBirth, email);
						
					AirlineDAO airDAO = new AirlineDAO();
					airDAO.addToDB(traveler);
					
					mainPanel.removeAll();
					mainPanel.revalidate(); 
					mainPanel.repaint();
					
					System.out.println("Implement Flight Selection Screen!");
				}
			}
		});
		

		

		
	}
	
//	public static void main(String[] args) {
//		
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				new EnterTraveler().setVisible(true);
//			}
//		});
//		SimpleDateFormat sqlDate = new SimpleDateFormat("YYYY-MM-DD");
//		java.sql.Date travelDate;
//		java.util.Date travDate;
//		travDate = sqlDate.parse("2001-11-11");
//		travelDate = new java.sql.Date(travDate.getTime());
//		Traveler abe = Traveler(1, "Abraham", "Lincoln", 99, travelDate, "ST LOUIS", "CHICAGO", travelDate);
//		AirlineDAO airlineDAO;
//		airlineDAO.addToDB(abe);
//	}
	
}
