/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.IOException;
import java.net.UnknownHostException;
import webserver.filework.FileManager;
import webserver.parameters.Parameters;
import webserver.parameters.ParametersManager;

/**
 *
 * @author User
 */
public class HttpServer {

    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        Parameters serverParameters = ParametersManager.CreateServerParametersFromKeyboard();
        FileManager.CreateFolderIfNotExist(serverParameters.getContentDirectoryPath());
        RequestListener listener = new RequestListener(serverParameters);
        listener.StartListen();
        
    }
    
}
