package com.sapient.DbsEngine;

import com.sapient.DbsEngine.Goal;
import com.sapient.DbsEngine.Goal3;

import java.util.ArrayList;


public class App 
{
	
    public static void main( String[] args )
    {
    		 Goal g1=new Goal();
    		 System.out.println("Enter the query:");
    		 String  query="select venue , umire1 from ipl.csv where id = 20 and season=2008 group by team1";
    		 ArrayList<String> token=g1.token1(query);		 
    		 //task1
    		 g1.fname(query);
    		 //task2  
    		 g1.basefilter(query);
    		 //task 3
    		 String lastResult=g1.endfilter(query);
    		 //task4
    		 String conditionResult=g1.conditions(lastResult);
    		 //task5
    		 g1.operator(token);
    		 //task6
    		 ArrayList<String> selectResult=g1.selectInfo(query);
    		 //task7
    		 g1.order(query);	
    		 //task 8
    		 g1.group(query);
    		 //task 9
    		 g1.aggregate(query);
    		 // goal 3
    		 Goal3 obj3=new Goal3();
    		 obj3.goalHead();
    		 //goal 5
    		 g1.goal5(selectResult,conditionResult);

    	
    }
}
