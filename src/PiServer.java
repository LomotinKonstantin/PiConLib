//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//
///**
// * Created by Konstantin on 21.04.2017.
// */
//public class PiServer extends PiAbstractPeer
//{
//    private ServerSocket soc;
//    private Socket con;
//    private DataInputStream inps;
//    private DataOutputStream outs;
//
//    PiServer(int port) { super(port); }
//
//    PiServer() { super(); }
//
//    private void init()
//    {
//        try
//        {
//            soc = new ServerSocket(port);
//            con = soc.accept();
//            inps = new DataInputStream(con.getInputStream());
//            outs = new DataOutputStream(con.getOutputStream());
//        }
//        catch (IOException e)
//        {
//            System.out.println(e);
//        }
//    }
//
//    public void waitForConnection()
//    {
//        init();
//    }
//
//    public void send(msg m, ArrayList<Object> arg)
//    {
//        String json = formJson(m, arg).toString();
//    }
//}
