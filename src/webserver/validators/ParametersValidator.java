
package webserver.validators;

import java.io.BufferedReader;
import java.io.IOException;
import webserver.parameters.DefaultParameters;
import webserver.validators.typevalidator.ContentDirectoryPathValidator;
import webserver.validators.typevalidator.InetAddressValidator;
import webserver.validators.typevalidator.ServerPortValidator;

/**
 *
 * @author User
 * Выполняет проверку параметров сервера
 */
public class ParametersValidator {
    
    //Проверяем введенный IP и требуем вводить его до ввода правильного
    public static String getIPAdressTextWithValidate(String IPAdressTextToValidate, BufferedReader reader) 
            throws IOException {
        //Создадим валидатор
        InetAddressValidator validator = InetAddressValidator.getInstance();
        
        //Создадим переменную для адреса
        //Будем считать, что введенный адрес верен
        String ValidIPAdress = IPAdressTextToValidate;
        
        //Если пустая строка, то вернем адрес по умолчанию
        if (ValidIPAdress.isEmpty())
        {
            return DefaultParameters.IPADDRESS;
        }
        
        //Если адрес не валиден, то запросим его заново
        if(!validator.isValid(IPAdressTextToValidate))
        {
            
            System.out.println("Incorrect IP");
            System.out.println("Input new IP or press Enter to use default IP");
            ValidIPAdress = reader.readLine();
            
            //Если введена пустая строка, то подставим адрес по умолчанию
            if (ValidIPAdress.isEmpty())
            {
                ValidIPAdress = DefaultParameters.IPADDRESS;
            }
            else
            {
                //Если ввели новый адрес, то снова проверим его
                ValidIPAdress = getIPAdressTextWithValidate(ValidIPAdress, reader);
            }
            
        }
        return ValidIPAdress;
    }
    
    //Проверяем введенный порт и требуем вводить его до ввода правильного
    public static int getServerPortWithValidate(String portNumberString, BufferedReader reader) 
            throws IOException
    {
        int Port = 0;
        //Создадим валидатор
        ServerPortValidator validator = ServerPortValidator.getInstance();
        
        if (portNumberString.isEmpty())
        {
             return DefaultParameters.PORT;
        }
        
        //Если порт невалиден, то запросим его снова
        if (!validator.isValid(portNumberString))
        {
            String newPortNumberText;
            
            System.out.println("Incorrect Port");
            System.out.println("Input new Port or press Enter to use default Port");
            
            newPortNumberText = reader.readLine();
            
            //Если ввели новый порт, то снова проверим его
            Port = getServerPortWithValidate(newPortNumberText,reader);
        }
        else
        {
             //Если валиден, то преобразуем текст
             Port = Integer.parseInt(portNumberString);
        }
        
        return Port;
    }
    
    public static String getContentDirectoryPathWithValidate(String contentDirectoryPathTemplate, BufferedReader reader) throws IOException
    {
        String ContentDirectoryPath = contentDirectoryPathTemplate;
        
        ContentDirectoryPathValidator validator = ContentDirectoryPathValidator.getInstance();
        
        if (contentDirectoryPathTemplate.isEmpty())
        {
            return DefaultParameters.CONTENT_DIRECTORY_PATH;
        }
        
        if (!validator.isValid(contentDirectoryPathTemplate))
        {
            System.out.println("Incorrect content directory path");
            System.out.println("Input new path or press Enter to use default path (/www)");
            
            ContentDirectoryPath = reader.readLine();
            
            ContentDirectoryPath = getContentDirectoryPathWithValidate(ContentDirectoryPath,reader);
            
        }
        
        return ContentDirectoryPath;
    }
    
}
