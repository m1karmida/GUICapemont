package Presentation.Cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Business.Client;
import DomainClasses.Persona;

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

public class LoginCliente extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPwd;
	private JTextField txtUser;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JFrame init;
	private GroupLayout gl_contentPane;
	
	
	public LoginCliente(JFrame init) {
		
		this.init = init;
		setTitle("Login Cliente");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 360, 222);
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(lblPassword))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addComponent(lblUser)))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLogin)
						.addComponent(txtUser)
						.addComponent(txtPwd))
					.addGap(108))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
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
				String user = txtUser.getText().toLowerCase();
				String pwd = new String(txtPwd.getPassword());
				txtUser.setText("");
				txtPwd.setText("");
				
				if (user.equals("") || pwd.equals(""))
					
					JOptionPane.showMessageDialog(rootPane, "Errore: uno dei campi mancanti!","ERRORE REGISTRAZIONE",JOptionPane.ERROR_MESSAGE);
				
				else {
				try {
					Client c = new Client("93.88.110.173", 5000);
					Persona p = c.makeLoginUtente(user,pwd);
					if (p!=null) {
						GUICliente cliente = new GUICliente(p,init);
						cliente.setVisible(true);
						setVisible(false);
						init.setVisible(false);
					}
					else {	
						JOptionPane.showMessageDialog(rootPane, "Errore: credenziali non corrette!","ERRORE CREDENZIALI",JOptionPane.ERROR_MESSAGE);
					
					}
					c.closeConnection();
				} catch (IOException e) {
					e.printStackTrace();	
					JOptionPane.showMessageDialog(rootPane, "Errore: Connessione non riuscita con il server","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);
					
				}
				
				}
			}
		});
	}
}
