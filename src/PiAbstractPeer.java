import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Konstantin on 19.04.2017.
 */
public abstract class PiAbstractPeer
{
    protected DataInputStream inputStream;
    protected DataOutputStream outputStream;

    protected boolean connected = false;

    protected int port;

    protected Socket conn;

    PiAbstractPeer(int port)
    { this.port = port; }

    PiAbstractPeer() { port = 8698; }

    protected void initStreams(Socket conn)
    {
        try
        {
            inputStream = new DataInputStream(conn.getInputStream());
            outputStream = new DataOutputStream(conn.getOutputStream());
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
    public Message receive()
    {
        try
        {
            String inp = inputStream.readUTF();
            return new Message(inp);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public boolean isConnected()
    {
        return connected;
    }

    public boolean disconnect()
    {
        try
        {
            conn.close();
            connected = false;
            return true;
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return false;
    }

}
