/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloVO;

/**
 *
 * @author SHERMAN
 */
public class FacturasVo {
    
    
    private String IdFacturas, IdEmpleado, descFacturas, idTipoFactura, TipoFactura ;


    private byte [] ArchivoFactura;
    
    public FacturasVo (String IdFacturas, String IdEmpleado, String descFacturas, String idTipoFactura, String TipoFactura, byte [] ArchivoFactura ) {
        this.IdFacturas = IdFacturas;
        this.IdEmpleado = IdEmpleado;
        this.TipoFactura = TipoFactura;
        this.ArchivoFactura = ArchivoFactura;   
        this.descFacturas = descFacturas;
        this.idTipoFactura=idTipoFactura;
    }


        public FacturasVo (){
        }

        public String getIdFacturas() {
        return IdFacturas;
        }

        public void setIdFacturas(String IdFacturas) {
        this.IdFacturas = IdFacturas;
        }

        public String getIdEmpleado() {
        return IdEmpleado;
        }

        public void setIdEmpleado(String IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
        }

        public String getTipoFactura() {
        return TipoFactura;
        }

        public void setTipoFactura(String TipoFactura) {
        this.TipoFactura = TipoFactura;
        }

        public byte [] getArchivoFactura() {
        return ArchivoFactura;
        }

        public void setArchivoFactura(byte [] ArchivoFactura) {
        this.ArchivoFactura = ArchivoFactura;
        }
    
        public String getDescFacturas() {
            return descFacturas;
        }

        public void setDescFacturas(String descFacturas) {
            this.descFacturas = descFacturas;
        }

        public String getIdTipoFactura() {
            return idTipoFactura;
        }

        public void setIdTipoFactura(String idTipoFactura) {
            this.idTipoFactura = idTipoFactura;
        }
}
