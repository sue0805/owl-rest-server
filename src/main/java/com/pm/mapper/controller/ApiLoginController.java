package com.pm.mapper.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.pm.mapper.pojo.JwtHelper;
import com.pm.mapper.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Controller
@RequestMapping("/api")
public class ApiLoginController extends JwtHelper {

    @Value("${spring.api.naver.ClientID}")
    String naverClientID;
    @Value("${spring.api.naver.ClientSecret}")
    String naverClientSecret;
    @Value("${spring.api.kakao.ClientID}")
    String kakaoClientID;
    @Value("${spring.api.kakao.ClientSecret}")
    String kakaoClientSecret;
    @Value("${spring.api.github.ClientID}")
    String githubClientID;
    @Value("${spring.api.github.ClientSecret}")
    String githubClientSecret;

    @Autowired
    MemberService memberService;

    public enum Identify{NAVER, KAKAO, GITHUB}

    @GetMapping("/naver/callback")
    public ResponseEntity naverCallback(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "state") String state
    ) throws Exception {
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + naverClientID;
        apiURL += "&client_secret=" + naverClientSecret;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String userTokenURL = "https://openapi.naver.com/v1/nid/me";
        return getToken(apiURL, userTokenURL, null, ApiLoginController.Identify.NAVER);
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity kakaoCallback(@RequestParam(value = "code") String code) throws Exception {
        String apiURL;
        apiURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&";
        apiURL += "client_id=" + kakaoClientID;
        apiURL += "&client_secret=" + kakaoClientSecret;
        apiURL += "&code=" + code;
        String userTokenURL = "https://kapi.kakao.com/v2/user/me";
        return getToken(apiURL, userTokenURL, null, ApiLoginController.Identify.KAKAO);
    }

    @GetMapping("/github/callback")
    public ResponseEntity githubCallback(
            @RequestParam(value = "code") String code
    ) throws Exception {
        String apiURL;
        apiURL = "https://github.com/login/oauth/access_token?";
        apiURL += "client_id=" + githubClientID;
        apiURL += "&client_secret=" + githubClientSecret;
        apiURL += "&code=" + code;
        apiURL += "&redirect_uri=http://52.79.204.244/api/github/callback";
        apiURL += "&state=123";
        String userTokenURL = "https://api.github.com/user";
        System.out.println(apiURL);
        return getToken(apiURL, userTokenURL, "application/json", ApiLoginController.Identify.GITHUB);
    }

    private ResponseEntity getToken(String apiURL, String userTokenURL, String Header, Enum identify) throws URISyntaxException {
        String access_token = "";
        int id=-1;
        String nickName="";
        String tmp;
        StringBuffer res = getResponse(apiURL, Header);

        JsonParser parser = new JsonParser();
        JsonElement accessElement = parser.parse(res.toString());
        System.out.println(res.toString());
        access_token = accessElement.getAsJsonObject().get("access_token").getAsString();

        tmp = getUserInfo(access_token, userTokenURL);
        JsonElement userInfoElement = parser.parse(tmp);
        try {
            if(identify.equals(ApiLoginController.Identify.NAVER)) {
                id = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsInt();
                nickName = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("nickname").getAsString();
            } else if(identify.equals(ApiLoginController.Identify.KAKAO)){
                id = userInfoElement.getAsJsonObject().get("id").getAsInt();
                nickName = userInfoElement.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
            } else if(identify.equals(ApiLoginController.Identify.GITHUB)){
                id = userInfoElement.getAsJsonObject().get("id").getAsInt();
                nickName = userInfoElement.getAsJsonObject().get("login").getAsString();
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            String fakeEmail = id+"@"+identify.toString(); // API 연동시 아이디@공급자로 이메일 등록
            if(memberService.getMemberByEmail(fakeEmail)==null) // 기존 회원인지 확인
                memberService.signUp(fakeEmail,nickName,null); // 기존 회원이 아닐 시 DB 등록
            access_token = getJWTTokenFromMember(id, nickName, identify);
        }
        URI front = new URI("http://13.209.213.138/?token="+access_token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(front);
        return new ResponseEntity(httpHeaders, HttpStatus.SEE_OTHER);
    }

    private StringBuffer getResponse(String URL, String header) {
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (header != null || header != "")
                if (header == "application/json")
                    con.setRequestProperty("Accept", header);
                else
                    con.setRequestProperty("Authorization", header);

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            return res;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    private String getUserInfo(String access_token, String userTokenURL) {
        String header = "Bearer " + access_token; // Bearer 다음에 공백 추가
        return getResponse(userTokenURL, header).toString();
    }

}
