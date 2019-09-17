package com.wp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {

	public static SessionFactory getSF(Class className) {
		Configuration config = new Configuration().configure().addAnnotatedClass(className);
		SessionFactory sessionFactory = config.buildSessionFactory();
		return sessionFactory;
	}
}
