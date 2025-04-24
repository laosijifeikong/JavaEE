package config;

import aop.AspectTransaction;
import dao.Impl.StudentDaoImpl;
import dao.StudentDao;
import manager.SqlTemplateExt;
import manager.TransactionManagerExt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import service.StudentService;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {

    @Bean
    public StudentService studentService(StudentDao studentDao) {
        StudentService studentService = new StudentService();
        studentService.setStudentDao(studentDao);
        return studentService;
    }

    @Bean
    public StudentDao studentDao(SqlTemplateExt sqlTemplateExt) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        studentDao.setSqlTemplateExt(sqlTemplateExt);
        return studentDao;
    }

    @Bean
    public SqlTemplateExt sqlTemplateExt(TransactionManagerExt transactionManagerExt) {
        SqlTemplateExt sqlTemplateExt = new SqlTemplateExt();
        sqlTemplateExt.setTransactionManagerExt(transactionManagerExt);
        return sqlTemplateExt;
    }

    @Bean
    public TransactionManagerExt transactionManagerExt(DataSource dataSource) {
        TransactionManagerExt transactionManagerExt = new TransactionManagerExt();
        transactionManagerExt.setDataSource(dataSource);
        return transactionManagerExt;
    }

    @Bean
    public AspectTransaction aspectTransaction(TransactionManagerExt transactionManagerExt) {
        AspectTransaction aspectTransaction = new AspectTransaction();
        aspectTransaction.setTransactionManagerExt(transactionManagerExt);
        return aspectTransaction;
    }
}
