/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import webserver.parameters.Parameters;
import webserver.responsecreators.StaticResponse;

/**
 *
 * @author User
 * Прослушиватель запросов
 */
public class RequestListener {
    
    public RequestListener(Parameters serverParameters)
    {
        this.parameters = serverParameters;
    }
    //IPAdress
    private final Parameters parameters;
    //Запуск сервера
    public void StartListen() throws IOException
    {
        //Попробуем запустить сервер
        try {
            //Создадим сокет с указанным адрессом на заданном порту
            serverSocket = new ServerSocket(parameters.getPort(),0,parameters.getIPAdress());
            System.out.println("Server " + parameters.getIPAdress().toString() + " started at port " + parameters.getPort());
        } catch (IOException e) {
            //Если сервер создать не удалось, то выведем номер порта и ошибку
            System.out.println("Port " + parameters.getPort() + " is blocked");
            System.out.println(e.getMessage());
            //Завершим работу приложения
            System.exit(-1);
        }
        
        Listening();
    }
    //Прослушивание порта
    private void Listening()
    {
        //Вводим программу в бесконечный цикл
        while(true)
        {
            //Пробуем создать сокет для клиента
            try {
                //Как только подключится клиент, создадим для него обычный сокет
                Socket clientSocket = serverSocket.accept();
                StaticResponse clientResponse = new StaticResponse(clientSocket, parameters.getContentDirectoryPath());
                new Thread(clientResponse).start();
            } catch (IOException e) {
                System.out.println("Field to establish connection");
                System.out.println(e.getMessage());
            }
        }
    }
    
    private ServerSocket serverSocket;
}
