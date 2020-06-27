package com.bank.user.controller;

import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.ConstFile;
import com.bank.log.annotation.SystemLog;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.dto.OrgDemandDto;
import com.bank.user.dto.OrgListDto;
import com.bank.user.service.OrganizationService;
import com.bank.user.utils.OrgIdUtils;
import com.bank.user.utils.OrgTreeDetail;
import com.bank.user.vo.OrgQueryVo;
import com.bank.user.vo.OrgVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/1 16:37
 */
@Api(tags = "机构操作接口")
@RestController
@RequestMapping("/org")
@Slf4j
public class OrganizationController extends BaseUserController {

    @Autowired
    private OrganizationService organizationService;

    @ApiOperation(value = "分页获取所有机构列表信息", notes = "分页获取所有机构列表信息")
    @PostMapping("/list")
    public IPage<OrgListDto> getList(@RequestBody OrgQueryVo orgQueryVo, HttpServletRequest request){
        //获取当前用户的机构ID
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        String currentOrgId=tokenUserInfo.getOrgId();
        IPage<OrgListDto> pageOrg=organizationService.selectOrgPage(orgQueryVo,currentOrgId);
        return pageOrg;
    }

    @ApiOperation(value = "组织机构树", notes = "组织机构树")
    @PostMapping("/tree")
    public List<Map<String,Object>> getOrganization(HttpServletRequest request){
        //获取当前用户的机构ID
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        String currentOrgId=tokenUserInfo.getOrgId();
        List<Map<String,Object>> list=organizationService.listMaps();
        List<Map<String, Object>> orgTree=new ArrayList<>();
        //如果是总行 则 显示所有的机构
        if(organizationService.isHeadOffice(currentOrgId)){
            orgTree= OrgTreeDetail.getOrgTree(list,"1");;
        }else {
            orgTree= OrgTreeDetail.getOrgTree(list,currentOrgId);
        }
        return orgTree;
    }

    @SystemLog(logModul = ConstFile.MODULE_ORG,logType = ConstFile.TYPE_ADD,logDesc = "添加机构")
    @ApiOperation(value = "添加机构", notes = "添加机构")
    @PostMapping("/add")
    public Boolean saveOrg(@RequestBody OrgVo orgVo,HttpServletRequest request){
        //获取当前登录用户
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return organizationService.addOrg(orgVo,tokenUserInfo);
    }

    @SystemLog(logModul = ConstFile.MODULE_ORG,logType =ConstFile.TYPE_UPDATE,logDesc = "更新机构")
    @ApiOperation(value = "更新机构", notes = "更新机构")
    @PostMapping("/update")
    public Boolean updateOrg(@RequestBody OrgVo orgVo,HttpServletRequest request){
        //获取当前登录用户
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return organizationService.updateOrg(orgVo,tokenUserInfo);
    }

    @SystemLog(logModul = ConstFile.MODULE_ORG,logType = ConstFile.TYPE_DELETE,logDesc = "删除机构")
    @ApiOperation(value = "删除机构", notes = "删除机构")
    @ApiImplicitParam(name = "orgId", value = "机构编号", required = true, paramType = "path")
    @DeleteMapping("/{orgId}")
    public Boolean delOrg(@PathVariable String orgId){
        return organizationService.deleteOrg(orgId);
    }

    @ApiOperation(value = "根据ID获取机构", notes = "根据ID获取机构")
    @ApiImplicitParam(name = "orgId", value = "机构编号", required = true, paramType = "path")
    @GetMapping("/{orgId}")
    public OrgListDto getInfo(@PathVariable String orgId){
        return organizationService.getOrgDetailById(orgId);
    }

    @ApiOperation("机构按需加载父机构，第一次访问时用")
    @GetMapping("/getparent")
    public OrgDemandDto getParentOrg(HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return organizationService.getParentOrg(tokenUserInfo);
    }

    @ApiOperation("查询子机构，传父机构ID")
    @GetMapping("/getchild/{orgId}")
    public List<OrgDemandDto> getChild(@PathVariable String orgId){
        return organizationService.getChild(orgId);
    }


    @GetMapping("/search/{key}")
    public List<OrganizationDO> getSearchOrgList(@PathVariable String key){
//        //输入数据大于3
//        if(key.length()>3){
//
//        }else{
//            return null;
//        }

        return organizationService.getSearchOrgList(key);
    }
}
