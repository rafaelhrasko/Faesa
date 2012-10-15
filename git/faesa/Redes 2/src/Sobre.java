import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed May 12 17:48:28 GMT 2010
 */





public class Sobre  {

	private void buttonOkMousePressed(MouseEvent e) {
		this.Sobre.setVisible(false);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - 
		Sobre = new JFrame();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		buttonOk = new JButton();

		//======== Sobre ========
		{
			Sobre.setResizable(false);
			Sobre.setIconImage(((ImageIcon)UIManager.getIcon("OptionPane.informationIcon")).getImage());
			Sobre.setAlwaysOnTop(true);
			Sobre.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			Sobre.setTitle("Sobre");
			Container SobreContentPane = Sobre.getContentPane();
			Sobre.setVisible(true);
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}catch (Exception e){
			}
			
			//---- label1 ----
			label1.setText("Messenger Extreme");

			//---- label2 ----
			label2.setText("Vers\u00e3o: 1.0 Pro Edition");

			//---- label3 ----
			label3.setText("Desenvolvedores:");

			//---- label4 ----
			label4.setText("Andr\u00e9 Custodio");

			//---- label5 ----
			label5.setText("Rafael Hrasko");

			//---- label6 ----
			label6.setText("Rodrigo Pessotti");

			//---- buttonOk ----
			buttonOk.setText("OK");
			buttonOk.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					buttonOkMousePressed(e);
				}
			});

			GroupLayout SobreContentPaneLayout = new GroupLayout(SobreContentPane);
			SobreContentPane.setLayout(SobreContentPaneLayout);
			SobreContentPaneLayout.setHorizontalGroup(
				SobreContentPaneLayout.createParallelGroup()
					.addGroup(SobreContentPaneLayout.createSequentialGroup()
						.addGroup(SobreContentPaneLayout.createParallelGroup()
							.addGroup(SobreContentPaneLayout.createSequentialGroup()
								.addGap(18, 18, 18)
								.addGroup(SobreContentPaneLayout.createParallelGroup()
									.addComponent(label3)
									.addComponent(label2, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
							.addGroup(SobreContentPaneLayout.createSequentialGroup()
								.addGap(38, 38, 38)
								.addGroup(SobreContentPaneLayout.createParallelGroup()
									.addComponent(label5, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
									.addComponent(label4)
									.addComponent(label6, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
							.addGroup(SobreContentPaneLayout.createSequentialGroup()
								.addGap(90, 90, 90)
								.addComponent(label1))
							.addGroup(GroupLayout.Alignment.TRAILING, SobreContentPaneLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(buttonOk, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			SobreContentPaneLayout.setVerticalGroup(
				SobreContentPaneLayout.createParallelGroup()
					.addGroup(SobreContentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(label3)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(label4)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(label5)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(label6)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
						.addComponent(buttonOk)
						.addContainerGap())
			);
			Sobre.pack();
			Sobre.setLocationRelativeTo(Sobre.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - 
	private JFrame Sobre;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JButton buttonOk;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public void CriaSobre (){
		this.initComponents();
	}
}

