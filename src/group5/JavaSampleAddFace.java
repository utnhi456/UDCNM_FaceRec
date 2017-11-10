package group5;

import java.io.FileInputStream;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class JavaSampleAddFace 
{
    public static String main(String f, String PersonGroup, String PersonID)
    {
        HttpClient httpclient = new DefaultHttpClient();
        String FaceID=null;
        try
        {
            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/"+PersonGroup+"/persons/"+PersonID+"/persistedFaces");
            
            builder.setParameter("userData", "face1");
            //builder.setParameter("targetFace", "{string}");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", etc.subscriptionKey);


            // Request body
            FileInputStream fis=new FileInputStream(f);
			InputStreamEntity reqEntity=new InputStreamEntity(fis,-1);

			request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                FaceID=EntityUtils.toString(entity).trim().substring(20, 56);
                
            	System.out.println(EntityUtils.toString(entity));
                
                //{"persistedFaceId":"5371446f-083c-4634-bfc4-37c9a0eb9845"} Tung1
                //{"persistedFaceId":"856a0ff0-75eb-4cfb-820f-0619b0d59736"}Tung 2
                //{"persistedFaceId":"d0e87fe4-5451-4c4c-a5f5-1a61219e6836"}
                //{"persistedFaceId":"49d242e8-7bb3-4619-a68d-2ed359ef5381"}

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
		return FaceID;
    }
}
