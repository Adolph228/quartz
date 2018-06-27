package com.yuerui.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuerui.biz.service.ISysUserService;
import com.yuerui.model.SysUser;

import junit.framework.TestCase;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SysUserServiceTest extends TestCase{
	

	@Autowired
	ISysUserService sysUserService;

	
	@Test
	public void test() {
		SysUser user = sysUserService.getUserByuserame("yanao");
		System.out.println(user.getId());
		System.out.println(111);
	}
	
}
