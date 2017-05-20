import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Stack;

/**
 * Created by Konstantin on 25.04.2017.
 */

/**
 * Класс сообщения обмена между интерпретатором и IDE.
 * Предоставляет базовый интерфейс работы с полями: чтение и запись.
 */
public class Message
{
    /* Поля */

    /**
     * Направления указателей CC и DP (по часовой стрелке).
     * TOP = 0
     * RIGHT = 1
     * BOTTOM = 2
     * LEFT = 3
     */
    public final static int TOP = 0,
                            RIGHT = 1,
                            BOTTOM = 2,
                            LEFT = 3;

    /**
     * Режимы запуска интерпретатора.
     */
    public final static int NORMAL = 0,
                            DEBUG = 1;

    /**
     * Стек функций.
     * Каждая функция представляется парой: имя функции и стек ее переменных.
     */
    protected Stack<Pair<String, Stack<Object>>> func_stack = null;

    /**
     * Указатели направления <b>Direction Pointer</b> и <b>Codel Chooser</b>.
     */
    protected int   dp = -1,
                    cc = -1;

    /**
     * Координаты пикселя (от левого верхнего угла).
     */
    protected int   x = -1,
                    y = -1;

    /**
     * Выбранный режим запуска интерпретатора.
     */
    protected int mode = -1;

    /**
     * Строка <b>output</b> будет выведена в консоль (stdout).
     * Строка <b>input</b> была введена пользователем (stdin).
     */
    protected String    output = "",
                        input = "";

    /**
     * Сообщение об ошибке интерпретатора.
     */
    protected String err_msg = "";

    /**
     * Полный путь к файлу исходников в графическом формате.
     */
    protected String filename = "";

    protected msg type = msg.UNKNOWN_MSG;

    /* Константы */

    /**
     * Типы сообщений
     */
    public enum msg {
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
        SHUTDOWN,
        UNKNOWN_MSG
    }

    protected static final String   MESSAGE =       "message",
                                    OUTPUT_TEXT =   "output",
                                    INPUT_TEXT =    "input",
                                    X_COORD =       "x",
                                    Y_COORD =       "y",
                                    ERR_MSG =       "err_msg",
                                    MODE =          "mode",
                                    FILENAME =      "filename",
                                    CC =            "cc",
                                    DP =            "dp";

    /* Конструкторы */

    Message() {}

    Message(String json)
    {
        loadFromJson(json);
    }

    /* Селекторы */

    /**
     * Метод доступа к стеку функций <font color="red">(не переменных!)</font>.
     * @return Возвращает стек, каждый элемент которого - пара из имени функции
     * и стека переменных этой функции <font color="red">(не путать эти стеки!)</font>.
     * Каждый элемент стека переменных функции должен реализовывать метод toString().
     * Все базовые классы это делают.
     */
    public Stack<Pair<String, Stack<Object>>> getStackOfFunctions() { return func_stack; }

    /**
     * Метод доступа к значению указателя <b>CC</b>.
     * @return Возвращает одно из значений {@link MessageInterface#RIGHT} или
     * {@link MessageInterface#LEFT}
     */
    public int getCc() { return cc; }

    /**
     * Метод доступа к значению указателя <b>DP</b>.
     * @return Возвращает одно из значений {@link MessageInterface#TOP},
     * {@link MessageInterface#RIGHT}, {@link MessageInterface#BOTTOM} или
     * {@link MessageInterface#LEFT}
     */
    public int getDp() { return dp; }

    /**
     * Метод доступа к координате <b>X</b> агента (номер пикселя с верхнего левого угла).
     * @return Текущая координата X агента.
     */
    public int getX() { return x; }

    /**
     * Метод доступа к координате <b>Y</b> агента (номер пикселя с верхнего левого угла).
     * @return Текущая координата Y агента.
     */
    public int getY() {return y; }

    /**
     * Метод доступа к режиму работы агента.
     * @return режим работы агента.
     */
    @Deprecated
    public int getMode() {return mode; }

    /**
     * Метод доступа к тексту, который попадает в выходной поток.
     * <b>Метод актуален для стороны интерпретатора, а не IDE!</b>
     * @return Строка, которая должна попасть  в <i>stdout</i>
     */
    public String getOutput() { return output; }

    /**
     * Метод доступа к тексту, который введен пользователем во входной поток.
     * <b>Метод актуален для стороны IDE, а не интерпретатора!</b>
     * @return Строка, которая пришла из <i>stdin</i>
     */
    public String getInput() { return input; }

