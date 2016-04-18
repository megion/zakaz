package org.megion.system1.impl;

import org.megion.system1.api.System1Service;
import org.megion.system1.impl.entity.Member;
import org.megion.system1.impl.repository.MemberRepository;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by izadorozhny on 18.04.2016.
 */
@Remote
@Stateless(name = "System1Service")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class System1ServiceImpl implements System1Service {

//    @Inject
//    Logger log;
//
//    @Inject
//    MemberRepository memberRepository;

    @PersistenceContext(unitName = "zakaz-Unit")
    EntityManager em;

    @Override
    public String doWork1() {
        List<Member> list1 = findAllOrderedByName();
//        log.info("list1: " + list1);

        return "call doWork1";
    }

    private List<Member> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }
}
