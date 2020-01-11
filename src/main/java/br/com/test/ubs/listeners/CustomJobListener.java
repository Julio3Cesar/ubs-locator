package br.com.test.ubs.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.batch.core.BatchStatus.*;

@Component
public class CustomJobListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(CustomJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == STARTED) {
            log.info("UBS job process started!");
        }
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == COMPLETED) {
            log.info("UBS job process completed!");
        }
    }
}