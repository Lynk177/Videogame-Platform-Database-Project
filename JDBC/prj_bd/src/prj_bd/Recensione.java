package prj_bd;
import java.sql.*;
import java.util.UUID;
import java.io.Serializable;

public class Recensione implements Serializable{

	private String idCliente,idRecensione,contenuto, DataPubblicazioneRecensione,videogiocoDescritto;
	private int valutazione;
	private static Connection con = ConnectionManager.getConnection();;
	
	public Recensione(String id) {
		
		this.idRecensione=id;
	}
	
	public Recensione (String contenuto, int valutazione,String cliente, String videogiocoDescritto, String DataPubblicazioneRecensione) {
		
		this.idRecensione=UUID.randomUUID().toString();
		this.contenuto=contenuto;
		this.valutazione=valutazione;
		this.DataPubblicazioneRecensione=DataPubblicazioneRecensione;
		this.idCliente=cliente;
		this.videogiocoDescritto=videogiocoDescritto;
	
	}
	
	public int InsertIntoRecensione()throws SQLException {
	
		String InsertSQL = "INSERT INTO recensione VALUES(?,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(InsertSQL);
		pstm.setString(1,this.getIdRecensione());
		pstm.setString(2,this.getContenuto());
		pstm.setInt(3,this.getValutazione()); 
		pstm.setString(4, this.idCliente);
		pstm.setString(5, this.videogiocoDescritto);
		pstm.setString(6,this.getDataPubblicazioneRecensione());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
		return 1;
	}
	
	public void DeleteFromRecensione() throws SQLException{ 
		
		String DeleteSQL ="DELETE FROM recensione where idRecensione = ?";
		PreparedStatement pstm = con.prepareStatement(DeleteSQL);
		pstm.setString(1, this.getIdRecensione());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();	}
	
	public void UpdateRecensione(Recensione recensione)throws SQLException {

		String UpdateSQL = "UPDATE recensione SET contenuto = ?, valutazione = ?, DataPubblicazioneRecensione = ?, where idRecensione = ?";
		PreparedStatement pstm = con.prepareStatement(UpdateSQL);
		pstm.setString(1, recensione.getContenuto());
		pstm.setInt(2, recensione.getValutazione());
		pstm.setString(3, recensione.getDataPubblicazioneRecensione());
		pstm.setString(4, this.getIdRecensione());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
		
	}
	public static String[] PrelevaNomiRecensioni() throws SQLException {
		
		String[] nomi = new String[4501];
		int i=0;
		String SelectSQL = "SELECT idRecensione from recensione";
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery(SelectSQL);
		while(rset.next()) {
			
			nomi[i]=rset.getString("idRecensione");
			i++;
		}
		return nomi;
	}
	
	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public String getDataPubblicazioneRecensione() {
		return DataPubblicazioneRecensione;
	}

	public void setDataPubblicazioneRecensione(String dataPubblicazioneRecensione) {
		DataPubblicazioneRecensione = dataPubblicazioneRecensione;
	}

	public String getIdRecensione() {
		return idRecensione;
	}

	public void setIdRecensione(String idRecensione) {
		this.idRecensione = idRecensione;
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		Recensione.con = con;
	}

}
