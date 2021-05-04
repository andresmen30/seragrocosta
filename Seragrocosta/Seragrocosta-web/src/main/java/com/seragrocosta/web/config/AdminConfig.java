/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seragrocosta.web.config;

import static com.seragrocosta.web.util.Assert.has;
import com.seragrocosta.web.util.Constants;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author USER
 */
@Named
@ApplicationScoped
public class AdminConfig implements Serializable {

    @Inject
    private BundleConfig bundleConfig;

    private static final long serialVersionUID = 834212776758014169L;

    private String loginPage;
    private String indexPage;
    private String dateFormat;
    private String templatePath;
    private Integer breadCrumbMaxSize;
    private boolean renderMessages;
    private boolean renderAjaxStatus;
    private boolean disableFilter;
    private boolean enableRipple;
    private boolean renderBreadCrumb;
    private boolean extensionLessUrls;
    private boolean enableSlideMenu;
    private String rippleElements;
    private String skin;
    private boolean autoShowNavbar;
    private String ignoredResources;
    private String loadingImage;
    private boolean renderControlSidebar;
    private boolean leftMenuTemplate;
    private boolean renderMenuSearch;
    private boolean renderFormAsterisks;
    private boolean closableLoading;
    private boolean enableMobileHeader;

    //controlsidebar
    private ControlSidebarConfig controlSidebar;
    private String pageSuffix;
    private boolean rippleMobileOnly;
    private String messagesHideTimeout;
    private boolean autoHideMessages;
    private boolean iconsEffect;

    @PostConstruct
    public void init() {
        loadDefaults();
    }

    protected void loadDefaults() {
        loginPage = bundleConfig.loadBublePropertie("adm", "admin.loginPage");
        indexPage = bundleConfig.loadBublePropertie("adm", "admin.indexPage");
        dateFormat = bundleConfig.loadBublePropertie("adm", "admin.dateFormat");
        if (!has(dateFormat)) {
            dateFormat = ((SimpleDateFormat) DateFormat.getDateTimeInstance()).toLocalizedPattern();
        }
        breadCrumbMaxSize = Integer.parseInt(bundleConfig.loadBublePropertie("adm", "admin.breadcrumbSize"));
        renderMessages = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.renderMessages"));
        renderAjaxStatus = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.renderAjaxStatus"));
        disableFilter = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.disableFilter"));
        enableRipple = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.enableRipple"));
        renderBreadCrumb = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.renderBreadCrumb"));
        extensionLessUrls = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.extensionLessUrls"));
        rippleElements = bundleConfig.loadBublePropertie("adm", "admin.rippleElements");
        enableSlideMenu = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.enableSlideMenu"));
        skin = bundleConfig.loadBublePropertie("adm", "admin.skin");
        autoShowNavbar = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.autoShowNavbar"));
        autoHideMessages = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.autoHideMessages"));
        iconsEffect = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.iconsEffect"));
        loadingImage = bundleConfig.loadBublePropertie("adm", "admin.loadingImage");
        renderControlSidebar = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.renderControlSidebar"));
        rippleMobileOnly = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.rippleMobileOnly"));
        renderMenuSearch = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.renderMenuSearch"));
        renderFormAsterisks = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.renderFormAsterisks"));
        enableMobileHeader = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.enableMobileHeader"));
        closableLoading = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.closableLoading"));
        messagesHideTimeout = bundleConfig.loadBublePropertie("adm", "admin.messagesHideTimeout");
        leftMenuTemplate = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.controlSidebar.leftMenuTemplate"));
        boolean controlSidebarShowOnMobile = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.controlSidebar.showOnMobile"));
        boolean fixedLayout = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.controlSidebar.fixedLayout"));
        boolean boxedLayout = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.controlSidebar.boxedLayout"));
        boolean expandOnHover = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.controlSidebar.expandOnHover"));
        boolean sidebarCollapsed = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.controlSidebar.sidebarCollapsed"));
        boolean fixedControlSidebar = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.controlSidebar.fixed"));
        boolean darkControlSidebarSkin = Boolean.parseBoolean(bundleConfig.loadBublePropertie("adm", "admin.controlSidebar.darkSkin"));
        controlSidebar = new ControlSidebarConfig(controlSidebarShowOnMobile, fixedLayout, boxedLayout, expandOnHover, sidebarCollapsed, fixedControlSidebar, darkControlSidebarSkin);
    }

    /**
     * infer page suffix from index and login page configured in
     * admin-config.properties
     *
     * If none is configured then use default suffix: 'xhtml'.
     *
     * @return the page suffix
     */
    public String getPageSufix() {
        if (has(pageSuffix)) {
            return pageSuffix;
        }
        if (!has(indexPage) && !has(loginPage)) {
            pageSuffix = Constants.DEFAULT_PAGE_FORMAT;
        }
        if (has(indexPage)) {
            pageSuffix = indexPage.substring(indexPage.lastIndexOf('.') + 1);
        } else {
            pageSuffix = indexPage.substring(loginPage.lastIndexOf('.') + 1);
        }
        return pageSuffix;
    }

