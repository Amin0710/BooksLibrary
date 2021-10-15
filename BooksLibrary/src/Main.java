
//import package
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	// creating arraylist
	static ArrayList<Book> Book = new ArrayList<Book>();
	static ISBN_Format isbn = new ISBN_Format();

	public static void main(String args[]) throws IOException {

		Scanner scanner_Obj = new Scanner(System.in);
		// opening and read file operation
		File getfile = new File("datas.txt");
		try {
			BufferedReader readData = new BufferedReader(new FileReader(getfile));
			String linesInFile;
			String[] bookDatas;
			Book books = null;
			while ((linesInFile = readData.readLine()) != null) {

				bookDatas = linesInFile.split(",");

				for (int x = 0; x < bookDatas.length; x++) {
					// while getting data, if any braces found means, it will be
					// replaced
					bookDatas[x] = bookDatas[x].toString().replace("[", "").replace("]", "").trim();
					// adding datas to object
					books = new Book(bookDatas[0], bookDatas[1], Integer.parseInt(bookDatas[2]), bookDatas[3]);
				}
				// adding datas to arraylist
				Book.add(books);
			}
		}

		catch (FileNotFoundException ex) {
			System.out.println("Given file is not available");
			System.exit(0);
		}
		int Choice;
		while (true) {
			System.out.println("  \n");
			System.out.println(" --- BOOKS LIBRARY ---\n");
			System.out.print("1 --> Add a new book\n");
			System.out.print("2 --> Delete a book\n");
			System.out.print("3 --> Search for a book\n");
			System.out.print("4 --> Display all book\n");
			System.out.print("5 --> Exit\n");
			System.out.print("Please, Select the option\n");
			System.out.print(" ");
			// handling input mismatch exception
			try {
				Choice = scanner_Obj.nextInt();
				// switch case to execute the choice
				switch (Choice) {

				case 1:
					AddingBook();
					break;

				case 2:
					DeleteBook();
					break;

				case 3:
					SearchBook();
					break;

				case 4:
					DisplayBook();
					break;

				case 5:
					writeFile(Book);
					System.out.println("Exiting Program...");
					System.exit(0);
					break;

				default:
					System.out.println("Invalid choice !! Choose the right option");
					break;

				}
			} catch (InputMismatchException ex) {

				System.out.println("Input is mismatched");
				break;

			}
		}
	}

	public static void AddingBook() throws NumberFormatException, IOException {

		String title, author;
		int yearOfPublication = 0;
		String isbnNumber = "";
		Book books = new Book();
		Scanner input = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("Add Book");
		System.out.println("_________________________");
		System.out.println("Enter title of the book");
		title = input.nextLine();
		books.setTitle(title);
		System.out.println("Enter author of the book");
		author = input.nextLine();
		books.setAuthor(author);
		System.out.println("Enter year of publication");
		String publicationYear = null;
		boolean check = true;
		// process of validating year
		while (check) {

			yearOfPublication = input.nextInt();
			input.nextLine();
			books.setYearOfPublication(yearOfPublication);
			publicationYear = String.valueOf(books.getYearOfPublication());
			if (publicationYear.length() != 4) {
				System.out.print("ERROR year must have 4 digit\n");
			} else {
				check = false;
			}
		}

		boolean check1 = true;
		int Counts_1 = 0;
		String getUserChoice = null;
		int increment_count = 0;
		// validating isbn number
		while (check1) {
			System.out.println("Input a 10-digit ISBN number ");
			isbnNumber = input.nextLine();
			Scanner scan = new Scanner(System.in);

			int counts = 0, count_2 = 0;
			if (isbn.ISBNFormat(isbnNumber) == true) {

				for (int i = 0; i < Book.size(); i++) {

					if (Book.get(i).getIsbnNumber().equalsIgnoreCase(isbnNumber)) {

						increment_count = increment_count + 1;

					}
				}

				if (increment_count > 0) {
					System.out.println("Isbn is already present, need to add second copy");
					getUserChoice = scan.next();
					if (getUserChoice.equalsIgnoreCase("NO")) {
						counts = counts + 1;

					}

					else if (getUserChoice.equalsIgnoreCase("YES")) {
						count_2 = count_2 + 1;

					}

				} else {
					Counts_1 = Counts_1 + 1;
				}

				if (Counts_1 > 0) {

					books.setIsbnNumber(isbnNumber);
					System.out.println("Book will be successfully added");
					books = new Book(title, author, yearOfPublication, isbnNumber);
					Book.add(books);
					check1 = false;
					break;

				}

				if (counts > 0) {
					System.out.println("Book will not be added");
					check1 = false;

				}

				if (count_2 > 0) {

					books.setIsbnNumber(isbnNumber);
					books = new Book(title, author, yearOfPublication, isbnNumber);
					Book.add(books);
					System.out.println("Book will be successfully added");
					check1 = false;

				}

			} else {
				System.out.println(" ");
				System.out.println("Illegal ISBN, enter the correct isbn number");

			}

		}

	}

	// method to do the delete operation
	public static void DeleteBook() throws NumberFormatException, IOException {

		DisplayBook();
		Scanner scanner_Obj = new Scanner(System.in);
		boolean executeLoop = true;
		Book books = new Book();
		int Choice;
		System.out.println(" ");
		System.out.println("Delete Book ");
		System.out.println("______________________________\n");
		System.out.print("Title\n");
		System.out.print("Author\n");
		System.out.print("Year of Publication\n");
		System.out.print("ISBN Number\n");
		System.out.println("Please, select the option\n");
		// handling input mismatch exception
		try {
			Choice = scanner_Obj.nextInt();
			Scanner input = new Scanner(System.in);

			// deleting the book based on the title
			if (Choice == 1) {
				String title;
				int count = 0;
				System.out.println("Enter title of the book");
				title = input.nextLine();
				for (int i = 0; i < Book.size(); i++) {

					if (Book.get(i).getTitle().equalsIgnoreCase(title)) {
						Book.remove(i);
						count = count + 1;
						System.out.println(" ");
						System.out.println("Book based on the given title is deleted");
						System.out.println(" ");
						break;

					}
				}
				// error message
				if (count == 0) {

					System.out.println("Invalid title");
				}

				System.out.println();

			}
			// deleting the book based on the author
			if (Choice == 2) {
				String author;
				int count = 0;
				System.out.println("Enter author of the book");
				author = input.nextLine();
				for (int i = 0; i < Book.size(); i++) {

					if (Book.get(i).getAuthor().equals(author)) {
						Book.remove(i);
						count = count + 1;
						System.out.println(" ");
						System.out.println("Book based on the given author name is deleted");
						System.out.println(" ");
						break;
					}
				}
				// error message
				if (count == 0) {

					System.out.println("Invalid author");
				}

				System.out.println();

			}
			// deleting the book based on the year
			if (Choice == 3) {
				int yearOfPublication = 0;
				int count = 0;
				System.out.println("Enter publication year  of the book");

				String publicationYear = null;
				boolean check = true;
				while (check) {

					yearOfPublication = input.nextInt();
					publicationYear = String.valueOf(yearOfPublication);
					if (publicationYear.length() != 4) {
						System.out.print("ERROR year must have 4 digits\n");
					} else {
						check = false;
					}
				}

				for (int i = 0; i < Book.size(); i++) {

					if (Book.get(i).getYearOfPublication() == yearOfPublication) {
						Book.remove(i);
						count = count + 1;
						System.out.println(" ");
						System.out.println("Book based on the given publication year is deleted");
						System.out.println(" ");
						break;
					}
				}
				// error message
				if (count == 0) {

					System.out.println("Invalid year of publication");
				}

				System.out.println();

			}
			// deleting the book based on the ISBN number
			if (Choice == 4) {
				int count = 0;
				String isbnNumber = "";

				String ISBN_Data = null;

				boolean check1 = true;
				// process of validating ISBN number
				while (check1) {

					InputStreamReader in = new InputStreamReader(System.in);
					BufferedReader br = new BufferedReader(in);
					System.out.print("Input a 10-digit ISBN number: ");
					isbnNumber = br.readLine();
					if (isbn.ISBNFormat(isbnNumber) == true) {

						for (int i = 0; i < Book.size(); i++) {

							if (Book.get(i).getIsbnNumber().equals(isbnNumber)) {
								Book.remove(i);
								count = count + 1;
								System.out.println(" ");
								System.out.println("Book based on the given isbn number is deleted");
								System.out.println(" ");
							}
						}
						check1 = false;
					} else {
						check1 = true;
						System.out.println("Illegal ISBN");
					}

				}
				// error message
				if (count == 0) {

					System.out.println("Invalid ISBN Number");

				}

				System.out.println();
			}
		} catch (InputMismatchException ex) {

			System.out.println("Input is mismatched");

		}
	}

	// method to search the book
	public static void SearchBook() throws NumberFormatException, IOException {

		Scanner scanner_Obj = new Scanner(System.in);
		boolean executeLoop = true;

		int Choice;
		Book books = new Book();
		System.out.println(" ");
		System.out.println("Search Book");
		System.out.println("_______________________________\n");
		System.out.print("Title\n");
		System.out.print("Author\n");
		System.out.print("Year of Publication\n");
		System.out.print("ISBN Number\n");
		System.out.println("Please, select the option\n");
		// handling inputmismatch exception
		try {
			Choice = scanner_Obj.nextInt();
			Scanner input = new Scanner(System.in);

			// searching based on title
			if (Choice == 1) {

				String title;
				System.out.println("Enter title of the book");
				title = input.nextLine();
				int count = 0;
				for (int i = 0; i < Book.size(); i++) {

					if (Book.get(i).getTitle().equals(title)) {
						System.out.println("\nTitle: " + Book.get(i).getTitle());
						System.out.println("Author: " + Book.get(i).getAuthor());
						System.out.println("Year: " + Book.get(i).getYearOfPublication());
						System.out.println("ISBN: " + Book.get(i).getIsbnNumber());
						count = count + 1;
					}
				}
				// error message
				if (count == 0) {

					System.out.println("invalid title");

				}

				System.out.println();

			}
			// searching based on author
			if (Choice == 2) {

				String author;
				System.out.println("Enter author of the book");
				author = input.nextLine();
				int count = 0;
				for (int i = 0; i < Book.size(); i++) {

					if (Book.get(i).getAuthor().equals(author)) {
						System.out.println("\nTitle: " + Book.get(i).getTitle());
						System.out.println("Author: " + Book.get(i).getAuthor());
						System.out.println("Year: " + Book.get(i).getYearOfPublication());
						System.out.println("ISBN: " + Book.get(i).getIsbnNumber());
						count = count + 1;
					}
				}
				// error message
				if (count == 0) {

					System.out.println("invalid author name");
				}

				System.out.println();

			}
			// searching based on publication year
			if (Choice == 3) {
				int count = 0;
				int yearOfPublication = 0;
				System.out.println("Enter publication year  of the book");

				String publicationYear = null;
				boolean check = true;
				while (check) {

					yearOfPublication = input.nextInt();
					books.setYearOfPublication(yearOfPublication);
					publicationYear = String.valueOf(books.getYearOfPublication());

					if (publicationYear.length() != 4) {
						System.out.print("ERROR year must have 4 digits\n");
					} else {
						check = false;
					}}

					for (int i = 0; i < Book.size(); i++) {

						if (Book.get(i).getYearOfPublication() == yearOfPublication) {
							System.out.println("\nTitle: " + Book.get(i).getTitle());
							System.out.println("Author: " + Book.get(i).getAuthor());
							System.out.println("Year: " + Book.get(i).getYearOfPublication());
							System.out.println("ISBN: " + Book.get(i).getIsbnNumber());
							count = count + 1;
						}
					}
					// error message
					if (count == 0) {

						System.out.println("Invalid publication year");
					}

					System.out.println();
				

			}
			// searching based on ISBN number
			if (Choice == 4) {
				int counts = 0;
				String isbnNumber = "";
				String ISBN_Data = null;

				boolean check1 = true;
				// process of validating ISBN number
				while (check1) {

					InputStreamReader in = new InputStreamReader(System.in);
					BufferedReader br = new BufferedReader(in);
					System.out.print("Input a 10-digit ISBN number: ");
					isbnNumber = br.readLine();
					books.setIsbnNumber(isbnNumber);
					ISBN_Data = String.valueOf(books.getIsbnNumber());

					ISBN_Format isbn = new ISBN_Format();
					if (isbn.ISBNFormat(books.getIsbnNumber()) == true) {

						check1 = false;
					} else {
						check1 = true;
						System.out.println("Illegal ISBN number");
					}
				}

				for (int i = 0; i < Book.size(); i++) {

					if (String.valueOf(Book.get(i).getIsbnNumber()).equals(ISBN_Data)) {
						System.out.println("\nTitle: " + Book.get(i).getTitle());
						System.out.println("Author: " + Book.get(i).getAuthor());
						System.out.println("Year: " + Book.get(i).getYearOfPublication());
						System.out.println("ISBN: " + Book.get(i).getIsbnNumber());
						counts = counts + 1;
					}
				}
				// error message
				if (counts == 0) {

					System.out.println("Invalid isbn number");

				}
				System.out.println();
			}
		} catch (InputMismatchException ex) {

			System.out.println("Input is mismatched");

		}
	}

	// method to display the book details
	public static void DisplayBook() throws FileNotFoundException {
		// sorting operation
		for (int i = 0; i < Book.size(); i++) {
			for (int j = i + 1; j < Book.size(); j++) {
				if ((Book.get(i).getTitle().compareToIgnoreCase(Book.get(j).getTitle())) > 0) {
					Book tmp;
					tmp = Book.get(i);
					Book.set(i, Book.get(j));
					Book.set(j, tmp);
				} else if ((Book.get(i).getTitle().compareToIgnoreCase(Book.get(j).getTitle())) == 0) {
					if ((Book.get(i).getAuthor().compareToIgnoreCase(Book.get(j).getAuthor())) > 0) {
						Book tmp;
						tmp = Book.get(i);
						Book.set(i, Book.get(j));
						Book.set(j, tmp);
					} else if ((Book.get(i).getAuthor().compareToIgnoreCase(Book.get(j).getAuthor())) == 0) {
						if (Book.get(i).getYearOfPublication() > Book.get(j).getYearOfPublication()) {
							Book tmp;
							tmp = Book.get(i);
							Book.set(i, Book.get(j));
							Book.set(j, tmp);
						}
					}
				}
			}
		}
		// printing datas in arraylist
		for (int i = 0; i < Book.size(); i++) {
			System.out.println("");
			System.out.println("Title: " + Book.get(i).getTitle());
			System.out.println("Author: " + Book.get(i).getAuthor());
			System.out.println("Year of Publication: " + Book.get(i).getYearOfPublication());
			System.out.println("ISBN number: " + Book.get(i).getIsbnNumber());
		}

	}

	private static void writeFile(List<Book> book) throws FileNotFoundException {
		// opening the file
		PrintWriter writeProcess = new PrintWriter("datas.txt");
		writeProcess.print("");
		// for loop to write the datas in file
		for (int i = 0; i < ((List<Book>) book).size(); i++) {
			String line = Book.get(i).getTitle() + "," + Book.get(i).getAuthor() + ","
					+ Book.get(i).getYearOfPublication() + "," + Book.get(i).getIsbnNumber() + "";
			// replacing the braces and whitespaces
			Book.toString().replace("[", "").replace("]", "").replace("\\s", "");
			writeProcess.println(line);
		}
		// close the file
		writeProcess.close();

	}

}
