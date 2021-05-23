package app;
import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
class Pair {
    int x;
    int y;

    // Constructor
public Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

class Compare {

	static void compare(Pair arr[], int n)
    {
        // Comparator to sort the pair according to second element
        Arrays.sort(arr, new Comparator<Pair>() {
            @Override public int compare(Pair p1, Pair p2)
            {
                return p1.y - p2.y;
            }
        });


    }
}

public class CWReq3 {

	void fun() {
		String url="jdbc:mysql://localhost:3306/Sakila";
		String uname="";
		String pass="";
		String query="select * from inventory";

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
//			Set<Integer> data = new LinkedHashSet<Integer>();

			Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();// pair ( ID , COUNT )

			while(result.next()){
				Integer ans=result.getObject(2,Integer.class);
				Integer count = myMap.get(ans);
				if (count == null) {
				    myMap.put(ans, 1);
				}
				else {
				    myMap.put(ans, count + 1);
				}
//				data.add(ans);
//				String ck="PENELOPE";
//				System.out.println(ans);


			}
			Pair arr[] = new Pair[myMap.size()];
			int i=0;
			for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
				int x=entry.getKey();
				int y=entry.getValue();
				arr[i++] = new Pair(x,y );
//			    System.out.println(x+" : "+y);
			}
//			for(int i1=0;i1<myMap.size();i1++) {
//				System.out.println(arr[i1].x+" "+arr[i1].y);
//			}
			Compare obj = new Compare();
			obj.compare(arr, myMap.size());
			Pair topTen[] = new Pair[myMap.size()];
			for(int i1=myMap.size()-1,j=0;j<10;i1--,j++) {
				topTen[j]=arr[i1];
			}
			System.out.println("Top 10 most popular film IDs with based on number of rentals");
			System.out.println();
			for(int i1=0;i1<10;i1++) {
				System.out.print(topTen[i1].x+"\t"+topTen[i1].y);
				System.out.println();
			}
			query="select * from film";
			result=st.executeQuery(query);
			Map<Integer, String> newMap = new HashMap< Integer,String>();

			while(result.next()){
				Integer xx=result.getObject(1,Integer.class);
				String str=result.getString(2);
				newMap.put(xx, str);



			}
			System.out.println();
			System.out.println();
			System.out.println("Top 10 most popular film based on number of rentals");
			System.out.println();
			for(int i1=0;i1<10;i1++) {
				for (Map.Entry<Integer, String> entry : newMap.entrySet()) {
					int xx=entry.getKey();
					if(xx==topTen[i1].x) {
						System.out.print(topTen[i1].x+"\t"+topTen[i1].y+"\t"+entry.getValue());
						System.out.println();
					}
				}
			}





		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
