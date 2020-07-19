var dayCountInMonths = [31,28,31,30,31,30,31,31,30,31,30,31];
var monthNames = ["January","February","March","April","May","June","July","August","September","October","November","December"];
    
var monthCodes = [0,3,3,6,1,4,6,2,5,0,3,5];
var centuryCodes = [6,4,2,0];

var isLeapYear;
var dateNo, month, year;

clearCalendar = function() {
	$(".days").empty();
};

function isValid() {
	//Check for Leap year
	isLeapYear = ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	
	//Check for validity of provided date
	if(year >= 0 && (month > 0 && month <= 12) && (dateNo > 0 && dateNo <= dayCountInMonths[month - 1] + (isLeapYear? 1 : 0)))
		return true;
	
	return false;
};

calculate = function() {
	if(!isValid()) {
		alert("Invalid date!!! Please re-enter correct date!");
		return -1;
	}
	
	//Calculating corresponding year code
    var yearCode = (year % 100 + parseInt((year % 100) / 4)) % 7;
	
	//Calculating corresponding month code
	var monthCode = monthCodes[month - 1];
	
	//Calculating corresponding century code
	var centuryCode = centuryCodes[parseInt(year / 100) % 4];
	
	//If the date is in January or February of a leap year, subtract 1
	var leapYearCode = isLeapYear && month <= 2? 1  :0;
	
	//Final Calculation of date code
	var dateCode = (yearCode + monthCode + centuryCode + dateNo - leapYearCode) % 7;
	
	return dateCode;
};

function printCalendar(startDateCode) {
	$("#year").text(year);
	$("#month").text(monthNames[month - 1]);
	
	for (index = 1; index <= startDateCode; index++) {
		$("<li></li>").appendTo($(".days"));
	}	
	
	for (currentDateNo = 1; currentDateNo <= (isLeapYear && month == 2? 29 : dayCountInMonths[month - 1]); currentDateNo++) {
		$("<li>" + currentDateNo + "</li>").appendTo($(".days"));
	}
};

prepareCalendar = function() {
	clearCalendar();
	
	//Parsing dateNo, month, year from provided date
	month = parseInt($("#monthYear").val().substring(5));
	year = parseInt($("#monthYear").val().substring(0, 4));
	dateNo = 1;
	
	console.log("Input : " + year + " " + month);
	
	var startDateCode = calculate();
	
	printCalendar(startDateCode);	
};