import java.util.Stack;

public class HTMLChecker {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            if (charArray[i] == '<') {
                if (charArray[i + 1] != '/') { // if it is an opening tag
                    stack.push(charArray[i + 1]);
                    i += 3;
                } else {   // if it is a closing tag
                    if (stack.empty()) {  // if there is no opening tag in the stack
                        System.out.print("Unexpected closing tag </" + charArray[i + 2] + ">.\n");
                        return false;
                    }
                    if (stack.peek() == charArray[i + 2]) { // if closing tag corresponds to opening tag in the stack
                        stack.pop();
                        i += 4;
                    } else {   // if closing tag does not correspond to given opening tag in the stack
                        System.out.print("Expected closing tag </" + stack.peek() + "> but got </" + charArray[i + 2] + "> instead.\n");
                        return false;
                    }
                }
            } else i++; // if it is not a tag, move to next character
        }
        if (stack.empty()) { // if all opening tags closed properly, stack is empty so HTML is valid
            System.out.print("Tags are correct.\n");
            return true;
        } else { // if an opening tag exists in the stack it means it was not closed by a closing tag
            System.out.print("The tag <" + stack.peek() + "> not closed.\n");
            return false;
        }
        // Notes:
        // if two or more opening tags exist in the stack my code does not give an information about that
        // if opening or closing tags are not properly written (e.g <w/a>) my code won't run properly
    }
}
