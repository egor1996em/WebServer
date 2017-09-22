/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.validators.typevalidator;


/**
 *
 * @author User
 */
//Проверка корректности пути к файлу
public class ContentDirectoryPathValidator {
    private static final ContentDirectoryPathValidator VALIDATOR = new ContentDirectoryPathValidator();
    
    public static ContentDirectoryPathValidator getInstance()
    {
        return VALIDATOR;
    }
    
    public boolean isValid(String ContentDirectoryPath)
    {
        //Проверим текст на наличие символов
        return ContentDirectoryPath.matches("^[a-zA-Z0-9/]+$");
    }
    
}
