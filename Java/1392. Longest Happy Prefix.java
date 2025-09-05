class Solution {
    public String longestPrefix(String s) {
      int n=s.length();
      String ans="";
     
      for(int len=n-1;len>0;len--){
 boolean match=true;
        for(int i=0;i<len;i++){
            if(s.charAt(i)!=s.charAt(n-len+i)){
               match=false;
               break;
            }
        }
        if(match){
        ans=s.substring(0,len);
        break;
        }
      }
      return ans;
    }
}

class Solution {
    public int[] makeLps(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int length = 0;
        int i = 1; // lps[0] is always 0

        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public String longestPrefix(String s) {
      int n=s.length();
      int[] lps=makeLps(s);
      int len=lps[n-1];
      return s.substring(n-len,n);
    }
}
