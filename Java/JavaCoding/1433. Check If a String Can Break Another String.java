// java
import java.util.Arrays;
class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
         char[] ch1 = s1.toCharArray();
         char[] ch2=s2.toCharArray();
         Arrays.sort(ch1);
         Arrays.sort(ch2);
        boolean flag1=true,flag2=true;
         for(int i=0;i<ch1.length;i++){
            if(ch1[i]>ch2[i]){
                flag1=false;
            }
         }
         for(int i=0;i<ch1.length;i++){
            if(ch1[i]<ch2[i]){
                flag2=false;
            }
         }
        return flag1||flag2;
    }
}

//cpp
class Solution {
public:
    bool checkIfCanBreak(string s1, string s2) {
        sort(s1.begin(),s1.end());
        sort(s2.begin(),s2.end());
       
       bool flag=true;
       for(int i=0;i<s1.length();i++){
        if(s2[i]<s1[i]){
             flag=false;
        }
       }
       bool flag2=true;
       for(int i=0;i<s1.length();i++){
        if(s2[i]>s1[i]){
             flag2=false;
        }
       }
       if(!flag && !flag2){
        return false;
       }
        return true;
    }
};
