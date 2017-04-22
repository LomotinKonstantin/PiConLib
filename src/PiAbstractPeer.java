import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        START,
        STEP,
        SET_BP,
        DELETE_BP,
        CLEAR,
        CONTINUE,
        DIE,
        SHUTDOWN
    }

    protected int port;


    PiAbstractPeer(int port) { this.port = port; }

    PiAbstractPeer() { port = 8698; }

    protected static JSONObject formJson(msg m, ArrayList<Object> arg)
    {
        JSONObject jso = new JSONObject();
        jso.put("message", m.toString());
        switch (m)
        {
            case OK:
            case ERR:
            case WAIT4INPUT:
            case SUCCESS:
            case STEP:
            case CLEAR:
            case DIE:
            case SHUTDOWN:
            case CONTINUE:
                return jso;
            case OUTPUT:
                if (arg.size() < 1) return jso;
                jso.put("output", arg.get(0));
                return jso;
            case INPUT:
                if (arg.size() < 1) return jso;
                jso.put("input", arg.get(0));
                return jso;
            case BP_REACHED:
                if (arg.size() < 3) return jso;
                jso.put("x", arg.get(0));
                jso.put("y", arg.get(1));
                for (Object i : ((Map<String, Object>)arg.get(2)).keySet())
                    jso.put(i, ((Map<String, Object>) arg.get(2)).get(i));
                return jso;
            case SYNTAX_ERROR:
                if (arg.size() < 3) return jso;
                jso.put("x", arg.get(0));
                jso.put("y", arg.get(1));
                jso.put("err_msg", arg.get(2));
                return jso;
            case START:
                if (arg.size() < 2) return jso;
                jso.put("filename", arg.get(0));
                jso.put("mode", arg.get(1));
                return jso;
            case SET_BP:
            case DELETE_BP:
                if (arg.size() < 2) return jso;
                jso.put("x", arg.get(0));
                jso.put("y", arg.get(1));
                return jso;
        }
        return null;
    }

    static protected HashMap<String, Object> parseJson(String json)
    {
        HashMap<String, Object> res = new HashMap<>();
        JSONParser jp = new JSONParser();
        try
        {
            return (HashMap<String, Object>) jp.parse(json);

        }
        catch (ParseException e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] argv)
    {
        ArrayList<Object> arg = new ArrayList();
        arg.add(new Integer(120));
        arg.add(new Integer(300));
        Map<String, Object> map = new HashMap();
        ArrayList<Double> st = new ArrayList();
        st.add(new Double(3.14));
        st.add(new Double(2.7));
        st.add(new Double(0.001));
        map.put("Stack", st);
        arg.add(map);
        for (msg it : msg.values())
            System.out.println(formJson(it, arg).toString() + "\n");
        map = parseJson(formJson(msg.BP_REACHED, arg).toString());
        for (String it : map.keySet())
        {
            System.out.println(it + " " + map.get(it) + " " + map.get(it).getClass());
            if (it.equalsIgnoreCase("err_msg"))
            {
                HashMap<String, Object> map1;
                map1 = parseJson(map.get(it).toString());
                for (String j : map1.keySet())
                    System.out.println("\t" + it + " " + map1.get(it) + " " + map1.get(it).getClass());
            }
        }
    }
}
