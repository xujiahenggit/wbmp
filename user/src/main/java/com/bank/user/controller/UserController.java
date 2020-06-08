package com.bank.user.controller;

import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.ConstFile;
import com.bank.log.annotation.SystemLog;
import com.bank.user.dos.UserDO;
import com.bank.user.dto.UserInfoDto;
import com.bank.user.vo.UserSaveUpdateVo;
import com.bank.user.vo.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.user.service.UserService;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户操作入口类
 *
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Api(tags = "用户操作接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseUserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页获取所有用户信息", notes = "分页获取所有用户信息")
    @PostMapping("/list")
    public IPage<UserInfoDto> getUserList(@RequestBody UserVO userVO, HttpServletRequest request) {
        //获取当前用户的机构ID
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        String currentOrgId = tokenUserInfo.getOrgId();
        IPage<UserInfoDto> pageList = userService.getUserList(userVO,currentOrgId);
        return pageList;
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "userid",
                    value = "用户工号",
                    example = "1",
                    dataType = "String",
                    required = true),
            @ApiImplicitParam(
                    name = "password",
                    value = "密码明文",
                    example = "123456",
                    dataType = "String",
                    required = true)
    })
    @SystemLog(logModul = ConstFile.MODULE_USER, logType =ConstFile.TYPE_CGPWD, logDesc = "修改密码")
    @PutMapping("/changepwd")
    public Boolean updatePassword(@RequestParam(required = true) String userid,@RequestParam(required = true) String password){
        return userService.changePassword(userid,password);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户ID 删除用户")
    @ApiImplicitParam(name = "userId", value = "用户工号", required = true, paramType = "path")
    @SystemLog(logModul = ConstFile.MODULE_USER, logType =ConstFile.TYPE_DELETE, logDesc = "删除用户")
    @DeleteMapping("/{userId}")
    public Boolean deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户ID 获取用户 更新用户时用")
    @ApiImplicitParam(name = "userId", value = "用户工号", required = true, paramType = "path")
    @GetMapping("/{userId}")
    public UserDO getUserInfo(@PathVariable String userId){
        return userService.getById(userId);
    }


    @SystemLog(logModul = ConstFile.MODULE_USER, logType = ConstFile.TYPE_UPDATE, logDesc = "更新用户信息")
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @PutMapping("/update")
    public Boolean updateUserInfo(@RequestBody UserSaveUpdateVo userSaveUpdateVo,HttpServletRequest request){
        //获取当前登录用户
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return userService.updateUserInfo(userSaveUpdateVo,tokenUserInfo);
    }

    @SystemLog(logModul = ConstFile.MODULE_USER, logType = ConstFile.TYPE_ADD, logDesc = "添加用户信息")
    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    @PostMapping("/add")
    public Boolean saveUserInfo(@RequestBody UserSaveUpdateVo userSaveUpdateVo,HttpServletRequest request){
        //获取当前登录用户信息
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return userService.saveUserInfo(userSaveUpdateVo,tokenUserInfo);
    }
}
