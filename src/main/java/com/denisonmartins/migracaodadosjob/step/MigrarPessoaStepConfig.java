package com.denisonmartins.migracaodadosjob.step;

import com.denisonmartins.migracaodadosjob.domain.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrarPessoaStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public MigrarPessoaStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step migrarPessoaStep(ItemReader<Pessoa> arquivoPessoaReader,
                                 ItemWriter<Pessoa> bancoPessoaWriter) {
        return stepBuilderFactory
                .get("migrarPessoaStep")
                .<Pessoa, Pessoa>chunk(1)
                .reader(arquivoPessoaReader)
                .writer(bancoPessoaWriter)
                .build();
    }
}
