package prj_bd;
import java.sql.*;
import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;

import java.sql.PreparedStatement;

public class Sviluppatore implements Serializable {
	
	private String idSviluppatore,nome,cognome,nomeProfilo,email,password,casaDiProduzione;
	private static Connection con = ConnectionManager.getConnection();
	
	public Sviluppatore(String id) {
		
		this.idSviluppatore=id;
	}
	
	public Sviluppatore(String nome,String cognome,String nomeProfilo,String email,String password,String casaDiProduzione) {
		this.nome=nome;
		this.cognome=cognome;
		this.nomeProfilo=nomeProfilo;
		this.email=email;
		this.password=password;
		this.idSviluppatore=UUID.randomUUID().toString();
		this.casaDiProduzione=casaDiProduzione;
		// con = ConnectionManager.getConnection();
	}
	
	public void InsertIntoSviluppatore()throws SQLException {
		
		String InsertSQL = "INSERT INTO sviluppatore VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(InsertSQL);
		pstm.setString(1,this.getIdSviluppatore());
		pstm.setString(2,this.getNome());
		pstm.setString(3,this.getCognome());
		pstm.setString(4,this.getNomeProfilo());
		pstm.setString(5,this.getEmail());
		pstm.setString(6,this.getPassword());
		pstm.setString(7,this.getCasaDiProduzione());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
	}
	
	public void DeleteFromSviluppatore() throws SQLException{
		
		String DeleteSQL ="DELETE FROM sviluppatore where idSviluppatore = ?";
		PreparedStatement pstm = con.prepareStatement(DeleteSQL);
		pstm.setString(1, this.getIdSviluppatore());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
		
	}
	
	public void UpdateSviluppatore(Sviluppatore sviluppatore) throws SQLException {
		
		String UpdateSQL = "UPDATE sviluppatore SET Nome = ?, Cognome = ?, nomeProfilo = ?, email = ?, pwd = ?, casaDiProduzione = ? where idSviluppatore = ?";
		PreparedStatement pstm = con.prepareStatement(UpdateSQL);
		pstm.setString(1, sviluppatore.getNome());
		pstm.setString(2, sviluppatore.getCognome());
		pstm.setString(3, sviluppatore.getNomeProfilo());
		pstm.setString(4, sviluppatore.getEmail());
		pstm.setString(5, sviluppatore.getPassword());
		pstm.setString(6, sviluppatore.getCasaDiProduzione());
		pstm.setString(7, this.getIdSviluppatore());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
	}
	
	
	public static String[] PrelevaNomiSviluppatori() throws SQLException {
		
		String[] nomi = new String[250];
		int i=0;
		String SelectSQL = "SELECT idSviluppatore, nome from sviluppatore";
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery(SelectSQL);
		while(rset.next()) {
			
			nomi[i]=rset.getString("idSviluppatore");
			i++;
		}
		return nomi;
	}
	
	public static String[] StampaMiglioriSviluppatori()throws SQLException {
		
		String[] nomi = new String[201];
		int i=0;
		String SelectSQL = " SELECT distinct idSviluppatore, Nome, media\r\n" + 
				"from sviluppatore join (\r\n" + 
				"SELECT videogiocoDescritto,AVG(valutazione) as media\r\n" + 
				"FROM recensione r1\r\n" + 
				"group by(videogiocoDescritto)\r\n" + 
				"having avg(valutazione)>8) temp";
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery(SelectSQL);
		while(rset.next()){
		
			String id = rset.getString("idSviluppatore");
			String nome = rset.getString("nome");
			String media = rset.getString("media");
			nomi[i] = id + " - " + nome + " - " + media;
			i++;
			
		}
		return nomi;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNomeProfilo() {
		return nomeProfilo;
	}

	public void setNomeProfilo(String nomeProfilo) {
		this.nomeProfilo = nomeProfilo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCasaDiProduzione() {
		return casaDiProduzione;
	}

	public void setCasaDiProduzione(String casaDiProduzione) {
		this.casaDiProduzione = casaDiProduzione;
	}

	public String getIdSviluppatore() {
		return idSviluppatore;
	}

	public void setIdSviluppatore(String idSviluppatore) {
		this.idSviluppatore = idSviluppatore;
	}
		
}
