package com.org.kodvix.freelanceapp.exceptions;

/**************************************************************************************
 * @author       Vishnuvardhan
 * Description : Invalid JobSeeker Exception class
 * Created Date: 22 April, 2021
 * Version     : v1.0.0
 *************************************************************************************/
public class InvalidJobSeekerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidJobSeekerException() {
        super();
    }

    public InvalidJobSeekerException(String message) {
        super(message);
    }
}
