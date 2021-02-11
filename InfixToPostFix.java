import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Exception;


public class InfixToPostFix  {
    public static void main(String[] args) {
        System.out.println("Enter an Infix: ");
        Scanner input = new Scanner(System.in);
        System.out.println(postFix(input.next()));
    }


    //method that checks the rules to be followed in converting infix to postfix
    public static String postFix(String expression)  {
        Stack<Character> resultStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        //breaks the string into array of Strings
        expression = fillBlanks(expression);
        String []token = expression.split(" ");

        for(String tokens: token) {
            if(tokens.length()==0)
                continue;

            //push operator enclosed in () resultStack
            else if (tokens.trim().charAt(0)==')'){
                while (operatorStack.peek()!='(') {
                    resultStack.push(operatorStack.pop());
                }
                operatorStack.pop();
            }
            //push operator ( into the resultStack
            else if (tokens.trim().charAt(0)=='(') {
                operatorStack.push(tokens.charAt(0));
            }


            //checks if the token is equal to the highest priority and pops the
            //stack and push the token into the operator stack
            else if (tokens.charAt(0)=='*'||tokens.charAt(0)=='/') {
                if (operatorStack.isEmpty()){
                    operatorStack.push(tokens.charAt(0));
                }
                else if (operatorStack.peek() == '*' || operatorStack.peek() == '/') {
                    //throw exception if the emptystackexception occur
                    try {
                        while (operatorStack.peek() == '*' || operatorStack.peek() == '/') {
                            resultStack.push(operatorStack.pop());
                        }
                    }
                    catch (EmptyStackException e){}
                   finally{
                        operatorStack.push(tokens.charAt(0));
                    }
                    ;
                }
                else {
                    operatorStack.push(tokens.charAt(0));
                }

                }
            //checks if the token is equal or less than to the highest priority and pops the
            //stack and push the token into the operator stack
            else if (tokens.charAt(0)=='+'||tokens.charAt(0)=='-'){
                if (operatorStack.isEmpty()) {
                    operatorStack.push(tokens.charAt(0));
                }

                else if (operatorStack.peek()=='*'||operatorStack.peek()=='/'||
                        operatorStack.peek()=='+'||operatorStack.peek()=='-'){
                    //throw exception if the emptystackexception occur
                    try{
                    while(operatorStack.peek()=='*'||operatorStack.peek()=='/'||
                            operatorStack.peek()=='+'||operatorStack.peek()=='-')
                    {resultStack.push(operatorStack.pop());

                    }}
                    catch (EmptyStackException e){}
                    finally {
                        operatorStack.push(tokens.charAt(0));
                    }
                }
                else {
                    operatorStack.push(tokens.charAt(0));
                }
            }
            else{
                resultStack.push(tokens.charAt(0));
            }


        }
        while(!operatorStack.isEmpty()) {
            resultStack.push(operatorStack.pop());
        }
        expression = String.valueOf(resultStack);
        return expression;
    }

    public static String fillBlanks(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
                    s.charAt(i) == '+' || s.charAt(i) == '-' ||
                    s.charAt(i) == '/' || s.charAt(i) == '*') {
                result += " " + s.charAt(i) + " ";
            } else
                result += s.charAt(i);
        }
        return result;
    }
}