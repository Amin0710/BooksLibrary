

public class Book {
    //variable declaration
    String title, author;
    String isbnNumber;
    int yearOfPublication;
    //parameterized constructor
    public Book(String title, String author, int publicationYear, String iSBN_Data) {

        this.author = author;
        this.title = title;
        this.yearOfPublication = publicationYear;
        this.isbnNumber = iSBN_Data;

    }
    //non parameterized constructor
    public Book() {
    }
    
    //getter and setter of title, author, isbn number, year of publication
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

}
