package com.sl.service;

import com.sl.model.GroupInfo;

import java.util.List;

public interface GroupService {
    /**
     * 获取群
     *
     * @return
     */
    List<GroupInfo> getGroupInfo();
}
