package myexception;

import java.util.NoSuchElementException;

public class OhYourElementNotFoundExeption extends NoSuchElementException {
    public OhYourElementNotFoundExeption(String s) {
        super(s);
    }
}
