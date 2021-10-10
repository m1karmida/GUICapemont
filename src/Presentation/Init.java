package Presentation;

import java.awt.EventQueue;

import Business.Client;
import Presentation.Azienda.LoginAzienda;
import Presentation.Azienda.RegisterAzienda;
import Presentation.Cliente.LoginCliente;
import Presentation.Cliente.RegisterCliente;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.*;
import java.awt.event.ActionEvent;

import java.awt.*;
import javax.swing.*;


public class Init extends JFrame {

	private static final long serialVersionUID = 1L;
	private GroupLayout gl_contentPane;
	private JFrame pointer;
	private JPanel contentPane;
	
	private JButton btnCliente;
	private JButton btnAzienda;
	private JButton btnRegistra;
	
	private JLabel lblRegistra;
	private JLabel lblBenvenuto;
	private JLabel lblShowB;



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


	
	
	public Init() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.pointer = this;
		setTitle("Capemont");
		setBackground(Color.GRAY);


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

		setComponents();
	}


	private void setComponents() {
		
		
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
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				
				int confirm = JOptionPane.showOptionDialog(
			             rootPane, "Sei sicuro di chiudere l'applicazione?", 
			             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
			             JOptionPane.QUESTION_MESSAGE, null, null, null);
				
			        if (confirm == 0) {
			           System.exit(0);
			        } else
			        	setVisible(true);
				
			}


		});
	}
	
	
	
	
/***************** funzioni di supporto per la gestione dei pulsanti ***************************/
	private void clickAzienda() {
	
		btnAzienda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				LoginAzienda login = new LoginAzienda(pointer);
				login.setVisible(true);
			}
		});
		
	}


	private void clickRegistra() {
		
		btnRegistra.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String[] options = new String[2];
				options[0] = new String("Azienda");
				options[1] = new String("Cliente");
				int confirm = JOptionPane.showOptionDialog(rootPane,"Scegli il profilo da registrare: ",null, 0 ,
																JOptionPane.INFORMATION_MESSAGE,null,options,null);
				
				if (confirm == 0) {
					RegisterAzienda r  =new RegisterAzienda(pointer);
					r.setVisible(true);
				} else if (confirm== 1) {
					RegisterCliente c = new RegisterCliente(pointer);
					c.setVisible(true);
				}
				
			}
		});
	}
	
	private void clickCliente() {
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							LoginCliente frame = new LoginCliente(pointer);
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

