import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
/*
 * Created by JFormDesigner on Wed May 12 20:10:14 GMT 2010
 */



/**
 * @author 
 */
public class LoginCliente  {
	SimpleClient cliente;

	public LoginCliente (SimpleClient cli){
		this.cliente = cli;
	}

	public String getIp(){


		return textFieldIP.getText();
	}
	public int getPorta(){

		return Integer.parseInt(textFieldPorta.getText());
	}
	public String getApelido(){

		return textFieldApelido.getText();
	}

	
	//ação para quando o botão OK é pressionado
	private void buttonOKActionPerformed(ActionEvent e) {
		int flag = this.validaDados();
		
		if(flag == 1){
			this.Login.setVisible(false);

		}else if(flag == -1) {
			this.labelFalha.setForeground(Color.RED);
			this.erro("Destino não existe ou é inalcançável");

		}else if(flag == -2){
			this.labelFalha.setForeground(Color.RED);
			this.erro("A Porta deve ser entre 1 e 1024");
		}
	

	}

	public void erro (String erro){

		this.labelFalha.setText(erro);



	}

	
	// valida os dados de acordo com as restrições.
	public int validaDados (){ 
		int booleado = 0;

		try{
			if(getPorta() > 1024 && getPorta() < 65530){
			
			 cliente.init(getIp(), getPorta());	
			 return 1;
			}else{
				 return  -2;
			}
		}catch(Exception e){

			booleado = -1;
		}
		return -1;



	}


	private void buttonCancelarMousePressed(MouseEvent e) {
		this.Login.dispose();
		System.exit(0);
	}
	


	protected void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - 
		Login = new JFrame();
		label1 = new JLabel();
		labelApelido = new JLabel();
		textFieldApelido = new JTextField();
		labelIP = new JLabel();
		textFieldIP = new JTextField();
		label4 = new JLabel();
		textFieldPorta = new JTextField();
		buttonOK = new JButton();
		buttonCancelar = new JButton();
		labelFalha = new JLabel();
		//======== Login ========
		{
			Login.setTitle("Login - Cliente");
			Container LoginContentPane = Login.getContentPane();
			Login.setVisible(true);
			Login.setResizable(false);
			Login.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
			Login.setIconImage(new ImageIcon(getClass().getResource("cliente.png")).getImage());
			Login.getRootPane().setDefaultButton(buttonOK);
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}catch (Exception e){
			}
			
			
			//---- label1 ----
			label1.setText("Login Extreme Messenger");

			//---- labelApelido ----
			labelApelido.setText("Apelido:");

			//---- labelIP ----
			labelIP.setText("IP do Servidor:");

			//---- label4 ----
			label4.setText("Porta:");

			labelFalha.setVisible(true);
			labelFalha.setText(" ");

			//---- buttonOK ----
			buttonOK.setText("OK");
			buttonOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonOKActionPerformed(e);
				}
			});

			//---- buttonCancelar ----
			buttonCancelar.setText("Cancelar");
			buttonCancelar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					buttonCancelarMousePressed(e);
				}
			});


			GroupLayout LoginContentPaneLayout = new GroupLayout(LoginContentPane);
			LoginContentPane.setLayout(LoginContentPaneLayout);
			LoginContentPaneLayout.setHorizontalGroup(
					LoginContentPaneLayout.createParallelGroup()
					.addGroup(LoginContentPaneLayout.createSequentialGroup()
							.addGap(96, 96, 96)
							.addComponent(label1)
							.addContainerGap(123, Short.MAX_VALUE))
							.addGroup(LoginContentPaneLayout.createSequentialGroup()
									.addGap(28, 28, 28)
									.addGroup(LoginContentPaneLayout.createParallelGroup()
											.addComponent(labelIP)
											.addComponent(labelApelido)
											.addComponent(label4))

											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

											.addGroup(LoginContentPaneLayout.createParallelGroup()
													.addComponent(labelFalha)
													.addGroup(LoginContentPaneLayout.createSequentialGroup()

															.addComponent(buttonOK)
															.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
															.addComponent(buttonCancelar)
															.addContainerGap())

															.addGroup(LoginContentPaneLayout.createSequentialGroup()
																	.addGroup(LoginContentPaneLayout.createParallelGroup()

																			.addComponent(textFieldPorta, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
																			.addComponent(textFieldApelido, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
																			.addComponent(textFieldIP, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
																			.addContainerGap(20, Short.MAX_VALUE))))
			);
			LoginContentPaneLayout.setVerticalGroup(
					LoginContentPaneLayout.createParallelGroup()
					.addGroup(LoginContentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label1)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(LoginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(labelApelido)
									.addComponent(textFieldApelido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(LoginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											.addComponent(labelIP)
											.addComponent(textFieldIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addGroup(LoginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
													.addComponent(label4)
													.addComponent(textFieldPorta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

													.addComponent(labelFalha)


													.addGroup(LoginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
															.addGap(18, 18, 18)
															.addComponent(buttonOK)
															.addComponent(buttonCancelar))
															.addGap(18, 18, 18)
															.addContainerGap())
															.addGap(30, 30, 30)
			);
			Login.pack();
			Login.setLocationRelativeTo(Login.getOwner());


		}
	}
	// JFormDesigner - End of component initialization  //GEN-END:initComponents


	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - 
	protected JFrame Login;
	private JLabel label1;
	private JLabel labelApelido;
	private JTextField textFieldApelido;
	private JLabel labelIP;
	private JTextField textFieldIP;
	private JLabel label4;
	private JTextField textFieldPorta;
	private JButton buttonOK;
	private JButton buttonCancelar;
	private JLabel labelFalha;
	public boolean conectou = false;

	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
