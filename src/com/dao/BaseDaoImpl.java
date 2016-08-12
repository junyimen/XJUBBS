package com.dao;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;



@Repository("dao")
public class BaseDaoImpl implements BaseDao{
	
	@Resource(name="hibernateTemplate")
	HibernateTemplate hibernateTemplate;
	
	@Override
	public Object loadById(Class clazz, Serializable id) {
		return hibernateTemplate.get(clazz, id);
		
	}

	@Override
	public Object loadObject(String hql) {
		final String hql1 = hql;
		Object obj = null;
		List list = hibernateTemplate.executeFind(new HibernateCallback(){

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql1);
				return query.list();
			}
			
		});
		if(list.size() > 0)
			obj = list.get(0);
		return obj;
	}

	@Override
	public void delById(Class clazz, Serializable id) {
		hibernateTemplate.delete(hibernateTemplate.get(clazz, id));
	}

	@Override
	public void saveOrUpdate(Object obj) {
		hibernateTemplate.saveOrUpdate(obj);
	}

	@Override
	public List listAll(String clazz) {
		return hibernateTemplate.find("from " + clazz + " asc");
	}

	@Override
	public List listAll(String clazz, int pageNo, int pageSize) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hqlString = "from " + clazz + " as c order by c.id desc ";
		List list = hibernateTemplate.executeFind(new HibernateCallback<Object>(){

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hqlString);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo-1) * pSize);
				List result = query.list();
				//淇濊瘉鍔犺浇闄や簡result閲屾墍鏈夊璞�
				if(!Hibernate.isInitialized(result)){
					Hibernate.initialize(result);
				}
				return result;
			}
		});
		return list;
	}

	@Override
	public int countAll(String clazz) {
		final String hql = "select count(*) from " + clazz;
		Long count = (Long)hibernateTemplate.execute(new HibernateCallback<Object>(){

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@Override
	public List query(String hql) {
		final String hql1 = hql;
		return hibernateTemplate.executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql1);
				return query.list();
			}
			
		});
	}

	@Override
	public List query(String hql, int pageNo, int pageSize) {
		final int PNo = pageNo;
		final int PSize = pageSize;
		final String hqlString = hql;
		List list = hibernateTemplate.executeFind(new HibernateCallback<Object>(){

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hqlString);
				query.setMaxResults(PSize);
				query.setFirstResult((PNo-1) * PSize);
				List result = query.list();
				if(!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
			
		});
		return list;
	}

	@Override
	public int countQuery(String hql) {
		final String hql1 = hql;
		Long count = (Long)hibernateTemplate.execute(new HibernateCallback<Object>(){

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql1);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
			
		});
		return count.intValue();
	}

	@Override
	public int update(String hql) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Connection getConnection() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
