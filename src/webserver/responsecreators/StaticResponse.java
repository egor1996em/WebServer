/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.responsecreators;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import webserver.HttpServer;
import java.util.Date;
import webserver.filework.FileGetter;
/**
 *
 * @author User
 */
//Класс создания статического ответа
public class StaticResponse implements Runnable{
    
    public StaticResponse(Socket clientSocket, String path) throws IOException
    {
        this.socket = clientSocket;
        this.path = path;
        //Инициализуем поток ввода
        inizialize();
    }
    
    //Инициализация потоков ввода и вывода
    private void inizialize() throws IOException
    {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }
    //Запуск обработки в потоке
    @Override
    public void run() {
        try {
            sendResponse();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    //Получаем шапку запроса
    private String readHeader() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        while (true) {            
           line = reader.readLine();
           if (line == null || line.isEmpty()) {
               break;
           }
           builder.append(line).append(System.getProperty("line.separator"));
        }
        return builder.toString();  
    }
    
    //Получаем имя ресурса
    private String getURIfromHeader(String header)
    {
        String uri;
        int startURIIndex = header.indexOf(" ") + 1;
        int endURIIndex = header.indexOf(" ", startURIIndex);
        uri = header.substring(startURIIndex, endURIIndex);
        int parametersIndex = header.indexOf("?");
        if (parametersIndex != -1)
        {
            uri = uri.substring(0,parametersIndex);
        }
        return uri; 
    }
    
    //Создаем url для ресурса
    private String createResourceURL(String uri)
    {
       return path + uri;
    }
    
    //Получаем ресурс по идентификатору
    private InputStream getResourseByURL(String url) throws FileNotFoundException, IOException
    {
        InputStream response = FileGetter.getFileByURL(url);
        return response;
    }
    
    //Создаем заголовок ответа
    private String createResponseHeader(int code)
    {
        StringBuilder buffer = new StringBuilder();
        switch(code) {
            case 200 : 
                buffer.append("HTTP/1.1 ").append(code).append(" OK \n");
                break;
            case 404 :
                buffer.append("HTTP/1.1 ").append(code).append(" Not found \n");
                break;
            default:
                buffer.append("HTTP/1.1 ").append(" Internal Server Error \n");
        }
        
        buffer.append("Date: ").append(new Date().toString()).append("\n");
        buffer.append("Accept-Ranges: none\n");
        buffer.append("Content-Type: text/html\n");
        buffer.append("\n");
        buffer.append("\n");
        
        return  buffer.toString();
    }
    
    private InputStream createResponseBody() throws IOException
    {
        //ПРочитаем заголовок запроса
        String requestHeader = readHeader();
        //Найдем идентификатор ресурсов
        String uri = getURIfromHeader(requestHeader);
        //Получаем URL
        InputStream file = getResourseByURL(createResourceURL(uri));
        
        return file; 
    }
    
    //Отправим ответ
    private void sendResponse() throws UnsupportedEncodingException, IOException
    {
        PrintStream response = new PrintStream(outputStream, true, "UTF-8");
        InputStream file = createResponseBody();
        int code = file != null ? 200 : 404;
        String responseHeader = createResponseHeader(code);
        response.print(responseHeader);
        
        if (code == 200)
        { 
            int count;
            byte[] buffer = new byte[1024];
            while((count = file.read(buffer)) != -1) 
            {
                 outputStream.write(buffer, 0, count);
            }
            file.close();
        
        }
    }
    
    private final Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private final String path;
}
