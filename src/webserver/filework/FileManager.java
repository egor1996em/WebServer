/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.filework;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author User
 */
public class FileManager {
    public static void CreateFolderIfNotExist(String folderPath) throws IOException
    {
        File dirToCreate = new File(folderPath);

       //Если папка отсутствует, то создадим ее
       if (!dirToCreate.exists())
       {
           dirToCreate.mkdir();
       }
    }
}
