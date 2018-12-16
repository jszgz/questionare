package com.wt.po;

import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for Ques
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.wt.po.Ques
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class QuesDAO {
	private static final Logger log = LoggerFactory.getLogger(QuesDAO.class);
	// property constants
	public static final String QUESREQUIRED = "quesrequired";
	public static final String QUESORDER = "quesorder";
	public static final String QUESCONTENT = "quescontent";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Ques transientInstance) {
		log.debug("saving Ques instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Ques persistentInstance) {
		log.debug("deleting Ques instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ques findById(java.lang.Integer id) {
		log.debug("getting Ques instance with id: " + id);
		try {
			Ques instance = (Ques) getCurrentSession()
					.get("com.wt.po.Ques", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Ques> findByExample(Ques instance) {
		log.debug("finding Ques instance by example");
		try {
			List<Ques> results = (List<Ques>) getCurrentSession()
					.createCriteria("com.wt.po.Ques").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Ques instance with property: " + propertyName
				+ ", value: " + value);
		System.out.println("finding Ques instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Ques as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	

	public List<Ques> findByQuesrequired(Object quesrequired) {
		return findByProperty(QUESREQUIRED, quesrequired);
	}

	public List<Ques> findByQuesorder(Object quesorder) {
		return findByProperty(QUESORDER, quesorder);
	}

	public List<Ques> findByQuescontent(Object quescontent) {
		return findByProperty(QUESCONTENT, quescontent);
	}

	public List findAll() {
		log.debug("finding all Ques instances");
		try {
			String queryString = "from Ques";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Ques merge(Ques detachedInstance) {
		log.debug("merging Ques instance");
		try {
			Ques result = (Ques) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Ques instance) {
		log.debug("attaching dirty Ques instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ques instance) {
		log.debug("attaching clean Ques instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static QuesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (QuesDAO) ctx.getBean("QuesDAO");
	}
}