package Queue.level2;

import java.util.*;

class Printer {

    private Queue<Document> queue;
    private int answer;

    class Document {
        int priority;
        int index;

        public Document(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }
    }
}
