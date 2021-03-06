package lifestats.a350s18_21_lifestats;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "lifestats-mobilehub-2078005233-stress")

public class StressDO {
    private String _userId;
    private Set<String> _stress;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "stress")
    public Set<String> getStress() {
        return _stress;
    }

    public void setStress(final Set<String> _stress) {
        this._stress = _stress;
    }

}
