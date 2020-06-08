import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @Author: Andy
 * @Date: 2020/4/1 9:42
 */
public class test {
    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            Snowflake snowflake = IdUtil.createSnowflake(1, 1);
            long id = snowflake.nextId();
            System.out.println(id);

            String simpleUUID = IdUtil.simpleUUID();
            System.out.println(simpleUUID);
        }
    }
}
