package com.sofka.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Class that creates different utilities that are needed in the program and allows to obtain them by instantiating
 * a single class.
 * ex.:
 *
 *   Utility utility = new Utility;
 *   utility.displayData("display a message");
 *   utility.getDataUser(DataUserType.INTEGER);
 * ]
 *
 * @version 1.03.000 2022-07-14, The class correspond to the first version of the system
 *                               the same has 3 refactorings in the version and
 *                               0 minor changes in the third refactoring.
 *                               The last change was made on April 23rd of 2022.
 *
 * @author Katerin Calderón - kccc0713@gmail.com
 *
 * @since 1.00.000
 */
public class Utility {

    /**
     * New instance of scanner class allows to capture data.
     */
    private Scanner getUserData = new Scanner(System.in);

    /**
     * Display a message to the user.
     * @param message the message that is display by console to the user.
     */
    public void displayData(String message){
        System.out.println(message);
    }

    /**
     * Display a message to the user.
     * @param message the message that is display by console to the user.
     */
    public void displayDataNoLineBreak(String message){
        System.out.print(message);
    }

    public void displayError(String message){
        System.err.println(message);
    }

    /**
     * Allows capturing different types of data from the user.
     * @param dataUserType defines the type of data to capture from the user: float, String and int.
     * @return an objet with captured user data.
     */
    public Object getDataUser(com.sofka.util.DataUserType dataUserType){

        return switch (dataUserType) {
            case TEXT -> getTextDataUser();
            case DOUBLE -> getDoubleDataUser();
            case INTEGER -> getIntegerDataUser();
            case BOOLEAN -> getBooleanUser();
            case DATE -> getDateDataUser();
            case TIME -> getTimeDataUser();
        };
    }

    /**
     * Allows capturing data from the user of type String.
     * @return the information type String capture.
     */
    private String getTextDataUser(){
        return getUserData.nextLine();
    }

    /**
     * Allows capturing data from the user of type integer.
     * @return the information type int capture and validate if the data is a number and if is positive.
     */
    private int getIntegerDataUser(){

        int number;

        do {

            while (!getUserData.hasNextInt()) {
                displayError("Invalid integer number entered. Please try again:");
                getUserData.next();
            }

            number = getUserData.nextInt();
            validateNumber(number);

        } while (number < 0);
        return number;
    }

    /**
     * Allows capturing data from the user of type double.
     * @return the information type double capture and validate if the data is a decimal number and if is positive.
     */
    private double getDoubleDataUser(){

        double number;
        do {
            while (!getUserData.hasNextDouble()) {
                displayError("Invalid decimal number entered. Please try again:");
                getUserData.next();
            }
            number = getUserData.nextDouble();
            validateNumber(number);
        } while (number < 0);
        return number;
    }

    /**
     * Allows capturing data from the user of type boolean.
     * @return the information type double capture and validate
     * if the data is a decimal number and if is positive.
     */
    private Boolean getBooleanUser(){

        int booleanOption;
        do {

            validateNumberBool(getUserData);

            booleanOption = getUserData.nextInt();
            validateBoolean(booleanOption);

        } while (isBoolean(booleanOption));

        return booleanOption == 1;

    }

    private boolean isBoolean(int booleanOption) {
        return booleanOption != 0 && booleanOption != 1;
    }

    private void validateNumberBool(Scanner getUserData) {
        while (!getUserData.hasNextInt()) {
            displayError("Please indicate 1 for true or 0 for false:");
            getUserData.next();
        }
    }

    /**
     * Allows capturing data from the user of type date format (yyyy-mmm-dd).
     * @return the date enter for the user.
     */
    private LocalDate getDateDataUser(){

        LocalDate date = null;
        String dateText;

        do{
            try {
                dateText = getUserData.nextLine();
                date = LocalDate.parse(dateText);
            }
            catch (DateTimeParseException exception){
                displayError("Invalid date entered. The valid format is: yyyy-mm-dd. Please try again:");
            }
        }while (date == null);

        return date;
    }

    /**
     * Allows capturing data from the user of type time format (HH:MM).
     * @return the time enter for the user.
     */
    private LocalTime getTimeDataUser(){

        LocalTime time = null;
        String hourText;

        do{
            try {
                hourText = getUserData.nextLine();
                time = LocalTime.parse(hourText);
            }
            catch (DateTimeParseException exception){
                displayError("Invalid hour entered. The valid format is: HH:MM Please try again:");
            }
        }while (time == null);

        return time;
    }

    /**
     * Validate if the number enter for the user is different of 1 or 0.
     * @param number the number to validate.
     */
    private void validateBoolean(int number){
        if (isBoolean(number)){
            displayError("Please indicate 1 for true or 0 for false:");
        }
    }

    /**
     * Evaluate if a number is positive.
     * @param numeric number to evaluate.
     */
    private void validateNumber(double numeric){
        if(numeric < 0){
            displayError("The number must be positive. Please try again:");
        }
    }
}
