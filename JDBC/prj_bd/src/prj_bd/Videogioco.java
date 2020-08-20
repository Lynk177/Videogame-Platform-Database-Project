package prj_bd;
//import Model.ConnectionManager;
import java.sql.*;
import java.util.UUID;
import java.io.*;

public class Videogioco implements Serializable{

	private String idVideogioco,titolo, genere, requisiti, sviluppatore, dataPubblicazione;
	private static Connection con = ConnectionManager.getConnection();;
	
	public Videogioco(String id) {
		
		this.idVideogioco=id;
	}
	
	public Videogioco(String titolo,String genere,String requisiti,String sviluppatore,String dataPubblicazione) {
		
		this.idVideogioco=UUID.randomUUID().toString();
		this.titolo=titolo;
		this.genere=genere;
		this.requisiti=requisiti;
		this.sviluppatore=sviluppatore;
		this.dataPubblicazione=dataPubblicazione;
		
	}
	
	public void InsertIntoVideoGioco() throws SQLException{
		
		String InsertSQL = "INSERT INTO videogioco VALUES(?,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(InsertSQL);
		pstm.setString(1,this.getIdVideogioco());
		pstm.setString(2,this.getTitolo());
		pstm.setString(3,this.getGenere());
		pstm.setString(4,this.getRequisiti());
		pstm.setString(5,this.getSviluppatore());
		pstm.setString(6,this.getDataPubblicazione());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
		
	}
	
	public void DeleteFromVideoGioco() throws SQLException{
		
		String DeleteSQL ="DELETE FROM videogioco where idVideogioco = ?";
		PreparedStatement pstm = con.prepareStatement(DeleteSQL);
		pstm.setString(1, this.getIdVideogioco());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
	}
	
	public void UpdateVideogioco(Videogioco videogioco) throws SQLException {
		
		String UpdateSQL = "UPDATE videogioco SET Titolo = ?, Genere = ?, Requisiti = ?, Sviluppatore = ?, dataPubblicazione = ? where idVideogioco = ?";
		PreparedStatement pstm = con.prepareStatement(UpdateSQL);
		pstm.setString(1, videogioco.getTitolo());
		pstm.setString(2, videogioco.getGenere());
		pstm.setString(3, videogioco.getRequisiti());
		pstm.setString(4, videogioco.getSviluppatore());
		pstm.setString(5, videogioco.getDataPubblicazione());
		pstm.setString(6, this.getIdVideogioco());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
	}
	
	public static String[] PrelevaNomiVideogiochi() throws SQLException {
		
		String[] nomi = new String[500];
		int i=0;
		String SelectSQL = "SELECT idVideogioco from videogioco";
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery(SelectSQL);
		while(rset.next()) {
			
			nomi[i]=rset.getString("idVideogioco");
			i++;
		}
		return nomi;
	}
	
	public static String[] StampaMensileVideogiochi() throws SQLException{
		
		String SelectSQL = "SELECT * from videogioco";
		PreparedStatement pstm = con.prepareStatement(SelectSQL);
		ResultSet rset = pstm.executeQuery(SelectSQL);
		int i=0;
		String[] dati = new String[9000];
		while(rset.next()) {
			
			i++;
			String titolo = rset.getString("titolo");
			String genere = rset.getString("genere");
			String requisiti = rset.getString("requisiti");
			String sviluppatore = rset.getString("sviluppatore");
			String data = rset.getString("dataPubblicazione");
			String summary = titolo + " " + genere + " " + requisiti + " " + sviluppatore + " " + data;
			dati[i] = summary;
		}
		
		return dati;
	}
	
	
	public String getIdVideogioco() {
		return idVideogioco;
	}
	public void setIdVideogioco(String idVideogioco) {
		this.idVideogioco = idVideogioco;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getRequisiti() {
		return requisiti;
	}
	public void setRequisiti(String requisiti) {
		this.requisiti = requisiti;
	}
	public String getSviluppatore() {
		return sviluppatore;
	}
	public void setSviluppatore(String sviluppatore) {
		this.sviluppatore = sviluppatore;
	}
	public String getDataPubblicazione() {
		return dataPubblicazione;
	}
	public void setDataPubblicazione(String dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
}
