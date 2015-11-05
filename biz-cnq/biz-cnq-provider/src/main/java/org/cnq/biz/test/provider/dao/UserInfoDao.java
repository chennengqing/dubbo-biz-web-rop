package org.cnq.biz.test.provider.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.cnq.biz.test.entity.UserInfo;
import org.cnq.biz.test.entity.UserInfoExample;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}