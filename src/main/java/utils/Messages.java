package utils;

public class Messages {

    public static String onFailure(String expected, String actual){
        return String.format("Incorrect result: Expected '%s', but got '%s'", expected, actual);
    }

}
