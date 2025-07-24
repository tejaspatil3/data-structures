class Solution:
    def isValid(self, word):
        """
        :type word: str
        :rtype: bool
        """
        # self.word = word
        if(len(word) < 3):
            return False
        else:
            flagvowel = False
            flagcon = False
            for ch in word:
                if not ch.isalnum():
                    return False 
                if ch.isalpha():
                    if ch.lower() in ('a', 'e', 'i', 'o', 'u'):
                        flagvowel = True
                    else:
                        flagcon = True

            return flagvowel and flagcon
            

c1= Solution()
print(c1.isValid("234Adas"))