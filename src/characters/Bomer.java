package characters;

public class Bomer extends Entity {
    private int _limit_bom = 1;
    private int _bom_number = 0;
    private int _bom_time = 3000;
    private boolean canDelete = true;
    private boolean isLife = true;
    private String path = "../BTL_OOP_Game/image/bomer.png";

    public Bomer(int x, int y) {
        super(x, y);
    }

    public String getPath() {
        return path;
    }

    public int get_limit_bom() {
        return _limit_bom;
    }

    public void set_limit_bom(int _limit_bom) {
        this._limit_bom = _limit_bom;
    }

    public int get_bom_number() {
        return _bom_number;
    }

    public void set_bom_number(int _bom_number) {
        this._bom_number = _bom_number;
    }

    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean life) {
        isLife = life;
    }

    public int get_bom_time() {
        return _bom_time;
    }

    public void set_bom_time(int _bom_time) {
        this._bom_time = _bom_time;
    }

    @Override
    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
}

