package com.sl.service.impl;

import com.sl.model.GroupInfo;
import com.sl.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.sl.constant.URLConst.*;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<GroupInfo> getGroupInfo() {
        List<GroupInfo> groupInfoList = (List<GroupInfo>) restTemplate.getForObject(URL + GET_GROUP_LIST, GroupInfo.class);
        return groupInfoList;
    }
}
