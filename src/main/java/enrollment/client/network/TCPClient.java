package enrollment.client.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    Socket socket;
    InputStreamReader reader;
    OutputStreamWriter writer;
    public TCPClient(String serverIP, int portNum) {
        try{
            this.socket = new Socket(serverIP, portNum);
            this.reader = new InputStreamReader(socket.getInputStream());
            this.writer = new OutputStreamWriter(socket.getOutputStream());
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
            StringBuffer strBuf = new StringBuffer();
            strBuf.append(this.reader.read());
            return strBuf.toString();
        }catch(IOException e){
            return "";
        }
    }

    public void terminate()throws IOException{
        this.reader.close();
        this.writer.close();
        System.out.println("버퍼 해제 완료.");
    }

}
