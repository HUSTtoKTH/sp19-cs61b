public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> charOfWord = new ArrayDeque<>();
        for(int i = 0; i<word.length();i++){
            charOfWord.addLast(word.charAt(i));
        }
        return charOfWord;
    }

    public boolean isPalindrome(String word){
        Deque<Character> charOfWord = wordToDeque(word);
        if(charOfWord.size() == 1||charOfWord.size() == 0){
            return true;
        }
        return isPalindromeHelper(charOfWord);
    }
    public boolean isPalindromeHelper(Deque<Character> d){
        if(d.size() == 0 ||d.size() == 1){
            return true;
        }
        if(d.removeFirst() == d.removeLast()){
            return isPalindromeHelper(d);
        }
        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> charOfWord = wordToDeque(word);
        if(charOfWord.size() == 1){
            return true;
        }
        return isPalindromeOffByOne(charOfWord,cc);
    }
    public boolean isPalindromeOffByOne(Deque<Character> d,CharacterComparator cc){
        if(d.size() == 0 ||d.size() == 1){
            return true;
        }
        char left = d.removeFirst();
        char right = d.removeLast();
        if(cc.equalChars(left,right)){
            return isPalindromeOffByOne(d,cc);
        }
        return false;
    }
}
