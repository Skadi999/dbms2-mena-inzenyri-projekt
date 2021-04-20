package enums;

public enum MessageType {
    REGULAR(-1), TECHNICAL_ISSUE(0), COMPLAINT(1), OTHER(2);

    private final int num;

    MessageType(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
