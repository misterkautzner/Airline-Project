package airline_package;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Seating extends JFrame{


	public Seating() {
		createView();
		
		setTitle("Flight Seating Diagram");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(100, 375);
		// Centers frame to middle of screen
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void createView() {
		JPanel panelMain = new JPanel();
		getContentPane().add(panelMain);
		
		JPanel panelForm = new JPanel(new GridBagLayout());
		panelMain.add(panelForm);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		
		List<Button> buttons = new ArrayList<Button>();
		List<Traveler> passengerList = Flight.getPassengerList();
		System.out.println(passengerList);
		int count = passengerList.size();
		System.out.println(count);
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 13; j++) {
				count--;
				if (i == 1 && j == 12)
					break;
				
				char seatChar = (char) (i+65);
				String seat = Character.toString(seatChar) + (j+1);
				Button thisButton = new Button(seat);
				if (count < 1)
					thisButton.setBackground(Color.BLUE);
				else
					thisButton.setBackground(Color.RED);
				panelForm.add(thisButton, c);
				c.gridy++;
			}
			c.gridx++;
			c.gridx++;
			c.gridy = 0;
		}
	}

	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Seating().setVisible(true);
			}
		});
	
	}
}
