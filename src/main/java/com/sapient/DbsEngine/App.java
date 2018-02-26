package com.sapient.DbsEngine;

import com.sapient.DbsEngine.Goal;
import com.sapient.DbsEngine.Goal3;

import java.util.ArrayList;


public class App 
{
	
    public static void main( String[] args )
    {
    		 // Making object of class Goal
    		 Goal g1=new Goal();
    		 System.out.println("Enter the query:");
    		 String  query="select venue , umire1 from ipl.csv where id = 20 and season=2008 group by team1";
    		 ArrayList<String> token=g1.token(query);		 
    		 //Calling function to retrieve the name of file
    		 g1.fname(query);
    		 //Calling function for query before where  
    		 g1.basefilter(query);
    		 //Calling function for query after where and assign it to a String
    		 String lastResult=g1.endfilter(query);
    		 //Calling function for finding condition in query after where 
    		 String conditionResult=g1.conditions(lastResult);
    		 //Calling function for logical operators in query
    		 g1.operator(token);
    		 //Calling function for selected field in query
    		 ArrayList<String> selectResult=g1.selectInfo(query);
    		 //Calling function for order by field
    		 g1.order(query);	
    		 //Calling function for group by field
    		 g1.group(query);
    		 //Calling function for aggregate function in query
    		 g1.aggregate(query);
    		 //Calling function to retrieve the data of selected field in query
    		 g1.goal5(selectResult,conditionResult);
    		 // Making the object of class Goal3
    		 Goal3 obj3=new Goal3();
    		 // Calling function for finding data type of each field in query
    		 obj3.goalHead();
    	
    		 

    	
    }
}
