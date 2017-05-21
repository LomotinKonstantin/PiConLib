/**
 * Created by Konstantin on 20.05.2017.
 */

/**
 * Пример использования:
 *
 * ThreadServer ts = new ThreadServer();
 * Thread serverThread = new Thread(ts);
 * serverThread.start();
 *
 * Этот код запускает ожидание подключение к серверу в другом потоке.
 */
public class ThreadServer extends PiServer implements Runnable
{
    public void run()
    {
        waitForConnection();
    }
}
