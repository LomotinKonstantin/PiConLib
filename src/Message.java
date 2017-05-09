import javafx.util.Pair;
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

    Message() {}

    /**
     * Метод доступа к стеку функций <font color="red">(не переменных!)</font>.
     * @return Возвращает стек, каждый элемент которого - пара из имени функции
     * и стека переменных этой функции <font color="red">(не путать эти стеки!)</font>.
     * Каждый элемент стека переменных функции должен реализовывать метод toString().
     * Все базовые классы это делают.
     */
    public Stack<Pair<String, Stack<Object>>> stackOfFunctions() { return func_stack; }

    /**
     * Метод доступа к значению указателя <b>CC</b>.
     * @return Возвращает одно из значений {@link MessageInterface#RIGHT} или
     * {@link MessageInterface#LEFT}
     */
    public int cc() { return cc; }

    /**
     * Метод доступа к значению указателя <b>DP</b>.
     * @return Возвращает одно из значений {@link MessageInterface#TOP},
     * {@link MessageInterface#RIGHT}, {@link MessageInterface#BOTTOM} или
     * {@link MessageInterface#LEFT}
     */
    public int dp() { return dp; }

    /**
     * Метод доступа к координате <b>X</b> агента (номер пикселя с верхнего левого угла).
     * @return Текущая координата X агента.
     */
    public int x() { return x; }

    /**
     * Метод доступа к координате <b>Y</b> агента (номер пикселя с верхнего левого угла).
     * @return Текущая координата Y агента.
     */
    public int y() {return y; }

    /**
     * Метод доступа к режиму работы агента.
     * @return режим работы агента.
     */
    public int mode() {return mode; }

    /**
     * Метод доступа к тексту, который попадает в выходной поток.
     * <b>Метод актуален для стороны интерпретатора, а не IDE!</b>
     * @return Строка, которая должна попасть  в <i>stdout</i>
     */
    public String output() { return output; }

    /**
     * Метод доступа к тексту, который введен пользователем во входной поток.
     * <b>Метод актуален для стороны IDE, а не интерпретатора!</b>
     * @return Строка, которая пришла из <i>stdin</i>
     */
    public String input() { return input; }

    /**
     * Метод доступа к сообщению об ошибке, которое необходимо вывести на экран.
     * @return Текст сообщения об ошибке.
     */
    public String errorMessage() { return err_msg; }

    /**
     * Метод доступа к имени файла с исходниками.
     * <b>Метод актуален для IDE, а не интерпретатора!</b>
     * @return Строка с полным именем файла.
     */
    public String filename() { return filename; }

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

    public void setFilename(String filename)
    {
        this.filename = filename;
    }
}
