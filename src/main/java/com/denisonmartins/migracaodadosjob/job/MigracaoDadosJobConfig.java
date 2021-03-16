package com.denisonmartins.migracaodadosjob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class MigracaoDadosJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    public MigracaoDadosJobConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public Job migracaoDadosJob(@Qualifier("migrarPessoaStep") Step migrarPessoaStep,
                                @Qualifier("migrarDadosBancariosStep") Step migrarDadosBancariosStep) {
        return jobBuilderFactory
                .get("migracaoDadosJob")
                .start(migrarPessoaStep)
                .next(migrarDadosBancariosStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
