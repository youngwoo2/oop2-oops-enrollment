package enrollment.client.network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    Socket socket;
    BufferedReader reader;
    BufferedWriter writer;
    public TCPClient(String serverIP, int portNum) {
        try{
            this.socket = new Socket(serverIP, portNum);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public boolean send(String msg){
       try{
           this.writer.write(msg);
           this.writer.flush();
           return true;
       }catch(IOException e){
           return false;
       }
    }

    public String receive(){
        //[TODO] 메시지가 도착한 것을 어떻게 확인하고 읽을 것인지.
        try{
            String msg = this.reader.readLine();
            while(msg==null) {
                msg = this.reader.readLine();
            }
            return msg;
        }catch(IOException e){
            return null;
        }
    }

    public void terminate(){
        try {
            this.reader.close();
            this.writer.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("TCP 연결 해제 실패");
        }
        System.out.println("버퍼 해제 완료.");
    }

}
