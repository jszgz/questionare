package com.wt.po;

import java.util.List;
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
 * A data access object (DAO) providing persistence and search support for
 * Answer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wt.po.Answer
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AnswerDAO {
	private static final Logger log = LoggerFactory.getLogger(AnswerDAO.class);
	// property constants
	public static final String ANSWERCONTENT = "answercontent";

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

	public void save(Answer transientInstance) {
		log.debug("saving Answer instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Answer persistentInstance) {
		log.debug("deleting Answer instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Answer findById(java.lang.Integer id) {
		log.debug("getting Answer instance with id: " + id);
		try {
			Answer instance = (Answer) getCurrentSession().get(
					"com.wt.po.Answer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Answer> findByExample(Answer instance) {
		log.debug("finding Answer instance by example");
		try {
			List<Answer> results = (List<Answer>) getCurrentSession()
					.createCriteria("com.wt.po.Answer").add(create(instance))
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
		log.debug("finding Answer instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Answer as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Answer> findByAnswercontent(Object answercontent) {
		return findByProperty(ANSWERCONTENT, answercontent);
	}

	public List findAll() {
		log.debug("finding all Answer instances");
		try {
			String queryString = "from Answer";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Answer merge(Answer detachedInstance) {
		log.debug("merging Answer instance");
		try {
			Answer result = (Answer) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Answer instance) {
		log.debug("attaching dirty Answer instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Answer instance) {
		log.debug("attaching clean Answer instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AnswerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AnswerDAO) ctx.getBean("AnswerDAO");
	}
}