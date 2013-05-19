package results;
import hash.HashFunction;
import hash.hashFunctions.LookUp3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private JTextArea jTextArea1;
	private newl.Actions action;

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
				jPanel1.setPreferredSize(new java.awt.Dimension(575, 368));
				{
					jTextArea1 = new JTextArea();
					jTextArea1.setName("jTextArea1");
				}
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
					jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						        .addComponent(hashFunction, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 0, Short.MAX_VALUE))
						    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 90, Short.MAX_VALUE)
						        .addComponent(b, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap());
					jPanel2Layout.setVerticalGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(b, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(17)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(hashFunction, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(136, 136));
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
			        jButton1.setEnabled(false);
				}
				jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					        .addGap(173))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(258))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jCheckBox1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					        .addGap(119))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(pathToFile, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					        .addGap(119))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jCheckBox2, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					        .addGap(119))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					        .addGap(119))
					    .addComponent(jPanel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(jTextArea1, 0, 279, Short.MAX_VALUE)
					.addContainerGap());
				jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
					.addGap(8)
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addComponent(pathToFile, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(18)
					        .addComponent(jCheckBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(jCheckBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(jPanel2, 0, 209, Short.MAX_VALUE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					        .addGap(11))
					    .addComponent(jTextArea1, GroupLayout.Alignment.LEADING, 0, 397, Short.MAX_VALUE))
					.addContainerGap());
			}
			pack();
			this.setSize(664, 447);
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void pathToFileMouseClicked(MouseEvent evt) {
		
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	    chooser.addChoosableFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	            pathToFile.setText(chooser.getSelectedFile().getName());
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
			jTextArea1.setText("Similarities");
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
			jTextArea1.setText("HashFunctions");
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
		// TODO Auto-generated method stub
		
	}

	private void performHyperLogLog() {
		String path = this.pathToFile.getText();
		int b = (int) this.b.getValue();
		String hashFunc = "hash.hashFunctions." + (String) this.hashFunction.getValue();
		
		HyperLogLog.exec(path, hashFunc, b);
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
