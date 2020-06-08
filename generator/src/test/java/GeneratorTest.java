import com.bank.gen.GeneratorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratorTest {

//    @Test
    public void test() {
        GeneratorCode generator = new GeneratorCode();
        generator.setPackageDir("E:\\tem\\gen");//测试

        generator.setPackageDir("E:\\YUXIN\\CSBank\\Code\\wbmp\\generator");
        generator.setPackageName("com.bank.generator");

//        generator.setPackageDir("E:\\YUXIN\\CSBank\\Code\\wbmp\\manage");
//        generator.setPackageName("com.bank.manage");

//        generator.setPackageDir("E:\\YUXIN\\CSBank\\Code\\wbmp\\message");
//        generator.setPackageName("com.bank.message");
        generator.setMapperXmlDirName("mybatis-mapper");
        generator.setTablePrefix(new String[]{"T_"});
        generator.setIncludeTables(new String[]{"T_STAR_DATA_ADMIN",
                "T_STAR_DATA_TEMP_BRANCH"});
        generator.setHasSuperEntity(false);
        generator.run();
        log.info("代码生成完成,生成路径：{}", generator.getPackageDir());
    }


}
