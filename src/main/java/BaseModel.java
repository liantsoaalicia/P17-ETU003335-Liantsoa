package login;

public class BaseModel {
    protected int id; // Modificateur d'accès changé en protected

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
