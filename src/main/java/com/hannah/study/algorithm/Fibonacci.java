package com.hannah.study.algorithm;

/**
 * 意大利-斐波那契数列，又称黄金分割数列，指的是这样一个数列：0、1、1、2、3、5、8、13、21、34...
 * 兔子问题：假定一对兔子每个月可以生一对兔子，而这对新兔子在出生后第二个月就开始生另外一对兔子，这些兔子不会死去，那么一对兔子一年内能繁殖多少对兔子？
 * 以递归的方法定义：F(0)=0, F(1)=1, F(n)=F(n-1)+F(n-2)(n≥2，n∈N*)
 * @author longrm
 */
public class Fibonacci {

	/**
	 * 递归
	 * @param n
	 * @return
	 */
	public static int recursion(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return recursion(n-1) + recursion(n-2);
	}

	/**
	 * 循环：前状态值
	 * @param n
	 * @return
	 */
	public static int loop(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		
		int fn = 0;
		int fn1 = loop(0);
		int fn2 = loop(1);
		for (int i = 2; i <= n; i++) {
			fn = fn1 + fn2;
			fn1 = fn2;
			fn2 = fn;
		}
		return fn;
	}

	/**
	 * 动态规划：状态表（数组）
	 * @param n
	 * @return
	 */
	public static int dp(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		
		int[] fns = new int[n+1];
		fns[0] = dp(0);
		fns[1] = dp(1);
		for (int i = 2; i <= n; i++) {
			fns[i] = fns[i-1] + fns[i-2];
		}
		return fns[n];
	}

	public static void main(String[] args) {
		for (int i = 0; i < 45; i++) {
			long l = System.currentTimeMillis();
			int f = dp(i);
			long time = System.currentTimeMillis() - l;
			System.out.println("F(" + i + "): " + f + " -> " + time);
		}
	}

}
