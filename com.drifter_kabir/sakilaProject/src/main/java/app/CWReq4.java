package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CWReq4 {

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
			int cr[]=new int[myMap.size()+1000];
			int i=0;
			for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
				int x=entry.getKey();
				int y=entry.getValue();
				cr[x]=y;
				arr[i++] = new Pair(x,y );
//			    System.out.println(x+" : "+y);
			}
//			for(int i1=0;i1<myMap.size();i1++) {
//				System.out.println(arr[i1].x+" "+arr[i1].y);
//			}
			Compare obj = new Compare();
			obj.compare(arr, myMap.size());
			for(int i1=myMap.size()-1;i1>=0;i1--) {
//				System.out.println(arr[i1].x+" "+arr[i1].y);
			}

			query="select * from film_category";
			result=st.executeQuery(query);
			Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

//			int cnt=0;
			while(result.next()){
//				cnt++;
				Integer key = result.getObject(2,Integer.class);
				int number = result.getObject(1,Integer.class);
				if (map.get(key) == null) {
				    map.put(key, new ArrayList<Integer>());
				}
				map.get(key).add(number);
			}

			int cate[]=new int[16+1];
			for (Entry<Integer, List<Integer>> ee : map.entrySet()) {
//				System.out.println(ee);
			    Integer key = ee.getKey();
			    cate[key]=0;
			    List<Integer> values = ee.getValue();
			    int ck=0,mx=-1;
			    for(Integer num: values) {
			    	int xx=cr[num];
			    	if(mx<xx) {
			    		mx=xx;
			    		cate[key]=num;
			    	}
			    }

			}

			query="select * from film_text";
			result=st.executeQuery(query);
			String ar[]=new String[2222];
			while(result.next()){
				String ss=result.getString(2);
				int id=result.getObject(1,Integer.class);
				ar[id]=ss;
			}






			for(int i1=1;i1<=16;i1++) {
				System.out.println("Category: "+i1+" -> id -> "+cate[i1]+" -> "+ar[cate[i1]]+" -> "+cr[cate[i1]]);
			}









		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