    /**
     * Метод доступа к сообщению об ошибке, которое необходимо вывести на экран.
     * @return Текст сообщения об ошибке.
     */
    public String getErrorMessage() { return err_msg; }

    /**
     * Метод доступа к имени файла с исходниками.
     * <b>Метод актуален для IDE, а не интерпретатора!</b>
     * @return Строка с полным именем файла.
     */
    @Deprecated
    public String getFilename() { return filename; }

    public msg getType() { return type; }

    /* Модификаторы */

    public void setStack(Stack<Pair<String, Stack<Object>>> stack)
    {
        this.func_stack = stack;
    }

    public void setCC(int cc)
    {
        this.cc = cc;
    }

    public void setDP(int dp)
    {
        this.dp = dp;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    @Deprecated
    public void setMode(int mode)
    {
        this.mode = mode;
    }

    public void setInput(String input)
    {
        this.input = input;
    }

    public void setOutput(String output)
    {
        this.output = output;
    }

    public void setErrorMessage(String err_msg)
    {
        this.err_msg = err_msg;
    }

    @Deprecated
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public void setType(msg m) { this.type = m; }

    public JSONObject toJsonObject()
    {
        JSONObject jso = new JSONObject();
        jso.put(MESSAGE, type.toString());
        switch (type)
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
            {
                jso.put(OUTPUT_TEXT, output);
                return jso;
            }
            case INPUT:
            {
                jso.put(INPUT_TEXT, input);
                return jso;
            }
            case BP_REACHED:
            {
                Stack<Pair<String, Stack<Object>>> stack = this.func_stack;
                if (stack == null) return jso;
                jso.put(X_COORD, x);
                jso.put(Y_COORD, y);
                jso.put(CC, cc);
                jso.put(DP, dp);
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
                if ((x < 0) || (y < 0) || (err_msg.isEmpty())) return jso;
                jso.put(X_COORD, x);
                jso.put(Y_COORD, x);
                jso.put(ERR_MSG, err_msg);
                return jso;
            }
            case START:
            {
                if (filename.isEmpty() || (mode == -1)) return jso;
                jso.put(FILENAME, filename);
                jso.put(MODE, mode);
                return jso;
            }
            case SET_BP:
            case DELETE_BP:
            {
                if ((x < 0) || (y < 0)) return jso;
                jso.put(X_COORD, x);
                jso.put(Y_COORD, y);
                return jso;
            }
        }
        return jso;
    }

    protected void loadFromJson(String json_str)
    {
        msg res_key = msg.UNKNOWN_MSG;
        JSONParser jp = new JSONParser();
        Stack func_stack = new Stack();
        JSONObject jso;
        try
        {
            jso = (JSONObject) jp.parse(json_str);
            for (Object key_obj : jso.keySet())
            {
                String key = (String) key_obj;
                Object value = jso.get(key);
                switch (key)
                {
                    case MESSAGE:
                    {
                        String val = (String) value;
                        for (msg msg_type : msg.values())
                        {
                            if (val.equalsIgnoreCase(msg_type.toString()))
                            {
                                res_key = msg_type;
                                break;
                            }
                        }
                        break;
                    }
                    case OUTPUT_TEXT:
                        setOutput((String) value);
                        break;
                    case INPUT_TEXT:
                        setInput((String) value);
                        break;
                    case X_COORD:
                        setX(((Number)value).intValue());
                        break;
                    case Y_COORD:
                        setY(((Number)value).intValue());
                        break;
                    case ERR_MSG:
                        setErrorMessage((String) value);
                        break;
                    case MODE:
                        setMode(((Number)value).intValue());
                        break;
                    case FILENAME:
                        setFilename((String) value);
                        break;
                    case CC:
                        setCC(((Number)value).intValue());
                        break;
                    case DP:
                        setDP(((Number)value).intValue());
                        break;
                    default:
                        JSONArray var_js_arr = (JSONArray) value;
                        Stack<Object> var_stack = new Stack<>();
                        for (Object var : var_js_arr)
                            var_stack.push(var);
                        Pair<String, Stack<Object>> pair = new Pair<>(key, var_stack);
                        func_stack.add(pair);
                        break;
                }
            }
            type = res_key;
            setStack(func_stack);
        }
        catch (ParseException e)
        {
            System.out.println(e);
        }
    }
}
