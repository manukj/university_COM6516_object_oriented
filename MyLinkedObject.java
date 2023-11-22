class MyLinkedObject {
    private String word;
    private int count;
    private MyLinkedObject next;

    public MyLinkedObject(String word) {
        this.word = word;
        this.count = 1;
        this.next = null;
    }

    public void setNext(MyLinkedObject newNext) {
        this.next = newNext;
    }

    public MyLinkedObject getNext() {
        return next;
    }

    public void seCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public MyLinkedObject clone() {
        MyLinkedObject newLinkedObject = new MyLinkedObject(word);
        newLinkedObject.seCount(count);
        newLinkedObject.setNext(next);
        return newLinkedObject;
    }

    public void setWord(String w) {
        int result = w.compareTo(word);
        if (result == 0) {
            count++;
        } else if (result > 0) {
            if (next == null) {
                next = new MyLinkedObject(w);
            } else {
                next.setWord(w);
            }
        } else {
            MyLinkedObject newLinkedObject = this.clone();
            word = w;
            next = newLinkedObject;
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