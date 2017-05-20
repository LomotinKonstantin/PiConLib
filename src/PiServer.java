import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Konstantin on 21.04.2017.
 */
public class PiServer extends PiAbstractPeer
{
    protected ServerSocket serverSocket;
    protected Socket conn;
    protected DataInputStream inputStream;
    protected DataOutputStream outputStream;
    protected boolean connected;

    PiServer(int port)
    {
        super(port);
    }

    PiServer()
    {
        super();
        connected = false;
    }

    public void start()
    {
        try
        {
            serverSocket = new ServerSocket(port);
            conn = serverSocket.accept();
            inputStream = new DataInputStream(conn.getInputStream());
            outputStream = new DataOutputStream(conn.getOutputStream());
            connected = true;
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public boolean send(Message msg)
    {
        if (!connected)
            return false;
        String jsonMessage = msg.toJsonObject().toJSONString();
        try
        {
            outputStream.writeUTF(jsonMessage);
            outputStream.flush();
            return true;
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Работает с задержкой
     * @return null в случае ошибки, иначе - посылку в UTF-8
     */
    public String receive()
    {
        try
        {
            String inp = inputStream.readUTF();
            return inp;
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return null;
    }
}
