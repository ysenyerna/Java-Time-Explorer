import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;
	private static DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private static DateTimeFormatter longFormat = DateTimeFormatter.ofPattern("E, MMM dd, yyyy");

	public static void main(String[] args) {
		System.out.println("Java Time Explorer");
		System.out.println("------------------");

		scanner = new Scanner(System.in);

	}

	// Prompt the user to enter a date 
	private static LocalDate promptForDate(String msg) {
		System.out.print(msg);
		String input = "";
		// Prompt the user to enter a date until they enter a valid one
		while (true) {
			input = scanner.nextLine();
			// Parse and return the user's input
			try {
				return LocalDate.parse(input, shortFormat);
			}
			catch (Exception e) {
				System.out.print("Invalid input... Please enter a date using MM/dd/yyy format: ");
			}
		}
	}

	// Displays the current date, time, and date time together
	private static void DisplayCurrentDateTime() {
		System.out.println("Current Date: " + LocalDate.now().toString());
		System.out.println("Current Time: " + LocalTime.now().toString());
		System.out.println("Current Date and Time: " + LocalDateTime.now().toString());

	}

	// Display the same date in 3 different formats
	private static void DisplayDateFormatting() {
		DateTimeFormatter exampleFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate date = LocalDate.now();
		System.out.println("Default Format: " + date.format(exampleFormat));
		System.out.println("Short Format: " + date.format(shortFormat));
		System.out.println("Long Format: " + date.format(longFormat));
	}

	// Ask user to enter a date
	private static void DisplayUserEnteredDate() {
		LocalDate inputDate = promptForDate("Enter a date using MM/dd/yyyy format: ");
		System.out.println("You entered the date " + inputDate.format(longFormat));

	}

	// Calculate the difference between two dates
	private static void DisplayAgeDifference() {
		LocalDate inputDate = promptForDate("Enter your birth date using MM/dd/yyyy format: ");
		Period period = Period.between(inputDate, LocalDate.now());
		System.out.println("Time since that date: ");
		System.out.println(period.getYears() + " years, " + period.getMonths() + 
		" months, and " + period.getDays() + " days");
		
	}




}
