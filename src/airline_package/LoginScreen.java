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

public class LoginScreen extends JFrame {
	
	private JTextField fieldUsername;
	private JTextField fieldPassword;
	private static AirlineDAO airlineDao = new AirlineDAO();

	public LoginScreen() {
		createView();
		
		setTitle("Login Screen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 450);

		setLocationRelativeTo(null);
		setResizable(true);
	}
	
	private void createView() {
		
		JPanel panelForm = new JPanel(new GridBagLayout());
		JPanel mainPanel = (JPanel) getContentPane().add(panelForm);
			
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END; 
		
		JPanel labelPanel = new JPanel();
		panelForm.add(labelPanel, c);
		JLabel labelMessage = new JLabel();
		labelPanel.add(labelMessage);
		
		c.gridy++;
		JPanel pBlank1 = new JPanel();
		panelForm.add(pBlank1, c);
		
		c.gridy++;
		JPanel pBlank2 = new JPanel();
		panelForm.add(pBlank2, c);
		
		c.gridy++;
		JPanel p1 = new JPanel();
		panelForm.add(p1, c);
		p1.add(new JLabel("Username: "));
		fieldUsername = new JTextField(14);
		p1.add(fieldUsername);
		
		c.gridy++;
		JPanel p2 = new JPanel();
		panelForm.add(p2, c);
		p2.add(new JLabel("Password: "));
		fieldPassword = new JTextField(14);
		p2.add(fieldPassword);
		
		c.gridy++;
		JPanel panelButton = new JPanel();
		panelForm.add(panelButton, c);
		JButton buttonLogin = new JButton("Login");
		panelButton.add(buttonLogin);
		panelButton.add(new JLabel("            "));
		
		c.gridy++;
		JPanel pBlank4 = new JPanel();
		panelForm.add(pBlank2, c);
		
		JPanel pLabel = new JPanel();
		panelForm.add(pLabel, c);
		pLabel.add(new JLabel("---   ---   or   ---   ---        "));
		
		c.gridy++;
		JPanel pBlank3 = new JPanel();
		panelForm.add(pBlank1, c);
		
		c.gridy++;
		JPanel panelNewPassengerButton = new JPanel();
		panelForm.add(panelNewPassengerButton, c);
		JButton buttonNewPassenger = new JButton("New Passenger");
		panelNewPassengerButton.add(buttonNewPassenger);
		panelNewPassengerButton.add(new JLabel("  "));
		

		

		
		
		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = fieldUsername.getText();
				String password = fieldPassword.getText();
				
				if (userName.isEmpty() || password.isEmpty()) {
					labelMessage.setText("You must fill both fields.  ");
				}
				
				else {
				
					int id = airlineDao.login(userName, password);
					
					if (id >= 0){
						mainPanel.removeAll();
						mainPanel.revalidate(); 
						mainPanel.repaint();
						System.out.println("Success");
						System.out.println("Implement Customer Page");
						//if so, show them page with their flights, options to add more or cancel
						int admin_id = 1;
						if (id == admin_id)
							System.out.println("Implement Admin Page!");
						// Implement the admin page
					}
					else
						labelMessage.setText("Username doesn't match password.");
	
				}
			}
		});
			
			buttonNewPassenger.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					mainPanel.removeAll();
					mainPanel.revalidate(); 
					mainPanel.repaint();
					
					EnterTraveler et = new EnterTraveler(mainPanel);
				}
			});
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginScreen().setVisible(true);
			}
		});
	
//		int id = airlineDao.login("John", "Kautzner");
//		System.out.println(id);
		
	}
}