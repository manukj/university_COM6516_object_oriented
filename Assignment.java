/**
 * Assignment
 */
public class Assignment {

    public static void main(String[] args) {
        MyLinkedObject list = new MyLinkedObject("d");
        list.setWord("d");
        list.setWord("b");
        list.setWord("c");
        list.setWord("c");
        list.setWord("d");
        list.setWord("a");
        list.setWord("a");
     
        
        //print the list by iterating through it
        MyLinkedObject current = list;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }
}