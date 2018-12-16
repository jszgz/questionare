package com.wt.po;

import java.sql.Timestamp;
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
 * Survey entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wt.po.Survey
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SurveyDAO {
	private static final Logger log = LoggerFactory.getLogger(SurveyDAO.class);
	// property constants
	public static final String SURVEYNAME = "surveyname";
	public static final String SURVEYDESCRIPTION = "surveydescription";
	public static final String SURVEYPERORATION = "surveyperoration";

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

	public void save(Survey transientInstance) {
		log.debug("saving Survey instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Survey persistentInstance) {
		log.debug("deleting Survey instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Survey findById(java.lang.Integer id) {
		log.debug("getting Survey instance with id: " + id);
		try {
			Survey instance = (Survey) getCurrentSession().get(
					"com.wt.po.Survey", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Survey> findByExample(Survey instance) {
		log.debug("finding Survey instance by example");
		try {
			List<Survey> results = (List<Survey>) getCurrentSession()
					.createCriteria("com.wt.po.Survey").add(create(instance))
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
		log.debug("finding Survey instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Survey as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Survey> findBySurveyname(Object surveyname) {
		return findByProperty(SURVEYNAME, surveyname);
	}

	public List<Survey> findBySurveydescription(Object surveydescription) {
		return findByProperty(SURVEYDESCRIPTION, surveydescription);
	}

	public List<Survey> findBySurveyperoration(Object surveyperoration) {
		return findByProperty(SURVEYPERORATION, surveyperoration);
	}

	public List findAll() {
		log.debug("finding all Survey instances");
		try {
			String queryString = "from Survey";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Survey merge(Survey detachedInstance) {
		log.debug("merging Survey instance");
		try {
			Survey result = (Survey) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Survey instance) {
		log.debug("attaching dirty Survey instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Survey instance) {
		log.debug("attaching clean Survey instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SurveyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SurveyDAO) ctx.getBean("SurveyDAO");
	}
}