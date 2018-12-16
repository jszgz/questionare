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
 * A data access object (DAO) providing persistence and search support for
 * Questype entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wt.po.Questype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class QuestypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(QuestypeDAO.class);
	// property constants
	public static final String TYPENAME = "typename";

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

	public void save(Questype transientInstance) {
		log.debug("saving Questype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Questype persistentInstance) {
		log.debug("deleting Questype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Questype findById(java.lang.Integer id) {
		log.debug("getting Questype instance with id: " + id);
		try {
			Questype instance = (Questype) getCurrentSession().get(
					"com.wt.po.Questype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Questype> findByExample(Questype instance) {
		log.debug("finding Questype instance by example");
		try {
			List<Questype> results = (List<Questype>) getCurrentSession()
					.createCriteria("com.wt.po.Questype").add(create(instance))
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
		log.debug("finding Questype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Questype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Questype> findByTypename(Object typename) {
		return findByProperty(TYPENAME, typename);
	}

	public List findAll() {
		log.debug("finding all Questype instances");
		try {
			String queryString = "from Questype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Questype merge(Questype detachedInstance) {
		log.debug("merging Questype instance");
		try {
			Questype result = (Questype) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Questype instance) {
		log.debug("attaching dirty Questype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Questype instance) {
		log.debug("attaching clean Questype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static QuestypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (QuestypeDAO) ctx.getBean("QuestypeDAO");
	}
}