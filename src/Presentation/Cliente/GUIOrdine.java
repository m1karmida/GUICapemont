package Presentation.Cliente;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Business.*;
import DomainClasses.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class GUIOrdine extends JFrame {
	
	private static int codice = 0;
	
	private JPanel contentPane;
	private Persona persona;
	private JTable tbProdotto;
	private JButton btnRimuovi;
	private JButton btnAggiungi;
	private JTextArea txtCarrello;
	private JButton btnOrdina;
	private String data[][];
	private String column[] = {"Codice","Nome", "Disponibilita", "Prezzo", "Fornitore", "Azienda"};
	private ArrayList<Prodotto> listaProdotti;
	private Ordine ordine;
	private JScrollPane tablePane;
	private JLabel lblProdotti;
	private JLabel lblCarrello;
	private GroupLayout gl_contentPane;
	
	public GUIOrdine(Persona persona, JFrame init) {
		
		this.persona = persona;
		Agente agente = chooseAgente(init);
		if (agente!= null) {
				ordine =  new Ordine(codice + "", Date.valueOf(LocalDate.now()), agente, this.persona);
				codice++;
				
				setTitle("Ordine in corso");
				setBounds(100, 100, 799, 495);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				
				populateTable(column.length);
				tbProdotto = new JTable(data, column);
				tablePane = new JScrollPane(tbProdotto);
				tablePane.setSize(tbProdotto.getWidth(),tbProdotto.getHeight());

				btnAggiungi = new JButton("Aggiungi al carrello");
				clickAggiungi();
				
				txtCarrello = new JTextArea();
				
				
				btnRimuovi = new JButton("Svuota Carrello");
				clickRimuovi();
				
				btnOrdina = new JButton("Effettua ordine");
				clickEffettua();
				
				lblProdotti = new JLabel("Prodotti disponibili");
				lblProdotti.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
				lblCarrello = new JLabel("Il tuo carrello");
				lblCarrello.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
				setComponents();
				
				setLocationRelativeTo(init);
				setVisible(true);
	
		}
		
		}

	
	private void setComponents() {

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(203)
					.addComponent(lblProdotti)
					.addPreferredGap(ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
					.addComponent(lblCarrello, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 559, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnOrdina, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(77)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(txtCarrello, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRimuovi)
							.addGap(36))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCarrello, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProdotti))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnOrdina, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtCarrello, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(btnRimuovi, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	private Agente chooseAgente(JFrame init) {
		
		Client client;
		try {
			client = new Client("93.88.110.173", 5000);
			ArrayList<Agente> agenti = client.getAgenti();
			client.closeConnection();
			
			if (agenti == null || agenti.isEmpty())
				JOptionPane.showMessageDialog(init, "Non ci sono agenti a disposizione per l'ordine!","ERRORE AGENTI",JOptionPane.ERROR_MESSAGE);
			
			else {
				String nomiCogAgenti[] = new String[agenti.size()];
				String emailAgenti[] = new String[agenti.size()];				
				int i = 0;
				
				for (Agente a : agenti) {
					nomiCogAgenti[i] = a.getNome() + " " + a.getCognome() + " \t " + a.getIndirizzo();
					emailAgenti[i++] = a.getEmail();
				}
				
				JComboBox comboBox = new JComboBox(nomiCogAgenti);
				JOptionPane.showMessageDialog(init, comboBox, "Selezione Agente di vendita",JOptionPane.QUESTION_MESSAGE);
				
				int index = comboBox.getSelectedIndex();
				
				for (Agente a : agenti) {
					if (a.getEmail().equals(emailAgenti[index]))
						return a;
				}
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Attenzione: devi selezionare almeno un agente di Vendita!","ERRORE SELEZIONE",JOptionPane.ERROR_MESSAGE);	
		}
		catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Connessione con il server non riuscita: riprovare","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);	
		}
		
		
		return null;
		
	}
	
	
	
	
	private void clickAggiungi() {
		
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index =  tbProdotto.getSelectedRow();
				if (index < 0) 
					JOptionPane.showInputDialog(rootPane, "Non hai selezionato alcun prodotto!" );
				else {
						int quantita = Integer.parseInt(JOptionPane.showInputDialog(rootPane, "Hai selezionato " + data[index][1] + ". Inserisci la quantita da ordinare :" ));
						
						if (Integer.parseInt(data[index][2]) >= quantita) {
							
							txtCarrello.append(data[index][1] + "\t" + quantita + "\n");
							int quantitaRimasta = Integer.parseInt(data[index][2]) - quantita;
							data[index][2] = quantitaRimasta + "";
							tbProdotto.setValueAt(quantitaRimasta + "", index, 2);
							Prodotto prodotto = null;
							for (Prodotto p : listaProdotti)
								if (p.getCodice_prodotto().equals(data[index][0])) {
									prodotto = p;
									break;
								}
		
							ordine.addElenco_prodotti(new ProdottoOrdinato(prodotto, quantita));
						} else
							JOptionPane.showMessageDialog(rootPane, "Errore: quantita maggiore della disponibilita!");
					}
			}
		});
	}
	
	
	private void clickRimuovi() {
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCarrello.getText().equals(""))
					JOptionPane.showMessageDialog(rootPane, "Il tuo carrello è già vuoto!", "ERRORE CARRELLO", JOptionPane.ERROR_MESSAGE);
				else {
					int confirm = JOptionPane.showOptionDialog(rootPane, "Sei sicuro di voler rimuovere gli elementi dal carrello?",
							"Svuota carrello", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (confirm == 0) {
						for (ProdottoOrdinato po : ordine.getElenco_prodotti()) {

							int i;
							for (i = 0; i < tbProdotto.getRowCount(); i++) {
								if (data[i][0].equals(po.getCodice_prodotto())) {
									System.out.println("trovato " + i + "");
									break;
								}
							}
							data[i][2] = (Integer.parseInt(data[i][2]) + po.getQuantita_ordinata()) + "";
							tbProdotto.setValueAt(data[i][2], i, 2);
						}
						ordine.clearElencoProdotti();
						txtCarrello.setText("");
					}
				}
			}
		});
		
	}
	
	private void clickEffettua() {

			
			btnOrdina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					float somma = 0;
					for (ProdottoOrdinato po : ordine.getElenco_prodotti())
						somma += po.getPrezzo() * po.getQuantita_ordinata();
					
					int confirm = JOptionPane.showOptionDialog(rootPane, "Il totale dell'ordine è di : " + somma + " €. Vuoi confermare l'ordine?", 
																"Conferma Ordine", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (confirm == 0) {
									try {
										Client client = new Client("93.88.110.173", 5000);
										if (client.makeOrder(ordine))
											JOptionPane.showMessageDialog(rootPane, "Il suo ordine è andato a buon fine!","ESITO ORDINE",JOptionPane.INFORMATION_MESSAGE);
										else
											JOptionPane.showMessageDialog(rootPane, "Errore: ordine non andato a buon fine. Riprovare!","ERRORE ORDINE",JOptionPane.ERROR_MESSAGE);
										
										client.closeConnection();
										txtCarrello.setText("");
										populateTable(column.length);
										
									} catch (IOException ex) {
										JOptionPane.showMessageDialog(rootPane, "Connessione con il server non riuscita: riprovare","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);
										ex.printStackTrace();
									}

					}
					
				
				}
			});
			
			

	}
	
	
	private void populateTable(int columns) {

		try {
			Client client = new Client("93.88.110.173", 5000);
			listaProdotti = client.getListaProdotti();
			data = new String[listaProdotti.size()][columns];
			
			int i = 0;
			
			for (Prodotto p : listaProdotti) {
				if (p.getQuantita() > 0) {
					data[i][0] = p.getCodice_prodotto();
					data[i][1] = p.getNome();
					data[i][2] = p.getQuantita() + "";
					data[i][3] = p.getPrezzo() + "";
					data[i][4] = p.getFornitore().getNome();
					data[i++][5] = p.getA().getNome();
				}
			}




		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Connessione con il server non riuscita: riprovare","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);
		}

	}
}
