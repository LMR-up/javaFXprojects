package com.lmr.service;

import com.lmr.pojo.UserTran;
import com.lmr.vo.UserTranVo;

import java.util.List;

public interface UserTranService {
    int getPageCount(UserTranVo userTranVo);

    List<UserTran> page(Integer currentPage, Integer pageSize, UserTranVo userTranVo);

    boolean deleteById(int id);

    String findImagePath(int id);
}
