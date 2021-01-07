package travelling.api.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }

    public static boolean validateLinkFacebook(String link) {
        String regex = "((http|https)://)?(www[.])?facebook.com/.+";
        if (link.matches(regex)) return true;
        else return false;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        else if (phoneNo.matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) return true;
        else if (phoneNo.matches("\\d{11}")) return true;

            //return false if nothing matches the input
        else return false;

    }

    public static boolean validatePhoneNew(String phone) {
        if (phone.matches("(\\+?84|0)?(3[2-9]|7[0-9]|8[0-9]|5[0-9])(\\d{7})")) return true;
        else return false;
    }

    public static boolean validatePhoneOld(String phone) {
        if (phone.matches("(\\+?84|0)?(16[2-9]|12[0-9]|18[68]|199)(\\d{7})")) return true;
        else return false;
    }

}
