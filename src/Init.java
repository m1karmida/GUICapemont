import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import Azienda.LoginAzienda;
import Cliente.Cliente;
import Cliente.LoginCliente;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.*;
import javax.swing.*;

public class Init extends JFrame {

	private JPanel contentPane;
	private JButton btnCliente;
	private JButton btnAzienda;



	/**
	 * Launch the application.
	 */


	

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnCliente = new JButton("Cliente");
		clickCliente();

		btnAzienda = new JButton("Azienda");
		clickAzienda();


		JLabel lblBenvenuto = new JLabel("Benvenuto!");
		lblBenvenuto.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblShowB = new JLabel("Cliccare per fare il login:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(70)
					.addComponent(btnAzienda)
					.addGap(66)
					.addComponent(btnCliente)
					.addContainerGap(46, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addComponent(lblShowB)
					.addGap(129))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(120)
					.addComponent(lblBenvenuto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(74))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(lblBenvenuto)
					.addGap(18)
					.addComponent(lblShowB)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAzienda)
						.addComponent(btnCliente))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}


	private void clickAzienda() {
		btnAzienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAzienda login = new LoginAzienda();
				login.setVisible(true);
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

