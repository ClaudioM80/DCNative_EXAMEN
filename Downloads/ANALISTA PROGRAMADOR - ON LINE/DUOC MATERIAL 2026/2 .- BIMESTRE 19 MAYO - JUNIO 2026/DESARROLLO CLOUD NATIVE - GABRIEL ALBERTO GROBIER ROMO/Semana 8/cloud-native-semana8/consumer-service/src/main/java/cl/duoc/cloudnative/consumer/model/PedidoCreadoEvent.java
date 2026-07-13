package cl.duoc.cloudnative.consumer.model;

public class PedidoCreadoEvent {
    private String eventId;
    private String pedidoId;
    private String usuarioId;
    private String producto;
    private int cantidad;
    private String email;
    private String roles;

    public PedidoCreadoEvent() {}

    public PedidoCreadoEvent(String eventId, String pedidoId, String usuarioId, String producto, int cantidad, String email, String roles) {
        this.eventId = eventId;
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.producto = producto;
        this.cantidad = cantidad;
        this.email = email;
        this.roles = roles;
    }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public String getPedidoId() { return pedidoId; }
    public void setPedidoId(String pedidoId) { this.pedidoId = pedidoId; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }
}
