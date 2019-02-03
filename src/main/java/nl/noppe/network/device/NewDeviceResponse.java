package nl.noppe.network.device;

public class NewDeviceResponse {
    private int statusCode;

    public NewDeviceResponse() {
    }

    public NewDeviceResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
