/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloVO;

/**
 *
 * @author SHERMAN
 */
public class ProduccionVo {
    
    private String IdProduccion, DescProd, Cantidad, FechaCreacion, Estado, FechaFinal, EmpSolicitante, EmpEjecutor, TipoOrden;
    private byte [] ArchivoDisenoProd;

    public ProduccionVo(String IdProduccion, String DescProd, String Cantidad, String FechaCreacion, String Estado, String FechaFinal, String EmpSolicitante, String EmpEjecutor, String TipoOrden, byte[] ArchivoDisenoProd) {
        this.IdProduccion = IdProduccion;
        this.DescProd = DescProd;
        this.Cantidad = Cantidad;
        this.FechaCreacion = FechaCreacion;
        this.Estado = Estado;
        this.FechaFinal = FechaFinal;
        this.EmpSolicitante = EmpSolicitante;
        this.EmpEjecutor = EmpEjecutor;
        this.ArchivoDisenoProd = ArchivoDisenoProd;
        this.TipoOrden = TipoOrden;
    }
    
    public ProduccionVo () {
        
    }    
    
    public String getIdProduccion() {
        return IdProduccion;
    }

    public void setIdProduccion(String IdProduccion) {
        this.IdProduccion = IdProduccion;
    }

    public String getDescProd() {
        return DescProd;
    }

    public void setDescProd(String DescProd) {
        this.DescProd = DescProd;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(String FechaFinal) {
        this.FechaFinal = FechaFinal;
    }

    public String getEmpSolicitante() {
        return EmpSolicitante;
    }

    public void setEmpSolicitante(String EmpSolicitante) {
        this.EmpSolicitante = EmpSolicitante;
    }

    public String getEmpEjecutor() {
        return EmpEjecutor;
    }

    public void setEmpEjecutor(String EmpEjecutor) {
        this.EmpEjecutor = EmpEjecutor;
    }

    public byte[] getArchivoDisenoProd() {
        return ArchivoDisenoProd;
    }

    public void setArchivoDisenoProd(byte[] ArchivoDisenoProd) {
        this.ArchivoDisenoProd = ArchivoDisenoProd;
    }
    
    public String getTipoOrden() {
        return TipoOrden;
    }

    public void setTipoOrden(String TipoOrden) {
        this.TipoOrden = TipoOrden;
    }
    
    
    
}
