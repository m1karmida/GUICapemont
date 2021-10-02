import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import org.eclipse.swt.browser.VisibilityWindowAdapter;

import APIClient.Client;
import Azienda.LoginAzienda;
import Cliente.GUICliente;
import Cliente.LoginCliente;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

import java.awt.*;
import javax.swing.*;


public class Init extends JFrame {

	private GroupLayout gl_contentPane;
	
	private JPanel contentPane;
	
	private JButton btnCliente;
	private JButton btnAzienda;
	private JButton btnRegistra;
	
	private JLabel lblRegistra;
	private JLabel lblBenvenuto;
	private JLabel lblShowB;

	private  Client client;


	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Init frame = new Init();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 */
	public Init() {
		setBackground(Color.GRAY);
		
/********** apertura connessione ****************/
		try {
			client = new Client("93.88.110.173",5000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} ;
		
		
	
/*********** codice generato per la costruzione del form *************/		
		setBounds(100, 100, 427, 284);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		btnCliente = new JButton("Cliente");
		clickCliente();

		btnAzienda = new JButton("Azienda");
		clickAzienda();

		btnRegistra = new JButton("Registrati");
		clickRegistra();
		
		lblBenvenuto = new JLabel("Benvenuto!");
		lblBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenuto.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblShowB = new JLabel("Cliccare per fare il login:");
		
		lblRegistra = new JLabel("Non sei ancora registrato?");
		
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(128, Short.MAX_VALUE)
							.addComponent(lblShowB)
							.addGap(3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(btnAzienda)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(btnCliente)
					.addGap(58))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addComponent(lblBenvenuto, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(95))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(150)
					.addComponent(btnRegistra)
					.addContainerGap(163, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(112)
					.addComponent(lblRegistra)
					.addContainerGap(126, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(lblBenvenuto)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblShowB)
							.addGap(49))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCliente)
								.addComponent(btnAzienda))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(13)
					.addComponent(lblRegistra)
					.addGap(18)
					.addComponent(btnRegistra)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				int confirm = JOptionPane.showOptionDialog(
			             null, "Sei sicuro di chiudere l'applicazione?", 
			             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
			             JOptionPane.QUESTION_MESSAGE, null, null, null);
			        if (confirm == 0) {
				       client.closeConnection();
			           System.exit(0);
			        }
				
			}


		});
	}


	
	
/***************** funzioni di supporto per la gestione dei pulsanti ***************************/
	private void clickAzienda() {
		btnAzienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAzienda login = new LoginAzienda();
				login.setVisible(true);
			}
		});
	}


	private void clickRegistra() {
		btnRegistra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}
	
	private void clickCliente() {
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							LoginCliente frame = new LoginCliente();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});


	}
}

