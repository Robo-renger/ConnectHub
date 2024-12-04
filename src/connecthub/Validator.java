package connecthub;

public class Validator {
    private static final String EMAIL_REGEX = "^[a-z0-9]+@[a-z]+\\.[a-z]+$";
    
    public static boolean validate(int i, String data)
    {
        switch(i)
        {
            case 0:
                return data.matches(EMAIL_REGEX);
        }
        return false;
    }
}
