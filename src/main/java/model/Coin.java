package model;

public class Coin {
    private int id;
    private String name;
    private double price;
    private int year;
    private String country;
    private String metal;
    private String imagePath;

    public static final String COIN_FOLDER_PATH = "C:\\Users\\Skadi\\IdeaProjects\\dbms2project\\src\\main\\resources\\Coins\\";

    public Coin(int id, String name, double price, int year, String country, String metal, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.year = year;
        this.country = country;
        this.metal = metal;
        this.imagePath = imagePath;
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
}
