package com.oracle.tour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.oracle.tour.dao.LJPlannerRepository;
import com.oracle.tour.domain.Planner;
import com.oracle.tour.dto.PlannerDTO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/*
 * 이진호
 * 플래너 ServiceImpl 
 */

@Service
public class LJPlannerServiceImpl implements LJPlannerService {
    @Autowired
    private LJPlannerRepository repository;

    @Override
    public PlannerDTO insertPlanner(PlannerDTO dto) throws ParseException {
        Planner entity = dtoToEntity(dto); // plannerService interface의 default 메소드 dtoToEntity 실행
        Planner planner = repository.save(entity); // planner_tbl 저장후 entity 값을 planner entity에 저장
        PlannerDTO plannerDTO = entityToDto(planner); // plannerService interface의 default 메소드 entityToDto 실행 -> plannerDto에 저장
        return plannerDTO; // dto return
    }

    
    /*
     * planList 페이지에서 보여줄 모든 planner 조회 (sessionId 기준으로)
     * */
    @Override
    public List<PlannerDTO> selectPlanners(String id) {
        List<Planner> planners = repository.findAllByIdOrderByPlannerNoDesc(id);
        List<PlannerDTO> dto = new ArrayList<PlannerDTO>();
        for(Planner planner : planners){
            dto.add(entityToDto(planner));
        }
        return dto;
    }

    @Override
    public PlannerDTO selectPlanner(Long plannerNo) {
        Planner planner = repository.findByPlannerNo(plannerNo);
        PlannerDTO dto = entityToDto(planner);
        return  dto;
    }

    @Override
    public void deletePlanner(Long plannerNo) {
        repository.deleteByPlannerNo(plannerNo);
    }

}
