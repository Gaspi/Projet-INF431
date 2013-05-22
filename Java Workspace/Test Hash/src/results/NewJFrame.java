package results;
import hash.HashFunction;
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
import javax.swing.BorderFactory;

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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import org.jdesktop.application.Application;

import sampling.Sampling;

import drafts.HyperLogLog;
import drafts.Similarities;

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
	private JTextField jTextField1;
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JCheckBox jCheckBox3;
	private JSpinner frequency;
	private JLabel jLabel10;
	private JSpinner nbOcc;
	private JLabel jLabel9;
	private JSpinner nbWords;
	private JLabel jLabel8;
	private JSpinner threshold;
	private JLabel jLabel7;
	private JLabel jLabel5;
	private JSpinner k;
	private JLabel jLabel6;
	private JScrollPane jScrollPane2;
	private JTextArea pathToFile;
	private JRadioButton jRadioButtonCollisions;
	private JRadioButton jRadioButtonLoiUniforme;
	private JRadioButton jRadioButtonVitesse;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JSpinner hashFunction;
	private JLabel jLabel2;
	private JSpinner b;
	private JPanel jPanel2;
	private JCheckBox jCheckBox1;
	private JLabel jLabel1;
	private JFileChooser jFileChooser1;
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
			//GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
				jPanel1.setLayout(jPanel1Layout);
				{
					jScrollPane1 = new JScrollPane();
					{
						jTextArea1 = new JTextArea();
						jScrollPane1.setViewportView(jTextArea1);
					}
				}
				{
					jCheckBox2 = new JCheckBox();
					jCheckBox2.setName("jCheckBox2");
				}
				{
					jPanel2 = new JPanel();
					GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
					jPanel2.setLayout(jPanel2Layout);
					jPanel2.setBorder(BorderFactory.createTitledBorder(""));
					jPanel2.setName("jPanel2");
					{
						SpinnerNumberModel bModel = new SpinnerNumberModel(11,4,15,1);
						
						b = new JSpinner();
						b.setModel(bModel);
						b.setName("b");
						b.setEnabled(false);
					}
					{
						jRadioButtonCollisions = new JRadioButton();
						jRadioButtonCollisions.setName("jRadioButtonCollisions");
						jRadioButtonCollisions.setEnabled(false);
					}
					{
						jLabel2 = new JLabel();
						jLabel2.setName("jLabel2");
					}
					{
						SpinnerNumberModel nbOccModel = new SpinnerNumberModel(1, 1, 10, 1);
						nbOcc = new JSpinner();
						nbOcc.setModel(nbOccModel);
						nbOcc.setEnabled(false);
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
						jLabel10 = new JLabel();
						jLabel10.setName("jLabel10");
						jLabel10.setEnabled(false);
					}
					{
						SpinnerNumberModel frequencyModel = new SpinnerNumberModel(0.01, 0, 0.1, 0.001);								
						frequency = new JSpinner();
						frequency.setModel(frequencyModel);
						frequency.setEnabled(false);
					}
					{
						jLabel7 = new JLabel();
						jLabel7.setName("jLabel7");
						jLabel7.setEnabled(false);
					}
					{
						SpinnerNumberModel thresholdModel = new SpinnerNumberModel(0.5, 0, 1, 0.02); 
						threshold = new JSpinner();
						threshold.setModel(thresholdModel);
						threshold.setEnabled(false);
					}
					{
						jLabel5 = new JLabel();
						jLabel5.setName("jLabel5");
						jLabel5.setEnabled(false);
					}
					{
						jLabel9 = new JLabel();
						jLabel9.setName("jLabel9");
						jLabel9.setEnabled(false);
					}
					{
						jLabel8 = new JLabel();
						jLabel8.setName("jLabel8");
						jLabel8.setEnabled(false);
					}
					{
						SpinnerNumberModel nbWordsModel = new SpinnerNumberModel(5,5,20,1);
						nbWords = new JSpinner();
						nbWords.setModel(nbWordsModel);
						nbWords.setEnabled(false);
					}
					{
						jLabel6 = new JLabel();
						jLabel6.setName("jLabel6");
						jLabel6.setEnabled(false);
					}
					{
						SpinnerNumberModel kModel = new SpinnerNumberModel(1,1,10,1);
						k = new JSpinner();
						k.setModel(kModel);
						k.setEnabled(false);
					}
					{
						jLabel4 = new JLabel();
						jLabel4.setName("jLabel4");
						jLabel4.setEnabled(false);
					}
					{
						jRadioButtonVitesse = new JRadioButton();
						jRadioButtonVitesse.setName("jRadioButtonVitesse");
						jRadioButtonVitesse.setEnabled(false);
					}
					{
						jRadioButtonLoiUniforme = new JRadioButton();
						jRadioButtonLoiUniforme.setName("jRadioButtonLoiUniforme");
						jRadioButtonLoiUniforme.setEnabled(false);
					}
					jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup()
						    .addGroup(jPanel2Layout.createSequentialGroup()
						        .addGroup(jPanel2Layout.createParallelGroup()
						            .addComponent(jLabel8, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jRadioButtonVitesse, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						                .addGap(13))
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						                .addGap(70))
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						                .addGap(70)))
						        .addGap(44)
						        .addGroup(jPanel2Layout.createParallelGroup()
						            .addGroup(jPanel2Layout.createSequentialGroup()
						                .addGap(0, 0, Short.MAX_VALUE)
						                .addComponent(hashFunction, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jRadioButtonCollisions, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						                .addGap(0, 34, Short.MAX_VALUE))))
						    .addGroup(jPanel2Layout.createSequentialGroup()
						        .addGroup(jPanel2Layout.createParallelGroup()
						            .addGroup(jPanel2Layout.createSequentialGroup()
						                .addGroup(jPanel2Layout.createParallelGroup()
						                    .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						                    .addComponent(jLabel6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						                .addGap(8)
						                .addGroup(jPanel2Layout.createParallelGroup()
						                    .addComponent(b, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						                    .addComponent(k, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						                .addGap(42)
						                .addGroup(jPanel2Layout.createParallelGroup()
						                    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                        .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						                        .addGap(21))
						                    .addComponent(jLabel10, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jRadioButtonLoiUniforme, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
						                .addGap(7))
						            .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
						                .addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
						                .addGap(21))
						            .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))
						        .addGap(27)
						        .addGroup(jPanel2Layout.createParallelGroup()
						            .addComponent(threshold, GroupLayout.Alignment.LEADING, 0, 62, Short.MAX_VALUE)
						            .addComponent(nbWords, GroupLayout.Alignment.LEADING, 0, 62, Short.MAX_VALUE)
						            .addComponent(nbOcc, GroupLayout.Alignment.LEADING, 0, 62, Short.MAX_VALUE)
						            .addComponent(frequency, GroupLayout.Alignment.LEADING, 0, 62, Short.MAX_VALUE))))
						.addContainerGap());
					jPanel2Layout.setVerticalGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(hashFunction, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(threshold, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel7, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(b, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(frequency, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel10, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						    .addComponent(k, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGap(21)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(nbWords, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel8, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(nbOcc, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabel9, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(0, 57, Short.MAX_VALUE)
						.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(jRadioButtonCollisions, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jRadioButtonVitesse, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jRadioButtonLoiUniforme, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(8));
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
					jTextField1 = new JTextField();
					jTextField1.setName("jTextField1");
					jTextField1.setEditable(false);
				}
				{
					jCheckBox3 = new JCheckBox();
					jCheckBox3.setName("jCheckBox3");
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
				{
					jScrollPane2 = new JScrollPane();
					{
						pathToFile = new JTextArea();
						jScrollPane2.setViewportView(pathToFile);
						pathToFile.setName("pathToFile");
						pathToFile.addMouseListener(new MouseAdapter() {
			        		public void mouseClicked(MouseEvent evt) {
			        			pathToFileMouseClicked(evt);
			        		}
			        	});
					}
				}
				jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
					        .addGap(8))
					    .addGroup(jPanel1Layout.createSequentialGroup()
					        .addGroup(jPanel1Layout.createParallelGroup()
					            .addComponent(jCheckBox1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					                .addGap(23))
					            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					                .addGap(72))
					            .addComponent(jTextField1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
					        .addGap(21)
					        .addGroup(jPanel1Layout.createParallelGroup()
					            .addComponent(jCheckBox2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					            .addComponent(jCheckBox3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
					        .addGap(8))
					    .addComponent(jPanel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addComponent(jScrollPane1, 0, 510, Short.MAX_VALUE)
					.addContainerGap());
				jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					        .addGap(26)
					        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					            .addComponent(jCheckBox2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					            .addComponent(jCheckBox1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					            .addComponent(jTextField1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					            .addComponent(jCheckBox3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					        .addGap(25)
					        .addComponent(jPanel2, 0, 325, Short.MAX_VALUE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, 0, 541, Short.MAX_VALUE))
					.addContainerGap());
			}
			pack();
			this.setSize(993, 616);
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void setUpForAction(){
		switch(action){
		case HASHFUNCTIONS:
			jLabel4.setEnabled(true);
			jRadioButtonVitesse.setEnabled(true);
			jRadioButtonCollisions.setEnabled(true);
			jRadioButtonLoiUniforme.setEnabled(true);
			jButton1.setEnabled(true);
			jCheckBox3.setEnabled(false);
			break;
		case HYPERLOGLOG:
			b.setEnabled(true);
			jLabel5.setEnabled(true);
			break;
		case SIMILARITIES:
			b.setEnabled(true);
			jLabel5.setEnabled(true);
			k.setEnabled(true);
			jLabel6.setEnabled(true);
			jLabel7.setEnabled(true);
			threshold.setEnabled(true);
			break;
		case FREQUENTWORDS:
			k.setModel(new SpinnerNumberModel(50, 20, 1000, 10));
			k.setEnabled(true);
			jLabel6.setEnabled(true);
			nbWords.setEnabled(true);
			jLabel8.setEnabled(true);
			break;
		case MICE:
			k.setModel(new SpinnerNumberModel(50, 20, 2000, 10));
			k.setEnabled(true);
			jLabel6.setEnabled(true);
			nbOcc.setEnabled(true);
			jLabel9.setEnabled(true);
			break;
		case ICEBERGS:
			k.setModel(new SpinnerNumberModel(50, 20, 2000, 10));
			k.setEnabled(true);
			jLabel6.setEnabled(true);
			frequency.setEnabled(true);
			jLabel10.setEnabled(true);
			hashFunction.setEnabled(false);
			jLabel3.setEnabled(false);
			break;
		}
	}
	
	private void pathToFileMouseClicked(MouseEvent evt) {
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	    chooser.addChoosableFileFilter(filter);
	    chooser.setMultiSelectionEnabled(true);	    
	    pathToFile.setText("");   	    
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION)
	    	for( File f: chooser.getSelectedFiles() )
	    		pathToFile.append(f.getName() + System.lineSeparator());
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
		case HASHFUNCTIONS:
			performHashFunctionTests();
			break;
		case HYPERLOGLOG:
			performHyperLogLog();
			break;
		case SIMILARITIES:
			performSimilarities();
			break;
		case FREQUENTWORDS:
			performFrequentWords();
			break;
		case MICE:
			performMice();
			break;
		case ICEBERGS:
			performIcebergs();
			break;
		default:
			break;
		}
	}

	private void performMice() {
		int k = (int) this.k.getValue();
		String func = "hash.hashFunctions." + (String) this.hashFunction.getValue();
		int nbOcc = (int) this.nbOcc.getValue();
		
		for(File f: selectedFiles)
			Sampling.numberOfMiceForFile(f.getAbsolutePath(), nbOcc, HashFunction.getHashFunction(func), k);
		
	}

	private void performIcebergs() {
		double frequency = (double) this.frequency.getValue();

		for(File f: selectedFiles)
			Sampling.icebergsForFile(f.getAbsolutePath(), frequency);	
	}
	
	private void performFrequentWords(){
		int k = (int) this.k.getValue();
		String func = "hash.hashFunctions." + (String) this.hashFunction.getValue();
		int nbWords = (int) this.nbWords.getValue();
		
		for(File f: selectedFiles)
			Sampling.significantWordsForFile(f.getAbsolutePath(), nbWords, HashFunction.getHashFunction(func), k);
	}

	private void performSimilarities() {
		int b = (int) this.b.getValue();
		String func = "hash.hashFunctions." + (String) this.hashFunction.getValue();
		String[] urls = new String[selectedFiles.length];
		for(int i=0; i<selectedFiles.length; i++)
			urls[i] = selectedFiles[i].getAbsolutePath();
		
		int k = (int) this.k.getValue();
		double threshold = (double) this.threshold.getValue();
		
		Similarities.exec(urls, k, b, func, threshold);
	}

	private void performHyperLogLog() {
		int b = (int) this.b.getValue();
		String hashFunc = "hash.hashFunctions." + (String) this.hashFunction.getValue();
		
		for(File f: selectedFiles)
			HyperLogLog.exec(f.getAbsolutePath(), hashFunc, b);
	}
	
	private void performHashFunctionTests(){
		String hashFunc = "hash.hashFunctions." + (String) this.hashFunction.getValue();
		
		if(this.jRadioButtonVitesse.isSelected())
			hash.HashFunctionTests.speedTests(HashFunction.getHashFunction(hashFunc));
		if(this.jRadioButtonCollisions.isSelected())
			hash.HashFunctionTests.collisionTests(HashFunction.getHashFunction(hashFunc));
		if(this.jRadioButtonLoiUniforme.isSelected())
			hash.HashFunctionTests.uniformDistribTest(HashFunction.getHashFunction(hashFunc), true);
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
