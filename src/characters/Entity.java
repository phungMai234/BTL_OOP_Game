package src.characters;

public abstract class Entity {
    protected int _x, _y;
    protected boolean canMove = false;
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

    public void set_x(int _x) {
        this._x = _x;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public abstract String getPath();

}
