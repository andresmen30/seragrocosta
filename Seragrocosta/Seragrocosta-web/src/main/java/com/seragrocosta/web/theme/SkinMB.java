package com.seragrocosta.web.theme;

import com.seragrocosta.web.config.AdminConfig;
import static com.seragrocosta.web.util.Assert.has;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by rmpestano on 07/01/17.
 */
@Named
@SessionScoped
public class SkinMB implements Serializable {

    private String skin;

    @Inject
    AdminConfig adminConfig;

    @PostConstruct
    public void init() {
        skin = adminConfig.getSkin();
        if (!has(skin)) {
            skin = "skin-blue";
        }
    }

    public void changeSkin(String skin) {
        this.skin = skin;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
