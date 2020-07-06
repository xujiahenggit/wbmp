import com.bank.gen.GeneratorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratorTest {

//    @Test
    public void generVUE() {
        GeneratorCode generator = new GeneratorCode();
        generator.setPackageDir("E:\\IDEA\\wbmp-server\\manage");
        generator.setPackageName("com.bank.manage");
        //        generator.setServiceName("沙龙活动");
        //        generator.setPackageWebDir("C:\\Users\\ZHAO\\Desktop\\view");
        generator.setIncludeTables(new String[]{
                "wbmp_abs_teller_info",
                "wbmp_abs_teller_online_time",
                "wbmp_abs_online_time"
        });
        //        generator.setIncludeTables(new String[]{"T_ACTIVITIE_SALON",
        //                "T_ACTIVITIE_SALON_LOG"});

        //        generator.setPackageDir("E:\\IDEA\\generator");
        //        generator.setPackageName("com.bank.generator");
        //        generator.setServiceName("游戏模块");
        //        generator.setPackageWebDir("E:\\IDEA\\generator\\src\\main\\resources\\view");
        //        generator.setIncludeTables(new String[]{
        //                "T_GAME",
        //                "t_user"
        //        });
        generator.setMapperXmlDirName("mybatis-mapper");
        generator.setTablePrefix(new String[]{"S_", "T_", "t_"});
        generator.setHasSuperEntity(false);
        generator.run();
        System.out.println("代码生成完成,生成路径：" + generator.getPackageDir());
    }

}
