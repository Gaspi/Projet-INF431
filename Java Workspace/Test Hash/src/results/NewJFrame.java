package results;
import hash.HashFunction;
import hash.ProvidingHashFunction;
import hash.hashFunctions.*;
import hash.HashFunctionTests;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import javax.swing.WindowConstants;
import org.jdesktop.application.Application;

import drafts.HyperLogLog;

import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;


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
public class NewJFrame extends javax.swing.JFrame {
	private JPanel jPanel1;
	private JButton jButton1;
	private JCheckBox jCheckBox2;
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JRadioButton jRadioButtonCollisions;
	private JRadioButton jRadioButtonLoiUniforme;
	private JRadioButton jRadioButtonVitesse;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JSpinner hashFunction;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JSpinner b;
	private JPanel jPanel2;
	private JCheckBox jCheckBox1;
	private JLabel jLabel1;
	private JFileChooser jFileChooser1;
	private JTextField pathToFile;
	private newl.Actions action;
	private File[] selectedFiles;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewJFrame inst = new NewJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public NewJFrame() {
		super();
		initGUI();
	}
	
	public NewJFrame(newl.Actions action) {
		super();
		initGUI();
	
		this.action = action;
		setUpForAction();
		redirectSystemStreams();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(723, 417));
				{
					jTextField1 = new JTextField();
					jTextField1.setEditable(false);
				}
				{
					jCheckBox2 = new JCheckBox();
					jCheckBox2.setName("jCheckBox2");
				}
				{
					jPanel2 = new JPanel();
					GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
					jPanel2.setLayout(jPanel2Layout);
					{
						SpinnerNumberModel bModel = new SpinnerNumberModel(11,4,15,1);
						
						b = new JSpinner();
						b.setModel(bModel);
						b.setName("b");
					}
					{
						jRadioButtonCollisions = new JRadioButton();
						jRadioButtonCollisions.setName("jRadioButtonCollisions");
					}
					{
						jLabel2 = new JLabel();
						jLabel2.setName("jLabel2");
					}
					{
						jLabel3 = new JLabel();
						jLabel3.setName("jLabel3");
					}
					{
						SpinnerListModel hashFunctionModel = 
								new SpinnerListModel(
										new String[] { "LookUp3", "MurmurHash3" , "DJB2" , "LoseLose" , "JavaHash" , "HomemadeHash"});
						hashFunction = new JSpinner();
						hashFunction.setModel(hashFunctionModel);
					}
					{
						jLabel4 = new JLabel();
						jLabel4.setName("jLabel4");
					}
					{
						jRadioButtonVitesse = new JRadioButton();
						jRadioButtonVitesse.setName("jRadioButtonVitesse");
					}
					{
						jRadioButtonLoiUniforme = new JRadioButton();
						jRadioButtonLoiUniforme.setName("jRadioButtonLoiUniforme");
					}
					jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						        .addGroup(jPanel2Layout.createParallelGroup()
						            .addComponent(jLabel2, GroupLayout.Alignment.LEADING, 0, 311, Short.MAX_VALUE)
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jRadioButtonLoiUniforme, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
						                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)))
						        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						        .addComponent(b, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						        .addGroup(jPanel2Layout.createParallelGroup()
						            .addComponent(jRadioButtonVitesse, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jRadioButtonCollisions, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						                .addGap(100))
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						                .addGap(57))
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						                .addGap(57)))
						        .addGap(0, 28, Short.MAX_VALUE)
						        .addComponent(hashFunction, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap());
					jPanel2Layout.setVerticalGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(b, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGap(17)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(hashFunction, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jRadioButtonVitesse, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jRadioButtonCollisions, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jRadioButtonLoiUniforme, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(95, Short.MAX_VALUE));
				}
				{
					jScrollPane1 = new JScrollPane();
					jScrollPane1.setName("jScrollPane1");
					{
						jTextArea1 = new JTextArea();
						jScrollPane1.setViewportView(jTextArea1);
						jTextArea1.setName("jTextArea1");
					}
				}
				{
					jCheckBox1 = new JCheckBox();
					jCheckBox1.setName("jCheckBox1");
				}
				{
					jLabel1 = new JLabel();
					jLabel1.setName("jLabel1");
				}
				{
					pathToFile = new JTextField();
					pathToFile.setName("pathToFile");
					pathToFile.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							pathToFileMouseClicked(evt);
						}
					});
				}
				{
					jButton1 = new JButton();
					jButton1.setName("jButton1");
			        jButton1.addActionListener(new ActionListener() {
			        	 
			            public void actionPerformed(ActionEvent e)
			            {
			            	jButton1MouseClicked(e);
			            }
			        }); 
			        
				}
				jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 186, Short.MAX_VALUE))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jCheckBox2, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 186, Short.MAX_VALUE))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(pathToFile, 0, 392, Short.MAX_VALUE)
					        .addGap(0, 6, GroupLayout.PREFERRED_SIZE))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jCheckBox1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 186, Short.MAX_VALUE))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 325, Short.MAX_VALUE))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 240, Short.MAX_VALUE))
					    .addComponent(jPanel2, GroupLayout.Alignment.LEADING, 0, 398, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.addContainerGap());
				jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
					.addGap(8)
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addComponent(pathToFile, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addComponent(jCheckBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(19)
					        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(jCheckBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(jPanel2, 0, 258, Short.MAX_VALUE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(10))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addGap(11)
					        .addComponent(jScrollPane1, 0, 436, Short.MAX_VALUE)))
					.addContainerGap());
			}
			pack();
			this.setSize(803, 497);
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void setUpForAction(){
		switch(action){
		case HASHFUNCTIONS:
			b.setEnabled(false);
			jLabel2.setEnabled(false);
			jLabel1.setEnabled(false);
			pathToFile.setEnabled(false);
			jCheckBox2.setEnabled(false);
			break;
		case HYPERLOGLOG:
			jButton1.setEnabled(false);
			jLabel4.setEnabled(false);
			this.jRadioButtonCollisions.setEnabled(false);
			this.jRadioButtonLoiUniforme.setEnabled(false);
			this.jRadioButtonVitesse.setEnabled(false);
			break;
		case ICEBERGS:
			jLabel4.setEnabled(false);
			this.jRadioButtonCollisions.setEnabled(false);
			this.jRadioButtonLoiUniforme.setEnabled(false);
			this.jRadioButtonVitesse.setEnabled(false);
			break;
		case MICE:
			jLabel4.setEnabled(false);
			this.jRadioButtonCollisions.setEnabled(false);
			this.jRadioButtonLoiUniforme.setEnabled(false);
			this.jRadioButtonVitesse.setEnabled(false);
			break;
		case SIMILARITIES:
			jLabel4.setEnabled(false);
			this.jRadioButtonCollisions.setEnabled(false);
			this.jRadioButtonLoiUniforme.setEnabled(false);
			this.jRadioButtonVitesse.setEnabled(false);
			break;
		}
	}
	
	private void pathToFileMouseClicked(MouseEvent evt) {
		
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	    chooser.addChoosableFileFilter(filter);
	    chooser.setMultiSelectionEnabled(true);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    		selectedFiles = chooser.getSelectedFiles();
	            pathToFile.setText(selectedFiles[1].getName());
	    }
	    jButton1.setEnabled(true);
	}
	
	private void jButton1MouseClicked(ActionEvent evt) {
		
		long start = System.nanoTime();
		performTask();
		long end = System.nanoTime();
		
		if(this.jCheckBox1.isSelected())
			this.jTextField1.setText(Double.toString((end-start)/1000000000.) + " s");
	}
	
	private void performTask(){
		switch(this.action){
		case HYPERLOGLOG:
			performHyperLogLog();
			break;
		case SIMILARITIES:
			performSimilarities();
			break;
		case ICEBERGS:
			performIcebergs();
			jTextArea1.setText("Icebergs");
			break;
		case MICE:
			performMice();
			jTextArea1.setText("Mice");
			break;
		case HASHFUNCTIONS:
			performHashFunctionTests();
			break;
		default:
			break;
		}
	}

	private void performMice() {
		
		
	}

	private void performIcebergs() {
		// TODO Auto-generated method stub
		
	}

	private void performSimilarities() {
		
		
	}

	private void performHyperLogLog() {
		String path = this.pathToFile.getText();
		int b = (int) this.b.getValue();
		String hashFunc = "hash.hashFunctions." + (String) this.hashFunction.getValue();
		
		HyperLogLog.exec(path, hashFunc, b);
	}
	
	private void performHashFunctionTests(){
		String hashFunc = "hash.hashFunctions." + (String) this.hashFunction.getValue();
		
		if(this.jRadioButtonVitesse.isSelected())
			hash.HashFunctionTests.speedTests(ProvidingHashFunction.newHashFunction(hashFunc));
		if(this.jRadioButtonCollisions.isSelected())
			hash.HashFunctionTests.collisionTests(ProvidingHashFunction.newHashFunction(hashFunc));
		if(this.jRadioButtonLoiUniforme.isSelected())
			hash.HashFunctionTests.uniformDistribTest(ProvidingHashFunction.newHashFunction(hashFunc), true);
	}
	
	private void updateTextArea(final String text) {
		  SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		      jTextArea1.append(text);
		    }
		  });
	}
		 
	private void redirectSystemStreams() {
		  OutputStream out = new OutputStream() {
		    @Override
		    public void write(int b) throws IOException {
		      updateTextArea(String.valueOf((char) b));
		    }
		 
		    @Override
		    public void write(byte[] b, int off, int len) throws IOException {
		      updateTextArea(new String(b, off, len));
		    }
		 
		    @Override
		    public void write(byte[] b) throws IOException {
		      write(b, 0, b.length);
		    }
		  };
		 
		  System.setOut(new PrintStream(out, true));
		  System.setErr(new PrintStream(out, true));
		}

}
