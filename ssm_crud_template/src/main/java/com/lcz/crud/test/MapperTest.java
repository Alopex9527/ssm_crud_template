package com.lcz.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lcz.crud.bean.Department;
import com.lcz.crud.bean.Employee;
import com.lcz.crud.dao.DepartmentMapper;
import com.lcz.crud.dao.EmployeeMapper;

/**
 * 测试dao层的工作
 * @author LvChaoZhang
 * 推荐spring的项目就可以使用spring的单元测试，可以自动注入我们需要的空间
 * 1.导入springtest包
 * 2.@ContextConfiguration指定spring配置文件的位置
 * 3.直接autowired的组件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentmapper;
	@Autowired
	EmployeeMapper employmapper;
	
	@Autowired
	SqlSession sqlsession;
	/**
	 * 测试DepartmentMapper
	 */
	@SuppressWarnings("resource")
	@Test
	public void testCRUD() {
//		//1.创建spring ioc容器
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
//		//2.从容器中获取mapper
//		Department bean = ioc.getBean(Department.class);
		System.out.println(departmentmapper);
		
		//1.插入几个部门
		
//		departmentmapper.insertSelective(new Department(null,"开发部"));
//		departmentmapper.insertSelective(new Department(null,"测试部"));
		
		//2.插入员工
		//employmapper.insertSelective(new Employee(null,"jack","M","jack@baidu.com",1));
		//3.批量插入多个员工,批量，使用可以执行批量操作的sqlsession
		/*for()
		employmapper.insertSelective(new Employee(null,"jack","M","jack@baidu.com",1));
		*/
		EmployeeMapper mapper = sqlsession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0, 5)+""+i;
			mapper.insertSelective(new Employee(null,uid,"M",uid+"@lcz.com",1));
		}
		System.out.println("批量执行完毕！");
	}
}
