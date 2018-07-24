package com.example.bootdemo.dao;

import com.example.bootdemo.model.SysRole;
import java.util.List;

public interface SysRoleMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectRoles(SysRole sysRole);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}