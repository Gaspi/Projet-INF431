package newl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import org.jdesktop.application.SingleFrameApplication;

import results.NewJFrame;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * 
 */
public class NewSingleFrameApplication extends SingleFrameApplication {
    private JPanel topPanel;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton3;
    private JRadioButton jRadioButton4;
    private JButton jButton1;
    private JRadioButton jRadioButton5;
    private JRadioButton jRadioButton2;
    private ButtonGroup buttonGroup1;
    private JLabel jLabel1;

    @Override
    protected void startup() {
        topPanel = new JPanel();
        GroupLayout topPanelLayout = new GroupLayout((JComponent)topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanel.setPreferredSize(new java.awt.Dimension(579, 408));
        {
        	jLabel1 = new JLabel();
        	jLabel1.setName("jLabel1");
        }
        {
        	jRadioButton1 = new JRadioButton();
        	jRadioButton1.setName("jRadioButton1");
        	getButtonGroup1().add(jRadioButton1);
        }
        {
        	jRadioButton2 = new JRadioButton();
        	jRadioButton2.setName("jRadioButton2");
        	getButtonGroup1().add(jRadioButton2);
        }
        {
        	jRadioButton3 = new JRadioButton();
        	jRadioButton3.setName("jRadioButton3");
        	getButtonGroup1().add(jRadioButton3);
        }
        {
        	jRadioButton4 = new JRadioButton();
        	jRadioButton4.setName("jRadioButton4");
        	getButtonGroup1().add(jRadioButton4);
        }
        {
        	jRadioButton5 = new JRadioButton();
        	jRadioButton5.setName("jRadioButton5");
        	getButtonGroup1().add(jRadioButton5);
        }
        {
        	jButton1 = new JButton();
        	jButton1.setName("jButton1");
        	jButton1.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent evt) {
        			jButton1MouseClicked(evt);
        		}
        	});
        }
        topPanelLayout.setVerticalGroup(topPanelLayout.createSequentialGroup()
        	.addContainerGap()
        	.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
        	.addGap(25)
        	.addComponent(jRadioButton5, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addGap(21)
        	.addComponent(jRadioButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addGap(34)
        	.addComponent(jRadioButton3, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addGap(32)
        	.addComponent(jRadioButton4, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addGap(25)
        	.addComponent(jRadioButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addGap(0, 62, Short.MAX_VALUE)
        	.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	.addContainerGap(28, 28));
        topPanelLayout.setHorizontalGroup(topPanelLayout.createSequentialGroup()
        	.addContainerGap()
        	.addGroup(topPanelLayout.createParallelGroup()
        	    .addGroup(GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
        	        .addComponent(jRadioButton5, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
        	        .addGap(0, 23, Short.MAX_VALUE))
        	    .addGroup(topPanelLayout.createSequentialGroup()
        	        .addComponent(jRadioButton1, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
        	        .addGap(0, 0, Short.MAX_VALUE))
        	    .addGroup(GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
        	        .addComponent(jRadioButton3, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
        	        .addGap(0, 364, Short.MAX_VALUE))
        	    .addGroup(GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
        	        .addComponent(jRadioButton4, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
        	        .addGap(0, 364, Short.MAX_VALUE))
        	    .addGroup(GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
        	        .addComponent(jRadioButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        	        .addGap(0, 442, Short.MAX_VALUE))
        	    .addGroup(GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
        	        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
        	        .addGap(0, 456, Short.MAX_VALUE))
        	    .addGroup(GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
        	        .addGap(22)
        	        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
        	        .addGap(0, 92, Short.MAX_VALUE)))
        	.addContainerGap(31, 31));
        show(topPanel);
    }

    public static void main(String[] args) {
        launch(NewSingleFrameApplication.class, args);
    }
    
    private ButtonGroup getButtonGroup1() {
    	if(buttonGroup1 == null) {
    		buttonGroup1 = new ButtonGroup();
    	}
    	return buttonGroup1;
    }
    
    private void jButton1MouseClicked(MouseEvent evt) {
    	
    	Actions action = null;
    	switch(getSelectedButtonText(this.buttonGroup1)){
    	case "Comparaison des fonctions de hachage (question 1)":
    		action = Actions.HASHFUNCTIONS;
    		break;
    	case "Calcul de la taille du fichier par l'algorithme HyperLogLog (question 2)":
    		action = Actions.HYPERLOGLOG;
    		break;
    	case "Similarities":
    		action = Actions.SIMILARITIES;
    		break;
    	case "Icebergs":
    		action = Actions.ICEBERGS;
    		break;
    	case "Mice":
    		action = Actions.MICE;
    		break;	
    	}

		NewJFrame inst = new NewJFrame(action);
		inst.addWindowListener(new WindowAdapter() {
    		public void windowClosed(WindowEvent evt)  {
    			taskclosed(evt);
    		}
		});
		inst.setLocationRelativeTo(null);
		this.getMainFrame().setEnabled(false);
		inst.setVisible(true);
    }
    

	private void taskclosed(WindowEvent evt) {
    	this.getMainFrame().setEnabled(true);
	}
	
   public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

}
