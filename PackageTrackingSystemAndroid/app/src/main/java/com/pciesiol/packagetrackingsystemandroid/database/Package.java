package com.pciesiol.packagetrackingsystemandroid.database;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@ParseClassName("Package")
public class Package extends ParseObject implements Serializable {
    private String courierIdKey = "courierId";
    private String packageIdKey = "objectId";

    private String receiverNameKey = "receiverName";
    private String receiverSurnameKey = "receiverSurname";
    private String receiverEmailKey = "receiverEmail";
    private String receiverPhoneNumberKey = "receiverPhoneNumber";
    private String destAddressKey = "destinationAddress";
    private String destCityKey = "destinationCity";
    private String destZipCodeKey = "destinationZipCode";
    private String senderNameKey = "senderName";
    private String senderSurnameKey = "senderSurname";
    private String senderEmailKey = "senderEmail";
    private String senderPhoneNumberKey = "senderPhoneNumber";
    private String weightKey = "weight";
    private String descriptionKey = "description";

    public Package() {}

    public String toString() {
        return getPackageId()+": "+getDestAddress()+" "+getDestCity();
    }

    public static ParseQuery<Package> getQuery() {
        return ParseQuery.getQuery(Package.class);
    }

    public static ParseQuery<Package> getCourierPackagesQuery(String courierId) {
        ParseQuery<Package> query = ParseQuery.getQuery("Package");
        query.whereEqualTo("courierId", courierId);

        return query;
    }

    public void leavePackage() {
        this.put(courierIdKey, "?");
        this.saveInBackground();
    }

    public void assignToNewCourier(String courierId) {
        this.put(courierIdKey, courierId);
        this.saveInBackground();
    }

    public String getPackageId() { return this.getObjectId(); }
    public String getCourierId() { return this.getString(courierIdKey); }

    public String getReceiverName() { return this.getString(receiverNameKey); }
    public String getReceiverSurname() { return this.getString(receiverSurnameKey); }
    public String getReceiverEmail() { return this.getString(receiverEmailKey); }
    public String getReceiverPhoneNumber() { return this.getString(receiverPhoneNumberKey); }
    
    public String getDestAddress() { return this.getString(destAddressKey); }
    public String getDestCity() { return this.getString(destCityKey); }
    public String getDestZipCode() { return this.getString(destZipCodeKey); }

    public String getSenderName() { return this.getString(senderNameKey); }
    public String getSenderSurname() { return this.getString(senderSurnameKey); }
    public String getSenderEmail() { return this.getString(senderEmailKey); }
    public String getSenderPhoneNumber() { return this.getString(senderPhoneNumberKey); }

    public Integer getWeight() { return this.getInt(weightKey); }
    public String getDescription() { return this.getString(descriptionKey); }

    //formatted field name, value
    //note that is LinkedHashMap
    public Map<String, String> getPackageInfo() {
        Map<String, String> vals = new LinkedHashMap<>();
        vals.put(formatKeyName(packageIdKey), getPackageId());
        vals.put(formatKeyName(receiverNameKey), getReceiverName());
        vals.put(formatKeyName(receiverSurnameKey), getReceiverSurname());
        vals.put(formatKeyName(receiverEmailKey), getReceiverEmail());
        vals.put(formatKeyName(receiverPhoneNumberKey), getReceiverPhoneNumber());
        vals.put(formatKeyName(destAddressKey), getDestAddress());
        vals.put(formatKeyName(destCityKey), getDestCity());
        vals.put(formatKeyName(destZipCodeKey), getDestZipCode());
        vals.put(formatKeyName(senderNameKey), getSenderName());
        vals.put(formatKeyName(senderSurnameKey), getSenderSurname());
        vals.put(formatKeyName(senderEmailKey), getSenderEmail());
        vals.put(formatKeyName(senderPhoneNumberKey), getSenderPhoneNumber());
        vals.put(formatKeyName(weightKey), Integer.toString(getWeight()));
        vals.put(formatKeyName(descriptionKey), getDescription());
        return vals;
    }

    private String formatKeyName(String key) {
        return Character.toUpperCase(key.charAt(0)) + key.substring(1).replaceAll("(.)([A-Z])", "$1 $2").toLowerCase();
    }
}
