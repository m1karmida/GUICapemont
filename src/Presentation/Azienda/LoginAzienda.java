package Presentation.Azienda;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DomainClasses.Azienda;
import Business.Client;

import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LoginAzienda extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPwd;
	private JTextField txtUser;
	private JButton btnLogin;
	private JLabel lblPassword;
	private JLabel lblUser;
	private JFrame init;
	private GroupLayout gl_contentPane;

	
	public LoginAzienda(JFrame init) {
		this.init = init;
		setTitle("Login Azienda");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtPwd = new JPasswordField();
		txtPwd.setColumns(20);
		
		txtUser = new JTextField();
		txtUser.setColumns(20);
		
		lblUser = new JLabel("user");
		
		lblPassword = new JLabel("password");
		
		btnLogin = new JButton("Login");
		clickLogin();
		
		setComponents();
	}
	
	
	private void setComponents() {
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(lblPassword))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addComponent(lblUser)))
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogin)
						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPwd, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
					.addGap(54))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(71, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUser))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(18)
					.addComponent(btnLogin)
					.addGap(47))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(init);
		
	}
	
	private void clickLogin() {

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String user = txtUser.getText();
				String password = new String(txtPwd.getPassword());	

				txtUser.setText("");
				txtPwd.setText("");
				
				if (user.equals("") || password.equals(""))
					
					JOptionPane.showMessageDialog(null, "Errore: uno dei campi mancanti!","ERRORE REGISTRAZIONE",JOptionPane.ERROR_MESSAGE);
				
				else {
					
					try {
							Client c = new Client("93.88.110.173", 5000);
							Azienda az = c.makeLoginAzienda(user, password);
							if ( az != null ) {
								GUIAzienda azienda = new GUIAzienda(az, init);
								init.setVisible(false);
								azienda.setVisible(true);
								setVisible(false);
							}
							else {
								
								JOptionPane.showMessageDialog(null, "Errore: credenziali errate!","ERRORE LOGIN",JOptionPane.ERROR_MESSAGE);
							}
							c.closeConnection();
						} catch (IOException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Errore: Connessione non riuscita con il server!","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);
						}	
					}
				}
			});

	}
}
