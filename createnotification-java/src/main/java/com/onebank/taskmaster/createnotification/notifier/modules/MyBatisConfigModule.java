package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.notifier.config.DatabaseConfigProperties;
import com.onebank.taskmaster.createnotification.repository.NotificationPreferenceRepository;
import com.onebank.taskmaster.createnotification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class MyBatisConfigModule extends AbstractModule {
    @Provides
    @Singleton
    public SqlSessionFactory buildSqlSessionFactory(AppConfigProperties appConfigProperties, DatabaseConfigProperties databaseConfigProperties) {
        DataSource dataSource = new PooledDataSource(databaseConfigProperties.getDriverClassName(), databaseConfigProperties.getUrl(),
                databaseConfigProperties.getUsername(), databaseConfigProperties.getPassword());

        Environment environment = new Environment(appConfigProperties.getProfile(), new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(NotificationRepository.class);
        configuration.addMapper(NotificationPreferenceRepository.class);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(configuration);
    }
}
