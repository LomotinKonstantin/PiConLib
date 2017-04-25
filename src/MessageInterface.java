import javafx.util.Pair;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Konstantin on 25.04.2017.
 */

/**
 * Интерфейс сообщения обмена между интерпретатором и IDE.
 */
public interface MessageInterface
{
    /**
     * Направления указателей CC и DP (по часовой стрелке).
     * TOP = 0
     * RIGHT = 1
     * BOTTOM = 2
     * LEFT = 3
     */
    int     TOP = 0,
            RIGHT = 1,
            BOTTOM = 2,
            LEFT = 3;

    /**
     * Метод доступа к стеку функций <font color="red">(не переменных!)</font>.
     * @return Возвращает стек, каждый элемент которого - пара из имени функции
     * и стека переменных этой функции <font color="red">(не путать эти стеки!)</font>.
     * Каждый элемент стек переменных функции должен реализовывать метод toString().
     * Все базовые классы это делают.
     */
    Stack<Pair<String, Stack<Object>>> stackOfFunctions();

    /**
     * Метод доступа к значению указателя <b>CC</b>.
     * @return Возвращает одно из значений {@link MessageInterface#RIGHT} или
     * {@link MessageInterface#LEFT}
     */
    int cc();

    /**
     * Метод доступа к значению указателя <b>DP</b>.
     * @return Возвращает одно из значений {@link MessageInterface#TOP},
     * {@link MessageInterface#RIGHT}, {@link MessageInterface#BOTTOM} или
     * {@link MessageInterface#LEFT}
     */
    int dp();

    /**
     * Метод доступа к координате <b>X</b> агента (номер пикселя с верхнего левого угла).
     * @return Текущая координата X агента.
     */
    int x();

    /**
     * Метод доступа к координате <b>Y</b> агента (номер пикселя с верхнего левого угла).
     * @return Текущая координата Y агента.
     */
    int y();

    /**
     * Метод доступа к режиму работы агента.
     * @return режим работы агента.
     */
    String mode();
}
