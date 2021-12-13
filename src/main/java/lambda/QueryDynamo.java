package lambda;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import saaf.Inspector;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 */
public class QueryDynamo implements RequestHandler<Request, HashMap<String, Object>> {

    /**
     * Lambda Function Handler
     *
     * @param request Request POJO with defined variables from Request.java
     * @param context
     * @return HashMap that Lambda will automatically convert into JSON.
     */
    public HashMap<String, Object> handleRequest(Request request, Context context) {

        //Collect initial data.
        Inspector inspector = new Inspector();
        //inspector.inspectAll();
        LambdaLogger logger = context.getLogger();
     
        Response r = new Response();
        
        BasicAWSCredentials AWS_CREDENTIALS = new BasicAWSCredentials(
            "Access key ID",
            "Secret access key"
        );
        
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(AWS_CREDENTIALS))
            .build();
        
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":region", new AttributeValue().withS(request.getRegion()));
        eav.put(":itemType", new AttributeValue().withS(request.getItemType()));
        eav.put(":salesChannel", new AttributeValue().withS(request.getSalesChannel()));
        eav.put(":orderPriority", new AttributeValue().withS(request.getOrderPriority()));
        eav.put(":country", new AttributeValue().withS(request.getCountry()));
        
        Map<String, String> ean = new HashMap<String, String>();
        ean.put("#rgn", "Region");
        
        ScanRequest scanRequest = new ScanRequest()
            .withTableName(request.getTable())
            .withFilterExpression("#rgn = :region and "
                                + "Item_Type = :itemType and "
                                + "Sales_Channel = :salesChannel and "
                                + "Order_Priority = :orderPriority and "
                                + "Country = :country")
            .withExpressionAttributeValues(eav)
            .withExpressionAttributeNames(ean);
        
        LinkedList<String> ll = new LinkedList<>();
        
        ScanResult result = client.scan(scanRequest);
        for (Map<String, AttributeValue> item : result.getItems()){
            ll.add(item.toString());
        }       

        r.setRows(ll);
        inspector.consumeResponse(r);
        
        //inspector.inspectAllDeltas();
        return inspector.finish();
    }
}
