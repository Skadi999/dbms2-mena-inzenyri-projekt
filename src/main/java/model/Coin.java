package model;

public class Coin {
    private int id;
    private final String name;
    private final double price;
    private final int year;
    private final String country;
    private final String metal;
    private final String imagePath;
    private final String sellerName;

    public static final String COIN_FOLDER_PATH = "C:\\Users\\Skadi\\IdeaProjects\\dbms2project\\src\\main\\resources\\Coins\\";

    public Coin(int id, String name, double price, int year, String country, String metal, String imagePath, String sellerName) {
        this(name, price, year, country, metal, imagePath, sellerName);
        this.id = id;
    }

    public Coin(String name, double price, int year, String country, String metal, String imagePath, String sellerName) {
        this.name = name;
        this.price = price;
        this.year = year;
        this.country = country;
        this.metal = metal;
        this.imagePath = imagePath;
        this.sellerName = sellerName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getMetal() {
        return metal;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String toString() {
        return "#" + id + "    " + name + "    " + year + "    $" + price;
    }
}
