/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xml_to_json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author MingZhang
 */
public class XmlToJson {
    
    
    static {
        disableSslVerification();
    }

    private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    
    
    
    

    public static String convert(String para) throws IOException {

        // Create a new XmlMapper to read XML tags
        XmlMapper xmlMapper = new XmlMapper();

        //Reading the XML
        JsonNode jsonNode = xmlMapper.readTree(para.getBytes());

        //Create a new ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //Get JSON as a string
        String value = objectMapper.writeValueAsString(jsonNode);
        JsonNode root = objectMapper.readTree(value);
        System.out.println(root.get("mbr_emailaddress").asText());
        return value;
    }

    public void GetCall(String account) {

        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            URL urlToken = new URL("https://elao-test.fa.us2.oraclecloud.com/crmRestApi/resources/11.13.18.05/loyMembers/?q=PhoneNum_c=9094940913");
//
//              HttpURLConnection urlconn = (HttpURLConnection) urlToken.openConnection();
//            urlconn.setRequestMethod("GET");
//            urlconn.setDoOutput(true);
//             urlconn.setRequestProperty("Content-Type", "application/json");
//            urlconn.setRequestProperty("Authorization", "Basic bHVjeWFwaWFkbWluOmxvZ2luQDEyMw==");
//            OutputStream os = urlconn.getOutputStream();
//           
//            os.flush();
//            InputStreamReader urlio = new InputStreamReader(urlconn.getInputStream());
//        
// 
//            try
//            {
//                String line;
//                BufferedReader bufferedReader = new BufferedReader( urlio);
//                while( (line = bufferedReader.readLine()) != null )
//                { 
//                    System.out.printf("%s\n", line);
//                }  
//                 System.out.printf("%s\n", line);
//            } 
//            catch( IOException e )
//            {
//                System.err.println( "Error: " + e );
//            }
            URL urlToken = new URL("https://elao-test.fa.us2.oraclecloud.com/crmRestApi/resources/11.13.18.05/loyMembers/?q=PhoneNum_c=9094940913");
            HttpURLConnection connection = (HttpURLConnection) urlToken.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic bHVjeWFwaWFkbWluOmxvZ2luQDEyMw==");
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void PostCall(String para) {

        try {

       //  URL urlToken = new URL("https://8989stag.drop-tank.com:443/soa-infra/resources/MakeItCountV2/CreateMICMembershipService!7.0/CreateMICMembership/createMembership");
            URL urlToken = new URL("http://0310601-lucy.drop-tank.com:80/soa-infra/resources/MakeItCountV2/CreateMICMembershipService!1.2/CreateMICMembership/createMembership");
       
            
            HttpURLConnection connection = (HttpURLConnection) urlToken.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic ZHRfbWljX2FwaV91c2VyOmR3dzloN2V1T0ZIZk9kT0tMdU1jMVhTcGRzV1A4R3pN");
            
            
            try(OutputStream oos = connection.getOutputStream()) {
                byte[] input = para.getBytes();
                oos.write(input, 0, input.length);           
                
            }
            
            
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
               //ex.printStackTrace();
               System.out.println(ex.getMessage());

        }
    }
 
    public void generatePost(String para) {

        ObjectMapper mapper = new ObjectMapper();
         String https_url = "http://0310601-lucy.drop-tank.com:80/soa-infra/resources/MakeItCountV2/CreateMICMembershipService!1.2/CreateMICMembership/createMembership";
        URL url;
        try {
            url = new URL(null, https_url, new sun.net.www.protocol.https.Handler());
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//	     print_https_cert(con);         
            if (con != null) {
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/json");
                OutputStream os = con.getOutputStream();
              
           
                os.write(para.getBytes());
                os.flush();
                BufferedReader br
                        = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String input;
                while ((input = br.readLine()) != null) {
                    sb.append(input);
                    System.out.println(input);
                }
                br.close();
            }
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void test_xml_to_json(){
        
    }
    

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("d:/860.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            Iterator it = records.iterator();

            XmlToJson x = new XmlToJson();

            while (it.hasNext()) {
                String tmp = it.next().toString();

                  System.out.println(  tmp.substring(1, tmp.length()-1));


                  x.PostCall(  tmp.substring(1, tmp.length()-1));
          //  System.out.println(  XmlToJson.convert(tmp.substring(1, tmp.length()-1)));
       //      XmlToJson.convert(tmp.substring(1, tmp.length()-1));
            //    System.out.println(tmp);
            }

//            records.forEach((t) -> {
//                System.out.println(t.toString());
//            });
//            
//            
           // x.PostCall("{\"mbr_firstname\":\"George\",\"mbr_lastname\":\"Wacker\",\"mbr_emailaddress\":\"gew50lpw@gmail.com\",\"mbr_password\":\"e1578e053abb818cbacea6b119adee4d\",\"mbr_phone\":\"7344281646\",\"mbr_zipcode\":\"48158\",\"mbr_gender\":\"Male\",\"mbr_birthdate\":\"1939-05-20\",\"mbr_loyaltyprogram\":\"CENTS_OFF\"}");
     
      //  x.generatePost("{\"mbr_firstname\":\"Moe\",\"mbr_lastname\":\"Alomari\",\"mbr_emailaddress\":\"Moealo1010@gmail.com\",\"mbr_password\":\"92c6d25284371c45ba5c4b32a5dda285\",\"mbr_phone\":\"3133583634\",\"mbr_zipcode\":\"48126\",\"mbr_gender\":\"Male\",\"mbr_birthdate\":\"1997-08-12\",\"mbr_loyaltyprogram\":\"SOUTHWEST\"}");
        } catch (IOException ex) {
            Logger.getLogger(XmlToJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
