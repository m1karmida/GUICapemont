package Azienda;

import APIClient.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import APIClient.Client;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUIAzienda extends JFrame {
	
	private Azienda azienda;
	private JPanel contentPane;
	private JButton btnInserisci;
	private JButton btnVisualizza; 
	private JFrame init;
	
	public GUIAzienda(Azienda azienda, JFrame init) {
		
		this.init = init;
		this.azienda = azienda;
		setTitle("Portale Azienda: " + this.azienda.getNome());
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
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
				init.setVisible(true);
			}


		});
		
		setLocationRelativeTo(null);
	}
	
	
	
	
	/******************* funzioni di supporto **********************************/
	
	private void clickInserisci() {
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIinsertProdotto insert = null;
				try {
					insert = new GUIinsertProdotto(azienda);
					insert.setVisible(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private void clickVisualizza() {
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Client c = new Client("93.88.110.173", 5000);
					ArrayList<Prodotto> listaProdotti = c.getListaProdottidiAzienda(azienda);
					
					String column[] = {"Nome","Categoria","Quantità", "Prezzo", "Nome fornitore", "indirizzo fornitore"};
					String data[][] = new String[listaProdotti.size()][column.length];
					int i = 0;
					
					for (Prodotto p : listaProdotti){
						data[i][0] = p.getNome();
						data[i][1] = p.getCategoria();
						data[i][2] = p.getQuantita() + "";
						data[i][3] = p.getPrezzo() + "";
						data[i][4] = p.getFornitore().getNome();
						data[i][5] = p.getFornitore().getIndirizzo();
						i++;
					}
					
					JTable table = new JTable(data, column);
					JScrollPane tablePane = new JScrollPane(table);
					tablePane.setSize(table.getWidth(),table.getHeight());
					JOptionPane.showMessageDialog(null, tablePane, "Prodotti disponibili",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
}