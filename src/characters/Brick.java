package characters;

public class Brick extends Entity {
    private String pathItem = "";
    private String typeItem = "";
    private boolean hasItem = false;
    private boolean canDelete = true;
    private String path = "../BTL_OOP_Game/image/brick_a.png";
    public Brick(int x, int y){
        super(x,y);
    }

    public String getPath() {
        return path;
    }
    public boolean isHasItem() {
        return hasItem;
    }
    public String getPathItem() {
        return pathItem;
    }
    public void setTypeItem(String typeItem) {
        this.typeItem = typeItem;
    }
    public String getTypeItem() {
        return typeItem;
    }
    public void setPathItem(String pathItem) {
        this.pathItem = pathItem;
    }
    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }
}
