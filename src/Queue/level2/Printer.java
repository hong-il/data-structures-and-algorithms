package Queue.level2;

import java.util.*;

class Printer {

    private Queue<Document> queue;
    private int answer;

    public int solution(int[] priorities, int location) {
        queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++)
            queue.offer(new Document(priorities[i], i));
        while (!queue.isEmpty()) {
            Document head = queue.poll();
            if (isPrintable(head)) {
                answer++;
                if (head.index == location)
                    break;
            } else queue.offer(head);
        }
        return answer;
    }

    public boolean isPrintable(Document head) {
        return queue.stream().noneMatch(document -> document.priority > head.priority);
    }

    class Document {
        int priority;
        int index;

        public Document(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }
    }
}
