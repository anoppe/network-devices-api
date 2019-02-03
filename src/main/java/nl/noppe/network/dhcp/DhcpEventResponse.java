package nl.noppe.network.dhcp;

public class DhcpEventResponse {

    private int statusCode;

    public DhcpEventResponse() {
    }

    public DhcpEventResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
