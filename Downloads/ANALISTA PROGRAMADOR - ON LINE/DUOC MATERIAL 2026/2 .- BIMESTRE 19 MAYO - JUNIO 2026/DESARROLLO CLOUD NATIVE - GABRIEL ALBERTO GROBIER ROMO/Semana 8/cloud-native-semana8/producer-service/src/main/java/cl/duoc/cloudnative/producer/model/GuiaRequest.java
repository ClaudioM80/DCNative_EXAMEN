package cl.duoc.cloudnative.producer.model;

public class GuiaRequest {
    private String usuarioId;
    private String producto;
    private int cantidad;
    private String email;
    private String roles;

    public GuiaRequest() {
    }

    public GuiaRequest(String usuarioId, String producto, int cantidad, String email, String roles) {
        this.usuarioId = usuarioId;
        this.producto = producto;
        this.cantidad = cantidad;
        this.email = email;
        this.roles = roles;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}