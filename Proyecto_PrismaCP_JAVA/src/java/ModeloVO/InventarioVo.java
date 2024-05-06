/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloVO;

/**
 *
 * @author SHERMAN
 */
public class InventarioVo {
    
    private String IdMaterial, cantidadInventrio, disponibilidadInventario, idMaterialesInventario , NombreMaterial, DescripcionMaterial, TamanoMaterial, UsuarioModInventario, TipoModificacion;

    public InventarioVo(String IdMaterial, String cantidadInventrio, String disponibilidadInventario, String idMaterialesInventario, String NombreMaterial, String DescripcionMaterial, String TamanoMaterial, String UsuarioModInventario, String TipoModificacion) {
        this.IdMaterial = IdMaterial;
        this.cantidadInventrio = cantidadInventrio;
        this.disponibilidadInventario = disponibilidadInventario;
        this.idMaterialesInventario = idMaterialesInventario;
        this.NombreMaterial = NombreMaterial;
        this.DescripcionMaterial = DescripcionMaterial;
        this.TamanoMaterial = TamanoMaterial;
        this.UsuarioModInventario = UsuarioModInventario;
        this.TipoModificacion = TipoModificacion;
    }
    
        public InventarioVo() {
        
    }

    
    public String getIdMaterial() {
        return IdMaterial;
    }

    public void setIdMaterial(String IdMaterial) {
        this.IdMaterial = IdMaterial;
    }

    public String getCantidadInventrio() {
        return cantidadInventrio;
    }

    public void setCantidadInventrio(String cantidadInventrio) {
        this.cantidadInventrio = cantidadInventrio;
    }

    public String getDisponibilidadInventario() {
        return disponibilidadInventario;
    }

    public void setDisponibilidadInventario(String disponibilidadInventario) {
        this.disponibilidadInventario = disponibilidadInventario;
    }

    public String getIdMaterialesInventario() {
        return idMaterialesInventario;
    }

    public void setIdMaterialesInventario(String idMaterialesInventario) {
        this.idMaterialesInventario = idMaterialesInventario;
    }

    public String getNombreMaterial() {
        return NombreMaterial;
    }

    public void setNombreMaterial(String NombreMaterial) {
        this.NombreMaterial = NombreMaterial;
    }

    public String getDescripcionMaterial() {
        return DescripcionMaterial;
    }

    public void setDescripcionMaterial(String DescripcionMaterial) {
        this.DescripcionMaterial = DescripcionMaterial;
    }

    public String getTamanoMaterial() {
        return TamanoMaterial;
    }

    public void setTamanoMaterial(String TamanoMaterial) {
        this.TamanoMaterial = TamanoMaterial;
    }

    public String getUsuarioModInventario() {
        return UsuarioModInventario;
    }

    public void setUsuarioModInventario(String UsuarioModInventario) {
        this.UsuarioModInventario = UsuarioModInventario;
    }

    public String getTipoModificacion() {
        return TipoModificacion;
    }

    public void setTipoModificacion(String TipoModificacion) {
        this.TipoModificacion = TipoModificacion;
    }
    
}
