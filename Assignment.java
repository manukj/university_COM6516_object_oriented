/**
 * Assignment
 */
public class Assignment {

    public static void main(String[] args) {
        MyLinkedObject list = new MyLinkedObject("aa");
        list.setWord("aaa");
        list.setWord("aab");
        list.setWord("hello");

     
        
        //print the list by iterating through it
        MyLinkedObject current = list;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }
}