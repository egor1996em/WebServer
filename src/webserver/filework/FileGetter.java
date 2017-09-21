/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.filework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author User
 */
public class FileGetter {
    
    public static InputStream getFileByURL(String url) throws FileNotFoundException, IOException
    {
        InputStream searchedFile = null;
        
       File fileToGet = new File(url);

       if (fileToGet.exists())
       {
           searchedFile = new FileInputStream(fileToGet);
       }
        
        return searchedFile;
    }
}
