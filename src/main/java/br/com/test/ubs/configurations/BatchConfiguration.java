package br.com.test.ubs.configurations;

import br.com.test.ubs.jobs.mappers.UbsFieldMapper;
import br.com.test.ubs.listeners.CustomJobListener;
import br.com.test.ubs.models.Ubs;
import br.com.test.ubs.repositories.UbsRepository;
import org.springframework.batch.core.Job;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private UbsRepository ubsRepository;

    @Autowired
    private UbsFieldMapper ubsFieldMapper;

    @Bean
    public FlatFileItemReader<Ubs> reader() {
        String[] headerNames = new String[]{"vlr_latitude", "vlr_longitude", "cod_munic", "cod_cnes", "nom_estab",
                "dsc_endereco", "dsc_bairro", "dsc_cidade", "dsc_telefone", "dsc_estrut_fisic_ambiencia",
                "dsc_adap_defic_fisic_idosos", "dsc_equipamentos", "dsc_medicamentos"};
        return new FlatFileItemReaderBuilder<Ubs>()
                .name("ubsItemReader")
                .resource(new ClassPathResource("ubs.csv"))
                .linesToSkip(1)
                .delimited()
                .names(headerNames)
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Ubs>() {{
                    setTargetType(Ubs.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<Ubs> lineMapper() {
        String[] headerNames = new String[]{"vlr_latitude", "vlr_longitude", "cod_munic", "cod_cnes", "nom_estab",
                "dsc_endereco", "dsc_bairro", "dsc_cidade", "dsc_telefone", "dsc_estrut_fisic_ambiencia",
                "dsc_adap_defic_fisic_idosos", "dsc_equipamentos", "dsc_medicamentos"};
        final DefaultLineMapper<Ubs> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(headerNames);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(ubsFieldMapper);

        return defaultLineMapper;
    }

    @Bean
    public RepositoryItemWriter<Ubs> writer() {
        RepositoryItemWriter<Ubs> repositoryItemWriter = new RepositoryItemWriter();
        repositoryItemWriter.setRepository(ubsRepository);
        repositoryItemWriter.setMethodName("save");
        return repositoryItemWriter;
    }

    @Bean
    public Step step1(RepositoryItemWriter<Ubs> writer) {
        return stepBuilderFactory.get("step1")
                .<Ubs, Ubs>chunk(10)
                .reader(reader())
                .writer(writer)
                .build();
    }

    @Bean
    public Job job(CustomJobListener listener, Step step) {
        return jobBuilderFactory.get("ubsProcessJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

}