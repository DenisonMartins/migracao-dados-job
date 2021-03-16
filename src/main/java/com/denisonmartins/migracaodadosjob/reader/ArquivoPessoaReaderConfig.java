package com.denisonmartins.migracaodadosjob.reader;

import com.denisonmartins.migracaodadosjob.domain.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Configuration
public class ArquivoPessoaReaderConfig {

    @Bean
    public FlatFileItemReader<Pessoa> arquivoPessoaReader() {
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("arquivoPessoaReader")
                .resource(new FileSystemResource("files/pessoa.csv"))
                .delimited()
                .names("nome","email","dataNascimento","idade","id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private FieldSetMapper<Pessoa> fieldSetMapper() {
        return fieldSet -> {
            var pessoa = new Pessoa();
            pessoa.setNome(fieldSet.readString("nome"));
            pessoa.setEmail(fieldSet.readString("email"));
            pessoa.setDataNascimento(LocalDate.parse(fieldSet.readString("dataNascimento"), ISO_LOCAL_DATE));
            pessoa.setIdade(fieldSet.readInt("idade"));
            pessoa.setId(fieldSet.readLong("id"));
            return pessoa;
        };
    }
}
