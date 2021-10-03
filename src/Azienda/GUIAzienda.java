package Azienda;

import APIClient.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import APIClient.Client;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUIAzienda extends JFrame {
	private String email;
	private JPanel contentPane;
	private JButton btnInserisci;
	private JButton btnVisualizza; 
	
	public GUIAzienda(String email) {
		this.email = email;
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		btnInserisci = new JButton("Inserisci");
		
		btnVisualizza = new JButton("Visualizza");
		clickVisualizza();
		
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGap(52).addComponent(btnInserisci).addPreferredGap(ComponentPlacement.RELATED, 181, 32767).addComponent(btnVisualizza).addGap(43)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap(119, 32767).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnVisualizza).addComponent(btnInserisci)).addGap(109)));
		this.contentPane.setLayout(gl_contentPane);

	}
	
	
	/******************* funzioni di supporto **********************************/
	private void clickVisualizza() {
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Client c = new Client("93.88.110.173", 5000);
					ArrayList<Prodotto> listaProdotti = c.getListaProdottiAzienda();
					String prodotti = "";
					
					for (Prodotto p : listaProdotti)
						prodotti = prodotti + p.toString() + '\n';
					
					
					JOptionPane.showMessageDialog(null, prodotti );
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
}