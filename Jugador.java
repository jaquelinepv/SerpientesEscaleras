class Jugador {

    private String nombre;
    private int posicion=0;

    public Jugador(String nombre){
        this.nombre= nombre;
    }
    public void asignarPosicion(int posicion){
        this.posicion=posicion;
    }

    public int obtenerPosicion(){
        return posicion;
    }

    public String obtenerNombre(){
        return nombre;
    }
}