package Persistencia;

/**
 * Colabora
 */
public class colabora {

    private String codExperto;
    private String codCaso;
    private String fecha;
    private String descripcionColaboracion;

    /**
     * Constructor por defecto
     */
    public colabora() {
        this.codCaso = null;
        this.codExperto = null;
        this.fecha = null;
        this.descripcionColaboracion = null;
    }

    /**
     * Constructor con parametros
     *
     * @param ce
     * @param cc
     * @param f
     * @param dc
     * @ param ce, cc, f, dc
     */
    public colabora(String ce, String cc, String f, String dc) {
        this.codCaso = cc;
        this.codExperto = ce;
        this.fecha = f;
        this.descripcionColaboracion = dc;
    }

    public String getCodExperto() {
        return this.codExperto;
    }

    public String getCodCaso() {
        return this.codCaso;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getDescripcionColaboracion() {
        return this.descripcionColaboracion;
    }

    public void setCodExperto(String ce) {
        this.codExperto = ce;
    }

    public void setCodcaso(String cc) {
        this.codCaso = cc;
    }

    public void setFecha(String f) {
        this.fecha = f;
    }

    public void setDescripcionColaboracion(String dc) {
        this.descripcionColaboracion = dc;
    }
}
