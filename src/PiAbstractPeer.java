/**
 * Created by Konstantin on 19.04.2017.
 */
public abstract class PiAbstractPeer extends Thread
{
    protected static enum msg {
        OK,
        ERR,
        WAIT4INPUT,
        OUTPUT,
        INPUT,
        BP_REACHED,
        SYNTAX_ERROR,
        SUCCESS,
    }

    protected int port;


    PiAbstractPeer(int port) { this.port = port; }

    PiAbstractPeer() { port = 8698; }
}
