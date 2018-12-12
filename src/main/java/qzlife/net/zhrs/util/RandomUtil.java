package qzlife.net.zhrs.util;

/**
 * 随机数工具
 * @author Deacon
 *
 */
public class RandomUtil 
{
	/**
	 * 获取随机数
	 * 
	 * @param length 随机数长度
	 * @return
	 */
	public static String getRandomStr(int length) 
	{
		// 根据长度, 计算需要相乘的数
		int num = 1;
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}

		// 保证随机数大于0.1
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}

		return String.valueOf((int) (random * num));
	}
	
	
	public static String getLongRandomStr(int length) 
	{
		// 根据长度, 计算需要相乘的数
		long num = 1;
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		// 保证随机数大于0.1
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		return String.valueOf((long) (random * num));
	}
	
    public static void main(String[] args) {
       
        System.out.println(getLongRandomStr(6));
    }
}
