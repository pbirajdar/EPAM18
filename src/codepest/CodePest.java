/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codepest;

import codepest.logic.exceptions.CompileTimeException;
import codepest.logic.exceptions.InvalidInputFileException;
import codepest.logic.reports.CompileTimeErrorReport;
import codepest.logic.reports.IReports;
import codepest.logic.validation.IValidation;
import codepest.logic.validation.InputFileValidation;
import codepest.logic.validation.JavaCompileValidation;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

/**
 *
 * @author Prashant-PC
 */
public class CodePest {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        String filePath = args[0];
        String classPath = args[1];
        IValidation fileInputValidation = new InputFileValidation(filePath);
        IValidation javaCompileValidation = new JavaCompileValidation(filePath,classPath);
        try 
        {
            fileInputValidation.validate();
            javaCompileValidation.validate();
            
            
        } catch (InvalidInputFileException inputFileException) 
        {
            System.out.println(inputFileException.toString());
        } catch (CompileTimeException compileTimeException)
        {
            System.out.println(compileTimeException.toString());
            IReports compileTimeErrorReprt = new CompileTimeErrorReport(filePath);
            compileTimeErrorReprt.makeReport();
        }
    }
   
}
