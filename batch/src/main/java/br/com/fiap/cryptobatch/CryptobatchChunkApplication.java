package br.com.fiap.cryptobatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.logging.Logger;

@SpringBootApplication
@EnableBatchProcessing
public class CryptobatchChunkApplication {

    Logger logger = Logger.getLogger(CryptobatchChunkApplication.class.getSimpleName());

    public static void main(String[] args) {
        SpringApplication.run(CryptobatchChunkApplication.class, args);
    }

    @Bean
    public ItemReader<Crypto> itemReader(@Value("${file.resource}") Resource resource){
        return new FlatFileItemReaderBuilder<Crypto>()
                .name("crypto file reader")
                .resource(resource)
                .targetType(Crypto.class)
                .delimited().delimiter(";").names("name","acronym")
                .build();
    }

    @Bean
    public ItemProcessor<Crypto, Crypto> itemProcessor(){
        return crypto -> {
            logger.info("Processing: "+crypto.getName());
            crypto.setName(crypto.getName().toLowerCase());
            String acronym = crypto.getAcronym();
            if(acronym.length() > 3){
                acronym = acronym.substring(0,3);
            }
            crypto.setAcronym(acronym);
            return crypto;
        };
    }

    @Bean
    public ItemWriter<Crypto> itemWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Crypto>()
                .sql("insert into TB_CRYPTOS(name, acronym) values (:name, :acronym)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory,
                     ItemReader<Crypto> itemReader,
                     ItemProcessor<Crypto, Crypto> itemProcessor,
                     ItemWriter<Crypto> itemWriter,
                     @Value("${file.chunk}") int chunkSize){
        return stepBuilderFactory.get("step chunk process csv")
                .<Crypto, Crypto>chunk(chunkSize)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   Step step){
        return jobBuilderFactory.get("job process file")
                .start(step)
                .build();
    }

}
