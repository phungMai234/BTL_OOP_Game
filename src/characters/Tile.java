package characters;

public class Tile extends Entity {
    private boolean hasItem = false;
    public boolean canDelete = true;
    private String path = "../BTL_OOP_Game/image/grass.png";
    private String pathItem = "";
    private String typeItem = "";

    public Tile(int x, int y) {
        super(x, y);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
