//Backtracking
93. Restore IP Addresses
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() < 4 || s.length() > 12) return res;
        helper(s,0,4,res,new StringBuilder());
        return res;

    }
    public void helper(String s, int start, int set, List<String> res, StringBuilder sb){
        if(start > s.length() || s.length()-start < set || s.length()-start > set*3){
            return;
        }else if(start == s.length()){
            sb.setLength(sb.length()-1);
            res.add(sb.toString());
            return;
        }
        int len = sb.length();
        for(int i = 1; i <= 3; i++){
            if(start+i <= s.length()
            &&(i!=3 || Integer.parseInt(s.substring(start,start+i))<256)
            &&(i==1 || Integer.parseInt(s.substring(start,start+1))!=0)){
                sb.append(s.substring(start,start+i)+".");
                helper(s,start+i,set-1,res,sb);
                sb.setLength(len);
            }
        }
    }
}

216. Combination Sum III
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res,new ArrayList<Integer>(), k, n, 1);
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> list, int k, int n, int start){
        if(list.size() == k && n == 0){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = start; i <= 9; i++){
            list.add(i);
            helper(res,list,k,n-i,i+1);
            list.remove(list.size()-1);
        }
    }
}

//Trie
208. Implement Trie (Prefix Tree)
class TrieNode{
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];
        public TrieNode(){}
        TrieNode(char c){
            this.val = c;
        }
}
public class Trie {
    private TrieNode root;
    /** Initialize your data structure here. */
   
    public Trie(){
        root = new TrieNode();
        root.val = ' ';
    }

    
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c-'a'] == null){
                ws.children[c-'a'] = new TrieNode(c);
            }
            ws = ws.children[c-'a'];
        }
        ws.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c-'a'] == null) return false;
            ws = ws.children[c-'a'];
        }
        return ws.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
         TrieNode ws = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c-'a'] == null) return false;
            ws = ws.children[c-'a'];
        }
        return true;
    }
        
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */