/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bank.gen;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 代码生成器配置类
 *
 * @author Chill
 */
@Data
@Slf4j
public class GeneratorCode {

    /**
     * 代码模块名称
     */
    private String codeName;
    /**
     * xml文件默认生成到/src/main/resources/下
     * 这里指定mapper的包名
     */
    private String mapperXmlDirName;
    /**
     * 代码所在服务名
     */
    private String serviceName = "blade-service";
    /**
     * 代码生成的包名
     */
    private String packageName = "com.bank";
    /**
     * 代码后端生成的地址
     */
    private String packageDir;
    /**
     * 代码前端生成的地址
     */
    private String packageWebDir;
    /**
     * 需要去掉的表前缀
     */
    private String[] tablePrefix = {"T_"};
    /**
     * 需要生成的表名(两者只能取其一)
     */
    private String[] includeTables = {"S_MENUS"};
    /**
     * 需要排除的表名(两者只能取其一)
     */
    private String[] excludeTables = {};
    /**
     * 是否包含基础业务字段
     */
    private Boolean hasSuperEntity = Boolean.FALSE;
    /**
     * 是否包含包装器
     */
    private Boolean hasWrapper = Boolean.FALSE;
    /**
     * 基础业务字段
     */
    private String[] superEntityColumns = {"create_time", "create_user", "create_dept", "update_time", "update_user", "status", "is_deleted"};
    /**
     * 租户字段
     */
    private String tenantColumn = "tenant_id";
    /**
     * 是否启用swagger
     */
    private Boolean isSwagger2 = Boolean.TRUE;
    /**
     * 数据库驱动名
     */
    private String driverName;
    /**
     * 数据库链接地址
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;

    public void run() {
        Properties props = getProperties();
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        String outputDir = getOutputDir();
        String author = props.getProperty("author");
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setActiveRecord(false);//不启用ac模式
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setEntityName("%s");
        gc.setMapperName("%sDao");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setSwagger2(isSwagger2);
        mpg.setGlobalConfig(gc);


        //数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        String driverName = props.getProperty("spring.datasource.driver-class-name");
        if (StrUtil.containsAny(driverName, DbType.MYSQL.getDb())) {
            dsc.setDbType(DbType.MYSQL);
            dsc.setTypeConvert(new MySqlTypeConvert());
        } else if (StrUtil.containsAny(driverName, DbType.POSTGRE_SQL.getDb())) {
            dsc.setDbType(DbType.POSTGRE_SQL);
            dsc.setTypeConvert(new PostgreSqlTypeConvert());
        } else {
            dsc.setDbType(DbType.ORACLE);
            dsc.setTypeConvert(new OracleTypeConvert());
        }
        dsc.setDriverName(driverName);

        dsc.setUrl(props.getProperty("spring.datasource.url"));
        dsc.setUsername(props.getProperty("spring.datasource.username"));
        dsc.setPassword(props.getProperty("spring.datasource.password"));
        mpg.setDataSource(dsc);



        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名
        // strategy.setDbColumnUnderline(true);//全局下划线命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(tablePrefix);

        //逻辑删除属性名称
        strategy.setLogicDeleteFieldName("deleted");

        List<TableFill> tableFillList= CollUtil.newArrayList();
        TableFill fill=new TableFill("update_time", FieldFill.INSERT_UPDATE);
        tableFillList.add(fill);

        fill=new TableFill("create_time", FieldFill.INSERT);
        tableFillList.add(fill);

        strategy.setTableFillList(tableFillList);

        if (includeTables.length > 0) {
            strategy.setInclude(includeTables);
        }
        if (excludeTables.length > 0) {
            strategy.setExclude(excludeTables);
        }
        if (hasSuperEntity) {
            strategy.setSuperEntityClass("org.springblade.core.mp.base.BaseEntity");
            strategy.setSuperEntityColumns(superEntityColumns);
            strategy.setSuperServiceClass("org.springblade.core.mp.base.BaseService");
            strategy.setSuperServiceImplClass("org.springblade.core.mp.base.BaseServiceImpl");
        } else {
            strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
            strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        }
        // 自定义 controller 父类
//        strategy.setSuperControllerClass("org.springblade.core.boot.ctrl.BladeController");
        strategy.setEntityBuilderModel(true);
        strategy.setEntityLombokModel(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);


        // 包配置
        mpg.setPackageInfo(new PackageConfig()
                .setModuleName(null)
                .setParent(packageName)
                .setController("controller")
                .setEntity("dos")
                .setMapper("dao"));

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setEntity(null);
        mpg.setTemplate(templateConfig);


        mpg.setCfg(getInjectionConfig());


        mpg.execute();
    }

    private InjectionConfig getInjectionConfig() {
        String servicePackage = serviceName.split("-").length > 1 ? serviceName.split("-")[1] : serviceName;
        // 自定义配置
        Map<String, Object> map = new HashMap<>(16);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                map.put("codeName", codeName);
                map.put("serviceName", serviceName);
                map.put("servicePackage", servicePackage);
                map.put("servicePackageLowerCase", servicePackage.toLowerCase());
                map.put("tenantColumn", tenantColumn);
                map.put("packageName", packageName);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/sql/menu.sql.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                map.put("entityKey", (tableInfo.getEntityName().toLowerCase()));
//                map.put("menuId", IdWorker.getId());
//                map.put("addMenuId", IdWorker.getId());
//                map.put("editMenuId", IdWorker.getId());
//                map.put("removeMenuId", IdWorker.getId());
//                map.put("viewMenuId", IdWorker.getId());
//                return getOutputDir() + "/" + "/sql/" + tableInfo.getEntityName().toLowerCase() + ".menu.mysql";
//            }
//        });
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return packageDir+"/src/main/resources/" + mapperXmlDirName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });


        final String dotJava = StringPool.DOT_JAVA;
        focList.add(new FileOutConfig("/templates/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "controller" + "/" + tableInfo.getControllerName() + dotJava;
            }
        });

        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "dao" + "/" + tableInfo.getMapperName() + dotJava;
            }
        });

        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "service" + "/" + tableInfo.getServiceName() + dotJava;
            }
        });

        focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "service/impl/" + tableInfo.getServiceImplName() + dotJava;
            }
        });



        focList.add(new FileOutConfig("/templates/entityVO.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "vo" + "/" + tableInfo.getEntityName() + "VO" + dotJava;
            }
        });

        focList.add(new FileOutConfig("/templates/entityDTO.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "dto" + "/" + tableInfo.getEntityName() + "DTO" + dotJava;
            }
        });
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "dos" + "/" + tableInfo.getEntityName()+ "DO" + dotJava;
            }
        });

        if (hasWrapper) {
            focList.add(new FileOutConfig("/templates/wrapper.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "wrapper" + "/" + tableInfo.getEntityName() + "Wrapper" + dotJava;
                }
            });
        }

        if (StrUtil.isNotBlank(packageWebDir)) {
            focList.add(new FileOutConfig("/templates/pageList.vue.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputWebDir() + "/" + tableInfo.getEntityName().toLowerCase() + ".vue";
                }
            });

            focList.add(new FileOutConfig("/templates/pageList.js.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return getOutputWebDir() + "/" + tableInfo.getEntityName().toLowerCase() + ".js";
                }
            });

        }
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 获取配置文件
     *
     * @return 配置Props
     */
    private Properties getProperties() {
        // 读取配置文件
        Resource resource = new ClassPathResource("/gen_db.properties");
        Properties props = new Properties();
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * 生成到项目中
     *
     * @return outputDir
     */
    public String getOutputDir() {
        return (StrUtil.isBlank(packageDir) ? System.getProperty("user.dir") + "/blade-ops/blade-develop" : packageDir) + "/src/main/java";
    }

    /**
     * 生成到Web项目中
     *
     * @return outputDir
     */
    public String getOutputWebDir() {
        return (StrUtil.isBlank(packageWebDir) ? System.getProperty("user.dir") : packageWebDir);
    }

    /**
     * 页面生成的文件名
     */
    private String getGeneratorViewPath(String viewOutputDir, TableInfo tableInfo, String suffixPath) {
        String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
        String path = viewOutputDir + "/" + name + "/" + name + suffixPath;
        File viewDir = new File(path).getParentFile();
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }
        return path;
    }

}
