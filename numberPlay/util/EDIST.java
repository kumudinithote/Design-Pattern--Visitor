package numberPlay.util;

public class EDIST {
	
	/**
	 * 
	 * @param str1
	 * @param str2 
	 * @param m is length of str1
	 * @param n is length of str2
	 * This method process all the character one by one, minimum number of edits required to convert str1 to str2 only by replace
	 * and uses dynamic programming
	 */

    public int editDist(String str1, String str2, int m, int n) 
    { 
        if (m == 0) 
            return n; 

        if (n == 0) 
            return m; 
  
        if (str1.charAt(m - 1) == str2.charAt(n - 1)) 
            return editDist(str1, str2, m - 1, n - 1); 
  
        return 1 + editDist(str1, str2, m - 1, n - 1); 
    } 
}
