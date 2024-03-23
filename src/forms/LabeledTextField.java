package forms;

import java.awt.BorderLayout;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Graphics;
public class LabeledTextField extends JPanel{
	
	private static final Color BACKGROUND_START = new Color(0, 51,0);
    private static final Color BACKGROUND_END = new Color(100, 150, 255);


	public LabeledTextField(String s, int col, int labelwidth) {
		
		JLabel l=new JLabel(s);
		JTextField tf=new JTextField(col);
		setLayout(new BorderLayout());
        setOpaque(false);

        // Visual enhancements for label
        l.setForeground(Color.white);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        // Visual enhancements for text field
        tf.setBorder(new LineBorder(Color.white, 1));
      tf.setOpaque(false);
        tf.setForeground(Color.white);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		add(l); add(tf);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		((JLabel)this.getComponent(0)).setPreferredSize(new Dimension(labelwidth,60));

	}
	protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Gradient background
        Paint gradient = new GradientPaint(0, 0, BACKGROUND_START, getWidth(), 0, BACKGROUND_END);
        g2d.setPaint(gradient);

        // Rounded rectangle with shadow
        int arc = 15;
        int shadowSize = 5;

        g2d.fill(new RoundRectangle2D.Double(shadowSize, shadowSize, getWidth() - 2 * shadowSize,
                getHeight() - 2 * shadowSize, arc, arc));

        super.paintComponent(g2d);
        g2d.dispose();
    }
	
	public String getText() {
		return ((JTextField)this.getComponent(1)).getText();
	}
	
	public void setText(String s) {
		((JTextField)this.getComponent(1)).setText(s);
	}
	
	
}
