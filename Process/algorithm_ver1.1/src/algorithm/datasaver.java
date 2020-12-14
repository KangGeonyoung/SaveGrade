package algorithm;

public class datasaver {
	private String name = null; //과목 이름
	private int credit; //학점 : 1~5 이내의 숫자
	private int week;
	private String day = null; //"월", "화", "수", "목", "금"
	private String time = null; // HHMM형식의 String type
	private boolean major = true; //true면 전공, false면 교양
	private int ppt; // 1~5의 수치 - {"적음", "조금 적음", "보통", "많음", "매우 많음"}와 매치됨
	private int diff; // 1~3의 수치 -  {"상", "중", "하"}와 매치됨
	
	
	//setter
	public void setStringValue(String name, String day, String time) {
		this.name = name;
		this.day = day;
		this.time = time;
	}
	
	public void setIntValue(int credit, int ppt, int diff, int week) {
		this.credit = credit;
		this.ppt = ppt;
		this.diff = diff;
		this.week = week;
	}
	
	public void setBoolValue(boolean major) {
		this.major = !major;
		this.ppt = ppt;
	}
	

	
	//getter
	public void getall() { //test용
		System.out.print(getName() + " ");
		System.out.print(getDay() + " ");
		System.out.print(getTime() + " ");
		System.out.print(getCredit() + " ");
		System.out.print(getPPT() + " ");
		System.out.print(getDiff() + " ");
		System.out.print(getMajor() + " \n");
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getDay() {
		int d = 0;
		
		if(week == 0) {
			if(day.equals("월")) {
				d = 10;
			}
			else if(day.equals("화")) {
				d = 9;
			}
			else if(day.equals("수")) {
				d = 8;
			}
			else if(day.equals("목")) {
				d = 7;
			}
			else if(day.equals("금")) {
				d = 6;
			}
		}
		else if(week == 1) {
			if(day.equals("월")) {
				d = 5;
			}
			else if(day.equals("화")) {
				d = 4;
			}
			else if(day.equals("수")) {
				d = 3;
			}
			else if(day.equals("목")) {
				d = 2;
			}
			else if(day.equals("금")) {
				d = 1;
			}
		}
		return d;
	}
	
	public int getTime() {
		
		return Integer.parseInt(time);
	}

	public int getCredit() {
		return credit;
	}
	
	public int getPPT() {
		return ppt;
	}
	
	public int getDiff() {
		if(ppt == 1) {
			return 5;

		}
		else if(ppt == 2) {
			return 4;

		}
		else {
			return 3;
		}
	}
	
	public boolean getMajor() {
		return major;
	}
	
}
