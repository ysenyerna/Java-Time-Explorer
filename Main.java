import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;
	private static DateTimeFormatter shortFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private static DateTimeFormatter longFormat = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy");

	public static void main(String[] args) {
		System.out.println("Java Time Explorer");
		System.out.println("------------------\n");

		// Display all available options
		System.out.println("Choose an option:");
		System.out.println("1. Current Date and Time Display");
		System.out.println("2. Date Formatting");
		System.out.println("3. Date Parsing");
		System.out.println("4. Age Difference Calculator");
		System.out.println("5. Countdown to a Future Date");
		System.out.println("6. Time Duration Calculator");
		System.out.println("7. Time Zone Converter");
		System.out.println("8. Current Instant Display");
		System.out.println("9. Exit");;

		scanner = new Scanner(System.in);
		boolean running = true;


		// Prompt the user to choose options
		while (running) {
			System.out.print("\nChoose an option (1-9): ");

			if (!scanner.hasNextInt()) {
				System.out.println("Please enter a number.");
				scanner.next();
				continue;
			}

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					System.out.println("\nCurrent Date and Time Display");
					System.out.println("-----------------------------");
					DisplayCurrentDateTime();
					break;
				case 2:
					System.out.println("\nDate Formatting");
					System.out.println("---------------");
					DisplayDateFormatting();
					break;
				case 3:
					System.out.println("\nDate Parsing");
					System.out.println("------------");
					DisplayUserEnteredDate();
					break;
				case 4:
					System.out.println("\nAge Difference Calculator");
					System.out.println("-------------------------");
					DisplayAgeDifference();
					break;
				case 5:
					System.out.println("\nCountdown to a Future Date");
					System.out.println("--------------------------");
					DisplayFutureDateCountdown();
					break;
				case 6:
					System.out.println("\nTime Duration Calculator");
					System.out.println("------------------------");
					DisplayTimeDifference();
					break;
				case 7:
					System.out.println("\nTime Zone Converter");
					System.out.println("-------------------");
					DisplayTimeZoneDifferences();
					break;
				case 8:
					System.out.println("\nCurrent Instant Display");
					System.out.println("-----------------------");
					DisplayCurrentInstant();
					break;
				case 9:
					running = false;
					System.out.println("\nExiting...");
					break;
				default:
					System.out.println("Choose a number from 0 to 9.");
			}
		}
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
				System.out.print("Invalid input... Please enter a date using MM/dd/yyyy format: ");
			}
		}
	}

	// Prompt the user to enter a time 
	private static LocalTime promptForTime(String msg) {
		System.out.print(msg);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
		String input = "";
		// Prompt the user to enter a time until they enter a valid one
		while (true) {
			input = scanner.nextLine();
			// Parse and return the user's input
			try {
				return LocalTime.parse(input, format);
			}
			catch (Exception e) {
				System.out.print("Invalid input... Please enter a date using HH:mm format: ");
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

	// Display a user-entered date
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

	// Countdown to a future date
	private static void DisplayFutureDateCountdown() {
		LocalDate inputDate = promptForDate("Enter a future date using MM/dd/yyyy format: ");
		if (inputDate.isBefore(LocalDate.now())) {
			System.out.println("Error, the date you entered is in the past.");
			return;
		}
		long days = ChronoUnit.DAYS.between(LocalDate.now(), inputDate);
		System.out.println("Days until that date: " + days);

	}

	// Calculate difference between two times
	private static void DisplayTimeDifference() {
		LocalTime time1 = promptForTime("Enter a time using HH:mm format: ");
		LocalTime time2 = promptForTime("Enter another time using HH:mm format: ");
		System.out.println("Duration: ");
		Duration duration = Duration.between(time1, time2);
		System.out.println(duration.toHours() + " hour(s) and " + duration.toMinutesPart() + " minute(s)");

	}

	// Timezone converter
	private static void DisplayTimeZoneDifferences() {
		System.out.println("Meeting Time: ");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mm a");
		ZonedDateTime date = ZonedDateTime.of(2026, 12, 1, 14, 0, 0, 0, ZoneId.of("America/New_York"));

		// Print dates
		System.out.println("New York: " + date.format(format));
		System.out.println("Chicago: " + date.withZoneSameInstant(ZoneId.of("America/Chicago")).format(format));
		System.out.println("London: " + date.withZoneSameInstant(ZoneId.of("Europe/London")).format(format));
		System.out.println("Tokyo: " + date.withZoneSameInstant(ZoneId.of("Asia/Tokyo")).format(format));

	}

	// Display current timestamp
	private static void DisplayCurrentInstant() {
		System.out.println("Current Instant");
		System.out.println(Instant.now());
		System.out.println("An instant is useful for timestamps, logs, APIs, " +
		"and databases because it represents a specific moment in time.");
	}


}
