package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.notifier.config.DatabaseConfigProperties;
import com.onebank.taskmaster.createnotification.repository.MyBatisNotificationPreferenceRepository;
import com.onebank.taskmaster.createnotification.repository.MyBatisNotificationRepository;
import com.onebank.taskmaster.createnotification.repository.NotificationPreferenceMapper;
import com.onebank.taskmaster.createnotification.repository.NotificationMapper;
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
    @Override
    protected void configure() {
        bind(NotificationMapper.class).to(MyBatisNotificationRepository.class).in(Scopes.SINGLETON);
        bind(NotificationPreferenceMapper.class).to(MyBatisNotificationPreferenceRepository.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public SqlSessionFactory buildSqlSessionFactory(final AppConfigProperties appConfigProperties, final DatabaseConfigProperties databaseConfigProperties) {
        DataSource dataSource = new PooledDataSource(databaseConfigProperties.getDriverClassName(), databaseConfigProperties.getUrl(),
                databaseConfigProperties.getUsername(), databaseConfigProperties.getPassword());

        Environment environment = new Environment(appConfigProperties.getProfile(), new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(NotificationMapper.class);
        configuration.addMapper(NotificationPreferenceMapper.class);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(configuration);
    }
}
