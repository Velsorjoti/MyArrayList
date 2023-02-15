package myexception;



public class NonCorrectIndexException extends IndexOutOfBoundsException {
    public NonCorrectIndexException(String s) {
        super(s);
    }
}
