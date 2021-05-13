package com.wotrd.dubbo.common.retry.queue;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/13 19:25
 */
public class BdbEnvironment {
    private static final String CLASS_CATALOG = "java_class_catalog";
    private Environment environment;
    private Database catalogDb;
    private StoredClassCatalog javaCatalog;
    private static Map<String, BdbEnvironment> envs = new ConcurrentHashMap<>();
    private static Logger logger = LoggerFactory.getLogger(BdbEnvironment.class);

    public BdbEnvironment(String envHome) throws DatabaseException {
        File home = new File(envHome);
        if (!home.exists()) {
            boolean success = home.mkdirs();
            if (!success) {
                logger.warn("创建目录失败");
                throw new RuntimeException();
            }
        }

        try {
            EnvironmentConfig environmentConfig = new EnvironmentConfig();
            environmentConfig.setTransactional(true);
            environmentConfig.setAllowCreate(true);
            environmentConfig.setDurability(Durability.COMMIT_WRITE_NO_SYNC);
            this.environment = new Environment(home, environmentConfig);
            DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setTransactional(true);
            dbConfig.setAllowCreate(true);
            this.catalogDb = this.environment.openDatabase((Transaction)null, "java_class_catalog", dbConfig);
            this.javaCatalog = new StoredClassCatalog(this.catalogDb);
        } catch (DatabaseException var5) {
            logger.error("创建bdb环境失败, 路径:{}", envHome, var5);
            throw new RuntimeException();
        }
    }

    public void close() {
        if (this.catalogDb != null) {
            this.catalogDb.close();
        }

        if (this.javaCatalog != null) {
            this.javaCatalog.close();
        }

        if (this.environment != null) {
            this.environment.close();
        }

    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public StoredClassCatalog getJavaCatalog() {
        return this.javaCatalog;
    }
}