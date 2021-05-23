package app;
import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;
public class CWReq2 {

	void fun(){
		String url="jdbc:mysql://localhost:3306/Sakila";
		String uname="";
		String pass="";
		String query="select * from actor";

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
				String ans=result.getString(2);
				String ck="PENELOPE";
//				System.out.println(ans);
				if(ans.equals(ck)) {
					data.add(result.getString(2)+" "+result.getString(3));
//					System.out.println(ans);
				}

			}
			System.out.println("The actors with first name PENELOPE : ");
			System.out.println(data);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}


	}





}
