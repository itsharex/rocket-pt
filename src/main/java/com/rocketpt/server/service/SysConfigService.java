package com.rocketpt.server.service;

import com.alibaba.fastjson.JSON;
import com.rocketpt.server.service.mail.SmtpConfig;
import com.rocketpt.server.service.sys.SystemConfigService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 系统配置
 *
 * @author plexpt
 */
@Service
@RequiredArgsConstructor
public class SysConfigService {

    final SystemConfigService systemConfigService;

    /**
     * @return 邮箱配置
     */
    public SmtpConfig getSmtpConfig() {
        String key = "smtp_config";

        String string = systemConfigService.getString(key);

        try {
            SmtpConfig config = JSON.parseObject(string, SmtpConfig.class);

            return config;
        } catch (Exception e) {
        }

        return null;
    }


    /**
     * @return 两步验证开启？
     */
    public Boolean getTotp() {

        String totpEnable = systemConfigService.getString("totp_enable");

        if ("1".equals(totpEnable)) {
            return true;
        }

        return false;
    }

    public Boolean getUseAbsolutePath() {
        String totpEnable = systemConfigService.getString("use_absolute_path");

        if ("1".equals(totpEnable)) {
            return true;
        }

        return false;
    }

    public String getTorrentStoragePath() {
        return getStringOrDefault("torrent_storage_path", "/torrent/");
    }

    public int getIntOrDefault(String key, int defaultValue) {

        String value = systemConfigService.getString(key);

        Integer integer = defaultValue;
        try {
            integer = Integer.valueOf(value);
        } catch (Exception e) {
        }

        return integer;
    }

    /**
     *
     */
    public String getStringOrDefault(String key, String defaultValue) {

        String string = systemConfigService.getString(key);
        if (StringUtils.isEmpty(string)) {
            return defaultValue;
        }

        return string;
    }
}

