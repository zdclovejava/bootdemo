package com.example.bootdemo.model;

import java.io.Serializable;

public class SysMenu extends PageParam implements Serializable{
   
	private static final long serialVersionUID = 1L;

	private Integer menuId;

    private String menuCode;

    private String menuName;

    private String menuUrl;

    private Integer parentId;

    private Integer menuLevel;

    private String menuIco;

    private Integer menuRdx;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco == null ? null : menuIco.trim();
    }

    public Integer getMenuRdx() {
        return menuRdx;
    }

    public void setMenuRdx(Integer menuRdx) {
        this.menuRdx = menuRdx;
    }
}