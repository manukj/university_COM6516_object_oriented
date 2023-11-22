class MyLinkedObject {
    private String word;
    private int count;
    private MyLinkedObject next;

    public MyLinkedObject(String word) {
        this.word = word;
        this.count = 1;
        this.next = null;
    }

    public MyLinkedObject getNext() {
        return next;
    }

    public setNext(MyLinkedObject newNext) {
        this.next = newNext;
    }

    public void setWord(String w) {
        int result = word.compareTo(w);
        if (result == 0) {
            count++;
        } else if (result > 0) {
            MyLinkedObject newLinkedObject = new MyLinkedObject(word);
            newLinkedObject.setNext(next);
            next = newLinkedObject;
            word = w;
        } else {
            if (next == null) {
                next = new MyLinkedObject(w);
            } else {
                next.setWord(w);
            }
        }
    }

    public int compareTo(String w) {
        return this.word.compareTo(w);
    }

    @Override
    public String toString() {
        return "word = " + word + " and count = " + count;
    }
}