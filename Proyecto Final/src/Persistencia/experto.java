package Persistencia;

/**
 * Experto
 */

public class experto {
	private String codExperto;
	private String nombre;
	private String pais;
	private String sexo;
	private String especialidad;
	   
	 /**
        * Constructor por defecto
         */
	public experto() {
	   codExperto=null;
	   nombre=null;
	   pais=null;
	   sexo=null;
	   especialidad=null;
  
	}
	            
	 /**
   * Constructor con parametros 
   * @param c, n, p, s, e
     * @param n
     * @param p
     * @param s
     * @param e
    */    	
	public experto(String c, String n, String p, String s, String e) {
            codExperto = c;
            nombre = n;
            pais = p;
            sexo = s;
            especialidad = e;
        }		
		
	public String getCodExperto() {
            return codExperto;
        }
	
	public String getNombre() {
            return nombre;
        }
	
	public String getPais() {
            return pais;
        }
		
	public String getSexo() {
            return sexo;
        }

	public String getEspecialidad() {
            return especialidad;
        }
		
	public void setCodExperto(String c) {
            codExperto = c;
        }
		
	public void setNombre(String n) {
            nombre = n;
        }
		
	public void setPais(String p) {
            pais = p;
        }
		
	public void setSexo(String s) {
            sexo = s;
        }
		
	public void setEspecialidad(String e) {
            especialidad = e;
        }
}