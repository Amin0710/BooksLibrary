


public class ISBN_Format {

    public boolean ISBNFormat(String isbnNumber) {
        //validating operation of isbn number
        if (isbnNumber.length() != 10) {
            return false;
        } else {
            return true;
        }

    }

}
