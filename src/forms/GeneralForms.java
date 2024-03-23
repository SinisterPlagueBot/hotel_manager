package forms;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GeneralForms extends JPanel{

	/**
	 * 
	 */
	
	LabeledTextField [] labelsTexts ;// to get the an array of labels in order to set there text or get there text 
	public GeneralForms() {
		
	}
	public GeneralForms(attributsGetter g) {
		JPanel p=new JPanel();
		 labelsTexts=new LabeledTextField[g.getNum()];
		for(int i=0;i<g.getNum(); i++) {
			LabeledTextField l = new LabeledTextField(g.getLabel(i),g.getCol(i),120);
			
			labelsTexts[i]=l;
			p.add(l);
		}
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		setLayout(new BorderLayout());
		add(p);
	}
}
