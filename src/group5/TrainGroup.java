package group5;

import java.net.URI;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

public class TrainGroup {
	 public static void Train(String GroupID)
	    {
		 HttpClient httpclient = new DefaultHttpClient();
	        try
	        {
	            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/"+GroupID+"/train");


	            URI uri = builder.build();
	            HttpPost request = new HttpPost(uri);
	            request.setHeader("Ocp-Apim-Subscription-Key", etc.subscriptionKey);


	            // Request body
	            StringEntity reqEntity = new StringEntity("");
	            request.setEntity(reqEntity);

	            HttpResponse response = httpclient.execute(request);
	            HttpEntity entity = response.getEntity();

	            if (entity != null) 
	            {
	                //System.out.println(EntityUtils.toString(entity));
	            	JOptionPane.showMessageDialog(null, "Done!");
	            }
	        }
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
	    }
}
