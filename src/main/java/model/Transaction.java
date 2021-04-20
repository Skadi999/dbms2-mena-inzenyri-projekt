package model;

public class Transaction {
    private int id;
    private final String coinName;
    private final double price;
    private final String sellerName;
    private final String buyerName;

    public Transaction(int id, String coinName, double price, String sellerName, String buyerName) {
        this(coinName, price, sellerName, buyerName);
        this.id = id;
    }

    public Transaction(String coinName, double price, String sellerName, String buyerName) {
        this.coinName = coinName;
        this.price = price;
        this.sellerName = sellerName;
        this.buyerName = buyerName;
    }

    public int getId() {
        return id;
    }

    public String getCoinName() {
        return coinName;
    }

    public double getPrice() {
        return price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }


    @Override
    public String toString() {
        return "#" + id + "    " + coinName + "    " + price;
    }
}
