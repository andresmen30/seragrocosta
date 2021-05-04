/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.config;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class ControlSidebarConfig implements Serializable {

    private final boolean showOnMobile;
    private final boolean fixedLayout;
    private final boolean boxedLayout;
    private final boolean expandOnHover;
    private final boolean sidebarCollapsed;
    private final boolean fixed;
    private final boolean darkSkin;

    public ControlSidebarConfig(boolean showOnMobile, boolean fixedLayout,
            boolean boxedLayout, boolean expandOnHover,
            boolean sidebarCollapsed, boolean fixed, boolean darkSkin) {
        this.showOnMobile = showOnMobile;
        this.fixedLayout = fixedLayout;
        this.boxedLayout = boxedLayout;
        this.expandOnHover = expandOnHover;
        this.sidebarCollapsed = sidebarCollapsed;
        this.fixed = fixed;
        this.darkSkin = darkSkin;
    }

    public Boolean getShowOnMobile() {
        return showOnMobile;
    }

    public Boolean getFixedLayout() {
        return fixedLayout;
    }

    public Boolean getBoxedLayout() {
        return boxedLayout;
    }

    public Boolean getExpandOnHover() {
        return expandOnHover;
    }

    public Boolean getSidebarCollapsed() {
        return sidebarCollapsed;
    }

    public Boolean getFixed() {
        return fixed;
    }

    public Boolean getDarkSkin() {
        return darkSkin;
    }

}
