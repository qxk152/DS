/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/05/23 10:06
 */
public class SubstringMatch {

    private static int[] getLps(String t) {
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
    //返回s中第一次出现模式串t的下标
    public static int kmp(String s,String t){
        if(s.length() <t.length()){
            return -1;
        }
        if(t.length() == 0){
            return  0;
        }
        int [] lps = getLps(t);
        int i = 0,j = 0; //i 指向s中匹配的位置 j指向t中匹配的位置
        while(i < s.length()){
            if(s.charAt(i) == t.charAt(j)){
                i++;
                j++;
                if(j == t.length()){
                    //说明匹配成功
                    //0 1 2 3 i
                    //  1 2 3 j
                    return i - t.length();
                }
            }else if(j>0){
               //匹配失败 i不动 移动j到最长匹配前缀的下一个位置
               j = lps[j - 1];
            }else{
                //第一个就匹配失败 j==0 i++；j已经是0
                i++;
            }
        }
        return -1;
    }
}
