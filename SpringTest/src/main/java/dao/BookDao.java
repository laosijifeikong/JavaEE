package dao;

public interface BookDao {
    double findBookPriceByIsbn(String isbn);
    int updateBookStock(String isbn);
    int updateUserAccount(int userId, double price);
}
