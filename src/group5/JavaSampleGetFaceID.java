package group5;

import java.io.FileInputStream;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

public class JavaSampleGetFaceID {
	public static String getStatus(String f) {
		HttpClient httpclient = new DefaultHttpClient();
		
		//save faceID
        String saveId = null;
	     
	     try
	     {
	         URIBuilder builder = new URIBuilder(etc.uriBaseD);

	         // Request parameters. All of them are optional.
	         builder.setParameter("returnFaceId", "true");

	         // Prepare the URI for the REST API call.
	         URI uri = builder.build();
	         HttpPost request = new HttpPost(uri);

	         // Request headers.
	         request.setHeader("Content-Type", "application/octet-stream");
	         request.setHeader("Ocp-Apim-Subscription-Key", etc.subscriptionKey);

	         // Request body.
	         FileInputStream fis = new FileInputStream(f);
	         InputStreamEntity reqEntity = new InputStreamEntity(fis, -1);
	         request.setEntity(reqEntity);

	         // Execute the REST API call and get the response entity.
	         HttpResponse response = httpclient.execute(request);
	         HttpEntity entity = response.getEntity();
	         
	         if (entity != null)
	         {
	             // Format and display the JSON response.
	             
	             String jsonString = EntityUtils.toString(entity).trim();
	             
	             JSONArray jsonArray = new JSONArray(jsonString);

	             //get faceID
		         saveId = jsonArray.getJSONObject(0).getString("faceId");

	             
	         }
	     }
	     catch (Exception e)
	     {
	         // Display error message.
	         System.out.println(e.getMessage());
	     }
		return saveId;
	}
}
