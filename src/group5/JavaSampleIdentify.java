package group5;

//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class JavaSampleIdentify 
{
 public static void Identify(String faceId, String GroupId) 
 {
	 
	 
	 // 
	 // Chưa gọi train nên chưa identify được.Phải xữ lý button train trước đã
	 // Ở lớp này thực hiện 3 công đoạn
	 /*
	  * Giai đoạn 1(tạo Method 1):Gọi hàm Detection trã về ID khuôn mặt trước xữ lý chuổi để lấy ID
	  * Giai đoạn 2(tạo Method 2): tạo method Identify trã về PersonID với giá trị truyền vào là ID khuôn mặt từ giai đoạn 1 và groupID từ bên Lớp giao diện đã có lưu GroupID rồi
	  * Giai đoạn 3(tạo Method 3):Gọi hàm GET A PERSON trã về thông tin của person đó bao gồm name và lấy name đó out ra một hộp thoại riêng hay gì cũng được
	  */
	 Object[] tFaceId = {
			 faceId
	 };
	 
	 HttpClient httpclient = new DefaultHttpClient();
	 
     try
     {
         URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/identify");


         URI uri = builder.build();
         HttpPost request = new HttpPost(uri);
         request.setHeader("Content-Type", "application/json");
         request.setHeader("Ocp-Apim-Subscription-Key", etc.subscriptionKey);


         // Request body
         JSONObject json=new JSONObject();
         json.put("personGroupId", GroupId);
         json.put("faceIds", tFaceId);
         json.put("maxNumOfCandidatesReturned",1);
         json.put("confidenceThreshold",0.5);
         
         StringEntity reqEntity = new StringEntity(json.toString());
         request.setEntity(reqEntity);

         HttpResponse response = httpclient.execute(request);
         HttpEntity entity = response.getEntity();

         if (entity != null) 
         {
             System.out.println(EntityUtils.toString(entity));
         }
     }
     catch (Exception e)
     {
         System.out.println(e.getMessage());
     }
 }
 
 
}
