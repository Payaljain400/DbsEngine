package com.sapient.DbsEngine;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
public class Goal {
	String first=null;
	String last=null;
	static int whereIndex;
	static String operate=null;
	static String selected;
	ArrayList<String> con=new ArrayList<String>();
    ArrayList<String> token=new ArrayList<String>();
    //Function to split the Query    
    public ArrayList<String> token(String query) {
    	String words=null;
    	StringTokenizer st = new StringTokenizer(query," ");  
    	while (st.hasMoreTokens()) {
    		words=st.nextToken();
    		token.add(words);
    		System.out.println(words);			     
    	} 
    	return token;
    }
    //Function to retrieve the name of the file
    public String fname(String query){
    	String code="";
    	System.out.print("File Name: ");
    	Pattern p = Pattern.compile("[\\w]+\\.(csv)");
    	Matcher m = p.matcher(query);  
    	while(m.find()) 
    		code=m.group();
    	System.out.println(code);
    	return code;
    }
    // Function for query before Where
    public String basefilter(String query) {
    	whereIndex=query.indexOf("where");
    	System.out.print("Query before where: ");
    	first=query.substring(0, whereIndex);
    	System.out.println(first);
    	return first;
    }
    // Function for query after Where
    public String endfilter(String query) {
    	System.out.print("Query after where: ");
    	int index2=whereIndex+("where ".length());
    	last=query.substring(index2, query.length());
    	System.out.println(last);
    	return last;
    }
    // Function for conditions in query
    public String conditions(String last) {
    	String condition="";
    	System.out.println("Conditions: ");
    	Pattern p2= Pattern.compile("([\\w]+[ ]?)((<=)|(>=)|(<>)|=|<|>)([ ]?[']?[\\w]+[']?)");
    	Matcher m2= p2.matcher(last);
    	while(m2.find())
    		condition=m2.group();
	    System.out.println(condition);		 
	    return condition;
    }
    // Function for the logical operator in query
    public String operator(ArrayList<String> token) {	 
		 for(String operatorCollection:token) { 
			 if((operatorCollection.equalsIgnoreCase("and"))||(operatorCollection.equalsIgnoreCase("or"))||(operatorCollection.equalsIgnoreCase("not")))
			 System.out.println("Logical operators in statement: " +operatorCollection);  
		 }  		
		return "not found";
    }
    // Function for selected field in query
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
    // Function for order by field
    public String order(String query) {
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
    // Function for group by field
    public String group(String query) {
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
    // Function for aggregate function in the query 
    public String aggregate(String query) {
		System.out.println("aggregate functions");
		Pattern p = Pattern.compile("[a-zA-Z]+[(][\\w]+[)]");
		Matcher m = p.matcher(query);  
		while(m.find())			
	        System.out.println(m.group());	
		return "aggregate function not found";
    }
    // Function to retrieve the data of selected fields
    public String goal5(ArrayList<String> selectResult,String conditionResult) {
	    String csvFile = "ipl.csv";
        BufferedReader br = null;
        String csvSplit = ",";
        String line = "";
        String heading=null;
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
