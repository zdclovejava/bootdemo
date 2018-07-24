package com.example.bootdemo.dao;

import com.example.bootdemo.model.SysMenu;

import java.util.List;

public interface SysMenuMapper {

    int deleteByPrimaryKey(Integer menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectMenus(SysMenu sysMenu);

    SysMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}