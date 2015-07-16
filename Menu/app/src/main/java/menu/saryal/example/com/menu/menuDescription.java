package menu.saryal.example.com.menu;

public class menuDescription {

    String title;
    String description;
    double cost;
    String image;
    double totalCost;
    int totalOrder;

    public double getRatinng() {
        return ratinng;
    }

    public void setRatinng(double ratinng) {
        this.ratinng = ratinng;
    }

    double ratinng;
    long id;

    public menuDescription(String titleInput, String descriptionInput, double costInput, String imageInput, double totalCostInput, int totalOrderInput){
        title = titleInput;
        description = descriptionInput;
        cost = costInput;
        image = imageInput;
        totalCost = totalCostInput;
        totalOrder = totalOrderInput;
    }

    public menuDescription(String titleInput, String descriptionInput, double costInput, String imageInput){
        title = titleInput;
        description = descriptionInput;
        cost = costInput;
        image = imageInput;
        totalCost = costInput;
        totalOrder = 1;
    }

    public String getTitle() {return title; }

    public String getDescription() { return description;}

    public double getCost() { return cost; }

    public String getImage() {return image;}

    public double getTotalCost() { return totalCost; }

    public void changeTotalCost() { this.totalCost = cost * totalOrder;}

    public void setTotalCost(double totalCost) { this.totalCost = totalCost;}

    public int getTotalOrder() { return totalOrder;}

    public void setTotalOrder(int totalOrder) { this.totalOrder = totalOrder; changeTotalCost();}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
