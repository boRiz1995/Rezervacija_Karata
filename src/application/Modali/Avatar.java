package application.Modali;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Avatar {
	
	private String Email;
	private String Password;
	private String Pol;
	private String Ime;
	private String Prezime;
	private String Telefon;
	private Date DatumRodjenja;
	
	public Avatar() {}

	public Avatar(String email, String password, String pol, String ime, String prezime, String telefon,
			Date datumRodjenja) {
		this.Email = email;
		this.Password = password;
		this.Pol = pol;
		this.Ime = ime;
		this.Prezime = prezime;
		this.Telefon = telefon;
		this.DatumRodjenja = datumRodjenja;
	}
	//SETTERS
	public void setEmail(String email) {
		Email = email;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public void setPol(String pol) {
		Pol = pol;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public void setPrezime(String prezime) {
		Prezime = prezime;
	}

	public void setTelefon(String telefon) {
		Telefon = telefon;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		DatumRodjenja = datumRodjenja;
	}
	//GETTERS
	public String getEmail() {
		return Email;
	}

	public String getPassword() {
		return Password;
	}

	public String getPol() {
		return Pol;
	}

	public String getIme() {
		return Ime;
	}

	public String getPrezime() {
		return Prezime;
	}

	public String getTelefon() {
		return Telefon;
	}

	public Date getDatumRodjenja() {
		return DatumRodjenja;
	}
	
	public boolean CheckAvatarDB() {
		try {
            // Replace the databaseUrl, username, and password with your database connection details
			 String url = "jdbc:mysql://localhost:3306/db";
			    String username = "root";
			    String dbPassword = "jbbov123456";

            // Create a connection to the database
            Connection connection = DriverManager.getConnection(url, username, dbPassword);

            // Prepare the SQL statement
            String sql = "SELECT COUNT(*) FROM avatar WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.Email);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();

            // Get the count of matching rows
            resultSet.next();
            int count = resultSet.getInt(1);

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();

            // Return true if the count is greater than 0, indicating the avatar already exists
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return false by default (assume avatar doesn't exist)
        return false;
	}
	
	
}
