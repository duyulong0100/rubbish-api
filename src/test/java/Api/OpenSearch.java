package Api;

public class OpenSearch extends OpenApi {
    public OpenSearch(String sPartnerId, String sPartnerKey, String stoken, String sRequestName, String sHostUrl) {
        this.PartnerId = sPartnerId;
        this.PartnerKey = sPartnerKey;
        this.RequestName = sRequestName;
        this.HostUrl = sHostUrl;
        this.Token = stoken;
    }
}
