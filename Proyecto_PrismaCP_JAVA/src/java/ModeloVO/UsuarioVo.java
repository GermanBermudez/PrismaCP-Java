
package ModeloVO;

/**
 *
 * @author SHERMAN
 */
public class UsuarioVo {
    
    private String idEmpleado, nombreUsuario, contrasenaUsuario, NombreEmpleado, CedulaEmpleado, CorreoEmpleado, TelefonoEmpleado, IdCargo, cargoEmpleado;

    public UsuarioVo(String idEmpleado, String nombreUsuario, String contrasenaUsuario, String NombreEmpleado, String CedulaEmpleado, String CorreoEmpleado, String TelefonoEmpleado, String IdCargo, String cargoEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombreUsuario = nombreUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.NombreEmpleado = NombreEmpleado;
        this.CedulaEmpleado = CedulaEmpleado;
        this.CorreoEmpleado = CorreoEmpleado;
        this.TelefonoEmpleado = TelefonoEmpleado;
        this.IdCargo = IdCargo;
        this.cargoEmpleado = cargoEmpleado;
    }

    
    
    public UsuarioVo() {
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getNombreEmpleado() {
        return NombreEmpleado;
    }

    public void setNombreEmpleado(String NombreEmpleado) {
        this.NombreEmpleado = NombreEmpleado;
    }

    public String getCedulaEmpleado() {
        return CedulaEmpleado;
    }

    public void setCedulaEmpleado(String CedulaEmpleado) {
        this.CedulaEmpleado = CedulaEmpleado;
    }

    public String getCorreoEmpleado() {
        return CorreoEmpleado;
    }

    public void setCorreoEmpleado(String CorreoEmpleado) {
        this.CorreoEmpleado = CorreoEmpleado;
    }

    public String getTelefonoEmpleado() {
        return TelefonoEmpleado;
    }

    public void setTelefonoEmpleado(String TelefonoEmpleado) {
        this.TelefonoEmpleado = TelefonoEmpleado;
    }

    public String getIdCargo() {
        return IdCargo;
    }

    public String getCargoEmpleado() {
        return cargoEmpleado;
    }

    public void setCargoEmpleado(String cargoEmpleado) {
        this.cargoEmpleado = cargoEmpleado;
    }

    public void setIdCargo(String IdCargo) {
        this.IdCargo = IdCargo;
    }

    
    
}
