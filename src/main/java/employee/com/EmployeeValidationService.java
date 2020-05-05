package employee.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

class EmployeeValidationService {

   private final int MIN_AGE = 16;
   private final int EMPLOYEE_ID_MIN_LEN= 2;
   private final int PPS_NOT_MIN_LEN = 8;
   private final int MIN_NAME_LENGTH = 3;

   String checkEmployeeIdValidity(String employeeId) {
       if(employeeId.length() < EMPLOYEE_ID_MIN_LEN) {
           throw new IllegalArgumentException("Employee Id must have a minimum of " + EMPLOYEE_ID_MIN_LEN + " digits");
       } else {
           try {
               Integer.parseInt(employeeId);

               return employeeId;
           } catch (NumberFormatException e){
               return  null;
           }
       }
   }

   String checkEmployeeNameValidity(String name) {
       if(name.length() <= MIN_NAME_LENGTH) {
           throw new IllegalArgumentException("Employee name must be a minimum of four letters");
       }
       else {
           return name;
       }
   }

   String checkEmployeeAddressValidity(String address) {
       if(address.equals("")) {
           throw new IllegalArgumentException("Employee address cannot be empty");
       } else {
           return address;
       }
   }

    String checkDOBValidity(String dob) {
        Date today = new Date();
        Date dobEntered;

        try {
            dobEntered = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
        } catch (ParseException e) {
            dobEntered = null;
        }

        if(dobEntered != null) {
            if(dobEntered.compareTo(today) < 0) {
                Calendar c = Calendar.getInstance();
                c.setTime(dobEntered);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH) + 1;
                int date = c.get(Calendar.DATE);
                LocalDate localdate = LocalDate.of(year, month, date);
                LocalDate thisDay = LocalDate.now();
                Period diff1 = Period.between(localdate, thisDay);

                if(diff1.getYears() < MIN_AGE) {
                    throw new IllegalArgumentException("Employee is under the MIN_AGE requirements");
                } else {
                    return dob;
                }
            } else {
                throw new IllegalArgumentException("Employee date of birth is invalid");
            }
        } else {
            return null;
        }
    }

    String checkPPSNoValidity(String ppsNo) {
        if (ppsNo.length() < PPS_NOT_MIN_LEN) {
            throw new IllegalArgumentException("Employee PPS must fulfill the length requirements");
        } else {
            int letterCount = 0;
            int numberCount = 0;

            for (int i = 0; i < ppsNo.length(); i++) {
                char c = ppsNo.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    letterCount++;
                } else if (c >= '0' && c <= '9') {
                    numberCount++;
                } else {
                    throw new IllegalArgumentException("Employee PPS must not contain special characters or lowercase letters");
                }
            }

            if (letterCount == 0 || letterCount > 2 || numberCount == 0) {
                throw new IllegalArgumentException("Employee PPS must contain numbers and 1-2 letters");
            } else {
                return ppsNo;
            }
        }
    }

    String validate(String stringToValidate, final int MIN_LENGTH, boolean checkForNumbers, boolean checkForSpecialCharacters) {
       if (stringToValidate.equals("")) {
           throw new IllegalArgumentException("Empty String");
       }

       if (stringToValidate.length() < MIN_LENGTH) {
           throw new IllegalArgumentException("String doesn't meet minimum length requirement");
       }

       if (checkForNumbers) {
           if (!validateStringDoesNotContainNumbers(stringToValidate)) {
               throw new IllegalArgumentException("String contains numbers");
           }
       }

       if (checkForSpecialCharacters) {
           if (!validateStringDoesNotContainSpecialCharacters(stringToValidate)) {
               throw new IllegalArgumentException("String contains special characters");
           }
       }

       return stringToValidate;
    }

    private boolean validateStringDoesNotContainNumbers(String stringToValidate) {
        char character;

        for (int i = 0; i < stringToValidate.length(); i++) {
            character = stringToValidate.charAt(i);

            if (character >= '0' && character <= '9') {
                return false;
            }
        }

        return true;
    }

    private boolean validateStringDoesNotContainSpecialCharacters(String stringToValidate) {
        char character;

        for (int i = 0; i < stringToValidate.length(); i++) {
            character = stringToValidate.charAt(i);

            if (!(character >= 'A' && character <= 'Z' || character >= 'a' && character <= 'z')) {
                return false;
            }
        }

        return true;
    }
}
