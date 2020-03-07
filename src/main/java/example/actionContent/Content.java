package example.actionContent;

public class Content {
    private String name;
    private String IdCard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    @Override
    public String toString() {
        return "Content{" +
                "name='" + name + '\'' +
                ", IdCard='" + IdCard + '\'' +
                '}';
    }
}
