import javafx.util.Pair;

import java.util.Stack;

/**
 * Created by Konstantin on 19.05.2017.
 */
public class test
{
    public static void main(String[] argv)
    {
        for (Message.msg m : Message.msg.values())
        {
            Message message = new Message();
            message.x = 4;
            message.y = 2;
            message.err_msg = "Unknown Error! ☺♀";
            message.input = "Input text";
            message.output = "Output text";
            message.func_stack = new Stack<>();
            for (int i = 0; i < 3; ++i)
            {
                Pair<String, Stack<Object>> pair;
                Stack<Object> st = new Stack<>();
                st.add(new Integer(i * 2));
                st.add(new Float(i * 1.5));
                st.add(new Boolean(i > 1));
                pair = new Pair<>("func_" + i, st);
                message.func_stack.add(pair);
            }
            message.cc = Message.BOTTOM;
            message.dp = Message.LEFT;
            message.filename = "./src.gif";
            message.mode = Message.DEBUG;
            message.type = m;

            String json_string = message.toJsonObject().toJSONString();
            System.out.println("To JSON:\n" + json_string + "\n");
            Message json_message = new Message(json_string);
            System.out.println("From JSON:");
            System.out.println("X: " + json_message.getX());
            System.out.println("Y: " + json_message.getY());
            System.out.println("CC: " + json_message.getCc());
            System.out.println("DP: " + json_message.getDp());
            System.out.println("Input: " + json_message.getInput());
            System.out.println("Output: " + json_message.getOutput());
            System.out.println("Error message: " + json_message.getErrorMessage());
            System.out.println("Filename: " + json_message.getFilename());
            System.out.println("Mode: " + json_message.getMode());
            System.out.println("Type: " + json_message.getType());
            System.out.println("Stack:");
            for (Pair<String, Stack<Object>> function : json_message.getStackOfFunctions())
            {
                System.out.println("\t" + function.getKey() + ":");
                for (Object var : function.getValue())
                    System.out.println("\t\t" + var);
            }
            System.out.println("---------------------------------");
        }
    }
}
