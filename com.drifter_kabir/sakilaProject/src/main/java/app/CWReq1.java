package app;
import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;
public class CWReq1 {

	static  void fun() {
		String url="jdbc:mysql://localhost:3306/Sakila";
		String uname="";
		String pass="";
		String query="select * from film_actor";

//		System.out.println("Hello");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection(url,uname,pass);
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
			Set<String> data = new LinkedHashSet<String>();
			while(result.next()){
				data.add(result.getString(1));

			}
			System.out.println("Total Number of Actor : "+data.size());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}


}
