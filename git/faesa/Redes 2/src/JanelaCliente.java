
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;





import java.io.*;
/*
 * Created by JFormDesigner on Tue May 11 20:57:56 GMT 2010
 */



/**
 * @author 
 */
public class JanelaCliente  {

	JFileChooser fc = new JFileChooser();
	File selFile;
	Color cor;
	SimpleClient cliente;
	String ip ="";
	int porta;
	String apelido;
	//LoginCliente log = new LoginCliente();
	public JanelaCliente(String ap, SimpleClient cli){
		this.apelido = ap;
		this.cliente = cli;
	}

	private void menuItemSalvarMousePressed(MouseEvent e) {

		fc.showSaveDialog(janelaPrincipal);
		selFile = fc.getSelectedFile();

		File arquivo;


		try {
			String ext[] = selFile.toString().split("\\.");  
			int i = ext.length; 
			if(i > 1){
				arquivo = new File(selFile.toString());
			}else{

				arquivo = new File(selFile.toString()+".rtf");
			}

			FileOutputStream zos = new FileOutputStream(arquivo);
			try {
				zos.write(this.painelReceber.getText().getBytes());
				zos.flush();
				zos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  


	}
	private void painelEnviarCaretUpdate(CaretEvent e) {
		type.play();

	}

	private void menuItemAjudaMousePressed(MouseEvent e) {

		janelaPrincipal.dispose();
		System.exit(0);
	}

	private void menuItemPadraoMousePressed(MouseEvent e) {
		cor = new Color (238,238,238);
		panel1.setBackground(cor);
		janelaPrincipal.getContentPane().setBackground(cor);	
	}

	private void menuItemVerdeMousePressed(MouseEvent e) {
		cor = new Color (0,204,51);
		panel1.setBackground(cor);
		janelaPrincipal.getContentPane().setBackground(cor);
	}

	private void menuItem4MousePressed(MouseEvent e) {
		cor = new Color (203,39,39);
		panel1.setBackground(cor);
		janelaPrincipal.getContentPane().setBackground(cor);
	}

	private void menuItemAparenciaMousePressed(MouseEvent e) {
		cor = new Color (71,115,208);
		panel1.setBackground(cor);
		janelaPrincipal.getContentPane().setBackground(cor);
	}

	private void menuItem5MousePressed(MouseEvent e) {
		cor = new Color (222,236,29);
		panel1.setBackground(cor);
		janelaPrincipal.getContentPane().setBackground(cor);
	}

	private void menuItemSobreMousePressed(MouseEvent e) {
		Sobre sobre = new Sobre();
		sobre.CriaSobre();

	}
	
	//  ação para envio das mensagens
	private void botaoEnviarActionPerformed(ActionEvent e) {
		if(this.botaoEnviar.isEnabled()){
			if(painelEnviar.getText().length() > 0){

				try {
					cliente.theSocket.println(painelEnviar.getText());
					
					//String s = cliente.theSocket.readLine();

					this.enviaMensagem(Color.ORANGE,this.apelido+" diz: ");
					this.enviaMensagem(Color.BLACK,painelEnviar.getText()+"\n");
					painelEnviar.setText("");	

				} catch (IOException e1) {

					e1.printStackTrace();
				}
				this.painelEnviar.grabFocus();
			}
		}
	}

	
// escreve a mensagem na tela da pessoa que está enviando a mensagem
	protected void enviaMensagem(Color cor, String msg ){
		this.painelReceber.setEditable(true);
		painelReceber.append(cor, msg);
		this.painelReceber.setEditable(false);

	}
	
	//escreve a mensagem no painel de recebimento de mensagens
	protected void escreveMensagem(Color cor, String msg, String apelidoContato ){
		
		this.painelReceber.setEditable(true);
		if(msg != "")
		painelReceber.append(Color.BLUE, apelidoContato+" diz: ");
		
		painelReceber.append(cor, msg);
		this.painelReceber.setEditable(false);
	}

	private void initComponents() throws IOException {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - 
		janelaPrincipal = new JFrame();
		menuBar1 = new JMenuBar();
		menuArquivo = new JMenu();
		menuItemSalvar = new JMenuItem();
		menuItemAjuda = new JMenuItem();
		menuEditar = new JMenu();
		menuAparencia = new JMenu();
		menuItemPadrao = new JMenuItem();
		menuItemVerde = new JMenuItem();
		menuItemVermelho = new JMenuItem();
		menuItemAzul = new JMenuItem();
		menuItemAmarelo = new JMenuItem();
		menuAjuda = new JMenu();
		menuItemSobre = new JMenuItem();
		panel1 = new JPanel();
		botaoEnviar = new JButton();
		scrollPane2 = new JScrollPane();
		painelEnviar = new JTextPane();
		panel2 = new JPanel();
		scrollPane3 = new JScrollPane();
		painelReceber = new ColorPane();
		msg = Applet.newAudioClip(getClass().getResource("Message.wav")); 
		type = Applet.newAudioClip(getClass().getResource("MsgType.wav"));  


		//======== janelaPrincipal ========
		{
			janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			janelaPrincipal.setIconImage(new ImageIcon(getClass().getResource("cliente.png")).getImage());
			janelaPrincipal.setTitle("Messenger - Cliente");
			cor = new Color (238,238,238);
			janelaPrincipal.getContentPane().setBackground(cor);
			janelaPrincipal.setVisible(true);

			Container janelaPrincipalContentPane = janelaPrincipal.getContentPane();
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}catch (Exception e){
			}

			//log.initComponents();



			//======== menuBar1 ========
			{

				//======== menuArquivo ========
				{
					menuArquivo.setText("Arquivo");

					//---- menuItemSalvar ----
					menuItemSalvar.setText("Salvar");
					menuItemSalvar.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							menuItemSalvarMousePressed(e);
						}
					});
					menuArquivo.add(menuItemSalvar);

					//---- menuItemAjuda ----
					menuItemAjuda.setText("Sair");
					menuItemAjuda.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							menuItemAjudaMousePressed(e);
						}
					});
					menuArquivo.add(menuItemAjuda);
				}
				menuBar1.add(menuArquivo);

				//======== menuEditar ========
				{
					menuEditar.setText("Editar");

					//======== menuAparencia ========
					{
						menuAparencia.setText("Apar\u00eancia");

						//---- menuItemPadrao ----
						menuItemPadrao.setText("Padrão");
						menuItemPadrao.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								menuItemPadraoMousePressed(e);
							}
						});
						menuAparencia.add(menuItemPadrao);

						//---- menuItemVerde ----
						menuItemVerde.setText("Verde");
						menuItemVerde.setIcon(new ImageIcon(getClass().getResource("iconGreen.JPG")));
						menuItemVerde.setAlignmentX(0.0F);
						menuItemVerde.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								menuItemVerdeMousePressed(e);
							}
						});
						menuAparencia.add(menuItemVerde);

						//---- menuItemVermelho ----
						menuItemVermelho.setText("Vermelho");
						menuItemVermelho.setIcon(new ImageIcon(getClass().getResource("iconRed.JPG")));
						menuItemVermelho.setAlignmentX(0.0F);
						menuItemVermelho.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								menuItem4MousePressed(e);
							}
						});
						menuAparencia.add(menuItemVermelho);

						//---- menuItemAzul ----
						menuItemAzul.setText("Azul");
						menuItemAzul.setIcon(new ImageIcon(getClass().getResource("iconBlue.JPG")));
						menuItemAzul.setAlignmentX(0.0F);
						menuItemAzul.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								menuItemAparenciaMousePressed(e);
							}
						});
						menuAparencia.add(menuItemAzul);

						//---- menuItemAmarelo ----
						menuItemAmarelo.setText("Amarelo");
						menuItemAmarelo.setIcon(new ImageIcon(getClass().getResource("iconYellow.JPG")));
						menuItemAmarelo.setAlignmentX(0.0F);
						menuItemAmarelo.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								menuItem5MousePressed(e);
							}
						});
						menuAparencia.add(menuItemAmarelo);
					}
					menuEditar.add(menuAparencia);
				}
				menuBar1.add(menuEditar);

				//======== menuAjuda ========
				{
					menuAjuda.setText("Ajuda");

					//---- menuItemSobre ----
					menuItemSobre.setText("Sobre");
					menuItemSobre.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							menuItemSobreMousePressed(e);
						}
					});
					menuAjuda.add(menuItemSobre);
				}
				menuBar1.add(menuAjuda);
			}
			janelaPrincipal.setJMenuBar(menuBar1);

			//======== panel1 ========
			{
				cor = new Color (238,238,238);
				panel1.setBackground(cor);


				//---- botaoEnviar ----
				botaoEnviar.setText("Enviar");
				botaoEnviar.setMnemonic(KeyEvent.VK_S);
				botaoEnviar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						botaoEnviarActionPerformed(e);
					}
				});


				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(painelEnviar);
				}

				GroupLayout panel1Layout = new GroupLayout(panel1);
				panel1.setLayout(panel1Layout);
				panel1Layout.setHorizontalGroup(
						panel1Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
								.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(botaoEnviar, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				);
				panel1Layout.setVerticalGroup(
						panel1Layout.createParallelGroup()
						.addComponent(botaoEnviar, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
						.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
				);
			}

			//======== panel2 ========
			{
				painelEnviar.addCaretListener(new CaretListener() {
					public void caretUpdate(CaretEvent e) {
						painelEnviarCaretUpdate(e);
					}
				});
				//======== scrollPane3 ========
				{

					//---- painelReceber ----
					painelReceber.setEditable(false);
					scrollPane3.setViewportView(painelReceber);
				}

				GroupLayout panel2Layout = new GroupLayout(panel2);
				panel2.setLayout(panel2Layout);
				panel2Layout.setHorizontalGroup(
						panel2Layout.createParallelGroup()
						.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
				);
				panel2Layout.setVerticalGroup(
						panel2Layout.createParallelGroup()
						.addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
				);
			}

			GroupLayout janelaPrincipalContentPaneLayout = new GroupLayout(janelaPrincipalContentPane);
			janelaPrincipalContentPane.setLayout(janelaPrincipalContentPaneLayout);
			janelaPrincipalContentPaneLayout.setHorizontalGroup(
					janelaPrincipalContentPaneLayout.createParallelGroup()
					.addGroup(janelaPrincipalContentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(janelaPrincipalContentPaneLayout.createParallelGroup()
									.addComponent(panel2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addContainerGap())
			);
			janelaPrincipalContentPaneLayout.setVerticalGroup(
					janelaPrincipalContentPaneLayout.createParallelGroup()
					.addGroup(janelaPrincipalContentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
			);
			janelaPrincipal.pack();
			janelaPrincipal.setLocationRelativeTo(janelaPrincipal.getOwner());
		}

		
		//cliente.init(log.getIp(), log.getPorta());
		enviaMensagem(Color.GREEN,"Conexão realizada com sucesso"+"\n");
		cliente.theSocket.println(this.apelido);
		String apelidoContato = cliente.theSocket.readLine();
		//log.Login.setVisible(false);
		janelaPrincipal.setVisible(true);





		// recebe as mensagens enquanto a conexão está ativa
		while(true){
			try{
				
				escreveMensagem(Color.BLACK,cliente.theSocket.readLine()+"\n",apelidoContato);
				if(janelaPrincipal.getState() == janelaPrincipal.ICONIFIED){
					msg.play();
					

				}else if(!janelaPrincipal.isFocused()){
					msg.play();
				
				}




			}catch(Exception e){
				enviaMensagem(Color.RED,"Perda de conexão com o servidor"+"\n");
				painelReceber.setCaretPosition(painelReceber.getText().length());
				this.cliente.finish();
				this.botaoEnviar.setEnabled(false);

				break;

			}

		}




	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - 
	private JFrame janelaPrincipal;
	private JMenuBar menuBar1;
	private JMenu menuArquivo;
	private JMenuItem menuItemSalvar;
	private JMenuItem menuItemAjuda;
	private JMenu menuEditar;
	private JMenu menuAparencia;
	private JMenuItem menuItemPadrao;
	private JMenuItem menuItemVerde;
	private JMenuItem menuItemVermelho;
	private JMenuItem menuItemAzul;
	private JMenuItem menuItemAmarelo;
	private JMenu menuAjuda;
	private JMenuItem menuItemSobre;
	private JPanel panel1;
	private JButton botaoEnviar;
	private JScrollPane scrollPane2;
	private JTextPane painelEnviar;
	private JPanel panel2;
	private JScrollPane scrollPane3;
	private ColorPane painelReceber;
	private AudioClip type;
	private AudioClip msg;
	// JFormDesigner - End of variables declaration  //GEN-END:variables


	public static void main( String [] args ) throws IOException 
	{
		SimpleClient cliente = new SimpleClient();

		LoginCliente log = new LoginCliente(cliente);

		log.initComponents();
		while(log.Login.isVisible()){}

		String apelido = log.getApelido();

		JanelaCliente msn = new JanelaCliente(apelido,cliente);
		msn.initComponents();



	}


}







