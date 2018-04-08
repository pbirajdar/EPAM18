/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codepest.logic.reports;

import codepest.logic.exceptions.CompileTimeException;
import codepest.logic.utilities.Constants;
import codepest.logic.utilities.ReportUtility;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 *
 * @author Prashant-PC
 */
public class CompileTimeErrorReport implements IReports{

    String fileName;
    public CompileTimeErrorReport(String filePath) {
        this.fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
    }

    public String getMessage() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.FilePathsConstants.compileOutputFilePath)));
        String line;
        StringBuilder outData = new StringBuilder();
        while ((line = br.readLine()) != null) {
            outData.append(line+"</br>");
        }
        br.close();
        return "<center>\n" +
                "<table border=1>\n" +
                "<tr>\n" +
                "<th>Title</th>\n" +
                "<td>Compile Time Error Report</td>\n" +
                "<tr>\n" +
                "<tr>\n" +
                "<th>Description</th>\n" +
                "<td>The given file "+fileName+" has a some errors. Hence we can't go furhter to find vulnearibilties or bugs from given java file.</td>\n" +
                "<tr>\n" +
                "<tr>\n" +
                "<th>Error Description</th>\n" +
                "<td>"+outData.toString()+"</td>\n" +
                "<tr>\n" +
                "<tr>\n" +
                "<th>Resoultion</th>\n" +
                "<td>Clear the errors from given java file before executing the findpest process.</td>\n" +
                "<tr>\n" +
                "</table>\n" +
                "</center>";
    }
    
    @Override
    public void makeReport() {
        try
        {
            StringBuilder htmlData = new StringBuilder();
            htmlData.append(ReportUtility.addHeader("Compile Time Error Report"));
            htmlData.append(getMessage());
            htmlData.append(ReportUtility.addFooter());
            FileWriter cmdFile = new FileWriter(Constants.FilePathsConstants.reportFilePath);
            cmdFile.write(htmlData.toString());
            cmdFile.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        
        
    }
    
}
