package com.sapient.DbsEngine;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
public class Goal {
	String query;
	String first=null;
	String last=null;
	static int index1;
	static String operate=null;
	static String selected;
	ArrayList<String> con=new ArrayList<String>();
    ArrayList<String> token=new ArrayList<String>();
public ArrayList<String> token1(String query) {
	 String words=null;
	 //this.query=query;
	 StringTokenizer st = new StringTokenizer(query," ");  
	 while (st.hasMoreTokens()) {
		 words=st.nextToken();
		 token.add(words);
         System.out.println(words);			     
	     } 
	 return token;
 }
public String fname(String query){
	//this.query=query;
	String code="";
    System.out.print("File Name: ");
	Pattern p = Pattern.compile("[\\w]+\\.(csv)");
	Matcher m = p.matcher(query);  
	while(m.find()) 
	code=m.group();
	System.out.println(code);
	return code;
}
public String basefilter(String query) {
	//this.query=query;
	index1=query.indexOf("where");
	System.out.print("Query before where: ");
	first=query.substring(0, index1);
	System.out.println(first);
	return first;
}
public String endfilter(String query) {
	//this.query=query;
	System.out.print("Query after where: ");
	int index2=index1+("where ".length());
	last=query.substring(index2, query.length());
	System.out.println(last);
	return last;
}
public String conditions(String last) {
	//this.last=last;
	String condition="";
	System.out.println("Conditions: ");
	Pattern p2= Pattern.compile("([\\w]+[ ]?)((<=)|(>=)|(<>)|=|<|>)([ ]?[']?[\\w]+[']?)");
	Matcher m2= p2.matcher(last);
	while(m2.find())
		condition=m2.group();
	    System.out.println(condition);		 
		return condition;
}
public String operator(ArrayList<String> token) {	 
		 for(String operatorCollection:token) { 
			 if((operatorCollection.equalsIgnoreCase("and"))||(operatorCollection.equalsIgnoreCase("or"))||(operatorCollection.equalsIgnoreCase("not")))
			 System.out.println("Logical operators in statement: " +operatorCollection);  
		 }  		
		return "error";
}
public ArrayList<String> selectInfo(String query) {	
		System.out.println("selected fields/information from the given query");
		int index3=token.indexOf("from");		
		con.addAll(token.subList(1, index3));
		Iterator<String> iterator= con.iterator();
		while(iterator.hasNext()) {
			String itrstr= iterator.next();
			selected=itrstr.replace(","," ");
			System.out.println(selected);
		}		
		return con;
}
public String order(String query) {
	//this.query=query;
	String afterOrderBy=null;
		if(query.contains("order")) {			
		    int index4=query.indexOf("order by")+("order by ".length());
		    afterOrderBy=query.substring(index4,query.length());
		    System.out.println("After order by:"+afterOrderBy);
		}
    	else
			System.out.println("doesn't contain any order by clause");
		 return afterOrderBy;
}	
public String group(String query) {
	//this.query=query;
	String afterGroupBy = null;
	if(query.contains("group")) {
		int index5=query.indexOf("group by")+("group by ".length());
		afterGroupBy=query.substring(index5,query.length());
		System.out.println("After group by:"+afterGroupBy);
		}
		else 
			System.out.println("doesn't contain any group by clause");
		    return afterGroupBy;
}
public String aggregate(String query) {
		System.out.println("aggregate functions");
		Pattern p = Pattern.compile("[a-zA-Z]+[(][\\w]+[)]");
		Matcher m = p.matcher(query);  
		while(m.find())			
	        System.out.println(m.group());	
		 return "in function aggregate";
}
public String goal5(ArrayList<String> selectResult,String conditionResult) {
	    String csvFile = "ipl.csv";
        BufferedReader br = null;
        String csvSplit = ",";
        String line = "";
        String heading=null;
       // String word=null; 
        String[] data=null;
        ArrayList<Integer> arrayMatch=new ArrayList<Integer>();
        String strArray[] = new String[18];
		  try {
			   br = new BufferedReader(new FileReader(csvFile));
	            heading=br.readLine();
	    		strArray= heading.split(csvSplit);
	    		List<String> headingList=Arrays.asList(strArray);
	    		ArrayList<String> headingArray = new ArrayList<String>(headingList);  	    
	    		for (int i = 0; i < selectResult.size(); i++) {	    	
	    			  for (int j =0; j < headingArray.size(); j++) {	    				  
	    			    if(selectResult.get(i).equals(headingArray.get(j))) {	
	    			    	arrayMatch.add(j);	    			   
	    			    }
	    			  }
	    		}	    	    			
	    		    for(int k=0;k<arrayMatch.size();k++)
	    		{	 
	    			    while ((line = br.readLine()) != null) {
	   		                 data= line.split(csvSplit);		   		               
	   		                 System.out.print(data[arrayMatch.get(k)]+" ");	   		                
	    			    }
	    			    System.out.println();
	    			    br = new BufferedReader(new FileReader(csvFile));
	    	            br.readLine();
	    			    }	    		   
		  }
	        catch(Exception e) {
	        	System.out.println(e);
	        }
	        finally
	        {
		          if (br != null)
		          {
		                try{ 
		                	br.close();} 
		                catch (IOException e){
		                	e.printStackTrace();}
		           }
		     }
		  return "in goal5";
	        }
}
