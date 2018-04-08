/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codepest.logic.exceptions;

/**
 *
 * @author Prashant-PC
 */
public class InvalidInputFileException extends Exception{

    String exceptionMessage;
   /* Constructor of custom exception class
    * here I am copying the message that we are passing while
    * throwing the exception to a string and then displaying 
    * that string along with the message.
    */
   public InvalidInputFileException(String exceptionMessage) {
	this.exceptionMessage=exceptionMessage;
   }
   public String toString(){ 
	return ("Invalid File: "+this.exceptionMessage) ;
   }
    
}
