package org.cnq.biz.test.provider.service.impl;

import javax.annotation.Resource;

import org.cnq.biz.test.entity.UserInfo;
import org.cnq.biz.test.entity.UserInfoExample;
import org.cnq.biz.test.provider.dao.UserInfoDao;
import org.cnq.biz.test.provider.service.UserInfoService;
import org.springframework.stereotype.Service;

import com.appleframework.exception.AppleException;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;
	
	@Override
	public UserInfo getById(Integer userId) throws AppleException {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andIdEqualTo(userId);
		return userInfoDao.selectByExample(example).get(0);
	}

}
