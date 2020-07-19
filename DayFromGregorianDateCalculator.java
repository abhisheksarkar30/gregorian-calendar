import java.util.Scanner;

/**
 * This class serves to display the day of week for a given Gregorian date.
 * 
 * @author abhisheksa
 * @see <a href="https://artofmemory.com/blog/how-to-calculate-the-day-of-the-week-4203.html">Source</a>
 */
public class DayFromGregorianDateCalculator {
	
	public static final int dayCountInMonths[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    public static final String daysOfWeek[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    
    public static final int monthCodes[] = {0,3,3,6,1,4,6,2,5,0,3,5};
    public static final int centuryCodes[] = {6,4,2,0};
    
    boolean isLeapYear;
    int dateNo, month, year;
    
    public DayFromGregorianDateCalculator(int dateNo, int month, int year) {
		this.dateNo = dateNo;
		this.month = month;
		this.year = year;
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int dateNo, month, year;
        
        while(true) {
            System.out.print("Enter an exact possible date exactly in the format of DD/MM/YEAR: ");
            String dateStr = sc.next().trim();
            
            //Stop the program if 0 is entered.
            if(dateStr.equals("0")) break;
            
            //Parsing dateNo, month, year from provided date
            year = Integer.parseInt(dateStr.substring(6));
            month = Integer.parseInt(dateStr.substring(3,5));
            dateNo = Integer.parseInt(dateStr.substring(0,2));
            
            DayFromGregorianDateCalculator dfgdCalculator = new DayFromGregorianDateCalculator(dateNo, month, year);
            
            int dateCode = dfgdCalculator.calculate();
            
            if(dateCode >= 0)
            	System.out.println("The given date is a " + daysOfWeek[dateCode]);
        }
    }
    
    /**
     * Calculates the final date code which represents the day of week the given date falls on.
     * 
     * @return -1 if invalid date, else returns value between [0, 6]
     */
    public int calculate() {
    	if(!isValid()) {
        	System.out.println("Invalid date!!! Please re-enter correct date!");
        	return -1;
        }
        
        //Calculating corresponding year code
        int yearCode = (year % 100 + (year % 100) / 4) % 7;
        
        //Calculating corresponding month code
        int monthCode = monthCodes[month - 1];
        
        //Calculating corresponding century code
        int centuryCode = centuryCodes[year / 100 % 4];
        
        //If the date is in January or February of a leap year, subtract 1
        int leapYearCode = isLeapYear && month <= 2? 1  :0;
        
        //Final Calculation of date code
        int dateCode = (yearCode + monthCode + centuryCode + dateNo - leapYearCode) % 7;
        
        return dateCode;
	}

    /**
     * Checks if leap year and if the given date is valid.
     * 
     * @param dateNo - The provided date/day no. in the month
     * @param month - The provided month no.
     * @param year - The provided year no.
     * @return True, only if the given date is valid.
     */
	private boolean isValid() {
    	//Check for Leap year
    	isLeapYear = ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    	
    	//Check for validity of provided date
        if(year >= 0 && (month > 0 && month <= 12) && (dateNo > 0 && dateNo <= dayCountInMonths[month-1] + (isLeapYear? 1 : 0)))
        	return true;
        
        return false;
    }
	
}