package algorithm;

import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crowling {

	private static String ID = null;
	private static String password = null;
	private static String[] date = {"2020-11-16", "2020-11-17", "2020-11-18", "2020-11-19", "2020-11-20"};
	public static String[][] info = new String[10][6];
	//과목 이름, 시간, 전공여부, 학점, 학수번호, 요일(1~5까지의 숫자로 지정됨)
	public static int len; //등록된 과목의 수
	public static boolean state = false; //외부 GUI에서 이거 보고 분기 판단
	
	/*
	 * public crowling(String ID, String password) { this.ID = ID; this.password =
	 * password; }
	 */
	public crowling(String id, String pw) {
		ID = id;
		password = pw;
	}
	

	public boolean crowling() throws Exception {

	        Connection.Response login = Jsoup.connect("https://att.gachon.ac.kr/ajax/PU_MNMN01_SVC/PU_MNMN01_LOGIN.do?USER_ID=" + ID + 
	        											"&USER_PW=" + password + "&isMobile=true&language=ko`,\r\n" + "")
	                            .method(Connection.Method.POST)
	                            .timeout(10000)
	                            .execute();    
	        
	        Map<String, String> loginCookie = login.cookies();
	        len = 0;
	        
	        System.out.println(ID);
	        
	        if (login.body().contains("\"loginChk\":false")) {
	        	state = false;
		        System.out.println(state);

	        	return false;
	        }
	        

	        
	        for(int i=0;i<5;i++) { //5일동안의 시간표를 돌린다
		        Document doc = Jsoup.connect("https://att.gachon.ac.kr/ajax/ST_SALA02_SVC/ST_SALA02_R01.do?FROM_YMD=" 
		        													+ date[i] + "&DT=" + date[i] + "")
		        		.cookies(loginCookie)
		        		.get();

		        String text = doc.getElementsByTag("body").text();

		        text = text.substring(1, text.length()-2);
		        String textline[] = text.split("},");
		        
		        for(String t : textline) {
		        	String element[] = t.split(",");
		        	
			        for(String s : element) {
			        	String ckname;
			        	int ck = 0;

			        	//이미 등록이 된 강의면 넘어간다
			        	if (s.contains("\"LECT_NM\"")) {
			        		ckname = s.substring(11, s.length() - 1);
			        		
			        		for(int j=0;j<len;j++) {
			        			if(ckname.compareToIgnoreCase(info[j][0]) == 0) {
			        				ck = 1;
			        				break;
			        			}
			        		}
			        		if(ck == 1) break;
			        	}
			        	
			        	if(s.contains("\"LECT_NM\"")) info[len][0] = s.substring(11, s.length() - 1);
			        	if(s.contains("\"PERIOD_NM\"")) info[len][1] = s.substring(13, s.length() - 9);
			        	if(s.contains("\"COMPLETE_CD_NM\"")) info[len][2] = s.substring(18, s.length() - 1);
			        	if(s.contains("\"LECT_CD\"")) {
			        		info[len][4] = s.substring(11, s.length() - 1);
			        		info[len][5] = Integer.toString(i + 1);
				        	len++;
			        	}
			        }
		        } 
	        }
	        
	        for(int i = 0; i < len ; i++) {
		        //학점을 얻기 위한 부분
		        Document credittable = Jsoup.connect("http://sg.gachon.ac.kr/main?attribute=timeTableJson&lang=ko&year=2020&hakgi=20&menu=1&p_isu_cd=3&p_univ_cd=CE0000&p_maj_cd=CE0160&p_cor_cd=&p_gwamok_nm="
						+ info[i][0]
						+ "").get();

		        String credittext = credittable.getElementsByTag("body").text();

		        credittext = credittext.substring(10, credittext.length() - 3);
		        String creditline[] = credittext.split("},");
		        
		        int ck = 0;
		        for(String c : creditline) {
		        	String c_element[] = c.split(",");
		        	
			        for(String s : c_element) {
			        	if(s.contains(info[i][4])) ck = 1;
			        	if(ck == 1 && s.contains("credit")) info[i][3] = new String(s.substring(10, s.length() - 1));
			        }
			        if(ck == 1) break;
		        }
	        }
	        
	        
//	        for(String[] k : info) {
//	        	for(String j : k) {
//	        		System.out.print(j + " ");
//	        	}
//	        	System.out.println();
//	        }
	        
        	state = true;
	        return true;
	    }
}
