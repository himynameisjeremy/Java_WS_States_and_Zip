package webService;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;

public class CreateTagCloud {

	private static String[] WORDS = new String[500];
			 
	protected void initUI() {
			 JFrame frame = new JFrame(CreateTagCloud.class.getSimpleName());
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 JPanel panel = new JPanel();
			 Cloud cloud = new Cloud();
			 for(int i=0;i<WORDS.length;i++){
				 while(WORDS[i]!=null && GetStates.zipCount[i]>0){
					 cloud.addTag(WORDS[i]);
					 System.out.println("WORDS[i]: "+WORDS[i]);
					 GetStates.zipCount[i]=GetStates.zipCount[i]-1;
				 }
			 }
			 for (Tag tag : cloud.tags()) {
			 final JLabel label = new JLabel(tag.getName());
			 label.setOpaque(false);
			 label.setFont(label.getFont().deriveFont((float) tag.getWeight() * 10));
			 panel.add(label);
			 }
			 frame.add(panel);
			 frame.setSize(800, 600);
			 frame.setVisible(true);
			 }
	
	public static void createCloud() {
		
		GetStates.getStateInfo();
		
		for(int x = 0; x< GetStates.zipCount.length;x++){
				WORDS[x]=GetStates.stateFull[x];
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CreateTagCloud().initUI();
			}
		});
	}

}