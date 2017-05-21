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
