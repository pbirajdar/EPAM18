/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codepest.logic.validation;

import codepest.logic.exceptions.CompileTimeException;
import codepest.logic.utilities.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Prashant-PC
 */
public class JavaCompileValidation implements IValidation{

    String javaFilePath, classPath;

    public JavaCompileValidation(String javaFilePath, String classPath) {
        this.javaFilePath = javaFilePath;
        this.classPath = classPath;
    }
    
    
    @Override
    public void validate() throws Exception {
        String fileLocation = javaFilePath.substring(0, javaFilePath.lastIndexOf("\\"));
        String javaFileName = javaFilePath.substring(javaFilePath.lastIndexOf("\\")+1);
        String cmd = "cd "+fileLocation
                    +"\njavac -cp "+classPath+" "+javaFileName+" > \""+Constants.FilePathsConstants.compileOutputFilePath+"\" 2>&1"
                    +"\nexit 0";
        FileWriter cmdFile = new FileWriter(Constants.FilePathsConstants.compileBatFilePath);
        cmdFile.write(cmd);
        cmdFile.close();
        
        String[] command = {"cmd.exe", "/C", "Start", Constants.FilePathsConstants.compileBatFilePath};
        Runtime.getRuntime().exec(command); 
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.FilePathsConstants.compileOutputFilePath)));
        String line;
        while ((line = br.readLine()) != null) {
            if(line.trim().length() > 0)
            {
                br.close();
                throw new CompileTimeException("Given "+javaFileName+" file has some errors !\nPlease clear those issues before starting the process.\n For more information see the report which is stored at following location:\n"+Constants.FilePathsConstants.reportFilePath);
            }
        }
        br.close();
    }
   
}
