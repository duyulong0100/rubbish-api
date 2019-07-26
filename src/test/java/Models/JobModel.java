package Models;

public class JobModel {

    private String PartnerId;

    public String getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(String partnerId) {
        PartnerId = partnerId;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public CommonStatus getStatus() {
        return Status;
    }

    public void setStatus(CommonStatus status) {
        Status = status;
    }

    public String Data;

    private CommonStatus Status = CommonStatus.IsSuccess;

}