    public void restoreDefaults() {
        loadDefaults();
    }

    public String getLoginPage() {
        return loginPage;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public boolean isIconsEffect() {
        return iconsEffect;
    }

    public void setIconsEffect(boolean iconsEffect) {
        this.iconsEffect = iconsEffect;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }

    public boolean isDisableFilter() {
        return disableFilter;
    }

    public void setDisableFilter(boolean disableFilter) {
        this.disableFilter = disableFilter;
    }

    public boolean isLeftMenuTemplate() {
        return leftMenuTemplate;
    }

    public boolean isRenderMenuSearch() {
        return renderMenuSearch;
    }

    public void setRenderMenuSearch(boolean renderMenuSearch) {
        this.renderMenuSearch = renderMenuSearch;
    }

    public boolean isAutoHideMessages() {
        return autoHideMessages;
    }

    public void setAutoHideMessages(boolean autoHideMessages) {
        this.autoHideMessages = autoHideMessages;
    }

    public boolean isRenderFormAsterisks() {
        return renderFormAsterisks;
    }

    public void setRenderFormAsterisks(boolean renderFormAsterisks) {
        this.renderFormAsterisks = renderFormAsterisks;
    }

    public String getMessagesHideTimeout() {
        return messagesHideTimeout;
    }

    public void setMessagesHideTimeout(String messagesHideTimeout) {
        this.messagesHideTimeout = messagesHideTimeout;
    }

    public void setLeftMenuTemplate(boolean leftMenuTemplate) {
        this.leftMenuTemplate = leftMenuTemplate;
    }

    public ControlSidebarConfig getControlSidebar() {
        return controlSidebar;
    }

    public void setControlSidebar(ControlSidebarConfig controlSidebarConfig) {
        this.controlSidebar = controlSidebarConfig;
    }

    public boolean isEnableMobileHeader() {
        return enableMobileHeader;
    }

    public void setEnableMobileHeader(boolean enableMobileHeader) {
        this.enableMobileHeader = enableMobileHeader;
    }

    public boolean isRippleMobileOnly() {
        return rippleMobileOnly;
    }

    public void setRippleMobileOnly(boolean rippleEffectMobileOnly) {
        this.rippleMobileOnly = rippleEffectMobileOnly;
    }

    @Deprecated
    /**
     * @deprecated use LayoutMB#template
     */
    public String getTemplatePath() {
        return templatePath;
    }

    public Integer getBreadCrumbMaxSize() {
        return breadCrumbMaxSize;
    }

    public void setBreadCrumbMaxSize(Integer breadCrumbMaxSize) {
        this.breadCrumbMaxSize = breadCrumbMaxSize;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public boolean isRenderMessages() {
        return renderMessages;
    }

    public void setRenderMessages(boolean renderMessages) {
        this.renderMessages = renderMessages;
    }

    public boolean isRenderAjaxStatus() {
        return renderAjaxStatus;
    }

    public void setRenderAjaxStatus(boolean renderAjaxStatus) {
        this.renderAjaxStatus = renderAjaxStatus;
    }

    public boolean isEnableRipple() {
        return enableRipple;
    }

    public void setEnableRipple(boolean enableRipple) {
        this.enableRipple = enableRipple;
    }

    public boolean isRenderBreadCrumb() {
        return renderBreadCrumb;
    }

    public void setRenderBreadCrumb(boolean renderBreadCrumb) {
        this.renderBreadCrumb = renderBreadCrumb;
    }

    public boolean isExtensionLessUrls() {
        return extensionLessUrls;
    }

    public void setExtensionLessUrls(boolean extensionLessUrls) {
        this.extensionLessUrls = extensionLessUrls;
    }

    public String getRippleElements() {
        return rippleElements;
    }

    public void setRippleElements(String rippleElements) {
        this.rippleElements = rippleElements;
    }

    public boolean isEnableSlideMenu() {
        return enableSlideMenu;
    }

    public void setEnableSlideMenu(boolean enableSlideMenu) {
        this.enableSlideMenu = enableSlideMenu;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public boolean isAutoShowNavbar() {
        return autoShowNavbar;
    }

    public void setAutoShowNavbar(boolean autoShowNavbar) {
        this.autoShowNavbar = autoShowNavbar;
    }

    public String getIgnoredResources() {
        return ignoredResources;
    }

    public void setIgnoredResources(String ignoredResources) {
        this.ignoredResources = ignoredResources;
    }

    public String getLoadingImage() {
        return loadingImage;
    }

    public void setLoadingImage(String loadingImage) {
        this.loadingImage = loadingImage;
    }

    public boolean isRenderControlSidebar() {
        return renderControlSidebar;
    }

    public void setRenderControlSidebar(boolean renderControlSidebar) {
        this.renderControlSidebar = renderControlSidebar;
    }

    public boolean isClosableLoading() {
        return closableLoading;
    }

    public void setClosableLoading(boolean closableLoading) {
        this.closableLoading = closableLoading;
    }

}
