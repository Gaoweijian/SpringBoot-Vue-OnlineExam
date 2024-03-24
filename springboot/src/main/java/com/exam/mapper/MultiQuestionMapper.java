package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

//选择题
@Mapper
public interface MultiQuestionMapper {
    /**
     * select * from multiquestions where questionId in (
     * select questionId from papermanage where questionType = 1 and paperId = 1001
     * )
     */
    @Select("select * from multi_question where 1=1 and multiType = #{multiType} and questionId in (select questionId from paper_manage where questionType = 1 and paperId = #{paperId})")
    List<MultiQuestion> findByIdAndType(@Param("paperId") Integer paperId,@Param("multiType") Integer multiType);

    @Select("select * from multi_question")
    IPage<MultiQuestion> findAll(Page page);

    /**
     * 查询最后一条记录的questionId
     *
     * @return MultiQuestion
     */
    @Select("select questionId from multi_question order by questionId desc limit 1")
    MultiQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true, keyProperty = "questionId")
    @Insert("insert into multi_question(subject,question,answerA,answerB,answerC,answerD,answerE,answerF,rightAnswer,analysis,section,level,multiType) " +
            "values(#{subject},#{question},#{answerA},#{answerB},#{answerC},#{answerD},#{answerE},#{answerF},#{rightAnswer},#{analysis},#{section},#{level},#{multiType})")
    int add(MultiQuestion multiQuestion);

    @Select("select questionId from multi_question  where subject =#{subject} and multiType=#{multiType} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject, Integer pageNo, Integer multiType);

    @Select("select * from multi_question t where t.questionId = #{questionId}")
    MultiQuestion findQuestionById(String questionId);

    @Update("update multi_question set  subject=#{subject},question=#{question},answerA=#{answerA},answerB=#{answerB},answerC=#{answerC},answerD=#{answerD}," +
            "answerE=#{answerE}," +
            "answerF=#{answerF},rightAnswer=#{rightAnswer},analysis=#{analysis},section=#{section},level=#{level} where questionId = #{questionId} ")
    int edit(MultiQuestion multiQuestion);
}
