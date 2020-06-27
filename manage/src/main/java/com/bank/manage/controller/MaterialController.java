package com.bank.manage.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.NetUtil;
import com.bank.manage.dos.MaterialDO;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.service.MaterialService;
import com.bank.manage.vo.MaterialVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 素材信息
 *
 * @author
 * @date 2020-4-9
 */
@Api(tags = "素材信息接口")
@RestController
@RequestMapping("/material")
@Slf4j
public class MaterialController extends BaseController {

    @Autowired
    private ConfigFileReader configFileReader;

    @Autowired
    private MaterialService materialService;

    @Resource
    NetUtil netUtil;

    @PostMapping("/upFileForShare")
    @ApiOperation(value = "素材上传临时目录--共享目录方式")
    public String upFileForShare(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String filename = file.getOriginalFilename();
        String name = tokenUserInfo.getUserId() + System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
        String path = null;

        /**文件上传开始**/
        File f = new File(this.configFileReader.getUPLOAD_FILE_PATH() + "/" + sdf.format(new Date()));
        if (!f.exists()) {
            f.mkdirs();
        }
        File fileUp = new File(new File(this.configFileReader.getUPLOAD_FILE_PATH() + "/" + sdf.format(new Date())).getAbsolutePath() + "/" + name);
        try {
            file.transferTo(fileUp);
            path = this.netUtil.getUrlSuffix("") + this.configFileReader.getFILE_PATH_FILE() + "/" + sdf.format(new Date()) + "/" + name;
            log.info("***************素材上传临时目录成功**************");
        }
        catch (IOException e) {
            e.printStackTrace();
            log.error("***************素材上传临时目录失败：" + e.getMessage());
        }
        /**文件上传结束**/
        log.info("**************素材上传返回路劲：{}", path);

        return path;
    }

    /*@PostMapping("/upFileForShare")
    @ApiOperation(value = "素材上传临时目录--共享目录方式")
    @ApiImplicitParam(name = "type", value = "素材类型", required = true, paramType = "query")
    public Map<String,String> upFileForShare(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "type")String type){
        *//*Subject subject = SecurityUtils.getSubject();
               AuthDTO authDTO = (AuthDTO)subject.getPrincipal();*//*
                                                                        Map<String,String> map = new HashMap<>();
                                                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                                                        String filename = file.getOriginalFilename();
                                                                        String name ="sj9033"+System.currentTimeMillis()+filename.substring(filename.lastIndexOf("."));
                                                                        String path = null;
                                                                        
                                                                        *//**文件上传开始**/

    /*
    File f = new File(configFileReader.getFILE_PATH_FILE()+"/"+sdf.format(new Date()));
    if(!f.exists()){
    f.mkdirs();
    }
    File fileUp = new File(new File(configFileReader.getFILE_PATH_FILE()+"/"+sdf.format(new Date())).getAbsolutePath()+"/"+name);
    try {
    file.transferTo(fileUp);
    log.info("***************素材上传临时目录成功**************");
    path = netUtil.getUrlSuffix("")+configFileReader.getFILE_PATH_FILE()+"/"+sdf.format(new Date())+"/"+name;
    map.put("path",path);
    //视频存储路径
    String videoPath = configFileReader.getFILE_PATH_FILE()+"/"+sdf.format(new Date());
    //获取第一帧路径
    String videoFramesPath = configFileReader.getFILE_PATH_FILE()+"/"+sdf.format(new Date());
    String videoFileName = name;
    if(ConstantEnum.MATERIAL_TYPE_VEDIO.getType().equals(type)){//视频获取第一帧
    String fetchFrame = VedioUtil.grabberVideoFramer(videoPath, videoFramesPath,videoFileName);
    if(StringUtils.isNotBlank(fetchFrame)){
    log.info("***************图片获取第一帧成功，路径：{}",fetchFrame);
    map.put("vedioImagePath",fetchFrame);
    }
    }
    
    } catch (IOException e) {
    log.error("***************素材上传临时目录失败："+e.getMessage());
    }
    *//**文件上传结束**//*
                       log.info("**************素材上传返回路劲：{}",map);
                       return map;
                       }*/

    @PostMapping("insertMaterial")
    @ApiOperation(value = "素材新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catalogId", value = "素材目录主键", dataType = "String", paramType = "query"),
    })
    public Boolean insertMaterial(@RequestBody @ApiParam(value = "materialDTOList列表") List<MaterialDTO> materialDTO, @RequestParam("catalogId") String catalogId, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.materialService.insertMaterial(materialDTO, catalogId, tokenUserInfo);
    }

    @PostMapping("updateMaterial")
    @ApiOperation(value = "素材修改")
    @ApiImplicitParam(name = "materialDTO", value = "素材信息", required = true, paramType = "body", dataType = "MaterialDTO")
    public Boolean updateMaterial(@RequestBody MaterialDTO materialDTO, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.materialService.updateMaterial(materialDTO, tokenUserInfo);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除素材")
    @ApiImplicitParam(name = "id", value = "素材唯一标识", required = true, paramType = "path")
    public Boolean deleteMaterial(@PathVariable Integer id) {
        return this.materialService.deleteMaterial(id);
    }

    @PostMapping("/queryMaterial")
    @ApiOperation(value = "查询素材列表")
    @ApiImplicitParam(name = "pageQueryModel", value = "素材信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<MaterialVo> queryMaterialList(@RequestBody PageQueryModel pageQueryModel) {
        return this.materialService.queryMaterialList(pageQueryModel);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据素材ID查询素材信息")
    @ApiImplicitParam(name = "id", value = "素材编号", required = true, paramType = "path")
    public MaterialDO queryMaterialById(@PathVariable Integer id) {
        return this.materialService.queryMaterialById(id);
    }

    @GetMapping("/queryAppMaterial")
    @ApiOperation(value = "查询手机app轮播图素材")
    public List<MaterialVo> queryAppMaterialList() {
        return this.materialService.queryAppMaterialList();
    }

    /*@PostMapping("/upFileForFtp")
    @ApiOperation(value = "素材上传临时目录--FTP目录方式")
    public String upFileForFtp(@RequestParam(value = "file") MultipartFile file) {
        Subject subject = SecurityUtils.getSubject();
        AuthDTO authDTO = (AuthDTO)subject.getPrincipal();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        InputStream input = null;
        String path = null;
        try {
            String filename = file.getOriginalFilename();
            String name =authDTO.getUserId()+System.currentTimeMillis()+filename.substring(filename.lastIndexOf("."));
            String pathname = configFileReader.getFTP_FILE_PATH() +"/"+sdf.format(new Date());
            input=file.getInputStream();
            if(FTPUtil.uploadFile(configFileReader.getFILE_FTP_IP(), Integer.parseInt(configFileReader.getFILE_FTP_PORT()),
                configFileReader.getFILE_FTP_USER(), configFileReader.getFILE_FTP_PASS(), pathname, name, input)){
                path=configFileReader.getFILE_FTP_HTTP_PATH()+"/"+sdf.format(new Date())+name;
            }
        } catch (IOException e) {
            log.error("***************素材上传临时目录失败："+e.getMessage());
        }
        log.info("**************素材上传返回路劲：{}",path);
        return path;
    }
    */

}
