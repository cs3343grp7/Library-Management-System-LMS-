package library;

import java.io.*;
import java.util.Scanner;

public class Day implements Cloneable{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
	
	//Constructor
	public Day(int y, int m, int d){
			this.year=y;
			this.month=m;
			this.day=d;
	}
	//Constructor
	public Day(String sDay) throws ExDayNotValid
	{
		set(sDay);
	}
	//Clone Method
	@Override
	public Day clone()
	{
		Day copy=null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
	
	
	public void set(String sDay) throws ExDayNotValid
	{
		int y,m,d;
		
		String[] sDayParts = sDay.split("-");
		y = Integer.parseInt(sDayParts[2]);
		d = Integer.parseInt(sDayParts[0]);
		if(MonthNames.indexOf(sDayParts[1])== -1)
			throw new ExDayNotValid();
		m = MonthNames.indexOf(sDayParts[1])/3+1;
		
		if (valid(y,m,d))
		{
			this.year = y;
			this.month = m;
			this.day = d;
		}
		else throw new ExDayNotValid();
	}
	
	// check if a given year is a leap year
	static public boolean isLeapYear(int y)
	{
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	public Day getOnholdDeadLineDate() //3 days
	{
		Day onholdDeadLineDate = null;
		
		switch(this.month)
		{
		case 1: case 3: case 5: case 7:
		case 8: case 10:
		{
			if (this.day>28)
				onholdDeadLineDate = new Day(this.year,this.month+1,this.day+ 3 - 31);
			else onholdDeadLineDate = new Day(this.year,this.month,this.day+ 3);
			break;
		}
		case 4: case 6: case 9: case 11:
		{
			if (this.day>27)
				onholdDeadLineDate = new Day(this.year,this.month+1,this.day+ 3 - 30);
			else onholdDeadLineDate = new Day(this.year,this.month,this.day+ 3);
			break;
		}
		case 2:
		{
			if (isLeapYear(this.year))
			{
				if (this.day>26)
					onholdDeadLineDate = new Day(this.year,this.month+1,this.day+ 3 - 29);
				else onholdDeadLineDate = new Day(this.year,this.month,this.day+ 3);
			}
			 else
				 if (this.day>25)
						onholdDeadLineDate = new Day(this.year,this.month+1,this.day+ 3 - 28);
					else onholdDeadLineDate = new Day(this.year,this.month,this.day+ 3);
			break;
		}
		case 12:
		{
			if (this.day>28)
				onholdDeadLineDate = new Day(this.year+1,1,this.day+ 3 - 31);
			else onholdDeadLineDate = new Day(this.year,this.month,this.day+ 3);
			break;
		}
		
		}
		
		return onholdDeadLineDate;
	}
	
	public Day getLoanDeadLineDate() //7 days
	{
		Day loanDeadLineDate = null;
		
		switch(this.month)
		{
		case 1: case 3: case 5: case 7:
		case 8: case 10:
		{
			if (this.day>24)
				loanDeadLineDate = new Day(this.year,this.month+1,this.day+ 7 - 31);
			else loanDeadLineDate = new Day(this.year,this.month,this.day+ 7);
			break;
		}
		case 4: case 6: case 9: case 11:
		{
			if (this.day>23)
				loanDeadLineDate = new Day(this.year,this.month+1,this.day+ 7 - 30);
			else loanDeadLineDate = new Day(this.year,this.month,this.day+ 7);
			break;
		}
		case 2:
		{
			if (isLeapYear(this.year))
			{
				if (this.day>22)
					loanDeadLineDate = new Day(this.year,this.month+1,this.day+ 7 - 29);
				else loanDeadLineDate = new Day(this.year,this.month,this.day+ 7);
				break;
			}
			 else{
				 if (this.day>21)
						loanDeadLineDate = new Day(this.year,this.month+1,this.day+ 7 - 28);
					else loanDeadLineDate = new Day(this.year,this.month,this.day+ 7);
				break;
			 }
		}
		case 12:
		{
			if (this.day>24)
				loanDeadLineDate = new Day(this.year+1,1,this.day+ 7 - 31);
			else loanDeadLineDate = new Day(this.year,this.month,this.day+ 7);
			break;
		}
		
		}
		
		return loanDeadLineDate;
	}
	
	
	// check if y,m,d valid
	static private boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
			{
					 return d<=31;
			}
			case 4: case 6: case 9: case 11:
			{
					return d<=30;
			}
			case 2:
			{
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28;
			}
		}
		return false;
	}

	public boolean datePassed(Day another)
	{
		if(this.year < another.year)
		{
			return true;
		}
		else if (this.year > another.year)
		{
			return false;
		}
		else if (this.month<another.month)
		{
			return true;
		}
		else if (this.month>another.month)
		{
			return false;
		}
		else if (this.day<another.day)
		{
			return true;
		}
		else return false;
	}
	
	// Return a string for the day like dd MMM yyyy
	@Override
	public String toString() {
		
		return day + "-" + MonthNames.substring((month-1)*3,month*3) + "-" + year;
	}
}
