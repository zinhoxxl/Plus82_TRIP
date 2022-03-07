package com.oracle.tour.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.domain.Plan;

import java.util.Date;
import java.util.List;

/*
 * 이진호
 * 플랜 관련 Repository 
 */
public interface LJPlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByPlannerNoAndDayBetweenOrderByPlanNo(Long plannerNo, Date start,Date end);
    List<Plan> findAllByPlannerNoOrderByPlanNo(Long plannerNo);
    @Transactional // 삭제후 커밋처리 되야하기 때문에 이를 자동으로 수행 해주는 Transactional 어노테이션 사용
    void deleteAllByPlannerNo(Long plannerNo);
    @Transactional // 삭제후 커밋처리 되야하기 때문에 이를 자동으로 수행 해주는 Transactional 어노테이션 사용
    void deleteByPlanNo(Long planNo);
}
