/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloVO;

/**
 *
 * @author SHERMAN
 */
public class DisenosVo {
    
    private String IdDiseno, Empleado, NombreDiseno, descDiseno, TipoDiseno;
    private byte [] ArchivoDiseno;

    public DisenosVo(String IdDiseno, String Empleado, String NombreDiseno, String descDiseno, String TipoDiseno, byte[] ArchivoDiseno) {
        this.IdDiseno = IdDiseno;
        this.Empleado = Empleado;
        this.NombreDiseno = NombreDiseno;
        this.descDiseno = descDiseno;
        this.TipoDiseno = TipoDiseno;
        this.ArchivoDiseno = ArchivoDiseno;
    }
    
    public DisenosVo () {
        
    }

    public String getIdDiseno() {
        return IdDiseno;
    }

    public void setIdDiseno(String IdDiseno) {
        this.IdDiseno = IdDiseno;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
    }

    public String getNombreDiseno() {
        return NombreDiseno;
    }

    public void setNombreDiseno(String NombreDiseno) {
        this.NombreDiseno = NombreDiseno;
    }

    public String getDescDiseno() {
        return descDiseno;
    }

    public void setDescDiseno(String descDiseno) {
        this.descDiseno = descDiseno;
    }

    public String getTipoDiseno() {
        return TipoDiseno;
    }

    public void setTipoDiseno(String TipoDiseno) {
        this.TipoDiseno = TipoDiseno;
    }

    public byte[] getArchivoDiseno() {
        return ArchivoDiseno;
    }

    public void setArchivoDiseno(byte[] ArchivoDiseno) {
        this.ArchivoDiseno = ArchivoDiseno;
    }
    
    
    
    
}
