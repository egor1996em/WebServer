/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.parameters;

import java.net.InetAddress;

/**
 *
 * @author User
 * Параметры сервера
 */

public class Parameters {
    
    public Parameters(InetAddress IPAdress, int port, String contentDirectoryPath)
    {
        this.IPAdres = IPAdress;
        this.Port = port;
        this.ContentDirectoryPath = contentDirectoryPath;
    }
    
    //IP адрес
    private InetAddress IPAdres;
    
    public InetAddress getIPAdress()
    {
        return this.IPAdres;
    }
    
    public void setIPAdress(InetAddress newIPAdress)
    {
        this.IPAdres = newIPAdress;
    }
    //Порт
    private int Port;
    
    public int getPort()
    {
        return this.Port;
    }
    
    public void setPort(int newPort)
    {
        this.Port = newPort;
    }
    
    //Домашняя дериктория
    private String ContentDirectoryPath;
    
    public String getContentDirectoryPath()
    {
        return this.ContentDirectoryPath;
    }
    
    public void setContentDirectoryPath(String newDirectory)
    {
        this.ContentDirectoryPath = newDirectory;
    }
    
    public String getContentDirectoryName()
    {
        String [] path = this.ContentDirectoryPath.split("/");
        return  path[path.length - 1];
    }
    
}
