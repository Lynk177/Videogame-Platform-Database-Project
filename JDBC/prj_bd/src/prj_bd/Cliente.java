package prj_bd;
import java.sql.*;
import java.io.*;
import java.util.UUID;

public class Cliente implements Serializable{
	
	private String idCliente,nome,cognome,nomeProfilo,email,password;
	private int numeroAcquisti;
	private static Connection con = ConnectionManager.getConnection();;
	
	
	public Cliente(String id) {
		this.idCliente=id;
	}
	
	public Cliente(String nome,String cognome,String nomeProfilo,String email,String password,int numeroAcquisti)throws SQLException {
		
		this.nome=nome;
		this.cognome=cognome;
		this.nomeProfilo=nomeProfilo;
		this.email=email;
		this.password=password;
		this.idCliente=UUID.randomUUID().toString();
		this.numeroAcquisti=numeroAcquisti;
		
	}
	
	public void Acquisto(Videogioco videogioco,String dataAcquisto)throws SQLException{
		
		PreparedStatement pstmInsert = con.prepareStatement("INSERT INTO acquisto VALUES(?,?,?)");
		pstmInsert.setString(1, this.getIdCliente());
		pstmInsert.setString(2, videogioco.getIdVideogioco());
		pstmInsert.setString(3,dataAcquisto);
		pstmInsert.executeUpdate(); 
		PreparedStatement pstmUpdate = con.prepareStatement("UPDATE cliente SET numeroAcquisti = numeroAcquisti+1 WHERE idCliente = ?");
		pstmUpdate.setString(1, this.getIdCliente());
		pstmUpdate.executeUpdate();
		if(!pstmInsert.isClosed()) pstmInsert.close();
		if(!pstmUpdate.isClosed()) pstmUpdate.close();
		this.setNumeroAcquisti(++this.numeroAcquisti);
	
	}
	
	public void InsertIntoCliente() throws SQLException{
		
		String InsertSQL = "INSERT INTO cliente VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstm = con.prepareStatement(InsertSQL);
		pstm.setString(1,this.getIdCliente());
		pstm.setString(2,this.getNome());
		pstm.setString(3,this.getCognome());
		pstm.setString(4,this.getNomeProfilo());
		pstm.setString(5,this.getEmail());
		pstm.setString(6,this.getPassword());
		pstm.setInt(7,this.getNumeroAcquisti());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
		
	}
	
	public void DeleteFromCliente() throws SQLException {
		
		String DeleteSQL = "DELETE FROM cliente where idCliente = ?";
		PreparedStatement pstm = con.prepareStatement(DeleteSQL);
		pstm.setString(1, this.getIdCliente());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
	}
	
	public void UpdateCliente(Cliente cliente) throws SQLException {
		
		String UpdateSQL = "UPDATE cliente SET Nome = ?, Cognome = ?, nomeProfilo = ?, email = ?, pwd = ? where idCliente = ?";
		PreparedStatement pstm = con.prepareStatement(UpdateSQL);
		pstm.setString(1, cliente.getNome());
		pstm.setString(2, cliente.getCognome());
		pstm.setString(3, cliente.getNomeProfilo());
		pstm.setString(4, cliente.getEmail());
		pstm.setString(5, cliente.getPassword());
		pstm.setString(6, this.getIdCliente());
		pstm.executeUpdate();
		if(!pstm.isClosed()) pstm.close();
				
	}
	
	public void PubblicaRecensione(Recensione recensione)throws SQLException{
		
		recensione.InsertIntoRecensione();
	}
	
	public void ModificaRecensione(Recensione recensione,Recensione nuovaRecensione)throws SQLException{
		
		recensione.UpdateRecensione(nuovaRecensione);
	}
	
	public static String[] StampaMensileClienti() throws SQLException{
		 
		String SelectSQL = "SELECT * from acquisto";
		PreparedStatement pstm = con.prepareStatement(SelectSQL);
		ResultSet rset = pstm.executeQuery(SelectSQL);
		int i=0;
		String[] dati = new String[9000];
		while(rset.next()) {
			
			i++;
			String cliente = rset.getString("idCliente");
			String videogioco = rset.getString("idVideogioco");
			String data = rset.getString("dataAcquisto");
			String summary = cliente + " " + videogioco + " " + data + " ";
			dati[i] = summary;
		}
		
		return dati;
	}
	
	public static String[] PrelevaNomiClienti() throws SQLException {
		
		String[] nomi = new String[1802];
		int i=0;
		String SelectSQL = "SELECT idCliente from cliente";
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery(SelectSQL);
		while(rset.next()) {
			
			nomi[i]=rset.getString("idCliente");
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

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public int getNumeroAcquisti() {
		return numeroAcquisti;
	}

	public void setNumeroAcquisti(int numeroAcquisti) {
		this.numeroAcquisti = numeroAcquisti;
	}
}
