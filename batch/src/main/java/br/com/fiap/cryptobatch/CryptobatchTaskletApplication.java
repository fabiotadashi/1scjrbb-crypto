//package br.com.fiap.cryptobatch;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.io.File;
//import java.nio.file.Paths;
//import java.util.logging.Logger;
//
//@SpringBootApplication
//@EnableBatchProcessing
//public class CryptobatchTaskletApplication {
//
//    Logger logger = Logger.getLogger(CryptobatchTaskletApplication.class.getSimpleName());
//
//    public static void main(String[] args) {
//        SpringApplication.run(CryptobatchTaskletApplication.class, args);
//    }
//
//    @Bean
//    public Tasklet tasklet(@Value("${file.path}") String filePath) {
//        return (stepContribution, chunkContext) -> {
//            File file = Paths.get(filePath).toFile();
//            if (file.delete()) {
//                logger.info("arquivo deletado");
//            } else {
//                logger.warning("arquivo não foi deletado ou não encontrado");
//            }
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//    public Step step(StepBuilderFactory stepBuilderFactory,
//                     Tasklet tasklet){
//        return stepBuilderFactory.get("delete step")
//                .tasklet(tasklet)
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    @Bean
//    public Job job(JobBuilderFactory jobBuilderFactory,
//                   Step step){
//        return jobBuilderFactory.get("delete job")
//                .start(step)
//                .build();
//    }
//
//}
