//java
import java.util.*;

class Solution {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        // Sort strings and check equality
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        if (Arrays.equals(arr1, arr2)) {
            return true;
        }

        // Count frequencies of characters
        Map<Character, Integer> mp1 = new HashMap<>();
        Map<Character, Integer> mp2 = new HashMap<>();

        for (int i = 0; i < word1.length(); i++) {
            mp1.put(word1.charAt(i), mp1.getOrDefault(word1.charAt(i), 0) + 1);
            mp2.put(word2.charAt(i), mp2.getOrDefault(word2.charAt(i), 0) + 1);
        }

        // If different sets of characters → not close
        if (!mp1.keySet().equals(mp2.keySet())) {
            return false;
        }

        // Compare frequency distributions
        List<Integer> vec1 = new ArrayList<>(mp1.values());
        List<Integer> vec2 = new ArrayList<>(mp2.values());

        Collections.sort(vec1);
        Collections.sort(vec2);

        return vec1.equals(vec2);
    }
}
//opmitized 
import java.util.*;

class Solution {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (char c : word1.toCharArray()) {
            freq1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            freq2[c - 'a']++;
        }

        // Check same set of characters
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0)) {
                return false;
            }
        }

        // Compare sorted frequency distributions
        Arrays.sort(freq1);
        Arrays.sort(freq2);

        return Arrays.equals(freq1, freq2);
    }
}


//cpp
class Solution {
public:
    bool closeStrings(string word1, string word2) {
        if(word1.length()!=word2.length()){
            return false;
        }
        sort(word1.begin(),word1.end());
        sort(word2.begin(),word2.end());
        if(word1==word2){
            return true;
        }

        unordered_map<char,int> mp1,mp2;
        for(int i=0;i<word1.length();i++){
            mp1[word1[i]]++;
            mp2[word2[i]]++;
        }
        vector<int> vec1;
        vector<int> vec2;
        for(auto it:mp1){
            if(mp2.find(it.first)==mp2.end()){
             return false;
            }
            vec1.push_back(it.second);
            
        }
        for(auto it:mp2){
            if(mp1.find(it.first)==mp1.end()){
             return false;
            }
            vec2.push_back(it.second);
        }
        sort(vec1.begin(),vec1.end());
        sort(vec2.begin(),vec2.end());
     for(int i=0;i<vec1.size();i++){
        if(vec1[i]!=vec2[i]){
            return false;
        }
     }
     return true;
    }
};
