package prj_bd;
import java.sql.*;


import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args)throws SQLException {
		
		
		Object[] possibilities = {"Inserire Voce","Cancellare Voce","Modificare Voce","Registrare Acquisto","Stampa Mensile Dati Acquisti","Stampa Mensile Dati Videogiochi","Stampa Migliori Sviluppatori"}; 
		
		JOptionPane.showMessageDialog(null, "Benvenuto nel Database di Lynk\n\n Premi OK per Continuare","Welcome!",1);
		String s = (String)JOptionPane.showInputDialog(null, "Seleziona L'operazione da fare\n", "DB", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Inserire Voce");
		
		if(s.equals("Inserire Voce")) {
			
			Object videogiochi[] = Videogioco.PrelevaNomiVideogiochi();
			Object[] clienti = Cliente.PrelevaNomiClienti();
			String[] valutazioni = {"1","2","3","4","5","6","7","8","9","10"};
			Object[] sviluppatori = Sviluppatore.PrelevaNomiSviluppatori();
			Object[] voci = {"Cliente", "Recensione", "Sviluppatore", "Videogioco"};
			
			int entity = JOptionPane.showOptionDialog(null, "Cosa vuoi inserire?", "Inserimento", 1, 1, null, voci, "Cliente");
			if(entity == 0) { 
				
				String nome =(String)JOptionPane.showInputDialog(null,"Inserisci il nome del Cliente:", "Nome Cliente");
				if(nome == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Nome mancante!"); System.exit(1);}
				String cognome = (String)JOptionPane.showInputDialog(null,"Inserisci il cognome del Cliente:", "Cognome Cliente");
				if(cognome == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Cognome mancante!"); System.exit(1);}
				String nomeProfilo = (String)JOptionPane.showInputDialog(null,"Inserisci il nome Profilo del Cliente:", "Nome Profilo");
				if(nomeProfilo == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Nome Profilo mancante!"); System.exit(1);}
				String email = (String)JOptionPane.showInputDialog(null,"Inserisci l'email del Cliente:", "email");
				if(email == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Email mancante!"); System.exit(1);}
				String password = (String)JOptionPane.showInputDialog(null,"Inserisci la password del Cliente:", "pwd");
				if(password == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Password mancante!"); System.exit(1);}
				
				Cliente cliente = new Cliente(nome,cognome,nomeProfilo,email,password,0);
				try {
				cliente.InsertIntoCliente();
				JOptionPane.showMessageDialog(null, "Inserimento riuscito con successo");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Inserimento fallito:SQL");}
				
			}
			if(entity == 1) { 
				
				String contenuto = (String)JOptionPane.showInputDialog("Inserisci il contenuto della recensione","Contenuto Recensione");
				if(contenuto == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Contenuto mancante!"); System.exit(1);}
				int valutazione = Integer.parseInt((String)JOptionPane.showInputDialog(null, "Valutazione", "Valutazione", JOptionPane.DEFAULT_OPTION, null, valutazioni, valutazioni[0]));
				if(valutazione == -1) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Valutazione mancante!"); System.exit(1);}
				String cliente = (String)JOptionPane.showInputDialog(null, "Lista", "Lista Clienti", JOptionPane.DEFAULT_OPTION, null, clienti , clienti[0]);
				if(cliente == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Cliente mancante!"); System.exit(1);}
				String videogioco = (String)JOptionPane.showInputDialog(null, "Lista", "Lista Videogiochi", JOptionPane.DEFAULT_OPTION, null, videogiochi , videogiochi[0]);
				if(videogioco == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Videogioco mancante!"); System.exit(1);}
				String dataPubblicazioneRecensione = JOptionPane.showInputDialog("Inserisci la data della recensione(FORMATO YYYY-MM-DD","Data Recensione");
				if(dataPubblicazioneRecensione == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Data mancante!"); System.exit(1);}
				
				Recensione recensione = new Recensione(contenuto,valutazione,cliente,videogioco,dataPubblicazioneRecensione);
				try {
					recensione.InsertIntoRecensione();
					JOptionPane.showMessageDialog(null, "Inserimento riuscito con successo");
					}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Inserimento fallito:SQL");}
			}
			if(entity == 2) { 
				
				String nome =(String)JOptionPane.showInputDialog(null,"Inserisci il nome dello Sviluppatore:", "Nome Sviluppatore");
				if(nome == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Nome mancante!"); System.exit(1);}
				String cognome = (String)JOptionPane.showInputDialog(null,"Inserisci il cognome dello Sviluppatore:", "Cognome Sviluppatore");
				if(cognome == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Cognome mancante!"); System.exit(1);}
				String nomeProfilo = (String)JOptionPane.showInputDialog(null,"Inserisci il nome Profilo dello Sviluppatore:", "Nome Profilo");
				if(nomeProfilo == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Nome Profilo mancante!"); System.exit(1);}
				String email = (String)JOptionPane.showInputDialog(null,"Inserisci l'email dello Sviluppatore:", "email");
				if(email == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Email mancante!"); System.exit(1);}
				String password = (String)JOptionPane.showInputDialog(null,"Inserisci la password dello Sviluppatore:", "pwd");
				if(password == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Password mancante!"); System.exit(1);}
				String casaDiProduzione = (String)JOptionPane.showInputDialog(null,"Inserisci la casa di Produzione dello Sviluppatore:", "Casa di Produzione");
				if(casaDiProduzione == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Casa di Produzione mancante!"); System.exit(1);}
				
				Sviluppatore sviluppatore = new Sviluppatore(nome,cognome,nomeProfilo,email,password,casaDiProduzione);
				try {
				sviluppatore.InsertIntoSviluppatore();
				JOptionPane.showMessageDialog(null, "Inserimento riuscito con successo");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Inserimento fallito:SQL");}
				
			}
			if(entity == 3) { 
				
				String titolo = (String)JOptionPane.showInputDialog(null,"Inserisci il titolo del Videogioco:", "Titolo Videogioco");
				if(titolo == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Titolo mancante!"); System.exit(1);}
				String genere = (String)JOptionPane.showInputDialog(null,"Inserisci il genere del Videogioco:", "Genere Videogioco");
				if(genere == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Genere mancante!"); System.exit(1);}
				String requisiti = (String)JOptionPane.showInputDialog(null,"Inserisci la lista dei requisiti", "Lista requisiti");
				if(requisiti == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Requisiti mancanti!"); System.exit(1);}
				String sviluppatore = (String)JOptionPane.showInputDialog(null, "Lista", "Lista Sviluppatori", JOptionPane.DEFAULT_OPTION, null, sviluppatori, sviluppatori[0]);
				if(sviluppatore == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Sviluppatore mancante!"); System.exit(1);}
				String dataPubblicazione = (String)JOptionPane.showInputDialog(null,"Inserisci la data di pubblicazione(FORMATO YYYY-MM-DD:", "Data Pubblicazione");
				if(dataPubblicazione == null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: Data mancante!"); System.exit(1);}
				
				Videogioco videogioco = new Videogioco(titolo,genere,requisiti,sviluppatore,dataPubblicazione);
				try {
				videogioco.InsertIntoVideoGioco();
				JOptionPane.showMessageDialog(null, "Inserimento riuscito con successo");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Inserimento fallito:SQL");}
			}
		}
		
		if(s.equals("Cancellare Voce")) {
			
			Object[] voci = {"Cliente", "Recensione", "Sviluppatore", "Videogioco"};
			Object videogiochi[] = Videogioco.PrelevaNomiVideogiochi();
			Object[] clienti = Cliente.PrelevaNomiClienti();
			Object[] sviluppatori = Sviluppatore.PrelevaNomiSviluppatori();
			Object[] recensioni = Recensione.PrelevaNomiRecensioni();
			
			int entity = JOptionPane.showOptionDialog(null, "Cosa vuoi cancellare?", "Cancellamento Voce", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, voci , voci[0]);
			
			if(entity == 0) {
				String cancel = (String)JOptionPane.showInputDialog(null, "Quale cliente vuoi cancellare?", "Cancella Cliente", JOptionPane.DEFAULT_OPTION, null, clienti, clienti[0]);
				if(cancel == null) {JOptionPane.showMessageDialog(null, "Cancellamento non riuscito: Nessuna selezione effettuata!"); System.exit(1);}
				try {
					Cliente cliente = new Cliente(cancel);
					cliente.DeleteFromCliente();
					JOptionPane.showMessageDialog(null, "Cancellamento riuscito con successo!");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Cancellamento fallito:SQL");}
			}
			if(entity == 1) {
				String cancel = (String)JOptionPane.showInputDialog(null, "Quale Recensione vuoi cancellare?", "Cancella Recensione", JOptionPane.DEFAULT_OPTION, null, recensioni, recensioni[0]);
				if(cancel == null) {JOptionPane.showMessageDialog(null, "Cancellamento non riuscito: Nessuna selezione effettuata!"); System.exit(1);}
				try {
					Recensione recensione = new Recensione(cancel);
					recensione.DeleteFromRecensione();
					JOptionPane.showMessageDialog(null, "Cancellamento riuscito con successo!");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Cancellamento fallito:SQL");}
			}
			if(entity == 2) {
				String cancel = (String)JOptionPane.showInputDialog(null, "Quale Sviluppatore vuoi cancellare?", "Cancella Sviluppatore", JOptionPane.DEFAULT_OPTION, null, sviluppatori, sviluppatori[0]);
				if(cancel == null) {JOptionPane.showMessageDialog(null, "Cancellamento non riuscito: Nessuna selezione effettuata!"); System.exit(1);}
				try {
					Sviluppatore sviluppatore = new Sviluppatore(cancel);
					sviluppatore.DeleteFromSviluppatore();
					JOptionPane.showMessageDialog(null, "Cancellamento riuscito con successo!");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Cancellamento fallito:SQL");}
			}
			if(entity == 3) {
				String cancel = (String)JOptionPane.showInputDialog(null, "Quale videogioco vuoi cancellare?", "Cancella Videogioco", JOptionPane.DEFAULT_OPTION, null, videogiochi, videogiochi[0]);
				if(cancel == null) {JOptionPane.showMessageDialog(null, "Cancellamento non riuscito: Nessuna selezione effettuata!"); System.exit(1);}
				try {
					Videogioco videogioco = new Videogioco(cancel);
					videogioco.DeleteFromVideoGioco();
					JOptionPane.showMessageDialog(null, "Cancellamento riuscito con successo!");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Cancellamento fallito:SQL");}
			}
		}
		
		if(s.equals("Modificare Voce")) {
			
			Object[] voci = {"Cliente", "Recensione", "Sviluppatore", "Videogioco"};
			Object videogiochi[] = Videogioco.PrelevaNomiVideogiochi();
			Object[] clienti = Cliente.PrelevaNomiClienti();
			Object[] sviluppatori = Sviluppatore.PrelevaNomiSviluppatori();
			Object[] recensioni = Recensione.PrelevaNomiRecensioni();
			String[] valutazioni = {"1","2","3","4","5","6","7","8","9","10"};
			
			int entity = JOptionPane.showOptionDialog(null, "Cosa vuoi modificare?", "Modifica Voce", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, voci , voci[0]);
			
			if(entity == 0) {
				String update = (String)JOptionPane.showInputDialog(null, "Quale cliente vuoi modificare?", "Modifica Cliente", JOptionPane.DEFAULT_OPTION, null, clienti, clienti[0]);
				if(update == null) {JOptionPane.showMessageDialog(null, "Modifica non riuscita: Nessuna selezione effettuata!"); System.exit(1);}
				Cliente cliente1 = new Cliente(update);
				
				String nome =(String)JOptionPane.showInputDialog(null,"Inserisci il nome del Cliente:", "Nome Cliente");
				if(nome == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Nome mancante!"); System.exit(1);}
				String cognome = (String)JOptionPane.showInputDialog(null,"Inserisci il cognome del Cliente:", "Cognome Cliente");
				if(cognome == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Cognome mancante!"); System.exit(1);}
				String nomeProfilo = (String)JOptionPane.showInputDialog(null,"Inserisci il nome Profilo del Cliente:", "Nome Profilo");
				if(nomeProfilo == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Nome Profilo mancante!"); System.exit(1);}
				String email = (String)JOptionPane.showInputDialog(null,"Inserisci l'email del Cliente:", "email");
				if(email == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Email mancante!"); System.exit(1);}
				String password = (String)JOptionPane.showInputDialog(null,"Inserisci la password del Cliente:", "pwd");
				if(password == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Password mancante!"); System.exit(1);}
				
				Cliente cliente2 = new Cliente(nome,cognome,nomeProfilo,email,password,0);
				try {
					cliente1.UpdateCliente(cliente2);
					JOptionPane.showMessageDialog(null, "Modifica riuscita con successo!");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Modifica non riuscita: SQL");}
				
			}
			if(entity == 1) {
				String update = (String)JOptionPane.showInputDialog(null, "Quale recensione vuoi modificare?", "Modifica Recensione", JOptionPane.DEFAULT_OPTION, null, recensioni, recensioni[0]);
				if(update == null) {JOptionPane.showMessageDialog(null, "Modifica non riuscita: Nessuna selezione effettuata!"); System.exit(1);}
				Recensione recensione1 = new Recensione(update);
				
				String contenuto = (String)JOptionPane.showInputDialog("Inserisci il contenuto della recensione","Contenuto Recensione");
				if(contenuto == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Contenuto mancante!"); System.exit(1);}
				int valutazione = Integer.parseInt((String)JOptionPane.showInputDialog(null, "Valutazione", "Valutazione", JOptionPane.DEFAULT_OPTION, null, valutazioni, valutazioni[0]));
				if(valutazione == -1) { JOptionPane.showMessageDialog(null, "Modifica fallita: Valutazione mancante!"); System.exit(1);}
				String cliente = (String)JOptionPane.showInputDialog(null, "Lista", "Lista Clienti", JOptionPane.DEFAULT_OPTION, null, clienti , clienti[0]);
				if(cliente == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Cliente mancante!"); System.exit(1);}
				String videogioco = (String)JOptionPane.showInputDialog(null, "Lista", "Lista Videogiochi", JOptionPane.DEFAULT_OPTION, null, videogiochi , videogiochi[0]);
				if(videogioco == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Videogioco mancante!"); System.exit(1);}
				String dataPubblicazioneRecensione = JOptionPane.showInputDialog("Inserisci la data della recensione(FORMATO YYYY-MM-DD","Data Recensione");
				if(dataPubblicazioneRecensione == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Data mancante!"); System.exit(1);}
				
				Recensione recensione2 = new Recensione(contenuto,valutazione,cliente,videogioco,dataPubblicazioneRecensione);
				try {
					recensione1.UpdateRecensione(recensione2);
					JOptionPane.showMessageDialog(null, "Modifica riuscita con successo");
					}catch(SQLException e) { e.printStackTrace(); JOptionPane.showMessageDialog(null, "Modifica fallita: SQL");}
			}
			if(entity == 2) {
				String update = (String)JOptionPane.showInputDialog(null, "Quale sviluppatore vuoi modificare?", "Modifica Sviluppatore", JOptionPane.DEFAULT_OPTION, null, sviluppatori, sviluppatori[0]);
				if(update == null) {JOptionPane.showMessageDialog(null, "Modifica non riuscita: Nessuna selezione effettuata!"); System.exit(1);}
				Sviluppatore sviluppatore1 = new Sviluppatore(update);
				
				String nome =(String)JOptionPane.showInputDialog(null,"Inserisci il nome dello Sviluppatore:", "Nome Sviluppatore");
				if(nome == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Nome mancante!"); System.exit(1);}
				String cognome = (String)JOptionPane.showInputDialog(null,"Inserisci il cognome dello Sviluppatore:", "Cognome Sviluppatore");
				if(cognome == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Cognome mancante!"); System.exit(1);}
				String nomeProfilo = (String)JOptionPane.showInputDialog(null,"Inserisci il nome Profilo dello Sviluppatore:", "Nome Profilo");
				if(nomeProfilo == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Nome Profilo mancante!"); System.exit(1);}
				String email = (String)JOptionPane.showInputDialog(null,"Inserisci l'email dello Sviluppatore:", "email");
				if(email == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Email mancante!"); System.exit(1);}
				String password = (String)JOptionPane.showInputDialog(null,"Inserisci la password dello Sviluppatore:", "pwd");
				if(password == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Password mancante!"); System.exit(1);}
				String casaDiProduzione = (String)JOptionPane.showInputDialog(null,"Inserisci la casa di Produzione dello Sviluppatore:", "Casa di Produzione");
				if(casaDiProduzione == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Casa di Produzione mancante!"); System.exit(1);}
				
				Sviluppatore sviluppatore2 = new Sviluppatore(nome,cognome,nomeProfilo,email,password,casaDiProduzione);
				try {
				sviluppatore1.UpdateSviluppatore(sviluppatore2);
				JOptionPane.showMessageDialog(null, "Modifica riuscita con successo!");
				}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Modifica fallita: SQL");}
			}
			if(entity == 3) {
				String update = (String)JOptionPane.showInputDialog(null, "Quale videogioco vuoi modificare?", "Modifica Videogioco", JOptionPane.DEFAULT_OPTION, null, videogiochi, videogiochi[0]);
				if(update == null) {JOptionPane.showMessageDialog(null, "Modifica non riuscita: Nessuna selezione effettuata!"); System.exit(1);}
				Videogioco videogioco1 = new Videogioco(update);
				
				String titolo = (String)JOptionPane.showInputDialog(null,"Inserisci il titolo del Videogioco:", "Titolo Videogioco");
				if(titolo == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Titolo mancante!"); System.exit(1);}
				String genere = (String)JOptionPane.showInputDialog(null,"Inserisci il genere del Videogioco:", "Genere Videogioco");
				if(genere == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Genere mancante!"); System.exit(1);}
				String requisiti = (String)JOptionPane.showInputDialog(null,"Inserisci la lista dei requisiti", "Lista requisiti");
				if(requisiti == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Requisiti mancanti!"); System.exit(1);}
				String sviluppatore = (String)JOptionPane.showInputDialog(null, "Lista", "Lista Sviluppatori", JOptionPane.DEFAULT_OPTION, null, sviluppatori, sviluppatori[0]);
				if(sviluppatore == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Sviluppatore mancante!"); System.exit(1);}
				String dataPubblicazione = (String)JOptionPane.showInputDialog(null,"Inserisci la data di pubblicazione(FORMATO YYYY-MM-DD:", "Data Pubblicazione");
				if(dataPubblicazione == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Data mancante!"); System.exit(1);}
				
				Videogioco videogioco2 = new Videogioco(titolo,genere,requisiti,sviluppatore,dataPubblicazione);
				try {
				videogioco1.UpdateVideogioco(videogioco2);
				JOptionPane.showMessageDialog(null, "Modifica riuscita con successo");
				}catch(SQLException e) { e.printStackTrace(); JOptionPane.showMessageDialog(null, "Modifica fallita:SQL");}
			}
			
		}
		
		if(s.equals("Registrare Acquisto")) {
			
			Object[] clienti = Cliente.PrelevaNomiClienti();
			Object videogiochi[] = Videogioco.PrelevaNomiVideogiochi();
			
			String cliente = (String)JOptionPane.showInputDialog(null, "Lista", "Lista Clienti", JOptionPane.DEFAULT_OPTION, null, clienti , clienti[0]);
			if(cliente == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Cliente mancante!"); System.exit(1);}
			String videogioco = (String)JOptionPane.showInputDialog(null, "Lista", "Lista Videogiochi", JOptionPane.DEFAULT_OPTION, null, videogiochi , videogiochi[0]);
			if(videogioco == null) { JOptionPane.showMessageDialog(null, "Modifica fallita: Videogioco mancante!"); System.exit(1);}
			String dataAcquisto = (String)JOptionPane.showInputDialog("Inserisci data di acquisto: ");
			if(dataAcquisto==null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: data mancante!"); System.exit(1);}
			Videogioco videogioco1 = new Videogioco(videogioco);
			Cliente cliente1 = new Cliente(cliente);
			try{ 
				cliente1.Acquisto(videogioco1, dataAcquisto);
				JOptionPane.showMessageDialog(null,"L'acquisto è stato registrato con successo!");
			
			}catch(SQLException e) { JOptionPane.showMessageDialog(null, "Errore nella registrazione dell'acquisto: SQL");
		}
	}
		
		if(s.equals("Stampa Mensile Dati Acquisti")) {
	
			String[] dati = new String[9000];
			/*String data1 = (String)JOptionPane.showInputDialog("Inserisci la prima data(FORMATO:YYYY-MM-DD):");
			if(data1==null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: data1 mancante!"); System.exit(1);}
			String data2 = (String)JOptionPane.showInputDialog("Inserisci la seconda data(FORMATO:YYYY-MM-DD):");
			if(data2==null) { JOptionPane.showMessageDialog(null, "Inserimento fallito: data2 mancante!"); System.exit(1);}
			*/
			dati = Cliente.StampaMensileClienti();
			JOptionPane.showInputDialog(null, "Stampa mensile dei dati relativi agli acquisti:", "Dati", JOptionPane.DEFAULT_OPTION, null, dati, dati[0]);
			System.exit(1);
		} 
	
		if(s.equals("Stampa Mensile Dati Videogiochi")) {
			
			String[] dati = new String[500];
			
			dati = Videogioco.StampaMensileVideogiochi();
			
			JOptionPane.showInputDialog(null, "Stampa mensile dei dati relativi ai videogiochi:", "Dati", JOptionPane.DEFAULT_OPTION, null, dati, dati[0]);
			System.exit(1);
		}
		
		if(s.equals("Stampa Migliori Sviluppatori")) {
			
			String[] dati = new String[201];
			
			dati = Sviluppatore.StampaMiglioriSviluppatori();
			JOptionPane.showInputDialog(null, "Stampa mensile dei dati relativi agli sviluppatori migliori:", "Dati", JOptionPane.DEFAULT_OPTION, null, dati, dati[0]);
			System.exit(1);
		}
	}		
}

	
	

