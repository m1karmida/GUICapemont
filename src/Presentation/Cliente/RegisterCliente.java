package Presentation.Cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Business.Client;
import DomainClasses.Persona;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class RegisterCliente extends JFrame {
	
	private final RegisterCliente pointer; 
	private JPanel contentPane;
	private GroupLayout gl_contentPane;
	
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtIndirizzo;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtRPassword;
	
	private JLabel lblIndirizzo;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblRipetiPassword;
	private JLabel lblNome;
	private JLabel lblCognome;
	
	private JButton btnRegistra;

	
	
	public RegisterCliente(JFrame frame) {
		
		this.pointer = this;
		setTitle("Registrazione Cliente");
		setBounds(100, 100, 388, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		lblNome = new JLabel("Nome");
		lblCognome = new JLabel("Cognome");
		lblIndirizzo = new JLabel("Indirizzo");
		lblEmail = new JLabel("E-mail");		
		lblPassword = new JLabel("Password");
		lblRipetiPassword = new JLabel("Ripeti password");
		
		txtNome = new JTextField();
		txtNome.setColumns(20);
		
		txtCognome = new JTextField();
		txtCognome.setColumns(20);
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setColumns(20);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(20);
		

		txtPassword = new JTextField();
		txtPassword.setColumns(20);
		
		txtRPassword = new JTextField();
		txtRPassword.setColumns(20);
		
		
		btnRegistra = new JButton("Registra");
		clickRegistra();
		
		SetComponents();

		setLocationRelativeTo(frame);
		
	}
	
	
	
	private void SetComponents() {
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCognome)
								.addComponent(lblIndirizzo)
								.addComponent(lblEmail)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPassword))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRipetiPassword)))
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtRPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(89))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(92)
					.addComponent(btnRegistra)
					.addContainerGap(136, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(66)
							.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtIndirizzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIndirizzo)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblCognome)))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRipetiPassword))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRegistra)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	private void clickRegistra() {
		
		btnRegistra.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if (!checkAllTxt())
					
					JOptionPane.showMessageDialog(rootPane, "Errore: uno dei campi mancanti!","ERRORE REGISTRAZIONE",JOptionPane.ERROR_MESSAGE);
				
				else if (!(txtPassword.getText().equals(txtRPassword.getText())))
					
					JOptionPane.showMessageDialog(rootPane, "Errore: Password non corrispondente nei due campi!","ERRORE PASSWORD",JOptionPane.ERROR_MESSAGE);
				
				else {
					try {
						Client c = new Client("93.88.110.173", 5000);
						
						
						Persona persona = new Persona(txtIndirizzo.getText(),txtEmail.getText().toLowerCase(),txtPassword.getText(),
																			txtNome.getText() , txtCognome.getText());
						
						if (c.makeRegisterUtente(persona)) {
						JOptionPane.showMessageDialog(rootPane, "Registrazione Avvenuta con successo: ora puoi effettuare il login");
						
						setVisible(false);
						}
						else 
							JOptionPane.showMessageDialog(rootPane, "Errore imprevisto: Riprovare");
						clearTXTs();
						c.closeConnection();
						
					} catch (IOException e) {

						e.printStackTrace();
						JOptionPane.showMessageDialog(rootPane, "Connessione con il server non riuscita: riprovare","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);	
						
					}
					
				}

			}
			
		});
	}
	
	private void clearTXTs() {
		
		txtNome.setText("");
		txtPassword.setText("");
		txtRPassword.setText("");
		txtEmail.setText("");
		txtIndirizzo.setText("");
		txtCognome.setText("");
		
	}
	
	
	private boolean checkAllTxt() {
		
		if (txtNome.getText().equals(""))
			return false;
		
		if (txtEmail.getText().equals(""))
			return false;
		
		if (txtIndirizzo.getText().equals(""))
			return false;
		
		if (txtCognome.getText().equals(""))
			return false;
		
		if (txtPassword.getText().equals(""))
			return false;
		
		if (txtRPassword.getText().equals(""))
			return false;
		
		return true;
		
	}
}
