package com.yuerui.biz.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.yuerui.biz.service.ISysUserService;
import com.yuerui.model.SysUser;

public class PushMessageJob {
	
	@Autowired
	ISysUserService sysUserService;
	 public void execute() {
		 try{
			 // 需要做的事情
	        System.out.println("执行targetObject中的targetMethod方法，开始！");
	        SysUser user = sysUserService.getUserByuserame("yanao");
			System.out.println(user.getId());
	        System.out.println("执行targetObject中的targetMethod方法，结束！");
		 }catch(Exception e){
			 e.printStackTrace();
		 }
       
    }
}
