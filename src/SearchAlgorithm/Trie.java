package SearchAlgorithm;

/*
* 가사 단어의 제한사항
 - words의 길이(가사 단어의 개수)는 2 이상 100,000 이하이다.
 - 각 가사 단어의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없다.
 - 전체 가사 단아 길이의 합은 2 이상 1,000,000 이하이다.
 - 가사에 동일 단어가 여러 번 나올 경우 중복을 제거하고 words에는 하나로만 제공된다.
 - 각 가사 단어는 오직 알파벳 소문자로만 구성되어 있으며, 특수문자나 숫자는 포함하지 않는 것으로 가정한다.

* 검색 키워드 제한사항
 - queries의 길이(검색 키워드 개수)는 2 이상 100,000 이하이다.
 - 각 검색 키워드의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없다.
 - 전체 검색 키워드 길이의 합은 2 이상 1,000,000 이다.
 - 검색 키워드는 중복될 수도 있다.
 - 각 검색 키워드는 오직 알파벳 소문자와 와일드카드 문자인 '?' 로만 구성되어 있으며, 특수문자나 숫자는 포함하지 않는 것으로 가정한다.
 - 검색 키워드는 와일드카드 문자인 '?'가 하나 이상 포함돼 있으며, '?'는 각 검색 키워드의 접두사 아니면 접미사 중 하나로만 주어진다.
* */
class Solution {
    public class Trie {
        Trie[] child = new Trie[26];
        int count;
        int aletter = Character.getNumericValue('a');

        void insert(String str) {
            Trie curr = this;
            for (char ch : str.toCharArray()) {
                curr.count++;
                // a = 0, b = 1
                int idx = Character.getNumericValue(ch) - aletter;
                if (curr.child[idx] == null)
                    curr.child[idx] = new Trie();

                curr = curr.child[idx];
            }

            curr.count++;
        }

        int search(String str) {
            Trie curr = this;
            for (char ch : str.toCharArray()) {
                if (ch == '?') return curr.count;

                curr = curr.child[Character.getNumericValue(ch) - aletter];
                if (curr == null)
                    return 0;
            }
            // 문제 조건상 이 리턴이 발생할 일은 없음
            return curr.count;
        }
    }

    Trie[] TrieRoot = new Trie[10000];
    Trie[] ReTrieRoot = new Trie[10000];

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        int ansIdx = 0;

        for (String str : words) {
            // TrieRoot 또는 ReTrieRoot 의 인덱스 0 ~ 9999까지
            int idx = str.length() - 1;
            if (TrieRoot[idx] == null) {
                TrieRoot[idx] = new Trie();
                ReTrieRoot[idx] = new Trie();
            }

            TrieRoot[idx].insert(str);
            str = new StringBuilder(str).reverse().toString();
            ReTrieRoot[idx].insert(str);
        }

        for (String str : queries) {
            int idx = str.length() - 1;
            // 빈 공백
            if (TrieRoot[idx] == null) {
                answer[ansIdx++] = 0;
                continue;
            }

            if (str.charAt(0) != '?') {
                answer[ansIdx++] = TrieRoot[idx].search(str);
            } else {
                str = new StringBuilder(str).reverse().toString();
                answer[ansIdx++] = ReTrieRoot[idx].search(str);
            }
        }

        return answer;
    }
}
