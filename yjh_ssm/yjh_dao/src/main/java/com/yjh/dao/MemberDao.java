package com.yjh.dao;

import com.yjh.pojo.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

    @Select("select * from member where id=#{memberId}")
    Member findByMemberId(String memberId)throws Exception;
}
