package com.asay.wetrip.user.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.asay.wetrip.entity.UserDetail;
import com.asay.wetrip.entity.Users;
/**
 * 
 * @ClassName:  LoginDaolmpl   
 * @Description:登录和注册功能的dao层，对用户表进行查询和插入操作
 * @author: 倪泽苒
 * @date:   2018年12月1日 上午11:19:10
 */
@Repository
@Transactional
public class UserDaoImpl {
	@Resource
	private SessionFactory sessionFactory;						
	//查询email,返回users对象列表，返回值不能为users，因为空指针;若列表为空，说明邮箱账户为注册
		public  List<Users> findByEmail(String email) {
			Session session=this.sessionFactory.getCurrentSession();
			Query q=session.createQuery("from Users u where u.email=?");
			q.setParameter(0, email);
			return q.list();
			}

	//注册时的邮箱与密码的存入，同时用户详情表插入默认值
		public void insertUser(Users users){
			Session session=this.sessionFactory.getCurrentSession();
			UserDetail ud=users.getUserDetail();
			ud.setUsername(users.getEmail());
			//默认头像
			ud.setUserhead("image/avatar/avatar4.png");
			ud.setSex("秘密");
			ud.setDescription("这个人很懒，什么都没有留下");
			//注册时间
			ud.setRegistTime(new Timestamp(System.currentTimeMillis()));
		}

}

