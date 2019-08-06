package com.kunlinr.lifealien.main;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import com.kunlinr.lifealien.main.entity.User;

/**
 * hibernate 测试
 * @author kunlingou
 *
 */
public class UserTest {
	public void insertTest() {
		Properties properties = new Properties();
		properties.setProperty(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
		properties.setProperty(AvailableSettings.URL, "jdbc:mysql://localhost:3306/lifeaide");
		properties.setProperty(AvailableSettings.USER, "root");
		properties.setProperty(AvailableSettings.PASS, "mybatis");
		//1.加载配置文件:hibernate.cfg.xml
//		Configuration configure = new Configuration().configure();
		Configuration configure = new Configuration().addProperties(properties);
		//2.获取SessionFactory工厂
		SessionFactory buildSessionFactory = configure.buildSessionFactory();
		//3.获取session
		Session session = buildSessionFactory.openSession();
		//4.开启事务
		Transaction transaction = session.beginTransaction();
		//5.操作
//		User user = new User(0X20L, "goukunlin1", "苟坤林1","123");
//		session.save(user);
		//6.提交事务
		transaction.commit();
		//7.关闭资源
		session.close();
		buildSessionFactory.close();
	}
}
