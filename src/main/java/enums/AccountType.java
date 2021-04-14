package enums;

public enum AccountType {
    REGULAR(0), SUPPORT(1), ADMIN(2);

    private final int num;

    AccountType(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public static AccountType getType(int num) {
        return switch (num) {
            case 0 -> REGULAR;
            case 1 -> SUPPORT;
            case 2 -> ADMIN;
            default -> throw new IllegalArgumentException();
        };
    }
}
