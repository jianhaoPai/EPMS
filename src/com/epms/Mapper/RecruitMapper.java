package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Recruit;
import com.epms.Bean.User;
//招聘计划发布
@Repository
public interface RecruitMapper {
	
	//提交招聘计划
	int insertRecruit(Recruit recruit);
	int checkIfRepect(Recruit recruit);
	
	//上级查看下级提交的招聘计划
	List<Recruit> selectRecruitToTotalManager(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("status") String status,@Param("before") int before,@Param("after") int after);
	int countToTotalManager(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("status") String status);
	
	List<Recruit> selectRecruitToBoard(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("status") String status,@Param("before") int before,@Param("after") int after);
	int countToBoard(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("status") String status);
	
	//下级查看自己提交给上级的招聘计划
	List<Recruit> selectAllRecruitByWriteId(@Param("before") int before,@Param("after") int after,@Param("writeId") int writeId);
	int countSelectAllRecruitByWriteId(int writeId);
	
	//审批招聘计划
	public int updateRecruitStatus(Recruit recruit);
	
	//通过事项id，查询事项信息
	public Recruit selectRecruitById(int id);
	
	//员工查询招聘计划
	List<Recruit> selectAllRecruitToEmployee(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("before") int before,@Param("after") int after);
	int countSelectAllRecruitToEmployee(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId);
	



	



}
