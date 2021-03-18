package Persistencia;
/**
 * Caso 
 */
public class caso {
    private String codCaso;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
	
   /**
   * Constructor por defecto   
    */
    public caso() {
	this.codCaso=null;
        this.fechaFin=null;
        this.fechaInicio=null;
        this.nombre=null;
    }
    
    /**
   * Constructor con parametros
   * @param cc
     * @param n
     * @param fi
     * @param ff
    */
    public caso(String cc,String n, String fi, String ff) {
	this.codCaso=cc;
        this.nombre=n;
        this.fechaInicio=fi;
        this.fechaFin=ff;
    }
	     
    public String getCodCaso(){
      return this.codCaso;
    }	
	
    public String getNombre(){
     return this.nombre;
    }

    public String getFechaInicio(){
       return this.fechaInicio;
    }

    public String getFechaFin(){
        return this.fechaFin;
    }

    public void setCodCaso(String c){
       this.codCaso=c;
    }

    public void setNombre(String n){
     this.nombre=n;
    }

    public void setFechaInicio(String fi){
       this.fechaInicio=fi;
    }
	     
    public void setFechaFin(String ff){
       this.fechaFin=ff;
    }
	    
}
