package com.epms.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.ExternalResume;
import com.epms.Bean.Recruit;
import com.epms.Bean.Resume;

@Repository
public interface ResumeMapper
{
   //插入简历
   int insertResume(Resume resume);
   
   //查找出最大的事项id，即为最新插入的简历id
   int selectMaxId();
   
   //审核简历
   public int updateAllExternalResume(Resume resume);
   
 //通过事项id，查询事项信息
 	public Resume selectResumeById(int id);
   

}
