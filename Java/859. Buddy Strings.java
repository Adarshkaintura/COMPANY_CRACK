class Solution {
    public boolean buddyStrings(String s, String goal) {
        int n1 = s.length();
        int n2 = goal.length();

        if (n1 != n2) return false;

        if (s.equals(goal)) {
            // If they are already equal, we need at least one duplicate character
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
                if (freq[c - 'a'] > 1) return true;
            }
            return false;
        }

        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < n1; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (idx1 == -1) {
                    idx1 = i;
                } else if (idx2 == -1) {
                    idx2 = i;
                } else {
                    return false; 
                }
            }
        }

        return (idx2 != -1 &&
                s.charAt(idx1) == goal.charAt(idx2) &&
                s.charAt(idx2) == goal.charAt(idx1));
    }
}
