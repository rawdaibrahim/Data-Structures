package DataStructures;

import java.util.Stack;

public class isMatched {
    public static boolean isMatch(String exp){
        String openning ="({[";
        String closing =")}]";
        Stack<Character> buffer = new Stack<>();
        for (char c : exp.toCharArray()){
            if (openning.indexOf(c)!=-1)
                buffer.push(c);
            else if (closing.indexOf(c)!= -1){
                if (buffer.isEmpty()) return false;
                if (closing.indexOf(c)!= openning.indexOf(buffer.pop())) return false;
            }
        }
        return buffer.isEmpty();
    }
    public static boolean isHtmlMatched(String html){
        Stack<String> buffer = new Stack<>();
        int j= html.indexOf('<');
        while (j != -1){
            int k=html.indexOf('>',j+1);
            if (k == -1)
             return false; // invalid tag
             String tag = html.substring(j+1, k); // strip away < >
            if (!tag.startsWith("/")) // this is an opening tag
                 buffer.push(tag);
            else { // this is a closing tag
                if (buffer.isEmpty( ))
                     return false; // no tag to match
                if (!tag.substring(1).equals(buffer.pop( )))
                     return false; // mismatched tag
            }
            j = html.indexOf('<', k+1); // find next ’<’ character (if any)
        }  return buffer.isEmpty( ); // were all opening tags matched?
    }
}
