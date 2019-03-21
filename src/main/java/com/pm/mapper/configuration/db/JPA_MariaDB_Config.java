package com.pm.mapper.configuration.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//빈 설정 정보가 포함된 클래스이다
@Configuration
//트랜잭션 관리
@EnableTransactionManagement
//JPA저장소 관리
@EnableJpaRepositories(
        //저장소의 경로를 지정해줘야 여기만 검색한다. 이게 없으면 DB를 여기저기 만든다.
        basePackages = "com.pm.mapper.repository",
        transactionManagerRef = "mariaDB_transactionManager",
        entityManagerFactoryRef = "mariaDB_entityManagerFactory"
)

public class JPA_MariaDB_Config {
    //pk설정
    @Primary
    //빈 객체를 등록해준다.
    @Bean(name = "maria_dataSource")
    //yml 환경설정의 속성을 가져온다. spring아래 data아래 maria 속성 전부다
    @ConfigurationProperties("spring.data.maria")
    public DataSource mariaDataSource() {
        //저 기반으로 데이터 소스를 만들어서 커넥션풀에 만든다.
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
//  데이터 소스를 받아와서 정보를 넣는다. entitiy매니저에
    @Bean(name = "mariaDB_entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            //@선택자로 해서 빈을 가져왔다.
            @Qualifier("maria_dataSource") DataSource dataSource) {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return builder.dataSource(dataSource)
                .packages("com.pm.mapper.model")
                .properties(map)
                .build();
    }


    @Primary
    @Bean(name = "mariaDB_transactionManager")
    //트랜잭션 관리를 해주는 메서드
    public PlatformTransactionManager transactionManager(
            @Qualifier("mariaDB_entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
