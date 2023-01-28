package dev.donghyeon.dev.springbatch.docs

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


@Configuration
class BatchConfiguration(
) {

    @Bean
    fun importUserJob(
        jobRepository: JobRepository,
        listener: JobCompletionNotificationListener,
        step1: Step
    ): Job {
        return JobBuilder("importUserJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build()
    }

    @Bean
    fun step1(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager, writer: JdbcBatchItemWriter<Person>
    ): Step? {
        return StepBuilder("step1", jobRepository)
            .chunk<Person,Person>(10,transactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build()
    }

    @Bean
    fun reader(): ItemReader<Person> {
        return FlatFileItemReaderBuilder<Person>()
            .name("personItemReader")
            .resource(ClassPathResource("sample-data.csv"))
            .delimited()
            .names(*arrayOf("firstName","lastName"))
            .fieldSetMapper(object : BeanWrapperFieldSetMapper<Person?>() {
                init {
                    setTargetType(Person::class.java)
                }
            })
            .build()
    }

    @Bean
    fun processor(): PersonItemProcessor {
        return PersonItemProcessor()
    }

    @Bean
    fun writer(dataSource: DataSource): JdbcBatchItemWriter<Person> {
        return JdbcBatchItemWriterBuilder<Person>()
            .itemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider())
            .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
            .dataSource(dataSource)
            .build()
    }
}
