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
	
	private Azienda azienda;
	private JPanel contentPane;
	private JButton btnInserisci;
	private JButton btnVisualizza; 
	
	public GUIAzienda(Azienda azienda) {
		this.azienda = azienda;
		setTitle("Portale Azienda: " + this.azienda.getNome());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 374, 252);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		btnInserisci = new JButton("Inserisci nuovo prodotto");
		clickInserisci();
		
		btnVisualizza = new JButton("Visualizza Catalogo");
		clickVisualizza();
		
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnVisualizza, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInserisci, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
					.addGap(91))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnInserisci, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnVisualizza, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		this.contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}
	
	
	/******************* funzioni di supporto **********************************/
	
	private void clickInserisci() {
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIinsertProdotto insert = new GUIinsertProdotto(azienda);
				insert.setVisible(true);
			}
		});
	}
	
	
	private void clickVisualizza() {
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Client c = new Client("93.88.110.173", 5000);
					ArrayList<Prodotto> listaProdotti = c.getListaProdottidiAzienda(azienda);
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