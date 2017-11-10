package group5;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class JavaSampleGetList
{
	public static List<String> ls = new ArrayList<String>();
    public static List<String> getList()
    {
    	HttpGetWithEntity httpclient = new HttpGetWithEntity();
    	HttpClient httpclient2 = new DefaultHttpClient();

        try
        {
            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups");

            builder.setParameter("start", "");
            builder.setParameter("top", "1000");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", etc.subscriptionKey);


            // Request body
            JSONObject json = new JSONObject();
            StringEntity reqEntity = new StringEntity(json.toString());
            httpclient.setEntity(reqEntity);

            HttpResponse response = httpclient2.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                //System.out.println(EntityUtils.toString(entity));
                String jsonString = EntityUtils.toString(entity).trim();
                
                //System.out.println(jsonString);
                
                JSONArray jar = new JSONArray(jsonString);
                
                for(int i = 0; i < jar.length(); i++) {
                	JSONObject job = jar.getJSONObject(i);
                	
                	ls.add(job.getString("personGroupId"));
                	//System.out.println(jar);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
		return ls;
    }
}