import javafx.util.Pair;
import java.util.Stack;

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
     * Метод доступа к значению указателя CC.
     * @return Возвращает одно из значений {@link MessageInterface#RIGHT} или
     * {@link MessageInterface#LEFT}
     */
    int cc();

    /**
     * Метод доступа к значению указателя DP.
     * @return Возвращает одно из значений {@link MessageInterface#TOP},
     * {@link MessageInterface#RIGHT}, {@link MessageInterface#BOTTOM} или
     * {@link MessageInterface#LEFT}
     */
    int dp();
}
