class Solution:
    def find_rec(self, n1, n2, s, found):
            if s == "" and found:
                return True
            n3 = str(n1 + n2)
            idx = min(len(n3),len(s))
            if s[:idx] == n3:
                return self.find_rec(n2, int(n3), s[idx:], True)
            return False
        
    def isAdditiveNumber(self, num: str) -> bool:
        for i in range(1, len(num)-1):
            n1 = int(num[:i])
            if str(n1) != num[:i]:
                break
            for j in range(i+1, len(num)):
                n2 = int(num[i:j])
                if str(n2) != num[i:j]:
                    break
                found = self.find_rec(n1, n2, num[j:], False)
                if found:
                    return True
        return False
