import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Konstantin on 21.04.2017.
 */

/**
 * Пример использования:
 *
 * PiServer server = new PiServer();
 * server.waitForConnection();
 * // Если соединение не удалось, в консоль будет выведен текст эксепшена.
 * // Если все в порядке, то...
 * Message message = new Message();
 * // Наполнение сообщения, например...
 * message.setType(Message.types.OK);
 * server.send(message);
 * // Отправили. Теперь ждем ответа. Функция может работать с задержкой.
 * Message response = server.receive();
 *
 */
public class PiServer extends PiAbstractPeer
{
    protected ServerSocket serverSocket;
    protected int timeout = 3000;

    PiServer(int port)
    {
        super(port);
    }

    PiServer()
    {
        super();
    }

    public void setTimeout(int t)
    {
        timeout = t;
    }

    public boolean waitForConnection()
    {
        try
        {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(timeout);
            conn = serverSocket.accept();
            initStreams(conn);
            connected = true;
            return true;
        }
        catch (IOException e)
        {
            conn = null;
            serverSocket = null;
            printException(e);
        }
        return false;
    }
}
