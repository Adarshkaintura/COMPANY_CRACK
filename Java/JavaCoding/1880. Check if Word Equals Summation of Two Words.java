//java
class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {

        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < firstWord.length(); i++) {
            sb1.append((char)(firstWord.charAt(i) - 'a' + '0'));
        }

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < secondWord.length(); i++) {
            sb2.append((char)(secondWord.charAt(i) - 'a' + '0'));
        }

        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < targetWord.length(); i++) {
            sb3.append((char)(targetWord.charAt(i) - 'a' + '0'));
        }

        int num1 = Integer.parseInt(sb1.toString());
        int num2 = Integer.parseInt(sb2.toString());
        int num3 = Integer.parseInt(sb3.toString());

        return num1 + num2 == num3;
    }
}


//cpp
class Solution {
public:
    bool isSumEqual(string firstWord, string secondWord, string targetWord) {
        for (int i = 0; i < firstWord.length(); i++) {
            firstWord[i] = firstWord[i] - 'a' + '0'; 
        }
        for (int i = 0; i < secondWord.length(); i++) {
            secondWord[i] = secondWord[i] - 'a' + '0';
        }
        for (int i = 0; i < targetWord.length(); i++) {
            targetWord[i] = targetWord[i] - 'a' + '0';
        }

        int num1 = stoi(firstWord);
        int num2 = stoi(secondWord);
        int num3 = stoi(targetWord);

        return num1 + num2 == num3;
    }
};
