package service;

import dao.BookDao;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Setter
public class BookService {

    private BookDao bookDao;
    private LogService logService;

    @Transactional
    public void purchase(int userId, String isbn) {
        try {
            double price = bookDao.findBookPriceByIsbn(isbn);
            int stockResult = bookDao.updateBookStock(isbn);
            if (stockResult == 0)
                throw new RuntimeException("库存不足");

            int accountResult = bookDao.updateUserAccount(userId, price);
            if (accountResult == 0)
                throw new RuntimeException("余额不足");

            logService.log("用户" + userId + "购买成功，图书ISBN：" + isbn);
        } catch (Exception e) {
            logService.log("用户" + userId + "购买失败：" + e.getMessage());
            throw e;
        }
    }

}
