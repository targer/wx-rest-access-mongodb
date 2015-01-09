package org.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
* Created by Administrator on 2014/12/10.
*/
@Configuration
public class ApplicationConfiguration {

    @Bean
    public MongoFactoryBean mongoFactoryBean() {
        MongoFactoryBean mongoFactoryBean = new MongoFactoryBean();
        mongoFactoryBean.setHost("192.168.6.78");
        return mongoFactoryBean;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
//        UserCredentials userCredentials = new UserCredentials("admin", "admin");
//        return new SimpleMongoDbFactory(new Mongo(), "test", userCredentials);
        return new SimpleMongoDbFactory(mongoFactoryBean().getObject(), "test"
//                , userCredentials
        );
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
