package forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Buttons extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Buttons(String[] s) {
		setBackground(Color.WHITE);
		for(int i=0; i<s.length; i++) {
			add(createStyledButton(s[i]));
		}
		setLayout(new FlowLayout(FlowLayout.RIGHT));
	}
	static public JButton createStyledButton(String text) {
		
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(59, 89, 182)); // Customize background color
        button.setFocusPainted(false); // Remove focus border
        button.setBorderPainted(false); // Remove button border
        button.setPreferredSize(new Dimension(120, 40)); // Set preferred size

        // Add hover effect
       
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.white);
                button.setForeground(Color.black);// Change color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(59, 89, 182)); // Restore original color
                button.setForeground(Color.white);
            }
        });
        return button;
    }
}
