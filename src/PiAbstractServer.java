/**
 * Created by Konstantin on 19.04.2017.
 */
public abstract class PiAbstractServer extends Thread
{
    protected enum msg {
        OK,
        ERR,
        WAIT4INPUT,
        OUTPUT,
        BP_REACHED,
        SYNTAX_ERROR,
        SUCCESS,
    }

    protected int port;

    PiAbstractServer(int port) { this.port = port; }

    PiAbstractServer() { port = 8698; }
}
