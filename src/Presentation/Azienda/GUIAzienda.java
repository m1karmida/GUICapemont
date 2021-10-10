package Presentation.Azienda;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import DomainClasses.Azienda ;

import Business.Client;
import DomainClasses.Prodotto;

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
	private GroupLayout gl_contentPane;
	
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
		
		setComponents();
	
	}
	
	
	private void setComponents() {
		
		gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnVisualizza, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInserisci, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
					.addGap(65))
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
		
		setLocationRelativeTo(init);
	}
	
	
	
	
	/******************* funzioni di supporto **********************************/
	
	private void clickInserisci() {
		
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GUIinsertProdotto insert = new GUIinsertProdotto(azienda,init);
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
					
					if (listaProdotti == null || listaProdotti.isEmpty())
						
						JOptionPane.showMessageDialog(rootPane, "Non ci sono prodotti disponibili per l'azienda selezionata!","ERRORE AZIENDA",JOptionPane.WARNING_MESSAGE);
					
					else { 
							String column[] = {"Nome","Categoria","Quantita", "Prezzo", "Nome fornitore", "indirizzo fornitore"};
							String data[][] = new String[listaProdotti.size()][column.length];
							int i = 0;
							
							for (Prodotto p : listaProdotti){
								data[i][0] = p.getNome();
								data[i][1] = p.getCategoria().toString();
								data[i][2] = p.getQuantita() + "";
								data[i][3] = p.getPrezzo() + "";
								data[i][4] = p.getFornitore().getNome();
								data[i][5] = p.getFornitore().getIndirizzo();
								i++;
							}
							
							JTable table = new JTable(data, column);
							table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							JScrollPane tablePane = new JScrollPane(table);
							tablePane.setSize(table.getWidth(),table.getHeight());
							JOptionPane.showMessageDialog(rootPane, tablePane, "Prodotti disponibili",JOptionPane.INFORMATION_MESSAGE);
						}
						} catch (IOException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(rootPane, "Errore: Connessione non riuscita con il server!","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);
						}
			}
		});
	}
}