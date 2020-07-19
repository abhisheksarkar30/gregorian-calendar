import java.util.Scanner;

/**
 * This class serves to display the calendar for a given Gregorian month.
 * 
 * @author abhisheksa
 * @see <a href="https://artofmemory.com/blog/how-to-calculate-the-day-of-the-week-4203.html">Source</a>
 */
public class GregorianCalendarCalculator extends DayFromGregorianDateCalculator {
	
    public GregorianCalendarCalculator(int dateNo, int month, int year) {
		super(dateNo, month, year);
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int year, month, dateNo;
        
        while(true) {
            System.out.print("Enter an exact possible date exactly in the format of MM/YEAR: ");
            String dateStr = sc.next().trim();
            
            //Stop the program if 0 is entered.
            if(dateStr.equals("0")) break;
            
            //Parsing dateNo, month, year from provided date
            year = Integer.parseInt(dateStr.substring(3));
            month = Integer.parseInt(dateStr.substring(0,2));
            dateNo = 1;
            
            GregorianCalendarCalculator dfgdCalculator = new GregorianCalendarCalculator(dateNo, month, year);
            
            int dateCode = dfgdCalculator.calculate();
            
            if(dateCode >= 0)
            	dfgdCalculator.printCalendar(dateCode);
        }
    }
    
    /**
     * Prints the calendar of the given month.
     * 
     * @param startDateCode - Takes the calculated date code of 1st date of the month.
     */
    private void printCalendar(int startDateCode) {
    	System.out.println("\n\nSUN\tMON\tTUE\tWED\tTHU\tFRI\tSAT");
    	
    	for(int index = 1; index <= startDateCode; index++)
    		System.out.print("\t");
    	
    	for(int currentDateNo = 1; currentDateNo <= (isLeapYear && month == 2? 29 : dayCountInMonths[month-1]); currentDateNo++) {
    		if((currentDateNo + startDateCode) % 7 == 0)
    			System.out.println(currentDateNo + "\t");
    		else
    			System.out.print(currentDateNo + "\t");
    	}
    	
    	System.out.println("\n");
    }

}