import com.sun.istack.internal.Nullable;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

/**
 * Created by Konstantin on 19.04.2017.
 */
public abstract class PiAbstractPeer
{
    protected enum msg {
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

    protected static JSONObject formJson(msg m)
    {
        JSONObject jso = new JSONObject();
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
                jso.put("message", m.toString());
                return jso;
        }
        return jso;
    }

    @Nullable
    protected static JSONObject formJson(msg m, Message data)
    {
        JSONObject jso = new JSONObject();
        jso.put("message", m.toString());
        switch (m)
        {
            case OUTPUT:
            {
                String out = data.output();
                if (out.isEmpty()) return jso;
                jso.put("output", out);
                return jso;
            }
            case INPUT:
            {
                String inp = data.input();
                if (inp.isEmpty()) return jso;
                jso.put("input", inp);
                return jso;
            }
            case BP_REACHED:
            {
                int x = data.x();
                int y = data.y();
                Stack<Pair<String, Stack<Object>>> stack = data.stackOfFunctions();
                if ((x < 0) || (y < 0) || (stack == null)) return jso;
                jso.put("x", x);
                jso.put("y", y);
                for (Pair<String, Stack<Object>> p : stack)
                {
                    Stack<Object> vars = p.getValue();
                    String key = p.getKey();
                    JSONArray js_st = new JSONArray();
                    for (Object v : vars)
                        js_st.add(v);
                    jso.put(key, vars);
                }
                return jso;
            }
            case SYNTAX_ERROR:
            {
                int x = data.x();
                int y = data.y();
                String err_msg = data.errorMessage();
                if ((x < 0) || (y < 0) || (err_msg.isEmpty())) return jso;
                jso.put("x", x);
                jso.put("y", x);
                jso.put("err_msg", err_msg);
                return jso;
            }
            case START:
            {
                String filename = data.filename();
                int mode = data.mode();
                if (filename.isEmpty() || (mode == -1)) return jso;
                jso.put("filename", filename);
                jso.put("mode", mode);
                return jso;
            }
            case SET_BP:
            case DELETE_BP:
            {
                int x = data.x();
                int y = data.y();
                if ((x < 0) || (y < 0)) return jso;
                jso.put("x", x);
                jso.put("y", y);
                return jso;
            }
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

    }
}
