import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Konstantin on 21.04.2017.
 */
public class PiServer extends PiAbstractPeer
{
    protected ServerSocket serverSocket;
    protected boolean connected;

    PiServer(int port)
    {
        super(port);
    }

    PiServer()
    {
        super();
    }

    public boolean waitForConnection()
    {
        try
        {
            serverSocket = new ServerSocket(port);
//            System.out.println("PiServer is waiting for connection...");
            conn = serverSocket.accept();
            initStreams(conn);
            connected = true;
//            System.out.println("PiServer accepted connection");
            return true;
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return false;
    }
}
