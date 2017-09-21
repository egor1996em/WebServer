/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import webserver.validators.ParametersValidator;


/**
 *
 * @author User
 * Менеджер параметров сервера
 */
public class ParametersManager {
    public static Parameters CreateServerParametersFromKeyboard() throws UnknownHostException, IOException
    {       
        //Зададим IP
        InetAddress IPadress = GetIPAdressFrmoKeyboard();
        //Зададим порт
        int Port = GetPortFromKeyboard();
        //Зададим домашнюю папку для контента
        String ContentDirectoryPath = GetContentDirectoryPathFromKeyboard();
        Parameters parameters = new Parameters(IPadress, Port, ContentDirectoryPath);
        
        return parameters;
    }
    
    //Создает IP адрес на основе данных с клавиатуры
    private static InetAddress GetIPAdressFrmoKeyboard() throws UnknownHostException, IOException
    {
        String IPAdressText;
        
        System.out.println("Input IP address:");
        System.out.println("Press Enter to use default IP");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        IPAdressText = ParametersValidator.getIPAdressTextWithValidate(reader.readLine(),reader);
        
        InetAddress IPAdress = InetAddress.getByName(IPAdressText);
        
        return IPAdress;
    }
    
    //Задает номер порта введенный с клавиатуры
    private static int GetPortFromKeyboard() throws IOException
    {
        int Port;
        
        System.out.println("Input Port number:");
        System.out.println("Press Enter to use default port number");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String PortNumberText = reader.readLine();
        
        Port = ParametersValidator.getServerPortWithValidate(PortNumberText, reader);
        
        return Port;
    }
    
    private static String GetContentDirectoryPathFromKeyboard() throws IOException
    {      
        System.out.println("Input path to content directory:");
        System.out.println("Press Enter to use default path (www/)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String ContentDirectoryPath = reader.readLine();
        
        ContentDirectoryPath = ParametersValidator.getContentDirectoryPathWithValidate(ContentDirectoryPath, reader);
        
        return ContentDirectoryPath;
    }
    
}
