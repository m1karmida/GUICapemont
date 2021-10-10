package Presentation.Cliente;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Business.Client;
import DomainClasses.Azienda;
import DomainClasses.CategoriaProdotto;
import DomainClasses.Fornitore;
import DomainClasses.Persona;
import DomainClasses.Prodotto;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUICliente extends JFrame {

	private Persona cliente;
	private JPanel contentPane;
	private JButton btnVisualizza;
	private JButton btnOrdine;
	private JFrame init;
	private GroupLayout gl_contentPane;

	public GUICliente(Persona cliente, JFrame init) {
		
		this.init = init;
		this.cliente = cliente;
		
		setTitle("Portale Cliente: " + cliente.getNome() + " " + cliente.getCognome());
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnVisualizza = new JButton("Visualizza catalogo");
		clickVisualizza();
		
		btnOrdine = new JButton("Effettua ordine");
		clickOrdine();
		
		setComponents();
		
	}

	private void setComponents() {
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(77)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnOrdine, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnVisualizza, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(btnVisualizza, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(btnOrdine, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		
		contentPane.setLayout(gl_contentPane);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
				init.setVisible(true);
			}


		});
		
		setLocationRelativeTo(init);
	}
	
	private void clickVisualizza() {
		
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String options[] = {"Visualizza tutti i prodotti","Visualizza per azienda"};
				int confirm = JOptionPane.showOptionDialog(rootPane,"Scegli il profilo da registrare: ",null, 0 ,
						JOptionPane.INFORMATION_MESSAGE,null,options,null);
				
				switch(confirm) {
				case 1:{
					
					allProduct();
					break;
					
				}
				case 2: {
					
					productAziende();
					break;
				}
				
				default:
					JOptionPane.showMessageDialog(rootPane, "Errore nella scelta: riprovare","ERRORE SCELTA",JOptionPane.ERROR_MESSAGE);
				
				}
			}
		});
		
	}
	
	
	private void allProduct() {

		try {
			Client client = new Client("93.88.110.173", 5000);
			ArrayList<Prodotto> listaProdotti  = client.getListaProdotti();
			if (listaProdotti==null)
				JOptionPane.showMessageDialog(rootPane, "Nessun prodotto disponibile!","ERRORE PRODOTTO",JOptionPane.ERROR_MESSAGE);
			else {	
				String columns[] = {"Codice","Nome", "Disponibilita", "Prezzo", "Fornitore", "Azienda"};
				String[][] data = new String[listaProdotti.size()][columns.length];
				
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
				
				JTable table = new JTable(data, columns);
				JScrollPane tablePane = new JScrollPane(table);
				tablePane.setSize(table.getWidth(),table.getHeight());
				JOptionPane.showMessageDialog(rootPane, tablePane, "Prodotti disponibili",JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Connessione con il server non riuscita: riprovare","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);	
		}

		
	
	}
	
	
	private void productAziende() {
		
		
		try {
			
			Client client = new Client("93.88.110.173", 5000);
			ArrayList<Azienda> aziende = client.getAziende();
			client.closeConnection();
			
			if (aziende == null)

					JOptionPane.showMessageDialog(rootPane, "Errore: Non ci sono aziende disponibili!",
															"ERRORE FORNITORI",JOptionPane.ERROR_MESSAGE);
			
			else {
				/********* seleziona il fornitore per la categoria *******/
				
					String nomiAziende[] = new String[aziende.size()];
					String emailAziende[] = new String[aziende.size()];				
					int i = 0;
					
					for (Azienda a: aziende) {
						emailAziende[i] = a.getEmail();
						nomiAziende[i++] = a.getNome();
					}
					
					JComboBox comboBox = new JComboBox(nomiAziende);
					JOptionPane.showMessageDialog(rootPane, comboBox, "Selezione Aziende",JOptionPane.QUESTION_MESSAGE);
					
					int index = comboBox.getSelectedIndex();
					Azienda azienda = null;
					
					for (Azienda a: aziende)
						if (a.getEmail().equals(emailAziende[index])){
							azienda = a;
							break;
							}

				/********* visualizza i prodotti per Azienda *******/
					client = new Client("93.88.110.173", 5000);
					ArrayList<Prodotto> listaProdotti  = client.getListaProdottidiAzienda(azienda);
					
					if (listaProdotti==null)
						JOptionPane.showMessageDialog(rootPane, "Nessun prodotto disponibile!","ERRORE PRODOTTO",JOptionPane.ERROR_MESSAGE);
					else {	
						String columns[] = {"Codice","Nome", "Disponibilita", "Prezzo", "Fornitore"};
						String[][] data = new String[listaProdotti.size()][columns.length];
						
						i = 0;
						
						for (Prodotto p : listaProdotti) {
							if (p.getQuantita() > 0) {
								data[i][0] = p.getCodice_prodotto();
								data[i][1] = p.getNome();
								data[i][2] = p.getQuantita() + "";
								data[i][3] = p.getPrezzo() + "";
								data[i][4] = p.getFornitore().getNome();
							}
						}
						
						JTable table = new JTable(data, columns);
						JScrollPane tablePane = new JScrollPane(table);
						tablePane.setSize(table.getWidth(),table.getHeight());
						JOptionPane.showMessageDialog(rootPane, tablePane, "Prodotti disponibili dell'Azienda " + azienda.getNome(),JOptionPane.INFORMATION_MESSAGE);
					}
		
					}
			}	catch(ArrayIndexOutOfBoundsException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(rootPane, "Errore: Non hai selezionato alcun fornitore!","ERRORE FORNITORE",JOptionPane.ERROR_MESSAGE);
			
			} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, "Connessione con il server non riuscita: riprovare","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);	
		}

		
	}
	

	
	private void clickOrdine() {
	
		btnOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIOrdine ordine = new GUIOrdine(cliente,init);
			}
		});
	
	}

	
}