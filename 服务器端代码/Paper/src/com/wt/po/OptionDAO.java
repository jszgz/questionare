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
 * Option entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wt.po.Option
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class OptionDAO {
	private static final Logger log = LoggerFactory.getLogger(OptionDAO.class);
	// property constants
	public static final String OPTIONCONTENT = "optioncontent";

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

	public void save(Option transientInstance) {
		log.debug("saving Option instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Option persistentInstance) {
		log.debug("deleting Option instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Option findById(java.lang.Integer id) {
		log.debug("getting Option instance with id: " + id);
		try {
			Option instance = (Option) getCurrentSession().get(
					"com.wt.po.Option", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Option> findByExample(Option instance) {
		log.debug("finding Option instance by example");
		try {
			List<Option> results = (List<Option>) getCurrentSession()
					.createCriteria("com.wt.po.Option").add(create(instance))
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
		log.debug("finding Option instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Option as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Option> findByOptioncontent(Object optioncontent) {
		return findByProperty(OPTIONCONTENT, optioncontent);
	}

	public List findAll() {
		log.debug("finding all Option instances");
		try {
			String queryString = "from Option";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Option merge(Option detachedInstance) {
		log.debug("merging Option instance");
		try {
			Option result = (Option) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Option instance) {
		log.debug("attaching dirty Option instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Option instance) {
		log.debug("attaching clean Option instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OptionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OptionDAO) ctx.getBean("OptionDAO");
	}
}