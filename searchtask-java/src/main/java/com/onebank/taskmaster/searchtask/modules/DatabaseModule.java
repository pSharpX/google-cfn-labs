package com.onebank.taskmaster.searchtask.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.onebank.taskmaster.searchtask.config.AppConfigProperties;
import com.onebank.taskmaster.searchtask.config.FunctionConfigProperties;
import com.onebank.taskmaster.searchtask.repository.TagRepository;
import com.onebank.taskmaster.searchtask.repository.TaskRepository;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.Optional;

public class DatabaseModule extends AbstractModule {
    @Provides
    @Singleton
    public Optional<SqlSessionFactory> buildSqlSessionFactory(AppConfigProperties appConfigProperties, FunctionConfigProperties functionConfigProperties) {
        if (!appConfigProperties.getDatabase().isEnabled()) {
            return Optional.empty();
        }
        DataSource dataSource = new PooledDataSource(functionConfigProperties.getDriverClassName(), functionConfigProperties.getUrl(),
                functionConfigProperties.getUsername(), functionConfigProperties.getPassword());

        Environment environment = new Environment(appConfigProperties.getProfile(), new JdbcTransactionFactory(), dataSource);

        Configuration configuration = new Configuration(environment);
        configuration.addMapper(TagRepository.class);
        configuration.addMapper(TaskRepository.class);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return Optional.of(builder.build(configuration));
    }
}
