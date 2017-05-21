import java.io.IOException;
import java.net.Socket;

/**
 * Created by Konstantin on 20.05.2017.
 */

/**
 * Пример использования:
 *
 * PiClient client = new PiClient();
 * client.connect();
 * // Если соединение не удалось, в консоль будет выведен текст эксепшена.
 * // Если все в порядке, то...
 * Message message = new Message();
 * // Наполнение сообщения, например...
 * message.setType(Message.types.OK);
 * client.send(message);
 * // Отправили. Теперь ждем ответа. Функция может работать с задержкой.
 * Message response = client.receive();
 *
 */
public class PiClient extends PiAbstractPeer
{
    protected Socket conn;

    PiClient()
    {
        super();
    }

    PiClient(int port)
    {
        super(port);
    }

    public boolean connect()
    {
        try
        {
            conn = new Socket("127.0.0.1", port);
            initStreams(conn);
            connected = true;
//            System.out.println("PiClient connected!");
            return true;
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        return false;
    }
}
