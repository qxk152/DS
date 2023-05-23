/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/05/23 09:44
 */
public class Lps {
    public String longestPrefix(String s ){
        int [] lps = getLps(s);
        int len = lps[s.length() - 1];
        return s.substring(0,len);
    }

    private int[] getLps(String t) {
        //默认值为0
        int [] lps = new int[t.length()];
        //由于lps[0]肯定是0 因为一个字符没有不包含自己的前后缀
        for(int i = 1 ; i < t.length();i++){
            int a = lps[i - 1];
            while(a > 0 && t.charAt(i) != t.charAt(a)){
                a = lps[a - 1];
            }
            if(t.charAt(i) == t.charAt(a)){
                lps[i] = a + 1;
            }
        }
        return lps;
    }
}
