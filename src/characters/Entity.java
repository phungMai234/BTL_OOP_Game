package src.characters;

public abstract class Entity {
    protected int _x, _y;
    public abstract void render(String path);

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }

    public Entity(int x, int y){
        _x = x;
        _y = y;
    }
}
