// Time Complexity : N*l + M*l  --> (N + M)*l
// Space Complexity : N*l where N is total number of words in the dictionary and l is length of word
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :
class Solution {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    TrieNode root = new TrieNode();

    private void insert(String words) {
        TrieNode curr = root;
        for (int i = 0; i < words.length(); i++) {
            char c = words.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder res = new StringBuilder();
        for (String word : dictionary) { // N*l - where N is number of words and l is length of words
            insert(word);
        }
        String[] strArray = sentence.split(" "); // O(M) - M total number of words in sentence
        for (int i = 0; i < strArray.length; i++) {
            String word = strArray[i];
            TrieNode curr = root;
            StringBuilder replacementString = new StringBuilder();
            if(i != 0)
                res.append(" ");
            for (int j = 0; j < word.length(); j++) {// l - for each word the length of word
                char c = word.charAt(j);
                if (curr.children[c - 'a'] == null || curr.isEnd)
                    break;
                curr = curr.children[c - 'a'];
                replacementString.append(c);
            }
            if (curr.isEnd)
                res.append(replacementString); //O(1)
            else {
                res.append(word);//O(1)
            }
        }
        return res.toString();
    }
}