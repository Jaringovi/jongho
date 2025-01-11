import java.io.*;
import java.util.*;

public class Main {
    StringTokenizer tokens;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    int T,N;

    // 트라이노드 클래스
    class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    // 트라이 클래스
    class Trie{
        private TrieNode root;

        // 트라이 초기화
        public Trie() {
            root = new TrieNode();
        }

        // 단어 삽입
        boolean insert(String word){
            TrieNode current = root;
            for(char c : word.toCharArray()) {
                if(current.isEndOfWord){
                    return false;
                }
                
                // 현재 노드에 자식이 없으면 새로 추가
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
            }
            if(!current.children.isEmpty()){
                return false;
            }
            // 단어의 끝 표시
            current.isEndOfWord = true;
            return true;
        }
    }

    void solve() throws IOException {
        T = Integer.parseInt(input.readLine());
        for(int i = 0; i<T; i++){
            boolean flag = true;
            Trie trie = new Trie();
            N = Integer.parseInt(input.readLine());
            for(int j = 0; j<N; j++){
                String callnumber = input.readLine();
                if(!trie.insert(callnumber))
                    flag = false;
            }
            if (flag) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }
}

