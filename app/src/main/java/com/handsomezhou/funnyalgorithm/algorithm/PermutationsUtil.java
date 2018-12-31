package com.handsomezhou.funnyalgorithm.algorithm;

/**
 * Created by handsomezhou on 2018/12/28.
 * 排列组合工具类
 */

public class PermutationsUtil {
    /**
     * 	有M个数字,有N个选项,现每个选项从M个数字任选一个数字,确保N1<=N2<=...<=Nn,
     * 	请问有多少种组合方式(只求组合方式数量,不要求枚举),推导或直接给出相关公式即可?
     * 	PS:M,N均为大于0的自然数
     *
     * 	示例:
     * 	有M=2个数字{1,2},有N=3个选项{N1,N2,N3},现在从M=2个数字任选一个,确保N1<=N2<=N3 组合方式有4种组合方式
     *  {1,1,1}
     *  {1,1,2}
     *  {1,2,2}
     *  {2,2,2}
     *
     *  由观察法推出如下公式
     * f(m,n)=f(m-1,n)+f(m,n-1)
     * f(m,1)=m
     * f(1,n)=1
     */
    public static int fun(int m,int n){
        if(m<=1){
            //f(1,n)=1
            return 1;
        }

        if(n<=1){
            // f(m,1)=m
            return m;
        }

        return fun(m-1,n)+fun(m,n-1);
    }
}
