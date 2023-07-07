package enums;

import com.google.gson.annotations.SerializedName;


public enum Categoria {
    @SerializedName("1")
    A("Categoria A", 1),
    @SerializedName("2")
    B("Categoria B", 2),
    @SerializedName("3")
    C("Categoria C", 3);
    
    private String nombre;
    private int codigo;

    private Categoria(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }
    
    
}
