import java.math.BigInteger;
class Solution {
    public boolean findrec(BigInteger n1,BigInteger n2,String s,Boolean found){
        if(found && s.length()==0){
            return true;
        }
        BigInteger sum=n1.add(n2);
        String n3=sum.toString();
        int idx=Math.min(s.length(),n3.length());
        String s2=s.substring(0,idx);
        if(s2.equals(n3)){
            return findrec(n2,sum,s.substring(idx),true);
        }

return false;
    }
    public boolean isAdditiveNumber(String num) {
        int n=num.length();
        for(int i=1;i<n-1;i++){
            BigInteger n1=new BigInteger(num.substring(0,i));
            if(!n1.toString().equals(num.substring(0,i))){
                break;
            }
            for(int j=i+1;j<n;j++){
                BigInteger n2=new BigInteger(num.substring(i,j));
                if(!n2.toString().equals(num.substring(i,j))){
                      break;
                  }
                if(findrec(n1,n2,num.substring(j),false)){
                    return true;
                }
            }
        }
 return false;
    }
}
