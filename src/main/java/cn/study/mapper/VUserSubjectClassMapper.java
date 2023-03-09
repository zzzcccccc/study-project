package cn.study.mapper;

import cn.study.entity.VUserSubjectClass;
import cn.study.vo.VUserSubClaVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VUserSubjectClassMapper extends BaseMapper<VUserSubjectClass> {

    List<VUserSubClaVo> selectUserByClassId(@Param("gradeId") Integer gradeId,
                                            @Param("classId") Integer classId);

    Long selectTeacher(@Param("gradeId") Integer gradeId,
                       @Param("subjectId") Integer subjectId,
                       @Param("classId") Integer classId);
}
