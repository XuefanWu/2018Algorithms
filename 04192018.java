//Trie
211. Add and Search Word - Data structure design

class WordDictionary {
    class TrieNode{
        public TrieNode[] children = new TrieNode[26];
        public boolean isWord; 
    }
    
    private TrieNode root = new TrieNode();

    // /** Initialize your data structure here. */
    // public WordDictionary() {
    //     TrieNode root = new TrieNode();
    // }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()){
            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new TrieNode();
            }
            node = node.children[c-'a'];
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(),0,root);
    }
    public boolean match(char[] chs, int k, TrieNode node){
        if(k == chs.length){return node.isWord;}
        if(chs[k] == '.'){
            for(int i = 0; i < node.children.length; i++){
                if(node.children[i] != null && match(chs,k+1,node.children[i])){
                    return true;
                }
            }
        }
        else{
                return node.children[chs[k]-'a']!=null && match(chs,k+1,node.children[chs[k]-'a']);
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
212. Word Search II
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = build(words);
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                helper(board,i,j,root,res);
            }
        }
        return res;
    }
    public void helper(char[][] board, int i, int j, TrieNode p, List<String> res){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
        char c = board[i][j];
        if(c == '.' || p.children[c-'a'] == null) return;
        p = p.children[c-'a'];
        if(p.word!=null){
            res.add(p.word);
            p.word = null;
        }

        board[i][j] = '.';
        helper(board,i+1,j,p,res);
        helper(board,i-1,j,p,res);
        helper(board,i,j+1,p,res);
        helper(board,i,j-1,p,res);
        board[i][j] = c;
    }
    public TrieNode build(String[] words){
        TrieNode root = new TrieNode();
        for(String w : words){
            TrieNode p = root;
            for(char c : w.toCharArray()){
                if(p.children[c-'a']==null){
                    p.children[c-'a'] = new TrieNode();
                }
                p = p.children[c-'a'];
            }
            p.word = w;
        }
        return root;
    }
    
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        String word;
    }
}


336. Palindrome Pairs
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words == null || words.length<2) return res;
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i],i);
        }
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j <= words[i].length(); j++){
                String str1 = words[i].substring(0,j);
                String str2 = words[i].substring(j);
                if(isPalindrome(str1)){
                    String str2rev = new StringBuilder(str2).reverse().toString();
                    if(map.containsKey(str2rev) && map.get(str2rev)!=i){
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(str2rev));
                        list.add(i);
                        res.add(list);
                    }
                }
                if(isPalindrome(str2)){
                    String str1rev = new StringBuilder(str1).reverse().toString();
                    if(map.containsKey(str1rev) && map.get(str1rev)!=i && str2.length()!=0){
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(str1rev));
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }
    public boolean isPalindrome(String s){
        int right = s.length()-1, left = 0;
        while(left <= right){
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}

648. Replace Words
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        TrieNode root = build(dict);
        return rebuildSen(words,root);
    }
    public String rebuildSen(String[] words,TrieNode root){
        StringBuilder sb = new StringBuilder();
        for(String w : words){
            sb.append(replace(w,root));
            sb.append(" ");
        }
        return sb.substring(0,sb.length()-1);
    }
    public String replace(String word, TrieNode root){
        TrieNode temp = root;
        StringBuilder sb = new StringBuilder();
        for(char c : word.toCharArray()){
            sb.append(c);
            if(temp.children[c-'a']!=null){
                if(temp.children[c-'a'].isWord){
                    return sb.toString();
                }else{
                    temp = temp.children[c-'a'];
                }
            }else{
                return word;
            }
        }
        return word;
    }
    
    public TrieNode build(List<String> dict){
        TrieNode root = new TrieNode();
        for(String s:dict){
            TrieNode temp = root;
            for(char c : s.toCharArray()){
                if(temp.children[c-'a']==null){
                    temp.children[c-'a'] = new TrieNode();
                }
                temp = temp.children[c-'a'];
            }
            temp.isWord = true;
        }
        return root;
    }
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }
}

676. Implement Magic Dictionary
class MagicDictionary {

    private TrieNode root;
    // /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
        System.out.println("initialize");
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for(String s : dict){
            TrieNode temp = root;
            for(char c : s.toCharArray()){
                if(temp.children[c-'a']==null){
                    temp.children[c-'a'] = new TrieNode();
                }
                temp = temp.children[c-'a'];
            }
            temp.isWord = true;
        }
        System.out.println("build");
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
       char[] chs = word.toCharArray();
        for(int i = 0; i < word.length(); i++){
            for(char c = 'a'; c <= 'z'; c++){
                if(chs[i] == c) continue;
                char org = chs[i];
                chs[i] = c;
                if(helper(new String(chs),root)){return true;}
                chs[i] = org;
            }
        }
        return false;
    }
    
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
        public TrieNode(){}
    }
    public boolean helper(String word, TrieNode root){
        TrieNode temp = root;
        for(char c: word.toCharArray()){
            if(temp.children[c-'a'] == null) {return false;}
            temp = temp.children[c-'a'];
        }
        return temp.isWord;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */