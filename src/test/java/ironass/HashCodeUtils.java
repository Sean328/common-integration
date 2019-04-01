package ironass;

/**
 * @author lixin
 * @date 2019-03-26 19:45
 **/
public class HashCodeUtils {
    private static long hash;

    public static void main(String[] args) {
        String ip1 = "192.168.0.1";
        String ip2 = "128.119.143.12";
        String ip3 = "255.255.254.124";

        int maxIdle = 65535;
        int idle = 4;

        long ip1Hash = longHashCode(ip1.toCharArray());
        long ip2Hash = longHashCode(ip2.toCharArray());
        long ip3Hash = longHashCode(ip3.toCharArray());

        long ip1Position = ip1Hash % maxIdle % idle;
        long ip2Position = ip2Hash % maxIdle % idle;
        long ip3Position = ip3Hash % maxIdle % idle;


        System.out.println();
    }

    public static long longHashCode(char[] value) {
        long h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 17 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
}
