package org.lql.util;

/**
 * Title: HeaderUtil <br>
 * ProjectName: learn-concurrent <br>
 * description: 对象头工具类 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/2 16:36 <br>
 */
public abstract class HeaderUtil {

    /**
     * 根据对象头字符串获取markWord信息
     * @param headerString 对象头字符串
     * @param isBinary 是否显示为二进制
     * @return markWord字符创
     */
    public static String getMarkWord(String headerString, boolean isBinary) {
        String result = "";

        if (headerString != null && !headerString.isEmpty()) {
            String[] strings = headerString.split("\\r\\n");
            String[] split = strings[2].split("\\s+");
            if (isBinary) {
                result = makeUpToLong(Long.toBinaryString(Long.parseLong(split[6].substring(2), 16)));
            }else {
                result = split[6];
            }
        }


        return result;
    }

    /**
     * 补齐long长度二进制字符串
     * @param str 输入字符串
     * @return  补齐后的字符串
     */
    public static String makeUpToLong(String str) {
        int length = str.length();
        if (length < 64) {
            StringBuilder sb = new StringBuilder();
            int diff = 64 - length;
            for (int i = 0; i < diff; i++) {
                sb.append(0);
            }
            sb.append(str);
            for (int i = sb.length() - 8; i > 0; i -= 8) {
                sb.insert(i, " ");
            }
            return sb.toString();
        }else {
            return str;
        }
    }
}
