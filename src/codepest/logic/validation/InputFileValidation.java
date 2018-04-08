/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codepest.logic.validation;

import codepest.logic.exceptions.InvalidInputFileException;

/**
 *
 * @author Prashant-PC
 */
public class InputFileValidation implements IValidation{
    String filePath;

    public InputFileValidation(String filePath) {
        this.filePath = filePath;
    }
    
   
    @Override
    public void validate() throws Exception {
        if(!filePath.endsWith(".java"))
            throw new InvalidInputFileException("Given path for file is not a JAVA file !\nPlease Provide the .java file path.");
    }
    
}
