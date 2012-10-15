
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed May 12 20:14:53 GMT 2010
 */



/**
 * @author 
 */
public class LoginServidor  {

	SimpleServer servidor;

	InetAddress thisIp;



	public LoginServidor(SimpleServer sv){
		this.servidor = sv;

	}

	// ação para quando o botão ok for pressionado
	private void buttonOKActionPerformed(ActionEvent e) {

		if(validaPorta()){
			this.erro("Aguardando Conexão.");


			this.Login.setVisible(false);

		}else{
			this.erro("Porta já usada ou indisponível.");
			this.labelErro.setForeground(Color.RED);
		} 


	}
	public void erro (String erro){


		this.labelErro.setText(erro);



	}
	
	//valida a porta de acordo com as restrições
	public boolean validaPorta(){
		boolean booleado = false;
		try{
			if(getPorta() >= 1024){
				booleado = servidor.init(getPorta());
				return true;

			}




			return booleado;
		}catch(Exception e){
			return false;
		} catch (Throwable e) {
			return false;
		}
	}


	public int getPorta(){

		return Integer.parseInt(textFieldPorta.getText());
	}
	public String getApelido(){

		return textFieldApelido.getText();
	}

	private void buttonCancelarMousePressed(MouseEvent e) {
		this.Login.dispose();
		System.exit(0);
	}

	protected void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  

		//GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - 
		Login = new JFrame();
		label1 = new JLabel();
		labelApelido = new JLabel();
		textFieldApelido = new JTextField();
		labelPorta = new JLabel();
		textFieldPorta = new JTextField();
		buttonOK = new JButton();
		buttonCancelar = new JButton();
		labelErro = new JLabel();

		//======== Login ========
		{
			Login.setTitle("Login - Servidor");
			Login.setResizable(false);
			Login.setVisible(true);
			Container LoginContentPane = Login.getContentPane();
			Login.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
			Login.setIconImage(new ImageIcon(getClass().getResource("cliente.png")).getImage());
			Login.getRootPane().setDefaultButton(buttonOK);
			try {  
		        
		         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		      }catch(Exception e) {
		      }

			//---- label1 ----
			label1.setText("Login Extreme Messenger");

			//---- labelApelido ----
			labelApelido.setText("Apelido:");

			//---- labelPorta ----
			labelPorta.setText("Porta:");

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

			//---- labelErro ----
			labelErro.setText(" ");
			try {
				this.thisIp = InetAddress.getLocalHost();
				erro("Seu IP é "+thisIp.getHostAddress());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}



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
											.addComponent(labelApelido)
											.addComponent(labelPorta))
											.addGap(37, 37, 37)
											.addGroup(LoginContentPaneLayout.createParallelGroup()
													.addGroup(LoginContentPaneLayout.createSequentialGroup()
															.addComponent(buttonOK)
															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(buttonCancelar)
															.addGap(106, 106, 106))
															.addGroup(LoginContentPaneLayout.createSequentialGroup()
																	.addGroup(LoginContentPaneLayout.createParallelGroup()
																			.addComponent(textFieldPorta, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
																			.addComponent(textFieldApelido, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
																			.addGap(6, 6, 6))))
																			.addGroup(LoginContentPaneLayout.createSequentialGroup()
																					.addGap(137, 137, 137)
																					.addComponent(labelErro)
																					.addContainerGap(144, Short.MAX_VALUE))
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
											.addComponent(textFieldPorta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(labelPorta))
											.addGap(1, 1, 1)
											.addComponent(labelErro)
											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
											.addGroup(LoginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
													.addComponent(buttonOK)
													.addComponent(buttonCancelar))
													.addContainerGap())
			);
			Login.pack();
			Login.setLocationRelativeTo(Login.getOwner());
		}
		// JFormDesigner - End of component initialization  

		//GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  

	//GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - 
	protected JFrame Login;
	private JLabel label1;
	private JLabel labelApelido;
	private JTextField textFieldApelido;
	private JLabel labelPorta;
	private JTextField textFieldPorta;
	private JButton buttonOK;
	private JButton buttonCancelar;
	private JLabel labelErro;

	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
