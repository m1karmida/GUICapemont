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
	
	public GUIOrdine(Persona persona, JFrame init) {
		
		this.persona = persona;
		Agente agente = chooseAgente();
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 559, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCarrello, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnOrdina, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(25)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(203)
					.addComponent(lblProdotti)
					.addPreferredGap(ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
					.addComponent(lblCarrello, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(197)
					.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(410, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCarrello, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProdotti))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tablePane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtCarrello, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addComponent(btnOrdina, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	private Agente chooseAgente() {
		
		Client client;
		try {
			client = new Client("93.88.110.173", 5000);
			ArrayList<Agente> agenti = client.getAgenti();
			client.closeConnection();
			
			if (agenti == null)
				JOptionPane.showMessageDialog(rootPane, "Non ci sono agenti a disposizione per l'ordine!","ERRORE AGENTI",JOptionPane.ERROR_MESSAGE);	
			
			else {
				String nomiCogAgenti[] = new String[agenti.size()];
				String emailAgenti[] = new String[agenti.size()];				
				int i = 0;
				
				for (Agente a : agenti) {
					nomiCogAgenti[i] = a.getNome() + " " + a.getCognome() + " \t " + a.getIndirizzo();
					emailAgenti[i++] = a.getEmail();
				}
				
				JComboBox comboBox = new JComboBox(nomiCogAgenti);
				JOptionPane.showMessageDialog(rootPane, comboBox, "Selezione Agente di vendita",JOptionPane.QUESTION_MESSAGE);
				
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
					JOptionPane.showMessageDialog(null, "Errore: quantit� maggiore della disponibilit�!");
			}
		});
	}
	
	
	
	private void clickEffettua() {

			
			btnOrdina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				try {
					Client client = new Client("93.88.110.173", 5000);
					System.out.println(ordine);
					if (client.makeOrder(ordine))
						JOptionPane.showMessageDialog(null, "Il suo ordine � andato a buon fine!","ESITO ORDINE",JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Errore: ordine non andato a buon fine. Riprovare!","ERRORE ORDINE",JOptionPane.ERROR_MESSAGE);
					
					client.closeConnection();
					txtCarrello.setText("");
					populateTable(column.length);
					
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Connessione con il server non riuscita: riprovare","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);	
					ex.printStackTrace();
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
			JOptionPane.showMessageDialog(null, "Connessione con il server non riuscita: riprovare","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);	
		}

	}
}
