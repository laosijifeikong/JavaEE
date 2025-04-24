package config;

import aop.AspectTransaction;
import dao.BookDao;
import dao.Impl.BookDaoImpl;
import dao.Impl.StudentDaoImpl;
import dao.StudentDao;
import manager.SqlTemplateExt;
import manager.TransactionManagerExt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import service.BookService;
import service.LogService;
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

    @Bean
    public BookDao bookDao(SqlTemplateExt sqlTemplateExt) {
        BookDaoImpl dao = new BookDaoImpl();
        dao.setSqlTemplateExt(sqlTemplateExt);
        return dao;
    }

    @Bean
    public BookService bookService(BookDao bookDao, LogService logService) {
        BookService service = new BookService();
        service.setBookDao(bookDao);
        service.setLogService(logService);
        return service;
    }

    @Bean
    public LogService logService(TransactionManagerExt transactionManagerExt) {
        LogService logService = new LogService();
        logService.setTransactionManagerExt(transactionManagerExt);
        return logService;
    }

}
