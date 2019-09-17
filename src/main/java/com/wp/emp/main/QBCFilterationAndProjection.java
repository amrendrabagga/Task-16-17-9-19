package com.wp.emp.main;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wp.entity.Emp;
import com.wp.util.Util;

public class QBCFilterationAndProjection {

	public static void main(String[] args) {
		Session session = Util.getSF(Emp.class).openSession();
		System.out.println("FILTERATION USING DEPRECATED METHODS");
		Criteria criteria = session.createCriteria(Emp.class);
		Criterion crt1 =  Restrictions.ge("esal", 50000);
		criteria.add(crt1);
		
		List<Emp> list = criteria.list();
		for(Emp emp : list)
			System.out.println(emp);
		System.out.println("===========================");
		System.out.println("PROJECTIONS AFTER FILTERATION ");
		
		ProjectionList projectionList = Projections.projectionList();
		Projection prj1 = Projections.property("eno");
		Projection prj2 = Projections.property("ename");
		projectionList.add(prj1);projectionList.add(prj2);
		criteria.setProjection(projectionList);
		
		List<Object[]> prjList = criteria.list();
		for(Object[] arr : prjList) {
			System.out.println("ENO and ENAME are ");
			for(Object ob : arr)
				System.out.println(ob);
			System.out.println("-----------------------------");
		}
		
		System.out.println("============================");
		System.out.println("FILTERATION USING CRITERIABUILDER ");
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<Emp> criteriaQuery1 = criteriaBuilder.createQuery(Emp.class);
		Root<Emp> root1 = criteriaQuery1.from(Emp.class);
		
		criteriaQuery1.select(root1).where(criteriaBuilder.ge(root1.<Integer>get("esal"), 50000));
		
		TypedQuery<Emp> typedQuery1 = session.createQuery(criteriaQuery1);
		List<Emp> list1 = typedQuery1.getResultList();
		
		for(Emp emp1 : list1)
			System.out.println(emp1);
		
		System.out.println("============================");
		System.out.println("USING PREDICATES WITH CRITERIA");
		//building and operation using criteria
		//Note Predicate are sub interface of Expression
		Predicate p1 = criteriaBuilder.gt(root1.<Integer>get("esal"),20000);
		Predicate p2 = criteriaBuilder.lt(root1.<Integer>get("esal"),100000);
		//Note we can take path as predicate only if its type is boolean like
		//Path<Boolean> p3 = root1.<Boolean>get("isMarried");
		//then criteriaBuilder.and(p1,p2,p3) will check salary and isMarried true or not
		criteriaQuery1.select(root1).where(criteriaBuilder.and(p1,p2));//and() has Predicate vargs o we can pass multiple parameters
		TypedQuery<Emp> typedQuery2 = session.createQuery(criteriaQuery1);
		List<Emp> list2 = typedQuery2.getResultList();
		
		for(Emp emp2 : list2)
			System.out.println(emp2);
		
		System.out.println("===========================");
		System.out.println("PROJECTIONS USING CRITERIABUILDER");
		
		CriteriaQuery<Emp> criteriaQuery2 = criteriaBuilder.createQuery(Emp.class);
		Root<Emp> root2 = criteriaQuery2.from(Emp.class);
		
		criteriaQuery2.multiselect(root2.<Integer>get("eno"),root2.<String>get("ename"));
		List<Emp> list3 = session.createQuery(criteriaQuery2).getResultList();//here it is must to have constructor with specified fields in multiselect
		for(Emp emp3 : list3)
			System.out.println("ENO ->"+emp3.getEno() + " ENAME ->"+emp3.getEname());
		
		System.out.println("============================");
		System.out.println("FOR RETRIEVING SINGLE COLUMN(SALARY)");
		
		CriteriaQuery<Integer> criteriaQuery3 = criteriaBuilder.createQuery(Integer.class);
		Root<Emp> root3 = criteriaQuery3.from(Emp.class);
		criteriaQuery3.select(root3.<Integer>get("esal"));
		List<Integer> list4 = session.createQuery(criteriaQuery3).getResultList();
		System.out.println("ESAL IS");
		for(Integer esal : list4)
			System.out.println(esal);
	
		System.out.println("============================");
		System.out.println("USING AGGREGATE FUNCTIONS");
		//Query is - select esal,count(esal) from emp;
		CriteriaQuery<Object[]> criteriaQuery4 = criteriaBuilder.createQuery(Object[].class);
		Root<Emp> root4 = criteriaQuery4.from(Emp.class);
		//criteriaQuery4.multiselect(root4.<Integer>get("esal"),criteriaBuilder.count(root4))); is also correct
		criteriaQuery4.multiselect(root4.<Integer>get("esal"),criteriaBuilder.count(root4.<Integer>get("esal")));
		criteriaQuery4.groupBy(root4.<Integer>get("esal"));
		List<Object[]> list5 = session.createQuery(criteriaQuery4).getResultList();
		
		System.out.println("SELECT esal,COUNT(esal) FROM emp GROUP BY esal;");
		for(Object[] arr : list5) {
			for(Object ob : arr)
				System.out.print("\t"+ob);
			System.out.println();
		}
		session.close();
	}

}
